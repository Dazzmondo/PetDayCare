/*
Project: Day Care console-based management system
*/

package utils;

import java.util.HashSet;
import java.util.Set;

public class DogBreedUtility {
    // Changed based on lab 12, didn't seem correct as before. Now options within {{}} instead of just in checkBreed()
    // HashSet interface, unordered
    private static  Set<String> dogBreeds = new HashSet<>(){{

        add("LABRADOR RETRIEVER");
        add("GERMAN SHEPHERD");
        add("GOLDEN RETRIEVER");
        add("BULLDOG");
        add("FRENCH BULLDOG");
        add("BEAGLE");
        add("ROTTWEILER");
        add("PIT BULL");
    // Added more options as list was quite limited
        add("CHIHUAHUA");
        add("JACK RUSSELL TERRIER");
        add("POODLE");
        add("PUG");
        add("HUSKY");
        add("DACHSHUND");
        add("BORDER COLLIE");
        add("MALTESE");
        add("POMERANIAN");
        add("YORKSHIRE TERRIER");
        add("CORGI");
        add("SHIH TZU");
    }};

    // Added getDogBreeds() method because didn't have a getter in DogBreedUtility originally.
    // Thus, couldn't access HashSet dogBreeds options.
    /**
     * Get a string of all valid Dog Breeds
     *
     * @return String containing all valid Dog Breeds
     */
    public static String getDogBreeds() {
        String info = "Valid Dog Breeds:\n";
        for (String breed : dogBreeds) {
            info += "- " + breed + "\n";
        }
        return info;
    }

    /**
     * Check if Breed is valid
     *
     * @param breed Breed to check
     * @return True if valid. False otherwise
     */
    public static boolean checkBreed(String breed) {
        return dogBreeds.contains(breed.toUpperCase());
    }

}
