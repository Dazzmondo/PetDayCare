/*
Project: Day Care console-based management system
*/

package controllers;

// Imported xStream for persistence save/load based on Social Network and Shop examples
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import models.*;
import utils.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


/**
 * Controller class controllers.DayCare manages the Day Care programme
 * Handles CRUD operations, reporting, and persistence for pets
 * The DayCare class saves all the entered products into a primitive ArrayList.
 *
 * @author Darragh Breheny
 * @version 1.0
 */
public class DayCare implements ISerializer{

    private ArrayList<Pet> pets;

    public DayCare() {
        pets = new ArrayList<Pet>(); // Instantiates pets list
    }

    // Changed name to daycareName because it was too confusing with Pet object also being name
    private String daycareName = "Pat Purrs Day Care"; // Initialise name of Day Care but chose not to offer update bc this wasn't in brief
    private int maxNumberOfPets = 90; // Set default capacity to 90 as this number was mentioned by Pat Purrs in video. This can be updated by user.


    /**
     * Constructor for objects of class controllers.DayCare
     *
     * @param daycareName Day Care name
     * @param maxNumberOfPets Maximum Number of Pets that can attend the Day Care
     */
    public DayCare(String daycareName, int maxNumberOfPets) {
        this.daycareName = initName(daycareName);
        this.maxNumberOfPets = maxNumberOfPets;
        this.pets = new ArrayList<>();
    }



    //----------------------
    // Getters and Setters
    //----------------------


    /**
     * Get Pet by Name (overloaded method)
     *
     * @param name Pet Name
     * @return First Pet with that name or null if not found
     */
    // Not used anymore because I created getPetsByName() method to access multiple Pets with same name instead of just one.
    // Originally searchByName() method in Driver was calling this method instead of getPetsByName()
    // and only the first Pet found with that name was displayed.
    // More practical for a real-world Day Care which would likely have multiple Pets with the same name.
    public Pet getPet(String name) {
        for (Pet pet : pets) {
            if (pet.getName().equalsIgnoreCase(name)) {
                return pet;
            }
        }
        return null;
    }

    /**
     * Get Pet by ID
     *
     * @param id Pet ID
     * @return Pet with that ID or null if not found
     */
    public Pet getPet(int id) { // Called by searchById() in Driver
        return getPetById(id);
    }

    /**
     * Returns Day Care Name
     *
     * @return daycareName
     */
    public String getName() {
        return daycareName;
    }

    /**
     * Updates Day Care Name to the value passed as a parameter
     *
     * @param daycareName The new Day Care Name
     */
    public void setName(String daycareName) {
        this.daycareName = initName(daycareName); // Uses initName() method
    }

    /**
     * Returns Max Number of Pets
     *
     * @return maxNumberOfPets
     */
    public int getMaxNumberOfPets() {
        return maxNumberOfPets;
    }

    /**
     * Updates Max Number of Pets to the value passed as a parameter
     *
     * @param maxNumberOfPets The new Max Number of Pets
     */
    public void setMaxNumberOfPets(int maxNumberOfPets) {
        if (maxNumberOfPets > 0) {
            this.maxNumberOfPets = maxNumberOfPets;
        }
    }

    /**
     * Returns Array List of Pets
     *
     * @return pets
     */
    public ArrayList<Pet> getPetsArray() {
        return pets;
    }

    /**
     * Updates the Array List of Pets to the value passed as a parameter
     *
     * @param pets The new Array List of Pets
     */
    public void setPetsArray(ArrayList<Pet> pets) {
        this.pets = pets;
    }



    //---------------
    // CRUD Methods
    //---------------


    /**
     * Add Pet
     *
     * @param pet Pet to add
     * @return added Pet (Dog or Cat)
     */
    public boolean addPet (Pet pet) {
        // Validates that Day Care is not at set Max Capacity
        if (pets.size() >= maxNumberOfPets) {
            System.out.println("Daycare is full. Can't add Pet right now.");
            return false;
        }
        return pets.add(pet);
    }

