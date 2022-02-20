
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import static java.lang.Thread.sleep;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DebugGraphics;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.text.Keymap;

public class interface61 extends javax.swing.JFrame implements KeyListener, MouseListener {

    int ay = 0;
    int bx = 0;
    JButton btn = new JButton("Help");
    JButton pausebtn = new JButton();
    int score = 0;
    boolean play = true;
    boolean pause = false;

    public class gameover extends JPanel {

        private JLabel label;

        public gameover() {
            setLayout(new GridBagLayout());
            JFrame frame = new JFrame("Game over");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JLabel gameover = new JLabel();
            gameover.setText("Game over ");
            gameover.setBounds(400, 400, 200, 200);
            frame.add(gameover);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setBounds(400, 300, 250, 200);
            frame.setVisible(true);

        }

    }

    public class PaneHelp extends JPanel {

        private JLabel label2;

        public PaneHelp() {
            setLayout(new GridBagLayout());

            btn.setBounds(800, 50, 90, 20);
            btn.setVisible(true);
            btn.setFocusable(false);
            add(btn);

            pausebtn.setBounds(800, 200, 90, 20);
            pausebtn.setText("Pause");
            pausebtn.setVisible(true);
            pausebtn.setFocusable(false);
            add(pausebtn);

        }

    }

    //=-----------------countdown function ---------------
    public class TestPane extends JPanel {

        private Timer timer;
        private long startTime = -1;
        private long duration = 500000;

        private JLabel label;

        public TestPane() {

            setLayout(new GridBagLayout());
            timer = new Timer(10, new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (play == true) {

                        if (startTime < 0) {
                            startTime = System.currentTimeMillis();
                        }
                        long now = System.currentTimeMillis();
                        long clockTime = now - startTime;
                        if (clockTime >= duration) {
                            clockTime = duration;
                            timer.stop();
//                        JFrame frame = new JFrame("time out");
//                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                        JLabel over = new JLabel();
//                        over.setText("             Time is over");
//                        over.setBounds(400, 400, 200, 200);
//                        frame.add(over);
//                        frame.pack();
//                        frame.setLocationRelativeTo(null);
//                        frame.setBounds(400, 300, 200, 200);
//                        frame.setVisible(true);
                            play = false;
                            g2.setColor(Color.red);
                            g2.setFont(new Font("serif", Font.BOLD, 50));
                            g2.drawString("Game over, Score = " + score, 80, 300);
                            g.drawImage(image, 30, 40, 700, 700, null);
                            repaint();

                        }
                        SimpleDateFormat df = new SimpleDateFormat("mm:ss:SSS");
                        label.setText(df.format(duration - clockTime));
                    }

                }
            });
            timer.setInitialDelay(0);
            timer.start();
            label = new JLabel("...");
            add(label);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(70, 50);
        }

    }

    int X = 0;
    int Y = 0;

    private BufferedImage img1, img2,img4;
    JPanel jPanel1 = new JPanel();
    searchEngine currentSearchEngine = null;
//    DepthFirstSearchEngine testSerch = null;
//
//    BreadthFirstSearchEngine SerchBFS = null;
    AStarSearchEngine SerchAstar = null;
    private BufferedImage acteur;
//    String Help[] = {"Help","A*", "BFS", "DFS"};
//    JComboBox cb = new JComboBox(Help);
    //  JButton btn = new JButton("Help");
    int height;
    int width;

    Maze maze = null;
    Maze maze4 = null;
    BufferedImage image = null;
    Graphics g2 = null;
    Graphics g = null;

    public interface61(int height, int width) {
        try {
            jbInit();
        } catch (Exception e) {
            System.out.println("GUI initilization error: " + e);
        }

        this.height = height;
        this.width = width;

        currentSearchEngine = new searchEngine(height, width);
//        testSerch = new DepthFirstSearchEngine(height, width);
//        SerchBFS = new BreadthFirstSearchEngine(height,width);
        SerchAstar = new AStarSearchEngine(height, width);
        repaint();
    }

    private void jbInit() throws Exception {

        this.setContentPane(jPanel1);
        this.setCursor(null);
        this.setDefaultCloseOperation(3);
        this.setTitle("MazeDepthFirstSearch");
        // this.getContentPane().setLayout(null);
        jPanel1.setBackground(Color.white);
        jPanel1.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);
        jPanel1.setDoubleBuffered(false);
        jPanel1.setRequestFocusEnabled(false);
        jPanel1.setLayout(null);
        this.setSize(1400,700);
        this.setVisible(true);

//        JLabel label = new JLabel("Welcome to WayToLearnX!", JLabel.RIGHT);
//        this.add(label);
//        label.setBounds(1200, 100,90,20);
//        btn.setBounds(800, 50, 90, 20);
//        btn.setVisible(true);
//     // btn.addMouseListener(this);
//      // btn.removeKeyListener(this);
//        jPanel1.add(btn);
        this.addKeyListener(this);

