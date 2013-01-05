package com.simple.process.nodes;

import org.springframework.stereotype.Service;

import com.opensource.frameworks.processframework.bind.annotations.RequestVar;

@Service
public class SayHelloToXiaoMing {
	public void execute(@RequestVar("name") String name) {
		System.out.println(SayHelloToXiaoMing.class+",Hello,my name is " + name + ",I am a male!");
	}
}
