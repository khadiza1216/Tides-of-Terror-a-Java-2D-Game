import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;
import java.util.Random;

public class UnderwaterSurvival  extends JPanel implements ActionListener,KeyListener,MouseMotionListener{
    
    public static final int HEIGHT = 1000;//screen size
    public static final int WIDTH = 1920;
      public diver player;
     public ArrayList<Enemy> enemies;
    public ArrayList<ammo>ammos;
     public ArrayList<powerup>powerups;
     public int health = 100;
     public boolean isrunning=false;
     public boolean gameover=false;
     public boolean gamecomplete=false;
     public int score=0;
     public int level=1;
     public int enemiesremaining=0;
     public Timer gametimer;
     public long powerupspawntimer;
     public Random random;
      public Point mouseposition = new Point(WIDTH/2,HEIGHT/2);
     public Image[] backgroundImage=new Image[5];
     public Image playerImage;
     public Image[] enemiesImage = new Image[5];//4 types 
     public Image ammoImage;
     public Image tetaImage;
      public Image[] powerupImage = new Image[3];// 3 types 

     
    
     public UnderwaterSurvival(){
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
            setFocusable(true);
             addKeyListener(this);
           addMouseMotionListener(this);
        
        random = new Random();

        Images();

        player = new diver (WIDTH/2,HEIGHT/2,playerImage);
        enemies = new ArrayList<>();
        ammos = new ArrayList<>();
        powerups = new ArrayList<>();
        gametimer = new Timer(16,this);
    
     } 
       
        private void Images(){
            
            // Use relative paths so the game works from any directory
            backgroundImage[0] = new ImageIcon("back01.jpg").getImage();
            backgroundImage[1] = new ImageIcon("back07.jpg").getImage();
            backgroundImage[2] = new ImageIcon("back03.jpg").getImage();
            backgroundImage[3] = new ImageIcon("back08.jpg").getImage();
            backgroundImage[4] = new ImageIcon("back6.jpg").getImage();

            playerImage = new ImageIcon("animated-diving-image-0022.gif").getImage();

            enemiesImage[0] = new ImageIcon("jel-unscreen.gif").getImage();//jellyfish
            enemiesImage[1] = new ImageIcon("squid-unscreen.gif").getImage();//squid
            enemiesImage[2] = new ImageIcon("pir-unscreen.gif").getImage();//piranha
            enemiesImage[3] = new ImageIcon("shar-unscreen.gif").getImage();//shark
            enemiesImage[4] = new ImageIcon("eel-unscreen.gif").getImage();//eel

            powerupImage[0] = new ImageIcon("stick-and-bunch.png").getImage();
            powerupImage[1] = new ImageIcon("health-removebg-preview.png").getImage();
            powerupImage[2] = new ImageIcon("pngtree-3d-gaming-shield-png-image_16062500.png").getImage();

            ammoImage = new ImageIcon("teta-removebg-preview.png").getImage();
            tetaImage = new ImageIcon("teta_-_Copy-removebg-preview.png").getImage();
          
        } 

        void spawnEnemywithDamage(int damage, int enemyType){

            int x=0,y=0;
            int attempts =0;
            while(attempts<=20){

         if (random.nextBoolean()){    
            //left or right
          x = random.nextBoolean()?-50:WIDTH +50; //-50-> left, +50 -> right
          y = random.nextInt(HEIGHT); // any point of 0 to height -1
            } else {
                //up or bottom
              x = random.nextInt(WIDTH);
              y = random.nextBoolean()?-50:HEIGHT +50;//-50-> up, +50 -> bottom
            }
boolean validposition= true;
for (Enemy e : enemies){
float dx= x-e.x;
float dy= y-e.y;
 if (Math.sqrt(dx * dx + dy * dy) < 80) {
                validposition = false;
                break;
            }}
            if(validposition) break;
            attempts++;
}

            
            
            float speedMultiplier = 1.0f + (level - 1) *0.5f;
            enemies.add(new SimpleEnemy(x, y, speedMultiplier, damage, enemiesImage[enemyType]));
        }

        void spawnenemiesforlevel(){
        int enemiesperType = (int)Math.pow(2,level-1);
        int totalenemies= enemiesperType*5;

            enemiesremaining = totalenemies;
        //spawn enemies
        for(int i=0;i<enemiesperType;i++){
          spawnEnemywithDamage(4,0);
         spawnEnemywithDamage(6,1);
          spawnEnemywithDamage(8,2);
             spawnEnemywithDamage(10,3);
             spawnEnemywithDamage(12,4);

        }
     }

