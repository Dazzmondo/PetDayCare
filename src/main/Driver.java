/*
Project: Day Care console-based management system
*/

package main;

import controllers.DayCare;
import models.*;
import utils.*;

/**
 * This class runs the application and handles the Day Care I/O
 *
 * @author Darragh Breheny
 * @version 1.0
 *
 */
public class Driver {

    private DayCare daycare = new DayCare();

    public static void main(String[] args) {
        new Driver();
    }

    public Driver() {
        runMenu();
    }

    //----------------------------------------------------------------------------
    // Private methods for displaying the menu and processing the selected options
    //----------------------------------------------------------------------------


    private int mainMenu() {
        return ScannerInput.readNextInt(""" 
             -----PET DAY CARE------
            |  1) Pets CRUD Menu    |
            |                       |
            |  2) Reports Menu      |
            |-----------------------|
            |  3) Search Pets       |
            |                       |
            |  4) Sort Pets         |
            |-----------------------|
            |  5) Statistics Menu   |
            |                       |
            |  6) Update Capacity   |
            |-----------------------|
            |  10) Save all         |
            |  11) Load all         |
            |-----------------------|
            |  0) Exit              |
             -----------------------
            ==>>""");
    }

    private void runMenu() {
        int option = mainMenu();
        while (option != 0){
            switch (option) {
                case 1 -> petsCRUDMenu();
                case 2 -> reportsMenu();
                case 3 -> searchPets();
                case 4 -> sortPets();
                case 5 -> statisticsMenu();
                case 6 -> updateMaxCapacity();
                case 10 -> savePets();
                case 11 -> loadPets();
                default -> System.out.println("Invalid option entered: " + option);
            }

            //pause the program so that the user can read what we just printed to the terminal window
            ScannerInput.readNextLine("\nPress enter key to continue...");
            //display the main menu again
            option = mainMenu();
        }

        exitApp();
    }


    //---------------------
    //  General Menu Items
    //---------------------


    /* MAIN MENU OPTION 1 - PETS CRUD MENU */
    private void petsCRUDMenu() {
        int option = ScannerInput.readNextInt(""" 
             -----PETS CRUD MENU------
            |  1) Add a new Pet       |
            |  2) Delete a Pet        |
            |  3) List all Pets       |
            |  4) Update Pet info     |
            |-------------------------|
            |  0) Return to Main Menu |
             -------------------------
            ==>>""");

        switch (option) {
            case 1 -> addPetMenu();
            case 2 -> deletePetMenu();
            case 3 -> System.out.println(daycare.listAllPets());
            case 4 -> updatePetMenu();
            case 0 -> {} // Return to main menu
            default -> System.out.println("Invalid option.");
        }
    }


    /* PETS CRUD SUB-MENU OPTION 1 - Add Pet */

