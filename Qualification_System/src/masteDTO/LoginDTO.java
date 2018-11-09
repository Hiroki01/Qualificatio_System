package masteDTO;

public class LoginDTO {
	private int id;
	private String pass;
	private String time;
	private int admin;

	public LoginDTO(int id, String pass) {
		this.id = id;
		this.pass = pass;
	}

	public LoginDTO(int tid, String pw, String time, int admin) {
		this.id = tid;
		this.pass = pw;
		this.time = time;
		this.admin = admin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

}
