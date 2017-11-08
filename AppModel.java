package GroupTwo.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Collection class for the Person class. Maintains the persistent file of
 * contacts
 *
 * @author To Yin Yu
 * @author Juhee Kim
 *
 * @version 8.0 8/23/2016
 */
public class AppModel {

    /**
     * constant for the total rooms available in the Hotel
     */
    public static final int TOTAL_ROOMS = 10;

    /**
     * price per night
     */
    public static final double FIXED_CHARGE = 150.0;

    private ArrayList<Person> CustomerList;
    private int roomsAvailable;

    /**
     * Constructor to create an object
     */
    public AppModel() {
        CustomerList = new ArrayList<>();
        roomsAvailable = TOTAL_ROOMS;
        readCollection();
    }

    /**
     * provides access to the CustomerList
     *
     * @return CustomerList
     */
    public ArrayList<Person> getCustomerList() {
        return CustomerList;
    }

    /**
     * Sets the Customer List field
     *
     * @param CustomerList List of customers
     */
    public void setCustomerList(ArrayList<Person> CustomerList) {
        this.CustomerList = CustomerList;
    }

    /**
     * Returns the size of the list
     *
     * @return CustomerList.size() the size of the list
     */
    public int getNumberOfCustomers() {
        return CustomerList.size();
    }

    /**
     * provides access to the available rooms field
     *
     * @return difference between total rooms and number of customers
     */
    public int getRoomsAvailable() {
        return TOTAL_ROOMS - CustomerList.size();
    }

