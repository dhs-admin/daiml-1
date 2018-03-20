package gov.dhs.daiml.tool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/** Read MNIST file and convert from grayscale to 
 * (binary) black and white (i.e., 0 and 255)
 * @author steve
 */
public class MnistGrayscaleToBlackAndWhite {

	final static String mnistGrayScaleInTrain = "C:\\work\\projects\\ai-ml\\tools\\h2o-3.18.0.2\\example_datasets\\mnist\\mnist_train2_grayscale.txt";
	final static String mnistBlackAndWhiteOutTrain = "C:\\work\\projects\\ai-ml\\tools\\h2o-3.18.0.2\\example_datasets\\mnist\\mnist_train3_blackwhite.txt";
	
	final static String mnistGrayScaleInTest = "C:\\work\\projects\\ai-ml\\tools\\h2o-3.18.0.2\\example_datasets\\mnist\\mnist_test2_grayscale.txt";
	final static String mnistBlackAndWhiteOutTest = "C:\\work\\projects\\ai-ml\\tools\\h2o-3.18.0.2\\example_datasets\\mnist\\mnist_test3_blackwhite.txt";
	
    public static void main(String [] args) {

    	convert(mnistGrayScaleInTrain, mnistBlackAndWhiteOutTrain);
    	//convert(mnistGrayScaleInTest, mnistBlackAndWhiteOutTest);
    	
        System.out.println("Done");
    }
    
    public static void convert(String infile, String outfile) {
        // This will reference one line at a time
        String line = null;

        try {
            // INPUT
            FileReader fileReader = 
                new FileReader(infile);
            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);
            
            // OUTPUT
            FileWriter fileWriter =
                new FileWriter(outfile);
            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                new BufferedWriter(fileWriter);
            
            while((line = bufferedReader.readLine()) != null) {
                String[] rowData = line.split("\\s+");
                //System.out.println("Rows: " + rowData.length);
                for (int i = 0; i < rowData.length; i++) {
                	int number = Integer.parseInt(rowData[i]);
                	if (number > 0) {
                		rowData[i] = "255";
                	}
                    bufferedWriter.write(rowData[i] + " ");
                }
                bufferedWriter.newLine();
            }   

            // Always close files.
            bufferedReader.close();  
            fileReader.close();
            bufferedWriter.close();
            fileWriter.close();
        }
        catch(FileNotFoundException ex) {
            System.err.println(
                "Unable to open file '" + 
                		infile + "'");                
        }
        catch(IOException ex) {
            System.err.println(
                "Error reading file '" 
                + infile + "'");                  
        }
    }
    
    
}
