import java.util.Scanner; 
import java.util.ArrayList; 

//Exceptions
import java.util.InputMismatchException; 


public class ProgramX{

    private boolean killSwitch = false; 
    
    private ArrayList<User> users = new ArrayList<>();
    private static int functionalUser;
    private static int functionalEntry; 
     


    /**
     * Displays a menu to the user and performs corresponding actions based on their selection.
     */
    public void menu()
    {
        String newUserMessage = "[1] New User [2] Close Program" + "\n" +
                                "------------------------------------------------------------";
         
            if(users.size() > 0)
            {
                // User Functionality Menu
                String userFunctionalityMessage = "[1] New User            [2] User Functionality" + "\n" + 
                                                  "[3] Change User         [4] New Entry" + "\n" + 
                                                  "[5] Entry Functionality [6] Delete Save Data" + "\n" + 
                                                  "[7] User Reciept        [8] Close Program" + "\n" +
                                                  "Calorie Allowence: " + users.get(functionalUser).getRunningAllowence() + "\n" + 
                                                  "------------------------------------------------------------";
                System.out.println("------------------------------------------------------------"); 
                System.out.println(users.get(functionalUser).getName()); 
                int option = newInteger(userFunctionalityMessage, 1, 8);
                
                switch(option)
                {
                    case 8: //Close Program 
                        killSwitch = true;
                        break; 
                        
                    case 7: //User Recipt
                        System.out.println("User Recipt"); 
                        break; 
                        
                    case 6: //Delete Save Data
                        System.out.println("Delete Save Data");
                        break; 
                        
                    case 5: //Entry Functionality
                        entryFunctionality(); 
                        break; 
                    
                    case 4: //New Entry
                        newEntryBranch();
                        break; 
                        
                    case 3://Change User  
                        changeUser();  
                        break;
                        
                    case 2://User Functionality  
                        userFunctionality();  
                        break; 
                        
                    case 1:// New User
                        newUser();  
                        break;  
                }
            }
            else
            {// New User Menu
                
                System.out.println("------------------------------------------------------------");
                int option = newInteger(newUserMessage, 1, 2); 
                
                switch(option)
                {
                    case 2://Close Program
                        killSwitch = true;  
                        break; 
                        
                    case 1://New User
                        newUser();  
                        break; 
                }
            }
    }
    
    /**
     * Creates a new user by collecting user information through user input.
     * User attributes include name, height, weight, age, sex, and level of exercise.
     * The new user is added to the user repository, and the functional user is set to the newly created user.
     */ 
    public void newUser()
    {
        Scanner keyboard = new Scanner(System.in); 
        String name; 
        double height;
        String heightMessage = "Height:";  
        double weight;
        String weightMessage = "Weight:";  
        double age; 
        String ageMessage = "Age:"; 
        String sex; 
        double levelOfExercise; 
        
        System.out.print("Name:"); 
        name = keyboard.nextLine(); 
        
        height = newDouble(heightMessage); 
        weight = newDouble(weightMessage); 
        age = newDouble(ageMessage); 
        sex = setSex();
        levelOfExercise = setLevelOfExercise();
        
        User user = new User(name, height, weight, age, sex, levelOfExercise); //New User
        user.calculateBMR(); 
        
        users.add(new User(user)); //Add User to userRepository
        
        functionalUser = users.size() - 1; //Set Functional User
    }
    
