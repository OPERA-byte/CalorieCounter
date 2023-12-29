


public class OptionNotFoundException extends Exception{

   
   public OptionNotFoundException()
   {
      super("Option Not Found: Option Does Not Exist"); 
   }
   
   public OptionNotFoundException(int x, int y)
   {
      super("Option Not Found: Choose From Available Selection " + x +"-" + y); 
   }
   
   public OptionNotFoundException(String x, String y)
   {
       super("<Option Not Found> Choose From the following: " + x + " or " + y); 
   }





}