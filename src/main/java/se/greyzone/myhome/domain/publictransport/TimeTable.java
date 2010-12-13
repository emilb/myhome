package se.greyzone.myhome.domain.publictransport;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "timetable")
public class TimeTable {

	@XmlElement(name = "location")
	private String location;
	
	@XmlElement(name = "type")
	private String transportType;
	
	@XmlElement(name = "updated")
	private String updatedAt;
	
	@XmlElement(name = "fareList")
	private FareList fares;
	
	public TimeTable() {}
	
	public TimeTable(String location, String transportType, String updatedAt, List<Fare> fares) {
		this.location = location;
		this.transportType = transportType;
		this.updatedAt = updatedAt;
		this.fares = new FareList();
		this.fares.setFares(fares);
	}

	public String getLocation() {
		return location;
	}
	public String getTransportType() {
		return transportType;
	}
	public String getUpdatedAt() {
		return updatedAt;
	}
	public List<Fare> getFares() {
		return fares.getFares();
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setTransportType(String transportType) {
		this.transportType = transportType;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public void setFares(List<Fare> fares) {
		this.fares.setFares(fares);
	}

	@Override
	public String toString() {
		return "TimeTable [location=" + location + ", transportType="
				+ transportType + ", updatedAt=" + updatedAt + ", fares="
				+ fares + "]";
	}
}
