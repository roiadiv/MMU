package com.hit.algorithm;
import java.util.Iterator;

public class Random<K, V> extends AbstractAlgoCache<K, V>
{
	public Random(int capacity)
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
		if (!this.cache.containsKey(key))
		{
			if (this.cache.size() < this.capacity)
			{
				this.cache.put(key, value);
			}
			else
			{
				java.util.Random rand = new java.util.Random();
				int randomPlace = rand.nextInt(this.capacity - 1) + 1;
				if (randomPlace < this.cache.size())
				{
					Iterator<K> it = this.cache.keySet().iterator();
					K removedKey = null;
					for (int i = 0; i < randomPlace; ++i)
					{
						if (it.hasNext())
						{
							removedKey = it.next();
						}
					}	
					removedValue = this.cache.get(removedKey);
					removeElement(removedKey);
					this.cache.put(key, value);
			    }
			}
		}
		
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
