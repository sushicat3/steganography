import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.*;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

/*
   - program takes the number of bits per pixel that will hold 
   the hidden image and creates a stego-image from files cover.jpg
   and hidden.jpg
*/

class Stego {
   BufferedImage image;
   BufferedImage image0;
   int width;
   int height;
   int width0;
   int height0;
   int hb; 
   
   public Stego(int hb) {
      try {
         File cover = new File("cover.jpg"); // old - huskycover
         File hidden = new File("hidden.jpg"); // old - huskystego
         image = ImageIO.read(cover);
         image0 = ImageIO.read(hidden);
         width = image.getWidth();
         height = image.getHeight();
         width0 = image0.getWidth();
         height0 = image0.getHeight();
         
         int count = 0;
         int r, g, b, r0, g0, b0;
         
         for(int i=0; i<height; i++){
         
            for(int j=0; j<width; j++){
            
               count++;
               Color c = new Color(image.getRGB(j, i));
               Color c0 = new Color(image0.getRGB(j, i));
               r = c.getRed() >> hb << hb;
               g = c.getGreen() >> hb << hb;
               b = c.getBlue() >> hb << hb;
               r0 = c0.getRed() >> (8-hb);
               g0 = c0.getGreen() >> (8-hb);
               b0 = c0.getBlue() >> (8-hb);
               Color newColor = new Color(r+r0, g+g0, b+b0);
               image.setRGB(j,i,newColor.getRGB());
            }
            
            File output = new File("finalstego.jpg");
            ImageIO.write(image, "jpg", output);
         }
         
      } catch (Exception e) { System.out.println("something went wrong"); }
   }
   
   static public void main(String args[]) throws Exception 
   {
      Scanner in = new Scanner(System.in);
      System.out.println("Enter stego bits (1-7): ");
      int x = in.nextInt();
	  Stego obj = new Stego(x);
   }
}