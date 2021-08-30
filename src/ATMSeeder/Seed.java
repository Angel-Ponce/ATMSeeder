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
import java.util.Scanner;

/**
 * Use this seed to generate a default data to the ATM project
 *
 * @author Angel Ponce
 */
public class Seed {

    private static final Random R = new Random();
    private static final Scanner READ = new Scanner(System.in);
    public static final String RED = "\033[0;31m";
    public static final String CYAN = "\033[0;36m";
    public static final String GREEN = "\033[0;32m";
    public static final String WHITE_BACKGROUND = "\033[47m";
    public static final String WHITE_BOLD = "\033[1;37m";
    public static final String WHITE = "\033[0;37m";
    public static final String RESET = "\033[0m";

    public static void main(String[] args) {
        int size = 0;
        do {
            try {
                System.out.print(WHITE + "Size of users: ");
                String entry = READ.nextLine();
                System.out.println("");
                size = Integer.parseInt(entry);
            } catch (Exception e) {
                System.err.println(RED + "Size must be a number (integer)");
                System.err.println("");
            }
        } while (size == 0);
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
        String adminName = Data.NAME_MAN[R.nextInt(Data.NAME_MAN.length)];
        String adminLastName = Data.LASTNAME_MAN[R.nextInt(Data.LASTNAME_MAN.length)];
        int adminAge = R.nextInt(50);
        String adminEmail = adminName + "@gmail.com";
        int adminPin = 3 * R.nextInt(100) + 1000;
        Date adminLastAccess = new Date();
        String adminPick = Data.PICK_MAN[R.nextInt(Data.PICK_MAN.length)];
        Helper.moveFile("/Resources/mens/" + adminPick, "database/profiles/" + adminPick);
        Admin admin = new Admin(adminName, adminLastName, adminAge, adminEmail, adminPin, adminLastAccess, "database/profiles/" + adminPick);
        persons.add(admin);

        System.out.println("seeding...");
        System.out.append(WHITE + "[ ");
        for (int i = 1; i <= size; i++) {
            int cardNumber = i;
            int currentBalance = R.nextInt(10000);
            int maximumAmount = R.nextInt(10000);
            String sex = Data.SEX[R.nextInt(Data.SEX.length)];
            String name = "user";
            String lastName = "" + i;
            String pick = Data.PICK_MAN[R.nextInt(Data.PICK_MAN.length)];
            if (sex.equals(Data.MAN)) {
                name = Data.NAME_MAN[R.nextInt(Data.NAME_MAN.length)];
                lastName = Data.LASTNAME_MAN[R.nextInt(Data.LASTNAME_MAN.length)];
                pick = Data.PICK_MAN[R.nextInt(Data.PICK_MAN.length)];
            } else if (sex.equals(Data.WOMAN)) {
                name = Data.NAME_WOMAN[R.nextInt(Data.NAME_WOMAN.length)];
                lastName = Data.LASTNAME_WOMAN[R.nextInt(Data.LASTNAME_WOMAN.length)];
                pick = Data.PICK_WOMAN[R.nextInt(Data.PICK_WOMAN.length)];
            }
            int age = R.nextInt(75);
            String email = cardNumber + "";
            int pin = cardNumber;
            Date lastAccess = new Date();

            User user = new User(cardNumber, currentBalance, maximumAmount, name, lastName, age, email, pin, lastAccess, "database/profiles/" + pick);
            Helper.moveFile("/Resources/" + sex + "/" + pick, "database/profiles/" + pick);
            persons.add(user);
            System.out.append(WHITE_BACKGROUND + " ");
        }
        System.out.println(WHITE + " ]");

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
            System.out.println(GREEN + "*----------------------------*");
            System.out.println(WHITE_BOLD + "    Seeder run successfuly");
            System.out.println(GREEN + "*----------------------------*");
            System.out.println("");
            System.out.println(GREEN + "*----------------------------*");
            System.out.println(WHITE_BOLD + "      ADMIN CREDENTIALS");
            System.out.println(GREEN + "*----------------------------*");
            System.out.println("");
            System.out.println(WHITE + "Admin email: " + WHITE_BOLD + adminEmail);
            System.out.println(WHITE + "Admin password " + WHITE_BOLD + adminPin);
            System.out.println("");
            System.out.println(GREEN + "*----------------------------*");
            System.out.println(WHITE_BOLD + "   USER CREDENTIALS PATTERN");
            System.out.println(GREEN + "*----------------------------*");
            System.out.println("");
            System.out.println(WHITE + "Each user follow the next pattern");
            System.out.println(WHITE + "User email: " + WHITE_BOLD + "${number}");
            System.out.println(WHITE + "User card number: " + WHITE_BOLD + "${number}");
            System.out.println(WHITE + "User password: " + WHITE_BOLD + "${number}");
            System.out.println("");
            System.out.println(WHITE_BOLD + "The ${number} variable is defined in a set between \"1\" and \"" + size + "\"");
            System.out.println("");
            System.out.println(GREEN + "*----------------------------*");
            System.out.println(WHITE_BOLD + "    Seeder run successfuly");
            System.out.println(GREEN + "*----------------------------*");
            System.out.println("");
        } else {
            System.err.println(RED + "Seeder failed");
        }
    }
}
