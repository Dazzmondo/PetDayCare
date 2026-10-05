# Day Care Console-Based Management System
 
## Brief description of piece
 
Interactive program created with Java to help user check in/add and check out/delete pets from their Day Care. This menu-based management system allows the user to gather various types of information about the pets in their care, their weekly profits, and whether they have capacity to admit new pets. It uses persistence to allow stored data to be loaded and updated over extended periods of time.
 
This was my 2nd project/assignment, submitted on 4 January 2026. All code was manually handwritten with no AI or autocomplete whatsoever.
 
I have combined the README and the separate `reflection.txt` where we were asked to reflect on the process of writing the code, note our difficulties, and any remaining bugs. We also needed to respond to 4 questions relating to the fictional character Pat Purrs who gave us design decisions to correctly implement.
 
The code, README, and `reflection.txt` are in their original form, except the formatting has been updated for markdown.
 
## Instructions
 
How to run the program and state how to use any features of it:
 
1. Compile and run `Driver.java`
2. Select relevant option from Main Menu (1 for Pets CRUD submenu, 2 for Reports submenu, 3 for Search Pets menu, 4 for Sort Pets menu, 5 for Statistics menu, 6 to update capacity, 10 save all changes, 11 to load all data, 0 to exit)
3. Enter requested inputs in the respective sub menu.
4. If this is your first time using the programme, you can select 1 (or any of the options). If you have used the programme before and wish to access your previously saved data, you should press 11 to load that previous data before proceeding.
5. Pets CRUD menu offers the option to add a new Pet (1), delete a Pet (2), list all Pets (3), update Pet info (4), or return to main menu (0).
6. Selecting 1 will bring you to a further submenu where you can choose to add a dog (1), a cat (2), or a new rabbit (3). After selecting an option, you will then be prompted to enter the relevant information for the pet - Name, Owner, Sex, Age, ID, days the pet will attend, etc.
7. You can enter repeat info for all other prompts/parameters, but id should always be unique. If you try to enter an ID that already exists, the programme will prompt you to use a different ID. You can view all ids in the system by inputting 3 in the Pets CRUD submenu to list all pets
8. Delete submenu offers the option to delete by index (1), delete by id (2), or return to main menu (0).
9. List all Pets will display all the information associated with each pet saved in the system.
10. Update submenu will give you the option to update a dog (1), a cat (2), a rabbit (3), or return to the main menu (0). After selecting an option, you will then be prompted to enter the relevant information for the pet.
11. The Pet Reports submenu offers the option to list all pets (1), all dogs (2), all cats (3), all rabbits (4), all dangerous dogs (5), all indoor cats (6), all dogs older than an age that the user will be prompted to enter (7), all cats by their favourite toy which the user will be prompted to enter (8), all dogs that are neutered (9), all pets by owner which the user will be prompted to enter (10), or all pets that stay longer than a number of days which the user will be prompted to enter (11). You can also return to the main menu by pressing 0.
12. The Search submenu offers the option to search by pet name (1), by id (2), by owner (3), by age (4), by breed (5), or by owner, breed, and age (6). The user will then be prompted to enter the input to search by. You can press 0 to return to the main menu.
13. The Sort submenu offers the option to sort by pet name (1), by id (2), by owner (3), by age (4), or by dog breed (5). Press 0 to return to main menu.
14. The Statistics submenu allows the user to check the total number of pets (1), number of dogs (2), number of cats (3), number of rabbits (4), number of dangerous dogs (5), number of indoor cats (6), the average days pets attend the Day Care per week (7), the weekly income (8), or the current capacity and available space of the Day Care (9). Press 0 to return to main menu.
15. Select Update Max Capacity if you wish to expand the current max capacity of the Day Care. You will be told the current max capacity and prompted to enter the new max capacity. You can't set the capacity lower than the current number of pets in the Day Care.
16. When you have finished using the programme, you can either choose to save your changes so you can load them the next time you use the programme by pressing 10, or you can simply exit the programme without saving by pressing 0.
17. You will be asked whether you wish to save or not after pressing 0. Enter y to save or enter n to exit.
## How to add new pet types/classes
 
