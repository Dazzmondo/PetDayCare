/*
Project: Day Care console-based management system
*/

package models;

import utils.*;


/**
 * Concrete class models.Rabbit representing Rabbits in DayCare
 * Extends abstract Pet class. Using inheritance
 *
 * @author Darragh Breheny
 * @version 1.0
 */
public class Rabbit extends Pet{

    //--------
    //Fields
    //--------

    private String colour = "";

    private static final float DAILY_RATE = 35;

    /**
     * Constructor for objects of class models.Rabbit
     *
     * @param name Rabbit Name (max 30 chars)
     * @param owner Owner Name (max 20 chars)
     * @param sex Rabbit Sex ('m' or 'f')
     * @param age Rabbit Age (must be >= 0)
     * @param id Rabbit ID (must be >= 1000)
     * @param colour Rabbit Colour
     */
    public Rabbit(String name, String owner, char sex, int age, int id, String colour) {
        super(name, owner, sex, age, id);
        this.colour = colour;
    }


    //---------
    // Getters
    //---------


    /**
     * Returns Rabbit Colour
     *
     * @return colour
     */
    public String getColour() {
        return colour;
    }


    //---------
    // Setters
    //---------


    /**
     * Updates Rabbit Colour to value passed as a parameter
     *
     * @param colour The new Rabbit Colour
     */
    public void setColour(String colour) {
        this.colour = colour;
    }


    //--------------
    //Other methods
    //--------------


    /**
     * Calculate weekly fee for Rabbit
     * Fixed daily rate of €35 per day
     *
     * @return Weekly fee in euro
     */
    @Override
    public double calculateWeeklyFee() {
        double weeklyFee = DAILY_RATE * numOfDaysInKennel();
        return Utilities.toTwoDecimalPlaces(weeklyFee);
    }

    @Override
    public String toString() {
        return super.toString() +
                ", Colour: " + colour +
                ", Weekly Fee: €" + calculateWeeklyFee();
    }
}
