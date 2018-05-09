package com.hit.algorithm;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class AbstractAlgoCache<K, V> extends java.lang.Object implements IAlgoCache<K, V>
{
	protected int capacity;
	protected Map<K, V> cache;

	public AbstractAlgoCache(int capacity)
	{
		this.capacity = capacity;
		this.cache = new LinkedHashMap<>(this.capacity);
	}
	
	public int getCapacity() 
	{
		return capacity;
	}

	public void setCapacity(int capacity)
	{
		this.capacity = capacity;
	}
	
    public Map<K, V> getCache()
    {
    	return this.cache;
    }
}