    private void addPetMenu() {
        boolean isAdded = false; // Initialise to false. Nothing added yet

        int option = ScannerInput.readNextInt("""
             --------------------------
            |      Add a New Pet       |
            |--------------------------|
            |   1) Add a New Dog       |
            |   2) Add a New Cat       |
            |   3) Add a New Rabbit    |
            |--------------------------|
            |   0) Main Menu           |
             --------------------------
            ==>>""");

        switch (option) {
            case 1 -> {
                String name = ScannerInput.readNextLine("Enter Dog Name: ");
                String owner = ScannerInput.readNextLine("Enter Owner Name: ");

                // Validate Sex with do while loop. Used do loop because this step needs to be run at least once
                char sex = 'f'; // Initialised. Not sure if necessary with do loop
                do {
                    sex = ScannerInput.readNextChar("Enter Sex ('m' for male or 'f' for female): ");
                    if (sex != 'm' && sex != 'M' && sex != 'f' && sex != 'F') {
                        System.out.println("Invalid input. Please enter 'm' or 'f'.");
                    }
                } while (sex != 'm' && sex != 'f'); // Once valid option chosen loop stops

                int age = ScannerInput.readNextInt("Enter Dog Age: ");

                // Validate ID with do while loop. Used do loop because this step needs to be run at least once
                int id = 1000; // Initialised. Not sure if necessary with do loop
                do {
                    id = ScannerInput.readNextInt("Enter Dog ID: ");
                    if (!daycare.isValidId(id)) {
                        System.out.println("ID already exists. Please use different ID.");
                    }
                } while (!daycare.isValidId(id));  // Once valid option chosen loop stops

                // Uses DogBreedUtility options to display only valid options
                // Displays in random order because of Hash Set interface
                System.out.println("\n" + DogBreedUtility.getDogBreeds());
                //Used do while loop to deal with invalid Dog Breed inputs. Validation.
                // Used do loop because this step needs to be run at least once
                String breed = ""; // Initialised. Not sure if necessary with do loop
                do {
                    breed = ScannerInput.readNextLine("Enter Dog Breed (must be one of the above options): ");
                    if (!DogBreedUtility.checkBreed(breed)) {
                        System.out.println("Invalid Breed. Please choose from above");
                    }
                } while (!DogBreedUtility.checkBreed(breed)); // Once valid option chosen loop stops

                // Validate reply with do while loop. Used do loop because this step needs to be run at least once
                char dangerousBreedChar = 'N'; // Initialised. Not sure if necessary with do loop
                do {
                    dangerousBreedChar = ScannerInput.readNextChar("Is Dog a Dangerous Breed? (Y or N): ");
                    if (dangerousBreedChar != 'y' && dangerousBreedChar != 'Y' &&
                            dangerousBreedChar != 'n' && dangerousBreedChar != 'N') {
                        System.out.println("Invalid input. Please enter 'Y' or 'N'.");
                    }
                } while (dangerousBreedChar != 'y' && dangerousBreedChar != 'Y' &&
                        dangerousBreedChar != 'n' && dangerousBreedChar != 'N'); // Once valid option chosen loop stops
                // YNtoBoolean method from Utilities used to convert y to true and f to false
                boolean dangerousBreed = Utilities.YNtoBoolean(dangerousBreedChar);

                // Validate reply with do while loop. Used do loop because this step needs to be run at least once
                char neuteredChar = 'F'; // Initialised. Not sure if necessary with do loop
                do {
                    neuteredChar = ScannerInput.readNextChar("Is Dog Neutered? (Y or N): ");
                    if (neuteredChar != 'y' && neuteredChar != 'Y' &&
                            neuteredChar != 'n' && neuteredChar != 'N') {
                        System.out.println("Invalid input. Please enter 'Y' or 'N'.");
                    }
                } while (neuteredChar != 'y' && neuteredChar != 'Y' &&
                        neuteredChar != 'n' && neuteredChar != 'N'); // Once valid option chosen loop stops
                // YNtoBoolean method from Utilities used to convert y to true and f to false
                boolean neutered = Utilities.YNtoBoolean(neuteredChar);

                // Originally followed Social Media and Shop Examples with code
                // isAdded = daycare.addPet(new Dog(name, owner, sex, age, id, breed, dangerousBreed, neutered));
                // Realised this wasn't the best approach as Dog dog = new Dog() was simpler
                Dog dog = new Dog(name, owner, sex, age, id, breed, dangerousBreed, neutered);
                inputDaysAttending(dog);
                isAdded = daycare.addPet(dog); // Returns true if successful or false if Day Care is full

                if (isAdded) {
                    System.out.println("Dog added successfully");
                } else {
                    System.out.println("Failed to add Dog. Daycare may be full");
                }
            }

            case 2 -> {
                String name = ScannerInput.readNextLine("Enter Cat Name: ");
                String owner = ScannerInput.readNextLine("Enter Owner Name: ");

                // Validate Sex with do while loop. Used do loop because this step needs to be run at least once
                char sex = 'f'; // Initialised. Not sure if necessary with do loop
                do {
                    sex = ScannerInput.readNextChar("Enter Sex ('m' for male or 'f' for female): ");
                    if (sex != 'm' && sex != 'M' && sex != 'f' && sex != 'F') {
                        System.out.println("Invalid input. Please enter 'm' or 'f'.");
                    }
                } while (sex != 'm' && sex != 'f'); // Once valid option chosen loop stops

                int age = ScannerInput.readNextInt("Enter Cat Age: ");

                // Validate ID with do while loop. Used do loop because this step needs to be run at least once
                int id = 1000; // Initialised. Not sure if necessary with do loop
                do {
                    id = ScannerInput.readNextInt("Enter Cat ID:  ");
                    if (!daycare.isValidId(id)) {
                        System.out.println("ID already exists. Please use different ID.");
                    }
                } while (!daycare.isValidId(id)); // Once valid option chosen loop stops

                // Uses CatToyUtility class Toy options to display only valid options
                // Displays in order because uses HashMap interface
                System.out.println("\n" + CatToyUtility.getCatToys());
                //Used do while loop to deal with invalid Cat Toy inputs. Validation.
                // Used do loop because this step needs to be run at least once
                String favouriteToy = ""; // Initialised. Not sure if necessary with do loop
                do {
                    // Changes any input to upper class to match catToys Hash Map
                    favouriteToy = ScannerInput.readNextLine("Enter Cat's Favourite Toy (must be one of the above options): ").toUpperCase();
                        if (!CatToyUtility.isCatToy(favouriteToy)) {
                            System.out.println("Invalid Toy. Please choose from above");
                        }
                    } while (!CatToyUtility.isCatToy(favouriteToy)); // Once valid option chosen loop stops

                // Validate reply with do while loop. Used do loop because this step needs to be run at least once
                char indoorCatChar = 'N'; // Initialised. Not sure if necessary with do loop
                do {
                    indoorCatChar = ScannerInput.readNextChar("Is it an Indoor Cat? (Y or N): ");
                    if (indoorCatChar != 'y' && indoorCatChar != 'Y' &&
                            indoorCatChar != 'n' && indoorCatChar != 'N') {
                        System.out.println("Invalid input. Please enter 'Y' or 'N'.");
                    }
                } while (indoorCatChar != 'y' && indoorCatChar != 'Y' &&
                        indoorCatChar != 'n' && indoorCatChar != 'N'); // Once valid option chosen loop stops
                // YNtoBoolean method from Utilities used to convert y to true and f to false
                boolean indoorCat = Utilities.YNtoBoolean(indoorCatChar);

                // Follows same structure as Dog
                Cat cat = new Cat(name, owner, sex, age, id, favouriteToy, indoorCat);
                inputDaysAttending(cat);
                isAdded = daycare.addPet(cat); // Returns true if successful or false if Day Care is full

                if (isAdded) {
                    System.out.println("Cat added successfully");
                } else {
                    System.out.println("Failed to add Cat. Daycare may be full.");
                }
            }

            case 3 -> {
                String name = ScannerInput.readNextLine("Enter Rabbit Name: ");
                String owner = ScannerInput.readNextLine("Enter Owner Name: ");

                // Validate Sex with do while loop. Used do loop because this step needs to be run at least once
                char sex = 'f'; // Initialised. Not sure if necessary with do loop
                do {
                    sex = ScannerInput.readNextChar("Enter Sex ('m' for male or 'f' for female): ");
                    if (sex != 'm' && sex != 'M' && sex != 'f' && sex != 'F') {
                        System.out.println("Invalid input. Please enter 'm' or 'f'.");
                    }
                } while (sex != 'm' && sex != 'f'); // Once valid option chosen loop stops

                int age = ScannerInput.readNextInt("Enter Rabbit Age: ");

                // Validate ID with do while loop. Used do loop because this step needs to be run at least once
                int id = 1000; // Initialised. Not sure if necessary with do loop
                do {
                    id = ScannerInput.readNextInt("Enter Rabbit ID: ");
                    if (!daycare.isValidId(id)) {
                        System.out.println("ID already exists. Please use different ID.");
                    }
                } while (!daycare.isValidId(id)); // Once valid option chosen loop stops

                String colour = ScannerInput.readNextLine("Enter Rabbit Colour: ");

                Rabbit rabbit = new Rabbit(name, owner, sex, age, id, colour);
                inputDaysAttending(rabbit);
                isAdded = daycare.addPet(rabbit); // Returns true if successful or false if Day Care is full

                if (isAdded) {
                    System.out.println("Rabbit added successfully!");
                } else {
                    System.out.println("Failed to add Rabbit. Daycare may be full.");
                }
            }

            case 0 -> {} // Return to Main Menu
            default -> System.out.println("Invalid option entered: " + option);
        }
    }


