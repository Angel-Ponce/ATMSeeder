package ATMSeeder;

import Entity.Person;
import Entity.Properties;
import Entity.Admin;
import Entity.Ticket;
import Entity.User;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * Use this seed to generate a default data to the ATM project
 *
 * @author Angel Ponce
 */
public class Seed {

    private static Random r;

    public static void main(String[] args) {
        int size = 10;
        r = new Random();
        if (args.length > 0) {
            size = Integer.parseInt(args[0]);
        }
        generate(size);
    }

    public static void generate(int size) {

        //Remove all files in database/profiles dir
        File profiles = new File("database/profiles/");
        for (File child : profiles.listFiles()) {
            if (!child.isDirectory()) {
                child.delete();
            }
        }
        //Create an ArrayList of Persons
        ArrayList<Person> persons = new ArrayList<>();
        //First generate a unique admin
        String adminName = Data.NAME_MAN[r.nextInt(Data.NAME_MAN.length)];
        String adminLastName = Data.LASTNAME_MAN[r.nextInt(Data.LASTNAME_MAN.length)];
        int adminAge = r.nextInt(50);
        String adminEmail = adminName + "@gmail.com";
        int adminPin = 3 * r.nextInt(100) + 1000;
        Date adminLastAccess = new Date();
        String adminPick = Data.PICK_MAN[r.nextInt(Data.PICK_MAN.length)];
        Helper.moveFile("/Resources/mens/" + adminPick, "database/profiles/" + adminPick);
        Admin admin = new Admin(adminName, adminLastName, adminAge, adminEmail, adminPin, adminLastAccess, "database/profiles/" + adminPick);
        persons.add(admin);

        for (int i = 1; i <= size; i++) {
            int cardNumber = i;
            int currentBalance = r.nextInt(10000);
            int maximumAmount = r.nextInt(10000);
            String sex = Data.SEX[r.nextInt(Data.SEX.length)];
            String name = "user";
            String lastName = "" + i;
            String pick = Data.PICK_MAN[r.nextInt(Data.PICK_MAN.length)];
            if (sex.equals(Data.MAN)) {
                name = Data.NAME_MAN[r.nextInt(Data.NAME_MAN.length)];
                lastName = Data.LASTNAME_MAN[r.nextInt(Data.LASTNAME_MAN.length)];
                pick = Data.PICK_MAN[r.nextInt(Data.PICK_MAN.length)];
            } else if (sex.equals(Data.WOMAN)) {
                name = Data.NAME_WOMAN[r.nextInt(Data.NAME_WOMAN.length)];
                lastName = Data.LASTNAME_WOMAN[r.nextInt(Data.LASTNAME_WOMAN.length)];
                pick = Data.PICK_WOMAN[r.nextInt(Data.PICK_WOMAN.length)];
            }
            int age = r.nextInt(75);
            String email = cardNumber + "";
            int pin = cardNumber;
            Date lastAccess = new Date();

            User user = new User(cardNumber, currentBalance, maximumAmount, name, lastName, age, email, pin, lastAccess, "database/profiles/" + pick);
            Helper.moveFile("/Resources/" + sex + "/" + pick, "database/profiles/" + pick);
            persons.add(user);
        }

        ArrayList<Ticket> tickets = new ArrayList();
        tickets.add(new Ticket(1, 10));
        tickets.add(new Ticket(5, 10));
        tickets.add(new Ticket(10, 10));
        tickets.add(new Ticket(20, 10));
        tickets.add(new Ticket(50, 10));
        tickets.add(new Ticket(100, 10));
        tickets.add(new Ticket(200, 10));

        Properties properties = new Properties("", 3860, persons.get(1));

        if (Helper.saveObjectToFile(persons, "database/Persons.txt")
                && Helper.saveObjectToFile(tickets, "database/Tickets.txt")
                && Helper.saveObjectToFile(properties, "database/Properties.txt")) {

            System.out.println("");
            System.out.println("*---------------------------*");
            System.out.println("\tSeeder run successfuly");
            System.out.println("*---------------------------*");
            System.out.println("");
            System.out.println("*----- ADMIN CREDENTIALS -----*");
            System.out.println("\tAdmin email: " + adminEmail);
            System.out.println("\tAdmin password " + adminPin);
            System.out.println("");
            System.out.println("*----- USER CREDENTIALS PATTERN-----*");
            System.out.println("Each user follow the next pattern");
            System.out.println("\tUser email: ${number}");
            System.out.println("\tUser card number: ${number}");
            System.out.println("\tUser password: ${number}");
            System.out.println("The ${number} variable is defined in a set between \"1\" and \"" + size + "\"");
        } else {
            System.err.println("Seeder failed");
        }
    }
}
