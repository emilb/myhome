package se.greyzone.myhome.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import se.greyzone.myhome.domain.user.User;

import com.google.code.morphia.DAO;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.query.Query;
import com.mongodb.Mongo;

@Repository
public class UserDao extends DAO<User, String>{

	@Autowired
	public UserDao(Mongo mongo, Morphia morphia) {
		super(mongo, morphia, "testdb");
		ds.ensureIndexes();
	}

	public User getUserByLogin(String login) {
		Query<User> q = this.createQuery().filter("login =", login);
		return findOne(q);
	}
}