    /* PETS CRUD SUB-MENU OPTION 2 - Delete Pet */

    // Ask the user to enter the index or id of the object to delete, and assuming it's valid, delete it.
    private void deletePetMenu() {
        int option = ScannerInput.readNextInt("""
              -----Delete Pet Menu-----
            |   1) Delete by Index    |
            |   2) Delete by ID       |
            |   0) Return             |
             -------------------------
            ==>>""");

        switch (option) {
            case 1 -> deleteByIndex();
            case 2 -> deleteById();
            case 0 -> {}
            default -> System.out.println("Invalid option.");
        }
    }

    private void deleteByIndex() {
        System.out.println(daycare.listAllPets());

        if (daycare.numberOfPets() > 0) {   // Only ask the user to choose the product to delete if products exist
            int indexToDelete = ScannerInput.readNextInt("Enter index of Pet to delete: ");
            // Pass the index of the product to controllers.DayCare for deleting and check for success.
            Pet deletedPet = daycare.deletePetByIndex(indexToDelete);
            if (deletedPet != null) {
                System.out.println("Deleted pet: " + deletedPet.getName());
            } else {
                System.out.println("Delete unsuccessful - invalid index");
            }
        }
    }

    private void deleteById() {
        System.out.println(daycare.listAllPets());

        if (daycare.numberOfPets() > 0) {
            int idToDelete = ScannerInput.readNextInt("Enter ID of Pet to delete: ");
            Pet deletedPet = daycare.deletePetById(idToDelete);
            if (deletedPet != null) {
                System.out.println("Deleted Pet: " + deletedPet.getName());
            } else {
                System.out.println("Delete unsuccessful - no ID found");
            }
        }
    }


    /* PETS CRUD SUB-MENU OPTION 4 - Update Pet */

