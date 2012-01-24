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
package org.openmeetings.test.domain;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.openmeetings.app.data.user.Organisationmanagement;
import org.openmeetings.app.persistence.beans.domain.Organisation;
import org.openmeetings.test.AbstractOpenmeetingsSpringTest;
import org.springframework.beans.factory.annotation.Autowired;

public class TestUserGroupAggregation extends AbstractOpenmeetingsSpringTest {

	@Autowired
	private Organisationmanagement organisationmanagement;

	private static final Logger log = Logger
			.getLogger(TestUserGroupAggregation.class);

	@Test
	public void testitNow() {

		List<Organisation> orgUser = organisationmanagement.getOrganisationsByUserId(3, 1, 0,
				100, "organisation_id", true);

		log.error("testitNow" + orgUser.size());

		for (Iterator<Organisation> it2 = orgUser.iterator(); it2.hasNext();) {
			Organisation orgUserObj = it2.next();
			log.error("testitNow" + orgUserObj.getOrganisation_id());
			log.error(orgUserObj.getName());
		}

		List<Organisation> orgUser2 = organisationmanagement.getRestOrganisationsByUserId(3,
				1, 0, 100, "organisation_id", true);

		log.error("testitNow" + orgUser2.size());

		for (Iterator<Organisation> it2 = orgUser2.iterator(); it2.hasNext();) {
			Organisation orgUserObj = it2.next();
			log.error("testitNow" + orgUserObj.getOrganisation_id());
			log.error(orgUserObj.getName());
		}

	}
}
