
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Image;

public class diver extends GameObject {
    public int health = 100;
   public float speed = 8.0f;
    public long invulnerableuntil;
    public boolean invulnerable= false;
    public int ammo= 20;
public boolean canShootNow=true;
   
    public diver(int x, int y ,Image image){
        super(x, y, 100, 100,image);
    }

    public void update(Point mouseposition){
        if (mouseposition !=null){
            float dx= mouseposition.x - (x+width/2);
            float dy= mouseposition.y - (y+height/2);
            float length = (float)Math.sqrt(dx*dx+dy*dy);

            if(length>5){
                dx=dx/length*speed;
                dy=dy/length*speed;
                x+=dx;
                y+=dy;
            }
        }
        
        if(invulnerable){
            if(System.currentTimeMillis()>invulnerableuntil){
                invulnerable=false;
            }
        }
    }
    public void setInvulnerable(long duration){
        invulnerable=true;
        invulnerableuntil=System.currentTimeMillis() + duration;
    }
    public void draw(Graphics2D g){

        if(invulnerable && System.currentTimeMillis() /100%2==0){  //blink
            return;
        }
        if(image!=null){
             g.drawImage(image,(int) x,(int) y, width, height, null);
        }
    }
    public boolean canShoot() {
        return ammo > 0 && canShootNow;
    }
    public void shoot() {
        if (canShoot()) {
            ammo--;
            canShootNow = false; 
        }
    }
     public void addHealth(int amount) {
        health += amount;
    }
    
    public void addAmmo(int amount) {
        ammo += amount;
    }
    public void resetShooting() {
        canShootNow = true;
    }
    public void takeDamage(int damage){
        if(!invulnerable){
        health -= damage;
        }
    }
   
    public boolean isInvulnerable(){
        return invulnerable;
    }
    public int getHealth(){
        return health;
    }
    public int getAmmo() {
        return ammo;
    }
}
