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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author Rabarbar
 */
public class GamePictureCanvas extends Canvas {
    private int tilesize;
    private int width;
    private int height;
    private int[][] tilesArray;
    boolean clickable = false;
    
    private ArrayList<Point> clickedFields = new ArrayList();
    
    public GamePictureCanvas (final int tilesize, int[][] tilesArray) {
        this.tilesize = tilesize;
        this.tilesArray = tilesArray;
        this.width = tilesArray.length;
        this.height = tilesArray[0].length;
    }   
    
    public GamePictureCanvas (final int tilesize, int[][] tilesArray, boolean clickable) {
        this.tilesize = tilesize;
        this.tilesArray = tilesArray;
        this.width = tilesArray.length;
        this.height = tilesArray[0].length;
        this.clickable = clickable;
        
        this.addMouseListener(new MouseAdapter() {       
            @Override
            public void mouseClicked(MouseEvent e){
                int x = e.getX();
                int y = e.getY();
                int ccol;
                int crow;
                ccol = x / tilesize;
                crow = y / tilesize;
                if((ccol < width) && (crow < height)) {
                    Point p = new Point(ccol, crow);
                    if(clickedFields.contains(p)) {
                        clickedFields.remove(p);
                        tilesArray[p.y][p.x] = 0;
                    } else {
                        clickedFields.add(p);
                        tilesArray[p.y][p.x] = 1;
                    }
                    repaint();  
                }
        }
        });
    }
    
    @Override
    public void paint(Graphics g) {

        g.setColor(Color.white);
        g.fillRect(0, 0, width * tilesize, height * tilesize);

        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                if(tilesArray[y][x] == 1) {
                    g.setColor(Color.black);
                    g.fillRect(x*tilesize, y*tilesize, tilesize, tilesize);
                }
                if(tilesArray[y][x] == 2) {
                    g.setColor(Color.DARK_GRAY); //dilation color
                    g.fillRect(x*tilesize, y*tilesize, tilesize, tilesize);
                }
                if(tilesArray[y][x] == 3) {
                    g.setColor(Color.LIGHT_GRAY); //erosion color
                    g.fillRect(x*tilesize, y*tilesize, tilesize, tilesize);
                }
                if(tilesArray[y][x] == 4) {
                    g.setColor(Color.RED); //erosion color
                    g.fillRect(x*tilesize, y*tilesize, tilesize, tilesize);
                }
            }           
        }
        
        //Draw Vertical Lines
        g.setColor(Color.black);
        for(int i = 0; i <= width; i++) {
            int x1 = tilesize * i;
            int y1 = 0;
            int x2 = tilesize * i;
            int y2 = tilesize * width;
            g.drawLine(x1, y1, x2, y2);
        }
        //Draw Horizontal Lines
        g.setColor(Color.black);
        for(int i = 0; i <= height; i++) {
            int x1 = 0;
            int y1 = tilesize * i;
            int x2 = tilesize * width;
            int y2 = tilesize * i;
            g.drawLine(x1, y1, x2, y2);
        }
          
    }
    
    public int[][] getTilesArray() {
        return tilesArray;
    }

}