    /**
     * Displays a menu for user functionality and performs corresponding actions based on user selection.
     * Options include updating user information such as name, age, height, sex, weight, and level of exercise.
     */
    public void userFunctionality()
    {
        Scanner keyboard = new Scanner(System.in); 
        String userFunctionMessage = "[1] New Name     [2] New Age" + "\n" + 
                                     "[3] New Height   [4] New Sex" + "\n" +
                                     "[5] New Weight   [6] New Level of Exercise" + "\n" + 
                                     "[7] Back" + "\n" +
                                     "-------------------------------------------------";
        while(true)
        {
            // Display user information
            printUserInfo();
            
            int option = newInteger(userFunctionMessage, 1, 7); 
            
            switch(option)
            {
                case 7: //Back
                    return; 
                    
                case 6: //New Level of Exercise
                    newLevelOfExercise(); 
                    break; 
                    
                case 5: //New Weight  
                    newWeight(); 
                    break; 
                    
                case 4: //New Sex
                    newSex();
                    break; 
                
                case 3: //New Height 
                    newHeight(); 
                    break; 
                    
                case 2: //New Age
                    newAge(); 
                    break; 
                    
                case 1: //New Name
                    newName(); 
                    break; 
            }
        }
    }
    
    //Change User 
    public void changeUser()
    {
        Scanner keyboard = new Scanner(System.in);
        int option = 0;  
        
        while(true)
        {
            try
            {
                System.out.println("Current User:" + users.get(functionalUser).getName());
                printUserList();
                System.out.println("-------------------------------------------------"); 
                option = keyboard.nextInt(); 
                 
                if(option >= 0 && option <= (users.size() - 1))
                {
                    break; 
                }
                else
                {
                    throw new OptionNotFoundException(); 
                }
            }
            catch(InputMismatchException e)
            {
                System.out.println("-------------------------------------------------"); 
                System.out.println(e.getMessage());
                System.out.println("-------------------------------------------------");
                keyboard.nextLine();
            }
            catch(OptionNotFoundException e)
            {
                System.out.println("-------------------------------------------------"); 
                System.out.println(e.getMessage());
                System.out.println("-------------------------------------------------");
                keyboard.nextLine();
            }
        }
        functionalUser = option;     
    }
    
    //New Entry
    // Entry: String name, double kCal.
    public void newEntry()
    {
        Scanner keyboard = new Scanner(System.in); 
        
        String name; 
        double kCal;
        String kCalMessage = "Calories:";  
        
        
        System.out.print("Name:"); 
        name = keyboard.nextLine(); 
        
        kCal = newDouble(kCalMessage);
        
        Entry entry = new Entry(name, kCal); 
        
        users.get(functionalUser).addEntry(new Entry(entry)); 
        
        functionalEntry = (users.get(functionalUser).getEntries().size() - 1); 
        
        users.get(functionalUser).calculateRunningBalance(); 
        
        String message = "Would you like to add macros? [Yes] [No]"; 
        
        String option = newBoolean(message, "Yes", "No"); 
        
        if(option.equalsIgnoreCase("yes"))
        {
            addMacros();  
        }

    }
    
    //Entry Functionality 
    public void entryFunctionality()
    {
        Scanner keyboard = new Scanner(System.in); 
        
        if(users.get(functionalUser).getEntries().size() <= 0)
        {
            System.out.println(" No Entries to Speak of"); 
        
        }
        else if(users.get(functionalUser).getEntries().size() > 0)
        {
            int option; 
            
            while(true)
            {
                try
                {
                    System.out.println("Choose from the Following Selection"); 
                    printEntryListX();
                    System.out.println("[" + (users.get(functionalUser).getEntries().size() + 1) + "] Back"); 
                    
                    option = keyboard.nextInt(); 
                    
                    if(option >= 1 && option <= (users.get(functionalUser).getEntries().size() + 1))
                    {
                        break; 
                    }
                    else
                    {
                        throw new OptionNotFoundException(); 
                    }   
                }
                catch(InputMismatchException e)
                {
                    System.out.println("-------------------------------------------------"); 
                    System.out.println(e.getMessage());
                    System.out.println("-------------------------------------------------");
                    keyboard.nextLine();
                }
                catch(OptionNotFoundException e)
                {
                    System.out.println("-------------------------------------------------"); 
                    System.out.println(e.getMessage());
                    System.out.println("-------------------------------------------------");
                    keyboard.nextLine(); 
                }  
            }
            
            if(option <= users.get(functionalUser).getEntries().size())
            {
                System.out.println(users.get(functionalUser).getEntry(option - 1).getName() + ": " 
                                 + users.get(functionalUser).getEntry(option - 1).getKCal()); 
            }
            else if(option == users.get(functionalUser).getEntries().size() + 1)
            {
                return; 
            } 
        }
    }
    
