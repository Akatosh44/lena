package lena;

/**
 * 
 * @author alexis
 *
 */
public class Pixel {

	private int x;
	private int y;
	private int level;
	
	
	/**
	 * Constructor. If the level given is under 0, we put 0 and if it's over 255, we put 255
	 * @param x
	 * @param y
	 * @param level
	 */
	public Pixel(int x, int y, int level){
		this.x=x;
		this.y=y;
		if (level<0){
			this.level=0;
		}
		else if (level>255){
			this.level=255;
		}
		else {
			this.level=level;
		}
		
	}
	
	
	/**
	 * Copy constructor
	 * @param pixel
	 */
	public Pixel(Pixel pixel){
		this.x=pixel.getX();
		this.y=pixel.getY();
		this.level=pixel.getLevel();
	}
	
	/**
	 * X getter
	 * @return
	 */
	public int getX(){
		return this.x;
	}
	
	/**
	 * Y getter
	 * @return
	 */
	public int getY(){
		return this.y;
	}
	
	
	/**
	 * Level getter
	 * @return
	 */
	public int getLevel(){
		return this.level;
	}
	
	/**
	 * X setter
	 * @param x
	 */
	public void setX(int x){
		this.x=x;
	}
	
	
	/**
	 * Y setter
	 * @param y
	 */
	public void setY(int y){
		this.y=y;
	}
	
	/**
	 * Setter for the level. We check if it's under 0 or over 255
	 * @param level
	 */
	public void setLevel(int level){
		if (level<0){
			this.level=0;
		}
		else if (level>255){
			this.level=255;
		}
		else {
			this.level=level;
		}
	}
        
	/**
	 * toString ==> "x: x, y: y, level: level"
	 */
   
        @Override
	public String toString(){
		return "x: "+ x + ", y:" + y + ", level: "+level;
	}
        

        @Override
        public boolean equals(Object obj){
           if (obj instanceof Pixel){
               return ( this.x == ((Pixel)obj).getX() &&
                       this.y == ((Pixel)obj).getY() &&
                    this.level == ((Pixel)obj).getLevel());
           }
           else{
               return false;
           }
            
        }
        
        @Override
       public int hashCode(){
           return ((Object)this).hashCode();
       } 
}
