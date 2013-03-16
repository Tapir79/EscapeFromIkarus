
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author tapir
 * 
 * game state 
 * map char[][]
 * player x,y
 * 
 */
public class UserInput{
    
    
    static String message="You are Paul Denton, a prisoner in an alien space ship. \n "
            + "Escape! Don't let the guard catch you.";
    int floor;
    Grid upperDeck; 
    Grid lowerDeck;
    char[][] currentMap;
    Grid currentGrid;
    int x,y;
    Bag reppu;
    
    
    
   public UserInput(){
      
   }
   
   public void setDecks(Grid lowerdeck, Grid upperdeck){
       //System.out.println("UserInput creation");
       upperDeck = upperdeck;
       lowerDeck = lowerdeck;
       floor = 0;
       setCurrentMap();
       reppu = new Bag();
   }
    
   public ArrayList<Item> getItems(){
       return reppu.getItems();
   }
  
   /**
    * message
    * @return 
    */
    public String getMessage(){
        return message;
    }
    
  
    public char[][] getCurrentMap(){
        return currentGrid.charGrid();
        
    }

    public int floor(){
        return floor;
    }
    
    
    // change 
    // ADSW liike
        // check if wall #
        // if not wall check if door - || |, if door check if key in bag
        // if lift L change floor and map
        // if A following A saa P:n koordinaatit
        // move
        // uusi x,y gets image P
   
    /**
     * saa peruskartan char[][]
     * kopioi uuteen karttaan joka palautetaan ja piirretään
     * peruskarttaa päivitetään jos muutokset ovat pysyviä, esim. ovi auki
     * @param jono 
     */
    public void gameLogic(char c){
       
        if(c == 'a' || c == 'd' || c == 'w' || c == 's'){
            
            int npcx = Player.x;
            int npcy = Player.y;
            
        if(c == 'a'){    
            x = Player.x;
            y = Player.y-1;
            movePlayer(x,y);
        }

        if(c == 'd'){
            x = Player.x;
            y = Player.y+1;
            movePlayer(x,y);
        }

        if(c == 'w'){
            x = Player.x-1;
            y = Player.y; 
            movePlayer(x,y);
        }

        if(c == 's'){
            x = Player.x+1;
            y = Player.y;
            movePlayer(x,y);
        }
        
                    
          
            
    }

        // U = use
        // check if 'O'
        // get ArrayList useSquares
        // get current Square with player x,y
        // get useSquare.neededItem
        // check bag, if contains neededItem
        // remove item from bag
        // do useSquare what happens
        if(c == 'u'){
            if(currentGrid.charGrid()[Player.x][Player.y] == 'O'){ 
                message = useSquareUse(Player.x, Player.y);   
            }
            else{
                message = "I would need a monkey wrench to use that.";
            }
         } 



        // T = talk
        // jos current x,y ylös,alas tai sivulle == A
        // hae NPC.needed item
        // tarkista onko tavara Bag:ssä
        // hae NPC.dialogi(on tavara)
        if(c == 't'){
             
             if(NpcWoman.x == Player.x && NpcWoman.y == Player.y ||
                NpcWoman.x == Player.x && NpcWoman.y == Player.y-1 ||
                NpcWoman.x == Player.x && NpcWoman.y == Player.y+1 ||
                NpcWoman.x == Player.x-1 && NpcWoman.y == Player.y ||
                NpcWoman.x == Player.x+1 && NpcWoman.y == Player.y){
                 if(floor == 0){
                     NpcWoman.talk(reppu); 
                 }
                 else{
                     message = "I'd love to stay and chat but, ...uh... I've got to go...";
                 }
                
             }
             else{
                 message = "Okay talking, blah blah blah.";
             }
            
        }

        // G = get
        // check if item *
        // get ArrayList items, get item by x,y
        // change deck.setSquare(x, y, '.')
        // insert item to bag
        if(c == 'g'){
           if(currentGrid.charGrid()[Player.x][Player.y] == '*'){
              message = reppu.addItem(getItem(Player.x,Player.y).name());
               currentGrid.setSquare(Player.x, Player.y, '.');   
           }
           else{
               message = "Not picking that up.";
           }
        }
        
        
    }
    
    public void setCurrentMap(){
         if(floor==0){
           currentMap = lowerDeck.charGrid();  
           currentGrid = lowerDeck;
           NpcGuard.setCoords(0,0);
       }
         else{
           currentMap = upperDeck.charGrid();  
           currentGrid = upperDeck;
           NpcGuard.reset();
         }
    }
    
    
    