1. Create a new animal class that extends the `Pet` superclass
2. Enter the constructor and any fields. Your parameters should include `super(name, owner, sex, age, id)` as these are inherited from the `Pet` superclass. In addition to this add any unique parameters for that animal type. Add the getters and setters. Add a static `DAILY_RATE` that can be used to calculate the weekly fee for the animal type and make sure to add a `toString()` method. Override `CalculateWeeklyFee()` and `toString()` methods.
3. In the `DayCare` class add `update[NewAnimal]()`, `listAll[NewAnimals]()`, `numberOf[NewAnimals]` methods, and add the new animal class to the persistence `load()` method:
```java
    public void load() throws Exception {
            //list of classes that you wish to include in the serialisation, separated by a comma
            Class<?>[] classes = new Class[]{Pet.class, Dog.class, Cat.class, Rabbit.class, [NewAnimal].class};
```
 
4. In the `Driver` class update the menus to include relevant new animal options and reports.
5. If you wish to implement validation on your new animal class, you should consider creating a separate helper class in `utils` with any such methods. See `CatToyUtility` and `DogBreedUtility` classes for examples. Make sure to update any other methods which might be affected.
## Added Functionality
 
- Added Rabbit models class and Rabbit-related functionality
- Added `ISerializer` interface to utils and applied it to the `load()`/persistence methods
- Added sort submenu and sorting methods
- Added do while loop validation and integrated `CatToyUtility` and `DogBreedUtility` classes
- Added Javadoc for `Pet`, `Dog`, `Cat`, and `Rabbit` classes
- Changed weekly income from reports menu to statistics menu. Added pets by owner and pets that stay more than a certain number of days to reports menu
- Changed List all Dogs older than Age to List all Pets older than age as seemed unnecessarily restrictive to not list cats, rabbits and any other pets over that age.
- Added wrapper methods for several reports menu options, e.g. `listPetsByOwner`
- Added search submenu and search methods to `Driver` (though these do not use search algorithms like linear or binary search)
- Added statistics submenu which integrated the various `numberOf` methods, the `getAverageNumberOfDaysPerWeek()` methods, and the `getWeeklyIncome()` method from `DayCare` class
- Added `viewCapacity()` and `updateMaxCapacity()` methods which enable the user to see and update the current max capacity of the Day Care
- Added an additional prompt to save before exiting to the `exitApp()` method and linked it to the `savePets()` method
- Added the option to return to main menu by pressing 0 in all submenus
- Made `dogBreeds` HashSet functional
- Made `checkBreed()` method functional
- Added `getDogBreeds` method to `DogBreedUtility`
- Added more dog breeds to `dogBreeds` HashSet
- Added newline `\n` to `getCatToys()` in `CatToyUtility` to make options easier to read. Now the options are listed vertically as opposed to all on the one horizontal line as before.
- Added `getPetsByName()` method to `DayCare` class to enable user to find numerous Pets with the same name. Originally the programme was set up to use `getPet(name)` with `searchByName()`. However this only displayed the first Pet to match this name. If there were several Pets with this name the others would not be displayed.
## Known bugs/problems
 
1. The first time you save you will get this message:
```text
    WARNING: A terminally deprecated method in sun.misc.Unsafe has been called
    WARNING: sun.misc.Unsafe::objectFieldOffset has been called by com.thoughtworks.xstream.converters.reflection.SunUnsafeReflectionProvider (file:.../src/utils/xstream-1.4.21%20(1).jar)
    WARNING: Please consider reporting this to the maintainers of class com.thoughtworks.xstream.converters.reflection.SunUnsafeReflectionProvider
    WARNING: sun.misc.Unsafe::objectFieldOffset will be removed in a future release
```
 
    However the data still saves correctly, so this bug doesn't affect functionality.
 
