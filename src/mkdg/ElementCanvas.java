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
import java.util.Arrays;
import java.util.Iterator;

/**
 *
 * @author daroslav
 */
public class ElementCanvas extends Canvas {
    
    private int tilesize;
    private int panel;
    
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private boolean klikniecie = false;

    private ArrayList<Point> clickedFields = new ArrayList();
    
    public ElementCanvas (final int tilesize) {
        this.tilesize = tilesize;
        this.addMouseListener(new MouseAdapter() {
            
            public void mouseClicked(MouseEvent e){
                klikniecie = true; 
                int x = e.getX();
                int y = e.getY();
                int ccol;
                int crow;
                ccol = x / tilesize;
                crow = y / tilesize;
                System.out.println("x: " + ccol + " y: "+crow);
                if((ccol < 3) && (crow < 3)) {
                    Point p = new Point(ccol, crow);
                    if(clickedFields.contains(p) && !(p.x == 1 && p.y == 1)) {
                        clickedFields.remove(p);
                    } else if (!(p.x == 1 && p.y == 1)) {
                        clickedFields.add(p);
                    }
                    repaint();  
                }
        }
        });
        
        clickedFields.add(new Point(1,1));
    }
    
    @Override
    public void paint(Graphics g) {
        //Draw Vertical Lines
        g.setColor(Color.black);
        for(int i = 0; i < 3 + 1; i++) {
            x1 = tilesize * i;
            y1 = 0;
            x2 = tilesize * i;
            y2 = tilesize * 3;
            g.drawLine(x1, y1, x2, y2);
        }
        //Draw Horizontal Lines
        g.setColor(Color.black);
        for(int i = 0; i < 3 + 1; i++) {
            x1 = 0;
             y1 = tilesize * i;
            x2 = tilesize * 3;
            y2 = tilesize * i;
            g.drawLine(x1, y1, x2, y2);
        }
        
        
        for(Point p : clickedFields) {
            if( p.getX() == 1 & p.getY() == 1 ){
                g.setColor(Color.red);
                g.fillRect(p.x*tilesize, p.y*tilesize, tilesize, tilesize);
            }
            else {
                g.setColor(Color.black);
                g.fillRect(p.x*tilesize, p.y*tilesize, tilesize, tilesize);
            }
            
        }
        
       
    }
    
    public int[][] getStructuralElementArray() {
        int[][] array = new int[3][3];
        for(int i = 0; i < array.length; i++) {
            for(int j = 0; j<array[0].length; j++) {
                array[i][j] = 0;
            }
        }
        for(Point p: clickedFields) {
            if(p.y == 0 && p.x == 0) {
                array[0][0] = 1;
            } else if (p.y == 1 && p.x == 0) {
                array[1][0] = 1;
            } else if (p.y == 2 && p.x == 0) {
                array[2][0] = 1;
            } else if (p.y == 0 && p.x == 1) {
                array[0][1] = 1;
            } else if (p.y == 1 && p.x == 1) {
                array[1][1] = 1;
            } else if (p.y == 2 && p.x == 1) {
                array[2][1] = 1;
            } else if (p.y == 0 && p.x == 2) {
                array[0][2] = 1;
            } else if (p.y == 1 && p.x == 2) {
                array[1][2] = 1;
            } else if (p.y == 2 && p.x == 2) {
                array[2][2] = 1;
            }
        }
        
        //print
        for(int i = 0; i < array.length; i++) {
            for(int j = 0; j<array[0].length; j++) {
                System.out.print(" "+array[i][j]);
            }
            System.out.println();
        }
        
        return array;
    }
    
}
