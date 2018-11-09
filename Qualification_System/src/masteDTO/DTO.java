package masteDTO;

public class DTO {
	private String cname;
	private String sname;
	private String cfname;
	private String level;

	private String name;
	private String namek;
	private String email;
	private int did;
	private String dname;
	private int coid;
	private String cname2;
	private int year;
	private int clasies;
	private int question;

	private int sid;
	private String date;
	private String result;

	public DTO(String cname, String sname, String cfname, String level) {
		this.cname = cname;
		this.sname = sname;
		this.cfname = cfname;
		this.level = level;
	}

	public DTO(String name, String namek, String mail, int did, String dname, int coid, String cname2, int year,
			int clasies, int question) {
		this.name = name;
		this.namek = namek;
		this.email = mail;
		this.did = did;
		this.dname = dname;
		this.coid = coid;
		this.cname2 = cname2;
		this.year = year;
		this.clasies = clasies;
		this.question = question;
	}

	public DTO(int sid, String sname2, String cname3, String d, String date, String result) {
		this.sid = sid;
		this.sname = sname2;
		this.cname = cname3;
		this.date =date;
		this.level = d;
		this.result = result;

	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getCfname() {
		return cfname;
	}

	public void setCfname(String cfname) {
		this.cfname = cfname;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNamek() {
		return namek;
	}

	public void setNamek(String namek) {
		this.namek = namek;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getDid() {
		return did;
	}

	public void setDid(int did) {
		this.did = did;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public int getCoid() {
		return coid;
	}

	public void setCoid(int coid) {
		this.coid = coid;
	}

	public String getCname2() {
		return cname2;
	}

	public void setCname2(String cname2) {
		this.cname2 = cname2;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getClasies() {
		return clasies;
	}

	public void setClasies(int clasies) {
		this.clasies = clasies;
	}

	public int getQuestion() {
		return question;
	}

	public void setQuestion(int question) {
		this.question = question;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
