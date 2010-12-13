package se.greyzone.myhome.vo.commute;

import java.util.List;

public class SiteQueryResult {
	public final String query;
	public final List<Site> sites;
	
	public SiteQueryResult(String query, List<Site> sites) {
		this.query = query;
		this.sites = sites;
	}
}
