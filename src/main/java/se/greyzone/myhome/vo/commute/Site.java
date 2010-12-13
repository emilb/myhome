package se.greyzone.myhome.vo.commute;

public class Site {
	public final String identifier;
	public final String name;
	public final String area;
	
	public Site(String identifier, String name, String area) {
		this.identifier = identifier;
		this.name = name;
		this.area = area;
	}

	@Override
	public String toString() {
		return "Site [identifier=" + identifier + ", name=" + name + ", area="
				+ area + "]";
	}
}
