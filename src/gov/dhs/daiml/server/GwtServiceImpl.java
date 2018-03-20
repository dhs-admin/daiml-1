package gov.dhs.daiml.server;

import gov.dhs.daiml.gwt.client.GWTService;
import gov.dhs.daiml.properties.DaimlProperties;
import gov.dhs.daiml.shared.server.Logger;
import hex.genmodel.easy.EasyPredictModelWrapper;
import hex.genmodel.easy.RowData;
import hex.genmodel.easy.exception.PredictException;
import hex.genmodel.easy.prediction.MultinomialModelPrediction;
import hex.genmodel.easy.prediction.RegressionModelPrediction;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;


import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class GwtServiceImpl extends RemoteServiceServlet implements
GWTService {

	// Instantiate H2O class
	//gbm_3632d608_7e63_4c05_97bc_6ccb79d070cf gbm = new gbm_3632d608_7e63_4c05_97bc_6ccb79d070cf();

	private static String homePricePredictModel = "gov.dhs.daiml.server.gbm_579ec8b5_cddb_4152_955d_76adba184db0";
	private static String mnistBlackWhiteModel = "gov.dhs.daiml.server.gbm_1fb3f812_5c8d_4e0c_a9b3_c9f78b6c6ea6";
	public static hex.genmodel.GenModel rawModel;
	public static EasyPredictModelWrapper model = null;
	public RowData row = new RowData();

	private static final long serialVersionUID = 1L;
	private Logger log = DaimlProperties.log;

	@Override
	public String getDigit(String[] imageArray) throws IllegalArgumentException {
		MultinomialModelPrediction p = null;
		//BinomialModelPrediction p = null;
		
		try {
			rawModel = (hex.genmodel.GenModel) Class.forName(mnistBlackWhiteModel).newInstance();
			model = new EasyPredictModelWrapper(rawModel);
			int CIndex = 2; // The first value of imageArray must start at C2.
			
			for (int i = 0; i < imageArray.length; i++) {
				String colName = "C" + (CIndex);
				CIndex++; 
				String value = imageArray[i]; 
				row.put(colName, new Integer(imageArray[i]).toString());
			}
			
			
			// COMMENT OUT GENERATION OF RECEIVED IMAGE
//			int x = 0;
//			int y = 0;
//			File f = null;
//			BufferedImage img = 
//					new BufferedImage(28, 28, BufferedImage.TYPE_INT_ARGB);
			
//			// Skip first value which is the answer field
//			for (int i = 0; i < imageArray.length; i++) {
//
//				int a = Integer.parseInt(imageArray[i]);  // alpha value (0 or 255 for black and white)
//
//				int pixel = (a<<24) | (0<<16) | (0<<8) | 0; //pixel
//				
//				
//				System.out.println("(" + x + ", " + y + ") <-- i = " + i + " == " + imageArray[i] + ", pixel: " + pixel);
//
//				img.setRGB(x, y, pixel);
//
//				//imageMatrix[x][y] = Integer.parseInt(rowData[i]);
//
//				if ((i + 1) % 28 == 0) {
//					y++;
//					x = 0;
//				} else {
//					x++;
//				}
//			}

			// Write image
//			try{
//				f = new File("C:\\work\\projects\\ai-ml\\tools\\h2o-3.18.0.2\\example_datasets\\mnist\\image_steve.png");
//				ImageIO.write(img, "png", f);
//				System.out.println("wrote file");
//			}catch(IOException e){
//				System.out.println("Error: " + e);
//			}
			

			p = model.predictMultinomial(row);
			//p = model.predictBinomial(row);
			System.out.println("Result: " + p.label);

			row.clear();

		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (PredictException e) {
			e.printStackTrace();
		}

		return p.label;
	}


	public void testFromFile() {

		try {
			rawModel = (hex.genmodel.GenModel) Class.forName(mnistBlackWhiteModel).newInstance();
			model = new EasyPredictModelWrapper(rawModel);

		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		/* REMOVE AFTER TESTING */
		MultinomialModelPrediction p = null;


		System.out.println("----------------");

		/* From UI */
		//		for (int i = 0; i < imageArray.length; i++) {
		//			String colName = "C" + (i + 1);
		//			String value = Integer.toString(imageArray[i]); 
		//			row.put(colName, new Integer(imageArray[i]).toString());
		//			System.out.println(colName + ": " + value);
		//		}

		try {
			File file = new File("C:\\work\\projects\\ai-ml\\tools\\h2o-3.18.0.2\\example_datasets\\mnist\\mnist-test-from-test2.txt");
			if (file.exists()) {
				System.out.println("File exists");
			} else {
				System.err.println("File does not exist");
			}

			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = "";
			int badCount = 0;
			int goodCount = 0;

			System.out.println("COmputing from test file");
			RowData row = new RowData();

			while (line != null) {
				line = br.readLine();
				String[] tokens = line.split("\\s+");
				String theDigit = tokens[0];

				for (int i = 1; i < tokens.length-1; i++) {
					String colName = "C" + (i);
					String value = tokens[i]; 
					row.put(colName, new Integer(tokens[i]).toString());
				}
				try {
					p = model.predictMultinomial(row);
					if (theDigit.equals(p.label)) {
						goodCount++;
						System.out.println("GOOD: " + theDigit + " = " + p.label);
					} else {
						badCount++;
						System.out.println("BAD: " + theDigit + " != " + p.label);
					}

				} catch (PredictException e) {
					e.printStackTrace();
				}
				row.clear();
			}
			br.close();
			System.out.println("good: " + goodCount + ", bad: " + badCount);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//String predictedPrice = p.toString();
		System.out.println("Prediction: " + p.label);

	}

	/** Initialize the service, including static properties. */
	public Boolean initService() throws IllegalArgumentException {
		return true;
	}

	public Boolean authenticate(String username, String pwd) throws IllegalArgumentException {
		if (username == null) {
			log.debug("username is null. Aborting");
			return false;
		}
		if (pwd == null) {
			log.debug("pwd is null. Aborting");
			return false;
		}
		log.debug("Got authenticate service");
		log.debug("In username: " + username);
		log.debug("In password: " + pwd);
		if (username.equals("daiml") &&
				(pwd.equals("dhs123?"))) {
			log.info("Authenticated");
			return true;
		} else {
			return false;
		}
	}

	public String predictPrice(String sqftStr, String lotSizeStr, String storiesStr, 
			String bedroomsStr, String bathroomsStr, String selectedGarageText, String selectedPoolText, 
			String selectedIslandText, String selectedFloordingText, String selectedGraniteText, String ageStr) 
					throws IllegalArgumentException {

		RegressionModelPrediction p = null;
		DecimalFormat df2 = new DecimalFormat( "#,###,###,##0.00" );

		try {
			rawModel = (hex.genmodel.GenModel) Class.forName(homePricePredictModel).newInstance();
			model = new EasyPredictModelWrapper(rawModel);

			System.out.println("----------------");

			row.put("sqrt", sqftStr);
			System.out.println("sqrt: " + sqftStr);

			row.put("lot_size_acres", lotSizeStr);
			System.out.println("lot_size_acres: " + lotSizeStr);

			row.put("stories", storiesStr);
			System.out.println("stories: " + storiesStr);

			row.put("number_bedrooms", bedroomsStr);
			System.out.println("number_bedrooms: " + bedroomsStr);

			row.put("number_bathrooms", bathroomsStr);
			System.out.println("number_bathrooms: " + bathroomsStr);

			row.put("attached_garage", selectedGarageText);
			System.out.println("attached_garage: " + selectedGarageText);

			row.put("has_pool", selectedPoolText);
			System.out.println("has_pool: " + selectedPoolText);

			row.put("has_kitchen_island", selectedIslandText);
			System.out.println("has_kitchen_island: " + selectedIslandText);

			row.put("main_flooring_type", selectedFloordingText);
			System.out.println("main_flooring_type: " + selectedFloordingText);

			row.put("has_granite_counters", selectedGraniteText);
			System.out.println("has_granite_counters: " + selectedGraniteText);

			row.put("house_age_years", ageStr);
			System.out.println("house_age_years" + ageStr);

			
			p = model.predictRegression(row);

			System.out.println("Prediction: " + p.value);

			row.clear();

		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (PredictException e) {
			e.printStackTrace();
		}

		return "$ " + df2.format(p.value);
	}
}
