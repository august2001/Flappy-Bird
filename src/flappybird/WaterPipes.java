/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package flappybird;
import Game.QueueList;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
/**
 *
 * @author Admin
 */
public class WaterPipes {
    private QueueList<WaterPipe> waterpipes;
    private BufferedImage waterpipeimg, waterpipeimg2;
    
    public static int SIZE=6;
    
    private int topWaterPipeY = -300;
    private int bottomWaterPipeY = 250;
    
    public WaterPipe getWaterPipe(int i){
        return waterpipes.get(i);
    }
    
    public int getRandomY(){
        Random random = new Random();
        int a;
        a=random.nextInt(10);
        return a*30;
    }
    public WaterPipes(){
        waterpipes = new QueueList<WaterPipe>();
        WaterPipe wt;
        
        try{
            waterpipeimg = ImageIO.read(new File("./Anh/waterpipe1.png"));
            waterpipeimg2 = ImageIO.read(new File("./Anh/waterpipe2.png"));
        }catch(IOException ex){}
        
        for(int i=0; i<SIZE/2; i++){
            int deltaY = getRandomY();
            wt = new WaterPipe(830+i*300, bottomWaterPipeY+ deltaY, 74, 400);
                waterpipes.push(wt);
            wt = new WaterPipe(830+i*300, topWaterPipeY+ deltaY, 74, 400);    
                waterpipes.push(wt);
        }
    }
    
    public void resetWaterPipes(){
        waterpipes = new QueueList<WaterPipe>();
        WaterPipe wt;
        
        for(int i=0; i<SIZE/2; i++){
            
            int deltaY = getRandomY();
            wt = new WaterPipe(830+i*300, bottomWaterPipeY+ deltaY, 74, 400);
                waterpipes.push(wt);
            wt = new WaterPipe(830+i*300, topWaterPipeY+ deltaY, 74, 400);       
                waterpipes.push(wt);
        }
    }
    public void update(){
        
        for(int i=0; i<SIZE; i++){
            waterpipes.get(i).update();
            
        if(waterpipes.get(0).getPosX()<-74)
        {
            int deltaY = getRandomY();
            WaterPipe wtp;
            wtp=waterpipes.pop();
            wtp.setPosX(waterpipes.get(4).getPosX()+300);
            wtp.setPosY(bottomWaterPipeY+ deltaY);
            waterpipes.push(wtp);
            wtp.setIsBehindBird(false);
            
            wtp=waterpipes.pop();
            wtp.setPosX(waterpipes.get(4).getPosX());
            wtp.setPosY(topWaterPipeY+ deltaY);
            waterpipes.push(wtp);
            wtp.setIsBehindBird(false);
            
        }
        }    
    }
    public void paint(Graphics2D g2){
        for(int i=0; i<SIZE; i++){
            if(i%2==0)
                g2.drawImage(waterpipeimg, (int) waterpipes.get(i).getPosX(), (int) waterpipes.get(i).getPosY(), null);
            else
                 g2.drawImage(waterpipeimg2, (int) waterpipes.get(i).getPosX(), (int) waterpipes.get(i).getPosY(), null);
        }
    }
}