    private void updatePetMenu() {
        if (daycare.numberOfPets() > 0) {
            boolean isUpdated = false; // Initialise to false. Nothing updated yet

            int option = ScannerInput.readNextInt("""
                 ----------------------
                |   1) Update Dog      |
                |   2) Update Cat      |
                |   3) Update Rabbit   |
                |----------------------|
                |   0) Main Menu       |
                 ----------------------
                ==>>""");

            switch (option) {
                case 1 -> {
                    System.out.println(daycare.listAllDogs());
                    if (daycare.numberOfDogs() > 0) {
                        int dogIndex = ScannerInput.readNextInt("Enter index of Dog to update ==> ");

                        if (Utilities.isValidIndex(daycare.getPetsArray(), dogIndex)) {
                            Pet pet = daycare.getPetByIndex(dogIndex);

                            if (pet instanceof Dog) {
                                Dog dog = (Dog) pet;
                                System.out.println("Current details: " + dog);

                                String name = ScannerInput.readNextLine("Enter Dog Name: ");
                                String owner = ScannerInput.readNextLine("Enter Owner's Name: ");

                                // Validate Sex with do while loop. Used do loop because this step needs to be run at least once
                                char sex = 'f'; // Initialised. Not sure if necessary with do loop
                                do {
                                    sex = ScannerInput.readNextChar("Enter Sex ('m' for male or 'f' for female): ");
                                    if (sex != 'm' && sex != 'M' && sex != 'f' && sex != 'F') {
                                        System.out.println("Invalid input. Please enter 'm' or 'f'.");
                                    }
                                } while (sex != 'm' && sex != 'f'); // Once valid option chosen loop stops

                                int age = ScannerInput.readNextInt("Enter Dog Age: ");

                                // Validate ID with do while loop. Used do loop because this step needs to be run at least once
                                int id = 1000; // Initialised. Not sure if necessary with do loop
                                do {
                                    System.out.println("Current ID: " + dog.getId());
                                    id = ScannerInput.readNextInt("Enter Dog ID: ");

                                    // Loop until ID remains unchanged or until a valid ID is entered
                                    if (id != dog.getId() && !daycare.isValidId(id)) {
                                        System.out.println("ID already exists. Please choose different ID or enter current ID if you don't wish to change.");
                                    }
                                } while (id != dog.getId() && !daycare.isValidId(id));

                                System.out.println("\n" + DogBreedUtility.getDogBreeds());
                                String breed = ""; // Initialised. Not sure if necessary with do loop
                                do {
                                    breed = ScannerInput.readNextLine("Enter Dog Breed: ");
                                    if (!DogBreedUtility.checkBreed(breed)) {
                                        System.out.println("Invalid breed. Please choose from above.");
                                    }
                                } while (!DogBreedUtility.checkBreed(breed));

                                // Validate reply with do while loop. Used do loop because this step needs to be run at least once
                                char dangerousBreedChar = 'N'; // Initialised. Not sure if necessary with do loop
                                do {
                                    dangerousBreedChar = ScannerInput.readNextChar("Is Dog a Dangerous Breed? (Y or N): ");
                                    if (dangerousBreedChar != 'y' && dangerousBreedChar != 'Y' &&
                                            dangerousBreedChar != 'n' && dangerousBreedChar != 'N') {
                                        System.out.println("Invalid input. Please enter 'Y' or 'N'.");
                                    }
                                } while (dangerousBreedChar != 'y' && dangerousBreedChar != 'Y' &&
                                        dangerousBreedChar != 'n' && dangerousBreedChar != 'N'); // Once valid option chosen loop stops
                                // YNtoBoolean method from Utilities used to convert y to true and f to false
                                boolean dangerousBreed = Utilities.YNtoBoolean(dangerousBreedChar);

                                // Validate reply with do while loop. Used do loop because this step needs to be run at least once
                                char neuteredChar = 'F'; // Initialised. Not sure if necessary with do loop
                                do {
                                    neuteredChar = ScannerInput.readNextChar("Is Dog Neutered? (Y or N): ");
                                    if (neuteredChar != 'y' && neuteredChar != 'Y' &&
                                            neuteredChar != 'n' && neuteredChar != 'N') {
                                        System.out.println("Invalid input. Please enter 'Y' or 'N'.");
                                    }
                                } while (neuteredChar != 'y' && neuteredChar != 'Y' &&
                                            neuteredChar != 'n' && neuteredChar != 'N'); // Once valid option chosen loop stops
                                // YNtoBoolean method from Utilities used to convert y to true and f to false
                                boolean neutered = Utilities.YNtoBoolean(neuteredChar);

                                Dog updatedDog = new Dog(name, owner, sex, age, id, breed, dangerousBreed, neutered);
                                inputDaysAttending(updatedDog);

                                isUpdated = daycare.updateDog(dog.getId(), updatedDog); // Returns true if found and updated. False if not found or not Dog
                            } else {
                                System.out.println("Selected Pet is not a Dog.");
                            }
                        } else {
                            System.out.println("Invalid index entered.");
                        }
                    }
                }

                case 2 -> {
                    System.out.println(daycare.listAllCats());
                    if (daycare.numberOfCats() > 0) {
                        int catIndex = ScannerInput.readNextInt("Enter index of Cat to update ==> ");

                        if (Utilities.isValidIndex(daycare.getPetsArray(), catIndex)) {
                            Pet pet = daycare.getPetByIndex(catIndex);

                            if (pet instanceof Cat) {
                                Cat cat = (Cat) pet;
                                System.out.println("Current details: " + cat);

                                String name = ScannerInput.readNextLine("Enter Cat Name: ");
                                String owner = ScannerInput.readNextLine("Enter Owner's Name: ");

                                // Validate Sex with do while loop. Used do loop because this step needs to be run at least once
                                char sex = 'f'; // Initialised. Not sure if necessary with do loop
                                do {
                                    sex = ScannerInput.readNextChar("Enter Sex ('m' for male or 'f' for female): ");
                                    if (sex != 'm' && sex != 'M' && sex != 'f' && sex != 'F') {
                                        System.out.println("Invalid input. Please enter 'm' or 'f'.");
                                    }
                                } while (sex != 'm' && sex != 'f'); // Once valid option chosen loop stops

                                int age = ScannerInput.readNextInt("Enter Cat Age: ");

                                // Validate ID with do while loop. Used do loop because this step needs to be run at least once
                                int id = 1000; // Initialised. Not sure if necessary with do loop
                                do {
                                    System.out.println("Current ID: " + cat.getId());
                                    id = ScannerInput.readNextInt("Enter Cat ID: ");

                                    // Loop until ID remains unchanged or until a valid ID is entered
                                    if (id != cat.getId() && !daycare.isValidId(id)) {
                                        System.out.println("ID already exists. Please choose different ID or enter current ID if you don't wish to change.");
                                    }
                                } while (id != cat.getId() && !daycare.isValidId(id));

                                System.out.println("\n" + CatToyUtility.getCatToys());
                                String favouriteToy = ""; // Initialised. Not sure if necessary with do loop
                                do {
                                    favouriteToy = ScannerInput.readNextLine("Enter Cat's Favourite Toy: ").toUpperCase();
                                    if (!CatToyUtility.isCatToy(favouriteToy)) {
                                        System.out.println("Invalid Toy. Please choose from above options");
                                    }
                                } while (!CatToyUtility.isCatToy(favouriteToy));

                                // Validate reply with do while loop. Used do loop because this step needs to be run at least once
                                char indoorCatChar = 'N'; // Initialised. Not sure if necessary with do loop
                                do {
                                    indoorCatChar = ScannerInput.readNextChar("Is it an Indoor Cat? (Y or N): ");
                                    if (indoorCatChar != 'y' && indoorCatChar != 'Y' &&
                                            indoorCatChar != 'n' && indoorCatChar != 'N') {
                                        System.out.println("Invalid input. Please enter 'Y' or 'N'.");
                                    }
                                } while (indoorCatChar != 'y' && indoorCatChar != 'Y' &&
                                            indoorCatChar != 'n' && indoorCatChar != 'N'); // Once valid option chosen loop stops
                                // YNtoBoolean method from Utilities used to convert y to true and f to false
                                boolean indoorCat = Utilities.YNtoBoolean(indoorCatChar);

                                Cat updatedCat = new Cat(name, owner, sex, age, id, favouriteToy, indoorCat);
                                inputDaysAttending(updatedCat);

                                isUpdated = daycare.updateCat(cat.getId(), updatedCat); // Returns true if found and updated. False if not found or not Cat
                            } else {
                                System.out.println("Selected Pet is not a Cat.");
                            }
                        } else {
                            System.out.println("Invalid index entered.");
                        }
                    }
                }

                case 3 -> {
                    System.out.println(daycare.listAllRabbits());
                    if (daycare.numberOfRabbits() > 0) {
                        int rabbitIndex = ScannerInput.readNextInt("Enter index of Rabbit to update ==> ");

                        if (Utilities.isValidIndex(daycare.getPetsArray(), rabbitIndex)) {
                            Pet pet = daycare.getPetByIndex(rabbitIndex);

                            if (pet instanceof Rabbit) {
                                Rabbit rabbit = (Rabbit) pet;
                                System.out.println("Current details: " + rabbit);

                                String name = ScannerInput.readNextLine("Enter Rabbit Name: ");
                                String owner = ScannerInput.readNextLine("Enter Owner's Name: ");

                                // Validate Sex with do while loop. Used do loop because this step needs to be run at least once
                                char sex = 'f'; // Initialised. Not sure if necessary with do loop
                                do {
                                    sex = ScannerInput.readNextChar("Enter Sex ('m' for male or 'f' for female): ");
                                    if (sex != 'm' && sex != 'M' && sex != 'f' && sex != 'F') {
                                        System.out.println("Invalid input. Please enter 'm' or 'f'.");
                                    }
                                } while (sex != 'm' && sex != 'f'); // Once valid option chosen loop stops

                                int age = ScannerInput.readNextInt("Enter Rabbit Age: ");

                                // Validate ID with do while loop. Used do loop because this step needs to be run at least once
                                int id = 1000; // Initialised. Not sure if necessary with do loop
                                do {
                                    System.out.println("Current ID: " + rabbit.getId());
                                    id = ScannerInput.readNextInt("Enter Rabbit ID: ");

                                    // Loop until ID remains unchanged or until a valid ID is entered
                                    if (id != rabbit.getId() && !daycare.isValidId(id)) {
                                        System.out.println("ID already exists. Please choose different ID or enter current ID if you don't wish to change.");
                                    }
                                } while (id != rabbit.getId() && !daycare.isValidId(id));

                                String colour = ScannerInput.readNextLine("Enter Rabbit Colour: ");

                                Rabbit updatedRabbit = new Rabbit(name, owner, sex, age, id, colour);
                                inputDaysAttending(updatedRabbit);

                                isUpdated = daycare.updateRabbit(rabbit.getId(), updatedRabbit); // Returns true if found and updated. False if not found or not Rabbit
                            } else {
                                System.out.println("Selected Pet is not a Rabbit.");
                            }
                        } else {
                            System.out.println("Invalid index entered.");
                        }
                    }
                }

                case 0 -> {} // Return to Main Menu
                default -> System.out.println("Invalid option.");
            }
        }
    }


