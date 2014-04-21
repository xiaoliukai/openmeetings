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
package org.apache.openmeetings.web.mail.template;

import org.apache.openmeetings.web.app.Application;
import org.apache.openmeetings.web.app.WebSession;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ThreadContext;
import org.apache.wicket.markup.IMarkupResourceStreamProvider;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.mock.MockWebResponse;
import org.apache.wicket.protocol.http.BufferedWebResponse;
import org.apache.wicket.protocol.http.mock.MockHttpServletRequest;
import org.apache.wicket.protocol.http.mock.MockHttpSession;
import org.apache.wicket.protocol.http.servlet.ServletWebRequest;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.cycle.RequestCycleContext;
import org.apache.wicket.util.resource.IResourceStream;
import org.apache.wicket.util.resource.StringResourceStream;

public abstract class AbstractTemplatePanel extends Panel {
	private static final long serialVersionUID = 1L;
	
	public AbstractTemplatePanel(String id) {
		super(id);
	}
	
	/**
	 * Collects the html generated by the rendering of a page.
	 * 
	 * @param panel
	 *            the panel that should be rendered.
	 * @return the html rendered by the panel
	 */
	protected static CharSequence renderPanel(final Panel panel) {
		RequestCycle requestCycle = RequestCycle.get();

		final Response oldResponse = requestCycle.getResponse();
		BufferedWebResponse tempResponse = new BufferedWebResponse(null);

		try {
			requestCycle.setResponse(tempResponse);

			TemplatePage page = new TemplatePage();
			page.add(panel);

			panel.render();
		} finally {
			requestCycle.setResponse(oldResponse);
		}

		return tempResponse.getText();
	}

	public static class TemplatePage extends WebPage implements IMarkupResourceStreamProvider {
		private static final long serialVersionUID = 1L;
		public static final String COMP_ID = "template";

		public IResourceStream getMarkupResourceStream(MarkupContainer container, Class<?> containerClass) {
			return new StringResourceStream("<wicket:container wicket:id='" + COMP_ID + "'></wicket:container>");
		}
	}
	
	public static void ensureApplication(long langId) {
		if (!Application.exists()) {
			Application a = (Application)Application.get(Application.getAppName());
			ThreadContext.setApplication(a);
			
			ServletWebRequest req = new ServletWebRequest(new MockHttpServletRequest(a, new MockHttpSession(a.getServletContext()), a.getServletContext()), "");
			RequestCycleContext rctx = new RequestCycleContext(req, new MockWebResponse(), a.getRootRequestMapper(), a.getExceptionMapperProvider().get()); 
			ThreadContext.setRequestCycle(new RequestCycle(rctx));
			
			WebSession s = WebSession.get();
			s.setLanguage(1);
			ThreadContext.setSession(s);
		}
	}
}
