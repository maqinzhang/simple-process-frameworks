package com.opensource.frameworks.processframework.config;

import java.util.LinkedList;
import java.util.List;

public class ConditionsTagConfig extends ParentTagConfig {
	public List<ElseIfTagConfig> elseIfTags = new LinkedList<ElseIfTagConfig>();
	public ElseTagConfig elseTags;

	public ElseTagConfig getElseTags() {
		return elseTags;
	}

	public void setElseTags(ElseTagConfig elseTags) {
		this.elseTags = elseTags;
	}

	public List<ElseIfTagConfig> getElseIfTags() {
		return elseIfTags;
	}

	public void setElseIfTags(List<ElseIfTagConfig> elseIfTags) {
		this.elseIfTags = elseIfTags;
	}

	public void addElseIf(ElseIfTagConfig elseIf) {
		elseIfTags.add(elseIf);
	}

	@Override
	public String toString() {
		return "ConditionsTagConfig [childs=" + childs + " elseIfTags=" + elseIfTags + ", elseTags="
				+ elseTags + "]";
	}

}
