package gov.dhs.daiml.tool;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;


public class ImageGenerator {

	final static String mnistTrain3BlackWhite = 
			"C:\\work\\projects\\ai-ml\\tools\\h2o-3.18.0.2\\example_datasets\\mnist\\mnist_train3_blackwhite.txt";

	public static String pixelArray = "";

	public static void main(String args[]) {

		// Read MNIST images from black and white txt file spreadsheet
		try {
			// INPUT
			FileReader fileReader = 
					new FileReader(mnistTrain3BlackWhite);
			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = 
					new BufferedReader(fileReader);

			//            // OUTPUT
			//            FileWriter fileWriter =
			//                new FileWriter(outfile);
			//            // Always wrap FileWriter in BufferedWriter.
			//            BufferedWriter bufferedWriter =
			//                new BufferedWriter(fileWriter);

			String line = null;
			int width = 28;
			int height = 28;
			int[][] imageMatrix = new int[28][28];

			int y = 0;
			int x = 0;
			int imageNumber = 1;
			int maxImages = 3;

			File f = null;
			BufferedImage img = 
					new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);


			while((line = bufferedReader.readLine()) != null) {

				// rowData will be 28*28=784 pixels
				String[] rowData = line.split("\\s+");
				System.out.println("rowData.length: " + rowData.length);

				//System.out.println("Rows: " + rowData.length);

				// Skip first value which is the answer field
				for (int i = 1; i < rowData.length; i++) {

					int a = Integer.parseInt(rowData[i]);  // alpha value (0 or 255 for black and white)

					int p = (a<<24) | (0<<16) | (0<<8) | 0; //pixel
					img.setRGB(x, y, p);

					//imageMatrix[x][y] = Integer.parseInt(rowData[i]);
					System.out.println("(" + x + ", " + y + ") <-- i = " + i + " == " + rowData[i]);

					if (i % 28 == 0) {
						y++;
						x = 0;
					} else {
						x++;
					}
				}

				// Write image
				try{
					f = new File("C:\\work\\projects\\ai-ml\\tools\\h2o-3.18.0.2\\example_datasets\\mnist\\image_" + imageNumber + ".png");
					ImageIO.write(img, "png", f);
				}catch(IOException e){
					System.out.println("Error: " + e);
				}

				// Reset x and y
				x = 0;
				y = 0;

				imageNumber++;
				if (imageNumber >= maxImages) {
					break;
				}
			}   

			// Always close files.
			bufferedReader.close();  
			fileReader.close();
		}
		catch(FileNotFoundException ex) {
			System.err.println(
					"Unable to open file '" + 
							mnistTrain3BlackWhite + "'");                
		}
		catch(IOException ex) {
			System.err.println(
					"Error reading file '" 
							+ mnistTrain3BlackWhite + "'");                  
		}

		//	     //image dimension
		//	     int width = 640;
		//	     int height = 320;
		//	     //create buffered image object img
		//	     BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		//	     //file object
		//	     File f = null;
		//	     //create random image pixel by pixel
		//	     for(int y = 0; y < height; y++){
		//	       for(int x = 0; x < width; x++){
		//	         int a = (int)(Math.random()*256); //alpha
		//	         int r = (int)(Math.random()*256); //red
		//	         int g = (int)(Math.random()*256); //green
		//	         int b = (int)(Math.random()*256); //blue
		//	 
		//	         int p = (a<<24) | (r<<16) | (g<<8) | b; //pixel
		//	 
		//	         img.setRGB(x, y, p);
		//	       }
		//	     }
		//	     //write image
		//	     try{
		//	       f = new File("D:\\Image\\Output.png");
		//	       ImageIO.write(img, "png", f);
		//	     }catch(IOException e){
		//	       System.out.println("Error: " + e);
		//	     }
	}
}
