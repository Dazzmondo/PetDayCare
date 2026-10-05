/*
Project: Day Care console-based management system
*/

package models;

import java.util.Arrays;
import utils.*;

/**
 * Abstract class models.Pet representing Pets in DayCare
 * This is the superclass for all specific Pet types (Dog, Cat, etc.)
 * Using inheritance
 *
 * @author Darragh Breheny
 * @version 1.0
 */
public abstract class Pet {

    //-------
    //Fields
    //-------

    private String name = "";
    private String owner = "";
    private char sex = 'f';
    private int age = 0;
    private int id = 1000;
    private boolean[] daysAttending = {false, false, false, false, false};


    // Constructor, using validation of setters

    /**
     * Constructor for objects of class models.Pet
     *
     * @param name Pet Name (max 30 chars)
     * @param owner Owner Name (max 20 chars)
     * @param sex Pet Sex ('m' or 'f')
     * @param age Pet Age (must be >= 0)
     * @param id Pet ID (must be >= 1000)
     */
    public Pet(String name, String owner, char sex, int age, int id) {
        // Truncates name to 30 characters through initName() method
        this.name = initName(name);
        // Truncates owner to 20 characters using truncateString() from Utilities
        this.owner = Utilities.truncateString(owner, 20);
        // Used set methods with parameters to avoid repeating code as methods already exist below with validation in place
        setSex(sex);
        setAge(age);
        setID(id);

        // Decided not to use nextId() method because we already had id param. Using both could have resulted in issues
        // Duplicate ids would have occurred. If user entered id 1001, nextId would assign id 1002 to next pet
        // But the user would still be prompted to enter an id. If user entered 1005, nextId would assign 1006 to next pet
        // If user then entered id 1004, nextId would assign 1005 to next pet but that id was already assigned
        // They can't both work together. Needed to decide whether to remove id param from each constructor or remove nextId
        // Decided simpler to just keep id param and remove nextID auto generation
    }


    //-------
    // Getters
    //-------
    /**
     * Returns Pet Name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns Owner Name
     *
     * @return owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Returns the Pet Sex
     *
     * @return sex
     */
    public char getSex() {
        return sex;
    }

    /**
     * Returns the Pet Age
     *
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * Returns the Pet ID
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the Days Pet is Attending
     *
     * @return daysAttending
     */
    public boolean[] getDaysAttending() {
        return daysAttending;
    }

    //-------
    // Setters
    //-------

    /**
     * Updates the Pet Name to the value passed as a parameter
     * Max 30 characters. Using validation to reduce errors in programme by calling initName()
     *
     * @param name The new Pet Name
     */
    public void setName(String name) {
        this.name = initName(name);
    }


    /**
     * Updates the Owner Name to the value passed as a parameter
     * Max 20 characters
     * Using validation to reduce errors in programme
     *
     * @param owner The new Owner Name
     */
    public void setOwner(String owner) {
        if (Utilities.validStringlength(owner, 20)) {
            this.owner = owner;
        }
    }

    /**
     * Updates Pet Sex to the value passed as a parameter
     * Must enter either 'm' for male or 'f' for female. Using validation to reduce errors in programme
     *
     * @param sex The new Pet Sex
     */
    public void setSex(char sex) {
        if (sex == 'm' || sex == 'f') {
            this.sex = sex;
        }
    }


    /**
     * Updates Pet Age to the value passed as a parameter
     * Age can't be a negative number. Using validation to reduce errors in programme
     *
     * @param age The new Pet Age
     */
    public void setAge(int age) {
        if (age >= 0) {
            this.age = age;
        }
    }


    /**
     * Updates Pet ID to the value passed as a parameter
     * Pet ID must be at least 1000. Using validation to reduce errors in programme
     *
     * @param id The new Pet ID
     */
    public void setID(int id) {
        if (id >= 1000) {
            this.id = id;
        }
    }


    // Assumed <=5 in instructions was a typo and meant <5
    // Days Attending is thus limited to Mon-Fri as opposed to Mon-Sat
    /**
     * Updates Days Attending to the value passed as a parameter
     * Days attending must be Monday, Tuesday, Wednesday, Thursday or Friday. Using validation to reduce errors in programme
     *
     * @param daysAttending The new Days Attending
     */
    public void setDaysAttending(boolean[] daysAttending) {
        if (daysAttending != null && daysAttending.length == 5) {
            this.daysAttending = daysAttending;
        }
    }


    //--------------
    //Other methods
    //--------------


    /**
     * Initialises and validates Pet Name. Truncates to 30 characters if too long
     *
     * @param name The name to initialize
     * @return Validated and truncated name
     */
    private String initName(String name) {
        return Utilities.truncateString(name, 30);
    }


    /**
     * Abstract method to calculate weekly fee. Need concrete classes to inherit from Pet.
     * For understanding of how method works, see Dog, Cat and Rabbit classes
     *
     * @return Weekly fee for Pet
     */
    public abstract double calculateWeeklyFee();


    /**
     * Calculate number of days Pet is attending. Iterates through array
     * Adds 1 if true (Pet attending on that day). Returns variable.
     * Boolean True/False (Y/N) response to each day as opposed to entering string, e.g. Mon, Tues, etc.
     *
     * @return Number of days in kennel
     */
    public int numOfDaysInKennel() {
        int count = 0;
        for (boolean day : daysAttending) {
            if (day) {
                count++;
            }
        }
        return count;
    }


    // Assumed <=5 in instructions was a typo and meant <5
    // Monday-Friday represented as opposed to Monday-Saturday
    /**
     * Check in Pet for specific day
     * A number indicating day of the week is passed to the method
     * If valid dayIndex >=0 <5 assign true
     *
     * @param dayIndex Day of week (0-4 for Mon-Fri)
     */
    public void checkIn(int dayIndex) {
        if (Utilities.validRange(dayIndex, 0, 4)) {  // Used validRange method from Utilities class
            daysAttending[dayIndex] = true;
        }
    }


    // Assumed <=5 in instructions was a typo and meant <5
    // Monday-Friday represented as opposed to Monday-Saturday
    /**
     * Check out Pet for a specific day
     * A number indicating day of the week is passed to the method
     * If valid dayIndex >=0 <5 assign true
     *
     * @param dayIndex Day of week (0-4 for Mon-Fri)
     */
    public void checkOut(int dayIndex) {
        if (Utilities.validRange(dayIndex, 0, 4)) {
            daysAttending[dayIndex] = false;
        }
    }

    /**
     * Builds a String representing specific Pet data
     *
     * @return Details of specific Pet
     */
    public String toString(){
        return "ID: " + id +
                ", Pet Name: " + name +
                ", Owner: " + owner +
                ", Sex: " + sex +
                ", Age: " + age +
                ", Days Attending: " + Arrays.toString(daysAttending); // Use Arrays.toString() to avoid hexadecimal output
    }

}
