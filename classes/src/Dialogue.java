

import java.util.Scanner;

/**
 *
 * @author lentonen_admin
 */
public class Dialogue {
   
    private static Scanner lukija = new Scanner(System.in);
    static int visited = 0;
    /**
     * 
     * @return 
     */
    public static void talkToGirl(boolean follow){
       
        // if you have aid
        if(follow){
            followDialogue(); 
        }
        else{
            basicDialogue();
        }
        visited ++;
        System.out.println("Press any key to continue.");
        lukija.nextLine();
        
    }
    
    public static void basicDialogue(){
        
        //System.out.println(visited);
        AsciiPics.theGirl();
        
        if(visited == 0){
            System.out.println("You: Who are you?");
            lukija.nextLine();
            System.out.println("Woman prisoner: My name is Aino. I am a prisoner from colony 51. ");
            lukija.nextLine();
            System.out.println("You: Come with me, we can escape together.");
            lukija.nextLine();
            System.out.println("Aino: I can't walk. I'm hurt. You have to heal me first.");
            lukija.nextLine();
            
        }
        else if(visited == 1){
            System.out.println("You: I haven't found a way to heal you yet.");
            lukija.nextLine();
            System.out.println("Aino: I've heard the guards talking about a sick bay. Maybe you should check it out. Would you kindly?");
            lukija.nextLine();
            
        }
        else{
            System.out.println(randomAnswer());
            lukija.nextLine();
            
        } 
            
    }
    
    
    public static void followDialogue(){
        AsciiPics.theGirl();
        if(visited == 0){
        System.out.println("You: Who are you?");
        lukija.nextLine();
        System.out.println("Woman prisoner: My name is Aino. I am a prisoner from colony 51. ");
        lukija.nextLine();
        System.out.println("You: Come with me, we can escape together.");
        lukija.nextLine();
        System.out.println("Aino: I can't walk. I'm hurt. You have to heal me first.");
        lukija.nextLine(); 
        System.out.println("You: I found a first aid kit. ");
        lukija.nextLine();
        System.out.println("Aino: Oh thank you so much! Now I can follow you to the escape pod.");
        lukija.nextLine();    
        }
        else{
         System.out.println("Aino: Thank you so much! Now I can follow you to the escape pod.");
         lukija.nextLine();
        }
    }
    
    
    public static String randomAnswer(){
        
      String dialogue = "";
      double d =  Math.random();
      d = d*10;
      int id = (int) Math.round(d);
       // System.out.println("id:"+id);
      
      if(id <= 2){
          return dialogue = "Aino: Please hurry. It hurts.";
      }    
      else if(id > 2 && id <= 4){
          return dialogue = "Aino: I sure hope we both fit into the pod.\n"+
                             "You: You know... I don't think that's gonna be a problem.";
      }
      else if(id > 4 && id <= 6){
          return dialogue ="Aino: I have a good feeling about this.";
      }
      else if(id > 6 && id <= 8){
          return dialogue ="Aino: War. War never changes.";
      }
      else if(id > 8 && id <= 9){
          return dialogue = "You: I could really use a breath mint.\n"+
                            "Aino: I just felt a sudden disturbance in the Force\n"+
                             "…as if a tiny voice shouted out and then went silent.";
      }
      else{
          return dialogue ="Aino: The right man in the wrong place can make all the difference in the world.";   
      }
      
      
        
    }
    
    
//    public static String readAnswer(){
//       String line = ""; 
//       boolean read = true;
//       
//        while(read){
//            line =lukija.nextLine();
//            if(line.equals("1") || line.equals("2")){
//               read = false; 
//               return line;
//            }
//       }
//        return line;
//   }
    
    
}
