import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;


public class HendrataGame extends ScrollingGame {

    // Starting Player coordinates
    protected static final int STARTING_PLAYER_X = 200;
    protected static final int STARTING_PLAYER_Y = 100;

    // Score needed to win the game
    protected static final int SCORE_TO_WIN = 500;

    // Maximum that the game speed can be increased to
    // (a percentage, ex: a value of 300 = 300% speed, or 3x regular speed)
    protected static final int MAX_GAME_SPEED = 300;

    // Interval that the speed changes when pressing speed up/down keys
    protected static final int SPEED_CHANGE = 20;

    protected static final String INTRO_SPLASH_FILE = "assets/DragonsParadise.png";
    // Key pressed to advance past the splash screen
    public static final int ADVANCE_SPLASH_KEY = KeyEvent.VK_ENTER;

    // Interval that Entities get spawned in the game window
    // ie: once every how many ticks does the game attempt to spawn new Entities
    protected static final int SPAWN_INTERVAL = 37;

    private static int ENTER_COUNT= 0;
    private static final int MAX_ENTER_COUNT = 2;


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
        player = new DragonPlayer(STARTING_PLAYER_X, STARTING_PLAYER_Y); 
        displayList.add(player);
        score = 0;
        setSplashImage(INTRO_SPLASH_FILE);
    }

    // Called on each game tick
    protected void updateGame() {
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
                gcOutOfWindowEntities();
            } 
        }
        determineIfGameIsOver();
        if (((DragonPlayer) player).getRainbowTime() > 0){
            setTitleText("Lives Left: " + player.getHP() + "        Energy Level: " + score + "        INVINCIBILITY MODE: ON");  
        } else {
            setTitleText("Lives Left: " + player.getHP() + "        Energy Level: " + score + "        INVINCIBILITY MODE: OFF");  
        }

        //collisions between dragons and between dragons and pigs
        handleDragonToDragonCollisions();
        handleDragonToPigCollisions();
        handleLongDragonToGoldCloudCollisions();
        handlePigToGoldCloudCollisions();
        handleOuchVisibility();

        ((DragonPlayer) player).setRainbowTime(((DragonPlayer) player).getRainbowTime()-1);
        if (((DragonPlayer) player).getRainbowTime() <= 0){
            ((DragonPlayer) player).setRainbowState(false);
        }

    }


    public void handleOuchVisibility(){
        for (int i = 0; i < displayList.size(); i++) {
            Entity currEntity = displayList.get(i);
            if (currEntity instanceof Ouch){
                ((Ouch)currEntity).setVisibilityDuration(((Ouch)currEntity).getVisibilityDuration()-1);
                if (((Ouch)currEntity).getVisibilityDuration() <= 0){
                    displayList.remove(currEntity);
                }
            }

        }
    }

    protected void handlePlayerCollision(Collectable collidedWith) {
        if (player.isCollidingWith((Entity) collidedWith)) {
            int points = collidedWith.getPoints();
            score += points;
            
            if (((DragonPlayer) player).getRainbowTime() <= 0){ //if no invincibility
                int damage = collidedWith.getDamage();
                player.modifyHP(damage);
            } else {
                if (collidedWith.getDamage() > 0){
                    int damage = collidedWith.getDamage();
                    player.modifyHP(damage);
                }
            }
  
            if (collidedWith instanceof GoldCloud){
                ((DragonPlayer) player).setRainbowState(true);
                ((DragonPlayer) player).setRainbowTime(250);
            }

            if (collidedWith instanceof LongDragon && (((DragonPlayer) player).getRainbowTime() <= 0)){
                Ouch ouch = new Ouch((player.getX()+(player.getWidth()/2)), (player.getY()-(player.getHeight()/2)));
                displayList.add(ouch);
            }
    
            displayList.remove((Entity) collidedWith); // Removes entity from the displayList when collision with player occurs
        }
    }

    

    public void handleDragonToDragonCollisions() {
        // Assuming currentEntity is another Collectable representing the other dragon
        for (int i = 0; i < displayList.size(); i++) {
            Entity currEntity = displayList.get(i);
            if (currEntity instanceof LongDragon){
                for (int j = 0; j < displayList.size(); j++){
                    Entity othEntity = displayList.get(j);
                    if ((othEntity instanceof LongDragon) && (i != j)){
                        if (currEntity.isCollidingWith(othEntity)) {
                                // Collision occurred, remove both entities from the display list
                                displayList.remove(currEntity);
                                displayList.remove(othEntity);
                                BloodSplat bloodSplat = new BloodSplat(currEntity.getX(), currEntity.getY());
                                displayList.add(bloodSplat);
                            }
                    }
                }
            }
            if (currEntity instanceof BloodSplat) {
                ((BloodSplat) currEntity).setVisibilityDuration(((BloodSplat) currEntity).getVisibilityDuration() - 1);
                if (((BloodSplat) currEntity).getVisibilityDuration() <= 0) {
                    displayList.remove(currEntity);
                }
            }

        }
    }

    public void handleDragonToPigCollisions() {
        for (int i = 0; i < displayList.size(); i++) {
            Entity currEntity = displayList.get(i);
            if (currEntity instanceof LongDragon){
                for (int j = 0; j < displayList.size(); j++){
                    Entity othEntity = displayList.get(j);
                    if ((othEntity instanceof Pig)){
                        if (currEntity.isCollidingWith(othEntity)) {
                            // Collision occurred, only remove pig
                            displayList.remove(othEntity);
                            Yum yum = new Yum(othEntity.getX(), othEntity.getY());
                            displayList.add(yum);
                        }
                    }
                }
            }
            if (currEntity instanceof Yum) {
                ((Yum) currEntity).setVisibilityDuration(((Yum) currEntity).getVisibilityDuration() - 1);
                if (((Yum) currEntity).getVisibilityDuration() <= 0) {
                    displayList.remove(currEntity);
                }
            }

        }
    }

    public void handleLongDragonToGoldCloudCollisions(){
        for (int i = 0; i < displayList.size(); i++) {
            Entity currEntity = displayList.get(i);
            if (currEntity instanceof LongDragon){
                for (int j = 0; j < displayList.size(); j++){
                    Entity othEntity = displayList.get(j);
                    if ((othEntity instanceof GoldCloud)){
                        if (currEntity.isCollidingWith(othEntity)) {
                            // Collision occurred, only remove gold cloud
                            displayList.remove(othEntity);
                        }
                    }
                }
            }
        }
    }

    public void handlePigToGoldCloudCollisions(){
        for (int i = 0; i < displayList.size(); i++) {
            Entity currEntity = displayList.get(i);
            if (currEntity instanceof Pig){
                for (int j = 0; j < displayList.size(); j++){
                    Entity othEntity = displayList.get(j);
                    if ((othEntity instanceof GoldCloud)){
                        if (currEntity.isCollidingWith(othEntity)) {
                            // Collision occurred, only remove gold cloud
                            displayList.remove(othEntity);
                        }
                    }
                }
            }
        }
    }
    



    
    
    
        //Spawn new Entities on the right edge of the game window
        protected void spawnEntities(){
            int randomY = rand.nextInt(getWindowHeight() - DragonPlayer.PLAYER_HEIGHT);
            if (rand.nextInt(100) < 12) {
                GoldCloud goldcloud = new GoldCloud(getWindowWidth(), randomY);
                if (isNotCollidingRightNow(goldcloud))
                    displayList.add(goldcloud);
            } else if (rand.nextInt(100) < 34) { // Increase the probability of LongDragon spawn
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
        if (player.getHP() <= 0){
            setTitleText("GAME OVER! YOU LOST!");
            setSplashImage("assets/lost.png");
            
        } else {
            setTitleText("MISSION ACCOMPLISHED!");
            setSplashImage("assets/Won.png");
        }
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
        if (!isPaused){
            if (key == UP_KEY && player.getY() - player.getMovementSpeed() > 0){
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
            if (key == SPEED_UP_KEY){
                if (getGameSpeed() < MAX_GAME_SPEED){
                    setGameSpeed(getGameSpeed() + SPEED_CHANGE);
                }
            }
            if (key == SPEED_DOWN_KEY){
                if (getGameSpeed() > SPEED_CHANGE){
                    setGameSpeed(getGameSpeed() - SPEED_CHANGE);
                }
            }
        }
        if (key == KEY_PAUSE_GAME){ 
                isPaused = !isPaused;
        }
        
        
        setDebugText("Key Pressed!: " + KeyEvent.getKeyText(key) + ",  DisplayList size: " + displayList.size());
        
        //if a splash screen is active, only react to the "advance splash" key... nothing else!
        if (getSplashImage() != null) {
            if (key == ADVANCE_SPLASH_KEY && ENTER_COUNT < MAX_ENTER_COUNT) {
                switch (ENTER_COUNT) {
                    case 0:
                        super.setSplashImage("assets/Legend.gif");
                        break;
                    case 1:
                        super.setSplashImage(null);
                        break;
                    // Add more cases if needed
                }
                ENTER_COUNT++;
            }
    
            return;
        }

        if (key == RIGHT_KEY && ((DragonPlayer) player).getDragonOrientation().equals("LEFT")){
            ((DragonPlayer) player).setRightFacingDragon();
        }
        if (key == DOWN_KEY && ((DragonPlayer) player).getDragonOrientation().equals("LEFT")){
            ((DragonPlayer) player).setLeftFacingDragon();
        }
        if (key == UP_KEY && ((DragonPlayer) player).getDragonOrientation().equals("LEFT")){
            ((DragonPlayer) player).setLeftFacingDragon();
        }
        if (key == LEFT_KEY && ((DragonPlayer) player).getDragonOrientation().equals("RIGHT")){
            ((DragonPlayer) player).setLeftFacingDragon();
        }
        if (key == DOWN_KEY && ((DragonPlayer) player).getDragonOrientation().equals("RIGHT")){
            ((DragonPlayer) player).setRightFacingDragon();
        }
        if (key == UP_KEY && ((DragonPlayer) player).getDragonOrientation().equals("RIGHT")){
            ((DragonPlayer) player).setRightFacingDragon();
        }
    }



}
