package com.hit.algorithm;
import java.util.List;
import java.util.Map;
import java.util.Arrays;
import org.junit.Test;
import junit.framework.Assert;

public class IAlgoCacheTest {
	
	@Test
	public void LRUtest()
	  {
		List<String> expectedLRU = Arrays.asList(new String[] {"2 - Ni", "3 - San", "4 - Yon", "5 - Go", "6 - Roku"});
		List<String> actualLRU = null;
		LRUAlgoCacheImpl<Integer, String> algoLRU = new LRUAlgoCacheImpl<>(5);
		System.out.println(algoLRU.putElement(1, "Ichi"));
		System.out.println(algoLRU.putElement(2, "Ni"));
		System.out.println(algoLRU.putElement(3, "San"));
		System.out.println(algoLRU.putElement(4, "Yon"));
		System.out.println(algoLRU.putElement(5, "Go"));
		System.out.println(algoLRU.putElement(6, "Roku"));
		System.out.println(algoLRU.putElement(6, "Roku"));
		actualLRU = convertCacheToStringList(algoLRU.getCache());
		Assert.assertEquals(expectedLRU, actualLRU);
		
	  }
	@Test
	public void MRUtest()
	  {
		List<String> expectedMRU = Arrays.asList(new String[] {"1 - Ichi", "2 - Ni", "3 - San", "4 - Yon", "6 - Roku"});
		List<String> actualMRU = null;
		MRUAlgoCacheImpl<Integer, String> algoMRU = new MRUAlgoCacheImpl<>(5);
		algoMRU.putElement(1, "Ichi");
		algoMRU.putElement(2, "Ni");
		algoMRU.putElement(3, "San");
		algoMRU.putElement(4, "Yon");
		algoMRU.putElement(5, "Go");
		algoMRU.putElement(6, "Roku");
		algoMRU.putElement(6, "Roku");
		actualMRU = convertCacheToStringList(algoMRU.getCache());
		Assert.assertEquals(expectedMRU, actualMRU);
		
	  }
	@Test
	public void Randomtest()
	  {
		List<String> expectedRandom = Arrays.asList(new String[] {"1 - Ichi","2 - Ni", "3 - San", "4 - Yon", "5 - Go"});
		List<String> actualRandom = null;
		Random<Integer, String> algoRandom = new Random<>(5);
		algoRandom.putElement(1, "Ichi");
		algoRandom.putElement(2, "Ni");
		algoRandom.putElement(3, "San");
		algoRandom.putElement(4, "Yon");
		algoRandom.putElement(5, "Go");
		actualRandom = convertCacheToStringList(algoRandom.getCache());
		Assert.assertEquals(expectedRandom, actualRandom);
		
	  }
		
		private  List<String> convertCacheToStringList(Map<Integer, String> cache)
		{ 
			String[] convertedCacheElements = new String[cache.size()];
			int i = 0;
			for (Integer key : cache.keySet())
			{
				convertedCacheElements[i] = key + " - " + cache.get(key);
				i++;
			}
			
			return Arrays.asList(convertedCacheElements);
		}
}
