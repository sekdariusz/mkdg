/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkdg;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javafx.scene.control.Slider;
import javax.swing.JSlider;

/**
 *
 * @author daroslav
 */
public class TransformationCanvas extends Canvas {
    
    private FastRGB rgbModel;
    private int[][] binaryModel;
    private int width;
    private int height;
    private int tilesize = 10; //default
        
    public TransformationCanvas(FastRGB rgbModel) {
        this.rgbModel = rgbModel;
        this.binaryModel = rgbModel.getImageAsBinaryArray();
        this.width = binaryModel.length;
        this.height = binaryModel[0].length;
    }
    
     public TransformationCanvas(int[][] binaryModel) {
        this.binaryModel = binaryModel;
        this.width = binaryModel.length;
        this.height = binaryModel[0].length;
    }
    
    @Override
    public void paint(Graphics g) {
                
        g.setColor(Color.white);
        g.fillRect(0, 0, width * tilesize, height * tilesize);
        
        g.setColor(Color.black);
        for(int x = 0; x < width; x++) {
            
            for(int y = 0; y < height; y++) {
                if(binaryModel[x][y] == 1) {
                    g.fillRect(x*tilesize, y*tilesize, tilesize, tilesize);
                }
            }
        }
        
    }
    
    public void setTileSize(int newSize) {
        this.tilesize = newSize;
        this.repaint();
    }
    
    public int getTileSize() {
        return this.tilesize;
    }
    
    public void updateBinaryImage() {
        this.binaryModel = rgbModel.getImageAsBinaryArray();
        this.repaint();
        
        new java.util.Timer().schedule( 
            new java.util.TimerTask() {
                @Override
                public void run() {
                    TransformationCanvas.this.repaint();
                }
            }, 
        100);
    }
    
     public void updateBinaryImage(int[][] newImage) {
        this.binaryModel = newImage;
        this.repaint();
        
         new java.util.Timer().schedule( 
            new java.util.TimerTask() {
                @Override
                public void run() {
                    TransformationCanvas.this.repaint();
                }
            }, 
        100);
    }
}
