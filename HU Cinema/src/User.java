public class User {
    
    private String username, password;
    private Boolean admin,memberClub;
   
    public User(String username, String password,Boolean memberClub,Boolean admin) {
        this.username = username;
        this.password = password;
        this.memberClub = memberClub;
        this.admin = admin;
      
    }
	public String getUsername() {
		return username;
	}


	public Boolean getAdmin() {
		return admin;
	}
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	public Boolean getMemberClub() {
		return memberClub;
	}
	public void setMemberClub(Boolean memberClub) {
		this.memberClub = memberClub;
	}
	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	public String toString() {
		return username+ " "+ password+" "+memberClub+" "+admin;
	}


	
    
    
    
    
}