CREATE TABLE `dblogistica`.entrega(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
cliente_id BIGINT NOT NULL,
taxa DECIMAL(10,2) NOT NULL,
status_entrega VARCHAR(20) NOT NULL,
data_pedido datetime NOT NULL,
data_finalizacao datetime NOT NULL,

destinatario_nome VARCHAR(60) NOT NULL,
destinatario_logradouro VARCHAR(255) NOT NULL,
destinatario_numero VARCHAR(30) NOT NULL,
destinatario_complemento VARCHAR(60) NOT NULL,
destinatario_bairro VARCHAR(30) NOT NULL

)engine = InnoDB charset=utf8;

alter table `dblogistica`.entrega add constraint fk_entrega_cliente foreign key (cliente_id) references `dblogistica`.cliente (id);