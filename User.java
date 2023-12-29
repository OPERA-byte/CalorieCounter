import java.util.ArrayList; 


public class User{

   private String name; 
   private double height; 
   private double weight; 
   private double age; 
   private String sex; 
   private double levelOfExercise; 
   private ArrayList<Entry> entries = new ArrayList<>(); 
   private double BMR;
   private double runningAllowence;  
   
   
   //Constructor. 
   //User: String name, double height, double weight, double age, String sex, double levelOfExercise. 
   public User(String name, double height, double weight, double age, String sex, double levelOfExercise)
   {
      this.name = name; 
      this.height = height; 
      this.weight = weight; 
      this.age = age; 
      this.sex = sex; 
      this.levelOfExercise = levelOfExercise; 
      BMR = 0.0; 
      runningAllowence = BMR; 
      entries = new ArrayList<>(); 
   }
   
   //Defualt Constructor. 
   public User()
   {
      name = "initialized"; 
      height = 0.0; 
      weight = 0.0; 
      age = 0.0; 
      sex = "initialized"; 
      levelOfExercise = 1.2;
      BMR = 0.0; 
      runningAllowence = BMR; 
      entries = new ArrayList<>(); 
   }
   
   //Copy Constructor. 
   public User(User that)
   {
      this.name = that.name; 
      this.height = that.height; 
      this.weight = that.weight; 
      this.age = that.age; 
      this.sex = that.sex; 
      this.levelOfExercise = that.levelOfExercise; 
      this.BMR = that.BMR; 
      this.runningAllowence = that.BMR; 
      this.entries.addAll(that.entries); 
   }
   
//-------------------------------------------------------------------------------------------------------------------
   //Setters and Getters. 
   
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
   
   //Set height. 
   public void setHeight(double height)
   {
      this.height = height;
   }
   
   //Get height. 
   public double getHeight()
   {
      return height; 
   }
   
   //Set weight. 
   public void setWeight(double weight)
   {
      this.weight = weight; 
   }
   
   //Get weight. 
   public double getWeight()
   {
      return weight; 
   }
   
   //Set age. 
   public void setAge(double age)
   {
      this.age = age; 
   }
   
   //Get age. 
   public double getAge()
   {
      return age; 
   }
   
   //Set Sex
   public void setSex(String sex)
   {
       this.sex = sex; 
   }
   
   //Get Sex
   public String getSex()
   {
       return sex; 
   }
   
   //Set Level of Exercise
   public void setLevelOfExercise(double levelOfExercise)
   {
       this.levelOfExercise = levelOfExercise; 
   }
   
   //Get Level of Exercise
   public double getLevelOfExercise()
   {
       return levelOfExercise; 
   }
   
   //Get BMR. 
   public double getBMR()
   {
      return BMR; 
   }
   
   //Set levelOfExercise. 
   public void setLevelOfExercise(int levelOfExercise)
   {
      this.levelOfExercise = levelOfExercise; 
   }
   
   public double calculateBMR(String sex, double levelOfExercise)
   {
      double tempBMR = 00; 
      if(sex.equalsIgnoreCase("male"))
      {
         tempBMR = 655+(6.23 * weight)+(12.7 * height)-(6.8 * age); 
      }
      if(sex.equalsIgnoreCase("female"))
      {
         tempBMR = 655+(4.35 * weight)+(4.7 * height)-(4.7 * age); 
      }
      return tempBMR * levelOfExercise;     
   }
   
   //Calculate BMR 
   public void calculateBMR()
   {
      double tempBMR = 00; 
      if(sex.equalsIgnoreCase("male"))
      {
         tempBMR = 655+(6.23 * weight)+(12.7 * height)-(6.8 * age); 
      }
      else if(sex.equalsIgnoreCase("female"))
      {
         tempBMR = 655+(4.35 * weight)+(4.7 * height)-(4.7 * age); 
      } 
      
      BMR = tempBMR * levelOfExercise;  
      runningAllowence = BMR; 
   
   }
   
   //Calculate Running Balance
   public void calculateRunningBalance()
   {
       double entriesBalance = 0.0; 
       
       for(int i = 0; i < entries.size(); i++)
       {
           entriesBalance += entries.get(i).getKCal(); 
       }
       runningAllowence -= entriesBalance; 
       
   }
   
   //Set Running Allowence
   public void setRunningAllowence(double runningAllowence)
   {
       this.runningAllowence = runningAllowence; 
   }
   
   //Get Running Allowence
   public double getRunningAllowence()
   {
       return runningAllowence; 
   }
   
   //Add Entry
   public void addEntry(Entry entry)
   {
       entries.add(entry); 
   }
   
   //Get Entries
   public ArrayList getEntries()
   {
       return entries; 
   }
   
   //Get Entry 
   public Entry getEntry(int i)
   {
       return entries.get(i); 
   }
   
  //toString 
  public String toString()
  {
      return "Name:" + name + "\n" + 
             "Height:" + height + "\n" + 
             "Weight:" + weight + "\n" + 
             "Age:" + age + "\n" + 
             "Sex:" + sex + "\n" + 
             "Level of Exercise Multiplier:" + levelOfExercise + "\n" + 
             "BMR:"+ BMR; 
  }





}