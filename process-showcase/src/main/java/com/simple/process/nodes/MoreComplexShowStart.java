package com.simple.process.nodes;

import org.springframework.stereotype.Service;

@Service
public class MoreComplexShowStart {
	public void execute() {
		System.out.println(MoreComplexShowStart.class+",**************************Complex start*******************************");
	}
}