    /* MAIN MENU OPTION 2 - REPORTS MENU */

    private void reportsMenu() {
        int option = ScannerInput.readNextInt(""" 
             -------------PET REPORTS MENU------------
            |  1) List all Pets                       |
            |  2) List all Dogs                       |
            |  3) List all Cats                       |
            |  4) List all Rabbits                    |
            |  5) List all Dangerous Dogs             |
            |  6) List all Indoor Cats                |
            |  7) List all Pets older than an age     |
            |  8) List all Cats by Favourite Toy      |
            |  9) List all Dogs that are Neutered     |
            | 10) List all Pets by Owner              |
            | 11) List all Pets that stay more than   |
            |        a certain Number of Days         |
            |-----------------------------------------|
            |  0) Return to Main Menu                 |
             -----------------------------------------
            ==>>""");

        switch (option) {
            case 1 -> System.out.println(daycare.listAllPets());
            case 2 -> System.out.println(daycare.listAllDogs());
            case 3 -> System.out.println(daycare.listAllCats());
            case 4 -> System.out.println(daycare.listAllRabbits());
            case 5 -> System.out.println(daycare.listAllDangerousDogs());
            case 6 -> System.out.println(daycare.listAllIndoorCats());
            case 7 -> listPetsOlderThanAge(); // Wrapper used
            case 8 -> listCatsByFavouriteToy(); // Wrapper
            case 9 -> System.out.println(daycare.listAllNeuteredAnimals());
            case 10 -> listPetsByOwner(); // Wrapper
            case 11 -> listPetsStayingMoreThanDays(); // Wrapper
            case 0 -> {} // Return to main menu
            default -> System.out.println("Invalid option.");
        }
    }


