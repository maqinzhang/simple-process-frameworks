package com.simple.process.nodes;

import org.springframework.stereotype.Service;

@Service
public class ExceptionHanlder {
	public void execute() {
		System.out.println(ExceptionHanlder.class+",handle Exception!++++++++++++++++++++++++++++++++++++++++++++++++++++");
	}
}
