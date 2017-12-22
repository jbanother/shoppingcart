package com.xgen.interview.displayers;

/**
 * This interface defines how to display entries,
 * which were scanned
 */

public interface IDisplayer {

    /**
     * Displays to specific output (cmd, monitor)
     * Depends on impl
     * @param data
     */
    void Display(String data);

}
