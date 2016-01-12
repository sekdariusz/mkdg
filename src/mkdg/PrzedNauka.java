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
   
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private boolean klikniecie = false;
    
    private ArrayList<Point> clickedFields = new ArrayList();
    
    public PrzedNauka (final int tilesize) {
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
        
         if(klikniecie== false){
          Point p1 = new Point(0, 0);
          g.setColor(Color.black);
          g.fillRect(p1.x*tilesize, p1.y*tilesize, tilesize, tilesize);
          Point p2 = new Point(0, 1);
          g.setColor(Color.black);
          g.fillRect(p2.x*tilesize, p2.y*tilesize, tilesize, tilesize);
          Point p3 = new Point(2, 2);
          g.setColor(Color.black);
          g.fillRect(p3.x*tilesize, p3.y*tilesize, tilesize, tilesize);
          Point p4 = new Point(1, 3);
          g.setColor(Color.black);
          g.fillRect(p4.x*tilesize, p4.y*tilesize, tilesize, tilesize);
          Point p5 = new Point(4, 4);
          g.setColor(Color.black);
          g.fillRect(p5.x*tilesize, p5.y*tilesize, tilesize, tilesize);
          Point p6 = new Point(1, 1);
          g.setColor(Color.black);
          g.fillRect(p6.x*tilesize, p6.y*tilesize, tilesize, tilesize);
          Point p7 = new Point(1, 2);
          g.setColor(Color.black);
          g.fillRect(p7.x*tilesize, p7.y*tilesize, tilesize, tilesize);
          Point p8 = new Point(3, 4);
          g.setColor(Color.black);
          g.fillRect(p8.x*tilesize, p8.y*tilesize, tilesize, tilesize);
                      
        }
      
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
        
        if (klikniecie == false){
            array[0][0] = 1;
            array[1][0] = 1;          
            array[2][2] = 1;  
            array[3][1] = 1;
            array[4][4] = 1;          
            array[1][1] = 1; 
            array[2][1] = 1;          
            array[4][3] = 1; 
       
        }
        else if (klikniecie == true){
        for(Point p: clickedFields) {
              if(p.y == 0 && p.x == 0) {
                array[0][0] = 1;
            } else if (p.y == 1 && p.x == 0) {
                array[1][0] = 1;
            } else if (p.y == 2 && p.x == 0) {
                array[2][0] = 1;
            } else if (p.y == 3 && p.x == 0) {
                array[3][0] = 1;
            } else if (p.y == 4 && p.x == 0) {
                array[4][0] = 1;
            } 
              else if (p.y == 0 && p.x == 1) {
                array[0][1] = 1;
            } else if (p.y == 1 && p.x == 1) {
                array[1][1] = 1;
            } else if (p.y == 2 && p.x == 1) {
                array[2][1] = 1;
            } else if (p.y == 3 && p.x == 1) {
                array[3][1] = 1;
            } else if (p.y == 4 && p.x == 1) {
                array[4][1] = 1;
            } 
              else if (p.y == 0 && p.x == 2) {
                array[0][2] = 1;
            } else if (p.y == 1 && p.x == 2) {
                array[1][2] = 1;
            } else if (p.y == 2 && p.x == 2) {
                array[2][2] = 1;
            } else if (p.y == 3 && p.x == 2) {
                array[3][2] = 1;
            } else if (p.y == 4 && p.x == 2) {
                array[4][2] = 1;
            }
             else if (p.y == 0 && p.x == 3) {
                array[0][3] = 1;
            } else if (p.y == 1 && p.x == 3) {
                array[1][3] = 1;
            } else if (p.y == 2 && p.x == 3) {
                array[2][3] = 1;
            } else if (p.y == 3 && p.x == 3) {
                array[3][3] = 1;
            } else if (p.y == 4 && p.x == 3) {
                array[4][3] = 1;
            }
              else if (p.y == 0 && p.x == 4) {
                array[0][4] = 1;
            } else if (p.y == 1 && p.x == 4) {
                array[1][4] = 1;
            } else if (p.y == 2 && p.x == 4) {
                array[2][4] = 1;
            } else if (p.y == 3 && p.x == 4) {
                array[3][4] = 1;
            } else if (p.y == 4 && p.x == 4) {
                array[4][4] = 1;
            }
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
