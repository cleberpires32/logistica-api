CREATE TABLE `dblogistica`.cliente(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(50) NOT NULL,
email VARCHAR(50) NOT NULL,
telefone VARCHAR(12) NULL
)engine = InnoDB charset=utf8;