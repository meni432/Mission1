package ex1;

import java.util.HashSet;

/**
 * ****************************************************************************
 * Compilation: javac EdgeWeightedDigraph.java Execution: java
 * EdgeWeightedDigraph digraph.txt Dependencies: Bag.java DirectedEdge.java Data
 * files: http://algs4.cs.princeton.edu/44st/tinyEWD.txt
 * http://algs4.cs.princeton.edu/44st/mediumEWD.txt
 * http://algs4.cs.princeton.edu/44st/largeEWD.txt
 *
 * An edge-weighted digraph, implemented using adjacency lists.
 *
 *****************************************************************************
 */
/**
 * The {@code EdgeWeightedDigraph} class represents a edge-weighted digraph of
 * vertices named 0 through <em>V</em> - 1, where each directed edge is of type
 * {@link DirectedEdge} and has a real-valued weight. It supports the following
 * two primary operations: add a directed edge to the digraph and iterate over
 * all of edges incident from a given vertex. It also provides methods for
 * returning the number of vertices <em>V</em> and the number of edges
 * <em>E</em>. Parallel edges and self-loops are permitted.
 * <p>
 * This implementation uses an adjacency-lists representation, which is a
 * vertex-indexed array of @link{Bag} objects. All operations take constant time
 * (in the worst case) except iterating over the edges incident from a given
 * vertex, which takes time proportional to the number of such edges.
 * <p>
 * For additional documentation, see
 * <a href="http://algs4.cs.princeton.edu/44sp">Section 4.4</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class EdgeWeightedDigraph {

    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;                // number of vertices in this digraph
    private int E;                      // number of edges in this digraph
    private Bag<DirectedEdge>[] adj;    // adj[v] = adjacency list for vertex v
    private int[] indegree;             // indegree[v] = indegree of vertex v

    private double[][] fffMat;

    /**
     * Initializes an empty edge-weighted digraph with {@code V} vertices and 0
     * edges.
     *
     * @param V the number of vertices
     * @throws IllegalArgumentException if {@code V < 0}
     */
    public EdgeWeightedDigraph(int V) {
        if (V < 0) {
            throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
        }
        this.V = V;
        this.E = 0;
        this.indegree = new int[V];
        adj = (Bag<DirectedEdge>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<DirectedEdge>();
        }
    }

    /**
     * Initializes an edge-weighted digraph from the specified input stream. The
     * format is the number of vertices <em>V</em>, followed by the number of
     * edges <em>E</em>, followed by <em>E</em> pairs of vertices and edge
     * weights, with each entry separated by whitespace.
     *
     * @param in the input stream
     * @throws IndexOutOfBoundsException if the endpoints of any edge are not in
     * prescribed range
     * @throws IllegalArgumentException if the number of vertices or edges is
     * negative
     */
    public EdgeWeightedDigraph(In in) {
        this(in.readInt());
        fffMat = new double[V][V];
        initialMatrix(fffMat);
        int E = in.readInt();
        if (E < 0) {
            throw new IllegalArgumentException("Number of edges must be nonnegative");
        }
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            if (v < 0 || v >= V) {
                throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (V - 1));
            }
            if (w < 0 || w >= V) {
                throw new IndexOutOfBoundsException("vertex " + w + " is not between 0 and " + (V - 1));
            }
            double weight = in.readDouble();
            fffMat[v][w] = weight;
            fffMat[w][v] = weight;
            addEdge(new DirectedEdge(v, w, weight));
            addEdge(new DirectedEdge(w, v, weight));
        }
    }

    /**
     * initial flowyd-warshel matrix
     * fill the main diagonal in zeros, all others edges in inifinity
     * @param M matrix to initialize
     */
    private void initialMatrix(double[][] M) {
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                if (i != j) {
                    M[i][j] = Double.POSITIVE_INFINITY;
                } else {
                    M[i][j] = 0;
                }
            }
        }
    }

    /**
     * check if the graph satisfied the triangle inequality
     * @return true if satisfied, else satisfied
     */
    public boolean checkTriangleInequality() {
        for (int k = 0; k < fffMat.length; k++) {
            for (int i = 0; i < fffMat.length; i++) {
                for (int j = 0; j < fffMat.length; j++) {
                    if (fffMat[i][k] != Double.POSITIVE_INFINITY && fffMat[k][j] != Double.POSITIVE_INFINITY) {
                        if ((fffMat[i][j] > fffMat[i][k] + fffMat[k][j]
                                || fffMat[i][k] > fffMat[i][j] + fffMat[j][k]
                                || fffMat[k][j] > fffMat[k][i] + fffMat[i][j])) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * Returns the number of vertices in this edge-weighted digraph.
     *
     * @return the number of vertices in this edge-weighted digraph
     */
    public int V() {
        return V;
    }

    /**
     * Returns the number of edges in this edge-weighted digraph.
     *
     * @return the number of edges in this edge-weighted digraph
     */
    public int E() {
        return E;
    }

    // throw an IndexOutOfBoundsException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        if (v < 0 || v >= V) {
            throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (V - 1));
        }
    }

    /**
     * Adds the directed edge {@code e} to this edge-weighted digraph.
     *
     * @param e the edge
     * @throws IndexOutOfBoundsException unless endpoints of edge are between
     * {@code 0} and {@code V-1}
     */
    public void addEdge(DirectedEdge e) {
        int v = e.from();
        int w = e.to();
        validateVertex(v);
        validateVertex(w);
        adj[v].add(e);
        indegree[w]++;
        E++;
    }

    /**
     * Returns the directed edges incident from vertex {@code v}.
     *
     * @param v the vertex
     * @return the directed edges incident from vertex {@code v} as an Iterable
     * @throws IndexOutOfBoundsException unless {@code 0 <= v < V}
     */
    public Iterable<DirectedEdge> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    /**
     * Returns the number of directed edges incident from vertex {@code v}. This
     * is known as the <em>outdegree</em> of vertex {@code v}.
     *
     * @param v the vertex
     * @return the outdegree of vertex {@code v}
     * @throws IndexOutOfBoundsException unless {@code 0 <= v < V}
     */
    public int outdegree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    /**
     * Returns the number of directed edges incident to vertex {@code v}. This
     * is known as the <em>indegree</em> of vertex {@code v}.
     *
     * @param v the vertex
     * @return the indegree of vertex {@code v}
     * @throws IndexOutOfBoundsException unless {@code 0 <= v < V}
     */
    public int indegree(int v) {
        validateVertex(v);
        return indegree[v];
    }

    /**
     * Returns all directed edges in this edge-weighted digraph. To iterate over
     * the edges in this edge-weighted digraph, use foreach notation:
     * {@code for (DirectedEdge e : G.edges())}.
     *
     * @return all edges in this edge-weighted digraph, as an iterable
     */
    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> list = new Bag<DirectedEdge>();
        for (int v = 0; v < V; v++) {
            for (DirectedEdge e : adj(v)) {
                list.add(e);
            }
        }
        return list;
    }

    /**
     * Returns a string representation of this edge-weighted digraph.
     *
     * @return the number of vertices <em>V</em>, followed by the number of
     * edges <em>E</em>, followed by the <em>V</em> adjacency lists of edges
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (DirectedEdge e : adj[v]) {
                s.append(e + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    /**
     * Unit tests the {@code EdgeWeightedDigraph} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);
        StdOut.println(G);
    }

}
