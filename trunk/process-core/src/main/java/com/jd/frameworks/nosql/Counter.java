package com.jd.frameworks.nosql;

/**
 * @author luolishu
 *
 */
public interface Counter extends Cache {
	public Long increment();
	
	public Long decrement();
	
    public Long incrementBy(int i);
	
	public Long decrementBy(int i);

}
