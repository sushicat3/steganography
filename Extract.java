import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.*;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

/*
   -  program takes a filename of the stego-image and the
   number of bits used for the hidden image and extracts a
   hidden image from the stego-image. 
*/

class Extract {
   BufferedImage image;
   int width;
   int height;
   int hb;
   String filename; 
   
   public Extract(String filename, int hb) {
      try {
         File stego = new File(filename);
         image = ImageIO.read(stego);
         width = image.getWidth();
         height = image.getHeight();
         
         int count = 0;
         int r, g, b;
         
         for(int i=0; i<height; i++){
         
            for(int j=0; j<width; j++){
            
               count++;
               Color c = new Color(image.getRGB(j, i));
               r = ( c.getRed() & (pow(2, hb) - 1) ) << (8-hb); 
               g = ( c.getGreen() & (pow(2, hb) - 1) ) << (8-hb);
               b = ( c.getBlue() & (pow(2, hb) - 1) ) << (8-hb);
               Color newColor = new Color(r, g, b);
               image.setRGB(j,i,newColor.getRGB());
            }
            
            File output = new File("finalhidden.jpg");
            ImageIO.write(image, "jpg", output);
            
         }
         
      } catch (Exception e) { System.out.println("something went wrong"); }
   }
   
   public static int pow(int x, int y) {
		int prod = 1;
		for (int i = 0; i<y; i++) {
			prod = (prod * x);
		}
		return prod;
	}
   
   static public void main(String args[]) throws Exception 
   {
      Scanner in = new Scanner(System.in);
      System.out.println("Enter filename: ");
      String s = in.nextLine();
      System.out.println("Enter stego bits (1-7): ");
      int x = in.nextInt();
      Extract obj = new Extract(s, x);
   }
}