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
package org.apache.openmeetings.web.components.admin.rooms;

import org.apache.openmeetings.data.conference.RoomDAO;
import org.apache.openmeetings.persistence.beans.rooms.Rooms;
import org.apache.openmeetings.web.components.admin.AdminPanel;
import org.apache.openmeetings.web.components.admin.OmDataView;
import org.apache.openmeetings.web.components.admin.PagedEntityListPanel;
import org.apache.openmeetings.web.data.DataViewContainer;
import org.apache.openmeetings.web.data.OmDataProvider;
import org.apache.openmeetings.web.data.OmOrderByBorder;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;

public class RoomsPanel extends AdminPanel {

	private static final long serialVersionUID = -1L;
	private RoomForm form;
	
	@Override
	public void onMenuPanelLoad(AjaxRequestTarget target) {
		target.appendJavaScript("omRoomPanelInit();");
	}

	@SuppressWarnings("unchecked")
	public RoomsPanel(String id) {
		super(id);
		OmDataView<Rooms> dataView = new OmDataView<Rooms>("roomList", new OmDataProvider<Rooms>(RoomDAO.class)) {
			private static final long serialVersionUID = 8715559628755439596L;

			@Override
			protected void populateItem(final Item<Rooms> item) {
				final Rooms room = item.getModelObject();
				item.add(new Label("rooms_id", "" + room.getRooms_id()));
				item.add(new Label("name", "" + room.getName()));
				item.add(new Label("ispublic", ""+room.getIspublic()));
				item.add(new AjaxEventBehavior("onclick") {
					private static final long serialVersionUID = -8069413566800571061L;

					protected void onEvent(AjaxRequestTarget target) {
						form.setModelObject(room);
						form.hideNewRecord();
						target.add(form);
						target.appendJavaScript("omRoomPanelInit();");
					}
				});
				item.add(AttributeModifier.replace("class", (item.getIndex() % 2 == 1) ? "even" : "odd"));
			}
		};
		
		final WebMarkupContainer listContainer = new WebMarkupContainer("listContainer");
		add(listContainer.add(dataView).setOutputMarkupId(true));
		DataViewContainer<Rooms> container = new DataViewContainer<Rooms>(listContainer, dataView);
		container.setLinks(new OmOrderByBorder<Rooms>("orderById", "rooms_id", container)
				, new OmOrderByBorder<Rooms>("orderByName", "name", container)
				, new OmOrderByBorder<Rooms>("orderByPublic", "ispublic", container));
		add(container.orderLinks);
		add(new PagedEntityListPanel("navigator", dataView) {
			private static final long serialVersionUID = -1L;

			@Override
			protected void onEvent(AjaxRequestTarget target) {
				target.add(listContainer);
			}
		});
		
		form = new RoomForm("form", new Rooms());
		form.showNewRecord();
        add(form);
		
	}
}
