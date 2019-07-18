package by.tolkun.barbershop.dao.pool;

import exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Pool of connections.
 *
 * @author Kirill Tolkun
 */
final public class ConnectionPool {
    /**
     * Logger of class {@code ConnectionPool}.
     */
    private static Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private Lock lock;
    private String url;
    private String user;
    private String password;
    private int maxSize;
    private int checkConnectionTimeout;
    private BlockingQueue<PooledConnection> freeConnections;
    private Set<PooledConnection> usedConnections;

    /**
     * Default constructor.
     */
    private ConnectionPool() {
        freeConnections = new LinkedBlockingQueue<>();
        usedConnections = new ConcurrentSkipListSet<>();
        lock = new ReentrantLock();
    }

    /**
     * Get instance.
     *
     * @return instance of connection pool
     */
    public static ConnectionPool getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * Get connection from pool.
     *
     * @return pooled connection
     * @throws PersistentException if impossible get pooled connection
     */
    public Connection getConnection() throws PersistentException {
        lock.lock();
        PooledConnection connection = null;
        while (connection == null) {
            try {
                if (!freeConnections.isEmpty()) {
                    connection = freeConnections.take();
                    if (!connection.isValid(checkConnectionTimeout)) {
                        try {
                            connection.getConnection().close();
                        } catch (SQLException e) {
                        }
                        connection = null;
                    }
                } else if (usedConnections.size() < maxSize) {
                    connection = createConnection();
                } else {
                    LOGGER.error("The limit of number of database connections is exceeded");
                    throw new PersistentException();
                }
            } catch (InterruptedException | SQLException e) {
                LOGGER.error("It is impossible to connect to a database", e);
                throw new PersistentException(e);
            }
        }
        usedConnections.add(connection);
        LOGGER.debug(String.format("Connection was received from pool. Current pool size: %d used connections; %d free connection", usedConnections.size(), freeConnections.size()));
        lock.unlock();
        return connection;
    }

    /**
     * Free connection.
     *
     * @param connection to free.
     */
    synchronized void freeConnection(PooledConnection connection) {
        try {
            if (connection.isValid(checkConnectionTimeout)) {
                connection.clearWarnings();
                connection.setAutoCommit(true);
                usedConnections.remove(connection);
                freeConnections.put(connection);
                LOGGER.debug(String.format("Connection was returned into pool. Current pool size: %d used connections; %d free connection", usedConnections.size(), freeConnections.size()));
            }
        } catch (SQLException | InterruptedException e1) {
            LOGGER.warn("It is impossible to return database connection into pool", e1);
            try {
                connection.getConnection().close();
            } catch (SQLException e2) {
            }
        }
    }

    /**
     * @param driverClass            the driver class
     * @param url                    url of database
     * @param user                   of database
     * @param password               password of user in database
     * @param startSize
     * @param maxSize
     * @param checkConnectionTimeout
     * @throws PersistentException
     */
    public synchronized void init(
            final String driverClass,
            final String url,
            final String user,
            final String password,
            final int startSize,
            final int maxSize,
            final int checkConnectionTimeout
    ) throws PersistentException {
        try {
            destroy();
            Class.forName(driverClass);
            this.url = url;
            this.user = user;
            this.password = password;
            this.maxSize = maxSize;
            this.checkConnectionTimeout = checkConnectionTimeout;
            for (int counter = 0; counter < startSize; counter++) {
                freeConnections.put(createConnection());
            }
        } catch (ClassNotFoundException | SQLException | InterruptedException e) {
            LOGGER.fatal("It is impossible to initialize connection pool", e);
            throw new PersistentException(e);
        }
    }

    /**
     * Create pooled connection.
     *
     * @return pooled connection
     * @throws SQLException if a database access error occurs or the url is null
     */
    private PooledConnection createConnection() throws SQLException {
        return new PooledConnection(
                DriverManager.getConnection(url, user, password)
        );
    }

    /**
     * Destroy connection pool.
     */
    public synchronized void destroy() {
        usedConnections.addAll(freeConnections);
        freeConnections.clear();
        for (PooledConnection connection : usedConnections) {
            try {
                connection.getConnection().close();
            } catch (SQLException e) {
            }
        }
        usedConnections.clear();
    }

    /**
     * Latest chance to destroy connection pool.
     */
    @Override
    protected void finalize() {
        destroy();
    }

    /**
     * Class to hold instanse of {@code ConnectionPool}.
     */
    private static class SingletonHolder {
        /**
         * Instance of connection pool.
         */
        private final static ConnectionPool INSTANCE = new ConnectionPool();
    }
}
