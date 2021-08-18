create table cidade (
	id bigint not null auto_increment,
    nome_cidade nvarchar(80) not null,
    nome_estado nvarchar(80) not null,
    
    primary key(id)
) engine=InnoDB default charset=utf8;