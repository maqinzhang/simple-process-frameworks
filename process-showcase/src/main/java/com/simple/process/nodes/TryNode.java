/**
 * 
 */
package com.simple.process.nodes;

import org.springframework.stereotype.Service;

/**
 * @author luolishu
 * 
 */
@Service
public class TryNode {
	public void execute() {
		System.out
				.println(TryNode.class
						+ ",-------------------------Try node start--------------------------------");
	}
}
