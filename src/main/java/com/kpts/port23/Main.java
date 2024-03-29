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

		List<Map<String,String>> itemList = new ArrayList<Map<String,String>>();
		System.out.println("Running strata project");
		
		try {
			FileReader fileReader = new FileReader(args[0]);
			
			if(args.length==2)
			{
				loopCount = Double.valueOf(args[1]);
				System.out.println("Per row loop count is "+(int)loopCount);
			}
			else {
				System.out.println("No loop count provided in argument, so per line loop will be taken into consideration.");
			}
			
			if(fileReader!=null)
			System.out.println("File found..start processing.. ");
			
			BufferedReader buffReader = new BufferedReader(fileReader);
			
			String line = "";
			int count = 0;
			while((line = buffReader.readLine())!=null)
			{
				String[] values = line.split(",");
			
				if(count==0)
				{
					for (String string : values) {
						headers.add(string);
						
					}
									
					System.out.println("headers size is "+headers.size());
				}
				
				else if(values.length>0) {
					
					Map<String,String> item = new TreeMap<String, String>();
					
					item.put("RowNo",String.valueOf(count));
					
					for(int i = 0;i<headers.size();i++)
					{
						item.put(headers.get(i), values[i]);
					}
					
					itemList.add(item);
				}	
				
				count++;
				
				
			}
			buffReader.close();
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
		System.out.println("File not found at location...terminating");
		System.exit(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
		System.out.println("File reading error - "+e.getLocalizedMessage());
		}
		catch(NumberFormatException e)
		{
			loopCount = -1;
		}
		
		int lineNo = 1;
		
		for (Map<String,String> item : itemList) {
		
		Product product = new 
				Product(item.get("SECURITY_SCHEME")+","+item.get("SECURITY_VALUE"),
						item.get("SECURITY_SCHEME")+","+item.get("ISSUER_VALUE"),
						Long.valueOf(item.get("QUANTITY").replace("L", "")),
						Double.valueOf(item.get("NOTIONAL")),
						Double.valueOf(item.get("FIXED_RATE")),
						item.get("START_DATE"),
						item.get("END_DATE"),
						item.get("SETTLEMENT"),
						Double.valueOf(item.get("CLEAN_PRICE")),
						item.get("VAL_DATE"),
						String.valueOf(lineNo)
						);
		
		
		double loopSize = loopCount==-1 ? Integer.valueOf(item.get("Loops")) : loopCount;
		
		product.setExecutionModeWithoutFallback(Kernel.EXECUTION_MODE.CPU);
		Range range = Range.create((int)loopCount);
		product.execute(range);
		product.dispose();
		
		System.out.println("Processed Row "+lineNo+" for "+loopSize+" times.\n");
		
		lineNo++;
	        
		break;	

	}
	 
}
}