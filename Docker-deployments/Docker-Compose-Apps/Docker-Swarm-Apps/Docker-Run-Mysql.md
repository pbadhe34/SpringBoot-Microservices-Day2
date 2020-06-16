 

Run MySQL 5.6 in Docker container:

 
docker run --name mysql -e MYSQL_ROOT_PASSWORD=MyRootPass123 -e MYSQL_DATABASE=userservice -e MYSQL_USER=app-user -e MYSQL_PASSWORD=MyRootPass123 -d mysql:5.6
 

Check the log to make sure the mysql server is running OK:
 
docker logs mysql

To run MYSQL with Volume specified
Create a data directory on a suitable volume on your host system, e.g. /c/Users/Prakash/mysql/mysqldata.

Run MySQL 5.6 in Docker container:

All the characters in the names of volume must be lowercase
C:\Program Files\Docker Toolbox\docker.exe: invalid reference format: repository name must be lowercase.

The Volume specified here is used across the containers start and stop and will maniatin the database state across containers and across start and stop operations.

c/Users/Prakash/Docker-User-Service/data/mysqldata

Make sure that the mysqldata folder/shared volume exists on the given path on the Host OS

On Windows

docker run --name app-mysql -v /d/data/mysqldata:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=MyRootPass123 -e MYSQL_DATABASE=userservice -e MYSQL_USER=app-user -e MYSQL_PASSWORD=MyRootPass123 -d mysql:5.6 


docker run --name app-mysql -v /d/data/mysqldata:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=MyRootPass123 -e MYSQL_DATABASE=userservice -e MYSQL_USER=app-user -e MYSQL_PASSWORD=MyRootPass123 mysql:5.6 


Another insdtance of MySQL is using the same volume
'
 On Linux
docker run --name mysql -v /home/mysqldata:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=MyRootPass123 -e MYSQL_DATABASE=userservice -e MYSQL_USER=app-user -e MYSQL_PASSWORD=MyRootPass123 -d mysql:5.6

docker logs app-mysql

To create some initial records and tables in mysql container
 
Solution to : File not found
Copy the files from host system into the Docker container context
The cp command can be used to copy files from host to docker container and reverse.
One specific file can be copied like:



docker exec -it app-mysql bash

To deploy initila records into MysQL Conatiner

Copy the file from host machine to the container
docker cp table.sql 253b5e322ace:/table.sql
docker cp records.sql 253b5e322ace:/records.sql

docker exec -it 253b5e322ace bash


To import and execute sql  script in container

Run these from bash terminal to import sql file into DB 

File copied to container from Host machine and ready to execute
mysql  -u root  -p userservice < table.sql

mysql  -u root  -p userservice < records.sql

mysql -uroot -p userservice -e "select * from users;"



Copy From container to Host OS
docker cp mysql:/user.txt data.txt

Multiple files contained by the folder src can be copied into the target folder using:

docker cp src/. mycontainer:/target
docker cp mycontainer:/src/. target
 

Connect to the container

docker exec -it app-mysql bash


mysql -uroot -p -e "Create Schema data;"

mysql -uroot -p data -e "create table users (id bigint not null, first_name varchar(255), last_name varchar(255), user_name varchar(255), primary key (id)) engine=MyISAM;"  


mysql -uroot -p data -e "describe users;"

mysql -uroot -p data -e "Insert into users values(4,'Mohan','Bhragav','Swadeshi');"

mysql -uroot -p data -e "select * from users;"


********************//Import data*****//

Copy the file from host machine to the container
docker cp table.sql app-mysql:/table.sql
docker cp records.sql app-mysql:/records.sql

Connect to the container

docker exec -it app-mysql bash

To import and execute sql  script in container

Run these from bash terminal to import sql file into DB 

File copied to container from Host machine and ready to execute
mysql  -u root  -p userservice < table.sql

mysql  -u root  -p userservice < records.sql



mysql -uroot -p userservice -e "Insert into users values(6,'Jijaja','Babawe','Datare');"


mysql -uroot -p userservice -e "commit" 

******///////////////////////**********
 
Run SQL Commands into MYSQl DB container
***************************////******************

docker exec -it app-mysql bash

mysql -uroot -p userservice -e "create table users (id bigint not null, first_name varchar(255), last_name varchar(255), user_name varchar(255), primary key (id)) engine=MyISAM;" 


mysql -uroot -p userservice -e "create table hibernate_sequence(next_val bigint not null) engine=MyISAM;"

mysql -uroot -p userservice -e "insert into hibernate_sequence values ( 1 );"
 

create table hibernate_sequence(next_val bigint not null) engine=MyISAM;

mysql -uroot -p userservice -e "select * from users"  

mysql -uroot -p userservice -e "select * from hibernate_sequence"  


mysql -uroot -p userservice -e "delete  from users where id=4" 
mysql -uroot -p userservice -e "describe users"  

mysql -uroot -p userservice -e "describe hibernate_sequence"

mysql -uroot -p userservice -e "select * from users"  


mysql -uroot -p userservice -e "DROP table users"  //Enter password

mysql -uroot -p userservice -e "commit"



******************///***** 


docker exec -it mysql bash

To directly run without terminal
docker exec app-mysql sh -c 'exec mysql userservice -uroot -p"MyRootPass123" < table.sql'

docker exec app-mysql sh -c 'exec mysql -p userservice -uroot < table.sql'

docker exec app-mysql sh -c 'exec mysql -p userservice -uroot < /c/Users/Prakash/table.sql'

docker exec app-mysql sh -c 'exec pwd'

docker exec app-mysql sh -c 'exec mysql -V' 

Get the SQL DUMP to out file
To get the mysql database dump to output file

docker exec -it mysql bash
#ls
#pwd

OR Directly run the command
docker exec mysql sh -c 'exec mysqldump userservice -uroot -p"MyRootPass123"' > /c/Users/Prakash/data.sql

docker exec app-mysql sh -c 'exec mysqldump userservice -uroot -p"MyRootPass123" > /c/Users/Prakash/app-data.sql'


Run sleect query
docker exec app-mysql sh -c 'exec mysql SELECT * from userservice.users AS Id FROM userservice.Users -uroot -p"MyRootPass123"'
 

docker exec app-mysql sh -c 'exec mysqldump userservice -uroot -p"MyRootPass123"' > /c/Users/Prakash/records.sql

 
SELECT COUNT(Id) AS Id FROM userservice.Users;

SELECT DATABASE();

SELECT VERSION();

**************************

Another way to add records at startup

Simply just put your Data.sql file (dbcreation.sql) in a folder (ie. /mysql_init) and add the folder as a volume like this:

volumes:
  - /mysql_init:/docker-entrypoint-initdb.d
The MySQL image will execute all .sql, .sh and .sql.gz files in /docker-entrypoint-initdb.d on startup.

*********************************
Build the image 'user-service-app'
Run user-service application in Docker container and link to mysql Container

  docker build -t user-service-app .

  docker build -t user-service-app:v1 .

  To Run the user-service-app image in detacheed mode

  docker run -d user-service-app in detacheed mode
 with app-mysql as linked container.
 
docker run -p 8090:8090 --name user-app -h java-host 
--link app-mysql:mysql  -d user-service-app 



The application decides the name.Because this name is the name 
of database host to whicvh the app is connecting


You can check the log by
 
docker logs user-app

curl http://192.168.99.100:8090/users
 

Open http://192.168.99.100:8090/users in browser  

