データベースその1のテーブルとデータ

    mysql> create table Sample(`id` int not null auto_increment, `name` varchar(32), primary key(id));
    mysql> insert into Sample (name) values ('A');

データベースその2のテーブルとデータ

    mysql> create table Sample(`id` int not null auto_increment, `name` varchar(32), primary key(id));
    mysql> insert into Sample (name) values ('B');
