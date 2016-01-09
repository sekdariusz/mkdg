/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkdg;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 *
 * @author daroslav
 */
public class SelectedImageCanvas extends Canvas {
  
    private Image image;
    private Shape shape;
    private Point startDrag, endDrag;
    private ZoomCallback zoomCallback;
    
    SelectedImageCanvas(final Image image, final ZoomCallback zoomCallback) {
        this.image = image;
        this.zoomCallback = zoomCallback;
        this.addMouseListener(new MouseAdapter() {
            
            public void mousePressed(MouseEvent e) {
                startDrag = new Point(e.getX(), e.getY());
                endDrag = startDrag;
                repaint();
            }

            public void mouseReleased(MouseEvent e) {
                shape = makeRectangle(startDrag.x, startDrag.y, e.getX(), e.getY());
                zoomCallback.zoomed(image, startDrag.x, startDrag.y, e.getX(), e.getY());
                startDrag = null;
                endDrag = null;
                repaint();
            }
        });
        
        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                endDrag = new Point(e.getX(), e.getY());
                repaint();
            }
        });
    }
    
    @Override
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, this);
        
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        paintBackground(g2);
        Color[] colors = { Color.YELLOW, Color.MAGENTA, Color.CYAN , Color.RED, Color.BLUE, Color.PINK};
        int colorIndex = 0;

        g2.setStroke(new BasicStroke(4));
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.50f));

        if (shape != null) {
            g2.setPaint(Color.BLACK);
            g2.draw(shape);
            g2.setPaint(colors[(colorIndex++) % 6]);
            g2.fill(shape);
        }

        if (startDrag != null && endDrag != null) {
            g2.setPaint(Color.YELLOW);
            Shape r = makeRectangle(startDrag.x, startDrag.y, endDrag.x, endDrag.y);
            g2.draw(r);
        }
    }
    
    private void paintBackground(Graphics2D g2){
        g2.setPaint(Color.LIGHT_GRAY);
        for (int i = 0; i < getSize().width; i += 10) {
            Shape line = new Line2D.Float(i, 0, i, getSize().height);
            g2.draw(line);
        }

        for (int i = 0; i < getSize().height; i += 10) {
            Shape line = new Line2D.Float(0, i, getSize().width, i);
            g2.draw(line);
        } 
    }
    
    private Rectangle2D.Float makeRectangle(int x1, int y1, int x2, int y2) {
        return new Rectangle2D.Float(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
    }
}
