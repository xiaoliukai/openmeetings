package org.openmeetings.jira.plugin.gateway;

import java.io.IOException;
import java.util.LinkedHashMap;
import javax.servlet.ServletException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.openmeetings.jira.plugin.ao.adminconfiguration.OmPluginSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import org.dom4j.DocumentException;
import org.dom4j.Element;

public class OmGateway {
	
	private static final Logger log = LoggerFactory.getLogger(OmGateway.class);
	
	private OmRestService omRestService;
	private OmPluginSettings omPluginSettings;
	private String sessionId;

	public OmGateway(OmRestService omRestService,
			OmPluginSettings omPluginSettings) {		
		this.omRestService = omRestService;
		this.omPluginSettings = omPluginSettings;
	}	
	
	public Boolean loginUser() throws XPathExpressionException, IOException, ServletException, SAXException, ParserConfigurationException, DocumentException{
		
		String url = (String)omPluginSettings.getSomeInfo("url"); 
    	String port = (String)omPluginSettings.getSomeInfo("port");         	
    	String userpass = (String)omPluginSettings.getSomeInfo("userpass");  
    	String omusername = (String)omPluginSettings.getSomeInfo("username"); 
    	String key = (String)omPluginSettings.getSomeInfo("key"); 
		
    	String sessionURL = "http://"+url+":"+port+"/openmeetings/services/UserService/getSession";
    	
    	LinkedHashMap<String,Element> elementMap = omRestService.call(sessionURL, null);
		
    	Element item = elementMap.get("return");
    	
    	this.setSessionId(item.elementText("session_id"));
    	
		log.info(item.elementText("session_id"));
		
		LinkedHashMap<String, Element> result = omRestService.call("http://"+url+":"+port+"/openmeetings/services/UserService/loginUser?SID="+this.getSessionId()+"&username="+omusername+"&userpass="+userpass, null);
		
		if (Integer.valueOf(result.get("return").getStringValue())>0){
	    	return true; 
		} else {
			return false;
		}
	}
	
	public Long addRoomWithModerationExternalTypeAndTopBarOption(	Boolean isAllowedRecording,
																	Boolean isAudioOnly,
																	Boolean isModeratedRoom,
																	String name,
																	Long numberOfParticipent,
																	Long roomType 
																) throws 	XPathExpressionException, 
																			IOException, 
																			ServletException, 
																			SAXException, 
																			ParserConfigurationException, DocumentException{
    			
    	String restURL = "http://localhost:5080/openmeetings/services/RoomService/addRoomWithModerationExternalTypeAndTopBarOption?" +
			    			"SID="+this.getSessionId()+
			    			"&name="+name+
							"&roomtypes_id="+roomType.toString()+
							"&comment=jira"+
							"&numberOfPartizipants="+numberOfParticipent.toString()+
							"&ispublic=false"+
							"&appointment=false"+
							"&isDemoRoom=false"+
							"&demoTime="+
							"&isModeratedRoom="+isModeratedRoom.toString()+
							"&externalRoomType=jira"+
							"&allowUserQuestions="+
							"&isAudioOnly="+isAudioOnly.toString()+
							"&waitForRecording=false"+
							"&allowRecording="+isAllowedRecording.toString()+
							"&hideTopBar=false";
    	    	
		LinkedHashMap<String, Element> result = omRestService.call(restURL, null);		
		
		String roomId = result.get("return").getStringValue();
		
    	return Long.valueOf(roomId);
    }
	
	public Long updateRoomWithModerationAndQuestions(	Boolean isAllowedRecording,
														Boolean isAudioOnly,
														Boolean isModeratedRoom,
														String roomname,
														Long numberOfParticipent,
														Long roomType,
														Long roomId
														) throws 	XPathExpressionException, 
																	IOException, 
																	ServletException, 
																	SAXException, 
																	ParserConfigurationException, 
																	DocumentException{

	
		String restURL = "http://localhost:5080/openmeetings/services/RoomService/updateRoomWithModerationAndQuestions?" +
							"SID="+this.getSessionId()+
							"&room_id="+roomId.toString()+
							"&name="+roomname.toString()+
							"&roomtypes_id="+roomType.toString()+
							"&comment="+
							"&numberOfPartizipants="+numberOfParticipent.toString()+
							"&ispublic=false"+
							"&appointment=false"+
							"&isDemoRoom=false"+
							"&demoTime="+
							"&isModeratedRoom="+isModeratedRoom.toString()+							
							"&allowUserQuestions=";
		
						//Is not available for update method	
//							"&externalRoomType=jira"+
//							"&isAudioOnly="+isAudioOnly+
//							"&waitForRecording=false"+
//							"&allowRecording="+isAllowedRecording+
//							"&hideTopBar=false";
		
		LinkedHashMap<String, Element> result = omRestService.call(restURL, null);		
		
		log.info("addRoomWithModerationExternalTypeAndTopBarOption with ID: ",result.get("return").getStringValue());
		
		String updateRoomId = result.get("return").getStringValue();
		
		return Long.valueOf(updateRoomId);
	}
	
	public String setUserObjectAndGenerateRoomHash (String username , String firstname , 
			String lastname , String profilePictureUrl , String email , Long externalUserId , 
			String externalUserType , Long room_id , int becomeModeratorAsInt , int showAudioVideoTestAsInt ) 
																	throws XPathExpressionException, 
																	IOException, 
																	ServletException, 
																	SAXException, 
																	ParserConfigurationException, 
																	DocumentException 
	{		
		
		String restURL = "http://localhost:5080/openmeetings/services/UserService/setUserObjectAndGenerateRoomHash?" +
				"SID="+this.getSessionId()+
				"&username="+username+
				"&firstname="+firstname+
				"&lastname="+lastname+
				"&profilePictureUrl="+profilePictureUrl+
				"&email="+email+
				"&externalUserId="+externalUserId+
				"&externalUserType="+externalUserType+
				"&room_id="+room_id+
				"&becomeModeratorAsInt="+becomeModeratorAsInt+
				"&showAudioVideoTestAsInt="+showAudioVideoTestAsInt;
				
	
				LinkedHashMap<String, Element> result = omRestService.call(restURL, null);		
				
				String roomHash = result.get("return").getStringValue();
				
				return roomHash;
		
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
}
