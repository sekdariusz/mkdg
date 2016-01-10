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
public class PrzedNauka extends Canvas {
    
    private int tilesize;
    private int panel;
    
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private boolean klikniecie = false;
    
    private ArrayList<Point> clickedFields = new ArrayList();
    
    public PrzedNauka (final int tilesize, int panel) {
        this.tilesize = tilesize;
        this.panel = panel;
        this.addMouseListener(new MouseAdapter() {
            
            public void mouseClicked(MouseEvent e){
                klikniecie = true; 
                int x = e.getX();
                int y = e.getY();
                int ccol;
                int crow;
                ccol = x / tilesize;
                crow = y / tilesize;
                System.out.println("crow: "+crow + " ccol: " + ccol);
                if((ccol < 5) && (crow < 5)) {
                    Point p = new Point(ccol, crow);
                    if(clickedFields.contains(p)) {
                        clickedFields.remove(p);
                    } else {
                        clickedFields.add(p);
                    }
                    repaint();  
                }
        }
        });
    }

   
    
    @Override
    public void paint(Graphics g) {
        
         if(panel==2 & klikniecie== false){
          Point p1 = new Point(0, 0);
          g.setColor(Color.black);
          g.fillRect(p1.x*tilesize, p1.x*tilesize, tilesize, tilesize);
          Point p2 = new Point(1, 1);
          g.setColor(Color.black);
          g.fillRect(p2.x*tilesize, p2.x*tilesize, tilesize, tilesize);
          Point p3 = new Point(2, 2);
          g.setColor(Color.black);
          g.fillRect(p3.x*tilesize, p3.x*tilesize, tilesize, tilesize);
          Point p4 = new Point(3, 3);
          g.setColor(Color.black);
          g.fillRect(p4.x*tilesize, p4.x*tilesize, tilesize, tilesize);
          Point p5 = new Point(4, 4);
          g.setColor(Color.black);
          g.fillRect(p5.x*tilesize, p5.x*tilesize, tilesize, tilesize);
                      
        }
        //Draw Vertical Lines
       /* g.setColor(Color.black);
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
        */
        
        g.setColor(Color.black);
        for(Point p : clickedFields) {
            g.fillRect(p.x*tilesize, p.y*tilesize, tilesize, tilesize);
        }
        
       
    }
    
    public int[][] getprzedArray() {
        int[][] array = new int[5][5];
        for(int i = 0; i < array.length; i++) {
            for(int j = 0; j<array[0].length; j++) {
                array[i][j] = 0;
            }
        }
        for(Point p: clickedFields) {
              if(p.x == 0 && p.y == 0) {
                array[0][0] = 1;
            } else if (p.x == 1 && p.y == 0) {
                array[1][0] = 1;
            } else if (p.x == 2 && p.y == 0) {
                array[2][0] = 1;
            } else if (p.x == 3 && p.y == 0) {
                array[3][0] = 1;
            } else if (p.x == 4 && p.y == 0) {
                array[4][0] = 1;
            } 
              else if (p.x == 0 && p.y == 1) {
                array[0][1] = 1;
            } else if (p.x == 1 && p.y == 1) {
                array[1][1] = 1;
            } else if (p.x == 2 && p.y == 1) {
                array[2][1] = 1;
            } else if (p.x == 3 && p.y == 1) {
                array[3][1] = 1;
            } else if (p.x == 4 && p.y == 1) {
                array[4][1] = 1;
            } 
              else if (p.x == 0 && p.y == 2) {
                array[0][2] = 1;
            } else if (p.x == 1 && p.y == 2) {
                array[1][2] = 1;
            } else if (p.x == 2 && p.y == 2) {
                array[2][2] = 1;
            } else if (p.x == 3 && p.y == 2) {
                array[3][2] = 1;
            } else if (p.x == 4 && p.y == 2) {
                array[4][2] = 1;
            }
             else if (p.x == 0 && p.y == 3) {
                array[0][3] = 1;
            } else if (p.x == 1 && p.y == 3) {
                array[1][3] = 1;
            } else if (p.x == 2 && p.y == 3) {
                array[2][3] = 1;
            } else if (p.x == 3 && p.y == 3) {
                array[3][3] = 1;
            } else if (p.x == 4 && p.y == 3) {
                array[4][3] = 1;
            }
              else if (p.x == 0 && p.y == 4) {
                array[0][4] = 1;
            } else if (p.x == 1 && p.y == 4) {
                array[1][4] = 1;
            } else if (p.x == 2 && p.y == 4) {
                array[2][4] = 1;
            } else if (p.x == 3 && p.y == 4) {
                array[3][4] = 1;
            } else if (p.x == 4 && p.y == 4) {
                array[4][4] = 1;
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
