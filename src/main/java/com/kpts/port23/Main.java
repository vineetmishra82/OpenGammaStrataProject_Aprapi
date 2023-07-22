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
				System.out.println("Per row loop count is "+loopCount);
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

		
	  float[] input = new float[256];
	  float[] output = new float[256];

	        // Initialize input array
	        
	        for(int i = 0;i<input.length;i++)
	        {
	        	input[i] = i*3;
	        }
	        
	        for(int i = 0;i<100;i++)
	        {
	        	
	       // 	Product kernel = new Product("something,som","something,som",i, 1.0, 1.0, "12-04-2015", "12-04-2025", "29-04-2016", 1.0, "25-04-2016", "0");
	        	Product kernel = new Product(input, output);
	        	kernel.execute(10);
	 	        kernel.dispose();
	        }

	       
	        
	        System.out.println("Output chk "+output[3]);
	        System.out.println("Program end..output length -"+output.length);
	        


	}
	 
}