    /**
     * Update Dog details
     *
     * @param id Dog ID
     * @param updatedDetails Updated Dog object
     * @return true if updated, false otherwise
     */
    public boolean updateDog(int id, Dog updatedDetails) {
        //find the pet object by id
        Pet foundPet = getPetById(id);

        // If the Pet exists and is of type Dog, use the details passed in the updatedDetails parameter to
        // update the found Pet in the ArrayList.
        if ((foundPet != null) && (foundPet instanceof Dog)) {
            foundPet.setName(updatedDetails.getName());
            foundPet.setOwner(updatedDetails.getOwner());
            foundPet.setSex(updatedDetails.getSex());
            foundPet.setAge(updatedDetails.getAge());
            foundPet.setID(updatedDetails.getId());
            ((Dog) foundPet).setBreed(updatedDetails.getBreed()); // Changed to access subclass Dog parameters
            ((Dog) foundPet).setNeutered(updatedDetails.isNeutered());
            ((Dog) foundPet).setDangerousBreed(updatedDetails.isDangerousBreed());
            foundPet.setDaysAttending(updatedDetails.getDaysAttending());
            return true;
        }
        //if the pet was not found, return false, indicating that the update was not successful
        return false;
    }

    /**
     * Update Cat Details
     *
     * @param id Cat ID
     * @param updatedDetails Updated Cat object
     * @return true if updated, false otherwise
     */
    public boolean updateCat(int id, Cat updatedDetails) {
        //find the pet object by id
        Pet foundPet = getPetById(id);

        // If the Pet exists and is of type Cat, use the details passed in the updatedDetails parameter to
        // update the found Pet in the ArrayList.
        if ((foundPet != null) && (foundPet instanceof Cat)) {
            foundPet.setName(updatedDetails.getName());
            foundPet.setOwner(updatedDetails.getOwner());
            foundPet.setSex(updatedDetails.getSex());
            foundPet.setAge(updatedDetails.getAge());
            foundPet.setID(updatedDetails.getId());
            ((Cat) foundPet).setFavouriteToy(updatedDetails.getFavouriteToy()); // Changed to access subclass Cat parameters
            ((Cat) foundPet).setIndoorCat(updatedDetails.isIndoorCat());
            foundPet.setDaysAttending(updatedDetails.getDaysAttending());
            return true;
        }
        // If the Pet was not found, return false, indicating that the update was not successful
        return false;
    }

    /**
     * Update Rabbit
     * @param id Rabbit ID
     * @param updatedDetails Updated Rabbit object
     * @return true if updated, false otherwise
     */
    public boolean updateRabbit(int id, Rabbit updatedDetails) {
        //find the pet object by id
        Pet foundPet = getPetById(id);

        // If the Pet exists and is of type Rabbit, use the details passed in the updatedDetails parameter to
        // update the found Pet in the ArrayList.
        if ((foundPet != null) && (foundPet instanceof Rabbit)) {
            foundPet.setName(updatedDetails.getName());
            foundPet.setOwner(updatedDetails.getOwner());
            foundPet.setSex(updatedDetails.getSex());
            foundPet.setAge(updatedDetails.getAge());
            foundPet.setID(updatedDetails.getId());
            ((Rabbit) foundPet).setColour(updatedDetails.getColour()); // Changed to access subclass Rabbit parameters
            foundPet.setDaysAttending(updatedDetails.getDaysAttending());
            return true;
        }
        // If the Pet was not found, return false, indicating that the update was not successful
        return false;
    }

    /**
     * Delete Pet by Index location
     *
     * @param indexToDelete Location in the list
     * @return Deleted Pet or null if invalid index
     */
    public Pet deletePetByIndex (int indexToDelete) {
        if (Utilities.isValidIndex(pets, indexToDelete)) {  // isValidIndex() method from Utilities class
            return pets.remove(indexToDelete);
        }
        return null;
    }

    /**
     * Delete Pet by ID
     *
     * @param idToDelete Pet ID
     * @return Deleted Pet or null if not found
     */
    public Pet deletePetById (int idToDelete) {
        if (isValidId(idToDelete)) {
            return pets.remove(idToDelete);
        }
        return null;
    }



    //-------------------
    // Reporting Methods
    //-------------------


