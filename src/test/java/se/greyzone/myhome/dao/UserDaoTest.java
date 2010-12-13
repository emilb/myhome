package se.greyzone.myhome.dao;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import se.greyzone.myhome.domain.user.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"classpath:applicationContext.xml"})
public class UserDaoTest {

	@Autowired
	private UserDao userDao;
	
	@Test
	public void testSaveUser() {
		User user = new User("emil3", "emil", "hejhopp", null);
		userDao.save(user);
		
		User emil = userDao.getUserByLogin("emil");
		Assert.assertEquals(user.getLogin(), emil.getLogin());
		System.out.println(emil.getId());
		//4cca6793afbbe77a15946a32
	}
	
	
}
