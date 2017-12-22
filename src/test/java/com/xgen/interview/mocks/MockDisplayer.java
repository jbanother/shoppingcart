package com.xgen.interview.mocks;

import com.xgen.interview.displayers.IDisplayer;
import org.junit.Assert;

import java.util.Queue;

/**
 * Fakes a displayer functionality
 * Checks that output fits to expected
 */
public class MockDisplayer implements IDisplayer {

    private Queue<String> expectedResults;

    public MockDisplayer(Queue<String> expectedResults) {this.expectedResults = expectedResults;}

    public void Display(String data){
        String expectedOutput = expectedResults.poll();
        if (expectedOutput != null) {
            Assert.assertEquals(expectedOutput, data);
        } else {
            Assert.fail("Unexpected data:" + data);
        }
    }
}