    /**
     * List all Pets
     *
     * @return String containing all Pets
     */
    public String listAllPets() {
        // Checks if Array List pets is empty. If yes, returns No Pets
        if (pets.isEmpty()) {
            return "No Pets";
        }
        else {
            // String to represent details of all Pets in list
            String listOfPets = "";
            // Loops through each Pet in Array List pets using index
            for (int i = 0; i < pets.size(); i++) {
                // Gets i from pets and using toString() adds to listOfPets String
                listOfPets += i + ": " + pets.get(i) + "\n";
            }
            return listOfPets;
        }
    }

    /**
     * List all Cats
     *
     * @return String containing all Cats
     */
    public String listAllCats() {
        if (pets.isEmpty()) {
            return "No Pets";
        }
        else {
            // String to represent details of all Cats in list
            String listOfCats = "";
            for (int i = 0; i < pets.size(); i++) {
                if (pets.get(i) instanceof Cat) {
                    // Gets i from pets and using toString() adds to listOfCats String
                    listOfCats += i + ": " + pets.get(i) + "\n";
                }
            }
            // If no Cats currently in system
            if (listOfCats.isEmpty()) {
                return "No Cats";
            } else {
                return listOfCats;
            }
        }
    }

    /**
     * List all Dogs
     *
     * @return String containing all dogs
     */
    public String listAllDogs() {
        if (pets.isEmpty()) {
            return "No Pets";
        }
        else {
            // String to represent details of all Dogs in list
            String listOfDogs = "";
            for (int i = 0; i < pets.size(); i++) {
                if (pets.get(i) instanceof Dog) {
                    // Gets i from pets and using toString() adds to listOfDogs String
                    listOfDogs += i + ": " + pets.get(i) + "\n";
                }
            }
            // If no Dogs currently in system
            if (listOfDogs.isEmpty()) {
                return "No Dogs";
            } else {
                return listOfDogs;
            }
        }
    }

    /**
     * List all Rabbits
     *
     * @return String containing all rabbits
     */
    public String listAllRabbits() {
        if (pets.isEmpty()) {
            return "No Pets";
        }
        else {
            // String to represent details of all Rabbits in list
            String listOfRabbits = "";
            for (int i = 0; i < pets.size(); i++) {
                if (pets.get(i) instanceof Rabbit) {
                    // Gets i from pets and using toString() adds to listOfRabbits String
                    listOfRabbits += i + ": " + pets.get(i) + "\n";
                }
            }
            // If no Rabbits currently in system
            if (listOfRabbits.isEmpty()) {
                return "No Rabbits";
            } else {
                return listOfRabbits;
            }
        }
    }

    /**
     * List all Dangerous Dogs
     *
     * @return String containing all Dangerous Dogs
     */
    public String listAllDangerousDogs() {
        if (pets.isEmpty()) {
            return "No Pets";
        }
        else {
            // String to represent details of all Dangerous Dogs in list
            String listOfDangerousDogs = "";
            for (int i = 0; i < pets.size(); i++) {
                if (pets.get(i) instanceof Dog) {
                    Dog dog = (Dog) pets.get(i);   // Polymorphism
                    if (dog.isDangerousBreed()) {
                        // Gets i from pets and using toString() adds to listOfDangerousDogs String
                        listOfDangerousDogs += i + ": " + dog + "\n";
                    }
                }
            }
            // If no Dangerous Dogs currently in system
            if (listOfDangerousDogs.isEmpty()) {
                return "No Dangerous Dogs";
            } else {
                return listOfDangerousDogs;
            }
        }
    }

    /**
     * List all Pets by Owner Name
     *
     * @param owner Owner Name
     * @return String containing Pets for that Owner
     */
    public String listAllPetsByOwner(String owner) {
        if (pets.isEmpty()) {
            return "No Pets";
        }
        else {
            // String to represent details of all Pets in list
            String listOfPets = "";
            for (int i = 0; i < pets.size(); i++) {
                if (pets.get(i).getOwner().equalsIgnoreCase(owner)) {
                    // Gets i from pets and using toString() adds to listOfPets String
                    listOfPets += i + ": " + pets.get(i) + "\n";
                }
            }
            // If no Pets with that Owner currently in system
            if (listOfPets.isEmpty()) {
                return "No Pets with Owner: " + owner;
            } else {
                return listOfPets;
            }
        }
    }

