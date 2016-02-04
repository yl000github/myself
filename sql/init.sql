create database myself character set 'utf8';
drop table if exists affair;
create table affair(
id int unsigned auto_increment primary key ,
what varchar(20),
why varchar(100),
how varchar(100),
status varchar(10),
type varchar(10),
comment varchar(200),
create_time DateTime,
start_time DateTime,
done_time DateTime,
duration varchar(20)
);
