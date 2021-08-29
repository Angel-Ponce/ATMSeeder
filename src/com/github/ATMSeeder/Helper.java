package com.github.ATMSeeder;

import com.github.Entity.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Angel Ponce
 */
public class Helper {

    public static Object getObjectFromFile(String path) {
        try {
            ObjectInputStream objectStream = new ObjectInputStream(
                    new FileInputStream(path)
            );
            Object object = objectStream.readObject();
            objectStream.close();
            return object;
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace(System.err);
        }
        return null;
    }

    public static boolean saveObjectToFile(Object object, String path) {
        try {
            ObjectOutputStream objectStream = new ObjectOutputStream(
                    new FileOutputStream(path)
            );
            objectStream.writeObject(object);
            objectStream.close();
            return true;
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
        return false;
    }

    public static User personToUser(Person person) {
        try {
            return (User) person;
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        return null;
    }

    public static Admin personToAdmin(Person person) {
        try {
            return (Admin) person;
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        return null;
    }
}
