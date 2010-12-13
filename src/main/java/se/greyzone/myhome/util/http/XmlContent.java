package se.greyzone.myhome.util.http;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpResponse;
import org.w3c.dom.Document;

/**
 * A container representing the contents resulting from a WebAction.
 * 
 * @author seemibre
 *
 */
public class XmlContent {

	protected final Document xmlRoot;
	

	/**
	 * Extracts the contents from a {@link HttpResponse} and stores
	 * the result as immutable values. Both the String and Node representation
	 * is available.
	 * 
	 * Should any extraction step fail the null value will be stored instead.
	 * 
	 * Note! The entity of the response object is closed for further reading.
	 * 
	 * @param response
	 */
	public XmlContent(HttpResponse response) {
				
		Document root = null;
		
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			root = db.parse(response.getEntity().getContent());
			root.getDocumentElement().normalize();
			
			// Close the entity for further reading
			response.getEntity().consumeContent();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		xmlRoot = root;
	}
	
	public Document getXmlDocument() {
		return xmlRoot;
	}
	
}
