import java.awt.Graphics2D;
import java.awt.Image;

public abstract class Enemy extends GameObject {
    protected float speed;
    protected int damage;
    
    
    public Enemy(float x,float y,int width,int height,float speed,int damage,Image image){
        super(x,y,width,height,image);
        this.speed =speed;
        this.damage= damage;
        
    }
    public abstract void update(float playerX,float playerY);

    public abstract void draw(Graphics2D g2d);

    public int getDamage(){
        return damage;
    }

}
 class JellyFish extends Enemy{
    public JellyFish (float x, float y, int width, int height, float speed,Image image){
        super(x,y,width,height,speed,4,image);
        }
        @Override
        public void update(float playerX, float playerY){
        float dx = playerX-x;
        float dy = playerY - y;
        float distance = (float) Math.sqrt(dx * dx + dy * dy);

        if (distance>0){
            x += (dx/distance) *speed;
            y+= (dy/distance)* speed;
        }
        }

        @Override
        public void draw(Graphics2D g2d){
            g2d.drawImage(image, (int)x,(int)y, width, height, null);
        }
}
class Squid extends Enemy{
    public Squid (float x, float y, int width, int height, float speed,Image image){
        super(x,y,150,150,speed,6,image);
        }
        @Override
        public void update(float playerX, float playerY){
        float dx = playerX-x;
        float dy = playerY - y;
        float distance = (float) Math.sqrt(dx * dx + dy * dy);

        if (distance>0){
            x += (dx/distance) *speed;
            y+= (dy/distance)* speed;
        }
        }

        @Override
        public void draw(Graphics2D g2d){
            g2d.drawImage(image,(int) x,(int) y, width, height, null);
        }
}
class Piranha extends Enemy{
    public Piranha (float x, float y, int width, int height, float speed,Image image){
        super(x,y,width,height,speed,8,image);
        }
        @Override
        public void update(float playerX, float playerY){
        float dx = playerX-x;
        float dy = playerY - y;
        float distance = (float) Math.sqrt(dx * dx + dy * dy);

        if (distance>0){
            x += (dx/distance) *speed;
            y+= (dy/distance)* speed;
        }
        }

        @Override
        public void draw(Graphics2D g2d){
            g2d.drawImage(image,(int) x, (int)y, width, height, null);
        }
}
class eel extends Enemy{
    public eel (float x, float y, int width, int height, float speed,Image image){
        super(x,y,width,height,speed,10,image);
        }
        @Override
        public void update(float playerX, float playerY){
        float dx = playerX-x;
        float dy = playerY - y;
        float distance = (float) Math.sqrt(dx * dx + dy * dy);

        if (distance>0){
            x += (dx/distance) *speed;
            y+= (dy/distance)* speed;
        }
        }

        @Override
        public void draw(Graphics2D g2d){
            g2d.drawImage(image, (int)x,(int)y, width, height, null);
        }
}
class Shark extends Enemy{
    public Shark (float x, float y, int width, int height, float speed,Image image){
        super(x,y,width,height,speed,12,image);
        }
        @Override
        public void update(float playerX, float playerY){
        float dx = playerX-x;
        float dy = playerY - y;
        float distance = (float) Math.sqrt(dx * dx + dy * dy);

        if (distance>0){
            x += (dx/distance) *speed;
            y+= (dy/distance)* speed;
        }
        }

        @Override
        public void draw(Graphics2D g2d){
            g2d.drawImage(image,(int)x,(int) y, width, height, null);
        }
}


 