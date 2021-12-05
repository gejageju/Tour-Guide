import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
  
public class MyCanvas extends Canvas{  
    String city;
    Image image;
    BufferedImage bufferedImage;
    MyCanvas(String s)
    {
        city=s;
        try{
            bufferedImage = ImageIO.read(new File("D:/Java/Travel_guide/Tour_giude/images/"+city+".jpg"));
            image = bufferedImage.getScaledInstance(500, 500, Image.SCALE_DEFAULT);
            
            System.out.println("My canvas yo yo ");
            
            }
            catch(Exception a){} 
    }
    public void paint(Graphics g) {  
  
        g.drawImage(image, 100,100,this);  
        System.out.println("paint");
    }  
        
  
} 