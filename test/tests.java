/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static ex1.Graph.runAlgorithm;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author moshe
 */
public class tests {

    public tests() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void starGraph() throws FileNotFoundException, IOException {
        String grapgPath = "testFiles/Gstar/Gstar.txt";
        String QueryPath = "testFiles/Gstar/starTest.txt";
        String expectedPath = "testFiles/Gstar/starExpectedAns.txt";
        String ansPath = "testFiles/Gstar/starAns.txt";
        runAlgorithm(grapgPath, QueryPath, ansPath);

        boolean isEqual = isEqualFiles(expectedPath, ansPath);
        assert isEqual;

    }

    @Test
    public void fullGraph() throws FileNotFoundException, IOException {
        String grapgPath = "testFiles/FullGraph/FullGraph.txt";
        String QueryPath = "testFiles/FullGraph/FullGraphTest.txt";
        String expectedPath = "testFiles/FullGraph/FullGraphExpectedAns.txt";
        String ansPath = "testFiles/FullGraph/FullGraphAns.txt";
        runAlgorithm(grapgPath, QueryPath, ansPath);

        boolean isEqual = isEqualFiles(expectedPath, ansPath);
        assert isEqual;
    }

    @Test
    public void whellGraph() throws FileNotFoundException, IOException {
        String grapgPath = "testFiles/WhellGraph/graph.txt";
        String QueryPath = "testFiles/WhellGraph/graphTest.txt";
        String expectedPath = "testFiles/WhellGraph/expectedAns.txt";
        String ansPath = "testFiles/WhellGraph/ans.txt";
        runAlgorithm(grapgPath, QueryPath, ansPath);

        boolean isEqual = isEqualFiles(expectedPath, ansPath);
        assert isEqual;
    }

    @Test
    public void cycleGraph() throws FileNotFoundException, IOException {
        String grapgPath = "testFiles/CycleGraph/graph.txt";
        String QueryPath = "testFiles/CycleGraph/graphTest.txt";
        String expectedPath = "testFiles/CycleGraph/expectedAns.txt";
        String ansPath = "testFiles/CycleGraph/ans.txt";
        runAlgorithm(grapgPath, QueryPath, ansPath);

        boolean isEqual = isEqualFiles(expectedPath, ansPath);
        assert isEqual;
    }

    @Test
    public void bigGraph() throws FileNotFoundException, IOException {
        String grapgPath = "testFiles/BigGraph/graph.txt";
        String QueryPath = "testFiles/BigGraph/graphTest.txt";
        String expectedPath = "testFiles/BigGraph/expectedAns.txt";
        String ansPath = "testFiles/BigGraph/ans.txt";
        runAlgorithm(grapgPath, QueryPath, ansPath);

        boolean isEqual = isEqualFiles(expectedPath, ansPath);
        assert isEqual;
    }

    @Test
    public void bbmBigGraph() throws FileNotFoundException, IOException {
        String grapgPath = "testFiles/BBMBigGraph/graph.txt";
        String QueryPath = "testFiles/BBMBigGraph/graphTest.txt";
        String expectedPath = "testFiles/BBMBigGraph/expectedAns.txt";
        String ansPath = "testFiles/BBMBigGraph/ans.txt";
        runAlgorithm(grapgPath, QueryPath, ansPath);

        boolean isEqual = isEqualFiles(expectedPath, ansPath);
        assert isEqual;
    }

    //check if 2 files are equals
    private boolean isEqualFiles(String expectedPath, String ansPath) throws IOException {
        File file = new File(expectedPath);
        File secondFile = new File(ansPath);

        // Bytes diff
        byte[] b1 = Files.readAllBytes(file.toPath());
        byte[] b2 = Files.readAllBytes(secondFile.toPath());

        boolean equals = Arrays.equals(b1, b2);

        // List Diff
        List<String> c1 = Files.readAllLines(file.toPath());
        List<String> c2 = Files.readAllLines(secondFile.toPath());

        // remove the last line (time line...)
        c1.remove(c1.size() - 1);
        c2.remove(c2.size() - 1);

        return c1.containsAll(c2);
    }
}
