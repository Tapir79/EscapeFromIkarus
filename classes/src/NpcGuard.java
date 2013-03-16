

/**
 * 5 steps down/up 
 * @author lentonen_admin
 */
public class NpcGuard {
    
    // starting x,y
    static int sx,sy;
    //
    static int x,y;
    static int steps = 0;
    static boolean continueGame = true;
    static boolean up = false;
    

    public NpcGuard(){
    
    }
    
    public static void setNpc(int cx, int cy){
       sx = cx;
       sy = cy;
       x = cx;
       y = cy;
      // xy = xys;
      
    }
    
    public static void reset(){
        x= sx;
        y = sy;
        up = false;
        steps = 0;
    }
    
    /**
     * 
     */
    
    public static boolean move(){
        
        if(x == Player.x && y == Player.y){
            AsciiPics.prisoner();
            continueGame = false;
            EscapeFromIkarus.stop = true;
        }
        
        if(steps == 10){
            steps = 0;
            if(up){
                up = false;
            }
            else{
                up = true;
            }
        }
        else{
            if(up){
                if(steps%2==0){
                  x = x-1;  
                }
                
            }
            else{
                if(steps%2==0){
                  x = x+1;
                }
            }
        }
        
        steps ++;
        return continueGame;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }

    public static void setCoords(int nx, int ny){
        x = nx;
        y = ny;
    }
    
   
}