    /**
     * Checks whether the customer with name and room provided is in the
     * CustomerList
     *
     * @param name Customer's name
     * @param room the room number that the customer stays in
     * @return true/false
     */
    public boolean checkCustomer(String name, int room) {
        for (Person x : CustomerList) {
            if (x.getCustomerName().equalsIgnoreCase(name) && x.getRoomNum() == room) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a customer to the list if there is room, decreases the number of
     * rooms available, writes to file
     *
     * @param name the name of the customer
     * @param room the room assignment
     * @param nights the number of nights booked
     * @param cell cell phone number
     *
     * @return String: whether the customer was booked or not
     */
    public String addCustomer(String name, int room, int nights, String cell) {
        if (CustomerList.size() < TOTAL_ROOMS) {
            Person p = new Person();
            p.setCustomerName(name);
            p.setRoomNum(room);
            p.setHowLongTheyStay(nights);
            p.setCell(cell);
            CustomerList.add(p);
            roomsAvailable = TOTAL_ROOMS - CustomerList.size();
            writeCollection();
            return p.getCustomerName() + " has been added ";
        } else {
            return "HOTEL IS FULL!";
        }
    }

    /**
     * Allows the manager to edit the customer's fields, writes to file.
     *
     * @param name the customer name
     * @param room the room assignment
     * @param nights the number of nights booked
     * @param cell the cell phone number
     *
     * @return String: whether the customer was updated or not found
     */
    public String updateCustomer(String name, int room, int nights, String cell) {
        for (Person x : CustomerList) {
            if (x.getCustomerName().equals(name) /*&& x.getRoomNum() == room*/) {
                CustomerList.remove(x);
                Person p = new Person();
                p.setCustomerName(name);
                p.setRoomNum(room);
                p.setHowLongTheyStay(nights);
                p.setCell(cell);
                CustomerList.add(p);
                roomsAvailable = TOTAL_ROOMS - CustomerList.size();
                writeCollection();
                return name + " has been updated";
            }
        }
        return "Customer not Found";
    }

    /**
     * Removes the customer with matching name and room assignment, writes to
     * file
     *
     * @param name the customer's name
     * @param room the room assignment
     *
     * @return String: whether they were removed or not found
     */
    public String removeCustomer(String name, int room) {
        if (!CustomerList.isEmpty()) {
            for (Person x : CustomerList) {
                if (x.getCustomerName().equalsIgnoreCase(name) && x.getRoomNum() == room) {
                    CustomerList.remove(x);
                    roomsAvailable = TOTAL_ROOMS - CustomerList.size();
                    writeCollection();
                    return name + " removed!";
                }
            }
            return "Customer not found!";
        } else {
            return "HOTEL IS EMPTY!";
        }
    }

    /**
     * Clears the customer list, writes to file
     */
    public void removeAll() {
        CustomerList.clear();
        roomsAvailable = TOTAL_ROOMS;
        writeCollection();
    }

    /**
     * Displays info about all customers with equal names
     *
     * @param name Customer's name
     * @return output: customer's info or "Customer " + name + " not found!".
     */
    public String displayEquals(String name) {
        boolean notFound = true;
        String output = "";
        for (int i = 0; i < CustomerList.size(); i++) {
            Person p = CustomerList.get(i);
            if (p.getCustomerName().equalsIgnoreCase(name)) {
                output += "Name: " + p.getCustomerName() + " room# " + p.getRoomNum() + " Cell#: " + p.getCell() + " total due: $" + (p.getHowLongTheyStay() * FIXED_CHARGE);
                output += "\n";
                notFound = false;
            }
        }
        if (notFound) {
            output = "Customer " + name + " not found!";
        }
        return output;
    }

    /**
     * Returns all customers in a given room
     *
     * @param room the room that the customer stayed in
     *
     * @return output: an array of names in the given room
     */
    public String[] retrieveCustomersFromRoom(int room) {
        String[] output = new String[CustomerList.size()];
        for (int i = 0; i < CustomerList.size(); i++) {
            Person p = CustomerList.get(i);
            if (p.getRoomNum() == room) {
                output[i] = p.getCustomerName();
            }
        }
        return output;
    }

    /**
     * Displays info about each customer name in the array passed in
     *
     * @param cust customers 
     * @return output: customer info or "Customers not found!"
     */
    public String displayCustomersInArray(String[] cust) {
        String output = "";
        boolean notFound = true;
        for (int i = 0; i < CustomerList.size(); i++) {
            Person p = CustomerList.get(i);
            for (int j = 0; j < cust.length; j++) {
                if (p.getCustomerName().equalsIgnoreCase(cust[i])) {
                    output += "Name: " + p.getCustomerName() + " room# " + p.getRoomNum() + " total due: $" + (p.getHowLongTheyStay() * FIXED_CHARGE);
                    output += "\n";
                    notFound = false;
                    break;
                }
            }
        }
        if (notFound) {
            output = "Customers not found!";
        }
        return output;
    }

    /**
     * Displays all customers in the list
     *
     * @return output: all customers if any or "HOTEL IS EMPTY!"
     */
    public String displayAll() {
        String output = "";
        if (!CustomerList.isEmpty()) {
            for (int i = 0; i < CustomerList.size(); i++) {
                Person x = CustomerList.get(i);
                output += x.getCustomerName() + ", room # " + x.getRoomNum() + ", " + x.getHowLongTheyStay() + " nights" + ", Cell#: " + x.getCell();
                output += "\n";
            }
            output += "\n\n" + CustomerList.size() + " Total customers \n" + getRoomsAvailable() + " Rooms available";
        } else {
            output = "HOTEL IS EMPTY!";
        }
        return output;
    }

    /**
     * Private method to write collection to a file.
     *
     * @return true if saved, false if not
     */
    private boolean writeCollection() {
        boolean success = true;
        try (
                FileOutputStream fos = new FileOutputStream("customers.ser");
                ObjectOutputStream output = new ObjectOutputStream(fos)) {
            output.writeObject(CustomerList);
        } catch (Exception ex) {
            System.out.println("Cannot write to file:\n" + ex.getMessage());
            success = false;
        }
        return success;
    }

    /**
     * Private method to read collection from a file.
     *
     * @return true if read, false if not
     */
    private boolean readCollection() {
        boolean success = true;
        File ser = new File("customers.ser");
        if (ser.exists()) {
            try (
                    FileInputStream fis = new FileInputStream("customers.ser");
                    ObjectInputStream input = new ObjectInputStream(fis)) {
                CustomerList = (ArrayList<Person>) input.readObject();
            } catch (Exception ex) {
                System.out.println("Cannot read from file:\n" + ex.getMessage());
                success = false;
            }
        }
        return success;
    }
}