2. When you select "load all" in the main menu, you get the following message:
```text
    WARNING: A terminally deprecated method in sun.misc.Unsafe has been called
    WARNING: sun.misc.Unsafe::objectFieldOffset has been called by com.thoughtworks.xstream.converters.reflection.SunUnsafeReflectionProvider (file:.../src/utils/xstream-1.4.21%20(1).jar)
    WARNING: Please consider reporting this to the maintainers of class com.thoughtworks.xstream.converters.reflection.SunUnsafeReflectionProvider
    WARNING: sun.misc.Unsafe::objectFieldOffset will be removed in a future release
```
 
    However, the data still loads correctly despite these warning messages, so it isn't a functionality bug.
 
3. If you add a Pet ID that is below 1000, it will automatically assign it to 1000 as the default setting. You can't add a Pet with the same ID. However, if you enter a number below 1000, you will not enter the validation loop. As a result, numerous Pets can end up defaulting to ID 1000. The same issue occurs in update Pet. The best way to fix this is to list Pets, then update Pets to assign each a unique ID, and ensure to always enter an ID of over 1000.
## Sources
 
- <https://www.geeksforgeeks.org/java/arrays-tostring-in-java-with-examples/>
- <https://www.medium.com/@AlexanderObregon/javas-objects-equals-method-explained-3a84c963edfa>
Most code for this assignment was based on lectures and labs.
 
---
 
# Reflection
 
## Chronology of your implementation (what you did first, etc.)
 
I began by trying to break down each part of the project into smaller chunks. I first created the `Pet`, `Dog`, and `Cat` class. I then typed the names of all the various methods in the appropriate sections of each class (I did not add anything else at this point). Once this was done, I began entering all of the constructors, getters and setters methods in full as these were basic. I set the fields and their defaults in each class.
 
I then loaded up various versions of the Shop and Social Network labs. I used these as a template to follow to structure my assignment. I generally tried to follow the naming convention of these example labs also. I implemented the save and load methods for persistence in both `DayCare` and `Driver`. I checked the utilities classes to see what methods I had available, as I wanted to use as many of these as possible. The cat and dog validation looked a bit more complicated, so I left those until later. I then focused on completing my initial `Pet`, `Dog`, and `Cat` classes. I began implementing Utilities methods like `toTwoDecimalPlaces()`, `booleanToYN()`, `YNtoBoolean()`, `truncateString()`, `validStringLength()`, and `validRange()` (only used int method).
 
I then began finishing the `DayCare` class with all the CRUD and list methods. I used Utilities `isValidId()` at this stage also. I followed the structure of our lab examples where possible for the list methods (and most other methods) for structural consistency. I used if and else statements/conditionals throughout to ensure the programme would not hit any errors.
 
I mostly used for loops whenever the system needed an infinite loop or if there was a finite number of loops to be iterated through (often used with `pets.size()`). I also used for loops when iterating through Array Lists. I used for each loops in certain methods like `numberOf` methods when dealing with inheritance, because they were used in the Social Network example and they looked neat. I implemented do while loops later in the `Driver` for validation. Do loops were used because the code to be repeated must be run at least once. A simple while loop was used for the `runMenu()` method, to instruct the programme to keep running the main menu until they pressed 0 to exit, at which point the `exitApp()` method would run.
 
I then turned my focus to the `Driver`. I designed the main menu to match the images in the brief (I would later add update capacity). I used the switch, option, case system to list the various paths that could be taken from the main menu. I typed the method names and then began filling out the rest of the methods and submenus in the `Driver` class. I originally designed all the methods to resemble the images in the brief. I would later add more options and cases as I decided to expand functionality. I always wanted to ensure there was a return to main menu option to ease the user experience and make sure they didn't get stuck in a loop or submenu if there were any bugs.
 
