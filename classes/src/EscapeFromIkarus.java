
/*
 holding cell 1,
living quarters get key 2
(optional)security center use security card -> open holding cell 2 door
command central use hacking device -> open escape pod door
kitchen get cheese,
living quarters get key 8
(optional)security center use security card -> open holding cell 8 door
command central use hacking device -> open escape pod door

	charimage, areaname
i = null	
0 = floor	
1 = floor, sick bay
2 = floor, conference room
3 = floor, living quarters 	
4 = escape pod area
5 = cargo
6 = holding cell
A = injured girl
| = door
- = door
7 = floor, command central
8 = floor, common area
9 = floor, living quarters 	
y = kitchen
g = security center
* = item
O = use item
L = lift

 */


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lentonen_admin
 */
public class EscapeFromIkarus{

    //
    
    private static Scanner lukija = new Scanner(System.in);
    private static String ttyConfig;
    static boolean stop = false;
    static boolean typed = false;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
    Grid upperDeck = new Grid();
    Grid lowerDeck = new Grid();
    char[][] upperDeckChar, lowerDeckChar;
    int floor=0;
    Player player = new Player(9,38);
    //Npc girl = new Npc();
    UserInput userinput=new UserInput();
    ArrayList<Item> reppu;
    String line = "";
    init(userinput,upperDeck,lowerDeck);
    
    
    // game intro
    AsciiPics.title();
    System.out.println("Press any key to continue");
    line = lukija.nextLine();
    AsciiPics.startInfo(userinput);
    System.out.println("Press any key to continue");
    line = lukija.nextLine();
    // get OS
    String os = System.getProperty("os.name");
       //
    //System.out.println(os);
    
    if(os.toUpperCase().contains("WINDOWS")){
          // X11Graphics Environment
        Robot robot = null;
            try {
                robot = new Robot();
            } catch (AWTException ex) {
                Logger.getLogger(EscapeFromIkarus.class.getName()).log(Level.SEVERE, null, ex);
            }
            //
            while( stop == false ){
                
                printCharShip(userinput);
                printInfo(userinput);
                
                line = lukija.nextLine();
                if(line.equalsIgnoreCase("q")){
                    System.out.println("I wouldn't leave if I were you. Work is much worse.");
                    stop = true;
                }
                robot.delay(300);
                robot.keyPress(KeyEvent.VK_ENTER);
                typed(line, userinput);
                
            }   
    } 
    
