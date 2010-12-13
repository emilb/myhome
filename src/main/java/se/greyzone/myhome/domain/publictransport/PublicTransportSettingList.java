package se.greyzone.myhome.domain.publictransport;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "publicTransportSettingList")
public class PublicTransportSettingList {

	@XmlElement(name = "settings")
	private List<PublicTransportSetting> settings;

	public List<PublicTransportSetting> getSettings() {
		return settings;
	}

	public void setSettings(List<PublicTransportSetting> settings) {
		this.settings = settings;
	}
	
	
}
