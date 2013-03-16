import java.util.HashMap;

/**
 *
 * @author tapir
 */
public class RemoteLocks {
    
    public static HashMap<String,String> remoteLocks = new HashMap<String, String>();
     
    public RemoteLocks(){
        
    }
   
    public static void addLock(String name, String xyf){
        System.out.println("[Name:xy]"+name+","+xyf);
       remoteLocks.put(name, xyf); 
    }
    
    public static int getX(String name){
       String xyf = remoteLocks.get(name); 
        System.out.println("xy:"+xyf);
       String[] point = xyf.split(":");
       int x = Integer.parseInt(point[0]);
       return x;
    }
    
    public static int getY(String name){
       String xyf = remoteLocks.get(name); 
       String[] point = xyf.split(":");
       int y = Integer.parseInt(point[1]);
       return y;
    }
    
    public static int getFloor(String name){
       String xyf = remoteLocks.get(name); 
       String[] point = xyf.split(":");
       int f = Integer.parseInt(point[2]);
       return f;
    }
}
