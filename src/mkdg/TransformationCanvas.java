/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkdg;

import java.awt.AlphaComposite;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
//import javafx.scene.control.Slider;
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
    private int[][] structuralElement;
    private Point structuralElementPosition;
    private boolean showStructuralElement = false;
    private int tilesize = 13; //default
        
    public TransformationCanvas(FastRGB rgbModel, int[][] structuralElement) {
        this.structuralElement = structuralElement;
        this.rgbModel = rgbModel;
        this.binaryModel = rgbModel.getImageAsBinaryArray();
        this.width = binaryModel.length;
        this.height = binaryModel[0].length;
    }
    
     public TransformationCanvas(int[][] binaryModel, int[][] structuralElement) {
        this.structuralElement = structuralElement;
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
        
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.80f));
        g2.setColor(Color.yellow);

        if(showStructuralElement) {
            for(int x = 0; x < structuralElement.length; x++) {
                for (int y = 0; y < structuralElement.length; y++) {
                    if(structuralElement[x][y] == 1) {
                        int dx = 0;
                        if(x < 1) {
                            dx = -1;
                        } else if (x > 1) {
                            dx = 1;
                        }
                        int dy = 0;
                        if(y < 1) {
                            dy = -1;
                        } else if (y > 1) {
                            dy = 1;
                        }
                        g.fillRect((structuralElementPosition.x + dx)*tilesize, (structuralElementPosition.y + dy)*tilesize, tilesize, tilesize);
                    }
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
    }
    
    public void updateBinaryImage(int[][] newImage) {
        this.binaryModel = newImage;
        this.repaint();    
    }
     
    public void showStructuralElement() {
        structuralElementPosition = new Point(0,0);
        showStructuralElement = true;
        this.repaint();
    }
    
    public void setStructuralElementPosition(Point newPosition) {
        structuralElementPosition = newPosition;
        this.repaint();
    }
}
