

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Builds game grids
 * Builds area,item,use square lists
 * 
 * 
 * @author lentonen_admin
 */
public final class Grid {
  
    private char[][] grid;
    private ArrayList<Area> areas;
    private ArrayList<Item> items;
    private ArrayList<UseSquare> uses;
    //private NpcWoman girl;
//    private RemoteLocks remotelocks = new RemoteLocks();
   
    HashMap<String, String> hashitems = new HashMap<String, String>();
    HashMap<String, String> hashuses = new HashMap<String, String>();
    
    public Grid(){
        
    }
    
    

    public Grid(char[][] ctable, HashMap <String,String> chashitems, HashMap<String,String> chashuses){
       this.grid = ctable; 
       this.hashitems = chashitems;
       this.hashuses = chashuses;
       createGrid();
       
//        printCharShip(this.grid);
////        printArea(areas);
//        printItems(items);
//        printUses(uses);
    }
    
    public void initGrid(char[][] ctable, HashMap <String,String> chashitems, HashMap<String,String> chashuses){
       this.grid = ctable; 
       this.hashitems = chashitems;
       this.hashuses = chashuses;
       createGrid(); 
    }
    
    public ArrayList areas(){
        return this.areas;
    }
    
    public ArrayList items(){
        return this.items;
    }
    
    public ArrayList uses(){
        return this.uses;
    }
    
    public char[][] charGrid(){
        return this.grid;
    }

//    public RemoteLocks remoteLocks(){
//        return this.remotelocks;
//    }
    
    public void setSquare(int x, int y, char c){
        this.grid[x][y]=c;
    }
    
    public void createGrid(){
        
        int xlength = grid.length;
        int ylength = grid[0].length;
        int itemSum = 0;
        int useSum  = 0;
        
        areas = new ArrayList<Area>();
        items = new ArrayList<Item>();
        uses = new ArrayList<UseSquare>();        
        
//        System.out.println("xlength:"+xlength);
//        System.out.println("ylength:"+ylength);
//       
        //printCharShip(this.grid, this.areas);
        //System.out.println(this.grid[0][0]);
        
        for(int x=0;x<xlength;x++){
            for(int y=0;y<ylength;y++){
                
                if(grid[x][y] == '@'){
                    RemoteLocks.addLock("@",x+":"+y+":0");
                }
                if(grid[x][y] == '&'){
                    RemoteLocks.addLock("&",x+":"+y+":0");
                }
                //int x, int y, String area, String name, char type, Object item
                //item array
                if(grid[x][y] == '*'){
                    
                    String sItem = this.hashitems.get(itemSum+"");
                    this.items.add(new Item(x,y,sItem));
                    itemSum++;
                }
                
                // use square array
                if(grid[x][y] == 'O'){
                    String sUse = this.hashuses.get(useSum+"");
                    System.out.println(sUse);
                    String[] sUses = sUse.split(":");
                    System.out.println(sUses[0]+","+sUses[1]+","+sUses[2]+","+sUses[3]);
                    this.uses.add(new UseSquare(x,y,sUses[0],sUses[1],sUses[2],sUses[3]));
                    
                    useSum++;
                }
                
                // in the future do a dynamic way to set more npc:s
                if(grid[x][y] == 'A'){
                   NpcWoman.setNpc(x,y,"First aid kit","Woman prisoner"); 
                   grid[x][y]='.';
                }
                if(grid[x][y] == 'G'){
                   NpcGuard.setNpc(x,y); 
                   grid[x][y]='.'; 
                }
                
                
                //System.out.println("grid[x][y]:"+grid[x][y]);
                // area array
                if(grid[x][y] == 'L'){
                   areas.add(new Area(x,y,"Lift"));
                }
                if(grid[x][y] == '1'){
                   areas.add(new Area(x,y,"Sick bay"));
                   grid[x][y]='.';
                }
                if(grid[x][y] == '2'){
                   areas.add(new Area(x,y,"Conference room"));
                   grid[x][y]='.';
                }
                if(grid[x][y] == '3'){
                   areas.add(new Area(x,y,"Living quarters"));
                   grid[x][y]='.';
                }
                if(grid[x][y] == '4'){
                   areas.add(new Area(x,y,"Escape pod area"));
                   grid[x][y]='.';
                }
                if(grid[x][y] == '5'){
                   areas.add(new Area(x,y,"Cargo hull"));
                   grid[x][y]='.';
                }
                if(grid[x][y] == '6'){
                   areas.add(new Area(x,y,"Holding cell"));
                   grid[x][y]='.';
                }
                if(grid[x][y] == '7'){
                   areas.add(new Area(x,y,"Command central"));
                   grid[x][y]='.';
                }
                if(grid[x][y] == '8'){
                   areas.add(new Area(x,y,"Common area"));
                   grid[x][y]='.';
                }
                if(grid[x][y] == '9'){
                   areas.add(new Area(x,y,"Living quarters"));
                   grid[x][y]='.';
                }
                if(grid[x][y] == 'y'){
                   areas.add(new Area(x,y,"Kitchen"));
                   grid[x][y]='.';
                }
                if(grid[x][y] == 'g'){
                   areas.add(new Area(x,y,"Security center"));
                   grid[x][y]='.';
                }
                
               
               
            }
        }
        
//        for(int i=0;i<uses.size();i++){
//            System.out.print("Name:"+uses.get(i).name);
//            System.out.print("Command:"+uses.get(i).command);
//            System.out.print("Needed item:"+uses.get(i).neededItem);
//            System.out.println("");
//            
//        }
        
//       
        
    }
    
     public static void printCharShip(char[][] grid){
         for(int x=0;x<grid.length;x++){
            for(int y=0;y<grid[0].length;y++){
                

                System.out.print(grid[x][y]);                
                //System.out.print("["+i+j+"]"+lowerdeck[i][j]);
            }
            System.out.println("");
        }
    }
     
     public static void printArray(ArrayList list){
         for(Object o : list){
             System.out.println(o.toString());
         }
     }
     
     public static void printUses(ArrayList<UseSquare> list){
          for(int i =0 ; i< list.size(); i++){
                    UseSquare a = list.get(i);
                   System.out.println(a.toString()); 
                }   
     }
     
     
     public static void printArea(ArrayList<Area> list){
         for(int i =0 ; i< list.size(); i++){
                    Area a = list.get(i);
                   System.out.println(a.toString()); 
                }         
     }

    public static void printItems(ArrayList<Item> list){
        for(int i =0 ; i< list.size(); i++){
                Item a = list.get(i);
               System.out.println(a.toString()); 
            }         
        }
     
}