    /**
     * List all Pets that stay more than specified Number of Days
     *
     * @param numDays Minimum Number of Days
     * @return String containing Pets staying longer than numDays
     */
    public String listAllPetsThatStayMoreThanDays(int numDays) {
        if (pets.isEmpty()) {
            return "No Pets";
        }
        else {
            // String to represent details of all Pets in list
            String listOfPets = "";
            for (int i = 0; i < pets.size(); i++) {
                if (pets.get(i).numOfDaysInKennel() > numDays) {
                    // Gets i from pets and using toString() adds to listOfPets String
                    listOfPets += i + ": " + pets.get(i) + "\n";
                }
            }
            // If no Pets staying longer than that number of days currently in system
            if (listOfPets.isEmpty()) {
                return "No Pets staying longer than " + numDays;
            } else {
                return listOfPets;
            }
        }
    }

    /**
     * List all Indoor Cats
     *
     * @return String containing all Indoor Cats
     */
    public String listAllIndoorCats() {
        if (pets.isEmpty()) {
            return "No Pets";
        }
        else {
            // String to represent details of all Indoor Cats in list
            String listOfIndoorCats = "";
            for (int i = 0; i < pets.size(); i++) {
                if (pets.get(i) instanceof Cat) {
                    Cat cat = (Cat) pets.get(i); // Polymorphism
                    if (cat.isIndoorCat()) {
                        // Gets i from pets and using toString() adds to listOfIndoorCats String
                        listOfIndoorCats += i + ": " + cat + "\n";
                    }
                }
            }
            // If no Indoor Cats currently in system
            if (listOfIndoorCats.isEmpty()) {
                return "No Indoor Cats";
            } else {
                return listOfIndoorCats;
            }
        }
    }

    /**
     * List all Pets older than specified age
     *
     * @param age Minimum age
     * @return String containing Pets older than age
     */
    public String listAllPetsOlderThanAge(int age) {
        if (pets.isEmpty()) {
            return "No Pets";
        }
        else {
            // String to represent details of all Pets in list
            String listOfPets = "";

            for (int i = 0; i < pets.size(); i++) {
                if (pets.get(i).getAge() > age) {
                    // Gets i from pets and using toString() adds to listOfPets String
                    listOfPets += i + ": " + pets.get(i) + "\n";
                }
            }
            // If no Pets older than that age currently in system
            if (listOfPets.isEmpty()) {
                return "No Pets older than " + age;
            } else {
                return listOfPets;
            }
        }
    }


    /**
     * List all Cats by Favourite Toy
     *
     * @param toy Favourite toy name
     * @return String containing Cats with that toy
     */
    public String listAllCatsByFavouriteToy(String toy) {
        if (pets.isEmpty()) {
            return "No Pets";
        }
        else {
            // String to represent details of all Cats in list
            String listOfCats = "";
            for (int i = 0; i < pets.size(); i++) {
                if (pets.get(i) instanceof Cat) {
                    Cat cat = (Cat) pets.get(i); // Polymorphism
                    if (cat.getFavouriteToy().equalsIgnoreCase(toy)) {
                        // Gets i from pets and using toString() adds to listOfCats String
                        listOfCats += i + ": " + cat + "\n";
                    }
                }
            }
            // If no Cats with that Favourite Toy currently in system
            if (listOfCats.isEmpty()) {
                return "No Cats with Favourite Toy: " + toy;
            } else {
                return listOfCats;
            }
        }
    }

    /**
     * List all Neutered Animals
     *
     * @return String containing all neutered animals
     */
    public String listAllNeuteredAnimals() {
        if (pets.isEmpty()) {
            return "No Pets";
        }
        else {
            // String to represent details of all Neutered Dogs in list
            String listOfNeuteredAnimals = "";
            for (int i = 0; i < pets.size(); i++) {
                if (pets.get(i) instanceof Dog) {
                    Dog dog = (Dog) pets.get(i); // Polymorphism
                    if (dog.isNeutered()) {
                        // Gets i from pets and using toString() adds to listOfNeuteredAnimals String
                        listOfNeuteredAnimals += i + ": " + dog + "\n";
                    }
                }
            }
            // If no Neutered Dogs currently in system
            if (listOfNeuteredAnimals.isEmpty()) {
                return "No Neutered Dogs";
            } else {
                return listOfNeuteredAnimals;
            }
        }
    }



