package quizzer.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity								//define the class as persistent class
@Table(name = "user_table")                           //table name created in DB
@Inheritance(strategy=InheritanceType.JOINED)
public class RegisteredUsers {                                 //persistent class name
              
	
	@Id												//marks the attribute as primary key
    @GeneratedValue
    @NotNull
    @Column(name = "user_id" , unique = true)
    private int userId;
	
	@NotNull
    @Column(name = "user_name")
    private String userName;
    
	@NotNull
    @Column(name = "user_password")
    private String userPassword;
    
	@NotNull
    @Column(name = "user_role")
	private String userRole;
    
	// no parameter constructor
    public RegisteredUsers(){}					
    
  //parameterized constructor
    public RegisteredUsers(String password,String username,String role){              
    	
    	this.userName = username;
    	this.userPassword=password; 
    	this.userRole=role;
    }
    
    //getters and setters 
    
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
