
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * item/käyttö
 * *************
 * sellin avain/sellin ovi
 * komentosillan avain/komentosillan ovi
 * ihmeellinen hakkerointilaite/komentosilta
 * juusto/hiirenpesä
 * 
 * 
 * 
 * @author tapir
 */
public class Bag {

    ArrayList<Item> stuff;
            
    public Bag(){  
      stuff = new ArrayList();     
    }
    
    public ArrayList getItems(){
        return stuff;
    }
    
    
    public boolean findItem(String name){
       boolean found = false;
       for(int i = 0; i< stuff.size();i++){
           if(stuff.get(i).name().contains(name)){
              found = true; 
              return found;
           }
       }
       return found;
    }
    
    
    public Item getItem(String name){
        Item item = new Item(0,0,"");
         for(int i = 0; i< stuff.size();i++){
           if(stuff.get(i).name().contains(name)){
              return stuff.get(i);
           }
       }
         return item;
    }
    
    
    public String addItem(String item){
        String  message;
        System.out.println("Tavara:"+item);
        stuff.add(new Item(0,0,item));
        
        if(item.trim().equals("Lockpick")){
         message= " Here's a lockpick. It might come in handy if you,\n the master of unlocking, take it with you.";
        }
        else if(item.equals("Hacking device")){
         message = " You lure the rats away from their nest with the cheese.\n You see a thingy in the nest and grab it.\n"
                 + " It's a hacking device! Do not let fear block your path.";   
        }
        else{
            message="Got: " + item;
        }
        return message;
    }
    
    /**
     * Käytetään itema ja poistetaan se repusta
     * @param item 
     */
    public void useItem(Item item){       
        stuff.remove(item);
    }
   
}
