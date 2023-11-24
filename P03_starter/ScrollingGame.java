import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

//The basic ScrollingGame, featuring Avoids, Gets, and SpecialGets
//Players must reach a score threshold to win
//If player runs out of HP (via too many Avoid collisions) they lose
public class ScrollingGame extends GameEngine {
    
 
    
    //Starting Player coordinates
    protected static final int STARTING_PLAYER_X = 0;
    protected static final int STARTING_PLAYER_Y = 100;
    
    //Score needed to win the game
    protected static final int SCORE_TO_WIN = 300;
    
    //Maximum that the game speed can be increased to
    //(a percentage, ex: a value of 300 = 300% speed, or 3x regular speed)
    protected static final int MAX_GAME_SPEED = 300;
    //Interval that the speed changes when pressing speed up/down keys
    protected static final int SPEED_CHANGE = 20;    
    
    protected static final String INTRO_SPLASH_FILE = "assets/splash.gif";        
    //Key pressed to advance past the splash screen
    public static final int ADVANCE_SPLASH_KEY = KeyEvent.VK_ENTER;
    
    //Interval that Entities get spawned in the game window
    //ie: once every how many ticks does the game attempt to spawn new Entities
    protected static final int SPAWN_INTERVAL = 25; 
    
    
    //A Random object for all your random number generation needs!
    protected static final Random rand = new Random();
    
            
    
    //Player's current score
    protected int score;
    
    //Stores a reference to game's Player object for quick reference
    //(This Player will also be in the displayList)
    protected Player player;
    
    
    
    
    
    public ScrollingGame(){
        super();
    }
    
    public ScrollingGame(int gameWidth, int gameHeight){
        super(gameWidth, gameHeight);
    }
    
    
    //Performs all of the initialization operations that need to be done before the game starts
    protected void pregame(){
        this.setBackgroundColor(Color.BLACK);
        player = new Player(STARTING_PLAYER_X, STARTING_PLAYER_Y);
        displayList.add(player); 
        score = 0;
        setSplashImage(INTRO_SPLASH_FILE);
    }
    
    
    //Called on each game tick
    protected void updateGame(){
        //scroll all scrollable Entities on the game board
        scrollEntities();  
        //Spawn new entities only at a certain interval
        if (super.getTicksElapsed() % SPAWN_INTERVAL == 0){            
            spawnEntities();
        }
        for (int i = 0; i < displayList.size(); i++){ //iterates through displaylist to check if collisions have happened
            Entity currentEntity = displayList.get(i);
            if (!(currentEntity instanceof Player)){
                handlePlayerCollision((Collectable)currentEntity);
                  
            } 
        }
        determineIfGameIsOver();

        //Updates title text on the top of the window   
        setTitleText("HP: " + player.getHP() + ", Score: " + score);   
    }
    
    
    //Scroll all scrollable entities per their respective scroll speeds
    protected void scrollEntities(){
        for (int i = 0; i < displayList.size(); i++){
            Entity entity = displayList.get(i);
            if (entity instanceof Scrollable) {
                Scrollable scrollableEntity = (Scrollable) entity;
                scrollableEntity.scroll();
            }
        }
    }
    
    
    //Handles "garbage collection" of the displayList
    //Removes entities from the displayList that have scrolled offscreen
    //(i.e. will no longer need to be drawn in the game window).
    protected void gcOutOfWindowEntities(){
        for (int i = 0; i < displayList.size(); i++) {
            Entity entity = displayList.get(i);
            if (entity instanceof Scrollable) {
                if (entity.getX() + entity.getWidth() < 0 || entity.getX() > getWindowWidth()) { //out of window
                    displayList.remove(displayList.get(i));
                    i--;
                }
            }
        } 


       
    }
    //Called whenever it has been determined that the Player collided with a collectable
    protected void handlePlayerCollision(Collectable collidedWith) {
        Entity entity = (Entity) collidedWith; 
        if (player.isCollidingWith(entity)) {
            if (entity instanceof SpecialGet) {
                int points = ((SpecialGet) entity).getPoints();
                score += points;
                player.modifyHP(((SpecialGet) entity).increaseHP()); 
            } else if (entity instanceof Get) {
                int points = ((Get) entity).getPoints();
                score += points;
            } else if (entity instanceof Avoid) {
                player.modifyHP(((Avoid) entity).getDamage()); //descreases hp
            }
            
            displayList.remove(entity); // Removes entity from the displayList when collision w player occurs
    
        }
    }

    
    

    
    
    
    
    //Spawn new Entities on the right edge of the game window
    protected void spawnEntities(){
        int randomY = rand.nextInt(getWindowHeight() - Avoid.AVOID_HEIGHT);
        if (rand.nextInt(100) < 10) { 
            SpecialGet specialGet = new SpecialGet(getWindowWidth(), randomY);
            displayList.add(specialGet);
        } else if (rand.nextInt(100) < 60) { 
            Avoid avoid = new Avoid(getWindowWidth(), randomY);
            displayList.add(avoid);
        } else {
            Get get = new Get(getWindowWidth(), randomY);
            displayList.add(get);
        }
            
    }
    
    
    //Called once the game is over, performs any end-of-game operations
    protected void postgame(){
        if (player.getHP() <= 0){
            setTitleText("GAME OVER - You Lose!");
        } else {
            setTitleText("GAME OVER - You Won!");
        }
    }
   
    
    //Determines if the game is over or not
    //Game can be over due to either a win or lose state
    protected boolean determineIfGameIsOver(){
        if (player.getHP() <= 0){
            return true;
        }
        if (score >= SCORE_TO_WIN){
            return true;
        }
        return false;
       
    }
    
    
    
    //Reacts to a single key press on the keyboard
    protected void reactToKey(int key){
        if (key == UP_KEY && player.getY() > 0){
            player.setY(player.getY()+(-1*player.getMovementSpeed()));
        }
        if(key == DOWN_KEY && player.getY() < (getWindowHeight() - Player.PLAYER_HEIGHT)){
            player.setY(player.getY()+(player.getMovementSpeed()));
        }
        if(key == RIGHT_KEY && player.getX() < getWindowWidth() - Player.PLAYER_WIDTH){
            player.setX(player.getX()+(player.getMovementSpeed()));
        }
        if(key == LEFT_KEY && player.getX() > 0){
            player.setX(player.getX()+(-1*player.getMovementSpeed()));
        }
        if (key == KEY_PAUSE_GAME){ 
            isPaused = !isPaused;
        }
        
        setDebugText("Key Pressed!: " + KeyEvent.getKeyText(key) + ",  DisplayList size: " + displayList.size());
        
        //if a splash screen is active, only react to the "advance splash" key... nothing else!
        if (getSplashImage() != null){
            if (key == ADVANCE_SPLASH_KEY)
                super.setSplashImage(null);
            
            return;
        } 
        
    }    
    
    
    //Handles reacting to a single mouse click in the game window
    //Won't be used in Milestone #2... you could use it in Creative Game though!
    protected MouseEvent reactToMouseClick(MouseEvent click){
        if (click != null){ //ensure a mouse click occurred
            int clickX = click.getX();
            int clickY = click.getY();
            setDebugText("Click at: " + clickX + ", " + clickY);
        }
        return click; //returns the mouse event for any child classes overriding this method
    }
    
    
    
    
}
