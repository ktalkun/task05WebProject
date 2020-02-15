package by.tolkun.barbershop.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "by.tolkun.barbershop")
@EnableAspectJAutoProxy
@PropertySource(value = "classpath:db.properties")
public class SpringConfig {

    private Environment environment;

    @Autowired
    public SpringConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate(){
        return new JdbcTemplate(getDataSource());
    }

    @Bean
    public DataSource getDataSource(){
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(environment.getProperty("jdbc.driverClassName"));
        dataSource.setJdbcUrl(environment.getProperty("jdbc.url"));
        dataSource.setUsername(environment.getProperty("jdbc.username"));
        dataSource.setPassword(environment.getProperty("jdbc.password"));
        return dataSource;
    }
}