    public void overlap(){
        for(int i=0;i<enemies.size();i++){
            Enemy en1 = enemies.get(i);
      for(int j=i+1;j<enemies.size();j++){
         Enemy en2 = enemies.get(j);
           float dx=en2.x - en1.x;
            float dy=en2.y-en1.y;
                float distance= (float)Math.sqrt(dx*dx+dy*dy);

    if (distance < 60 && distance > 0) {
                    float pushforce = (60 - distance) * 0.5f;
                    float pushx = (dx / distance) * pushforce;
                    float pushy = (dy / distance) * pushforce;
                    
    // Push enemies away from each other
        en1.x -= pushx * 0.5f;
        en1.y -= pushy * 0.5f;
        en2.x += pushx * 0.5f;
        en2.y += pushy * 0.5f;
                    
    // Keep enemies within reasonable bounds

     en1.x = Math.max(-100, Math.min(WIDTH + 100, en1.x));
    en1.y = Math.max(-100, Math.min(HEIGHT + 100, en1.y));
    en2.x = Math.max(-100, Math.min(WIDTH + 100, en2.x));
     en2.y = Math.max(-100, Math.min(HEIGHT + 100, en2.y));
                }
            }
        }
    }

      
    private void spawnpowerup() {
     int x = random.nextInt(WIDTH - 60) + 30;
     int y = random.nextInt(HEIGHT - 60) + 30;
      int type = random.nextInt(3); // 0: Ammo, 1: Health, 2: sheild
        
        powerups.add(new powerup(x, y, type,powerupImage[type]));
    }
    
   

    
    
     
    public void startGame(){
          player = new diver (WIDTH/2,HEIGHT/2,playerImage);
        enemies.clear();
        gamecomplete=false;
        score = 0;
       level= 1;
        spawnenemiesforlevel();
         powerupspawntimer = System.currentTimeMillis(); 
        isrunning=true;
         gameover=false;
          gametimer.start();
        powerups.clear();
          ammos.clear();
          
    }
     void update(){
        if(!isrunning) 
        return;
       // player
        player.update(mouseposition); 

        overlap();
         // ammo
        for (int i=ammos.size()-1;i>=0;i--) {
            ammo a = ammos.get(i);
            a.update();
            
        if (a.x<0||a.x>WIDTH||a.y<0||a.y>HEIGHT) {// out of screen
            ammos.remove(i);
        continue;
            }
            
            // collision  
            for (int j =enemies.size()-1;j>=0;j--) {
                Enemy e =enemies.get(j);
                if (a.intersects(e)) {
                    
                  enemies.remove(j);
                 score += 10;
                  enemiesremaining--;
                 break;
                }
            }
        }
        //update enemies
        for(int i=enemies.size()-1; i>=0;i--){
              Enemy e=enemies.get(i);
            e.update(player.x,player.y);

            if(e.intersects(player) && !player.isInvulnerable()){
                player.takeDamage(e.getDamage());
                   player.setInvulnerable(1000);
                
                //remove after collision
            enemies.remove(i);
                  enemiesremaining--;
            if(player.getHealth()<=0){
                       gameover=true;
                    isrunning=false;
                }
            }
        }

        //level 
        if(enemiesremaining<=0){
            if(level<5){level++;
                
            spawnenemiesforlevel();
        ammos.clear();}  
            else{
                gamecomplete=true;
                isrunning=false;
            }
            
        }

        // powerups
        for (int i = powerups.size() - 1; i >= 0; i--) {
            powerup p = powerups.get(i);
            p.update();
            
            // Check for collision with player
            if (p.intersects(player)) {
                switch (p.getType()) {
                    case 0: // Ammo
                        player.addAmmo(10);
                        break;
                    case 1: // Health
                        player.addHealth(25);
                        break;
                    case 2: // Shield
                       player.setInvulnerable(5000);
                        break;
                }
                powerups.remove(i);
            }
        }
        
        // Check level complete
        if (enemiesremaining <= 0) {
            level++;
            spawnenemiesforlevel();
        }
        
        // Spawn power-ups 
          long currentTime = System.currentTimeMillis();
        if (currentTime-powerupspawntimer>10000) { // Every 10 seconds
              spawnpowerup();
             powerupspawntimer = currentTime;
        }
    }

    //paint

    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        //background
        int index = Math.min(level-1,4);
            g2d.drawImage(backgroundImage[index],0,0,WIDTH,HEIGHT,this);
        