    // Wrapper used for listAllPetsOlderThanAge() in DayCare class
    // This method allows user to enter an int representing the minimum Age of the Dog
    private void listPetsOlderThanAge() {
        int age = ScannerInput.readNextInt("Enter minimum age: ");
        System.out.println(daycare.listAllPetsOlderThanAge(age));
    }

    // Wrapper used for listAllCatsByFavouriteToy() in DayCare class
    // This method allows user to enter a String representing Cat's Favourite Toy from a displayed list of valid options
    // Runs validation to ensure a valid option was selected
    private void listCatsByFavouriteToy() {
        // Display all valid Cat Toys using getCatToys() from CatToyUtility class
        System.out.println("\n" + CatToyUtility.getCatToys());
        // Accepts user input and converts it to upper case to match format of valid Toy options
        String toy = ScannerInput.readNextLine("Enter favourite toy: ").toUpperCase();

        // Validates Cat Toy using isCatToy() from CatToyUtility class
        if (!CatToyUtility.isCatToy(toy)) {
            System.out.println("Invalid toy. Please enter from the above list.");
            return;
        }

        // Prints out list of Cat Toys using listAllCatsByFavouriteToy from Day Care class
        System.out.println(daycare.listAllCatsByFavouriteToy(toy));
    }

    // Wrapper used for listAllPetsByOwner() in DayCare class
    // This method allows user to enter a String representing Pet Owner Name
    private void listPetsByOwner() {
        String owner = ScannerInput.readNextLine("Enter Owner Name: ");
        System.out.println(daycare.listAllPetsByOwner(owner));
    }

    // Wrapper used for listAllPetsThatStayMoreThanDays() in DayCare class
    // This method allows user to enter an int representing the Minimum Number of Days the Pet is staying at the Day Care
    private void listPetsStayingMoreThanDays() {
        int days = ScannerInput.readNextInt("Enter minimum number of days: ");
        System.out.println(daycare.listAllPetsThatStayMoreThanDays(days));
    }


    /* MAIN MENU OPTION 3 - SEARCH MENU */

