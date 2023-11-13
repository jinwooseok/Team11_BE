CREATE
USER 'root'@'localhost' IDENTIFIED BY 'root';
CREATE
USER 'root'@'%' IDENTIFIED BY 'root';

GRANT ALL PRIVILEGES ON *.* TO
'root'@'localhost';
GRANT ALL PRIVILEGES ON *.* TO
'root'@'%';

CREATE
DATABASE krampoline DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