The `Driver` in particular took a long time to complete to the point that the programme was actually functional, as there was a lot of typing required. I omitted the sort methods originally until I could watch the lectures to learn more about them. Once I completed all the menus, list and search methods, I tested the functionality of my programme for the first time. It worked relatively well with some bugs, which I will mention in the challenges section.
 
**Note:** I did not use the search algorithms mentioned in the lectures (linear and binary). I created these methods before seeing these lectures. I instead used if and else statements. If the input was found, it would direct the user to any matching Pets. If the input did not match any Pets, it would reply "no pet found". Because the `getPetsByOwnersName()` method already returned "No Pets for", the `searchByOwner()` method is a bit different. The `getPetsByOwnerName()` method in `DayCare` deals with the if else conditional. The `searchByOwner()` method instead takes the result of the `getPetsByOwnersName()` method and simply prints it.
 
I looked at the methods I had that weren't being used and tried to think of ways to use them. I decided against using `nextId()` as it was causing duplication problems with the ids, and I thought it was easier to simply list the ids and allow the user to update any that were wrong (after later adding validation, duplicate IDs shouldn't happen). I rewatched the Pat Purrs video and noticed she mentioned a max capacity of 90 (currently), which I had not originally implemented. I designed `viewCapacity()` and `updateMaxCapacity()` methods, which I added to the main menu in the `Driver`. This allowed the user to update the max capacity of the day care as Pat Purrs emphasised the need to be flexible with regards to growth and expansion. This allowed me to use the `maxNumberOfPets` fields and methods.
 
I added the validation methods from the `CatToyUtility` class, as these were already complete and simply needed to be implemented in the `Driver`. As mentioned previously, I used do while loops and if statements to ensure the user entered a valid cat toy option from the list (HashMap). The getter method was already created in `CatToyUtility` class unlike in the `DogBreedUtility` class, so I didn't need to edit `CatToyUtility` class much. The only changes I made were adding `\n` to list the options vertically as they were difficult to read all on one horizontal line. I followed the lab examples to ensure I didn't make any mistakes in the validation structure. I tested if the cat toy validation worked, and it did.
 
I then began typing similar code in the `Driver` for the Dog Breed validation but realised quickly it wouldn't work. I then checked the `DogBreedUtility` class again and realised there was no getter. I created this, but it still wasn't working. I looked at the week 10 lectures and realised that there were problems with the dog breeds being in the `checkBreed()` method instead of in the HashSet. I rewrote the code completely to include all the breeds in the HashSet `{{}}` instead. I then changed the `getDogBreeds()` method. This still didn't fix the problem, as I realised the `getDogBreeds()` method I created was completely wrong. I modelled it after the `getCatToys()` method but cat toys was using a HashMap with both Toy Name and Description, while dog breed was using a HashSet with just the Dog Breed name. I looked at the lectures and labs again to figure out how to implement this with a HashSet. I made the changes and then tried running the `Driver` again. This time it worked. I then expanded the HashSet of valid Dog Breeds to 20, as it was too limited before.
 
I added a `Rabbit` subclass of `Pet`, using inheritance in a consistent manner to that of `Dog` and `Cat`. I added colour as a unique parameter for `Rabbit`, but otherwise kept the class very basic. I added Rabbit CRUD and list methods to `DayCare`. I then updated the `Driver` menu where appropriate to incorporate rabbits into the menu. The implementations were all quite clear and straight forward in line with Pat Purrs' brief. The readme has a section explaining how a developer could add in any future animals.
 
I watched the lectures on sorting and decided to implement selection sort instead of bubble sort. I followed the structure of the lab and lecture examples and repeated for each specific sort criteria - Name, Owner, Age, ID, Dog Breed. I tested them and they seemed to work perfectly without any delay.
 
