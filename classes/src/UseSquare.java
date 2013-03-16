/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author lentonen_admin
 */
public class UseSquare {
    
    int x,y;
    String name, neededItem, command, target;
    
    Map kaikkiTavarat = new HashMap();
    Map kayttoKohteet = new HashMap();
    
    // GET, EXIT, OPEN commands
    // Target '&', '@', 
    public UseSquare(int cx, int cy, String cname, String cneededItem, String ccommand, String ctarget){
        x = cx;
        y = cy;
        name = cname;
        neededItem = cneededItem;
        command = ccommand;
        target = ctarget;
    }
    
    
    public String neededItem(){
        return neededItem;
    }
    
    public String getCommand(){
        return command; 
    }
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    
    public String name(){
        return this.name;
    }
    
    public String toString(){
        return x+y+":"+name;
    }
    
    public String command(String com, Grid lowerDeck, Grid upperDeck, Bag bag){
        String mes="";
        if(com.equals("GET")){
            mes = put(bag);
        }
        if(com.equals("OPEN")){
            open(lowerDeck, upperDeck,target,bag);
            mes = "A door opened somewhere.";
        }
        if(com.equals("EXIT")){
            // END GAME
            mes = "Escaped!";
            endGame();
        }
        return mes;
    }

    /**
     * add target item to Bag
     */
    public String put(Bag bag){
       String mes;
       mes = bag.addItem(target); 
       bag.useItem( bag.getItem(neededItem));
       return mes;
    }
   
    public void open(Grid lowerDeck, Grid upperDeck, String target, Bag bag){
        char c = target.charAt(0);
        Grid grid;
        // find remotelock from grid
        if(RemoteLocks.getFloor(target) == 0){
          grid = lowerDeck;  
        }
        else{
          grid = upperDeck;
        }
        
        grid.setSquare(RemoteLocks.getX(target), RemoteLocks.getY(target), '.');
        //remove from bag once used
        bag.useItem( bag.getItem(neededItem));
    }
    
    /**
     * exit from space ship
     */
    public void endGame(){
        // you saved the girl
        if(NpcWoman.follow){
           AsciiPics.happyEnd();
        }
        else{
           
           if(NpcWoman.found){
               AsciiPics.escapeAlone();
           }
           else{
              AsciiPics.escape(); 
           }
        }

        // finally end game
        EscapeFromIkarus.stop = true;
    }

   
    
}
