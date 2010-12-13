package se.greyzone.myhome.dao;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import se.greyzone.myhome.domain.publictransport.PublicTransportSetting;

import com.google.code.morphia.DAO;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;

@Repository
public class PublicTransportDao extends DAO<PublicTransportSetting, ObjectId> {

	@Autowired
	public PublicTransportDao(Mongo mongo, Morphia morphia) {
		super(mongo, morphia, "testdb");
		ds.ensureIndexes();
	}
}
