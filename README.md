# ATMSeeder 🌱
A simple seeder to generate user data

## Features ✔
- Comand line interface (CLI)
- Can generate 1800 diferent users
- Contains a folder with pretty pictures
- `.jar` version its avaliable
- Contains a group of entitys to use (ATM context)
- Its a free library to use in your code, if you need to generate random entitys

## How to use ⚙ like plugin for [ATM](https://github.com/Angel-Ponce/ATM)
1. Download the `ATMSeeder.jar` version (into `/dist/` folder) or download here
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
  
  ![image](https://user-images.githubusercontent.com/60164099/131358772-a92a5388-d8b0-4309-97cf-ebf8f9448349.png)
  
If all steps are ready, you ready to use the **ATMSeeder 🌱** plugin
  
## How to use ⚙ like free library
1. Download the `ATMSeeder.jar` or clone the repository into your project
2. Import the library into your project
3. Create the follow directory structure in the root of your project
  
  ![image](https://user-images.githubusercontent.com/60164099/131364981-efef0c4c-338a-45c5-9290-31f19ec0d465.png)
  
4. Once you have the library imported you can follow the next code to use it
```java
  import AMTSeeder.*; //Import the library in your class
  
  public class someClass{
    
    public static void main(String args[]){
      int sizeOfUsers = 25;
      Seed.generate(sizeOfUsers); //This write the files with all information in the root project
      //Now, to get the information you can use:
  
      ArrayList<Person> users = Helper.getObjectFromFile("database/Persons.txt");
      ArrayList<Ticket> tickets = Helper.getObjectFromFile("database/Tickets.txt");
      Properties properties = Helper.getObjectFromFile("database/Properties.txt");
  
      //Now you can use your data
      System.out.println("List of users");
      users.forEach((u)->{
        System.out.println(u);
      });
      
      System.out.println("Avaliable tickets");
      tickets.forEach((t)->{
        System.out.println(t.getType()+", "+t.getSize());
      });
  
      System.out.println("ATM current balance: "+properties.getCurrentBalance());
      System.out.println("ATM current theme: "+properties.getTheme());
      System.out.println("ATM latest person loged: "+propertes.getLastPerson());
  
  }
```
  
### Enjoy
  
# Author
[Angel Ponce](https://github.com/angel-ponce)
