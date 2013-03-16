
/**
 *
 * @author lentonen_admin
 */
public class Item {
    
    int x,y;
    String name;    
    
    public Item(int ax, int ay, String aname){
       x=ax;
       y=ay;
       name= aname;
    }
    
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    
    public String name(){
        return name;
    }
        public String toString(){
        return x+y+":"+name;
    }
    
}
