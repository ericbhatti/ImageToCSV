
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import boofcv.io.image.UtilImageIO;
import boofcv.struct.feature.TupleDesc;
import boofcv.struct.image.ImageFloat32;




public class ImageToCSVConvertor<Desc extends TupleDesc> {

	 BufferedWriter br;
	 StringBuilder sb;
	public static final String path = "/home/ericbhatti/data/data2/";
	public static final String fileName = "Stairs_2_Window";
	public ImageToCSVConvertor() throws IOException {
		 	br = new BufferedWriter(new FileWriter(path + fileName +".csv"));
	    	sb = new StringBuilder();
	   
	}
	 
	 
	 public ImageToCSVConvertor(int id)  throws IOException {
		 br = new BufferedWriter(new FileWriter("E:/Data/Testing/testing_"+id+ ".csv"));
	    	sb = new StringBuilder();
	    	createCSVHeaderTesting();
	 }
	 

	public static void main(String args[]) throws Exception
	{
		ImageToCSVConvertor convertor; 
		
		convertor = new ImageToCSVConvertor();
		//convertor.makeCSV(convertor.imageToCSV(i),i);
		convertor.makeCSVTestingWithLimit(convertor.imageToCSVTesting(path + fileName), 200);
	//	convertor.makeCSVSingleColumn(convertor.imageToCSVTesting(3));
	//	convertor.makeCSVSingleColumn(convertor.imageToCSVTesting(4));
	//	convertor.makeCSVSingleColumn(convertor.imageToCSVTesting(5));

		
	}
	
	public void createCSVHeader() throws IOException
	{
		 for(int i = 1; i <= 64; i++){
	    	 sb.append("Data" + i);
	    	 sb.append(",");
		 }
	    br.write(sb.toString());
	    br.close();
	    
	}
	
	public void createCSVHeaderTesting() throws IOException
	{
		 for(int i = 1; i <= 64; i++){
	    	 sb.append("Data" + i);
	    	 if(i != 64)
	    	 sb.append(",");
		 }
		 sb.append("\n");
	    br.write(sb.toString());
	    br.close();    
	}
	
	
	public void makeCSVTesting(Desc[] data, int id) throws IOException
	{
		br = new BufferedWriter(new FileWriter("E:/Data/Testing/testing_"+id+ ".csv",true));
    	sb = new StringBuilder();
    	for (Desc element : data) {
    		for(int i = 0; i < element.size() ; i++) {
    	    	
	    		 if(allzeros(element)) continue;
	    		 sb.append(element.getDouble(i));
	    		 if(i != element.size() - 1)
	    		 sb.append(",");
    	}
    		sb.append("\n");
    	}
    	br.write(sb.toString());
    	br.close();
	}

	public void makeCSVTestingWithLimit(Desc[] data, int limit) throws IOException
	{

		sb = new StringBuilder();
		int count = 0;

		for (Desc element : data) {
			count++;
				for(int i = 0; i < element.size() ; i++) {

				if(allzeros(element)) continue;
				sb.append(element.getDouble(i));
				if(i != element.size() - 1)
					sb.append(",");
			}

			sb.append("," + fileName);

			if(count == limit)
				break;

			sb.append("\n");
		}
		br.write(sb.toString());
		br.close();
	}


	public void makeCSVSingleColumn(Desc[] data) throws IOException
	{
    	sb = new StringBuilder();
    	int count = 0;
    	for (Desc element : data) {
    		count++;
    		for(int i = 0; i < element.size() ; i++) {
    	    	
	    		 if(allzeros(element)) continue;
	    		 sb.append(element.getDouble(i));
	    		 //if(i != element.size() - 1)
	    		 sb.append("\n");
    	}
    		//sb.append("\n");
    		if(count == 200)
    			break;
    	}
    	br.write(sb.toString());
    	br.close();
	}


	public Desc[] imageToCSVTesting(String file)
	{
		ImageFloat32 float32 = UtilImageIO.loadImage(file + ".jpg", ImageFloat32.class);
		if(float32 == null)
		{

			System.out.println("Null");
			File f = new File(file + ".jpg");
			System.out.println(f.exists());
			System.out.println(f.toString());
			return null;
		}
		return new Matcher<Desc>().detectDescribe(float32);
	}


	public Desc[] imageToCSVTesting(int id)
	{
		ImageFloat32 float32 = UtilImageIO.loadImage("E:/Training_Images/Boy_Sitting_Test_" + id + ".jpg", ImageFloat32.class);
		if(float32 == null)
		{
			
			System.out.println("Null");
			File file = new File("E:/Data/Testing/testing_" + id + ".jpg");
			System.out.println(file.exists());
			System.out.println(file.toString());
			return null;
		}
		return new Matcher<Desc>().detectDescribe(float32, id);
	}
	
	
	public Desc[] imageToCSV(int id)
	{
		ImageFloat32 float32 = UtilImageIO.loadImage("E:/Data/Testing/testing_" + id + ".jpg", ImageFloat32.class);
		if(float32 == null)
		{
			
			System.out.println("Null");
			File file = new File("E:/Data/Jpegs/train/" + "train" + id + ".jpg");
			System.out.println(file.exists());
			System.out.println(file.toString());
			return null;
		}
		return new Matcher<Desc>().detectDescribe(float32, id);
	}
	

	public void makeCSV(Desc[] data, int j) throws IOException {
		 	br = new BufferedWriter(new FileWriter("E:/trainingdata.csv",true));
	    	sb = new StringBuilder();
	    	int count = 0;
	    	for (Desc element : data) {
	    		count++;
	    		
	    	 for(int i = 0; i < element.size() ; i++){
	    	
	    		 if(allzeros(element)) continue;
	    	 sb.append(element.getDouble(i));
	    	 sb.append(",");
	    	 
	    	 if(i == 63)
	    	 {
	    		 switch (j){
	    		 case 6:
	    			 sb.append("Boy_Sitting");
	    			 break;
	       		 case 8:
	    			 sb.append("Boy_Sitting");
	    			 break;
	       		 case 7:
	    			 sb.append("Boy_Sitting");
	    			 break;
	       		 case 9:
	    			 sb.append("Boy_Sitting");
	    			 break;
	       		 case 10:
	    			 sb.append("Boy_Sitting");
	    			 break;
	    			 
	    		 case 1:
	    			 sb.append("Plant_Stairs_Office");
	    			 break;
	    		 case 2:
	    			 sb.append("Plant_Stairs_Office");
	    			 break;
	    		 case 3:
	    			 sb.append("Plant_Stairs_Office");
	    			 break;
	    		 case 4:
	    			 sb.append("Plant_Stairs_Office");
	    			 break;
	    		 case 5:
	    			 sb.append("Plant_Stairs_Office");
	    			 break;
	    		 }
	    			 
//	    		 sb.append(",");
	    		 sb.append("\n");
	    	 }
	    	}
	    	 if(count == 345)
	    	 {
	    		 break;
	    	 }

	    	}

	    	br.write(sb.toString());
	    	br.close();
			
		}
	
	public boolean allzeros(Desc desc)
	{
		boolean allzero = true;
		for(int i  = 0; i <desc.size(); i++)
		{
			if(desc.getDouble(i) != 0)
			{
				return false;
			}
		}
		return true;
	}
	
	
	
}
