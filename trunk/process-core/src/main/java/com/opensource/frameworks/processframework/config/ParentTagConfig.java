package com.opensource.frameworks.processframework.config;

import java.util.LinkedList;
import java.util.List;

public class ParentTagConfig implements ElementConfig{
	final List<ElementConfig> childs=new LinkedList<ElementConfig>();

	public List<ElementConfig> getChilds() {
		return childs;
	} 
	public void addChild(ElementConfig child){
		childs.add(child);
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName()+" [childs=" + childs + "]";
	}
	
}
