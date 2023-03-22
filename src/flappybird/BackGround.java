/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package flappybird;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 *
 * @author Admin
 */
public class BackGround {
    private BufferedImage backgroundimg;
    private int x1, y1, x2, y2;
    
    public BackGround(){
        try{
            backgroundimg = ImageIO.read(new File("./Anh/ground --.png"));
        } catch (IOException ex){}
        
        x1=0;
        y1=0;
        x2= x1+1000;
        y2=0;
    }
    public void Update(){
        x1-=2; 
        x2-=2;
        if(x2 < 0) x1=x2+1000;
        if(x1 < 0) x2=x1+1000;
    }
    public void Paint(Graphics2D g2){
        g2.drawImage(backgroundimg, x1, y1, null);
        g2.drawImage(backgroundimg, x2, y2, null);
    }
    
    public int getYGround(){
        return y1;
    }
}
