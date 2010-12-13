package se.greyzone.myhome.web.action;

import java.io.IOException;
import java.io.StringWriter;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.MappingJsonFactory;
import org.codehaus.jackson.map.ObjectMapper;

import se.greyzone.myhome.web.action.context.MyHomeContext;
import se.greyzone.myhome.web.action.context.SiteArea;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;

public class BaseAction implements ActionBean {

	MyHomeContext context;
	
	@Override
	public MyHomeContext getContext() {
		return context;
	}

	@Override
	public void setContext(ActionBeanContext context) {
		this.context = (MyHomeContext) context;
	}

	public SiteArea getSiteArea() {
		return SiteArea.HOME;
	}
	
	public boolean isSiteArea(String siteArea) {
		System.out.println(getSiteArea());
		System.out.println(SiteArea.valueOf(siteArea));
		return getSiteArea() == SiteArea.valueOf(siteArea);
	}
		
	public String getContextPath() {
		return context.getRequest().getContextPath();
	}
	
	public String toJson(Object o) throws IOException {
		StringWriter sw = new StringWriter();   // serialize
		ObjectMapper mapper = new ObjectMapper(); 
		MappingJsonFactory jsonFactory = new MappingJsonFactory();
		JsonGenerator jsonGenerator = jsonFactory.createJsonGenerator(sw);
		
		mapper.writeValue(jsonGenerator, o);
		sw.close();
		return sw.toString();
	}
}