I fixed an issue where the user would be brought back to the main menu if they entered invalid info when adding or updating a pet, e.g. if they entered an invalid id because another pet already had that id number. This was because I had been using `return`. I implemented do while loops in a similar way to the implementation with the Cat Toy and Dog Breed validation to fix this. Now the message would simply loop until the user entered a valid id.
 
I did not use the `checkout()` method even though I did type it into the code. I considered implementing it in the update methods, but it seemed an overly complicated way to track the number of pets. As the deadline came closer I decided it was simpler to just use the delete method and `maxNumberOfPets` methods to keep track of the Day Care max capacity. I designed the `viewCapacity()` and `updateMaxCapacity()` methods as such, relying on `getMaxNumberOfPets` and `numberOfPets()` methods to calculate the available space at any given time. I did implement `checkIn()` as part of the `setDaysAttending()` method. Each time `checkIn()` is called it either sets `daysAttending[]` to true or does nothing and leaves it false. I couldn't think of any use for the `setPetsArray()` method, so that remained unused.
 
I only noticed the mention of using interfaces when I was re-reading through the assignment brief and it was briefly mentioned in the interview section. I implemented `ISerializer` like the Social Network v9 example lab and lecture. I created a new java file under utils called `ISerializer` and made the relevant changes to `DayCare` and `Driver`. The core functionality didn't change much but I added the ability for the user to see what the name of the file they were loading and saving to was using the `fileName()` method.
 
I did not originally use the `initName()` method in `DayCare` `Pet` classes because it was not clear what the method was supposed to do, and there wasn't any description of the method in the brief. However, after my lecturer cleared up that it was used in the constructor for the Day Care name in Day Care class, I implemented it. I had originally used the simple truncate method from the Utilities class for validation in the constructor - `this.name = Utilities.truncateString(name, 20);`. I changed this by creating the `initName()` method which now uses the `truncateString()` method from Utilities in the same way, but the constructor is now updated to - `this.name = initName(name)`. I also updated `setName()` to use the `initName()` method.
 
I clarified with my lecturer that the sort methods should be put in the Day Care class instead of the `Driver`. I made this change, and changed each sort method from private to public. I also updated the `sortPets()` cases in `Driver` from `sortByName()` to `daycare.sortByName`, etc.
 
I used Javadoc in the `DayCare` class as suggested in the assignment brief. I also used Javadoc in the `Pet`, `Dog`, `Cat`, and `Rabbit` classes. I did not use Javadoc in the `Driver`, as I decided it would be confusing and time constraining with all the menus and other methods in that class.
 
I realised when testing my programme again, that my `searchByName()` method would only display one Pet with the same name. This was because it was using `getPet(name)` from `DayCare`, but this did not integrate the Array List `pets`. Instead it would simply find the first Pet to match that name and then stop. Thus, there could be 3 Pets named Patch, but only 1 would be displayed when the user input 1 in the Search submenu to Search Pet by Name. This was a problem because many pets have the same name. To fix this I introduced a new method `getPetsByName()` to `DayCare` and integrated it into `searchByName()`. This new method did use the Array List `pets`, and therefore enabled the user to find numerous Pets with the same name.
 
I finished completing the readme and reflection. I tried to change the name of my project in IntelliJ from `DayCare` to `darraghbreheny` following the instructions of the brief, but it kept returning back to `DayCare` any time I reopened it. I tried creating a copy folder and saving the copy as `darraghbreheny` in IntelliJ instead. I'm not sure whether this worked. I then deleted the `pets.xml` file I had in my folder, because I wanted to ensure you had unique and new data for your version of the assignment. I believe a new `pets.xml` file should be created once you save for the first time. Finally I zipped the assignment and submitted it to Moodle.
 
## Main difficulties you came across in your development of solution and how you solved them
 
The actual scale of the assignment was quite daunting. I struggled to remember where all the different code and methods were. I tried to tackle this by focusing on individual sections first, e.g. getters, setters, menu design, etc. The more complicated methods that I was less sure of like `equals()`, `toString()`, some more complicated validation, and implementation of the utilities methods, I completed later. The individual code wasn't that difficult, it was simply the scale of it all.
 
