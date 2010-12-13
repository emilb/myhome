package se.greyzone.myhome.web.action;

import java.io.IOException;
import java.net.URLEncoder;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import se.greyzone.myhome.domain.publictransport.PublicTransportSetting;
import se.greyzone.myhome.service.publictransport.CommuteService;
import se.greyzone.myhome.vo.commute.SiteQueryResult;
import se.greyzone.myhome.web.action.context.SiteArea;

@UrlBinding(value = "/commute.html")
public class CommuteAction extends BaseAction {

	@SpringBean
	protected CommuteService commute;
	
	@ValidateNestedProperties({ 
		@Validate(field = "name", required = true, maxlength = 50, minlength = 2),
		@Validate(field = "stationId", required = true)
	})
	PublicTransportSetting publicTransportSetting;
	
	@DefaultHandler
    @DontValidate
	public Resolution getResolution() {
		return new ForwardResolution("/WEB-INF/pages/commute/commute.jsp");
	}
	
	public Resolution save() {
		return getResolution();
	}
	
	@DontValidate
	public Resolution suggestStation() throws IOException {
		
		System.out.println("Suggesting station for: " + publicTransportSetting.getName());
		
		SiteQueryResult result = commute.querySites(URLEncoder.encode(publicTransportSetting.getName(), "UTF-8"));
		String response = toJson(result);
	    System.out.println(response);
		return new StreamingResolution("application/json", response);
	}

	public PublicTransportSetting getPublicTransportSetting() {
		return publicTransportSetting;
	}

	public void setPublicTransportSetting(
			PublicTransportSetting publicTransportSetting) {
		this.publicTransportSetting = publicTransportSetting;
	}
	
	public SiteArea getSiteArea() {
		return SiteArea.COMMUTE;
	}
	
}
