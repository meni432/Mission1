/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex1;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author meni
 */
public class Graph_algo {

    EdgeWeightedDigraph G;
    DijkstraSP[] dijkstraSPs;

    public Graph_algo(EdgeWeightedDigraph ewd) {
        G = ewd;
        dijkstraSPs = new DijkstraSP[ewd.V()];
    }

    public DijkstraSP getDijkstraSP(int start) {
        if (dijkstraSPs[start] == null) {
            dijkstraSPs[start] = new DijkstraSP(G, start);
        }
        return dijkstraSPs[start];
    }
    
    public DijkstraSP getDijkstraSP(int start, List<Integer> BlackList){
        return new DijkstraSP(G, start, BlackList);
    }

    public double[] getInfo() {
        StringBuilder builder = new StringBuilder();
        double[] eccentricity = new double[G.V()];
        for (int i = 0; i < G.V(); i++) {
            DijkstraSP dijkstraSP = getDijkstraSP(i);
            eccentricity[i] = dijkstraSP.getEccentricity();
        }
        List eccentricityList = Arrays.asList(eccentricity);
        double radius = EX1.min(eccentricity);
        double diameter = EX1.max(eccentricity);

        return new double[]{radius, diameter};
    }
    
    public boolean checkTriangleInequality(){
        return G.checkTriangleInequality();
    }
}