I found it difficult to understand what `equals()` did in `Dog` class. From what I could find on the internet it sounded like it would be more likely used in `Pet` as it was the superclass. I was unsure if there might have been a mistake in the brief. I generated it in IntelliJ as recommended in the Assignment video, but even that was confusing. There were different versions, including `getClass()`, which I opted for, and also `instanceof`. I wasn't sure if the selection of one of these made any difference. There were also different options based on Java versions. I experimented with not having `equals()`, having `equals()` in `Dog`, and also having `equals()` in `Pet`, but none of them seemed to make any difference to how my programme performed from what I could see. This made it very difficult to understand what exactly the function of this method was in my code. This is something I will need to clarify, as it is the only piece of my code I don't actually understand.
 
I originally was having difficulty displaying the `daysAttending` info when listing pets. This was because `daysAttending` was boolean and when I included it in the `toString()` method, it would simply return hexadecimal. I fixed this by using the `Arrays.toString()` method.
 
I had other minor typos that caused issues running the programme such as missing or unwanted brackets `}`. I also had an issue displaying my submenus because I had unknowingly used `return ScannerInput.readNextInt()` from the `mainMenu()` method when I should have used `int option = ScannerInput.readNextInt()`. Once I noticed this mistake the programme began working properly.
 
I also forgot to include `setID()` in my `updateDog()` and `updateCat()` methods, so I couldn't solve the issue of duplicate ids for a while. I only noticed this when I wrote the corresponding `DayCare` code for the `Rabbit` class and noticed I could update the id correctly for rabbits but not cats or dogs that I realised where the mistake was. Once I identified this mistake I fixed it. In a smaller assignment with less code I probably would have identified this quicker, but it again points to the actual scale of the code being the biggest challenge.
 
I originally simply used `return` instead of do while loops for the add methods, which resulted in the user being brought back to the main menu if the user entered invalid input, e.g. an already used id. I fixed this by implementing do while loops with validation in a similar way to my validation implementation with the Cat Toys and Dog Breeds. Do loops were used because the code to be repeated must be run at least once.
 
I also did not originally have do while loops for the sex, dangerous breed, neutered, indoor cats, or days attending replies. These did not have validation, so when something that wasn't m or f, or y or n, was input, they would simply use the default value. I updated this with do while loops in the same way I did to other prompts. Now the programme would ask you to enter a valid input before moving on to the next prompt.
 
As mentioned in detail above, I encountered difficulties when trying to implement validation for the `DogBreedUtility` class, but I solved the problems by referring to lectures and labs.
 
As mentioned above, `searchByName()` would only display 1 Pet with that name, even if there were 2+ Pets with that name. This was because the `getPet(name)` method it was calling did not iterate through the `pets` Array List. I solved this by introducing a new method into the `DayCare` class called `getPetsByName()` which used the Array List `pets`. I then updated the `searchByName()` method to call `getPetsByName()` instead of `getPet(name)`.
 
In my `Driver` class the formatting went wrong for the indenting, which resulted in huge amounts of the spaces in my code needing to be manually deleted to make the formatting consistent. I tried to find a quick way to automatically fix the indenting in IntelliJ but didn't find anything (I wouldn't be surprised if a feature did exist and I simply couldn't find it). I deleted 4 spaces on every line. This was very time-consuming and frustrating. A lesson I'll learn in the future is to make sure the indenting is correct early before adding huge amounts of code that then needs to be edited.
 
## Any bugs remaining in the solution or unfinished elements of spec
 
