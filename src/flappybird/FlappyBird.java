/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package flappybird;
import Game.AFrameOnImage;
import Game.Animation;
import Game.GameScreen;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
/**
 *
 * @author Admin
 */
public class FlappyBird extends GameScreen {

    private BufferedImage bird;
    private Animation bird_ani;
    private Bird b;
    private Ground ground;
    private BackGround background;
    private WaterPipes waterpipes;
    private int Score=0;
    
    private int BeginScreen=0;
    private int PlayScreen=1;
    private int OverScreen=2;
    private int CurrentScreen = BeginScreen;
    
    public static float g = 0.15f;
    
    public FlappyBird(){
        super(800,600);
        setTitle("FlappyBird");
        try{
            bird = ImageIO.read(new File("./Anh/bird_icon-.png"));
        }catch(IOException ex){}
        
        bird_ani = new Animation(150);
        AFrameOnImage af;
        af = new AFrameOnImage(0, 0, 65, 60);
        bird_ani.AddFrame(af);
        af = new AFrameOnImage(60, 0, 65, 60);
        bird_ani.AddFrame(af);
        af = new AFrameOnImage(120, 0, 65, 60);
        bird_ani.AddFrame(af);
        af = new AFrameOnImage(60, 0, 65, 60);
        bird_ani.AddFrame(af);
        
        b = new Bird(350, 250 , 50, 50);
        ground = new Ground();
        background = new BackGround();
        waterpipes =new WaterPipes();

        BeginGame();
    }
    
    public void ResetGame(){
        b.setPos(350, 250);
        b.setVt(0);
        b.setLive(true);
        Score=0;
        waterpipes.resetWaterPipes();
    }
    @Override
    public void GAME_UPDATE(long deltaTime) {
        if (CurrentScreen == BeginScreen){
            ResetGame();
        }else if (CurrentScreen == PlayScreen)
        {
            if(b.getLive()) bird_ani.Update_Me(deltaTime);
            b.update(deltaTime);
            ground.Update();
            background.Update();
            waterpipes.update();
            
            for(int i=0; i<WaterPipes.SIZE;i++){
                if(b.getRect().intersects(waterpipes.getWaterPipe(i).getRect())){
                    if(b.getLive()) b.impactSound.play();
                    b.setLive(false);                
                } 
            }
            for(int i=0; i<WaterPipes.SIZE;i++){
                if(b.getPosX() + b.getW() > waterpipes.getWaterPipe(i).getPosX() + waterpipes.getWaterPipe(i).getW()
                        && !waterpipes.getWaterPipe(i).getIsBehindBird() && i%2==0){
                    Score++;             
                    b.getscoreSound.play();
                    waterpipes.getWaterPipe(i).setIsBehindBird(true);
                }
            }          
            if(b.getPosY() + b.getH() > ground.getYGround()) CurrentScreen = OverScreen;
        }      
    }

    @Override
    public void GAME_PAINT(Graphics2D g2) {

        background.Paint(g2);
        waterpipes.paint(g2);
        ground.Paint(g2);
             
        if(b.getFlying())
            bird_ani.PaintAnims((int) b.getPosX(), (int) b.getPosY(), bird, g2, 0, -1);
        else 
            bird_ani.PaintAnims((int) b.getPosX(), (int) b.getPosY(), bird, g2, 0, 0);
        
        if(CurrentScreen == BeginScreen){ 
            g2.setColor(Color.orange);
            g2.setFont(new Font("TimesRoman", Font.BOLD, 30));
            g2.drawString("Chào mừng đến với trò chơi", 200, 140);
            g2.setFont(new Font("TimesRoman", Font.BOLD, 40));
            g2.drawString("FLAPPY BIRD", 250, 200);
            g2.setColor(Color.blue);
            g2.setFont(new Font("TimesRoman", Font.BOLD, 30));
            g2.drawString("Nhấn phím Space để chơi game^^", 150, 340);
        }
        if(CurrentScreen == OverScreen){
            g2.setColor(Color.red);
            g2.setFont(new Font("TimesRoman", Font.BOLD, 50));
            g2.drawString("KẾT THÚC!", 250, 240);
            g2.setColor(Color.blue);
            g2.setFont(new Font("TimesRoman", Font.BOLD, 30));
            g2.drawString("Điểm Số: "+Score, 300, 300);           
            g2.setFont(new Font("TimesRoman", Font.BOLD, 30));
            g2.drawString("Nhấn Space để bắt đầu lại", 200, 380);
        }
        
        g2.setColor(Color.blue);
        g2.setFont(new Font("TimesRoman", Font.BOLD, 24));
        g2.drawString("Score: "+Score, 10, 50);
    }

    @Override
    public void KEY_ACTION(KeyEvent e, int Event) {
        if(Event == KEY_PRESSED){
            if(CurrentScreen == BeginScreen){
                if(e.getKeyCode()==KeyEvent.VK_SPACE)
                {
                    CurrentScreen = PlayScreen;
                }
            }else if(CurrentScreen == PlayScreen){
                if(e.getKeyCode()==KeyEvent.VK_SPACE && b.getLive()) 
                {
                    b.fly();
                }              
            }else if(CurrentScreen == OverScreen){
                if(e.getKeyCode()==KeyEvent.VK_SPACE)
                {
                    CurrentScreen = BeginScreen;
                }
            }           
        }
    }
    public static void main(String[] args) {
        // TODO code application logic here
        new FlappyBird();
    }  
}
