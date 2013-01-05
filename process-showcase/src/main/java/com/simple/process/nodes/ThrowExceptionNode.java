package com.simple.process.nodes;

import org.springframework.stereotype.Service;

import com.simple.process.exception.SampleException;

@Service
public class ThrowExceptionNode {
	public void execute() throws SampleException {
		throw new SampleException(ThrowExceptionNode.class+",throwing exception for test!");
	}
}
