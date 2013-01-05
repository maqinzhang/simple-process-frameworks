package com.simple.process.nodes;

import org.springframework.stereotype.Service;

import com.opensource.frameworks.processframework.bind.annotations.RequestVar;

@Service
public class SayHelloWorld {
	public void execute(@RequestVar("name") String name) {
		System.out.println(SayHelloWorld.class+",Hello world,your name is " + name + "!");
	}
}
