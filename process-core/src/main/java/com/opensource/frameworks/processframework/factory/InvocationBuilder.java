/**
 * 
 */
package com.opensource.frameworks.processframework.factory;

import java.util.HashMap;
import java.util.Map;

import com.opensource.frameworks.processframework.config.ConditionsTagConfig;
import com.opensource.frameworks.processframework.config.ElementConfig;
import com.opensource.frameworks.processframework.config.ElseIfTagConfig;
import com.opensource.frameworks.processframework.config.ExceptionConfig;
import com.opensource.frameworks.processframework.config.IfTagConfig;
import com.opensource.frameworks.processframework.config.NodeConfig;
import com.opensource.frameworks.processframework.config.ProcessConfig;
import com.opensource.frameworks.processframework.invocation.Invocation;
import com.opensource.frameworks.processframework.utils.Assert;

/**
 * @author luolishu
 *
 */
public abstract class InvocationBuilder {
	static final Map<Class,InvocationFactory> factorys=new HashMap<Class,InvocationFactory>();
	static{
		register(ConditionsTagConfig.class,ConditionInvocationFactory.class);
		register(IfTagConfig.class,IfInvocationFactory.class);
		register(ElseIfTagConfig.class,ElseIfInvocationFactory.class);		
		register(ExceptionConfig.class,ExceptionInvocationFactory.class);
		register(NodeConfig.class,NodeInvocationFactory.class);
		register(ProcessConfig.class,ProcessInvocationFactory.class); 
		
	} 
	
	public static Invocation build(ElementConfig config){
		
		InvocationFactory factory=factorys.get(config.getClass());
		Assert.notNull(factory, "factory is not null,element config="+config);
		return factory.create(config);
	}
	
	private static <T extends InvocationFactory>void register(Class configClaz,Class<T> claz){
		try {
			factorys.put(configClaz, claz.newInstance());
		} catch (Exception e) { 
			e.printStackTrace();
		}  
	}
}
