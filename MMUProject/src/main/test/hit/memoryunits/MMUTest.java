package hit.memoryunits;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import org.junit.Assert;
import com.hit.algorithm.IAlgoCache;
import com.hit.algorithm.LRUAlgoCacheImpl;
import com.hit.memoryunits.MemoryManagementUnit;
import com.hit.memoryunits.Page;

public class MMUTest 
{
	@org.junit.Test
	public void TestSystem()
	{
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("HardDiskPages.bin"));
			os.writeObject(new Page<byte[]>((long)1, new byte[] {11,12,13,14,15}));
			os.writeObject(new Page<byte[]>((long)2, new byte[] {21,22,23,24}));
			os.writeObject(new Page<byte[]>((long)3, new byte[] {31}));
			os.writeObject(new Page<byte[]>((long)4, new byte[] {41, 42, 43}));
			os.writeObject(new Page<byte[]>((long)5, new byte[] {51, 52}));		
			os.close();
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		IAlgoCache<java.lang.Long, Page<byte[]>> algoCache = new LRUAlgoCacheImpl<>(3);
		MemoryManagementUnit mmu = new MemoryManagementUnit(3, algoCache);
		String[] expectedPages = new String[] {"2, [0]", "3, [0]", "4, [1, 2, 3, 4]"};				
		try {
			mmu.getPages(new java.lang.Long[] {(long) 1,(long) 2,(long) 3});
			System.out.println("\n\n\n");
			Page<byte[]>[] pages = mmu.getPages(new java.lang.Long[] {(long) 4});
			pages[0].setContent(new byte[] {1, 2, 3, 4});
			
			String[] actualPages = new String[mmu.getRam().getPages().size()];
			int i = 0;
			
			for(java.lang.Long key : mmu.getRam().getPages().keySet()) 
			{
				actualPages[i] = mmu.getRam().getPage(key).getPageId() + ", " + Arrays.toString((byte[])mmu.getRam().getPage(key).getContent());
				i++;
			}	
			
			Assert.assertArrayEquals(expectedPages, actualPages);
			
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
