/*
Project: Day Care console-based management system
*/

package models;

import utils.*;


/**
 * Concrete class models.Cat representing Cats in DayCare
 * Extends abstract Pet class. Using inheritance
 *
 * @author Darragh Breheny
 * @version 1.0
 */
public class Cat extends Pet{

    //-------
    //Fields
    //-------

    private String favouriteToy = "";
    private boolean indoorCat = true;


    /**
     * Constructor for objects of class models.Cat
     *
     * @param name Cat Name (max 30 chars)
     * @param owner Owner Name (max 20 chars)
     * @param sex Cat Sex ('m' or 'f')
     * @param age Cat Age (must be >= 0)
     * @param id Cat ID (must be >= 1000)
     * @param favouriteToy Cat's Favourite Toy
     * @param indoorCat Whether Cat is an Indoor Cat or not
     */
    public Cat(String name, String owner, char sex, int age, int id, String favouriteToy, boolean indoorCat) {
        super(name, owner, sex, age, id);
        // Used set method with parameter to avoid repeating code as method already exists below with validation in place
        setFavouriteToy(favouriteToy);
        this.indoorCat = indoorCat;
    }


    //-------
    //Getters
    //-------


    /**
     * Returns Cat's Favourite Toy
     *
     * @return favouriteToy
     */
    public String getFavouriteToy() {
        return favouriteToy;
    }

    /**
     * Returns whether Cat is an Indoor Cat or not
     *
     * @return indoorCat
     */
    public boolean isIndoorCat() {
        return indoorCat;
    }


    //-------
    // Setters
    //-------


    /**
     * Updates Cat Favourite Toy to the value passed as a parameter
     * Uses validation and toUpperCase
     *
     * @param favouriteToy The new Cat Favourite Toy
     */

    public void setFavouriteToy(String favouriteToy) {
        if (CatToyUtility.isCatToy(favouriteToy.toUpperCase())) {
            this.favouriteToy = favouriteToy.toUpperCase();
        }
    }

    /**
     * Updates Indoor Cat status to the value passed as a parameter
     *
     * @param indoorCat The new Indoor Cat status
     */
    public void setIndoorCat(boolean indoorCat) {
        this.indoorCat = indoorCat;
    }


    //--------------
    //Other Methods
    //--------------


    /**
     * Calculate the weekly fee for Cat based on Indoor status and Days Attending
     * Base rate is €20 per day
     * Extra €5 if indoor cat
     *
     * @return Weekly fee in euro
     */
    @Override
    public double calculateWeeklyFee() {
        double dailyRate = 20.0;
        if (indoorCat) {
            dailyRate += 5.0;
        }
        double weeklyFee = dailyRate * numOfDaysInKennel();
        return Utilities.toTwoDecimalPlaces(weeklyFee);
    }

    @Override
    public String toString() {
        return super.toString() +
                ", Indoor Cat: " + Utilities.booleanToYN(indoorCat) + //Using Utilities booleanToYN method
                ", Favourite Toy: " + favouriteToy +
                ", Weekly Fee: €" + calculateWeeklyFee();
    }

}