    //New Entry Branch
    public void newEntryBranch()
    {
        newEntry(); 
        
        if(users.get(functionalUser).getEntries().size() > 0)
        {
            while(true)
            {
                printEntryList(); 
                String message = "[1] New Entry  [2] Add Macros" + "\n" + 
                                 "[3] Back"; 
                
                int option = newInteger(message, 1, 3); 
                
                switch(option)
                {
                    case 3:
                        return; 
                        
                    case 2:
                        //addMacros();  
                        break; 
                        
                    case 1:
                        newEntry(); 
                        break;    
                }
            }
        
        }
    }
    
    //Protein   Carbohydrates  Fat  Sodium  Fiber
    public void addMacros()
    {
        Scanner keyboard = new Scanner(System.in); 
        double protein; 
        String proteinMessage = "Protein:"; 
        
        double carbohydrates; 
        String carbohydratesMessage = "Carbohydrates:"; 
        
        double fat; 
        String fatMessage = "Fat:"; 
        
        double sodium; 
        String sodiumMessage = "Sodium:"; 
        
        double fiber;
        String fiberMessage = "Fiber:"; 
        
        protein = newDouble(proteinMessage); 
        carbohydrates = newDouble(carbohydratesMessage);
        fat = newDouble(fatMessage); 
        sodium = newDouble(sodiumMessage); 
        fiber = newDouble(fiberMessage); 
        
        users.get(functionalUser).getEntry(functionalEntry).setProtein(protein);           
        users.get(functionalUser).getEntry(functionalEntry).setCarbohydrates(carbohydrates);
        users.get(functionalUser).getEntry(functionalEntry).setFat(fat);           
        users.get(functionalUser).getEntry(functionalEntry).setSodium(sodium);
        users.get(functionalUser).getEntry(functionalEntry).setFiber(fiber);           
         
    }
    
    
    
    
    
    /*
        String name; 
        double kCal;
        String kCalMessage = "Calories:";  
        
        
        System.out.print("Name:"); 
        name = keyboard.nextLine(); 
        
        kCal = newDouble(kCalMessage); 
        
        
        
        
        
        
        
    
    
    */ 
    
    
    
    //Print Entries 
    public void printEntryList()
    {
        for(int i = 0; i < users.get(functionalUser).getEntries().size(); i++)
        {
            System.out.println("+ " + users.get(functionalUser).getEntry(i).getName() + ": " + users.get(functionalUser).getEntry(i).getKCal());    
        }
        System.out.println("-------------------------------------------------");
    
    }
    
    //Print Entries 
    public void printEntryListX()
    {
        System.out.println("Choose from the Following Selection:"); 
        for(int i = 0; i < users.get(functionalUser).getEntries().size(); i++)
        {
            System.out.println("[" + (i + 1) + "] " + users.get(functionalUser).getEntry(i).getName() + ": " + users.get(functionalUser).getEntry(i).getKCal());    
        }
        System.out.println("-------------------------------------------------");
    
    }
    
    //Print Users
    public void printUserList()
    {
        System.out.println("------------------------------------------------------------"); 
        for(int i = 0; i < users.size(); i++)
        {
            System.out.println("[" + i + "]" + users.get(i).getName()); 
        }
    }
    
    //New Name
    public void newName()
    {
        Scanner keyboard = new Scanner(System.in); 
        String newName; 
                        
        System.out.println("-------------------------------------------------"); 
        System.out.print("Name:");
        newName = keyboard.nextLine(); 
                        
        users.get(functionalUser).setName(newName);
    }
    
