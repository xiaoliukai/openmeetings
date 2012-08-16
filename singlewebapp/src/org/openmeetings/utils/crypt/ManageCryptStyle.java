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
package org.openmeetings.utils.crypt;

import org.openmeetings.app.OpenmeetingsVariables;
import org.openmeetings.app.data.basic.Configurationmanagement;
import org.openmeetings.app.remote.red5.ScopeApplicationAdapter;
import org.red5.logging.Red5LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class ManageCryptStyle {

	private static final Logger log = Red5LoggerFactory.getLogger(
			ManageCryptStyle.class, OpenmeetingsVariables.webAppRootKey);
	@Autowired
	private Configurationmanagement cfgManagement;

	@Autowired
	private ScopeApplicationAdapter scopeApplicationAdapter;

	public ICryptString getInstanceOfCrypt() {
		try {

			log.debug("getInstanceOfCrypt: " + this);

			log.debug("getInstanceOfCrypt: " + cfgManagement);

			// String configKeyCryptClassName =
			// "org.openmeetings.utils.crypt.MD5Implementation";
			String configKeyCryptClassName = scopeApplicationAdapter
					.getCryptKey();

			log.debug("configKeyCryptClassName: " + configKeyCryptClassName);

			return (ICryptString) Class.forName(
					configKeyCryptClassName).newInstance();

		} catch (Exception err) {
			log.error("[getInstanceOfCrypt]", err);
		}
		return null;
	}

}
