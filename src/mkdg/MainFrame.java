/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkdg;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;



/**
 *
 * @author daroslav
 */
public class MainFrame extends javax.swing.JFrame implements ZoomCallback {
    
    private String lastChoosenPath;
    private ElementCanvas elementCanvas;
    private ElementNauka elementNauka;
    private PrzedNauka przedNauka;
    private BufferedImage zoomedImage;
    // agata - wiem, wiem, produkuję wiecej kodu, ale inaczej nie dam rady
    private TransformationCanvas obrazPrzed;
    private int[][] processedImage2;
    private int[][] elementNauka2;
    private int[][] obrazPrzed2;
    private TransformationCanvas afterProcessCanvas2;
    private Point structuralElementPosition2 = new Point(0,-1);
    private Timer autoDilationTimer;
    private Timer autoErosionTimer;
    private int kliknieciestart = 0;
    private final int DILATION_COLOR = 2;
    private final int EROSION_COLOR = 3;
    
    //private ElementCanvas elementGame;
    private GamePictureCanvas elementGame;
    GamePictureCanvas gamePictureAfter;
       
    private boolean loadPreviousConfiguration = false;
    
    
        /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        initChooseElement();
        //initChooseElementGame();
        ElementPan2();
        przed();
        initGameElements();
        
        radioErozja.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                next.setEnabled(false);
                structuralElementPosition2 = new Point(0,-1);
                przed.removeAll();
                po.removeAll();
                elementPanel2.removeAll();
                komentarzText.setText(" ");  
                kliknieciestart = 0;
                step = 1;
        
                afterProcessCanvas2 = null;
        
                ElementPan2();
                przed();
            }
        });
        
        radioDylatacja.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                next.setEnabled(false);
                structuralElementPosition2 = new Point(0,-1);
                przed.removeAll();
                po.removeAll();
                elementPanel2.removeAll();
                komentarzText.setText(" ");  
                kliknieciestart = 0;
                step = 1;
        
                afterProcessCanvas2 = null;
        
                ElementPan2();
                przed();
            }
        });
        
        Properties prop = new Properties();
        InputStream is = null;
        File f = new File("user.properties");
        if(f.exists()) {
            try {
                is = new FileInputStream(f);
                prop.load(is);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            String file = prop.getProperty("file", null);
            String dir = prop.getProperty("dir", null);
            if(file != null) {
                filePathEditText.setText(file);
                loadPreviousConfiguration = true;
                loadImageButton.doClick();
            }
            if(dir != null) {
                lastChoosenPath = dir;
            }
        }
    }
    
    private void initChooseElement() {
        int elementSize = ((int)((float)elementPanel.getHeight()-40)/3);
        elementCanvas = new ElementCanvas(elementSize);
        elementCanvas.setBounds((elementPanel.getWidth() - (elementSize+1)*3)/2, 20, (elementSize+1)*3, (elementSize+1)*3);
        elementPanel.add(elementCanvas);
    }
    
