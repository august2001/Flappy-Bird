/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package flappybird;
import Game.Objects;
import java.awt.Rectangle;
/**
 *
 * @author Admin
 */
public class WaterPipe extends Objects {
    private Rectangle rect;
    private boolean isBehindBird = false;
    
    public WaterPipe(int x, int y, int w, int h){
        super(x,y,w,h);
        rect = new Rectangle (x, y, w, h);
    }
    
    public void update(){
        setPosX(getPosX() -2);
        rect.setLocation((int) this.getPosX(), (int) this.getPosY());
    }
    
    public Rectangle getRect(){
        return rect;
    }
    
    public void setIsBehindBird(boolean b){
        isBehindBird = b;
    }
    public boolean getIsBehindBird(){
        return isBehindBird;
    }
}