    //New Age 
    public void newAge()
    {
        Scanner keyboard = new Scanner(System.in);
        double newAge; 
                        
        System.out.println("-------------------------------------------------");
        String ageMessage = "Age:";
        newAge = newDouble(ageMessage); 
                        
        users.get(functionalUser).setAge(newAge); 
    }
    
    //New Height
    public void newHeight()
    {
        Scanner keyboard = new Scanner(System.in);
        double newHeight; 
                        
        System.out.println("-------------------------------------------------");
        String heightMessage = "Height:";
        newHeight = newDouble(heightMessage); 
                        
        users.get(functionalUser).setHeight(newHeight);
    }
    
    //New Sex
    public void newSex()
    {
        Scanner keyboard = new Scanner(System.in);
        String newSex; 
                        
        //System.out.println("-------------------------------------------------");
        newSex = setSex(); 
                        
        users.get(functionalUser).setSex(newSex); 
    }
    
    //New Weight 
    public void newWeight()
    {
        Scanner keyboard = new Scanner(System.in);
        double newWeight; 
                        
        System.out.println("-------------------------------------------------");
        String weightMessage = "Weight:";
        newWeight = newDouble(weightMessage); 
                        
        users.get(functionalUser).setWeight(newWeight);
    }
    
    //New Level Of Exercise
    public void newLevelOfExercise()
    {
        Scanner keyboard = new Scanner(System.in);
        double newLevelOfExercise; 
                    
        System.out.println("-------------------------------------------------");
        newLevelOfExercise = setLevelOfExercise(); 
                        
        users.get(functionalUser).setLevelOfExercise(newLevelOfExercise);
    }
    
    //Print User Details 
    public void printUserInfo()
    {
        System.out.println("-------------------------------------------------"); 
        System.out.println("Name: " + users.get(functionalUser).getName()); 
        System.out.println("Height: " + users.get(functionalUser).getHeight());
        System.out.println("Weight: " + users.get(functionalUser).getWeight());
        System.out.println("Age: " + users.get(functionalUser).getAge());
        System.out.println("Sex: " + users.get(functionalUser).getSex());
        System.out.println("Level of Exercise: " + users.get(functionalUser).getLevelOfExercise());               
        System.out.println("-------------------------------------------------"); 
    }
    
    
    //Set Sex
    public String setSex()
    {
        Scanner keyboard = new Scanner(System.in); 
        String sex; 
        
            while(true)
            {
                try
                {   
                    System.out.println("-------------------------------------------------"); 
                    System.out.print("Sex:"); 
                    sex = keyboard.nextLine(); 
                    
                    if(sex.equalsIgnoreCase("male") ||sex.equalsIgnoreCase("female"))
                    {
                        break; 
                    }
                    else
                    {
                        throw new UnsupportedSexException(); 
                    }
                }
                catch(UnsupportedSexException e)
                {
                    System.out.println("-------------------------------------------------");
                    System.out.println(e.getMessage());
                }
            }
            return sex; 
    }
    
    //setLevelOfExercise
    public double setLevelOfExercise()
    {
        Scanner keyboard = new Scanner(System.in); 
    
        String message = "Level of Exercise:" + "\n" + 
                         "[1] No Exercise" + "\n" + 
                         "[2] Light Exercise" + "\n" + 
                         "[3] Moderate Exercise" + "\n" + 
                         "[4] Intense Exercise" + "\n" + 
                         "[5] Intense Exercise + Physical Job" + "\n" + 
                         "-------------------------------------------------"; 
        double levelOfExercise = 0.0; 
        
        int option = newInteger(message, 1, 5); 
        
        switch(option)
        {
            case 5:
                levelOfExercise = 1.9;
                break; 
                
            case 4:
                levelOfExercise = 1.725; 
                break; 
                
            case 3:
                levelOfExercise = 1.55;
                break; 
                
            case 2:
                levelOfExercise = 1.375; 
                break; 
                
            case 1:
                levelOfExercise = 1.2; 
                break; 
        }
        return levelOfExercise; 
    }
    
    
    
