/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt13;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author daroslav
 */
public class ElementCanvas extends Canvas {
    
    private int tilesize;
    
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    
    private ArrayList<Point> clickedFields = new ArrayList();
    
    public ElementCanvas (int tilesize) {
        this.tilesize = tilesize;
        this.addMouseListener(new MouseAdapter() {
            
            public void mouseClicked(MouseEvent e){
                int x = e.getX();
                int y = e.getY();
                int ccol;
                int crow;
                ccol = x / tilesize;
                crow = y / tilesize;
                System.out.println("crow: "+crow + " ccol: " + ccol);
                if((ccol < 3) && (crow < 3)) {
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
        
        g.setColor(Color.black);
        for(Point p : clickedFields) {
            g.fillRect(p.x*tilesize, p.y*tilesize, tilesize, tilesize);
        }
    }
    
}