    private void searchPets() {
        int option = ScannerInput.readNextInt("""
             ---------------------------
            |      Search Options       |
            |---------------------------|
            |   1) Search Pet by Name   |
            |   2) Search Pet by ID     |
            |   3) Search Pet by Owner  |
            |   4) Search Pet by Age    |
            |   5) Search Dog by Breed  |
            |   6) Search Dog by Owner, |
            |        Breed, and Age     |
            |---------------------------|
            |   0) Return to Main Menu  |
             ---------------------------
            ==>>""");

        switch (option) {
            case 1 -> searchByName();
            case 2 -> searchById();
            case 3 -> searchByOwner();
            case 4 -> searchByAge();
            case 5 -> searchByBreed();
            case 6 -> searchByOwnerBreedAge();
            case 0 -> {} // Return to main menu
            default -> System.out.println("Invalid option.");
        }
    }


    //-------------------
    //  Search Methods
    //-------------------

    // I created these search methods using logic. I didn't implement linear or binary algorithms.
    // I realise this probably wasn't what was meant by the search menu option in the brief,
    // but I had already created the Day Care and Driver methods before seeing the lecture on search.
    // Because search methods weren't mentioned in the marking scheme, I decided to just keep this functional code
    // to perform a similar role.
    // I also don't feel confident enough in understanding the search algorithms well enough to implement them yet


    private void searchByName() {
        String name = ScannerInput.readNextLine("Enter Pet Name: ");

        // Calls DayCare getPetsByName(), which loops through and returns all Pets with matching Name
        // If doesn't find a match, returns "No Pets found"
        String result = daycare.getPetsByName(name);
        // Displays the resulting String with Pet info
        System.out.println(result);
    }


    private void searchById() {
        // More basic than other search methods because each ID is unique. Don't need to loop through Array List pets
        int id = ScannerInput.readNextInt("Enter Pet ID: ");

        if (daycare.getPet(id) != null) {
            System.out.println("Found: " + daycare.getPet(id));
        } else {
            System.out.println("No pet found with ID: " + id);
        }
    }


    private void searchByOwner() {
        String owner = ScannerInput.readNextLine("Enter Owner Name: ");

        // Calls DayCare getPetsByName(), which loops through and returns all Pets with matching Owner
        // If doesn't find a match, returns "No Pets for" Owner
        String result = daycare.getPetsByOwnersName(owner);
        // Displays the resulting String with Pet info
        System.out.println(result);
    }


    private void searchByAge() {
        int age = ScannerInput.readNextInt("Enter Age: ");
        String result = "";

        // Unlike other search methods, doesn't call on a Day Care method that performs loop,
        // so we need to use for loop here instead
        for (int i = 0; i < daycare.getPetsArray().size(); i++) {
            Pet pet = daycare.getPetsArray().get(i);
            if (pet.getAge() == age) {
                result += i + ": " + pet + "\n";
            }
        }

        if (result.isEmpty()) {
            System.out.println("No pets found with Age: " + age);
        } else {
            System.out.println(result);
        }
    }


    private void searchByBreed() {
        String breed = ScannerInput.readNextLine("Enter Breed: ");
        String result = "";

        // Unlike other search methods, doesn't call on a Day Care method that performs loop,
        // so we need to use for loop here instead
        for (int i = 0; i < daycare.getPetsArray().size(); i++) {
            Pet pet = daycare.getPetsArray().get(i);
            if (pet instanceof Dog) {
                Dog dog = (Dog) pet; // Polymorphism
                if (dog.getBreed().equalsIgnoreCase(breed)) {
                    result += i + ": " + dog + "\n";
                }
            }
        }

        if (result.isEmpty()) {
            System.out.println("No Dog found with Breed: " + breed);
        } else {
            System.out.println(result);
        }
    }


    private void searchByOwnerBreedAge() {
        String owner = ScannerInput.readNextLine("Enter Owner Name: ");
        String breed = ScannerInput.readNextLine("Enter Dog Breed: ");
        int age = ScannerInput.readNextInt("Enter Dog Age: ");

        Pet pet = daycare.findDogByOwnerAndBreedAndAge(owner, breed, age); // Too long to use original daycare.find

        if (pet != null) {
            System.out.println("Found: " + pet);
        } else {
            System.out.println("No Dog found matching Owner: " + owner + ", Breed: " + breed + ", and Age: " + age);
        }
    }


    /* MAIN MENU OPTION 4 - SORT MENU */

    private void sortPets() {
        int option = ScannerInput.readNextInt("""
             ------------------------------
            |            Sort              |
            |------------------------------|
            |  1) Sort by Pet Name         |
            |  2) Sort by Pet ID           |
            |  3) Sort by Owner            |
            |  4) Sort by Pet Age          |
            |  5) Sort by Dog Breed        |
            |------------------------------|
            |  0) Return to Main Menu      |
             ------------------------------
            ==>>""");

        switch (option) {
            case 1 -> daycare.sortByName();
            case 2 -> daycare.sortById();
            case 3 -> daycare.sortByOwner();
            case 4 -> daycare.sortByAge();
            case 5 -> daycare.sortByBreed();
            case 0 -> {}
            default -> System.out.println("Invalid option.");
        }
    }


    /* MAIN MENU OPTION 5 - STATISTICS MENU */

