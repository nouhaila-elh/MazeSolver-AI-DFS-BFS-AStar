
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Title: MazeDepthFirstSearch<p>
 * Description: Demo program for Java AI Programming<p>
 * Copyright: Copyright (c) Mark Watson, Released under Open Source Artistic
 * License<p>
 * Company: Mark Watson Associates<p>
 * @author Mark Watson
 * @version 1.0
 */
public class MazeDepthFirstSearch extends javax.swing.JFrame {

    JPanel jPanel1 = new JPanel();
    DepthFirstSearchEngine currentSearchEngine = null;

    private BufferedImage img1;

    public MazeDepthFirstSearch(int height, int width) {
        try {
            jbInit();
        } catch (Exception e) {
            System.out.println("GUI initilization error: " + e);
        }
        currentSearchEngine = new DepthFirstSearchEngine(height, width);
        repaint();
    }

    // Fonction prise de MazeBreadthFirstSearch
    public void paint(Graphics g_unused) {
        if (currentSearchEngine == null) {
            return;
        }
        Maze maze = currentSearchEngine.getMaze();
        int width = maze.getWidth();
        int height = maze.getHeight();
        System.out.println("Size of current maze: " + width + " by " + height);
        Graphics g = jPanel1.getGraphics();
        BufferedImage image = new BufferedImage(700, 700, BufferedImage.TYPE_INT_RGB);
        Graphics g2 = image.getGraphics();
        g2.setColor(Color.PINK);
        g2.fillRect(0, 0, 670, 700);
        g2.setColor(Color.black);
        maze.setValue(0, 0, Maze.START_LOC_VALUE);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                short val = maze.getValue(x, y);

                if (val == Maze.OBSTICLE) {
                    g2.setColor(Color.lightGray);
                    g2.fillRect(6 + x * 29, 3 + y * 29, 29, 29);
                    g2.setColor(Color.black);
                    g2.drawRect(6 + x * 29, 3 + y * 29, 29, 29);
                } else if (val == Maze.START_LOC_VALUE) {
                    // g2.setColor(Color.blue);
                    //  g2.setFont(new Font("default", Font.BOLD, 16));
                    //PrintWriter printWriter = new PrintWriter(System.out, true);
                    //String aa = "🤠";
//
//                    g2.drawString(aa, 12 + x * 29, 23 + y * 29);

                    try {

                        img1 = ImageIO.read(new File("C:\\Users\\Home\\Documents\\NetBeansProjects\\Ai_projet\\src\\images\\icoman.png"));
                    } catch (IOException ex) {
                        Logger.getLogger(MazeDepthFirstSearch.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    g2.drawImage(img1, 5 + x * 29, 3 + y * 29, null);

                    g2.setColor(Color.black);
                    g2.drawRect(6 + x * 29, 3 + y * 29, 29, 29);
                } else if (val == Maze.GOAL_LOC_VALUE) {
                    g2.setColor(Color.red);
                    g2.drawString("G", 16 + x * 29, 19 + y * 29);
                    g2.setColor(Color.black);
                    g2.drawRect(6 + x * 29, 3 + y * 29, 29, 29);
                } else {
                    g2.setColor(Color.black);
                    g2.drawRect(6 + x * 29, 3 + y * 29, 29, 29);
                }
            }
        }
        // redraw the path in black:
        g2.setColor(Color.black);
        Dimension[] path = currentSearchEngine.getPath();
        for (int i = 1; i < (path.length - 1); i++) {
            int x = path[i].width;
            int y = path[i].height;
            short val = maze.getValue(x, y);
            g2.drawString("" + (path.length - i), 16 + x * 29, 19 + y * 29);
        }
        g.drawImage(image, 30, 40, 700, 700, null);

    }

//    public static void main(String[] args) {
//        int size = LireFichierText();
//        MazeDepthFirstSearch mazeSearch1 = new MazeDepthFirstSearch(size, size);
//    }

    private void jbInit() throws Exception {

        this.setContentPane(jPanel1);
        this.setCursor(null);
        this.setDefaultCloseOperation(3);
        this.setTitle("MazeDepthFirstSearch");
        this.getContentPane().setLayout(null);
        jPanel1.setBackground(Color.white);
        jPanel1.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);
        jPanel1.setDoubleBuffered(false);
        jPanel1.setRequestFocusEnabled(false);
        jPanel1.setLayout(null);
        String Help[] = {"DFS", "A*", "BFS"};
        JComboBox cb = new JComboBox(Help);
        cb.setBounds(800, 50, 90, 20);
        jPanel1.add(cb);
        this.setSize(1000, 700);
        this.setVisible(true);

//        cb.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                String data = "Programming language Selected: "
//                        + cb.getItemAt(cb.getSelectedIndex());
//                if(data == "DFS"){
//                
//            }
//            }
//        });

    }

    // ------------- Lire fichier 
    public static int LireFichierText() {

        int sizeW = 0;
        File fileDirs = new File("C:\\Users\\Home\\Documents\\NetBeansProjects\\Ai_projet\\src\\mazeFile\\LABY_21x21.txt");

        BufferedReader in = null;

        try {
            try {
                in = new BufferedReader(new InputStreamReader(new FileInputStream(fileDirs), "utf-8"));
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(MazeDepthFirstSearch.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MazeDepthFirstSearch.class.getName()).log(Level.SEVERE, null, ex);
        }

        String str;

        try {
            while ((str = in.readLine()) != null) {
                //   System.out.println(str);
                sizeW = str.length() + 1;

            }
        } catch (IOException ex) {
            Logger.getLogger(MazeDepthFirstSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(sizeW);

        return sizeW;
    }

}
