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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author meni
 */
public class Graph {

    public static final String INFO_QUERY = "info";

    /**
     * Generage
     *
     * @param pathToFile
     * @return
     */
    public static EdgeWeightedDigraph buildGraphFromFile(String pathToFile) {
        File file = new File(pathToFile);
        In in = new In(file);
        EdgeWeightedDigraph ewd = new EdgeWeightedDigraph(in);
        return ewd;
    }

    public static void runAlgorithm(String pathToGraphFile, String pathToQueryFile, String pathToAnsFile) throws FileNotFoundException {
        long start = System.currentTimeMillis();
        EdgeWeightedDigraph G = buildGraphFromFile(pathToGraphFile);
        Graph_algo graph_algo = new Graph_algo(G);

        File queryFile, ansFile;
        queryFile = new File(pathToQueryFile);
        ansFile = new File(pathToAnsFile);
        FileOutputStream ansFileOS = new FileOutputStream(ansFile);

        In queryIn = new In(queryFile);
        Out ansOut = new Out(ansFileOS);

        int numberOfQuery = queryIn.readInt();

        for (int i = 0; i < numberOfQuery; i++) {
            // if the next query is from->to query
            if (queryIn.hasNextInt()) {
                int from = queryIn.readInt();
                int to = queryIn.readInt();
                int numberOfBlackList = queryIn.readInt();
                ArrayList<Integer> blackList = new ArrayList<>();
                for (int j = 0; j < numberOfBlackList; j++) {
                    blackList.add(queryIn.readInt());
                }

                DijkstraSP dijkstraSP = new DijkstraSP(G, from, blackList);
                ansOut.printf("%d %d %d %s %f\n", from, to, numberOfBlackList, buildBlackListArgsString(blackList), dijkstraSP.distTo(to));
            } else if (queryIn.readString().equalsIgnoreCase(INFO_QUERY)) {
                // if this is a info query
                double[] info = graph_algo.getInfo();
                String tie = G.checkTriangleInequality() ? "TIE" : "!TIE";
                ansOut.printf("Graph: |V|=" + G.V() + ", |E|=" + G.E() / 2 + ", " + tie + ",  Radius:" + info[0] + ",  Diameter:" + info[1]+"\n");
            }
        }
        
        ansOut.print("total time: " + (System.currentTimeMillis() - start) + "\n");
    }

    private static String buildBlackListArgsString(List<Integer> blackList) {
        StringBuilder builder = new StringBuilder();
        for (int vertex : blackList) {
            builder.append(vertex).append(" ");
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        String pathToGraphFile = "input-G.txt";
        String pathToQueryFile = "test-input-G.txt";
        String pathToAnsFile = "ans-input-g-t.txt";

//        String pathToGraphFile = "testFiles\\Gstar.txt";
//        String pathToQueryFile = "testFiles\\starTest.txt";
//        String pathToAnsFile = "testFiles\\starAns.txt";
        long start = System.currentTimeMillis();
        try {
            runAlgorithm(pathToGraphFile, pathToQueryFile, pathToAnsFile);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

        System.out.println("total time: " + (System.currentTimeMillis() - start));
    }
}
