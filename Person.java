package GroupTwo.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Data class representing one person
 *
 * @author Tamanna Sharma
 * @author Joy Jeong
 *
 * @version 8.0 8/23/2016
 */
public class Person implements Comparable<Person>, Serializable {

    //private fields
    private String customerName;
    private String cell;
    private int howLongTheyStay;
    private int roomNum;

    /**
     * Full constructor, initializes a Person object
     *
     * @param customerName the customer's name
     * @param cell the customer's cell phone #
     * @param howLongTheyStay the total nights booked
     * @param roomNum the room assignment
     */
    public Person(String customerName, String cell,  int howLongTheyStay, int roomNum) {
        this.customerName = customerName;
        this.cell = cell;
        this.howLongTheyStay = howLongTheyStay;
        this.roomNum = roomNum;
    }

    /**
     * Default constructor
     */
    public Person() {
        this.howLongTheyStay = 1;
        this.roomNum = 1;
        this.customerName = "Default";
        this.cell = "555-555-5555";
    }

    /**
     * provides access to the cell phone field
     *
     * @return cell the cell phone number
     */
    public String getCell() {
        return cell;
    }

    /**
     * Allows the customer's name to be set
     *
     * @param cell the cell phone number
     */
    public void setCell(String cell) {
        this.cell = cell;
    }

    /**
     * Provides access to the nights booked
     *
     * @return howLongTheyStay: the nights booked
     */
    public int getHowLongTheyStay() {
        return howLongTheyStay;
    }

    /**
     * Allows the stay Duration field to be set
     *
     * @param howLongTheyStay The time of the customer is going to stay
     */
    public void setHowLongTheyStay(int howLongTheyStay) {
        this.howLongTheyStay = howLongTheyStay;
    }

    /**
     * Provides access to the room assignment
     *
     * @return roomNum: the room assignment
     */
    public int getRoomNum() {
        return roomNum;
    }

    /**
     * Allows the room assignment field to be set
     *
     * @param roomNum: the room assignment
     */
    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }

    /**
     * Provides access to the customerName field
     *
     * @return customerName: the customer's name
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Allows the customerName field to be set
     *
     * @param customerName: the customer's name
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Creates a unique identifier for the contact.
     *
     * @return the unique identifier
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.customerName);
        return hash;
    }

    /**
     * Determines if one person is the same name as another.
     *
     * @param obj the other contact
     * @return true if equal, false if not
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person other = (Person) obj;
        if (!Objects.equals(this.customerName, other.customerName)) {
            return false;
        }
        return true;
    }

    /**
     * Compares this person with another for sorting purposes.
     *
     * @param person the other contact
     * @return a number less than 0 if this person should come before the other,
     * 0 if they are the same, and a number greater than 0 if this person should
     * come after the other person.
     */
    @Override
    public int compareTo(Person person) {
        final int BEFORE = -1;
        final int EQUAL = 0;
        final int AFTER = 1;
        if (this == person) {
            return EQUAL;
        }
        return EQUAL;
    }
}