    //-------------------------------
    // Sort methods (selection sort)
    //-------------------------------


    /**
     * Sorts Pets in Pets Array List in ascending alphabetical order by Name.
     * Selection sort algorithm used based on lexicographical comparison, and sorted list of Pets displayed.
     */
    public void sortByName() {
        ArrayList<Pet> pets = getPetsArray();

        // Selection sort by name (ascending)
        for (int i = pets.size() - 1; i >= 0; i--) {
            // Index 0 is initialised as the first/highest Name alphabetically
            int highestIndex = 0;
            // For loop searches from 0 to current position to find actual highest Name
            for (int j = 0; j <= i; j++) {
                // compareTo() returns a positive value if j comes after highestIndex alphabetically.
                // If it returns a negative value, j comes before highestIndex alphabetically
                // If it returns 0, j and highestIndex are equal lexicographically
                if (pets.get(j).getName().compareTo(pets.get(highestIndex).getName()) > 0) {
                    // If current Pet Name greater than highest found so far, update highestIndex to equal this Pet
                    highestIndex = j;
                }
            }
            // Use swapPets() method to swap highestIndex and i in ArrayList pets each time Pet Name is no longer highest (first alphabetically)
            swapPets(pets, i, highestIndex);
        }

        // Displays list of Pets sorted by Name alphabetically from a-z
        System.out.println("Pets sorted by Name");
        System.out.println(listAllPets());
    }

    /**
     * Sorts Pets in Pets Array List in ascending order by ID.
     * Selection sort algorithm used for comparison, and sorted list of Pets displayed.
     */
    public void sortById() {
        ArrayList<Pet> pets = getPetsArray();

        // // Selection sort Pets by Age starting at the end of list and working backwards
        for (int i = pets.size() - 1; i >= 0; i--) {
            // Index 0 is initialised as the highest ID
            int highestIndex = 0;
            // For loop searches from 0 to current position to find actual highest ID
            for (int j = 0; j <= i; j++) {
                // If current Pet ID greater than highest found so far, update highestIndex to equal this Pet
                if (pets.get(j).getId() > pets.get(highestIndex).getId()) {
                    // If current Pet ID greater than highest found so far, update highestIndex to equal this Pet
                    highestIndex = j;
                }
            }
            // Use swapPets() method to swap highestIndex and i in ArrayList pets each time Pet ID is no longer highest
            swapPets(pets, i, highestIndex);
        }

        // Displays list of Pets sorted by ID
        System.out.println("Pets sorted by ID");
        System.out.println(listAllPets());
    }

    /**
     * Sorts Pets in Pets Array List in ascending alphabetical order by Owner.
     * Selection sort algorithm used based on lexicographical comparison, and sorted list of Pets displayed.
     */
    public void sortByOwner() {
        ArrayList<Pet> pets = getPetsArray();

        // Selection sort by owner (ascending)
        for (int i = pets.size() - 1; i >= 0; i--) {
            // Index 0 is initialised as the first/highest Owner alphabetically
            int highestIndex = 0;
            // For loop searches from 0 to current position to find actual highest Owner
            for (int j = 0; j <= i; j++) {
                // compareTo() returns a positive value if j comes after highestIndex alphabetically.
                // If it returns a negative value, j comes before highestIndex alphabetically
                // If it returns 0, j and highestIndex are equal lexicographically
                if (pets.get(j).getOwner().compareTo(pets.get(highestIndex).getOwner()) > 0) {
                    // If current Pet Owner greater than highest found so far, update highestIndex to equal this Pet
                    highestIndex = j;
                }
            }
            // Use swapPets() method to swap highestIndex and i in ArrayList pets each time Pet Owner is no longer highest (first alphabetically)
            swapPets(pets, i, highestIndex);
        }

        // Displays list of Pets sorted by Owner alphabetically from a-z
        System.out.println("Pets sorted by Owner");
        System.out.println(listAllPets());
    }