        if (isrunning || gameover||gamecomplete) {
            //  player
           player.draw(g2d);
            
            //  enemies
            for(Enemy e : enemies){
                e.draw(g2d);
            }
            for(ammo a : ammos){
                a.draw(g2d);
            }
            for(powerup p  : powerups){
                p.draw(g2d);
            }
            drawUI(g2d);
            if(gameover){
                drawgameover(g2d);
            }
            if(gamecomplete){
                drawwinScreen(g2d);
            }
            
        }  else {
            drawStartScreen(g2d);
        }

    }

    //draw UI
    public void drawUI(Graphics2D g2d){
            g2d.setFont(new Font("Arial",Font.BOLD,20));
            g2d.setColor(Color.WHITE);

            g2d.drawString("Score: " + score, 20,30);
            
            g2d.drawString("Health: " +player.getHealth(), 1800,30);
            g2d.drawString("Ammo: " +player.getAmmo(), 1800,60);
            g2d.drawString("Enemies Remaining: " + enemiesremaining, 20,60);
g2d.setFont(new Font("Britannic Bold",Font.BOLD,50));
            g2d.setColor(Color.WHITE);

                        g2d.drawString("LEVEL - " + level, 820,60);


    }
    //start screen
    public void drawStartScreen(Graphics2D g2d){
    g2d.setFont(new Font("Cooper Black",Font.BOLD,46));
    g2d.setColor(Color.WHITE);
    g2d.drawString("TIDES OF TERROR " ,700,400);

      g2d.setFont(new Font("Arial",Font.BOLD,20));
    g2d.setColor(Color.WHITE);
     g2d.drawString("Face terrifying creatures in the sea...!" ,750,490);
        
     g2d.setFont(new Font("Courier New",Font.BOLD,20));
     g2d.setColor(Color.WHITE);
     g2d.drawString("press SPACE to start " ,810,675);
    }
    public void drawwinScreen(Graphics2D g2d){
        g2d.drawImage(backgroundImage[0],0,0,WIDTH,HEIGHT,this); 
    g2d.setFont(new Font("Cooper Black",Font.BOLD,46));
    g2d.setColor(Color.WHITE);
    g2d.drawString("Congratulations!!!!! " ,700,300);

      g2d.setFont(new Font("Arial",Font.BOLD,20));
    g2d.setColor(Color.WHITE);
     g2d.drawString("You have finally survived...!" ,750,400);
        g2d.setFont(new Font("Arial",Font.BOLD,20));
          g2d.setColor(Color.WHITE);
         g2d.drawString("Your score : " +score,750,500);
          
     g2d.setFont(new Font("Courier New",Font.BOLD,20));
     g2d.setColor(Color.WHITE);
     g2d.drawString("press SPACE to start " ,810,675);
    }

    public void drawgameover(Graphics2D g2d){
        g2d.drawImage(backgroundImage[0],0,0,WIDTH,HEIGHT,this);
        g2d.setFont(new Font("Britannic Bold",Font.BOLD,46));
        g2d.setColor(Color.WHITE);
        g2d.drawString("Game Over" ,740,400);
          g2d.setFont(new Font("Arial",Font.BOLD,20));
          g2d.setColor(Color.WHITE);
         g2d.drawString("Score : " +score,750,500);
           g2d.drawString("Level  : " +level,750,550);
           g2d.setFont(new Font("Courier New",Font.BOLD,20));
         g2d.setColor(Color.WHITE);
         g2d.drawString("press SPACE to start again" ,750,700);

    }

    @Override
    public void actionPerformed(ActionEvent e){
        update();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        if(key== KeyEvent.VK_SPACE){
            if(!isrunning){
                startGame();
            }

        }
        if (isrunning) {
            // Shooting
            if (key == KeyEvent.VK_UP && player.canShoot()) {
                // Using Ammo class instead of Projectile
                ammos.add(new ammo((int)(player.x + player.width / 2 - 15), (int)player.y, 0, -10, ammoImage));
                player.shoot();
            }
            if (key == KeyEvent.VK_DOWN && player.canShoot()) {
                ammos.add(new ammo((int)(player.x + player.width / 2 - 15), (int)(player.y + player.height), 0, 10, ammoImage));
                player.shoot();
            }
            if (key == KeyEvent.VK_LEFT && player.canShoot()) {
                ammos.add(new ammo((int)player.x, (int)(player.y + player.height / 2 - 15), -10, 0, tetaImage));
                player.shoot();
            }
            if (key == KeyEvent.VK_RIGHT && player.canShoot()) {
                ammos.add(new ammo((int)(player.x + player.width), (int)(player.y + player.height / 2 - 15), 10, 0, tetaImage));
                player.shoot();
            }
        }}
   
    @Override
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
    
    // Reset shooting 
    if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN || 
        key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
        player.resetShooting();
    }
    }
    @Override
    public void keyTyped(KeyEvent e){

    }
    @Override
    public void mouseDragged(MouseEvent e){
        mouseposition.x = e.getX();
        mouseposition.y= e.getY();
    }
    @Override
    public void mouseMoved(MouseEvent e){
        mouseposition.x = e.getX();
        mouseposition.y= e.getY();
    }
} 
 