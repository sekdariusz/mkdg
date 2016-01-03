/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt13;

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
public class ZoomedImageCanvas extends Canvas {
  
    private Image image;
    
    ZoomedImageCanvas(Image image) {
        this.image = image;
    }
    
    @Override
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }
    
    public Image getImage() {
        return image;
    }
}
