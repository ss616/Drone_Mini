/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drone_mini;


import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author SIMRAN
 */
public class ShowImage {
    static String imgName;
    ShowImage(String name){
        this.imgName=name;
        int width = 640;    //width of the image
    int height = 480;   //height of the image
    BufferedImage image = null;
    File f = null;

    //read image
    try{
        imgName="C:\\Users\\SIMRAN\\Documents\\NetBeansProjects\\Drone_Mini\\"+imgName+".jpg";
        
      f = new File(imgName); //image file path
      image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
      image = ImageIO.read(f);
      System.out.println("Reading complete.");
      ImageIcon icon=new ImageIcon(image);
        JFrame frame=new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(width,height);
        JLabel lbl=new JLabel();
        lbl.setIcon(icon);
        frame.add(lbl);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
      //DisplayImage abc=new DisplayImage();
    }catch(IOException e){
      System.out.println("Error: No Suspect Found in this time slot!");
    }
    }
}
