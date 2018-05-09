package com.hit.algorithm;

public class LRUAlgoCacheImpl<K, V> extends AbstractAlgoCache<K, V> {

	public LRUAlgoCacheImpl(int capacity)
	{
		super(capacity);
	}

	@Override
	public V getElement(K key) 
	{
		return this.cache.get(key);
	}

	@Override
	public V putElement(K key, V value) 
	{
		V removedValue = null;
		if (this.cache.containsKey(key))
		{
			removeElement(key);
		}
		else
		{
		if (this.capacity <= this.cache.size())
		{
			K removedKey = this.cache.keySet().iterator().next();
			removedValue = this.cache.get(removedKey);
			removeElement(removedKey);
		}
		}
		
		this.cache.put(key, value);
		return removedValue;
	}


	@Override
	public void removeElement(K key) 
	{		
		this.cache.remove(key);
	}
		
	public void printTest()
	{
		for (K key : this.cache.keySet())
		{
			System.out.println(key.toString() + " - " + this.cache.get(key).toString());
		}
	}

}
