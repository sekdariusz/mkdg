/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkdg;

/**
 *
 * @author daroslav
 */
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;

public class FastRGB {

    private int width;
    private int height;
    private int[] pixels;
    private int threshold = 20;
    private boolean thresholdChanged = false;
    
    private int[][] binaryPixels;

    FastRGB(BufferedImage image){
        pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
        width = image.getWidth();
        height = image.getHeight();
    }

    public int getRGB(int x, int y) {
        int pos = (y * width) + x;
        int rgb = ((int) pixels[pos++]);
        return rgb;
    }
    
    public int[][] getImageAsBinaryArray() {
        
        if(binaryPixels == null || thresholdChanged) {
            thresholdChanged = false;
            binaryPixels = new int[width][height];
            int decisionLimit = (int)(-16777216.0/(100.0/(float)threshold));
        
            for(int x = 0; x < width; x++) {
                for(int y = 0; y < height; y++) {
                    if(getRGB(x,y) < decisionLimit) {
                        binaryPixels[x][y] = 1;
                    } else {
                        binaryPixels[x][y] = 0;
                    }
                }
            }
        }
        
        return binaryPixels.clone(); 
    }
    
    public void setThreshold(int value) {
        this.threshold = value;
        thresholdChanged = true;
    }
    
}