//    private void initChooseElementGame() {
//        int elementSize = ((int)((float)gameElementPanel.getHeight()-40)/3);
//        elementGame = new ElementCanvas(elementSize);
//        elementGame.setBounds((gameElementPanel.getWidth() - (elementSize+1)*3)/2, 20, (elementSize+1)*3, (elementSize+1)*3);
//        gameElementPanel.add(elementGame);
//    }

    
    // macierz dla nauki
    
    private void ElementPan2() {
       
        int elSize = ((int)((float)elementPanel2.getHeight()-40)/3);
        elementNauka = new ElementNauka(elSize);
        elementNauka.setBounds((elementPanel2.getWidth() - (elSize+1)*3)/2, 20, (elSize+1)*3, (elSize+1)*3);
        elementPanel2.add(elementNauka);
    }
    
     private void przed() {
      
        int elSize = ((int)((float)przed.getHeight()-40)/5);
        przedNauka = new PrzedNauka(elSize);
        przedNauka.setBounds((przed.getWidth() - (elSize+1)*5)/2, 20, (elSize+1)*5, (elSize+1)*5);
        przed.add(przedNauka);
    }
     
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        erodyl = new javax.swing.ButtonGroup();
        InfoEro = new javax.swing.JFrame();
        InfoDyla = new javax.swing.JFrame();
        InfoEle = new javax.swing.JFrame();
        tabbedPane = new javax.swing.JTabbedPane();
        programPanel = new javax.swing.JPanel();
        radioDylatacja = new javax.swing.JRadioButton();
        radioErozja = new javax.swing.JRadioButton();
        InfoErozja = new javax.swing.JButton();
        InfoDylatacja = new javax.swing.JButton();
        elementPanel2 = new javax.swing.JPanel();
        InfoElement = new javax.swing.JButton();
        przed = new javax.swing.JPanel();
        po = new javax.swing.JPanel();
        start = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        komentarzText = new javax.swing.JEditorPane();
        restart = new javax.swing.JButton();
        next = new javax.swing.JButton();
        teachPanel = new javax.swing.JPanel();
        binaryImagePanel = new javax.swing.JPanel();
        zoomedImagePanel = new javax.swing.JPanel();
        elementPanel = new javax.swing.JPanel();
        processButton = new javax.swing.JButton();
        filePathEditText = new javax.swing.JTextField();
        loadImageButton = new javax.swing.JButton();
        gamePanel = new javax.swing.JPanel();
        gameDescriptionLabel = new javax.swing.JLabel();
        MethodCbBox = new javax.swing.JComboBox<>();
        gamePicBeforePanel = new javax.swing.JPanel();
        checkButton = new javax.swing.JButton();
        gameElementPanel = new javax.swing.JPanel();
        gamePicAfterPanel = new javax.swing.JPanel();
        resetButton = new javax.swing.JButton();
        gameResultPanel = new javax.swing.JPanel();
        blackCorrectLabel = new javax.swing.JLabel();
        blackWrongLabel = new javax.swing.JLabel();
        whiteWrongLabel = new javax.swing.JLabel();
        whiteCorrectLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        blackNumberLabel = new javax.swing.JLabel();
        whiteNumberLabel = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        correctSumLabel = new javax.swing.JLabel();
        wrongSumLabel = new javax.swing.JLabel();
        tilesNumberLabel = new javax.swing.JLabel();
        gameResultLabel = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout InfoEroLayout = new javax.swing.GroupLayout(InfoEro.getContentPane());
        InfoEro.getContentPane().setLayout(InfoEroLayout);
        InfoEroLayout.setHorizontalGroup(
            InfoEroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        InfoEroLayout.setVerticalGroup(
            InfoEroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout InfoDylaLayout = new javax.swing.GroupLayout(InfoDyla.getContentPane());
        InfoDyla.getContentPane().setLayout(InfoDylaLayout);
        InfoDylaLayout.setHorizontalGroup(
            InfoDylaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        InfoDylaLayout.setVerticalGroup(
            InfoDylaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout InfoEleLayout = new javax.swing.GroupLayout(InfoEle.getContentPane());
        InfoEle.getContentPane().setLayout(InfoEleLayout);
        InfoEleLayout.setHorizontalGroup(
            InfoEleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        InfoEleLayout.setVerticalGroup(
            InfoEleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        erodyl.add(radioDylatacja);
        radioDylatacja.setLabel("Dylatacja");
        radioDylatacja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioDylatacjaActionPerformed(evt);
            }
        });

        erodyl.add(radioErozja);
        radioErozja.setSelected(true);
        radioErozja.setLabel("Erozja");
        radioErozja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioErozjaActionPerformed(evt);
            }
        });

        InfoErozja.setText("Info Erozja");
        InfoErozja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InfoErozjaActionPerformed(evt);
            }
        });

        InfoDylatacja.setText("Info Dylatacja");
        InfoDylatacja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InfoDylatacjaActionPerformed(evt);
            }
        });

        elementPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Element strukturalny", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));
        elementPanel2.setToolTipText("");

        javax.swing.GroupLayout elementPanel2Layout = new javax.swing.GroupLayout(elementPanel2);
        elementPanel2.setLayout(elementPanel2Layout);
        elementPanel2Layout.setHorizontalGroup(
            elementPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 172, Short.MAX_VALUE)
        );
        elementPanel2Layout.setVerticalGroup(
            elementPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 126, Short.MAX_VALUE)
        );

        InfoElement.setText("Info Element");
        InfoElement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InfoElementActionPerformed(evt);
            }
        });

        przed.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Przed", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        javax.swing.GroupLayout przedLayout = new javax.swing.GroupLayout(przed);
        przed.setLayout(przedLayout);
        przedLayout.setHorizontalGroup(
            przedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 216, Short.MAX_VALUE)
        );
        przedLayout.setVerticalGroup(
            przedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 191, Short.MAX_VALUE)
        );

        po.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Po", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        javax.swing.GroupLayout poLayout = new javax.swing.GroupLayout(po);
        po.setLayout(poLayout);
        poLayout.setHorizontalGroup(
            poLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 216, Short.MAX_VALUE)
        );
        poLayout.setVerticalGroup(
            poLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 191, Short.MAX_VALUE)
        );

        start.setText("START");
        start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startActionPerformed(evt);
            }
        });

        komentarzText.setEditable(false);
        komentarzText.setAutoscrolls(false);
        komentarzText.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane2.setViewportView(komentarzText);

        restart.setText("RESTART");
        restart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restartActionPerformed(evt);
            }
        });

        next.setText(">");
        next.setEnabled(false);
        next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout programPanelLayout = new javax.swing.GroupLayout(programPanel);
        programPanel.setLayout(programPanelLayout);
        programPanelLayout.setHorizontalGroup(
            programPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(programPanelLayout.createSequentialGroup()
                .addGroup(programPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(programPanelLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(programPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(radioErozja)
                            .addComponent(radioDylatacja))
                        .addGap(18, 18, 18)
                        .addGroup(programPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(InfoDylatacja)
                            .addComponent(InfoErozja))
                        .addGap(66, 66, 66)
                        .addComponent(elementPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)
                        .addComponent(InfoElement))
                    .addGroup(programPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(przed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(programPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(programPanelLayout.createSequentialGroup()
                                .addComponent(start)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(next)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(restart))
                            .addGroup(programPanelLayout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(po, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        programPanelLayout.setVerticalGroup(
            programPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(programPanelLayout.createSequentialGroup()
                .addGroup(programPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(programPanelLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(programPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(radioErozja)
                            .addComponent(InfoErozja))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(programPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(radioDylatacja)
                            .addComponent(InfoDylatacja)))
                    .addGroup(programPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(elementPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(programPanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(InfoElement)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(programPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(start)
                    .addComponent(next)
                    .addComponent(restart))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(programPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(przed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(po, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );

        tabbedPane.addTab("Nauka", programPanel);

        binaryImagePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Obraz", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));

        javax.swing.GroupLayout binaryImagePanelLayout = new javax.swing.GroupLayout(binaryImagePanel);
        binaryImagePanel.setLayout(binaryImagePanelLayout);
        binaryImagePanelLayout.setHorizontalGroup(
            binaryImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        binaryImagePanelLayout.setVerticalGroup(
            binaryImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 312, Short.MAX_VALUE)
        );

        zoomedImagePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Powiększony fragment", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));

        javax.swing.GroupLayout zoomedImagePanelLayout = new javax.swing.GroupLayout(zoomedImagePanel);
        zoomedImagePanel.setLayout(zoomedImagePanelLayout);
        zoomedImagePanelLayout.setHorizontalGroup(
            zoomedImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 429, Short.MAX_VALUE)
        );
        zoomedImagePanelLayout.setVerticalGroup(
            zoomedImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 312, Short.MAX_VALUE)
        );

        elementPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Stwórz element strukturalny", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        javax.swing.GroupLayout elementPanelLayout = new javax.swing.GroupLayout(elementPanel);
        elementPanel.setLayout(elementPanelLayout);
        elementPanelLayout.setHorizontalGroup(
            elementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 353, Short.MAX_VALUE)
        );
        elementPanelLayout.setVerticalGroup(
            elementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 126, Short.MAX_VALUE)
        );

        processButton.setText("Przetwórz obraz");
        processButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                processButtonActionPerformed(evt);
            }
        });

        filePathEditText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filePathEditTextActionPerformed(evt);
            }
        });

        loadImageButton.setText("Wczytaj dowolny obraz");
        loadImageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadImageButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout teachPanelLayout = new javax.swing.GroupLayout(teachPanel);
        teachPanel.setLayout(teachPanelLayout);
        teachPanelLayout.setHorizontalGroup(
            teachPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(teachPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(teachPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(elementPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(binaryImagePanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(teachPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(zoomedImagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(processButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(loadImageButton, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                    .addComponent(filePathEditText, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        teachPanelLayout.setVerticalGroup(
            teachPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(teachPanelLayout.createSequentialGroup()
                .addGroup(teachPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(binaryImagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(zoomedImagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(teachPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(teachPanelLayout.createSequentialGroup()
                        .addComponent(filePathEditText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(loadImageButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(processButton))
                    .addComponent(elementPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        tabbedPane.addTab("Program", teachPanel);

        gameDescriptionLabel.setText("<html>Zasady gry:<br><br>Wybierz rodzaj przekształcenia i odgadnij jaki obraz powstanie<br>w wyniku dylatacji bądź erozji, mając do dyspozycji obraz wejściowy<br>  i element strukturalny.");
        gameDescriptionLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        MethodCbBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dylatacja", "Erozja" }));
        MethodCbBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MethodCbBoxActionPerformed(evt);
            }
        });

        gamePicBeforePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Obraz wejściowy", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        gamePicBeforePanel.setPreferredSize(new java.awt.Dimension(380, 380));

        javax.swing.GroupLayout gamePicBeforePanelLayout = new javax.swing.GroupLayout(gamePicBeforePanel);
        gamePicBeforePanel.setLayout(gamePicBeforePanelLayout);
        gamePicBeforePanelLayout.setHorizontalGroup(
            gamePicBeforePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 393, Short.MAX_VALUE)
        );
        gamePicBeforePanelLayout.setVerticalGroup(
            gamePicBeforePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        checkButton.setText("SPRAWDŹ");
        checkButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkButtonActionPerformed(evt);
            }
        });

        gameElementPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Element strukturalny", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        javax.swing.GroupLayout gameElementPanelLayout = new javax.swing.GroupLayout(gameElementPanel);
        gameElementPanel.setLayout(gameElementPanelLayout);
        gameElementPanelLayout.setHorizontalGroup(
            gameElementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 197, Short.MAX_VALUE)
        );
        gameElementPanelLayout.setVerticalGroup(
            gameElementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gamePicAfterPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Obraz wyjściowy", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        gamePicAfterPanel.setPreferredSize(new java.awt.Dimension(380, 380));

        javax.swing.GroupLayout gamePicAfterPanelLayout = new javax.swing.GroupLayout(gamePicAfterPanel);
        gamePicAfterPanel.setLayout(gamePicAfterPanelLayout);
        gamePicAfterPanelLayout.setHorizontalGroup(
            gamePicAfterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 393, Short.MAX_VALUE)
        );
        gamePicAfterPanelLayout.setVerticalGroup(
            gamePicAfterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 290, Short.MAX_VALUE)
        );

        resetButton.setText("RESET");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        gameResultPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Wynik", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        blackCorrectLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        blackCorrectLabel.setText("0");
        blackCorrectLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        blackWrongLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        blackWrongLabel.setText("0");
        blackWrongLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        whiteWrongLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        whiteWrongLabel.setText("0");
        whiteWrongLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        whiteCorrectLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        whiteCorrectLabel.setText("0");
        whiteCorrectLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel5.setText("dobrze");

        jLabel7.setText("czarne");

        jLabel8.setText("białe");

        jLabel9.setText("suma");

        blackNumberLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        blackNumberLabel.setText("0");
        blackNumberLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 204)));

        whiteNumberLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        whiteNumberLabel.setText("0");
        whiteNumberLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 204)));

        jLabel12.setText("suma");

        correctSumLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        correctSumLabel.setText("0");
        correctSumLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 204)));

        wrongSumLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        wrongSumLabel.setText("0");
        wrongSumLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 204)));

        tilesNumberLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tilesNumberLabel.setText("25");
        tilesNumberLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        gameResultLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gameResultLabel.setText("WYNIK  0%");

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("źle");

        javax.swing.GroupLayout gameResultPanelLayout = new javax.swing.GroupLayout(gameResultPanel);
        gameResultPanel.setLayout(gameResultPanelLayout);
        gameResultPanelLayout.setHorizontalGroup(
            gameResultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gameResultPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(gameResultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gameResultLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(gameResultPanelLayout.createSequentialGroup()
                        .addGroup(gameResultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(gameResultPanelLayout.createSequentialGroup()
                                .addGroup(gameResultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(gameResultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(gameResultPanelLayout.createSequentialGroup()
                                        .addComponent(whiteCorrectLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(whiteWrongLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(gameResultPanelLayout.createSequentialGroup()
                                        .addComponent(blackCorrectLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(blackWrongLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(gameResultPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(correctSumLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(wrongSumLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE))
                            .addGroup(gameResultPanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)))
                        .addGroup(gameResultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(gameResultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(gameResultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(whiteNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(blackNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel9))
                            .addComponent(tilesNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        gameResultPanelLayout.setVerticalGroup(
            gameResultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gameResultPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(gameResultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel9)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(gameResultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(gameResultPanelLayout.createSequentialGroup()
                        .addGroup(gameResultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(blackCorrectLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(blackWrongLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(gameResultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(whiteCorrectLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(whiteWrongLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)))
                    .addGroup(gameResultPanelLayout.createSequentialGroup()
                        .addComponent(blackNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(whiteNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(gameResultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(correctSumLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(wrongSumLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tilesNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(gameResultLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout gamePanelLayout = new javax.swing.GroupLayout(gamePanel);
        gamePanel.setLayout(gamePanelLayout);
        gamePanelLayout.setHorizontalGroup(
            gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gamePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gameDescriptionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
                    .addGroup(gamePanelLayout.createSequentialGroup()
                        .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(gamePicBeforePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(gamePanelLayout.createSequentialGroup()
                                .addComponent(MethodCbBox, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(73, 73, 73)
                                .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(resetButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(checkButton, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(gamePicAfterPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(gamePanelLayout.createSequentialGroup()
                        .addComponent(gameResultPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(gameElementPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        gamePanelLayout.setVerticalGroup(
            gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gamePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(gamePanelLayout.createSequentialGroup()
                        .addComponent(gameDescriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(MethodCbBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(checkButton, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(gameElementPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(gameResultPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(gamePicBeforePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                    .addComponent(gamePicAfterPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabbedPane.addTab("Gra", gamePanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabbedPane)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabbedPane)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void filePathEditTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filePathEditTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_filePathEditTextActionPerformed

    private void loadImageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadImageButtonActionPerformed
        
        if(!loadPreviousConfiguration) {
            JFileChooser fileChooser = new JFileChooser();
        
            String pathToLoad;
            if(lastChoosenPath == null) {
                pathToLoad = System.getProperty("user.home");
            } else {
                pathToLoad = lastChoosenPath;
            }
        
            fileChooser.setCurrentDirectory(new File(pathToLoad));
            int result = fileChooser.showOpenDialog(this);
            
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                filePathEditText.setText(selectedFile.getAbsolutePath());
                saveLastPath(selectedFile);
                showFileAsImage(selectedFile);
            }
        } else {
            File selectedFile = new File(filePathEditText.getText());
            filePathEditText.setText(selectedFile.getAbsolutePath());
            saveLastPath(selectedFile);
            showFileAsImage(selectedFile);
            loadPreviousConfiguration = false;
        }
    }//GEN-LAST:event_loadImageButtonActionPerformed

    private void processButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_processButtonActionPerformed
        checkIfImagesAreLoded();
        new TransformationFrame(new FastRGB(zoomedImage), 
                                makeStructuralElement(),
                                zoomedImage.getWidth(), 
                                zoomedImage.getHeight())
                                .setVisible(true);
    }//GEN-LAST:event_processButtonActionPerformed

    private void InfoElementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InfoElementActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Element strukturalny - jest to macierz 'nakładana' na obraz pierwotny w celu dokonania przetworzenia tego obrazu. "+ "\n" + "Komórka środkowa(1,1) jest punktem reprezentacyjnym. Oznacza to, że ten punkt obrazu zostanie zmieniony (na podstawie wartości macierzy)");
    }//GEN-LAST:event_InfoElementActionPerformed

    private void InfoDylatacjaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InfoDylatacjaActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Dylatacja - jedno z przekształceń morfologicznych. Dylatacja służy do zamykania małych otworów oraz zatok we wnętrzu figury."+ "\n" + " Obiekty zwiększają swoją objętość i jeśli dwa lub więcej obiektów położonych jest blisko siebie, zrastają się w większe obiekty. ");
    }//GEN-LAST:event_InfoDylatacjaActionPerformed

    private void InfoErozjaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InfoErozjaActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Erozja - jedno z przekształceń morfologicznych. Jej działanie polega na obcinaniu brzegów obiektu na obrazie.");

    }//GEN-LAST:event_InfoErozjaActionPerformed

    private void radioDylatacjaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioDylatacjaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioDylatacjaActionPerformed

    private void radioErozjaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioErozjaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioErozjaActionPerformed

    private void startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startActionPerformed
        // TODO add your handling code here:
        // są problemy ze sprawdzeniem, czy rysunek jest narysowany, więc odpuszczam sprawdzanie
        next.setEnabled(true);
        kliknieciestart = 1;
        int elSize = ((int)((float)przed.getHeight()-40)/5);
        przed.removeAll();
      
        obrazPrzed = new TransformationCanvas(makePrzed(), makeStructuralElement2(), 1);
        obrazPrzed.setBounds((przed.getWidth() - (elSize+1)*5)/2, 20, (elSize+1)*5, (elSize+1)*5);
        obrazPrzed.showStructuralElement();
        obrazPrzed.repaint();
        przed.add(obrazPrzed);
      
        processedImage2 = new int[makePrzed().length][makePrzed()[0].length];
        for(int i = 0; i < processedImage2.length; i++) {
            for (int j = 0; j < processedImage2[0].length; j++) {
                processedImage2[i][j] = 0;
            }
        }
        
        showImageAfterProcess2(processedImage2);
        
        obrazPrzed2 = new int[makePrzed().length][makePrzed()[0].length];
        obrazPrzed.setStructuralElementPosition(new Point(0, -1));

       if(radioErozja.isSelected()){
            komentarzText.setContentType("text/html");
            komentarzText.setText("<b>EROZJA</b><br>"
                    + "         <b>1.</b> Wybierany jest punkt A(0,0) z obrazu PRZED. <br>"
                    //+ "         <b>2.</b> Na punkt A nakładamy element strukturalny, tak, aby <font color = red>środek </font> pokrywał punkt A.<br>"
                    //+ "         <b>3.</b> Porównujemy, czy WSZYSTKIE punkty otoczenia A są tak zamalowane jak otoczenie <font color = red>środka </font> elementu strukturalnego.<br>"
                    //+ "         <b>4a.</b> Jeśli TAK: na obrazie PO w punkcie A' zamalowywany jest prostokąt<br>"
                    //+ "         <b>4b.</b> Jeśli NIE: na obrazie PO w punkcie A' pozostaje pusty prostokąt<br>"
                    //+ "         <b>5.</b> Wracamy do punktu 1.<br>"
            );
                  
        } else if(radioDylatacja.isSelected()){
            komentarzText.setContentType("text/html");
            komentarzText.setText("<b>DYLATACJA</b><br>"
                    + "         <b>1.</b> Wybierany jest punkt A(0,0) z obrazu PRZED. <br>"
                    //+ "         <b>2.</b> Na punkt A nakładamy element strukturalny, tak, aby <font color = red>środek </font> pokrywał punkt A.<br>"
                    //+ "         <b>3.</b> Porównujemy, czy CHOĆ JEDEN punkt otoczenia A jest tak zamalowany jak otoczenie <font color = red>środka </font> elementu strukturalnego.<br>"
                    //+ "         <b>4a.</b> Jeśli TAK: na obrazie PO w punkcie A' zamalowywany jest prostokąt<br>"
                    //+ "         <b>4b.</b> Jeśli NIE: na obrazie PO w punkcie A' pozostaje pusty prostokąt<br>"
                    //+ "         <b>5.</b> Wracamy do punktu 1.<br>"
            );
            
        
    
        }
                
    }//GEN-LAST:event_startActionPerformed

    int step = 1;
    boolean shouldUpdate = true;
    // agata - moje przeróbki, wiem, brzydko to wygląda, ale inaczej nie moge tego wrzucic do okna po , niestety
     private void dilate(int[][] originalImage, int[][] processedImage ,Point p){

        int i = p.x;
        int j = p.y;
        
        int[][] structuralElement = makeStructuralElement2();
        
        if(i >= 0 && j >= 0) {
            processedImage[i][j] = originalImage[i][j];
        //for (int i=0; i<image.length; i++){
            //for (int j=0; j<image[i].length; j++){
                 if (originalImage[i][j] == 0){
                    if (structuralElement[1][0] == 1 && i>0 && originalImage[i-1][j] == 1) processedImage[i][j] = DILATION_COLOR;
                    if (structuralElement[0][1] == 1 && j>0 && originalImage[i][j-1] == 1) processedImage[i][j] = DILATION_COLOR;
                    if (structuralElement[1][2] == 1 && i+1<originalImage.length && originalImage[i+1][j] == 1) processedImage[i][j] = DILATION_COLOR;
                    if (structuralElement[2][1] == 1 && j+1<originalImage[i].length && originalImage[i][j+1] == 1) processedImage[i][j] = DILATION_COLOR;
                    
                    if (structuralElement[0][0] == 1 && i>0 && j>0 && originalImage[i-1][j-1] == 1) processedImage[i][j] = DILATION_COLOR;
                    if (structuralElement[2][0] == 1 && i>0 && j+1<originalImage[i].length && originalImage[i-1][j+1] == 1) processedImage[i][j] = DILATION_COLOR;
                    if (structuralElement[0][2] == 1 && i+1<originalImage.length && j>0 && originalImage[i+1][j-1] == 1) processedImage[i][j] = DILATION_COLOR;
                    if (structuralElement[2][2] == 1 && i+1<originalImage.length && j+1<originalImage[i].length && originalImage[i+1][j+1] == 1) processedImage[i][j] = DILATION_COLOR;
                }
            //}
        //}
        }
        
        obrazPrzed.setStructuralElementPosition(p);
        if(p.x == 0 && p.y == -1 && step == 1) {
            komentarzText.setText("<b>DYLATACJA</b><br>"
                    + "         <b>1.</b> Wybierany jest punkt A(0,0) z obrazu PRZED. <br>"
                    + "         <b>2.</b> Na punkt A nakładamy element strukturalny, tak, aby <font color = red>środek </font> pokrywał punkt A.<br>"
                    //+ "         <b>3.</b> Porównujemy, czy WSZYSTKIE punkty otoczenia A są tak zamalowane jak otoczenie <font color = red>środka </font> elementu strukturalnego.<br>"
                    //+ "         <b>4a.</b> Jeśli TAK: na obrazie PO w punkcie A' zamalowywany jest prostokąt<br>"
                    //+ "         <b>4b.</b> Jeśli NIE: na obrazie PO w punkcie A' pozostaje pusty prostokąt<br>"
                    //+ "         <b>5.</b> Wracamy do punktu 1.<br>"
            );
            step = 2;
            
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
            shouldUpdate = false;
        } else if (step == 2) {
            komentarzText.setText("<b>DYLATACJA</b><br>"
                    + "         <b>1.</b> Wybierany jest punkt A(0,0) z obrazu PRZED. <br>"
                    + "         <b>2.</b> Na punkt A nakładamy element strukturalny, tak, aby <font color = red>środek </font> pokrywał punkt A.<br>"
                    + "         <b>3.</b> Porównujemy, czy WSZYSTKIE punkty otoczenia A są tak zamalowane jak otoczenie <font color = red>środka </font> elementu strukturalnego.<br>"
                    //+ "         <b>4a.</b> Jeśli TAK: na obrazie PO w punkcie A' zamalowywany jest prostokąt<br>"
                    //+ "         <b>4b.</b> Jeśli NIE: na obrazie PO w punkcie A' pozostaje pusty prostokąt<br>"
                    //+ "         <b>5.</b> Wracamy do punktu 1.<br>"
            );
            step = 3;
        }  else if (step == 3) {
            komentarzText.setText("<b>DYLATACJA</b><br>"
                    + "         <b>1.</b> Wybierany jest punkt A(0,0) z obrazu PRZED. <br>"
                    + "         <b>2.</b> Na punkt A nakładamy element strukturalny, tak, aby <font color = red>środek </font> pokrywał punkt A.<br>"
                    + "         <b>3.</b> Porównujemy, czy WSZYSTKIE punkty otoczenia A są tak zamalowane jak otoczenie <font color = red>środka </font> elementu strukturalnego.<br>"
                    + "         <b>4a.</b> Jeśli TAK: na obrazie PO w punkcie A' zamalowywany jest prostokąt<br>"
                    + "         <b>4b.</b> Jeśli NIE: na obrazie PO w punkcie A' pozostaje pusty prostokąt<br>"
                    //+ "         <b>5.</b> Wracamy do punktu 1.<br>"
            );
            step = 4; 
            shouldUpdate = true;
        } else if (step == 4) {
            komentarzText.setText("<b>DYLATACJA</b><br>"
                    + "         <b>1.</b> Wybierany jest punkt A(0,0) z obrazu PRZED. <br>"
                    + "         <b>2.</b> Na punkt A nakładamy element strukturalny, tak, aby <font color = red>środek </font> pokrywał punkt A.<br>"
                    + "         <b>3.</b> Porównujemy, czy WSZYSTKIE punkty otoczenia A są tak zamalowane jak otoczenie <font color = red>środka </font> elementu strukturalnego.<br>"
                    + "         <b>4a.</b> Jeśli TAK: na obrazie PO w punkcie A' zamalowywany jest prostokąt<br>"
                    + "         <b>4b.</b> Jeśli NIE: na obrazie PO w punkcie A' pozostaje pusty prostokąt<br>"
                    + "         <b>5.</b> Wracamy do punktu 1.<br>"
            );
            step = 5; 
        }else  {
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
        }
    }
            
     
    private void erode(int[][] originalImage, int[][] processedImage ,Point p){

        int i = p.x;
        int j = p.y;
        
        int[][] structuralElement = makeStructuralElement2();
        
        
        if(i >= 0 && j >= 0) {
                    processedImage[i][j] = originalImage[i][j];
        //for (int i=0; i<image.length; i++){
            //for (int j=0; j<image[i].length; j++){
                if (originalImage[i][j] == 1){                    
                    if (structuralElement[1][0] == 1 && i>0 && originalImage[i-1][j] == 0) processedImage[i][j] = EROSION_COLOR;
                    if (structuralElement[0][1] == 1 && j>0 && originalImage[i][j-1] == 0) processedImage[i][j] = EROSION_COLOR;
                    if (structuralElement[1][2] == 1 && i+1<originalImage.length && originalImage[i+1][j] == 0) processedImage[i][j] = EROSION_COLOR;
                    if (structuralElement[2][1] == 1 && j+1<originalImage[i].length && originalImage[i][j+1] == 0) processedImage[i][j] = EROSION_COLOR;
                    
                    if (structuralElement[0][0] == 1 && i>0 && j>0 && originalImage[i-1][j-1] == 0) processedImage[i][j] = EROSION_COLOR;
                    if (structuralElement[2][0] == 1 && i>0 && j+1<originalImage[i].length && originalImage[i-1][j+1] == 0) processedImage[i][j] = EROSION_COLOR;
                    if (structuralElement[0][2] == 1 && i+1<originalImage.length && j>0 && originalImage[i+1][j-1] == 0) processedImage[i][j] = EROSION_COLOR;
                    if (structuralElement[2][2] == 1 && i+1<originalImage.length && j+1<originalImage[i].length && originalImage[i+1][j+1] == 0) processedImage[i][j] = EROSION_COLOR;
                }
            //}
        //}
        }
        
        obrazPrzed.setStructuralElementPosition(p);
        if(p.x == 0 && p.y == -1 && step == 1) {
            komentarzText.setText("<b>EROZJA</b><br>"
                    + "         <b>1.</b> Wybierany jest punkt A(0,0) z obrazu PRZED. <br>"
                    + "         <b>2.</b> Na punkt A nakładamy element strukturalny, tak, aby <font color = red>środek </font> pokrywał punkt A.<br>"
                    //+ "         <b>3.</b> Porównujemy, czy WSZYSTKIE punkty otoczenia A są tak zamalowane jak otoczenie <font color = red>środka </font> elementu strukturalnego.<br>"
                    //+ "         <b>4a.</b> Jeśli TAK: na obrazie PO w punkcie A' zamalowywany jest prostokąt<br>"
                    //+ "         <b>4b.</b> Jeśli NIE: na obrazie PO w punkcie A' pozostaje pusty prostokąt<br>"
                    //+ "         <b>5.</b> Wracamy do punktu 1.<br>"
            );
            step = 2;
            
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
            shouldUpdate = false;
        } else if (step == 2) {
            komentarzText.setText("<b>EROZJA</b><br>"
                    + "         <b>1.</b> Wybierany jest punkt A(0,0) z obrazu PRZED. <br>"
                    + "         <b>2.</b> Na punkt A nakładamy element strukturalny, tak, aby <font color = red>środek </font> pokrywał punkt A.<br>"
                    + "         <b>3.</b> Porównujemy, czy WSZYSTKIE punkty otoczenia A są tak zamalowane jak otoczenie <font color = red>środka </font> elementu strukturalnego.<br>"
                    //+ "         <b>4a.</b> Jeśli TAK: na obrazie PO w punkcie A' zamalowywany jest prostokąt<br>"
                    //+ "         <b>4b.</b> Jeśli NIE: na obrazie PO w punkcie A' pozostaje pusty prostokąt<br>"
                    //+ "         <b>5.</b> Wracamy do punktu 1.<br>"
            );
            step = 3;
        }  else if (step == 3) {
            komentarzText.setText("<b>EROZJA</b><br>"
                    + "         <b>1.</b> Wybierany jest punkt A(0,0) z obrazu PRZED. <br>"
                    + "         <b>2.</b> Na punkt A nakładamy element strukturalny, tak, aby <font color = red>środek </font> pokrywał punkt A.<br>"
                    + "         <b>3.</b> Porównujemy, czy WSZYSTKIE punkty otoczenia A są tak zamalowane jak otoczenie <font color = red>środka </font> elementu strukturalnego.<br>"
                    + "         <b>4a.</b> Jeśli TAK: na obrazie PO w punkcie A' zamalowywany jest prostokąt<br>"
                    + "         <b>4b.</b> Jeśli NIE: na obrazie PO w punkcie A' pozostaje pusty prostokąt<br>"
                    //+ "         <b>5.</b> Wracamy do punktu 1.<br>"
            );
            step = 4; 
            shouldUpdate = true;
        } else if (step == 4) {
            komentarzText.setText("<b>EROZJA</b><br>"
                    + "         <b>1.</b> Wybierany jest punkt A(0,0) z obrazu PRZED. <br>"
                    + "         <b>2.</b> Na punkt A nakładamy element strukturalny, tak, aby <font color = red>środek </font> pokrywał punkt A.<br>"
                    + "         <b>3.</b> Porównujemy, czy WSZYSTKIE punkty otoczenia A są tak zamalowane jak otoczenie <font color = red>środka </font> elementu strukturalnego.<br>"
                    + "         <b>4a.</b> Jeśli TAK: na obrazie PO w punkcie A' zamalowywany jest prostokąt<br>"
                    + "         <b>4b.</b> Jeśli NIE: na obrazie PO w punkcie A' pozostaje pusty prostokąt<br>"
                    + "         <b>5.</b> Wracamy do punktu 1.<br>"
            );
            step = 5; 
        }else  {
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
        }


    }
     
     private void showImageAfterProcess2 (int[][] binaryModelAfterProcess2) {
        
        //afterProcessCanvas2.updateBinaryImage(binaryModelAfterProcess2);
        //this.setPreferredSize(new Dimension((int)(width*originalCanvas.getTileSize() * 2 + 20), this.getHeight()));
        if(afterProcessCanvas2 == null ) {
            int elSize = ((int)((float)po.getHeight()-40)/5);
            po.removeAll();
      
            afterProcessCanvas2 = new TransformationCanvas(binaryModelAfterProcess2, makeStructuralElement2(), 1);
            afterProcessCanvas2.setBounds((po.getWidth() - (elSize+1)*5)/2, 20, (elSize+1)*5, (elSize+1)*5);
            afterProcessCanvas2.hideStructuralElement();
            afterProcessCanvas2.repaint();
            po.add(afterProcessCanvas2);
        } else {
            afterProcessCanvas2.updateBinaryImage(binaryModelAfterProcess2);
        }
  
    }
    
    
    private void restartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restartActionPerformed
        // TODO add your handling code here:
        next.setEnabled(false);
        structuralElementPosition2 = new Point(0,-1);
        przed.removeAll();
        po.removeAll();
        elementPanel2.removeAll();
        komentarzText.setText(" ");  
        kliknieciestart = 0;
        step = 1;
        
        afterProcessCanvas2 = null;
        
        ElementPan2();
        przed();
    }//GEN-LAST:event_restartActionPerformed

    private void nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextActionPerformed
        if (radioErozja.isSelected()) {
            erode(makePrzed(), processedImage2, structuralElementPosition2);
            if(shouldUpdate) showImageAfterProcess2(processedImage2);
        } else if (radioDylatacja.isSelected()){
            dilate(makePrzed(), processedImage2, structuralElementPosition2);
            if(shouldUpdate) showImageAfterProcess2(processedImage2);
        } 
    }//GEN-LAST:event_nextActionPerformed

    private void MethodCbBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MethodCbBoxActionPerformed
        String methodName = (String)MethodCbBox.getSelectedItem();
        drawGamePictures(methodName);
        checkButton.setBackground(null);
        checkButton.setText("SPRAWDŹ");
       
    }//GEN-LAST:event_MethodCbBoxActionPerformed

    private void checkButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkButtonActionPerformed

        String methodName = (String)MethodCbBox.getSelectedItem();
        if(checkAnswer(methodName))
        {
            checkButton.setBackground(new Color(100, 255, 150));
            checkButton.setText("DOBRZE!");
        }
        else
        {
           checkButton.setBackground(new Color(255, 100, 150)); 
           checkButton.setText("ŹLE");
        }

    }//GEN-LAST:event_checkButtonActionPerformed

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        String methodName = (String)MethodCbBox.getSelectedItem();
        drawGamePictures(methodName);
        checkButton.setBackground(null);
        checkButton.setText("SPRAWDŹ");
    }//GEN-LAST:event_resetButtonActionPerformed
   
   
    private boolean checkAnswer(String methodName)
    {
        int[][] correctTiles;
        if(methodName == "Dylatacja")
        {
            correctTiles = new int[][]{
                { 0, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 0 },
                { 1, 1, 1, 0, 0 },
                { 1, 1, 1, 1, 0 },
                { 1, 1, 1, 0, 0 }};
        }
        else 
        {
            correctTiles = new int[][]{
                { 0, 0, 0, 0, 0 },
                { 0, 0, 1, 0, 0 },
                { 0, 1, 0, 0, 0 },
                { 1, 0, 0, 0, 0 },
                { 1, 0, 0, 0, 0 }};
        }
        int whitesNumber = countTiles(correctTiles, 0);
        int blacksNumber = countTiles(correctTiles, 1);
        
        int correctWhitesNumber = countCorrectTiles(correctTiles, gamePictureAfter.getTilesArray(), 0);
        int correctBlacksNumber = countCorrectTiles(correctTiles, gamePictureAfter.getTilesArray(), 1);
        
        int wrongWhitesNumber = whitesNumber - correctWhitesNumber;
        int wrongBlacksNumber = blacksNumber - correctBlacksNumber;
        
        whiteCorrectLabel.setText(Integer.toString(correctWhitesNumber));
        blackCorrectLabel.setText(Integer.toString(correctBlacksNumber));
        
        whiteNumberLabel.setText(Integer.toString(whitesNumber));
        blackNumberLabel.setText(Integer.toString(blacksNumber));
        
        whiteWrongLabel.setText(Integer.toString(wrongWhitesNumber));
        blackWrongLabel.setText(Integer.toString(wrongBlacksNumber));
        
        int correctSum = correctWhitesNumber + correctBlacksNumber;
        correctSumLabel.setText(Integer.toString(correctSum));
        wrongSumLabel.setText(Integer.toString(wrongWhitesNumber + wrongBlacksNumber));
        
        float result = (correctSum/25.0f) * 100.0f;
        gameResultLabel.setText("WYNIK " + result + "%");
                
        return compareArrays(correctTiles, gamePictureAfter.getTilesArray());
    }
    
    int countCorrectTiles(int[][]correctArray, int[][]answerarray, int value)
    {
        int sum = 0;
        
        for(int x=0; x<correctArray.length; x++)
        {
            for(int y=0; y<correctArray[0].length; y++)
            {             
                if(correctArray[x][y] == value &&  correctArray[x][y] == answerarray[x][y])
                    sum ++;
            }
        }
        
        return sum;
    }
    
    int countTiles(int[][]array, int value)
    {
        int result = 0;
        for(int x=0; x<array.length; x++)
        {
            for(int y=0; y<array[0].length; y++)
            {
                if(array[x][y] == value)
                    result ++;
            }
        }
        return result;
    }
    
    private boolean compareArrays(int[][]array1, int[][]array2)
    {
        for(int x=0; x<array1.length; x++)
        {
            for(int y=0; y<array1[0].length; y++)
            {
                if(array1[x][y] != array2[x][y])
                    return false;
            }
        }
        return true;
    }
    
    private void initGameElements() {
        drawGamePictures("Dylatacja");
    }
    
    private void drawGamePictures(String methodName)
    {
        GamePictureCanvas gamePictureBefore;        
        int elementSize = ((int)((float)gameElementPanel.getHeight()-40)/3);
        int elSizePic1 = ((int)((float)gamePicBeforePanel.getHeight()-40)/5);
        int elSizePic2 = ((int)((float)gamePicAfterPanel.getHeight()-40)/5);
        int[][] tilesBefore;
        int[][] elementArray;
        int[][] tilesAfter = new int[][]{
            { 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0 }};

        if(methodName == "Dylatacja")
        {           
            elementArray = new int[][]{
                { 0, 1, 0 },
                { 1, 4, 1 },
                { 0, 1, 0 }};
            tilesBefore = new int[][]{
                { 0, 0, 0, 1, 0 },
                { 0, 1, 1, 0, 0 },
                { 0, 1, 0, 0, 0 },
                { 0, 1, 1, 0, 0 },
                { 1, 1, 0, 0, 0 }};
        }
        else
        {
            elementArray = new int[][]{
                { 0, 1, 0 },
                { 0, 4, 1 },
                { 0, 0, 0 }};
            tilesBefore = new int[][]{
                { 1, 0, 1, 0, 0 },
                { 0, 1, 1, 1, 0 },
                { 1, 1, 1, 0, 0 },
                { 1, 1, 0, 0, 0 },
                { 1, 1, 0, 0, 0 }};
        }
        
        elementGame = new GamePictureCanvas(elementSize, elementArray);
        elementGame.setBounds((gameElementPanel.getWidth() - (elementSize+1)*3)/2, 20, (elementSize+1)*3, (elementSize+1)*3);
        gameElementPanel.removeAll();
        gameElementPanel.add(elementGame);
        
        gamePictureBefore = new GamePictureCanvas(elSizePic1, tilesBefore);
        gamePictureBefore.setBounds((gamePicBeforePanel.getWidth() - (elSizePic1+1)*5)/2, 20, (elSizePic1+1)*5, (elSizePic1+1)*5); 
        gamePicBeforePanel.removeAll();
        gamePicBeforePanel.add(gamePictureBefore);
    
        gamePictureAfter = new GamePictureCanvas(elSizePic2, tilesAfter, true);
        gamePictureAfter.setBounds((gamePicAfterPanel.getWidth() - (elSizePic2+1)*5)/2, 20, (elSizePic2+1)*5, (elSizePic2+1)*5);
        gamePicAfterPanel.removeAll();
        gamePicAfterPanel.add(gamePictureAfter);      
    }
    
    
    private void checkIfImagesAreLoded() {
        if(binaryImagePanel.getComponentCount() == 0) {
            JOptionPane.showMessageDialog(null, "Wczytaj obraz w celu dalszego przetwarzania");
            return;
        }
        if(zoomedImage == null) {
            JOptionPane.showMessageDialog(null, "Zaznacz fragment wczytanego obrazu w celu uzyskania powiększenia i dalszego przetwarzania");
            return;
        }
    }
    
    private int[][] makeStructuralElement() {
        return elementCanvas.getStructuralElementArray();
    }
    
     private int[][] makeStructuralElement2() {
        return elementNauka.getElementNaukaArray();
    }
     
     private int[][] makePrzed() {
        return przedNauka.getprzedArray();
    }
     
    private void saveLastPath(File selectedFile) {
        String[] dividedPath = selectedFile.getAbsolutePath().split(""+File.separator+File.separator);
        dividedPath = Arrays.copyOf(dividedPath, dividedPath.length - 1);
        StringBuilder pathWithoutLastSegment = new StringBuilder();
        for (String s : dividedPath) {
            pathWithoutLastSegment.append(s);
            pathWithoutLastSegment.append(File.separator);
        } 
        lastChoosenPath = pathWithoutLastSegment.toString();
        System.out.println("Selected file: " + selectedFile.getAbsolutePath()); 
        System.out.println("Last path: " + lastChoosenPath); 
        
        Properties prop = new Properties();
        prop.setProperty("dir", lastChoosenPath);
        prop.setProperty("file", selectedFile.getAbsolutePath());
        File f = new File("user.properties");
        OutputStream out;
        try {
            out = new FileOutputStream(f);
            prop.store(out, "");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void showFileAsImage(File selectedFile) {
        Image pic = null;
        try {
            pic = ImageIO.read(selectedFile);
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(pic != null) {
            //remove prevois images
            binaryImagePanel.removeAll();
            zoomedImagePanel.removeAll();
            
            int width, height;
        
            BufferedImage fittedImage = toBufferedImage(pic);
            float aspectRatio = (float)fittedImage.getWidth() / (float)fittedImage.getHeight();

            if(fittedImage.getHeight() < fittedImage.getWidth()) {
                width = binaryImagePanel.getWidth() - 50;
                height = (int)((float)width / aspectRatio);
            } else {
                height = binaryImagePanel.getHeight() - 50;
                width = (int)(aspectRatio * (float)height);
            }
            fittedImage = createResizedCopy(fittedImage, width, height, false);
            
            SelectedImageCanvas canvas = new SelectedImageCanvas(fittedImage, this);
            canvas.setBounds((binaryImagePanel.getWidth() - fittedImage.getWidth(null))/2, (binaryImagePanel.getHeight() - fittedImage.getHeight(null))/2, fittedImage.getWidth(null), fittedImage.getHeight(null));
            binaryImagePanel.add(canvas);
        }
    }   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame InfoDyla;
    private javax.swing.JButton InfoDylatacja;
    private javax.swing.JFrame InfoEle;
    private javax.swing.JButton InfoElement;
    private javax.swing.JFrame InfoEro;
    private javax.swing.JButton InfoErozja;
    private javax.swing.JComboBox<String> MethodCbBox;
    private javax.swing.JPanel binaryImagePanel;
    private javax.swing.JLabel blackCorrectLabel;
    private javax.swing.JLabel blackNumberLabel;
    private javax.swing.JLabel blackWrongLabel;
    private javax.swing.JButton checkButton;
    private javax.swing.JLabel correctSumLabel;
    private javax.swing.JPanel elementPanel;
    private javax.swing.JPanel elementPanel2;
    private javax.swing.ButtonGroup erodyl;
    private javax.swing.JTextField filePathEditText;
    private javax.swing.JLabel gameDescriptionLabel;
    private javax.swing.JPanel gameElementPanel;
    private javax.swing.JPanel gamePanel;
    private javax.swing.JPanel gamePicAfterPanel;
    private javax.swing.JPanel gamePicBeforePanel;
    private javax.swing.JLabel gameResultLabel;
    private javax.swing.JPanel gameResultPanel;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JEditorPane komentarzText;
    private javax.swing.JButton loadImageButton;
    private javax.swing.JButton next;
    private javax.swing.JPanel po;
    private javax.swing.JButton processButton;
    private javax.swing.JPanel programPanel;
    private javax.swing.JPanel przed;
    private javax.swing.JRadioButton radioDylatacja;
    private javax.swing.JRadioButton radioErozja;
    private javax.swing.JButton resetButton;
    private javax.swing.JButton restart;
    private javax.swing.JButton start;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JPanel teachPanel;
    private javax.swing.JLabel tilesNumberLabel;
    private javax.swing.JLabel whiteCorrectLabel;
    private javax.swing.JLabel whiteNumberLabel;
    private javax.swing.JLabel whiteWrongLabel;
    private javax.swing.JLabel wrongSumLabel;
    private javax.swing.JPanel zoomedImagePanel;
    // End of variables declaration//GEN-END:variables

    @Override
    public void zoomed(Image image, int x1, int y1, int x2, int y2) {
        
        zoomedImagePanel.removeAll();
        int width, height;
        
        BufferedImage croppedImage = cropImage(toBufferedImage(image), x1, y1, x2, y2);
        float aspectRatio = (float)croppedImage.getWidth() / (float)croppedImage.getHeight();

        if(croppedImage.getHeight() < croppedImage.getWidth()) {
            width = (zoomedImagePanel.getWidth() - 60)/10;
            height = (int)((float)width / aspectRatio);
        } else {
            height = (zoomedImagePanel.getHeight() - 60)/10;
            width = (int)(aspectRatio * (float)height);
        }
        
        zoomedImage = createResizedCopy(croppedImage, width, height, false);
        croppedImage = createResizedCopy(zoomedImage, width*10, height*10, false);
        
        ZoomedImageCanvas canvas = new ZoomedImageCanvas(croppedImage);
        canvas.setBounds((zoomedImagePanel.getWidth() - croppedImage.getWidth(null))/2, (zoomedImagePanel.getHeight() - croppedImage.getHeight(null))/2, croppedImage.getWidth(null), croppedImage.getHeight(null));
        zoomedImagePanel.add(canvas);
    }
    
    private BufferedImage cropImage(BufferedImage src, int x1, int y1, int x2, int y2) {
        BufferedImage dest = src.getSubimage(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
        return dest; 
    }
    
    public BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }
    
    private BufferedImage createResizedCopy(Image originalImage, 
    		int scaledWidth, int scaledHeight, 
    		boolean preserveAlpha) {
    	int imageType = preserveAlpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
    	BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, imageType);
    	Graphics2D g = scaledBI.createGraphics();
    	if (preserveAlpha) {
    		g.setComposite(AlphaComposite.Src);
    	}
    	g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null); 
    	g.dispose();
    	return scaledBI;
    }
    
}
