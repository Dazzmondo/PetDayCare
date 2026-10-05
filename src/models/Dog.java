/*
Project: Day Care console-based management system
*/

package models;

import java.util.Objects;
import utils.*;


/**
 * Concrete class models.Dog representing Dogs in DayCare
 * Extends abstract Pet class. Using inheritance
 *
 * @author Darragh Breheny
 * @version 1.0
 */
public class Dog extends Pet{

    //-------
    //Fields
    //-------

    private String breed = "";
    private boolean dangerousBreed = false;
    private boolean neutered = false;

    private static final float DANGEROUS_DAILY_RATE = 40;
    private static final float NONDANGEROUS_DAILY_RATE = 30;


    /**
     * Constructor for objects of class models.Dog
     *
     * @param name Dog Name (max 30 chars)
     * @param owner Owner Name (max 20 chars)
     * @param sex Dog Sex ('m' or 'f')
     * @param age Dog Age (must be >= 0)
     * @param id Dog ID (must be >= 1000)
     * @param breed Dog Breed (max 20 chars)
     * @param dangerousBreed Whether Dog is a Dangerous Breed or not
     * @param neutered Whether Dog is Neutered or not
     */
    public Dog(String name, String owner, char sex, int age, int id, String breed, boolean dangerousBreed, boolean neutered){
        super(name, owner, sex, age, id); //Check why daysAttending not here, prob mistake
        // Used set method with parameter to avoid repeating code as method already exists below with validation in place
        setBreed(breed);
        this.neutered = neutered;
        this.dangerousBreed = dangerousBreed;
    }


    //-------
    // Getters
    //-------


    /**
     * Returns Dog Breed
     *
     * @return breed
     */
    public String getBreed() {
        return breed;
    }

    /**
     * Returns whether Dog is Neutered or not
     *
     * @return neutered
     */
    public boolean isNeutered() {
        return neutered;
    }

    /**
     * Returns whether Dog is a Dangerous Breed or not
     *
     * @return dangerousBreed
     */
    public boolean isDangerousBreed() {
        return dangerousBreed;
    }


    //---------
    // Setters
    //---------


    /**
     * Updates the Dog Breed to the value passed as a parameter
     * Max 20 characters
     * Using validation to reduce errors in programme
     *
     * @param breed The new Dog Breed
     */
    public void setBreed(String breed) {
        // Wrote 20 char validation before updating the DogBreedUtility validation to match limit mentioned in brief
        // Irrelevant now, because user can only select from HashSet options, all of which are 20 characters or less
        if (DogBreedUtility.checkBreed(breed) && Utilities.validStringlength(breed, 20)) {
            this.breed = breed.toUpperCase(); // Matches upper case options in DogBreedUtility class
        }
    }

    /**
     * Updates Dog Neutered status to the value passed as a parameter
     *
     * @param neutered The new Dog Neutered status
     */
    public void setNeutered(boolean neutered) {
        this.neutered = neutered;
    }

    /**
     * Updates Dog Dangerous Breed status to the value passed as a parameter
     *
     * @param dangerousBreed The new Dog Dangerous Breed status
     */
    public void setDangerousBreed(boolean dangerousBreed) {
        this.dangerousBreed = dangerousBreed;
    }


    //---------------
    // Other methods
    //---------------


    /**
     * Calculate weekly fee for Dog
     * Based on whether Dangerous or not and Number of Days in Kennel
     *
     * @return Weekly fee in euro
     */
    @Override
    public double calculateWeeklyFee() {
        float dailyRate = dangerousBreed ? DANGEROUS_DAILY_RATE : NONDANGEROUS_DAILY_RATE; // Gets Daily Rate for Dog depending on whether Dangerous or not
        double weeklyFee = dailyRate * numOfDaysInKennel(); // Product of Daily Rate by Number of Days in Kennel and returns answer for Weekly Fee
        return Utilities.toTwoDecimalPlaces(weeklyFee); // Using toTwoDecimalPLaces() method from Utilities class, replicating €0.00 format
    }



    // Originally generated as suggested in video but it was wrong
    // Didn't consider Pet superclass before
    // Also didn't include calculateWeeklyFee()
    // Adds Pet toString() with super
    @Override
    public String toString() {
        return super.toString() +
                ", Breed: " + breed +
                ", Dangerous: " + Utilities.booleanToYN(dangerousBreed) + //Using Utilities booleanToYN method
                ", Neutered: " + Utilities.booleanToYN(neutered) +
                ", Weekly Fee: €" + calculateWeeklyFee();
    }



    // Auto-generated. Not sure if I understand why equals() is being used in Dog instead of Pet class. From what I could find online
    // equals() in the subclass would forbid superclass equality while in the superclass it would allow subclass equality.
    // It wasn't clear what the purpose of this equals() method is. What is it trying to achieve? There's no discussion about it in the brief.
    // I don't understand how the project would be impacted if it wasn't there as it seemed to still run properly without it.
    // It also wasn't clear how to generate it as mentioned in the video. There were multiple ways to generate it with different code
    // and different versions. This uses getclass() but there was also instanceof.
    // I'm not sure if the version chosen makes any difference.
    // I will need to learn more about how this method works and why it is used.
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Dog dog = (Dog) o;
        return dangerousBreed == dog.dangerousBreed && neutered == dog.neutered && Objects.equals(breed, dog.breed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(breed, dangerousBreed, neutered);
    }
}
