


public class Entry{

   private String name; 
   private double kCal; 
                                  //Protein   Carbohydrates  Fat  Sodium  Fiber
   private String [] macroNames = {"Protein", "Carbohydrates", "Fat", "Sodium", "Fiber"}; 
   private double [] macroNutrients = {0.0, 0.0, 0.0, 0.0, 0.0}; 

   //Constructor. 
   //Entry: String name, double kCal. 
   public Entry(String name, double kCal)
   {
      this.name = name; 
      this.kCal = kCal; 
   }
   
   //Defualt Constructor. 
   public Entry()
   {
      name = "intialized"; 
      kCal = 0.0; 
   }
   
   //Copy Constructor. 
   public Entry(Entry that)
   {
      this.name = that.name; 
      this.kCal = that.kCal; 
      
      for(int i = 0; i < macroNutrients.length; i++)
      {
         this.macroNutrients[i] = that.macroNutrients[i]; 
      
      }
      
   }

//----------------------------------------------------------------------------------------------------------------
   
   //Set name. 
   public void setName(String name)
   {
      this.name = name; 
   }
   
   //Get name. 
   public String getName()
   {
      return name; 
   }
   
   //Set kCal. 
   public void setKCal(double kCal)
   {
      this.kCal = kCal; 
   }
   
   //Get kCal. 
   public double getKCal()
   {
      return kCal; 
   }
   
   //{Protein, Carbohydrates, Fat, Sodium, Fiber}
   
   //Set protein. 
   public void setProtein(double protein)
   {
      macroNutrients[0] = protein; 
   }
   
   //Get protein. 
   public double getProtein()
   {
      return macroNutrients[0]; 
   }
   
   //Set Carbohydrates. 
   public void setCarbohydrates(double carbohydrates)
   {
      macroNutrients[1] = carbohydrates; 
   }
   
   //Get Carbohydrates. 
   public double getCarbohydrates()
   {
      return macroNutrients[1]; 
   }
   
   //Set Fat. 
   public void setFat(double fat)
   {
      macroNutrients[2] = fat; 
   }
   
   //Get Fat. 
   public double getFat()
   {
      return macroNutrients[2]; 
   }
   
   //Set Sodium. 
   public void setSodium(double sodium)
   {
      macroNutrients[2] = sodium; 
   }
   
   //Get Sodium. 
   public double getSodium()
   {
      return macroNutrients[2]; 
   }
   
   //Set Fiber. 
   public void setFiber(double fiber)
   {
      macroNutrients[3] = fiber; 
   }
   
   //Get Fiber. 
   public double getNutrients()
   {
      return macroNutrients[3]; 
   }
      
//------------------------------------------------------------------------------------------------------------------

   //Print entry details. 
   public void printEntry()
   {
      System.out.println("Name:" + name + "\n" + 
                         "Calories:" + kCal); 
      for(int i = 0; i < macroNames.length; i++)
      {
         System.out.println(macroNames[i] + ":" + macroNutrients[i]); 
      }
   }


   //toString. 
   public String toString()
   {
      return "Name:" + name + "\n" +
             "Calories:" + kCal; 
   }

}