- The first time you save you will get this message:
```text
    WARNING: A terminally deprecated method in sun.misc.Unsafe has been called
    WARNING: sun.misc.Unsafe::objectFieldOffset has been called by com.thoughtworks.xstream.converters.reflection.SunUnsafeReflectionProvider (file:.../src/utils/xstream-1.4.21%20(1).jar)
    WARNING: Please consider reporting this to the maintainers of class com.thoughtworks.xstream.converters.reflection.SunUnsafeReflectionProvider
    WARNING: sun.misc.Unsafe::objectFieldOffset will be removed in a future release
```
 
    However the data still saves correctly, so this bug doesn't affect functionality.
 
- When you select "load all" in the main menu, you get the following:
```text
    WARNING: A terminally deprecated method in sun.misc.Unsafe has been called
    WARNING: sun.misc.Unsafe::objectFieldOffset has been called by com.thoughtworks.xstream.converters.reflection.SunUnsafeReflectionProvider (file:.../src/utils/xstream-1.4.21%20(1).jar)
    WARNING: Please consider reporting this to the maintainers of class com.thoughtworks.xstream.converters.reflection.SunUnsafeReflectionProvider
    WARNING: sun.misc.Unsafe::objectFieldOffset will be removed in a future release
```
 
    However, the data still loads correctly despite these warning messages, so it isn't really a bug that impacts the user experience.
 
- If you add a Pet ID that is below 1000, it will automatically assign it to 1000 as the default setting. You can't add a Pet with the same ID. However, if you enter a number below 1000, you will not enter the validation loop. As a result, numerous Pets can end up defaulting to ID 1000. The same issue occurs in update Pet. The best way to fix this is to list Pets, then update Pets to assign each a unique ID, and finally ensure to always enter an ID of over 1000.

## Main learnings from your engagement with assignment
 
My main learnings were how time-intensive putting together a functional system or app actually is. The key functionality of the system is somewhat standard, but I really gained an appreciation for how long it takes to simply type so many different methods, and design so many different menus and submenus. I also found it quite overwhelming at times, as I needed to take far more breaks than in previous assignments. The amount of writing gave me quite frequent headaches, and I found myself unable to think through quite basic logical methods at times. I also frequently found it difficult to remember where in my code each method and field was, and how they were interacting across classes became very difficult to keep track of.
 
I did feel a sense of accomplishment at the end of the process though. The design might not be revolutionary, but it is a system that could genuinely be implemented and tweaked by a real pet day care centre. The structure and segmentation of the different classes gave me a better overall view of the structure of how an app is developed. It was very valuable in this way. I suspect the more I try to design similar console-based systems, featuring menus, persistence, CRUD, listing, searching, and sorting, the more consistent the code design will seem. I feel better prepared for a real-world, practical project now than I did before I attempted this project.
 
## Answer the following questions
 
### 1. Pat Purrs mentioned a problem their friend had with their pet system. What was the issue with that system, and how is your design intended to avoid the same problem?
 
Pat Purrs' friend wrote all their code in one giant class. It wasn't separated, had no clear model or controller, and everything was tied to console interface. This meant that when wanted to change/update app, add animals or anything else, the whole code needed to be rewritten from scratch. My design addresses this by using a controller class called `DayCare`, model classes - `Dog`, `Cat`, `Rabbit`, a `Driver` class for user input and menu display, and also several utils classes with reusable methods and interfaces. This makes it easier to edit small parts of the code in the future to address specific problems, instead of needing to rewrite the entirety of the code, which could result in the programmer becoming confused by any specific errors that could occur as a result of their edits.
 
### 2. Why does Pat want the Driver class to be simple and user-friendly, and how does this relate to the plan to eventually develop a mobile app?
 
Pat understandably wants the `Driver` class to be simple and user-friendly, as this class will not only be responsible for the menu display and navigation, but also the only class that handles user input. An overly complicated design could lead to their workforce becoming confused about how to find the specific option they need. This is why I have tried to make the menu navigation logical and easy to navigate. Pat also wishes to later develop a mobile app. A simple user-friendly `Driver` class will make any changes from a console-based to mobile-based ui easier to design, while limiting the number of methods within the `Driver` class to keep it organised and well-structured, will make any rewrites very basic, if indeed they need to perform any rewrites at all.
 
