CREATE TABLE `dblogistica`.ocorrencia(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
entrega_id BIGINT NOT NULL,
descricao TEXT NOT NULL,
data_registro datetime NOT NULL

)engine = InnoDB charset=utf8;

alter table `dblogistica`.ocorrencia add constraint fk_ocorrencia_entrega foreign key (entrega_id) references `dblogistica`.entrega (id);