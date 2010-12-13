package se.greyzone.myhome.service.publictransport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import se.greyzone.myhome.util.http.XmlContent;
import se.greyzone.myhome.util.timer.StopWatch;
import se.greyzone.myhome.vo.commute.Site;
import se.greyzone.myhome.vo.commute.SiteQueryResult;

@Service
public class CommuteService {

	private String queryStationUrl = "http://2.latest.resisthlm-api.appspot.com/api/GetSites?query=";
	private String userAgent = "ResISTHLM/2.1.1,gzip";
	
	public SiteQueryResult querySites(String query) {
		
		StopWatch sw = new StopWatch();
		String swName = "querySites for " + query;
		sw.startTimerFor(swName);
		
		//TODO: Inject a httpclient from pool (faster and more optimized)
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(queryStationUrl + query);
		get.addHeader("User-Agent", userAgent);

		sw.addLapTimePoint(swName, "Create HTTPClient");
		
		List<Site> siteList = new ArrayList<Site>();
		
		try {
			HttpResponse httpResponse = client.execute(get);
			sw.addLapTimePoint(swName, "Execute HTTPClient");
			
			XmlContent content = new XmlContent(httpResponse);
			sw.addLapTimePoint(swName, "Parse XML");
			
			Document doc = content.getXmlDocument();
			
			NodeList sites = doc.getElementsByTagName("site");
			sw.addLapTimePoint(swName, "Get nodelist");
			
			for (int i = 0; i < sites.getLength(); i++) {
				Node nsite = sites.item(i);
				
				Site site = createSite(nsite);
				if (StringUtils.isNotBlank(site.area))
					siteList.add(site);
			}
			sw.addLapTimePoint(swName, "Creating response");
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(sw);
		
		return new SiteQueryResult(query, siteList);
	}
		
	private Site createSite(Node node) {
		NodeList nlist = node.getChildNodes();
		String name = null;
		String identifier = null;
		String area = null;
		
		for (int i = 0; i < nlist.getLength(); i++) {
			Node n = nlist.item(i);
			if (StringUtils.equals("name",n.getNodeName()))
				name = n.getTextContent();
			else if (StringUtils.equals("identifier",n.getNodeName()))
				identifier = n.getTextContent();
			else if (StringUtils.equals("area",n.getNodeName()))
				area = n.getTextContent();
		}
		
		return new Site(identifier, name, area);
	}
}
