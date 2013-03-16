

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * tavara/käyttö
 * *************
 * sellin avain/sellin ovi
 * komentosillan avain/komentosillan ovi
 * ihmeellinen hakkerointilaite/komentosilta
 * juusto/hiirenpesä
 * 
 * 
 * 
 * @author lentonen_admin
 */
public class Bag {

    ArrayList<Item> tavarat;
            
    public Bag(){  
      tavarat = new ArrayList();     
    }
    
    public ArrayList getItems(){
        return tavarat;
    }
    
    
    public boolean findItem(String name){
       boolean found = false;
       for(int i = 0; i< tavarat.size();i++){
           if(tavarat.get(i).name().contains(name)){
              found = true; 
              return found;
           }
       }
       return found;
    }
    
    
    public Item getItem(String name){
        Item tavara = new Item(0,0,"");
         for(int i = 0; i< tavarat.size();i++){
           if(tavarat.get(i).name().contains(name)){
              return tavarat.get(i);
           }
       }
         return tavara;
    }
    
    
    public String addItem(String tavara){
        String  message;
        System.out.println("Tavara:"+tavara);
        tavarat.add(new Item(0,0,tavara));
        
        if(tavara.trim().equals("Lockpick")){
         message= " Here's a lockpick. It might come in handy if you,\n the master of unlocking, take it with you.";
        }
        else if(tavara.equals("Hacking device")){
         message = " You lure the rats away from their nest with the cheese.\n You see a thingy in the nest and grab it.\n"
                 + " It's a hacking device! Do not let fear block your path.";   
        }
        else{
            message="Got: " + tavara;
        }
        return message;
    }
    
    /**
     * Käytetään tavaraa ja poistetaan se repusta
     * @param tavara 
     */
    public void useItem(Item tavara){       
        tavarat.remove(tavara);
    }
   
}