    public void movePlayer(int mx, int my){
          
          if(currentGrid.charGrid()[mx][my] == '#'){
          }
          else if(currentGrid.charGrid()[mx][my] == '@'){
              message = "This door is opened elsewhere";
          } 
          else if(currentGrid.charGrid()[mx][my] == '&'){
              message = "This door is opened elsewhere";
          }
          
          // CHECK KEY
          else if (currentGrid.charGrid()[mx][my] == '|' || currentGrid.charGrid()[mx][my] == '-'){
                
                if(reppu.findItem("Lockpick")){
                    currentGrid.charGrid()[mx][my] = '.';
                    Item poistettava = reppu.getItem("Lockpick");
                    reppu.useItem(poistettava);
                    message = "You picked a lock, you master of unlocking.";
                }
                else{
                    message = "The door is locked, but as always in game situations like these\n"
                            + "there's probably a useful item conveniently lying around.";
                }
          }
            //hissi
            
            
          else{
                    if(currentGrid.charGrid()[mx][my] == 'O'){
                        message = useSquareMessage(mx,my);
                    }
                    if(currentGrid.charGrid()[mx][my] == '*'){
                        message = itemMessage(mx,my);
                    }
                    if(currentGrid.charGrid()[mx][my] == '.'){
                        message = areaMessage(mx,my); 
                    }
                    if(currentGrid.charGrid()[mx][my] == 'L'){
                        if(floor == 0){
                            floor = 1;
                            setCurrentMap();
                        }
                        else{
                            floor = 0;
                            setCurrentMap();
                        }
                         message = areaMessage(mx,my); 
                    }
                if(NpcWoman.follow){
 
                   NpcWoman.setCoords(Player.x, Player.y);
                  
                }    
                  
                Player.y = my;
                Player.x = mx;
                
                if(Player.x == NpcGuard.x && Player.y == NpcGuard.y){
                    AsciiPics.prisoner();
                    
                    EscapeFromIkarus.stop = true;
                }
                
            
            
            }
    }
    
    /**
     * get message from square mx, my
     * @param mx
     * @param my
     * @return 
     */
    public String useSquareMessage(int mx, int my){
        UseSquare sq;
        String mes = "";
        for(int i = 0; i<currentGrid.uses().size(); i++){
                     sq = (UseSquare)currentGrid.uses().get(i);
                            if((sq.getX() == mx) && (sq.getY() == my)){
                                 mes = sq.name();       
                            }
        }
        return mes;
    }
    
     public String useSquareUse(int mx, int my){
        UseSquare sq;
        String mes = "You can't do that--at least not now!";
        for(int i = 0; i<currentGrid.uses().size(); i++){
                     sq = (UseSquare)currentGrid.uses().get(i);
                            if((sq.getX() == mx) && (sq.getY() == my)){
                                 if(reppu.findItem(sq.neededItem().trim()) || sq.neededItem().contains("null")){
                                     
                                     //check floor

                                    mes = sq.command(sq.getCommand().trim(), lowerDeck, upperDeck, reppu); 
                              }        
                            }
        }
        return mes;
    }
    
    /**
     * get message from square mx, my
     * @param mx
     * @param my
     * @return 
     */
    public String areaMessage(int mx, int my){
        Area sq;
        String mes = "";
        for(int i = 0; i<currentGrid.areas().size(); i++){
                     sq = (Area)currentGrid.areas().get(i);
                            if((sq.getX() == mx) && (sq.getY() == my)){
                                 mes = sq.name();       
                            }                    
        }
        return mes;
    }
    
    /**
     * get message from square mx, my
     * @param mx
     * @param my
     * @return 
     */
     public String itemMessage(int mx, int my){
        Item sq = new Item(0,0,"null");
        String mes = "";
        for(int i = 0; i<currentGrid.items().size(); i++){
                     sq = (Item)currentGrid.items().get(i);
                            if((sq.getX() == mx) && (sq.getY() == my)){
                                mes = sq.name();      
                            }                    
        }
        return mes;
    }
    
     
     /**
      * get item from square mx, my
      * @param mx
      * @param my
      * @return 
      */
    public Item getItem(int mx, int my){
        Item sq = new Item(0,0,"null");
        for(int i = 0; i<currentGrid.items().size(); i++){
                sq = (Item)currentGrid.items().get(i);
                            if((sq.getX() == mx) && (sq.getY() == my)){
                                 return sq;      
                            }                    
        }
        return sq;
    }
    
    
}