    /**
     * Sorts Pets in Pets Array List in ascending order by Age.
     * Selection sort algorithm used for comparison, and sorted list of Pets displayed.
     */
    public void sortByAge() {
        ArrayList<Pet> pets = getPetsArray();

        // Selection sort Pets by Age starting at the end of list and working backwards
        for (int i = pets.size() - 1; i >= 0; i--) {
            // Index 0 is initialised as the highest Age
            int highestIndex = 0;
            // For loop searches from 0 to current position to find actual highest Age
            for (int j = 0; j <= i; j++) {
                // If current Pet Age greater than highest found so far, update highestIndex to equal this Pet
                if (pets.get(j).getAge() > pets.get(highestIndex).getAge()) {
                    // If current Pet Age greater than highest found so far, update highestIndex to equal this Pet
                    highestIndex = j;
                }
            }
            // Use swapPets() method to swap highestIndex and i in ArrayList pets each time Pet Age is no longer highest
            swapPets(pets, i, highestIndex);
        }

        // Displays list of Pets sorted by Age
        System.out.println("Pets sorted by Age");
        System.out.println(listAllPets());
    }

    /**
     * Sorts Dogs in the Pets Array List in ascending alphabetical order by Breed.
     * Non-Dog Pets ignored when deciding Breed order.
     * Selection sort algorithm used based on lexicographical comparison, and sorted list of Dogs displayed.
     */
    public void sortByBreed() {
        ArrayList<Pet> pets = getPetsArray();

        // Selection sort dogs by breed starting at the end of list and working backwards
        for (int i = pets.size() - 1; i >= 0; i--) {
            // Index 0 is initialised as the first/highest Breed alphabetically
            int highestIndex = 0;
            // For loop searches from 0 to current position to find actual highest Breed
            for (int j = 0; j <= i; j++) {
                // Checks if Pet is Dog. If not Dog, skips
                if (pets.get(j) instanceof Dog && pets.get(highestIndex) instanceof Dog) {
                    Dog dogJ = (Dog) pets.get(j);
                    Dog dogHighestIndex = (Dog) pets.get(highestIndex);
                    // compareTo() returns a positive value if dogJ comes after dogHighestIndex alphabetically.
                    // If it returns a negative value, dogJ comes before dogHighestIndex alphabetically
                    // If it returns 0, dogJ and dogHighestIndex are equal lexicographically
                    if (dogJ.getBreed().compareTo(dogHighestIndex.getBreed()) > 0) {
                        // If current Dog Breed greater than highest found so far, update highestIndex to equal this Dog
                        highestIndex = j;
                    }
                }
            }
            // Use swapPets() method to swap highestIndex and i in ArrayList pets each time Dog is no longer highest Breed (first alphabetically)
            swapPets(pets, i, highestIndex);
        }

        // Displays list of Dog Breeds sorted alphabetically from a-z
        System.out.println("Dogs sorted by Breed");
        System.out.println(listAllDogs());
    }


    /**
     * Swaps two Pet objects at specified positions in Array List.
     *
     * @param pets List of pets in which swap will occur
     * @param i Index of the first pet
     * @param j Index of the second pet
     */
    private void swapPets(ArrayList<Pet> pets, int i, int j) {
        // Store Pet at i in a temporary variable. This prevents losing it when we overwrite i
        Pet temp = pets.get(i);
        // Move Pet from j to i. Now i contains what was at j but we still have the original i saved in temp
        pets.set(i, pets.get(j));
        // Move original Pet from i stored in temp to j. Swap is now complete. i and j exchanged Pets
        pets.set(j, temp);
    }



    //--------------------
    // Number of methods
    //--------------------


    /**
     * This method returns the Number of Pets in the DayCare.
     *
     * @return An int value representing the Number of Pet objects in the ArrayList.
     */
    public int numberOfPets() {
        return pets.size(); // Returns the length/size of Array List pets which represents the number of Pets in the Day Care
    }

