CREATE TABLE Ordem_servico(
    id bigint not null auto_increment,
    cliente_id bigint not null,
    descricao text not null,
    preco decimal(10,2) not null,
    status varchar(20) not null,
    data_abertura datetime not null,
    data_finalizacao datetime,
    
    PRIMARY KEY (id)
);

ALTER TABLE ordem_servico ADD CONSTRAINT fk_ordem_servico_cliente
FOREIGN KEY (cliente_id) REFERENCES cliente (id);