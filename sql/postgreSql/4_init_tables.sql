\c barbershop_db;

INSERT INTO users (login,
                   password,
                   name,
                   surname,
                   patronymic,
                   email,
                   phone,
                   image_path,
                   role)
VALUES ('admin',
        '$2a$10$nEu7exwosM3vsWzVmJeobuD257U.II8xK6tGyRpcSqjJpuoC58Gwq',
        'admin',
        'admin',
        'admin',
        'admin@gmail.com',
        00000000,
        'resources/upload/avatars/admin.jpg',
        0);