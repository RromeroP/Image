/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package image;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Rex
 */
public class Image {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        BufferedImage img = ImageIO.read(new File("src\\images\\mhw.jpg"));

        JFrame frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(img.getWidth() * 2, img.getHeight());
        frame.setVisible(true);

        JPanel pane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0, 0, null);

                WritableRaster raster = img.getRaster();
                Raster image = img.getData();
                
                for (int i = 0; i < img.getWidth(); i++) {
                    for (int j = 0; j < img.getHeight(); j++) {

                        int[] pixel = image.getPixel(i, j, new int[3]);

                        int gray = (pixel[0] + pixel[1] + pixel[2]) / 3;

                        int[] colors = {gray, gray, gray};

                        raster.setPixel(i, j, colors);
                    }
                }

                g.drawImage(img, img.getWidth(), 0, null);
            }
        };

        frame.add(pane);

//        System.out.println("R: " + pixel[0] + " G: " + pixel[1] + " B: " + pixel[2]);
    }

}
