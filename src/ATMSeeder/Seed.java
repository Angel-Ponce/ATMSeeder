package ATMSeeder;

import Entity.Person;
import Entity.Properties;
import Entity.Admin;
import Entity.Ticket;
import Entity.Transaction;
import Entity.User;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args) {
        int size = 0;
        int transactions = 10;
        String adminName = "Super";
        String adminLastName = "admin";
        int adminPin = 12345;
        do {
            try {
                System.out.print(WHITE + "Size of users: " + CYAN);
                String entry = READ.nextLine();
                System.out.println("");
                size = Integer.parseInt(entry);

                System.out.print(WHITE + "Transactions per user: " + CYAN);
                entry = READ.nextLine();
                System.out.println("");
                transactions = Integer.parseInt(entry);

                System.out.print(WHITE + "Admin name: " + CYAN);
                adminName = READ.nextLine();
                System.out.println("");

                System.out.print(WHITE + "Admin last name: " + CYAN);
                adminLastName = READ.nextLine();
                System.out.println("");

                System.out.print(WHITE + "Admin pin: " + CYAN);
                entry = READ.nextLine();
                System.out.println("");
                adminPin = Integer.parseInt(entry);

            } catch (NumberFormatException e) {
                System.err.println(RED + "Size and pin must be a number (integer)");
                System.err.println("");
            }
        } while (size == 0);
        generate(size, adminName.trim(), adminLastName.trim(), adminPin, transactions);
    }

    public static void generate(int size, String adminName, String adminLastName, int adminPin, int transactions) {

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
        int adminAge = R.nextInt(50);
        String adminEmail = adminName + "@gmail.com";
        Date adminLastAccess = new Date();
        String adminPick = Data.PICK_MAN[R.nextInt(Data.PICK_MAN.length)];
        Helper.moveFile("/Resources/mens/" + adminPick, "database/profiles/" + adminPick);
        Admin admin = new Admin(adminName, adminLastName, adminAge, adminEmail, adminPin, adminLastAccess, "database/profiles/" + adminPick);
        persons.add(admin);
        QueryBuilder.insert(admin); //Insert into database
        System.out.println("seeding...");
        System.out.print(WHITE + "[ ");
        for (int i = 1; i <= size; i++) {
            if (String.valueOf(i).length() >= 16) {
                System.out.println(RED + "Limit of users has been exceded");
                break;
            }
            String card = i + "0".repeat(16 - String.valueOf(i).length());
            long cardNumber = Long.valueOf(card);
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
            int pin = Integer.valueOf(card.substring(0, 4));
            Date lastAccess = new Date();

            User user = new User(cardNumber, currentBalance, maximumAmount, name, lastName, age, email, pin, lastAccess, "database/profiles/" + pick);
            for (int j = 0; j < transactions; j++) {
                String type = Data.TRANSACTION[R.nextInt(Data.TRANSACTION.length)];
                switch (type) {
                    case Transaction.RETREAT:
                        user.viewLatestTransactions().add(
                                new Transaction(R.nextInt(5000), Transaction.RETREAT, new GregorianCalendar(2020, R.nextInt(12), R.nextInt(29), R.nextInt(24), R.nextInt(60), R.nextInt(60)).getTime())
                        );
                        break;
                    case Transaction.DEPOSIT:
                        user.viewLatestTransactions().add(
                                new Transaction(R.nextInt(5000), Transaction.DEPOSIT, new GregorianCalendar(2020, R.nextInt(12), R.nextInt(29), R.nextInt(24), R.nextInt(60), R.nextInt(60)).getTime())
                        );
                        break;
                    default:
                        user.viewLatestTransactions().add(
                                new Transaction(R.nextInt(5000), Transaction.DEPOSIT, new GregorianCalendar(2020, R.nextInt(12), R.nextInt(29), R.nextInt(24), R.nextInt(60), R.nextInt(60)).getTime())
                        );
                        break;
                }
            }
            //Sending some changes of pin
            user.setCountPinChanged(R.nextInt(10));
            Helper.moveFile("/Resources/" + sex + "/" + pick, "database/profiles/" + pick);
            persons.add(user);
            QueryBuilder.insert(user); //Insert user into database
            user.viewLatestTransactions().forEach(t -> {
                QueryBuilder.insert(t, user.getCardNumber()); //Insert all transactions into database
            });
            System.out.print(WHITE_BACKGROUND + " ");
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
        QueryBuilder.insert(tickets); //Insert all tickets into database
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        now.add(Calendar.DAY_OF_MONTH, -1);
        Properties properties = new Properties("", 3860, persons.get(1), DATE_FORMAT.format(now.getTime()));
        QueryBuilder.insert(properties); //Insert properties into database
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
            System.out.println(WHITE + "User email: " + WHITE_BOLD + "${number}+0{16-$number.length}");
            System.out.println(WHITE + "User card number: " + WHITE_BOLD + "${number}+0{16-$number.length}");
            System.out.println(WHITE + "User password: " + WHITE_BOLD + "${email}.subString(0,4)");
            System.out.println("");
            System.out.println(WHITE_BOLD + "The ${number} variable is defined in a set between \"1\" and \"" + size + "\"");
            System.out.println(CYAN + "Example: ");
            System.out.println(WHITE + "User " + WHITE_BOLD + "No. 1, " + WHITE + "email: " + WHITE_BOLD + "1000000000000000, " + WHITE + "password/pin: " + WHITE_BOLD + "1000, " + WHITE + "Number card: " + WHITE_BOLD + "1000000000000000");
            System.out.println("");
            System.out.println(CYAN + "Other Example: ");
            System.out.println(WHITE + "User " + WHITE_BOLD + "No. 136, " + WHITE + "email: " + WHITE_BOLD + "1360000000000000, " + WHITE + "password/pin: " + WHITE_BOLD + "1360, " + WHITE + "Number card: " + WHITE_BOLD + "1360000000000000");
            System.out.println("");
            System.out.println(WHITE_BOLD + "***Always you can see your database to know all information of this users***");
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
