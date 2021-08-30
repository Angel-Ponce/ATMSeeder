package Entity;

import java.io.Serializable;

/**
 *
 * @author samyc
 */
public class Ticket implements Serializable{

    private int type;
    private int size;
    public Ticket(int type, int size) {
        this.type = type;
        this.size = size;
    }

    public Ticket() {
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

}
