package com.kpts.port23;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.aparapi.Kernel;
import com.aparapi.Range;
import com.aparapi.device.Device;
import com.aparapi.device.Device.TYPE;
import com.aparapi.internal.kernel.KernelManager;
import com.aparapi.internal.kernel.KernelPreferences;



public class Main {
	
	static List<String> headers = new ArrayList<String>();
	static double loopCount = -1;
	

	public static void main(String[] args) {

		System.out.println("Program start");
		  float[] input = new float[256];
	        float[] output = new float[256];

	        // Initialize input array
	        
	        for(int i = 0;i<input.length;i++)
	        {
	        	input[i] = i*3;
	        }
	        
	        for(int i = 0;i<100;i++)
	        {
	        	Product kernel = new Product("something","something",i, 1.0, 1.0, "01-02-03", "01-02-03", "01-02-03", 1.0, "01-02-03", "0");
	 	        kernel.execute(input.length);
	 	        kernel.dispose();
	        }

	       
	        
	        System.out.println("Output chk "+output[3]);
	        System.out.println("Program end..output length -"+output.length);
	        

//	List<Map<String,String>> itemList = new ArrayList<Map<String,String>>();
//	System.out.println("Running strata project");
//	
//	try {
//		FileReader fileReader = new FileReader(args[0]);
//		
//		if(args.length==2)
//		{
//			loopCount = Double.valueOf(args[1]);
//			System.out.println("Per row loop count is "+loopCount);
//		}
//		else {
//			System.out.println("No loop count provided in argument, so per line loop will be taken into consideration.");
//		}
//		
//		if(fileReader!=null)
//		System.out.println("File found..start processing.. ");
//		
//		BufferedReader buffReader = new BufferedReader(fileReader);
//		
//		String line = "";
//		int count = 0;
//		while((line = buffReader.readLine())!=null)
//		{
//			String[] values = line.split(",");
//		
//			if(count==0)
//			{
//				for (String string : values) {
//					headers.add(string);
//					
//				}
//				
//				
//				System.out.println("headers size is "+headers.size());
//			}
//			
//			else if(values.length>0) {
//				
//				Map<String,String> item = new TreeMap<String, String>();
//				item.put("RowNo",String.valueOf(count));
//				for(int i = 0;i<headers.size();i++)
//				{
//					item.put(headers.get(i), values[i]);
//				}
//				
//				itemList.add(item);
//			}	
//			
//			
//			count++;
//		}
//		
//		if(itemList.size()>0)			
//		{
//			File file = new File("resultData.txt");
//			
//			PrintWriter writer = new PrintWriter(file);
//			writer.print("");
//			writer.close();
//			
//			
//			 long start = System.currentTimeMillis();	
//			 
//			 ProcessComputationOfData(itemList);		
//				 
//			 long end =  System.currentTimeMillis();
//			 System.out.println("Total time -> "+(end-start)+" milliseconds");
//		}
//		
//		buffReader.close();
//		
//	
//		
//	} catch (FileNotFoundException e) {
//		// TODO Auto-generated catch block
//	System.out.println("File not found at location...terminating");
//	System.exit(0);
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//	System.out.println("File reading error - "+e.getLocalizedMessage());
//	}
//	catch(NumberFormatException e)
//	{
//		loopCount = -1;
//	}
//
//	}
//
//	private static void ProcessComputationOfData(List<Map<String, String>> itemList) {
//		System.out.println("File uploaded....");
//		int lineNo = 1;
//		
//		
//		 long start = System.currentTimeMillis();
//		 
//		for (Map<String,String> item : itemList) {
//			
//			List<Integer> loops = new ArrayList<Integer>();
//			
//			double loopSize = loopCount==-1 ? Integer.valueOf(item.get("Loops")) : loopCount;
//			
//			while(loopSize>0 && loopSize>Integer.MAX_VALUE-1 )
//			{
//				
//					double value = loopSize - Integer.MAX_VALUE-1;
//					
//					while(value>Integer.MAX_VALUE-1)
//					{
//						value -= Integer.MAX_VALUE-1;
//					}
//					
//					loopSize -= value;
//					
//					if(value>0)
//					{
//						loops.add((int)value);
//					}
//								
//			}
//			
//			loops.add((int) loopSize);
//			
//			//Checking loopsize
//			
//			for (Integer num : loops) {
//				
//				Product product = new 
//						Product(item.get("SECURITY_SCHEME")+","+item.get("SECURITY_VALUE"),
//								item.get("SECURITY_SCHEME")+","+item.get("ISSUER_VALUE"),
//								Long.valueOf(item.get("QUANTITY").replace("L", "")),
//								Double.valueOf(item.get("NOTIONAL")),
//								Double.valueOf(item.get("FIXED_RATE")),
//								item.get("START_DATE"),
//								item.get("END_DATE"),
//								item.get("SETTLEMENT"),
//								Double.valueOf(item.get("CLEAN_PRICE")),
//								item.get("VAL_DATE"),
//								item.get("RowNo")
//								);
//				
//				
//				Product.computeCount = 0;
//				
//				Device device = Device.bestGPU();
//				Range range = device.createRange(num);
//				product.execute(range);
//							
//				System.out.println("Processed Row "+lineNo+" for "+num.toString()+" times and times computed (check) is "+Product.computeCount+" \n");
//				
//				product.dispose();
//				
//				System.out.println("Processed Row "+lineNo+" for "+num.toString()+" times and times computed (check) is "+Product.computeCount+" \n");
//				
//				
//				
//				lineNo++;
//			}
//						
//			StringBuilder data = new StringBuilder("");		
//
////			data.append("\n");	
////			
////			BufferedWriter buff;
////			try {
////				buff = new BufferedWriter(new FileWriter("resultData.txt",true));
////			
////			
////			buff.write(data.toString());
////				
////			buff.close();
////			
////			} catch (IOException e) {
////				// TODO Auto-generated catch block
////				e.printStackTrace();
////			}
//			
//			
//		}
//	
//		 
//				
//		//Writing to file
////		System.out.println("Result data written to file -> resultData.txt");
//		
//		long end =  System.currentTimeMillis();
//		 
////		System.out.println("\n\nTotal time(only process and file writing not reading) -> "+(end-start)+" milliseconds\n");
//		System.out.println("\n\nTotal time(only process and not file writing not reading) -> "+(end-start)+" milliseconds\n");
//		
	}
	 
}
