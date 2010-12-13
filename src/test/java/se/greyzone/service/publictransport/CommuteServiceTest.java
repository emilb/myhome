package se.greyzone.service.publictransport;

import junit.framework.Assert;

import org.junit.Test;

import se.greyzone.myhome.service.publictransport.CommuteService;
import se.greyzone.myhome.vo.commute.SiteQueryResult;

public class CommuteServiceTest {

	@Test
	public void testQuerySites() {
		CommuteService cs = new CommuteService();
		
		SiteQueryResult result = cs.querySites("eks");
		Assert.assertEquals("eks", result.query);
		System.out.println("");
		cs.querySites("eks");
		
		System.out.println("");
		cs.querySites("eks");

	}
}
