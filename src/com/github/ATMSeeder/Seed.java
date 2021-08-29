package com.github.ATMSeeder;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import com.github.Entity.*;

/**
 * Use this seed to generate a default data to the ATM project
 *
 * @author Angel Ponce
 */
public class Seed {

    public static void main(String[] args) {
        generate();
    }

    public static void generate() {

        ArrayList<Person> persons = new ArrayList();
        persons.add(new Admin("Angel", "Ponce", 20, "angel@gmail.com", 2304, null, "database/profiles/angel.jpg"));
        persons.add(new Admin("Samantha", "Chub", 19, "sami@gmail.com", 12345, null, "database/profiles/sami.jpg"));
        persons.add(new User(2304, 1000, 2000, "Josselin", "Tot", 20, "2304", 2304, new GregorianCalendar(2021, 7, 1, 12, 30, 0).getTime(), "database/profiles/jossi.jpg"));
        boolean saved = Helper.saveObjectToFile(persons, "database/Persons.txt");
        System.out.println(saved);

        ArrayList<Ticket> tickets = new ArrayList();
        tickets.add(new Ticket(1, 10));
        tickets.add(new Ticket(5, 10));
        tickets.add(new Ticket(10, 10));
        tickets.add(new Ticket(20, 10));
        tickets.add(new Ticket(50, 10));
        tickets.add(new Ticket(100, 10));
        tickets.add(new Ticket(200, 10));
        saved = Helper.saveObjectToFile(tickets, "database/Tickets.txt");
        System.out.println(saved);

        Properties properties = new Properties("", 0, persons.get(2));
        saved = Helper.saveObjectToFile(properties, "database/Properties.txt");
        System.out.println(saved);

    }
}
