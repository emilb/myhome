package se.greyzone.myhome.dao;

import junit.framework.Assert;

import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import se.greyzone.myhome.domain.publictransport.PublicTransportSetting;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"classpath:applicationContext.xml"})
public class PublicTransportDaoTest {

	@Autowired
	private PublicTransportDao ptDao;
	
	@Test
	public void testSaveUser() {
		PublicTransportSetting pts = new PublicTransportSetting();
		pts.setName("Slussen");
		
		ptDao.save(pts);
		
		PublicTransportSetting pts2 = ptDao.get(new ObjectId(pts.getId()));
		
		
		Assert.assertTrue(pts2 != null);
		System.out.println(pts2.getName());
		System.out.println(pts.getId());
	}
	
	
}