//        this.add(label);
//        label.setBounds(1200, 100, 200, 200);
//         label.setVisible(true);
//         label.add(new TestPane());
//        this.setVisible(true);
//        
//        
        JPanel timepanel = new JPanel();
        // JPanel bounds
        this.add(timepanel);
        timepanel.setBounds(770, 100, 200, 200);
        timepanel.setVisible(true);
        timepanel.add(new TestPane());

        btn.addMouseListener(this);
        //     pausebtn.addMouseListener(this);

        JPanel helppanel = new JPanel();
        this.add(helppanel);
        helppanel.setBounds(770, 300, 200, 200);
        helppanel.setVisible(true);
        helppanel.add(new PaneHelp());

        this.setVisible(true);

        //      setFocusTraversalKeysEnabled(false);
        pausebtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (((JButton) e.getSource()).getText().equals("Pause")) {

                    play = false;

                    ((JButton) e.getSource()).setText("Retour");
                } else {
                    play = true;
                    ((JButton) e.getSource()).setText("Pause");
                }
            }

        });
    }

    public static int LireFichierText() {

        int sizeW = 0;
        File fileDirs = new File("C:\\Users\\Home\\Documents\\NetBeansProjects\\Ai_projet\\src\\mazeFile\\LABY_61x61.txt");

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

//    public static void main(String[] args) {
//        int size = LireFichierText();
//        interface61 mazeSearch1 = new interface61(size, size);
//    }

    public void paint(Graphics g_unused) {

        try {
            img1 = ImageIO.read(new File("C:\\Users\\Home\\Documents\\NetBeansProjects\\Ai_projet\\src\\images\\red-evil.png"));
        } catch (IOException ex) {
            Logger.getLogger(interface_maze.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            img4 = ImageIO.read(new File("C:\\Users\\Home\\Documents\\NetBeansProjects\\Ai_projet\\src\\images\\icons8-trophy-64.png"));
        } catch (IOException ex) {
            Logger.getLogger(interface_maze.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            img2 = ImageIO.read(new File("C:\\Users\\Home\\Documents\\NetBeansProjects\\Ai_projet\\src\\images\\dollar.png"));
        } catch (IOException ex) {
            Logger.getLogger(interface_maze.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (currentSearchEngine == null) {
            return;
        }
//        if (testSerch == null) {
//            return;
//        }
//        if (SerchBFS == null) {
//            return;
//        }

//
//        Maze maze2 = testSerch.getMaze();
//        int width2 = maze2.getWidth();
//        int height2 = maze2.getHeight();
//        
//        Maze maze3 = SerchBFS.getMaze();
//        int width3 = maze3.getWidth();
//        int height3 = maze3.getHeight();
        maze4 = SerchAstar.getMaze();
        int width4 = maze4.getWidth();
        int height4 = maze4.getHeight();

        maze = currentSearchEngine.getMaze();

        int width = maze.getWidth();
        int height = maze.getHeight();
        System.out.println("Size of current maze: " + width + " by " + height);
        g = jPanel1.getGraphics();
        image = new BufferedImage(2200,2200, BufferedImage.TYPE_INT_RGB);
        g2 = image.getGraphics();
        g2.setColor(Color.white);
        g2.fillRect(0, 0, 2200, 2200);
        g2.setColor(Color.white);
        maze.setValue(0, 0, Maze.START_LOC_VALUE);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                short val = maze.getValue(x, y);

                if (val == Maze.OBSTICLE) {
                    g2.setColor(Color.black);
                    g2.fillRect(6 + x * 29, 3 + y * 29, 29, 29);
                    g2.setColor(Color.black);
                    g2.drawRect(6 + x * 29, 3 + y * 29, 29, 29);
                } else if (val == Maze.START_LOC_VALUE) {

                    if (x == width - 1 && y == height - 1) {
                        play = false;
                        g2.setColor(Color.GREEN);
                        g2.setFont(new Font("serif", Font.BOLD, 40));
                        g2.drawString("congratulation you won,Score = " + score, 50, 300);
                        g2.setColor(Color.black);
                        g2.setFont(new Font("serif", Font.PLAIN, 10));
                        g.drawImage(image, 30, 40, 700, 700, null);
                        repaint();
                        break;
                    }
                    //maze.setValue(0,0, (short)0);
                    // g2.setColor(Color.blue);
                    //  g2.setFont(new Font("default", Font.BOLD, 16));
                    //PrintWriter printWriter = new PrintWriter(System.out, true);
                    // String aa = "🤠";

                    //  g2.drawString("S", 12 + x * 29, 23 + y * 29);
                    try {

                        acteur = ImageIO.read(new File("C:\\Users\\Home\\Documents\\NetBeansProjects\\Ai_projet\\src\\images\\icoman.png"));
                    } catch (IOException ex) {
                        Logger.getLogger(MazeDepthFirstSearch.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    g2.drawImage(acteur, 5 + x * 29, 3 + y * 29, null);
                    maze.setValue(0, 0, (short) 0);
                    g2.setColor(Color.black);
                    g2.drawRect(6 + x * 29, 3 + y * 29, 29, 29);

                } else if (val == Maze.ENNEMI) {

                    g2.drawImage(img1, 6 + x * 29, 3 + y * 29, 29, 29, null);
                    g2.setColor(Color.black);
                    g2.drawRect(6 + x * 29, 3 + y * 29, 29, 29);

                } else if (val == Maze.BONUS) {
                    g2.drawImage(img2, 7 + x * 29, 4 + y * 29, 28, 29, null);
                    g2.setColor(Color.PINK);
                    g2.drawRect(6 + x * 29, 3 + y * 29, 29, 29);
                    /*  g2.setColor(Color.GREEN);
                  g2.fillOval(6 + x * 29, 3 + y * 29, 29, 29);               
               
                   g2.setColor(Color.black);
                   g2.drawRect(6 + x * 29, 3 + y * 29, 29, 29); */
                } else if (val == Maze.GOAL_LOC_VALUE) {
                    g2.setColor(Color.red);
                    
                    
                    g2.drawImage(img4,  7 + x * 29, 4 + y * 29, 28, 29, null);
                    g2.setColor(Color.black);
                    g2.drawRect(6 + x * 29, 3 + y * 29, 29, 29);
                } else {
                    g2.setColor(Color.black);
                    g2.drawRect(6 + x * 29, 3 + y * 29, 29, 29);
                }
            }
        }
        g2.setColor(Color.red);
        g2.setFont(new Font("serif", Font.BOLD,30));
        g2.drawString("Score : " + score, 2200, 30);

        if (score < 0) {
            play = false;
            g2.setColor(Color.red);
            g2.setFont(new Font("serif", Font.BOLD, 50));
            g2.drawString("Game over, Score = " + score, 80, 300);
        }
        g.drawImage(image, 30, 40, 700, 700, null);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public void keyPressed(KeyEvent e) {
        if (play == true) {
            boolean B = false;
            switch (e.getKeyCode()) {

                case KeyEvent.VK_RIGHT:

                    if (maze.getValue(Y + 1, X) != Maze.OBSTICLE) {

                        if (maze.getValue(Y + 1, X) == Maze.BONUS) {
                            do {
                                B = maze.randomBonus();
                            } while (B == false);

                            maze.setValue(++Y, X, Maze.START_LOC_VALUE);
                            if (Y - 1 == ay && X == bx) {

                                maze.setValue(ay, bx, Maze.ENNEMI);
                            } else {
                                maze.setValue(Y - 1, X, (short) 0);
                            }

                            score += 5;
                            System.out.println("votre score est " + score);

                        } else if (maze.getValue(Y + 1, X) == Maze.ENNEMI) {
                            maze.setValue(++Y, X, Maze.START_LOC_VALUE);
                            score -= 2;
                            System.out.println("votre score est " + score);

                            if (Y - 1 == ay && X == bx) {

                                maze.setValue(ay, bx, Maze.ENNEMI);
                            } else {
                                maze.setValue(Y - 1, X, (short) 0);
                            }
                            ay = Y;
                            bx = X;

                        } else {
                            maze.setValue(++Y, X, Maze.START_LOC_VALUE);

                            if (Y - 1 == ay && X == bx) {

                                maze.setValue(ay, bx, Maze.ENNEMI);
                            } else {
                                maze.setValue(Y - 1, X, (short) 0);
                            }
                        }

                    }

                    break;

                case KeyEvent.VK_LEFT:
                    if (maze.getValue(Y - 1, X) != Maze.OBSTICLE) {

                        if (maze.getValue(Y - 1, X) == Maze.BONUS) {
                            score += 5;
                            System.out.println("votre score est " + score);

                            do {
                                B = maze.randomBonus();

                            } while (B == false);

                            maze.setValue(--Y, X, Maze.START_LOC_VALUE);

                            if (Y + 1 == ay && X == bx) {

                                maze.setValue(ay, bx, Maze.ENNEMI);
                            } else {
                                maze.setValue(Y + 1, X, (short) 0);
                            }

                        } else if (maze.getValue(Y - 1, X) == Maze.ENNEMI) {
                            score -= 2;
                            System.out.println("votre score est " + score);

                            maze.setValue(--Y, X, Maze.START_LOC_VALUE);
                            if (Y + 1 == ay && X == bx) {

                                maze.setValue(ay, bx, Maze.ENNEMI);
                            } else {
                                maze.setValue(Y + 1, X, (short) 0);
                            }
                            ay = Y;
                            bx = X;

                        } else {
                            maze.setValue(--Y, X, Maze.START_LOC_VALUE);

                            if (Y + 1 == ay && X == bx) {

                                maze.setValue(ay, bx, Maze.ENNEMI);
                            } else {
                                maze.setValue(Y + 1, X, (short) 0);
                            }

                        }
                    }
                    break;

                case KeyEvent.VK_UP:
                    if (maze.getValue(Y, X - 1) != Maze.OBSTICLE) {

                        if (maze.getValue(Y, X - 1) == Maze.BONUS) {
                            do {
                                B = maze.randomBonus();
                            } while (B == false);

                            score += 5;
                            System.out.println("votre score est " + score);
                            maze.setValue(Y, --X, Maze.START_LOC_VALUE);
                            if (Y == ay && X + 1 == bx) {

                                maze.setValue(ay, bx, Maze.ENNEMI);
                            } else {
                                maze.setValue(Y, X + 1, (short) 0);
                            }

                        } else if (maze.getValue(Y, X - 1) == Maze.ENNEMI) {
                            score -= 2;
                            System.out.println("votre score est " + score);

                            maze.setValue(Y, --X, Maze.START_LOC_VALUE);
                            if (Y == ay && X + 1 == bx) {

                                maze.setValue(ay, bx, Maze.ENNEMI);
                            } else {
                                maze.setValue(Y, X + 1, (short) 0);
                            }
                            ay = Y;
                            bx = X;

                        } else {
                            maze.setValue(Y, --X, Maze.START_LOC_VALUE);
                            if (Y == ay && X + 1 == bx) {

                                maze.setValue(ay, bx, Maze.ENNEMI);
                            } else {
                                maze.setValue(Y, X + 1, (short) 0);
                            }
                        }

                    }

                    break;

                case KeyEvent.VK_DOWN:
                    if (maze.getValue(Y, X + 1) != Maze.OBSTICLE) {

                        if (maze.getValue(Y, X + 1) == Maze.BONUS) {
                            do {
                                B = maze.randomBonus();
                            } while (B == false);

                            score += 5;
                            System.out.println("votre score est " + score);

                            maze.setValue(Y, ++X, Maze.START_LOC_VALUE);
                            if (Y == ay && X - 1 == bx) {

                                maze.setValue(ay, bx, Maze.ENNEMI);
                            } else {
                                maze.setValue(Y, X - 1, (short) 0);
                            }
                        } //SI LE PAS SUIVANT EST UN ENNEMI
                        else if (maze.getValue(Y, X + 1) == Maze.ENNEMI) {
                            score -= 2;
                            System.out.println("votre score est " + score);

                            maze.setValue(Y, ++X, Maze.START_LOC_VALUE);

                            if (Y == ay && X - 1 == bx) {

                                maze.setValue(ay, bx, Maze.ENNEMI);

                            } else {
                                maze.setValue(Y, X - 1, (short) 0);
                            }
                            ay = Y;
                            bx = X;

                        } //FIN DE "SI LE PAS SUIVANT EST UN ENNEMI
                        else {
                            maze.setValue(Y, ++X, Maze.START_LOC_VALUE);

                            if (Y == ay && X - 1 == bx) {

                                maze.setValue(ay, bx, Maze.ENNEMI);
                            } else {
                                maze.setValue(Y, X - 1, (short) 0);
                            }
                        }
                    }

                    break;

            }
            maze.setValue(0, 0, (short) 0);
            repaint();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (play == true) {
            System.out.println("help avec algorithme A*");
            score -= 3;
            System.out.println("votre score est " + score);


            g2.setColor(Color.black);
            Dimension[] path = SerchAstar.getPath();
            for (int i = 1; i < (path.length - 1); i++) {
                int x = path[i].width;
                int y = path[i].height;
                short val = maze4.getValue(x, y);
                g2.drawString("" + (path.length - i), 16 + x * 29, 19 + y * 29);
                g.drawImage(image, 30, 40, 700, 700, null);

            }

            Thread t = new Thread() {
                public void run() {

                    try {
                        sleep(10000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MazeDepthFirstSearch.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    System.out.println("hello");
//                            g2.setColor(Color.black);
//                        Dimension[] path = currentSearchEngine.getPath();
//                        for (int i = 1; i < (path.length - 1); i++) {
//                            int x = path[i].width;
//                            int y = path[i].height;
//                            short val = maze.getValue(x, y);
//                            g2.drawString("" + (path.length - i), 16 + x * 29, 19 + y * 29);
//                            g.drawImage(image, 30, 40, 700, 700, null);
//
//                        }
                    repaint();
                }

            };
            t.start();

        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
