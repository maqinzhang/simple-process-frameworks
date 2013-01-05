package com.opensource.frameworks.processframework.factory;
 
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.opensource.frameworks.processframework.config.DefaultConfigurationManager;
import com.opensource.frameworks.processframework.config.NodeConfig;
import com.opensource.frameworks.processframework.invocation.DefaultExecutionNode;
import com.opensource.frameworks.processframework.invocation.ExecutionNode;
import com.opensource.frameworks.processframework.invocation.Invocation;
import com.opensource.frameworks.processframework.invocation.NodeInvocation;

public class NodeInvocationFactory implements InvocationFactory<NodeConfig> {
    public static final List<ExecutionNode> nodes=Collections.synchronizedList(new LinkedList<ExecutionNode>());
	@Override
	public Invocation create(NodeConfig config) {
		ObjectFactory objectFactory=DefaultConfigurationManager.getInstance().getObjectFactory();
	 
		ExecutionNode node=new DefaultExecutionNode(config, objectFactory);
		nodes.add(node);
		return new 	NodeInvocation(node);
	}

}
