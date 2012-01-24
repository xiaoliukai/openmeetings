/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License") +  you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.openmeetings.webstart.beans;

public class ConnectionBean {
	
	/**Connection Settings**/
	
	public static String connectionURL = "http://macbook:5080/xmlcrm/ScreenServlet";
	
	public static String SID = "3010";
	
	public static String room = "1";
	
	public static String domain = "public";
	
	public static String publicSID = "";
	
	public static String record = "";
	
	/**Intervall Settings**/
	
	public static int intervallSeconds = 1;
	
	public static String quartzScreenJobName = "grabScreen";
	
	/**Picture Quality Settings
	 * Some guidelines: 0.75 high quality
     *           		0.5  medium quality
     *           		0.25 low quality
	 * **/
	
	public static Float imgQuality = new Float(0.40);
	
	/**
	 * current loading to server
	 */
	public static boolean isloading = false;

}
