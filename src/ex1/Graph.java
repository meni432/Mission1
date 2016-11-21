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

    public static double[] getInfo(EdgeWeightedDigraph G) {
        StringBuilder builder = new StringBuilder();
        double[] eccentricity = new double[G.V()];
        for (int i = 0; i < G.V(); i++) {
            DijkstraSP dijkstraSP = new DijkstraSP(G, i);
            eccentricity[i] = dijkstraSP.getEccentricity();
        }
        List eccentricityList = Arrays.asList(eccentricity);
        double radius = EX1.min(eccentricity);
        double diameter = EX1.max(eccentricity);

        return new double[]{radius, diameter};
    }

    public static void runAlgorithm(String pathToGraphFile, String pathToQueryFile, String pathToAnsFile) throws FileNotFoundException {
        EdgeWeightedDigraph G = buildGraphFromFile(pathToGraphFile);

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
                double[] info = getInfo(G);
                ansOut.printf("Graph: |V|=" + G.V() + ", |E|=" + G.E() / 2 + ",  Radius:" + info[0] + ",  Diameter:" + info[1]);
            }
        }
    }

    private static String buildBlackListArgsString(List<Integer> blackList) {
        StringBuilder builder = new StringBuilder();
        for (int vertex : blackList) {
            builder.append(vertex).append(" ");
        }
        return builder.toString();
    }

    public static void main(String[] args) {
//        String pathToGraphFile = "G-path.txt";
//        String pathToQueryFile = "test2.txt";
//        String pathToAnsFile = "ans2.txt";

        String pathToGraphFile = "testFiles\\Gstar.txt";
        String pathToQueryFile = "testFiles\\starTest.txt";
        String pathToAnsFile = "testFiles\\starAns.txt";

        try {
            runAlgorithm(pathToGraphFile, pathToQueryFile, pathToAnsFile);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
