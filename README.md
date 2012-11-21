#導入確認のための環境構築手順

##データベース作成  
#####rootで実行

    mysql> create database javaee6multidatasource01;
    mysql> create database javaee6multidatasource02;

##ユーザ作成  
#####rootで実行

    mysql> grant all privileges on javaee6multidatasource01.* to 'glassfish'@'%' identified by 'glassfish';
    mysql> grant all privileges on javaee6multidatasource02.* to 'glassfish'@'%' identified by 'glassfish';
    mysql> flush privileges;

##データベースその1のテーブルとデータ  
#####MySQLに作成したglassfishユーザで実行

    mysql> create table Sample (`id` int not null auto_increment, `name` varchar(32), primary key(id));
    mysql> insert into Sample (name) values ('A');

##データベースその2のテーブルとデータ  
#####MySQLに作成したglassfishユーザで実行

    mysql> create table Sample (`id` int not null auto_increment, `name` varchar(32), primary key(id));
    mysql> insert into Sample (name) values ('B');

##GlassFish設定

    $ asadmin create-jdbc-connection-pool --datasourceclassname com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource --restype javax.sql.DataSource --property "User=glassfish:Password=glassfish:URL=jdbc\:mysql\://dbserver/javaee6multidatasource01" javaee6multidatasource01Pool
    $ asadmin create-jdbc-connection-pool --datasourceclassname com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource --restype javax.sql.DataSource --property "User=glassfish:Password=glassfish:URL=jdbc\:mysql\://dbserver/javaee6multidatasource02" javaee6multidatasource02Pool
    $ asadmin ping-connection-pool javaee6multidatasource01Pool
    $ asadmin ping-connection-pool javaee6multidatasource02Pool
    $ asadmin create-jdbc-resource --connectionpoolid javaee6multidatasource01Pool jdbc/ds01
    $ asadmin create-jdbc-resource --connectionpoolid javaee6multidatasource02Pool jdbc/ds02

##確認URL  
画面をリロードするとAとBの何れかが表示されます。  
jdbc/ds01に接続されるとAが、jdbc/ds02に接続されるとBが表示されます。  
[http://localhost:8080/javaee6-multi-datasource-sample/faces/sample.xhtml](http://localhost:8080/javaee6-multi-datasource-sample/faces/sample.xhtml)