### 3. Pat talked about future expansion of the day care. What kinds of growth does the system need to support, and how should your code design accommodate this?
 
Pat mentioned they currently have a max capacity of 90 pets they can handle in their day care, but mentioned they plan to expand. I have included an option for employees to check their max capacity to ensure they don't accidently check in too many animals at once. However, I have also included an option for them to increase the max capacity of the Day Care as they expand. The default is 90, but they could easily increase this to 100 or more for example, through the menu. Pat also mentioned they only take dogs and cats currently, but did express a potential interest in expanding to admit other pets too. Pat specifically mentioned rabbits as one type of pet they were considering admitting in the future, so I actually implemented a rabbit class and made the relevant changes to the `DayCare` and `Driver` classes. If they do wish to start admitting rabbits in the near future, this option is already built into the menu for them, saving them time. However, if they wish to add more pet types in the future, e.g. ferrets, this is very easily done. I have given instructions on how a developer could do this in my readme.
 
### 4. Besides cats and dogs, what additional animal does Pat plan to include next, and what does this imply for how you structure your inheritance hierarchy?
 
This was already mentioned in point 3, but Pat wanted to expand to rabbits. The `Rabbit` class that was created as a subclass of the `Pet` superclass and inherits the same parameters that `Dog` and `Cat` do - name, owner, sex, age, id. `Rabbit` also has its own unique parameter - colour. There is no validation or limited options for this class. An employee could enter basic colours like black, brown or white, but could also enter mauve, strawberry blond, etc. if they wish to be more specific. Each rabbit has a set daily rate of €35, and their weekly fee is calculated by multiplying this by the number of days they spend in the day care.
 
## Mandatory: Please list any references used in your development/implementation of your submission
 
- <https://www.geeksforgeeks.org/java/arrays-tostring-in-java-with-examples/>
- <https://medium.com/@AlexanderObregon/javas-objects-equals-method-explained-3a84c963edfa>
Most of the code in this assignment relied on code from lectures and labs.
 
## Self-Assessment
 
I believe I have met the criteria laid out in the Marking Scheme from the Assignment brief.
 
In regard to the functionality of the code: I have used inheritance for the `Pet` (abstract), `Dog`, and `Cat` classes. I have a comprehensive and well laid-out controller `DayCare` class with basic CRUD methods, reporting/NumberOf methods, validation methods and persistence, as well as integrating the other methods mentioned in the brief. The UX has a good menu structure with many submenus that branch off from each other while always offering a way to return to the main menu. I believe the presentation looks good in line with the menus displayed in the brief. All ArrayListCRUD types were handled and there is a full Reports menu for all pets. I integrated all the other methods I could into the menu structure. The overall style has extensive comments throughout all classes of the code, has used standard naming and indentation while trying to avoid unnecessary repetition wherever possible in line with DRY principles. Javadoc is used throughout the `DayCare` class.
 
I have included this reflection document as well as a readme.
 
In regards to extra functionality:
 
I have added an extra animal class - `Rabbit`. I have added in extra reports (List all Pets by Owner and List all Pets that stay more than a certain number of days). I'm not sure whether my menu system qualifies as fancy but it has a clean and consistent interface with many branching submenus. I also added features like an `ISerializer` interface, sorting methods and a sort submenu, search methods, a statistics submenu, the ability to view and update max capacity, among other changes. I have listed all my changes in the readme which you can refer to for a more comprehensive list and explanation of extra functionality I added to this assignment outside of the brief.
 
## Academic Integrity Statement
 
Please consider the following statements and choose one (delete the inappropriate one):
 
- This is my work apart from the specific references noted above (and any code from class notes). I understand the code and can describe any parts of the solution if needed;