package com.jd.frameworks.processframework;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Script {
	ScriptTypes value()default ScriptTypes.JAVASCRIPT;	
	String function()default "";
	
	public static enum ScriptTypes{
		JAVASCRIPT,
		GROOVY;		
	}
}
