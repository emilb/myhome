package se.greyzone.myhome.web.action;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URLEncoder;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.MappingJsonFactory;
import org.codehaus.jackson.map.ObjectMapper;

import se.greyzone.myhome.service.publictransport.CommuteService;
import se.greyzone.myhome.vo.commute.SiteQueryResult;

@UrlBinding(value = "/index.html")
public class Index extends BaseAction {

	@SpringBean
	protected CommuteService commute;
	
	String stationSearch;
	int stationIdentifier;
	
	@DefaultHandler
    @DontValidate
	public Resolution getResolution() {
		return new ForwardResolution("index.ftl");
	}
	
	@DontValidate
	public Resolution suggestStation() throws IOException {
		
		System.out.println("Suggesting station for: " + stationSearch);
		
		SiteQueryResult result = commute.querySites(URLEncoder.encode(stationSearch, "UTF-8"));
		String response = toJson(result);
	    System.out.println(response);
		return new StreamingResolution("application/json", response);
	}

	public String getStationSearch() {
		return stationSearch;
	}

	public void setStationSearch(String stationSearch) {
		this.stationSearch = stationSearch;
	}

	public int getStationIdentifier() {
		return stationIdentifier;
	}

	public void setStationIdentifier(int stationIdentifier) {
		this.stationIdentifier = stationIdentifier;
	}
	
	
}
