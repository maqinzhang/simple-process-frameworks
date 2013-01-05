package com.simple.process.nodes;

import org.springframework.stereotype.Service;

/**
 * @author luolishu
 * 
 */
@Service
public class ProcessStart {

	public void execute() {
		System.out
				.println(ProcessStart.class+",-------------------------Start Process--------------------------------");
	}

}
