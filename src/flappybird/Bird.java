/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package flappybird;
import Game.Objects;
import Game.SoundPlayer;
import java.awt.Rectangle;
import java.io.File;
/**
 *
 * @author Admin
 */
public class Bird extends Objects {
    private float vt=0; 
    private boolean Flying = false;
    private Rectangle rect;
    public SoundPlayer flapSound, getscoreSound, impactSound;
    
    private boolean isLive = true;
    
    public Bird(int x, int y, int w, int h){
        super(x , y, w, h);
        rect = new Rectangle(x, y, w, h);
        
        flapSound = new SoundPlayer(new File("./Anh/flap.wav"));
        getscoreSound = new SoundPlayer(new File("./Anh/getscore.wav"));
        impactSound = new SoundPlayer(new File("./Anh/dead.wav"));
    }
    
    public void setLive(boolean b){       
        isLive = b;
    }
    public boolean getLive(){
        return isLive;
    }
    
    public void update(long deltaTime){
        vt+=FlappyBird.g;
        this.setPosY(this.getPosY()+vt);
        this.rect.setLocation((int) this.getPosX(), (int) this.getPosY());
        if(vt<0) Flying = true;
        else Flying = false;
    }
    
    public void fly(){
        vt = -3;
        flapSound.play();
    }
    public void setVt(float vt){
        this.vt = vt;
    }
    public boolean getFlying(){
        return Flying;
    }
    
    public Rectangle getRect(){
        return rect;
    }
}
