package ATMSeeder;

import Entity.Admin;
import Entity.User;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 *
 * @author Angel Ponce
 */
public class QueryBuilder {

    public static final SimpleDateFormat TIMESTAMP = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public static void insert(Admin admin) {
        try {
            Connecter c = new Connecter();
            c.con = c.getConnection();
            c.ps = c.con.prepareStatement("INSERT INTO \"admin\"(name,last_name,age,email,pin,last_access,pick) VALUES (?,?,?,?,?,?,?)");
            c.ps.setString(1, admin.getName());
            c.ps.setString(2, admin.getLastName());
            c.ps.setInt(3, admin.getAge());
            c.ps.setString(4, admin.getEmail());
            c.ps.setInt(5, admin.getPin());
            c.ps.setTimestamp(6, Timestamp.valueOf(TIMESTAMP.format(admin.getLastAccess())));
            c.ps.setString(7, admin.getPick());
        } catch (Exception e) {
        }
    }

    public static void insert(User user) {
        try {
            Connecter c = new Connecter();
            c.con = c.getConnection();
            c.ps = c.con.prepareStatement("INSERT INTO \"admin\"(name,last_name,age,email,pin,last_access,pick) VALUES (?,?,?,?,?,?,?)");
            c.ps.setString(1, user.getName());
            c.ps.setString(2, user.getLastName());
            c.ps.setInt(3, user.getAge());
            c.ps.setString(4, user.getEmail());
            c.ps.setInt(5, user.getPin());
            c.ps.setTimestamp(6, Timestamp.valueOf(TIMESTAMP.format(user.getLastAccess())));
            c.ps.setString(7, user.getPick());
        } catch (Exception e) {
        }
    }
}
