package se.greyzone.myhome.domain.user;

import java.util.List;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;
import com.google.code.morphia.utils.IndexDirection;

@Entity(concern="SAFE", value="users")
public class User {

	@Id
	private String id;
	
	private String name;
	
	@Indexed(value=IndexDirection.ASC, unique=true, dropDups=false)
	private String login;
	private String password;
	private List<Role> roles;
	
	public User() {}
	
	public User(String name, String login, String password, List<Role> roles) {
		this.name = name;
		this.login = login;
		this.password = password;
		this.roles = roles;
	}
	
	public String getId() {
		return id.toString();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
