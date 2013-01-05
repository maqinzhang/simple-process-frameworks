package com.simple.process.nodes;

import org.springframework.stereotype.Service;

@Service
public class ExceptionShowEnd {
	public void execute() {
		System.out.println(ExceptionShowEnd.class+",Exception End!=========================================");
	}
}
