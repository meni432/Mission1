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
        String grapgPath = "testFiles\\Gstar.txt";
        String QueryPath = "testFiles\\starTest.txt";
        String expectedPath = "testFiles\\starExpectedAns.txt";
        String ansPath = "testFiles\\starAns.txt";
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

//    System.out.println("the same? " + equals);

    // List Diff
    List<String> c1 = Files.readAllLines(file.toPath());
    List<String> c2 = Files.readAllLines(secondFile.toPath());

    return c1.containsAll(c2);
//    System.out.println("the same? " + containsAll);   
    }
}
