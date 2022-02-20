
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Stack;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Home
 */
public class searchEngine extends AbstractSearchEngine{
      ArrayList<Dimension> L= new ArrayList<Dimension>(), T=new ArrayList<Dimension>();
    Stack<Dimension>  pile=new Stack<Dimension>();
    Dimension predecessor[][] = new Dimension[maze.getWidth()][maze.getHeight()];

    public searchEngine(int width, int height) {
        super(width, height);
        
        // $$2
        for (int i=0; i<width; i++) 
        for (int j=0; j<height; j++) 
            predecessor[i][j] = null;

        pile.push(startLoc);
        T.add(goalLoc);

        maxDepth = 0;
        if (!isSearching) {
            searchPath[maxDepth++] = goalLoc;
            for (int i=0; i<100; i++) {
                searchPath[maxDepth] = predecessor[searchPath[maxDepth - 1].width][searchPath[maxDepth - 1].height];
                maxDepth++;
                if (equals(searchPath[maxDepth - 1], startLoc))  break;  // back to starting node
            }
        }
        
       
    
    }
    

}