    //Run
    public void run()
    {
        menu(); 
        
        if(!killSwitch)
        {
            run(); 
        }
    
    
    }
    
    /**
     * Reads a boolean value from the user with input validation against two specified options.
     *
     * @param message  The message to be displayed to the user.
     * @param option1  The first valid option (case-insensitive).
     * @param option2  The second valid option (case-insensitive).
     * @return The valid boolean value entered by the user (either option1 or option2).
     */ 
    public String newBoolean(String message, String option1, String option2)
    {
        Scanner keyboard = new Scanner(System.in); 
        String option = null; 
    
        while(true)
        {
            System.out.println(message); 
            option = keyboard.nextLine(); 
            try
            {
                // Check if the entered option is equal to either option1 or option2 (case-insensitive)
                if(option.equalsIgnoreCase(option1) ||option.equalsIgnoreCase(option2))
                {// Break out of the loop if the input is valid
                    break; 
                }
                else
                {// Throw an exception if the input is not one of the valid options
                    throw new OptionNotFoundException(option1, option2); 
                }
            }
            catch(OptionNotFoundException e)
            {// Handle the case where the user enters an invalid option
                System.out.println("------------------------------------------------------------"); 
                System.out.println(e.getMessage());
                System.out.println("------------------------------------------------------------");
            }
        }
        // Return the valid boolean value entered by the user
        return option; 
    }
    
    /**
     * Reads an integer value from the user with input validation within a specified range.
     *
     * @param message The message to be displayed to the user.
     * @param min     The minimum allowed value (inclusive).
     * @param max     The maximum allowed value (inclusive).
     * @return The valid integer value entered by the user within the specified range.
     */ 
    public int newInteger(String message, int min, int max)
    {
        Scanner keyboard = new Scanner(System.in); 
        int option; 
        
        while(true)
        {
            try
            {  
                //Display the message to the user
                System.out.println(message); 
                option = keyboard.nextInt(); //Read option as Integer
                
                // Check if the entered value is within the specified range
                if(option >= min && option <= max)
                {
                    // Break out of the loop if the input is within the range
                    break; 
                }
                else
                {// Throw an exception if the input is outside the specified range
                    throw new OptionNotFoundException(min,max); 
                }
            }
            catch(InputMismatchException e)
            {// Handle the case where the user enters a non-integer value
                System.out.println("-------------------------------------------------"); 
                System.out.println(e.getMessage());
                System.out.println("-------------------------------------------------");
                keyboard.nextLine(); // Consume the invalid input from the buffer     
            }
            catch(OptionNotFoundException e)
            {// Handle the case where the user enters an integer outside the specified range
                System.out.println("-------------------------------------------------"); 
                System.out.println(e.getMessage());
                System.out.println("-------------------------------------------------");
                keyboard.nextLine(); // Consume the invalid input from the buffer 
            }        
        }
        // Return the valid integer value entered by the user within the specified range
        return option; 
    }    
    
    /**
     * Reads a double value from the user with input validation.
     *
     * @param message The message to be displayed to the user.
     * @return The valid double value entered by the user.
     */
    public double newDouble(String message)
    {
        Scanner keyboard = new Scanner(System.in); 
        double option; 
        while(true)
        {
            try
            {
                //Display Message
                System.out.println("--------------------------------------------------");
                System.out.print(message); 
                option = keyboard.nextDouble(); // Read Option as Double
                
                // Break out of the loop if input is successful
                break; 
            }
            catch(InputMismatchException e)
            {
                // Handle the case where the user enters a non-double value
                System.out.println("(!) Invalid Input: Enter Integer (!)");
                keyboard.nextLine(); // Consume the invalid input from the buffer
            }
        }
            // Return the valid double value entered by the user
            return option; 
    }

}