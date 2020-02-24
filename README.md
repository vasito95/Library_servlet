# Library ( Option №16)

User can place an order for book which is available now.
Admin can confirm this order and give book to user or decline order.
Also admin can add, edit, delete books from library, and see all readers with books they are reading.


# Requirments

JDK 1.8  
Apache Maven  
Apache Tomcat  
MySQL

## Usage

Clone project to local repository  
Run SQL scripts(creation.sql , fill.sql) from resources folder to create and fill database.  
Go to database.property file and change usernname and password for database.
From root folder run - mvn tomcat7:run  
Use http://localhost:8088/app to open the app

## Use below emails and password to login as user and admin
admin1@gmail.com  Admin%1admin  
user1@gmail.com   User%1user

```

