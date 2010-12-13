package se.greyzone.myhome.domain.publictransport;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "fareList")
public class FareList {

	@XmlElement(name = "fare")
	private List<Fare> fares;

	public List<Fare> getFares() {
		return fares;
	}

	public void setFares(List<Fare> fares) {
		this.fares = fares;
	}
}
