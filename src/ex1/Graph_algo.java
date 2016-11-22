package ex1;

import java.util.Arrays;
import java.util.List;

/**
 * 
 */
public class Graph_algo {

    private EdgeWeightedDigraph G;
    private DijkstraSP[] dijkstraSPs;

    /**
     * costructor of class Graph_algo
     * @param ewd EdgeWeightedDigraph representing the graph
     */
    public Graph_algo(EdgeWeightedDigraph ewd) {
        G = ewd;
        dijkstraSPs = new DijkstraSP[ewd.V()];
    }

    /**
     * this function is factory for DijkstraSP object without blacklist
     * to improve the optimition, the class cache the object for Future uses
     * @param start start vertex
     * @return DijkstraSP object for given start
     */
    public DijkstraSP getDijkstraSP(int start) {
        if (dijkstraSPs[start] == null) {
            dijkstraSPs[start] = new DijkstraSP(G, start);
        }
        return dijkstraSPs[start];
    }
    
    /**
     * @param start start vertex
     * @param BlackList list of vertex that algorithm not allow to used them
     * @return DijkstraSP object for given start and BlackList
     */
    public DijkstraSP getDijkstraSP(int start, List<Integer> BlackList){
        return new DijkstraSP(G, start, BlackList);
    }

    /**
     * @return double Array with two args [radius of given graph, diameter of given graph]
     */
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
    
    /**
     * @return if the graph satisfy the triangle inequality
     */
    public boolean checkTriangleInequality(){
        return G.checkTriangleInequality();
    }
}
