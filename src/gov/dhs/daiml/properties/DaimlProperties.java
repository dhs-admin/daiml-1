/* This software was developed by employees of the National Institute of
 * Standards and Technology (NIST), an agency of the Federal Government.
 * Pursuant to title 15 United States Code Section 105, works of NIST
 * employees are not subject to copyright protection in the United States
 * and are considered to be in the public domain.  As a result, a formal
 * license is not needed to use the software.
 * 
 * This software is provided by NIST as a service and is expressly
 * provided "AS IS".  NIST MAKES NO WARRANTY OF ANY KIND, EXPRESS, IMPLIED
 * OR STATUTORY, INCLUDING, WITHOUT LIMITATION, THE IMPLIED WARRANTY OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, NON-INFRINGEMENT
 * AND DATA ACCURACY.  NIST does not warrant or make any representations
 * regarding the use of the software or the results thereof including, but
 * not limited to, the correctness, accuracy, reliability or usefulness of
 * the software.
 * 
 * Permission to use this software is contingent upon your acceptance
 * of the terms of this agreement.
 */
package gov.dhs.daiml.properties;

import gov.dhs.daiml.shared.server.Logger;

public class DaimlProperties {
	
	public static boolean error = false;

	public static final String DAIML_VERSION = "0.1";
	public static String MSAP_URL_RELATIVE = "/daiml";

	// Logging
	public static Logger log = null;
	private static String DAIML_LOG_NAME = "daiml_log.txt";
	public static String DAIML_LOG_PATH = null;
	public static String DAIML_LOG_DIR = null;
	public static String LOG_LEVEL = "DEBUG";
	public static String LOG_TO_CONSOLE = null;
	
	public static String JAVA_HOME = null;
	public static String JRE_HOME = null;
	public static String CATALINA_HOME = null; // Tomcat
	public static String DAIML_FILES_HOME = null;



	static {
		JAVA_HOME = System.getenv("JAVA_HOME");
		if (JAVA_HOME == null || JAVA_HOME.isEmpty()) {
			System.err.println("The JAVA_HOME environment variable is null!");
		}
		JRE_HOME = System.getenv("JRE_HOME");
		if (JRE_HOME == null || JRE_HOME.isEmpty()) {
			System.err.println("The JRE_HOME environment variable is null!");
		}
		CATALINA_HOME = System.getenv("CATALINA_HOME");
		if (CATALINA_HOME == null || CATALINA_HOME.isEmpty()) {
			System.err
					.println("The CATALINA_HOME environment variable is null!");
		}

		DAIML_FILES_HOME = System.getenv("DAIML_FILES_HOME");
		if (DAIML_FILES_HOME == null || DAIML_FILES_HOME.isEmpty()) {
			System.err
					.println("The DAIML_FILES_HOME environment variable is null!");
		}
		
		DAIML_LOG_DIR = DAIML_FILES_HOME + "/logs";
		DAIML_LOG_PATH = DAIML_LOG_DIR + "/" + DAIML_LOG_NAME;

		log = new Logger(DAIML_LOG_PATH, "DAIML");


	}
	
	public static String DEFAULT_USER_NAME="daiml";
	public static String DEFAULT_USER_PWD="dhs123?";


}
