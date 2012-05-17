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
package org.openmeetings.cli;

public class Db2Patcher extends ConnectionPropertiesPatcher {
	@Override
	protected String getUrl(String _url, String host, String _port, String _db) {
		String port = (_port == null) ? "50000" : _port;
		String db = (_db == null) ? "openmeet" : _db;
		return "jdbc:db2://" + host + ":" + port + "/" + db; 
	}
}