    // OS is linux
    else{
   
                  // ***
         // linux raw console
         try {
                    RawConsole.setTerminalToCBreak();

                    while ( stop == false) {
                            
			    printCharShip(userinput);
                            printInfo(userinput);
                            //System.out.print("Give your command(\"Esc\" to quit): ");
                        
                                    try {
                                    Thread.sleep(300);
                                    } catch (InterruptedException e1) {
                                    e1.printStackTrace();
                                    }
                                    if ( System.in.available() != 0 ) {
                                    int c = System.in.read();
                                    if ( c == 0x1B ) {
                                        System.out.println("I wouldn't leave if I were you. Work is much worse.");    
                                        stop = true;
                                            
                                    }
                                    // a or A
                                    else if( c == 0x61){
                                        line = "A";
                                        typed(line, userinput);
                                    }
                                    // d or D
                                    else if(c == 0x64){
                                        line = "D";
                                        typed(line, userinput);
                                    }
                                    else if(c == 0x77){
                                        line = "W";
                                        typed(line, userinput); 
                                    }
                                    else if(c == 0x73){
                                        line = "S";
                                        typed(line, userinput);
                                    }
                                    else if(c == 0x75){
                                       line = "U";
                                       typed(line, userinput); 
                                    }
                                    else if(c == 0x74){
                                       line = "T";
                                       typed(line, userinput); 
                                    }
                                    else if(c == 0x67){
                                       line = "G";
                                       typed(line, userinput); 
                                    }
                                    else{
                                       stop = false;
                                    }              
                            }
                    } // end while
                }
                catch (IOException e) {
                        System.err.println("IOException");
                }
                catch (InterruptedException e) {
                        System.err.println("InterruptedException");
                }
                finally {
                    try {
                        RawConsole.stty( ttyConfig.trim() );
                     }
                     catch (Exception e) {
                         System.err.println("Exception restoring tty config. Please close your terminal");
                     }
                }
            }
                
    }
    
    
    public static void init(UserInput userinput, Grid upperDeck, Grid lowerDeck){
            
    HashMap<String, String> lowerdeckItems = new HashMap<String, String>();
    HashMap<String, String> upperdeckItems = new HashMap<String, String>(); 
    HashMap<String,String> lowerdeckUseSquares = new HashMap<String, String>();
    HashMap<String,String> upperdeckUseSquares = new HashMap<String, String>();

    // game data    
    lowerdeckItems.put("0","First aid kit"); 
    lowerdeckItems.put("1","Lockpick");
    lowerdeckItems.put("2","Security card");

    upperdeckItems.put("0","Cheese");
    upperdeckItems.put("1","Lockpick");

    lowerdeckUseSquares.put("0","Escape pod:null:EXIT:EXIT");
    lowerdeckUseSquares.put("1","Rat nest:Cheese:GET:Hacking device");
    //you get a new item to player bag called hacking device, used to command centre
    upperdeckUseSquares.put("0","Central computer:Hacking device:OPEN:@");
    upperdeckUseSquares.put("1","Security computer:Security card:OPEN:&");

    
                char[][] lowerdeck = new char[][]{
		new char[] {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},    
                new char[] {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},    
                new char[] {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},    
                new char[] {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},    
                new char[] {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                new char[] {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                new char[] {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},    
                new char[] {' ',' ',' ',' ',' ',' ','#','#','#','#','#','#','#','#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#','#','#',' ',' ',' ','#',' ','#',' ',' ',' ','#','#','#',' ',' ',' '},
                new char[] {' ',' ',' ','#','#','#','#','#','1','1','1','*','1','#',' ','#','#','#',' ','#','#','#',' ',' ',' ',' ','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#',' ',' ',' '},
                new char[] {' ',' ','#','#','2','2','2','#','#','.','#','#','#','#',' ','#','O','#',' ','#','#','#',' ',' ','#','5','5','5','5','5','5','5','5','5','|','6','6','6','6','6','#','#',' ',' ',' '},
                new char[] {' ','#','#','2','2','2','2','2','2','2','#','#','#','#','#','#','@','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','.','#','6','*','6','6','#','#','#','#','#','#'},
                new char[] {'#','#','2','2','2','2','2','2','2','2','.','.','.','.','4','4','4','4','4','4','4','4','4','4','4','4','4','4','4','4','4','4','4','4','#','#','#','#','#','#','#','#','#','#','#'}, 
                new char[] {' ','#','#','2','2','2','2','2','2','2','#','#','L','L','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','-','#','6','6','A','6','#','#','#','#','#','#'},
                new char[] {' ',' ','#','#','2','2','2','#','#','.','#','#','#','#',' ','#','#','#',' ','#','#','#',' ',' ','#','5','5','O','5','5','5','5','5','5','&','6','6','6','6','6','#','#',' ',' ',' '},
                new char[] {' ',' ',' ','#','#','#','#','#','3','3','3','3','*','#',' ','#','#','#',' ','#','#','#',' ',' ',' ','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#',' ',' ',' '},        
                new char[] {' ',' ',' ',' ',' ',' ','#','#','#','#','#','#','#','#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#','#','#',' ',' ',' ','#',' ','#',' ',' ',' ','#','#','#',' ',' ',' '},
		new char[] {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},    
                  
               };

               //printCharShip(lowerdeck);

                char[][] upperdeck = new char[][]{
                new char[] {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},        
		new char[] {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},    
                new char[] {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},    
                new char[] {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                new char[] {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                new char[] {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                new char[] {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                new char[] {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},    
                new char[] {' ',' ',' ',' ',' ','#','#','#',' ',' ',' ',' ','#','#',' ','#','#',' ','#','#',' ','#','#',' ','#','#',' ','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#',' ',' ',' '},
                new char[] {' ',' ',' ','#','#','G','#','#','#','#','#','#','#','#',' ','#','#',' ','#','#',' ','#','#',' ','#','#',' ','#','#',' ',' ',' ','#','y','.','y','y','y','y','y','#','#',' ',' ',' '},
                new char[] {' ',' ','#','7','#','7','#','7','7','7','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','.','#','y','y','*','y','#','#','#','#','#','#'},
                new char[] {'#','#','O','7','7','7','7','7','7','7','.','.','.','.','.','.','8','8','8','8','8','8','8','8','8','8','8','8','8','8','8','8','8','8','#','#','#','#','#','#','#','#','#','#','#'},
                new char[] {'#','#','7','7','7','7','7','7','7','7','#','#','L','L','#','#','8','8','8','8','8','8','8','8','8','8','8','8','8','8','8','8','8','8','.','g','g','g','g','#','#','#','#','#','#'},
                new char[] {' ',' ','#','7','#','7','#','7','7','7','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','g','g','g','#','#','#','#','#','#'},
                new char[] {' ',' ',' ','#','#','7','#','#','#','#','#','#','#','#',' ','#','#',' ','#','#',' ','#','#',' ','#','#',' ','#','#',' ',' ',' ','#','*','g','g','g','g','g','O','#','#',' ',' ',' '},
                new char[] {' ',' ',' ',' ',' ','#','#','#',' ',' ',' ',' ','#','#',' ','#','#',' ','#','#',' ','#','#',' ','#','#',' ','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#',' ',' ',' '},
		new char[] {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '}       
              };

                 //  printCharShip(upperdeck);
                  upperDeck.initGrid(upperdeck,upperdeckItems,upperdeckUseSquares); 
                  lowerDeck.initGrid(lowerdeck,lowerdeckItems,lowerdeckUseSquares); 
                  
                  userinput.setDecks(lowerDeck, upperDeck);
                 // printCharShip(userinput.getCurrentMap());
                  
                  
    }

    /**
     * 
     * @param table 
     */
    public static void printCharShip(UserInput userinput){
         
        char[][] table = userinput.getCurrentMap();
        
        if(userinput.floor() == 0){ 
            for(int i=0;i<table.length;i++){
            for(int j=0;j<table[0].length;j++){
               if(Player.x == i && Player.y == j ){
                    System.out.print("P");
                } 
               else if((NpcWoman.x == i) && (NpcWoman.y == j)){
                    System.out.print("A");
                   }  
               else{
                System.out.print(table[i][j]);                
               }
                //System.out.print("["+i+j+"]"+lowerdeck[i][j]);
            }
            System.out.println("");
        }
        }
        else{
            //activate guard
            if(NpcGuard.move()){
            
                for(int i=0;i<table.length;i++){
                    for(int j=0;j<table[0].length;j++){
                       if(Player.x == i && Player.y == j ){
                            System.out.print("P");
                        } 
                       else if((NpcGuard.x == i) && (NpcGuard.y == j)){
                            System.out.print("G");
                           } 
                       else{
                        System.out.print(table[i][j]);                
                       }
                        //System.out.print("["+i+j+"]"+lowerdeck[i][j]);
                    }
                System.out.println("");
                }
            }
        }
 
    }
    

    
    public static void printInfo(UserInput userinput){
       if(stop == false){ 
        // init bagstrings
        String[] itemsInBag = new String[5];
        for(int z = 0;z < 5 ; z++ ){
            itemsInBag[z] = "";
        }        
        if(userinput.getItems().size()>0){
        for(int i = 0; i< userinput.getItems().size();i++){
             itemsInBag[i] ="["+i+"]: "+userinput.getItems().get(i).name(); 
        }
        }
        
        System.out.println(" _____________________________________________________");
        System.out.println("|"+userinput.getMessage());
        System.out.println("|__________________________|____BAG___________________");
        System.out.println("| w = up    | g = get item |"+itemsInBag[0]);
        System.out.println("| s = down  | u = use item |"+itemsInBag[1]);
        System.out.println("| a = left  | t = talk     |"+itemsInBag[2]);
        System.out.println("| d = right |              |"+itemsInBag[3]);
        System.out.print("Command \"q\"(uit) (+Enter): ");
        System.out.println("");
        
       } 
        
        
    }

   

    
    public static void typed(String jono, UserInput userinput) {
           
              
                        if( jono.trim().toUpperCase().equals("A")){
                           userinput.gameLogic('a');
                        }
                        if( jono.trim().toUpperCase().equals("D")){
                            userinput.gameLogic('d');
                        }
                        if( jono.trim().toUpperCase().equals("W")){     
                            userinput.gameLogic('w');
                        }
                        if( jono.trim().toUpperCase().equals("S")){
                            userinput.gameLogic('s');
                        }
                        if(jono.trim().toUpperCase().equals("U")){
                            userinput.gameLogic('u');
                        }
                        if(jono.trim().toUpperCase().equals("T")){
                            userinput.gameLogic('t');
                        }
                        if(jono.trim().toUpperCase().equals("G")){
                            userinput.gameLogic('g');
                        }
    }


    
}