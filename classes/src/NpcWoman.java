

/**
 *
 * @author tapir
 */
public class NpcWoman {
    
    static int x,y;
    static String neededItem;
    static String name;
    static boolean follow;
    static boolean found;
    
    
    public NpcWoman(){
    
    }
    
    public static void setNpc(int cx, int cy, String cneededItem, String cName){
        x = cx;
        y = cy;
        neededItem = cneededItem;
        name = cName;
        follow = false;
        found = false;
    }
    
    public static void talk(Bag bag){
        found = true;
        if(bag.findItem(neededItem)){
           follow = true; 
           bag.useItem( bag.getItem(neededItem));
        }
        Dialogue.talkToGirl(follow);
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public String neededItem(){
        return neededItem;
    }
    
    public String name(){
        return name;
    }
    
    public boolean follow(){
        return follow;
    }
    
    public static void setCoords(int nx, int ny){
        x = nx;
        y = ny;
    }
    
}