    /**
     * This method returns the Number of Cats in the DayCare.
     * Using inheritance
     *
     * @return An int value representing the Number of Cat objects in the ArrayList.
     */
    public int numberOfCats() {
        int number = 0;
        for (Pet pet: pets) { // For each loop, checks through each pet in pets (of type Cat), and counts them
            if (pet instanceof Cat){
                number++;
            }
        }
        return number;
    }

    /**
     * This method returns the Number of Dogs in the DayCare.
     * Using inheritance
     *
     * @return An int value representing the Number of Dog objects in the ArrayList.
     */
    public int numberOfDogs() {
        int number = 0;
        for (Pet pet: pets) { // For each loop, checks through each pet in pets (of type Dog), and counts them
            if (pet instanceof Dog){
                number++;
            }
        }
        return number;
    }

    /**
     * This method returns the Number of Dangerous Dogs in the DayCare.
     * Using inheritance and polymorphism
     *
     * @return An int value representing the Number of Dangerous Dog objects in the ArrayList.
     */
    public int numberOfDangerousDogs() {
        int number = 0;
        for (Pet pet: pets) { // For each loop, checks through each pet in pets (of type Dangerous Dog), and counts them
            if (pet instanceof Dog){
                Dog dog = (Dog) pet;   // Polymorphism, cast pet from Pet superclass to Dog subclass
                if (dog.isDangerousBreed()) {
                    number++;
                }
            }
        }
        return number;
    }

    /**
     * This method returns the Number of Indoor Cats in the DayCare.
     * Using inheritance and polymorphism
     *
     * @return An int value representing the Number of Indoor Cat objects in the ArrayList.
     */
    public int numberOfIndoorCats() {
        int number = 0;
        for (Pet pet: pets) { // For each loop, checks through each pet in pets (of type Indoor Cat), and counts them
            if (pet instanceof Cat){
                Cat cat = (Cat) pet;  // // Polymorphism, cast pet from Pet superclass to Cat subclass
                if (cat.isIndoorCat()) {
                    number++;
                }
            }
        }
        return number;
    }

    /**
     * This method returns the Number of Rabbits in the DayCare.
     * Using inheritance and polymorphism
     *
     * @return An int value representing the Number of Rabbit objects in the ArrayList.
     */
    public int numberOfRabbits() {
        int number = 0;
        for (Pet pet : pets) { // For each loop, checks through each pet in pets (of type Rabbit), and counts them
            if (pet instanceof Rabbit) {
                number++;
            }
        }
        return number;
    }



    //-----------------
    // Getter methods
    //-----------------


    /**
     * Get Pet object by Index location
     *
     * @param index Position in the list
     * @return Pet object at that location or null if invalid
     */
    public Pet getPetByIndex(int index) {
        if (Utilities.isValidIndex(pets, index)) { // Using isValidIndex method from Utilities class
            return pets.get(index);
        }
        return null;
    }

    /**
     * Get Pet by ID
     *
     * @param id Pet ID
     * @return Pet with that ID or null if not found
     */
    public Pet getPetById(int id) {
        for (Pet pet : pets) { // For each loop, checks through each pet in pets and counts each one that matches ID
            if (pet.getId() == id) {
                return pet;
            }
        }
        return null;
    }


    /**
     * Get all Pets by Owner Name
     *
     * @param owner Owner Name
     * @return String of Pets or "No Pets for" if none found
     */
    public String getPetsByOwnersName(String owner) {  // Assuming "name" in param was a typo in instructions
        String result = "";
        for (Pet pet : pets) { // For each loop, checks through each pet in pets and adds each one that matches Owner's Name
            if (pet.getOwner().equalsIgnoreCase(owner)) {
                result += pet + "\n";
            }
        }
        return result.isEmpty() ? "No Pets for " + owner : result;
    }


    /**
     * Get all Pets by Pet Name
     * Returns all Pets with specified Name (handles duplicates)
     *
     * @param name Pet Name
     * @return String of all Pets with that name or "No Pets" if none found
     */
    public String getPetsByName(String name) {

        String result = "";
        for (Pet pet : pets) { // For each loop, checks through each pet in pets and adds each one that matches Name
            if (pet.getName().equalsIgnoreCase(name)) {
                result += pet + "\n";
            }
        }
        return result.isEmpty() ? "No Pets found with name: " + name : result;
    }


