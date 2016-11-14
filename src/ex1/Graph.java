/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 *
 * @author meni
 */
public class Graph {
    public static final String INFO_QUERY = "info";
    
    public static EdgeWeightedDigraph buildGraphFromFile(String pathToFile) {
        File file = new File(pathToFile);
        In in = new In(file);
        EdgeWeightedDigraph ewd = new EdgeWeightedDigraph(in);
        return ewd;
    }
    
    public static void runAlgorithm(String pathToGraphFile, String pathToQueryFile, String pathToAnsFile) throws FileNotFoundException{
        EdgeWeightedDigraph G = buildGraphFromFile(pathToGraphFile);
        
        File queryFile, ansFile;
        queryFile = new File(pathToQueryFile);
        ansFile = new File(pathToAnsFile);
        FileOutputStream ansFileOS = new FileOutputStream(ansFile);
        
        In queryIn = new In(queryFile);
        Out ansOut = new Out(ansFileOS);
        
        int numberOfQuery = queryIn.readInt();
        
        for (int i = 0; i < numberOfQuery; i++){
            // if the next query is from->to query
            if (queryIn.hasNextInt()){
                int from = queryIn.readInt();
                int to = queryIn.readInt();
                int numberOfBlackList = queryIn.readInt();
                ArrayList<Integer> blackList = new ArrayList<>();
                for (int j = 0; j < numberOfBlackList; j++){
                    blackList.add(queryIn.readInt());
                }
                
                DijkstraSP dijkstraSP = new DijkstraSP(G, from);
                ansOut.printf("%d %d %d %lf\n", from, to, numberOfBlackList, dijkstraSP.distTo(to));
            } else if (queryIn.readLine().equals(INFO_QUERY)){
                // TODO write to file info
            }
        }
        
    }

    public static void main(String[] args) {
        String pathToGraphFile;
        String pathToQueryFile;
        String pathToAnsFile;
        
        
    }
}
