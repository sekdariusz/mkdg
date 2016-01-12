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
    private Point structuralElementPosition = new Point(0,0); //startPoint
    private boolean showStructuralElement = false;
    private int tilesize = 13; //default
    private int zakladka = 0;
        
    public TransformationCanvas(FastRGB rgbModel, int[][] structuralElement) {
        this.structuralElement = structuralElement;
        this.rgbModel = rgbModel;
        this.binaryModel = rgbModel.getImageAsBinaryArray();
        this.width = binaryModel.length;
        this.height = binaryModel[0].length;
    }
    
     public TransformationCanvas(int[][] binaryModel, int[][] structuralElement, int zakladka) {
        this.structuralElement = structuralElement;
        this.binaryModel = binaryModel;
        this.width = binaryModel.length;
        this.height = binaryModel[0].length;
        this.zakladka = zakladka;
    }
    
    @Override
    public void paint(Graphics g) {
                
        g.setColor(Color.white);
        g.fillRect(0, 0, width * tilesize, height * tilesize);
        
        g.setColor(Color.black);
        for(int x = 0; x < width; x++) {
            
            if (zakladka == 0){
            
            for(int y = 0; y < height; y++) {
                if(binaryModel[x][y] == 1) {
                    g.setColor(Color.black);
                    g.fillRect(x*tilesize, y*tilesize, tilesize, tilesize);
                }
                if(binaryModel[x][y] == 2) {
                    g.setColor(Color.DARK_GRAY); //dilation color
                    g.fillRect(x*tilesize, y*tilesize, tilesize, tilesize);
                }
                if(binaryModel[x][y] == 3) {
                    g.setColor(Color.LIGHT_GRAY); //erosion color
                    g.fillRect(x*tilesize, y*tilesize, tilesize, tilesize);
                }
            }}
            else if (zakladka ==1){
                
                tilesize = 35;
                for(int y = 0; y < height; y++) {
                if(binaryModel[y][x] == 1) {
                    g.setColor(Color.black);
                    g.fillRect(x*tilesize, y*tilesize, tilesize, tilesize);
                }
                if(binaryModel[x][y] == 2) {
                    g.setColor(Color.DARK_GRAY); //dilation color
                    g.fillRect(x*tilesize, y*tilesize, tilesize, tilesize);
                }
                if(binaryModel[x][y] == 3) {
                    g.setColor(Color.LIGHT_GRAY); //erosion color
                    g.fillRect(x*tilesize, y*tilesize, tilesize, tilesize);
                }
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
                        g.fillRect((structuralElementPosition.x + dy)*tilesize, (structuralElementPosition.y + dx)*tilesize, tilesize, tilesize);
                    }
                }
            }
            g2.setColor(Color.red);
            g.fillRect((structuralElementPosition.x)*tilesize, (structuralElementPosition.y)*tilesize, tilesize, tilesize);
            
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
        showStructuralElement = true;
        this.repaint();
    }
    
    public void hideStructuralElement() {
        showStructuralElement = false;
        this.repaint();
    }
    
    public void setStructuralElementPosition(Point newPosition) {
        structuralElementPosition = newPosition;
        this.repaint();
    }
}
