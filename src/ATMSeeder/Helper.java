package ATMSeeder;

import Entity.Person;
import Entity.User;
import Entity.Admin;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

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

    public static void moveFile(String seed, String target) {
        try {
            BufferedImage file = ImageIO.read(Helper.class.getResourceAsStream(seed));
            ImageIO.write((RenderedImage) file, "png", new File(target));
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }
}
