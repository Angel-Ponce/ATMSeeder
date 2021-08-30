package ATMSeeder;

import Entity.Person;
import Entity.Properties;
import Entity.Admin;
import Entity.Ticket;
import Entity.User;
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

        ArrayList<Ticket> tickets = new ArrayList();
        tickets.add(new Ticket(1, 10));
        tickets.add(new Ticket(5, 10));
        tickets.add(new Ticket(10, 10));
        tickets.add(new Ticket(20, 10));
        tickets.add(new Ticket(50, 10));
        tickets.add(new Ticket(100, 10));
        tickets.add(new Ticket(200, 10));

        Properties properties = new Properties("", 3860, persons.get(0));

        if (Helper.saveObjectToFile(persons, "database/Persons.txt")
                && Helper.saveObjectToFile(tickets, "database/Tickets.txt")
                && Helper.saveObjectToFile(properties, "database/Properties.txt")) {
            System.out.println("Seeder run successfuly");
            System.out.println("*----- CREDENTIALS -----*");
            System.out.println("\tAdmin email: " + adminEmail);
            System.out.println("\tAdmin password " + adminPin);
        } else {
            System.err.println("Seeder failed");
        }
    }
}
