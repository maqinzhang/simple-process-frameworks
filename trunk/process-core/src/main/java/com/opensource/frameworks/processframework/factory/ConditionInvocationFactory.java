package com.opensource.frameworks.processframework.factory;

import java.util.LinkedList;
import java.util.List;

import com.opensource.frameworks.processframework.config.ConditionsTagConfig;
import com.opensource.frameworks.processframework.config.ElementConfig;
import com.opensource.frameworks.processframework.config.ElseIfTagConfig;
import com.opensource.frameworks.processframework.config.ElseTagConfig;
import com.opensource.frameworks.processframework.invocation.ConditionInvocation;
import com.opensource.frameworks.processframework.invocation.IfInvocation;
import com.opensource.frameworks.processframework.invocation.Invocation;
import com.opensource.frameworks.processframework.utils.CollectionUtils;

public class ConditionInvocationFactory implements
		InvocationFactory<ConditionsTagConfig> {

	@Override
	public Invocation create(ConditionsTagConfig config) {
		List<IfInvocation> invocations = new LinkedList<IfInvocation>();
		List<IfInvocation> elseIfInvocations = new LinkedList<IfInvocation>();
		List<Invocation> elseInvocations = new LinkedList<Invocation>();

		List<ElementConfig> childs = config.getChilds();
		List<ElseIfTagConfig> elseIfs=config.getElseIfTags();
		ElseTagConfig elseTags=config.getElseTags();
		if (!CollectionUtils.isEmpty(childs)) {
			for (ElementConfig item : childs) {
				Invocation invocation=InvocationBuilder.build(item);
				if(invocation!=null){
					invocations.add((IfInvocation) invocation);
				}
			}
		}
		if (!CollectionUtils.isEmpty(elseIfs)) {
			for (ElementConfig item : elseIfs) {
				Invocation invocation=InvocationBuilder.build(item);
				if(invocation!=null){
					elseIfInvocations.add((IfInvocation) invocation);
				}
			}
		}
		if (elseTags!=null) {
			for (ElementConfig item : elseTags.getChilds()) {
				Invocation invocation=InvocationBuilder.build(item);
				if(invocation!=null){
					elseInvocations.add(invocation);
				}
			}
		}
		return new ConditionInvocation(invocations,elseIfInvocations,elseInvocations);
	}

}