    private void statisticsMenu() {
        int option = ScannerInput.readNextInt("""
             ------------------------------
            |      Statistics Menu         |
            |------------------------------|
            |  1) Total Number of Pets     |
            |  2) Number of Dogs           |
            |  3) Number of Cats           |
            |  4) Number of Rabbits        |
            |  5) Number of Dangerous Dogs |
            |  6) Number of Indoor Cats    |
            |  7) Average Days per Week    |
            |  8) Weekly Income            |
            |  9) Current Capacity         |
            |------------------------------|
            |  0) Return to Main Menu      |
             ------------------------------
            ==>>""");

            switch (option) {
                case 1 -> System.out.println("Total Pets: " + daycare.numberOfPets());
                case 2 -> System.out.println("Total Dogs: " + daycare.numberOfDogs());
                case 3 -> System.out.println("Total Cats: " + daycare.numberOfCats());
                case 4 -> System.out.println("Total Rabbits: " + daycare.numberOfRabbits());
                case 5 -> System.out.println("Dangerous Dogs: " + daycare.numberOfDangerousDogs());
                case 6 -> System.out.println("Indoor Cats: " + daycare.numberOfIndoorCats());
                case 7 -> System.out.println("Average Days per Week: " + daycare.getAverageNumDaysPerWeek());
                case 8 -> System.out.println("Weekly Income: €" + daycare.getWeeklyIncome());
                case 9 -> viewCapacity();
                case 0 -> {}
                default -> System.out.println("Invalid option.");
            }
        }



    //---------------------
    //  Helper Methods
    //---------------------


    // Prompts user to input what days their pet will attend.
    // Asks user to input y or n for each day from Mon-Fri to clarify whether or not their pet is attending that day
    private void inputDaysAttending(Pet pet) {
        System.out.println("Which days will the pet attend? (Mon=0, Tue=1, Wed=2, Thu=3, Fri=4)");
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};

        // Loops 5 times, representing each day from Mon-Fri
        for (int i = 0; i < 5; i++) {
            char attending;
            do {
                attending = ScannerInput.readNextChar(days[i] + "? (y/n): ");
                if (attending != 'y' && attending != 'Y' &&
                        attending != 'n' && attending != 'N') {
                    System.out.println("Invalid input. Please enter 'y' or 'n'.");
                }
            } while (attending != 'y' && attending != 'Y' &&
                    attending != 'n' && attending != 'N');

            // If pet is attending on that day, calls checkIn()
            // YNtoBoolean method from Utilities used to convert y to true and f to false
            if (Utilities.YNtoBoolean(attending)) {
                // When checkIn() is called, it sets that day in daysAttending Array to true
                pet.checkIn(i);
            }
        }
    }


    // Method shows current Day Care name, current max capacity, current occupants, and available space.
    // Prints "Day Care is full" if no space, or "Accepting new pets" if current occupancy is below max capacity
    private void viewCapacity() {
        System.out.println("\n=== Capacity Status ===");
        System.out.println("Day Care Name: " + daycare.getName());
        System.out.println("Maximum Capacity: " + daycare.getMaxNumberOfPets());
        System.out.println("Current Occupancy: " + daycare.numberOfPets());
        System.out.println("Available Space: " + (daycare.getMaxNumberOfPets() - daycare.numberOfPets()));

        if (daycare.numberOfPets() >= daycare.getMaxNumberOfPets()) {
            System.out.println("Day Care is full.");
        } else {
            System.out.println("Accepting new Pets");
        }
    }

    // Method prompts user to update max capacity and returns their input as the new max capacity
    private void updateMaxCapacity(){
        System.out.println("Current Max Capacity is " + daycare.getMaxNumberOfPets() + ".");
        int maxCap = ScannerInput.readNextInt("Enter new Max Capacity: ");

        // Validate that Max Capacity is not lower than the current number of Pets in Day Care
        if (maxCap < daycare.numberOfPets()) {
            System.out.println("Can't set Capacity lower than current number of Pets in Daycare.");
            return;
        }

        daycare.setMaxNumberOfPets(maxCap);
        System.out.println("Max capacity updated to: " + maxCap);
    }

    // On exit, asks user if they want to save their info for the next time they access the programme
    private void exitApp() {
        System.out.println("Would you like to save before exiting? (y/n)");
        char choice = ScannerInput.readNextChar("==>> ");

        // If user selects y, YNtoBoolean translates to true and calls savePets() to save data
        if (Utilities.YNtoBoolean(choice)) {
            savePets();
        }

        System.out.println("Goodbye");
        System.exit(0);
    }


    //-----------------------
    //  Persistence methods
    //-----------------------


    private void savePets() {
        // Implements ISerializer fileName() method
        try {
            // Displays specific file name
            System.out.println("Saving to file:" + daycare.fileName());
            daycare.save();
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e);
        }
    }

    private void loadPets() {
        // Implements ISerializer fileName() method
        try {
            // Displays specific file name
            System.out.println("Loading from file: " + daycare.fileName());
            daycare.load();
        } catch (Exception e) {
            System.err.println("Error reading from file: " + e);
        }
    }

}
