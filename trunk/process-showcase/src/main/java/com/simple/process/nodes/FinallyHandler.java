package com.simple.process.nodes;

import org.springframework.stereotype.Service;

@Service
public class FinallyHandler {
	public void execute() {
		System.out
				.println(FinallyHandler.class+",finally executed!++++++++++++++++++++++++++++++++++++++++++++++++++++");
	}
}
