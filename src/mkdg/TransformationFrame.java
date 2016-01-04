/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkdg;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.awt.image.ImageObserver.HEIGHT;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.w3c.dom.css.Rect;

/**
 *
 * @author daroslav
 */
public class TransformationFrame extends javax.swing.JFrame implements ChangeListener, ActionListener {

    
    private FastRGB rgbModel;
    private int[][] structuralElement;
    private int[][] processedImage;
    private int width;
    private int height;
    
    private JSlider thresholdSlider;
    private JButton dilationButton;
    private JButton erosionButton;
    private TransformationCanvas originalCanvas;
    private TransformationCanvas afterProcessCanvas;
    private GridBagConstraints c;
    
    private JButton dilationNextButton;    
    private JButton erosionNextButton;
    
    private JButton autoDilationNextButton;    
    private JButton autoErosionNextButton;
    
    private Point structuralElementPosition = new Point(0,-1);

    /**
     * Creates new form TransformationFrame
     */
    public TransformationFrame(FastRGB rgbModel, int[][] structuralElement, int width, int height) {
        this.rgbModel = rgbModel;
        this.structuralElement = structuralElement;
        this.width = width;
        this.height = height;
        
        System.out.println("w: " + width + " h: " + height);
        
        initComponents();
        
        binaryImagePanel.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        c.insets = new Insets(6,6,6,6);

        showBinaryImage();
        initAditionalElements();
      
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        binaryImagePanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        binaryImagePanel.setBorder(new javax.swing.border.MatteBorder(null));

        javax.swing.GroupLayout binaryImagePanelLayout = new javax.swing.GroupLayout(binaryImagePanel);
        binaryImagePanel.setLayout(binaryImagePanelLayout);
        binaryImagePanelLayout.setHorizontalGroup(
            binaryImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 601, Short.MAX_VALUE)
        );
        binaryImagePanelLayout.setVerticalGroup(
            binaryImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 426, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(binaryImagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(binaryImagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void showBinaryImage() {
        originalCanvas = new TransformationCanvas(rgbModel, structuralElement);
        this.setBounds(0,0, width*originalCanvas.getTileSize() + 10, this.getHeight());
        this.invalidate();
        binaryImagePanel.setBounds((this.getWidth() - width*originalCanvas.getTileSize() + 10)/2, 0, width*originalCanvas.getTileSize() + 10, height*originalCanvas.getTileSize());
        binaryImagePanel.invalidate();
        
        originalCanvas.setBounds((binaryImagePanel.getWidth() - width*originalCanvas.getTileSize())/2, 
                         0, 
                         width*originalCanvas.getTileSize(),
                         height*originalCanvas.getTileSize());
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        
        binaryImagePanel.add(originalCanvas, c);
        binaryImagePanel.invalidate();
        originalCanvas.repaint();     
    }
    
    private void showImageAfterProcess(int[][] binaryModelAfterProcess) {
        
        //this.setPreferredSize(new Dimension((int)(width*originalCanvas.getTileSize() * 2 + 20), this.getHeight()));

        if(afterProcessCanvas != null) {
            binaryImagePanel.remove(afterProcessCanvas);
        }
          
        afterProcessCanvas = new TransformationCanvas(binaryModelAfterProcess, structuralElement);
        binaryImagePanel.setBounds(0,0, width*originalCanvas.getTileSize() * 2 + 20, height*originalCanvas.getTileSize());
        
        originalCanvas.setBounds(5, 
                                 0, 
                                 width*originalCanvas.getTileSize(),
                                 height*originalCanvas.getTileSize());
        
        afterProcessCanvas.setBounds(originalCanvas.getWidth() + 10, 
                                     0, 
                                     width*originalCanvas.getTileSize(),
                                     height*originalCanvas.getTileSize());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        binaryImagePanel.add(afterProcessCanvas, c);       
        this.pack();
    }
    
    private void initAditionalElements() {
        thresholdSlider = new JSlider();
        thresholdSlider.setBounds(20, binaryImagePanel.getHeight() + 10, width*originalCanvas.getTileSize() + 10 - 40, 25);
        thresholdSlider.setVisible(true);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        binaryImagePanel.add(thresholdSlider, c);
        
        binaryImagePanel.invalidate();
        thresholdSlider.setMinimum(0);
        thresholdSlider.setMaximum(100);
        thresholdSlider.setValue(20);
        thresholdSlider.setMajorTickSpacing(10);
        thresholdSlider.setPaintTicks(true);
        thresholdSlider.addChangeListener(this);
        
        dilationButton = new JButton();
        dilationButton.setBounds(20, thresholdSlider.getX() + thresholdSlider.getWidth() + 10, width*originalCanvas.getTileSize() + 10 - 40, 25);
        dilationButton.setText("Dylatacja"); 
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        binaryImagePanel.add(dilationButton, c);
        
        dilationButton.addActionListener(this);
        
        erosionButton = new JButton();
        erosionButton.setBounds(20, dilationButton.getX() + dilationButton.getWidth() + 10, width*originalCanvas.getTileSize() + 10 - 40, 25);
        erosionButton.setText("Erozja");
       
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        binaryImagePanel.add(erosionButton, c);
        
        erosionButton.addActionListener(this);
        
        this.pack();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();
        if(source instanceof JSlider) {
            JSlider theJSlider = (JSlider) source;
            rgbModel.setThreshold(theJSlider.getValue());
            originalCanvas.updateBinaryImage();
            processedImage = null;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == dilationButton) {
            
            dilationNextButton = new JButton();
            dilationNextButton.setBounds(20, dilationButton.getX() + dilationButton.getWidth() + 10, width*originalCanvas.getTileSize() + 10 - 40, 25);
            dilationNextButton.setText(">");
       
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 1;
            c.gridy = 2;
            c.weightx = 0.5;
            binaryImagePanel.add(dilationNextButton, c);
            dilationNextButton.addActionListener(this);
            
            autoDilationNextButton = new JButton();
            autoDilationNextButton.setBounds(20, dilationButton.getX() + dilationButton.getWidth() + 10, width*originalCanvas.getTileSize() + 10 - 40, 25);
            autoDilationNextButton.setText("> AUTO");
       
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 2;
            c.gridy = 2;
            binaryImagePanel.add(autoDilationNextButton, c);
            autoDilationNextButton.addActionListener(this);
            
            processedImage = rgbModel.getImageAsBinaryArray();
            dilate(rgbModel.getImageAsBinaryArray(), processedImage, structuralElementPosition);
            showImageAfterProcess(processedImage);
            
            originalCanvas.showStructuralElement();
            
        } else if (source == erosionButton) {
            
            erosionNextButton = new JButton();
            erosionNextButton.setBounds(20, dilationButton.getX() + dilationButton.getWidth() + 10, width*originalCanvas.getTileSize() + 10 - 40, 25);
            erosionNextButton.setText(">");
       
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 1;
            c.gridy = 3;
            c.weightx = 0.5;
            binaryImagePanel.add(erosionNextButton, c);
            erosionNextButton.addActionListener(this);
            
            autoErosionNextButton = new JButton();
            autoErosionNextButton.setBounds(20, dilationButton.getX() + dilationButton.getWidth() + 10, width*originalCanvas.getTileSize() + 10 - 40, 25);
            autoErosionNextButton.setText("> AUTO");
       
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 2;
            c.gridy = 3;
            c.weightx = 0.5;
            binaryImagePanel.add(autoErosionNextButton, c);
            autoErosionNextButton.addActionListener(this);
            
            processedImage = rgbModel.getImageAsBinaryArray();
            erode(rgbModel.getImageAsBinaryArray(), processedImage, structuralElementPosition);
            showImageAfterProcess(processedImage);
            
            originalCanvas.showStructuralElement();
        } else if (source == dilationNextButton) {
            dilate(rgbModel.getImageAsBinaryArray(), processedImage, structuralElementPosition);
            showImageAfterProcess(processedImage);   
        } else if (source == erosionNextButton) {
            erode(rgbModel.getImageAsBinaryArray(), processedImage, structuralElementPosition);
            showImageAfterProcess(processedImage);   
        } else if (source == autoDilationNextButton) {
            new Timer().scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    dilate(rgbModel.getImageAsBinaryArray(), processedImage, structuralElementPosition);
                    showImageAfterProcess(processedImage);
                }
            }, 1*300, 1*200); 
        } else if (source == autoErosionNextButton) {
            new Timer().scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    erode(rgbModel.getImageAsBinaryArray(), processedImage, structuralElementPosition);
                    showImageAfterProcess(processedImage);  
                }
            }, 1*300, 1*200);     
        }  
    }
 
    private void dilate(int[][] originalImage, int[][] processedImage ,Point p){

        int i = p.x;
        int j = p.y;
        
        if(i >= 0 && j >= 0) {
        //for (int i=0; i<image.length; i++){
            //for (int j=0; j<image[i].length; j++){
                if (originalImage[i][j] == 1){
                    if (structuralElement[1][1] == 1) processedImage[i][j] = 1;           
                    
                    if (structuralElement[0][1] == 1 && i>0) processedImage[i-1][j] = 1;
                    if (structuralElement[1][0] == 1 && j>0) processedImage[i][j-1] = 1;
                    if (structuralElement[2][1] == 1 && i+1<originalImage.length) processedImage[i+1][j] = 1;
                    if (structuralElement[1][2] == 1 && j+1<originalImage[i].length) processedImage[i][j+1] = 1;
                    
                    if (structuralElement[0][0] == 1 && i>0 && j>0) processedImage[i-1][j-1] = 1;
                    if (structuralElement[0][2] == 1 && i>0 && j+1<originalImage[i].length) processedImage[i-1][j+1] = 1;
                    if (structuralElement[2][0] == 1 && i+1<originalImage.length && j>0) processedImage[i+1][j-1] = 1;
                    if (structuralElement[2][2] == 1 && i+1<originalImage.length && j+1<originalImage[i].length) processedImage[i+1][j+1] = 1;
                }
            //}
        //}
        }
        
        if (p.y + 1 < originalImage[0].length) {
            p.y++;
        } else {
            if(p.x + 1 < originalImage.length) {
                p.y = 0;
                p.x++;
            } else {
                p.y = 0;
                p.x = 0;
            }
        }
        
        originalCanvas.setStructuralElementPosition(p);
    }
    
    private void erode(int[][] originalImage, int[][] processedImage ,Point p){

        int i = p.x;
        int j = p.y;
        
        if(i >= 0 && j >= 0) {
        //for (int i=0; i<image.length; i++){
            //for (int j=0; j<image[i].length; j++){
                if (originalImage[i][j] == 0){
                    if (structuralElement[1][1] == 1) processedImage[i][j] = 0;           
                    
                    if (structuralElement[0][1] == 1 && i>0) processedImage[i-1][j] = 0;
                    if (structuralElement[1][0] == 1 && j>0) processedImage[i][j-1] = 0;
                    if (structuralElement[2][1] == 1 && i+1<originalImage.length) processedImage[i+1][j] = 0;
                    if (structuralElement[1][2] == 1 && j+1<originalImage[i].length) processedImage[i][j+1] = 0;
                    
                    if (structuralElement[0][0] == 1 && i>0 && j>0) processedImage[i-1][j-1] = 1;
                    if (structuralElement[0][2] == 1 && i>0 && j+1<originalImage[i].length) processedImage[i-1][j+1] = 0;
                    if (structuralElement[2][0] == 1 && i+1<originalImage.length && j>0) processedImage[i+1][j-1] = 0;
                    if (structuralElement[2][2] == 1 && i+1<originalImage.length && j+1<originalImage[i].length) processedImage[i+1][j+1] = 0;
                }
            //}
        //}
        }
        
        if (p.y + 1 < originalImage[0].length) {
            p.y++;
        } else {
            if(p.x + 1 < originalImage.length) {
                p.y = 0;
                p.x++;
            } else {
                p.y = 0;
                p.x = 0;
            }
        }
        
        originalCanvas.setStructuralElementPosition(p);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel binaryImagePanel;
    // End of variables declaration//GEN-END:variables
}