    /**
     * Find Dog by Owner, Breed, and Age
     *
     * @param owner Owner's name
     * @param breed Dog breed
     * @param age Dog age
     * @return Matching Dog or null
     */
    public Pet findDogByOwnerAndBreedAndAge(String owner, String breed, int age) {
        for (Pet pet : pets) { // For each loop, checks through each pet in pets and adds each one that matches Owner, Breed and Age
            if (pet instanceof Dog) {
                Dog dog = (Dog) pet; // Polymorphism
                if (dog.getOwner().equalsIgnoreCase(owner) && dog.getBreed().equalsIgnoreCase(breed) && dog.getAge() == age) {
                    return dog;
                }
            }
        }
        return null;
    }



    //----------------
    // Other methods
    //----------------

    /**
     * This method takes in a number and checks if it is a valid id in the pets ArrayList.
     *
     * @param id Pet ID
     * @return True if the number passed is a valid pet id, false otherwise.
     */
    public boolean isValidId(int id) {
        for (Pet pet : pets) {
            if (pet.getId() == id) // Originally I used.equals(id) like in examples but == appears to be correct bc id is int
                return false;
        }
        return true;
    }

    /**
     * Initialises and validates Day Care name. Truncates to 20 characters if too long
     *
     * @param daycareName The name to initialize
     * @return Validated and truncated name
     */
    private String initName(String daycareName) {
        return Utilities.truncateString(daycareName, 20);
    }


    /**
     * Calculate total Weekly Income from all Pets
     *
     * @return Total Weekly Income
     */
    public double getWeeklyIncome() {
        double totalIncome = 0.0;
        for (Pet pet : pets) {
                totalIncome += pet.calculateWeeklyFee();
        }
        return Utilities.toTwoDecimalPlaces(totalIncome); // Using 2 decimal method from Utilities class to replicate €0.00
    }

    /**
     * Calculate Average Number of Days Pets stay per week
     *
     * @return Average Number of Days per week
     */
    public double getAverageNumDaysPerWeek() {
        if (pets.isEmpty()) {
            return 0.0;
        }
        int totalDays = 0;
        for (Pet pet : pets) {
            totalDays += pet.numOfDaysInKennel();
        }
        double average = (double) totalDays / pets.size();
        return Utilities.toTwoDecimalPlaces(average);  // Using 2 decimal method from Utilities class to replicate €0.00
    }



    //---------------------
    // Persistence methods
    //---------------------


    /**
     * The save method uses the XStream component to write all the objects in the posts ArrayList
     * to the posts.xml file stored on the hard disk.
     *
     * @throws Exception An exception is thrown if an error occurred during the save e.g. drive is full.
     */
    public void save() throws Exception {
            XStream xstream = new XStream(new DomDriver());
            ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("pets.xml"));
            out.writeObject(pets);
            out.close();
    }

    /**
     * The load method uses the XStream component to read all the models.MessagePost objects from the posts.xml
     * file stored on the hard disk.  The read objects are loaded into the posts ArrayList
     *
     * @throws Exception An exception is thrown if an error occurred during the load e.g. a missing file.
     */
    @SuppressWarnings("unchecked")
    public void load() throws Exception {
        //list of classes that you wish to include in the serialisation, separated by a comma
        Class<?>[] classes = new Class[]{Pet.class, Dog.class, Cat.class, Rabbit.class};

        //setting up the xstream object with default security and the above classes
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        //doing the actual serialisation to an XML file
        ObjectInputStream in = xstream.createObjectInputStream(new FileReader("pets.xml"));
        pets = (ArrayList<Pet>) in.readObject();
        in.close();
    }


    /**
     * Implementation of ISerializer interface that allows system to display specific file name
     * for user when they save and load data (in conjunction with print as shown in Driver).
     *
     * @return String "pets.xml" representing the file name to save to and load from.
     */
    public String fileName(){
        return "pets.xml";
    }
}
