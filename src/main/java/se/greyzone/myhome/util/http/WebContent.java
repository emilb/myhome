package se.greyzone.myhome.util.http;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.sax.SAXSource;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.ccil.cowan.tagsoup.Parser;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

/**
 * A container representing the contents resulting from a WebAction.
 * 
 * @author seemibre
 *
 */
public class WebContent {

	protected final String responseContent;
	
	protected final Node transformedNode;
	
	protected final Document xmlRoot;
	
	// Attachment as a byte array (if any)
	protected byte[] byteAttachment;

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
	public WebContent(HttpResponse response) {
		
		String responseAsString = null;
		try {
			responseAsString = EntityUtils.toString(response.getEntity());

			// Close the entity for further reading
			response.getEntity().consumeContent();
		}
		catch (Exception e) {}
		
		this.responseContent = responseAsString;

		
		Node docNode = null;
		try {
			StringReader sr = new StringReader(responseContent);
			
			XMLReader reader = new Parser();
			reader.setFeature(Parser.namespacesFeature, false);
			reader.setFeature(Parser.namespacePrefixesFeature, false);
	
			Transformer transformer = TransformerFactory.newInstance()
					.newTransformer();
	
			DOMResult result = new DOMResult();
			transformer.transform(new SAXSource(reader, new InputSource(
					sr)), result);
			docNode = result.getNode();
		} catch (Exception e) {}
		
		transformedNode = docNode;
		
		Document root = null;
		
		try {
			StringReader sr = new StringReader(responseContent);

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			root = db.parse(new InputSource(sr));
			root.getDocumentElement().normalize();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		xmlRoot = root;
	}
	
	/**
	 * If the page contains an attachment it may be fetched here, but 
	 * usually it will be null.
	 *  
	 * @return a byte[] containing the attachment
	 */
	public byte[] getByteAttachment() {
		return byteAttachment;
	}

	public void setByteAttachment(byte[] byteAttachment) {
		this.byteAttachment = byteAttachment;
	}

	/**
	 * The content as a String
	 * 
	 * @return
	 */
	public String getResponseContent() {
		return responseContent;
	}

	/**
	 * The same content as a document Node.
	 * 
	 * @return 
	 */
	public Node getNormalizedNode() {
		return transformedNode;
	}

	public Document getXmlDocument() {
		return xmlRoot;
	}
	
}
