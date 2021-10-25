# ATMSeeder 🌱
A simple seeder to generate user data

## Features ✔
- Comand line interface (CLI)
- Can generate 1800 diferent users
- Contains a folder with pretty pictures
- `.jar` version its avaliable
- Contains a group of entitys to use (ATM context)
- Its a free library to use in your code, if you need to generate random entitys

## Database engine

You can find the `ATM.sql` file to mount the database schema (donwload or copy the sql scripts) [here](https://github.com/Angel-Ponce/ATM/blob/master/ATM.sql).

**Remember that this project run only on localhost environment**

You can configurate the database credentials on `src/ATMSeeder/Connecter.java` file

We recomend you to use the [SQLServer JDBC Driver](https://docs.microsoft.com/en-us/sql/connect/jdbc/microsoft-jdbc-driver-for-sql-server?view=sql-server-ver15)


## How to use ⚙ like plugin for [ATM](https://github.com/Angel-Ponce/ATM)
1. Download the `ATMSeeder.jar` version (into `/dist/` folder) or download [here](https://github.com/Angel-Ponce/ATMSeeder/raw/master/store/ATMSeeder.jar)
2. Make sure that you can the [`ATM.jar`](https://github.com/Angel-Ponce/ATM) version in your pc
3. Move the `ATMSeeder.jar` to folder that contains the `AMT.jar` application
4. Open a terminal in the same folder
5. Depend your system operating run the following command:
  ```bash
  $ java -jar AMTSeeder.jar
  ```
  <sub> *(You need the jdk11 version or higher)* <sub>
  
6. Use the CLI application
7. Make sure the application folder has the following structure
  
  ```bash
    |---root
    |------database
    |---------profiles
    |------ATM.jar
    |------ATMSeeder.jar
  ```
  
If all steps are ready, you ready to use the **ATMSeeder 🌱** plugin
  
## How to use ⚙ like free library
1. Download the `ATMSeeder.jar` or clone the repository into your project
2. Import the library into your project
3. Create the follow directory structure in the root of your project
  
  ```bash
    |---root
    |------database
    |---------profiles
    |------ATM source code structure
    |------ATMSeeder.jar
  ```
  
4. Once you have the library imported you can follow the next code to use it
```java
  import AMTSeeder.*; //Import the library in your class
  
  public class someClass{
    
    public static void main(String args[]){
      int sizeOfUsers = 25;
      Seed.generate(sizeOfUsers); //This write the files with all information in the root project
      //You can view your database engine to verify all data
  }
```
  
### Enjoy
  
# Author
[Angel Ponce](https://github.com/angel-ponce)
