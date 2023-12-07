public class Ouch extends Entity implements Collectable{
        //Location of image file to be drawn for an Avoid
        public static final String OUCH_IMAGE_FILE = "assets/ouch.png";
        //Dimensions of the Avoid    
        public static final int OUCH_WIDTH = 50;
        public static final int OUCH_HEIGHT = 50;
        //Speed that the avoid moves each time the game scrolls
        private int visibilityDuration; 
        
        public Ouch(){
            this(0, 0);       
        }
        
        public Ouch(int x, int y){
            super(x, y, OUCH_WIDTH, OUCH_HEIGHT, OUCH_IMAGE_FILE);  
            this.visibilityDuration = 30;
        }

        public int getVisibilityDuration(){
            return this.visibilityDuration;
        }
        
        public void setVisibilityDuration(int newDuration){
            this.visibilityDuration = newDuration;
        }
    
        public int getScrollSpeed(){
            return 0;
        }
        
        //Move the avoid left by the scroll speed
        public void scroll(){
            setX(getX());
        }
        
        //Colliding with an Avoid does not affect the player's score
        public int getPoints(){
           return 0;
        }

       public int getDamage(){
        return 0;
       }
 
       public boolean isCollidingWith(Entity other){
        return false; // does not matter if it collides with other entities as this is just a visual effect
    }
        

}
