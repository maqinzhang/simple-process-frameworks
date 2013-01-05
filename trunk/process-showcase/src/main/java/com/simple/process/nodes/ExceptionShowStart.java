package com.simple.process.nodes;

import org.springframework.stereotype.Service;

@Service
public class ExceptionShowStart {

	public void execute() {
		System.out.println(ExceptionShowStart.class+",Exception start!=========================================");
	}
}
