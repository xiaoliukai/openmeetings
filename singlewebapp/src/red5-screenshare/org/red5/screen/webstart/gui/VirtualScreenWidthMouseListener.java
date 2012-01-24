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
package org.red5.screen.webstart.gui;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;

import org.red5.screen.webstart.ScreenShare;


public class VirtualScreenWidthMouseListener extends MouseInputAdapter  {

	private double x = 0;

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		ScreenShare.instance.t.setCursor( Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR) ) ;
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		ScreenShare.instance.t.setCursor( Cursor.getPredefinedCursor( Cursor.DEFAULT_CURSOR ) ) ;
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		VirtualScreen.instance.showWarning=false;
		this.x = e.getX();
//		System.out.println(this.x+" "+this.y);
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		VirtualScreen.instance.showWarning=true;
	}

	public void mouseDragged(MouseEvent e) {
		double newX = e.getX();

		int newWidth = VirtualScreenBean.vScreenSpinnerWidth-Long.valueOf(Math.round(this.x-newX)).intValue();


		//System.out.println(newX+"  "+newWidth);
		if ((VirtualScreenBean.vScreenSpinnerX+newWidth)<=VirtualScreenBean.screenWidthMax) {
			VirtualScreen.instance.doUpdateBounds=false;
			ScreenShare.instance.jVScreenWidthSpin.setValue(newWidth);
			VirtualScreen.instance.doUpdateBounds=true;
			VirtualScreen.instance.updateVScreenBounds();
			VirtualScreen.instance.calcRescaleFactors();
		}

	}

}
