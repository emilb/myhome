package se.greyzone.myhome.domain.publictransport;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "fare")
public class Fare {

	@XmlElement(name = "line")
	private int lineNumber;
	
	@XmlElement(name = "destination")
	private String destination;
	
	@XmlElement(name = "departure")
	private String departure;
	
	public Fare() {}
	
	public Fare(int lineNumber, String destination, String departure) {
		this.lineNumber = lineNumber;
		this.destination = destination;
		this.departure = departure;
	}

	public int getLineNumber() {
		return lineNumber;
	}
	public String getDestination() {
		return destination;
	}
	public String getDeparture() {
		return departure;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	@Override
	public String toString() {
		return "Fare [lineNumber=" + lineNumber + ", destination="
				+ destination + ", departure=" + departure + "]";
	}
}
