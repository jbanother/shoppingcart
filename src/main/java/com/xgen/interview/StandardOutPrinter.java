
package com.xgen.interview;

public class StandardOutPrinter implements IPrinter {

	@Override
	public void printLine(String line) {
		System.out.println(line);
	}
}
