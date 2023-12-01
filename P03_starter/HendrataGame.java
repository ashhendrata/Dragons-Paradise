import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;


public class HendrataGame extends ScrollingGame {

    // Starting Player coordinates
    protected static final int STARTING_PLAYER_X = 200;
    protected static final int STARTING_PLAYER_Y = 100;

    // Score needed to win the game
    protected static final int SCORE_TO_WIN = 300;

    // Maximum that the game speed can be increased to
    // (a percentage, ex: a value of 300 = 300% speed, or 3x regular speed)
    protected static final int MAX_GAME_SPEED = 300;

    // Interval that the speed changes when pressing speed up/down keys
    protected static final int SPEED_CHANGE = 20;

    protected static final String INTRO_SPLASH_FILE = "assets/splash.gif";
    // Key pressed to advance past the splash screen
    public static final int ADVANCE_SPLASH_KEY = KeyEvent.VK_ENTER;

    // Interval that Entities get spawned in the game window
    // ie: once every how many ticks does the game attempt to spawn new Entities
    protected static final int SPAWN_INTERVAL = 60;



    // Constructor using default dimensions
    public HendrataGame() {
        this(1000, 600);
    }

    // Constructor with specified dimensions
    public HendrataGame(int gameWidth, int gameHeight) {
        super(gameWidth, gameHeight);
    }

    // Performs all of the initialization operations that need to be done before the
    // game starts
    protected void pregame() {
        this.setBackgroundImage("assets/clouds.gif");
        player = new DragonPlayer(STARTING_PLAYER_X, STARTING_PLAYER_Y); // Use ed_player instead of Player
        displayList.add(player);
        score = 0;
        setSplashImage(INTRO_SPLASH_FILE);
    }

    // Called on each game tick

    @Override
    protected void updateGame() {
        super.updateGame(); // Call the updateGame method of the superclass
        setTitleText("Lives Left: " + player.getHP() + ", Food Eaten: " + score);   

    
        // Iterate through the display list to check for collisions
        for (int i = 0; i < displayList.size(); i++) {
            Entity currentEntity = displayList.get(i);
    
            // Check if the current entity is a non-player entity (excluding Player and BloodSplat)
            if (!(currentEntity instanceof Player) && !(currentEntity instanceof BloodSplat)) {
                // Check for collisions with other non-player entities
                for (int j = 0; j < displayList.size(); j++) {
                    Entity otherEntity = displayList.get(j);
    
                    // Skip checking collisions with itself
                    if (i != j) {
                        // Check if the other entity is also a non-player entity
                        if (!(otherEntity instanceof Player) && !(otherEntity instanceof BloodSplat)) {
                            // Check for collisions between non-player entities
                            if (currentEntity.isCollidingWith(otherEntity)) {
                                // Collision occurred, remove both entities from the display list
                                displayList.remove(currentEntity);
                                displayList.remove(otherEntity);
                                BloodSplat bloodSplat = new BloodSplat(currentEntity.getX(), currentEntity.getY());
                                displayList.add(bloodSplat);
                            }
                        }
                    }
                }
            }
            if (currentEntity instanceof BloodSplat) {
                ((BloodSplat) currentEntity).setVisibilityDuration(((BloodSplat) currentEntity).getVisibilityDuration() - 1);
                if (((BloodSplat) currentEntity).getVisibilityDuration() <= 0) {
                    displayList.remove(currentEntity);
                }
            }
        }
    }

    protected void UpdateGame() {
        super.updateGame(); //deals with player collisions
        setTitleText("Lives Left: " + player.getHP() + ", Food Eaten: " + score);  

        //collisions between dragons and between dragons and pigs
        for (int i = 0; i < displayList.size(); i++){
            Entity currEntity = displayList.get(i);
            if (currEntity instanceof LongDragon){
                currEntity.handleDragonToDragonCollisions(currEntity, );
            }

        }

    }

    public void handleDragonToDragonCollisions(Collectable dragon, Collectable currentEntity) {
        // Assuming currentEntity is another Collectable representing the other dragon
        if (((Entity) dragon).isCollidingWith((Entity) currentEntity)){
            displayList.remove(currentEntity);
            displayList.remove(dragon);
            BloodSplat bloodSplat = new BloodSplat(((Entity)currentEntity).getX(), ((Entity) currentEntity).getY());
            displayList.add(bloodSplat);
        }
    }

    public void handleDragonToDragonCollisions(Collectable dragon) {
        for (int i = 0; i < displayList.size(); i++){
            Entity currEntity = displayList.get(i);
            if (currEntity instanceof LongDragon){
                currEntity.handleDragonToDragonCollisions(currEntity, );
            }

        }
    }
    



    
    
    
        //Spawn new Entities on the right edge of the game window
        protected void spawnEntities(){
            int randomY = rand.nextInt(getWindowHeight() - Avoid.AVOID_HEIGHT);
            if (rand.nextInt(100) < 10) {
                GoldCloud goldcloud = new GoldCloud(getWindowWidth(), randomY);
                if (isNotCollidingRightNow(goldcloud))
                    displayList.add(goldcloud);
            } else if (rand.nextInt(100) < 37) { // Increase the probability of LongDragon spawn
                    LongDragon longdragon = new LongDragon(-LongDragon.LONG_DRAGON_WIDTH, randomY, LongDragon.LONG_DRAGON_IMAGE_FILE);
                    if (isNotCollidingRightNow(longdragon))
                        displayList.add(longdragon);
            } else if (rand.nextInt(100) < 40){
                    LongDragon leftlongdragon = new LongDragon(getWindowWidth(), randomY, LongDragon.LONG_LEFT_DRAGON_IMAGE_FILE);
                    if (isNotCollidingRightNow(leftlongdragon))
                        displayList.add(leftlongdragon);
            } else {
                Pig pig = new Pig(getWindowWidth(), randomY);
                if (isNotCollidingRightNow(pig))
                    displayList.add(pig);
            }
        }



        public boolean isNotCollidingRightNow(Entity oneEntity){
            for (int i = 0; i < displayList.size(); i++) {
                Entity entity = displayList.get(i);
                if (entity instanceof Scrollable) {
                    if (oneEntity.isCollidingWith(displayList.get(i))) { 
                        return false;
                    }
                }
            } 
            return true;
        }


        public boolean isNotCollidingRightNowInX(Entity oneEntity){
            for (int i = 0; i < displayList.size(); i++) {
                Entity entity = displayList.get(i);
                if (entity instanceof Scrollable) {
                    if (oneEntity.isCollidingWithInX(displayList.get(i))) { 
                        return false;
                    }
                }
            } 
            return true;
        }

    // Called once the game is over, performs any end-of-game operations
    @Override
    protected void postgame() {
        super.postgame(); // Call the postgame method of the superclass
        // Additional postgame specific to HendrataGame if needed
    }

    // Determines if the game is over or not
    // Game can be over due to either a win or lose state
    @Override
    protected boolean determineIfGameIsOver() {
        return super.determineIfGameIsOver(); // Call the determineIfGameIsOver method of the superclass
        // Additional conditions specific to HendrataGame if needed
    }

    // Reacts to a single key press on the keyboard
    @Override
    protected void reactToKey(int key) {
        super.reactToKey(key); // Call the reactToKey method of the superclass
        
        if (key == RIGHT_KEY && ((DragonPlayer) player).getDragonOrientation().equals("LEFT")){
            ((DragonPlayer) player).setRightFacingDragon();
        }
        if (key == LEFT_KEY && ((DragonPlayer) player).getDragonOrientation().equals("RIGHT")){
            ((DragonPlayer) player).setLeftFacingDragon();
        }
    }



}
