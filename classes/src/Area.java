/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author lentonen_admin
 */
public class Area {
    
    int x,y;
    String name;
    
    public Area(int ax, int ay, String aname){
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
        return this.name;
    }
    
    public String toString(){
        return x+y+":"+name;
    }
}
