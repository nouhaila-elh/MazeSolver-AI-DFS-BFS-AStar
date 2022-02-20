
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class Maze - private class for representing search space as a two-dimensional
 * maze
 */
public class Maze {

    public static short BONUS = -5;
    public static short ENNEMI = -4;
    public static short OBSTICLE = -1;
    public static short START_LOC_VALUE = -2;
    public static short GOAL_LOC_VALUE = -3;
    int width = 0;
    int height = 0;
    public Dimension startLoc = new Dimension();
    public Dimension goalLoc = new Dimension();
    /**
     * The maze (or search space) data is stored as a short integer rather than
     * as a boolean so that bread-first style searches can use the array to
     * store search depth. A value of -1 indicates a barrier in the maze.
     */
    private final short[][] maze;

    public Maze(int width, int height) {
       
        this.width = width;
        this.height = height;
        maze = new short[width + 3][height + 3];

        // ---------------
        short[][] matrice = new short[21][21];

        int sizeW = 0;
        int j = 0;
        int i;
       
//------- affecter la valeur 0 a tous les case du matrice representront après les cases maze 
        for (i = 0; i < width + 2; i++) {
            for (j = 0; j < height + 2; j++) {
                maze[i][j] = 0;
            }
        }
//------- affecter la valeur -1 aux cases situant dans la frontière
        for (i = 0; i < height + 3; i++) {
            maze[0][i] = maze[width + 1][i] = OBSTICLE;
        }
        for (i = 0; i < width + 3; i++) {
            maze[i][0] = maze[i][height + 1] = OBSTICLE;
        }
       
 File fileDirs=null;
 //-------- des conditions pour choisir le fichier contenant maze qu'on va lire ----------
 
 
 //-------- si le width est de 21 on choisie le fichier avce maze de 21 x 21
        if(width==21){
            fileDirs = new File("C:\\Users\\Home\\Documents\\NetBeansProjects\\Ai_projet\\src\\mazeFile\\LABY_21x21.txt");
        }
//-------- si le width est de 41 on choisie le fichier avce maze de 41 x 41
        else if(width==41){
            fileDirs = new File("C:\\Users\\Home\\Documents\\NetBeansProjects\\Ai_projet\\src\\mazeFile\\LABY_41x41.txt");
        }
//-------- si le width est de 61 on choisie le fichier avce maze de 61 x 61        
        else if(width==61){
            fileDirs = new File("C:\\Users\\Home\\Documents\\NetBeansProjects\\Ai_projet\\src\\mazeFile\\LABY_61x61.txt");
        }
        
//---------- lire le fichier choisie ------------------
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
//----------- pour chaque espace plein ou vide du fichier on remplira la matrice maze [j][i]
        i = 1;
        try {
            while ((str = in.readLine()) != null) {

                for (j = 0; j < str.length(); j++) {
                    if (str.charAt(j) == ' ') {
                        maze[j + 1][i] = 0;
                    } else {
                        maze[j + 1][i] = -1;
                    }

                }
                i++;
            }
        } catch (IOException ex) {
            Logger.getLogger(Maze.class.getName()).log(Level.SEVERE, null, ex);
        }

// ---------- affichage du matrice dans console pour vérification---------
        for (i = 0; i < maze.length; i++) {

            for (j = 0; j < maze[i].length; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }

        /**
         * Specify the starting location
         */
        startLoc.width = 0;
        startLoc.height = 0;
        maze[1][1] = START_LOC_VALUE;
        /**
         * Specify the goal location
         */
        goalLoc.width = width - 1;
        goalLoc.height = height - 1;
        maze[width][height] = GOAL_LOC_VALUE;

        /**
         * Randomize the maze by putting up arbitray obsticals
         */
        java.util.Random rnd = new java.util.Random();
        
//---------- affecter des obstacles (Ennemi) du manière random
        /* */
        //int max_obsticles = (width * height)*2/100;
        int max_obsticles = 11;
        for (i = 0; i < max_obsticles; i++) {
            int x = rnd.nextInt(width - 1) + 1;
            int y = rnd.nextInt(height - 1) + 1;
            if (maze[x + 1][y + 1] != OBSTICLE && maze[x + 1][y + 1] != START_LOC_VALUE && maze[x + 1][y + 1] != GOAL_LOC_VALUE) {
                maze[x + 1][y + 1] = ENNEMI;
            }
        }

        /**
         * Randomize the maze by putting up arbitray bunus
         */
        /* */ java.util.Random rnd2 = new java.util.Random();

        // int max_obsticles2 = (width * height)*30/100;
//---------- affecter des bonus du manière random        
        int max_obsticles2 = 11;
        for (i = 0; i < max_obsticles2; i++) {
            int x = rnd2.nextInt(width - 1) + 1;
            int y = rnd2.nextInt(height - 1) + 1;
            if (maze[x + 1][y + 1] != OBSTICLE && maze[x + 1][y + 1] != ENNEMI && maze[x + 1][y + 1] != START_LOC_VALUE && maze[x + 1][y + 1] != GOAL_LOC_VALUE) {
                maze[x + 1][y + 1] = BONUS;
            }
        }

    }
    java.util.Random rnd2 = new java.util.Random();
    
            public boolean randomBonus(){
               /* */ 

        // int max_obsticles2 = (width * height)*30/100;
      
            int x = rnd2.nextInt(width - 1) + 1;
            int y = rnd2.nextInt(height - 1) + 1;
            if (maze[x + 1][y + 1] != OBSTICLE && maze[x + 1][y + 1] != ENNEMI && maze[x + 1][y + 1] != START_LOC_VALUE && maze[x + 1][y + 1] != GOAL_LOC_VALUE) {
                maze[x + 1][y + 1] = BONUS;
                return true;
            
        } return false;}

    synchronized public short getValue(int x, int y) {
        return maze[x + 1][y + 1];
    }

    synchronized public void setValue(int x, int y, short value) {
        maze[x + 1][y + 1] = value;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
