package masteDTO;

public class StudentDTO {
	private int id;
	private String name;
	private String namek;
	private String email;
	private int subject;
	private String subject_name;
	private int department;
	private String department_name;
	private int course;
	private String course_name;
	private int school_year;
	private int set_in;
	private int question;
	private String answer;
	private String pass;

	public StudentDTO(int id, String name, String namek, String email, int subject, int department, int course,
			int school_year, int set_in, int question, String answer, String key) {
		this.id = id;
		this.name = name;
		this.namek = namek;
		this.email = email;
		this.subject = subject;
		this.department = department;
		this.course = course;
		this.school_year = school_year;
		this.set_in = set_in;
		this.question = question;
		this.answer = answer;
		this.pass = key;
	}

	public StudentDTO(int id, String name, String dname, String cname, int year, int clasies) {
		this.id = id;
		this.name = name;
		this.department_name = dname;
		this.course_name = cname;
		this.school_year = year;
		this.set_in = clasies;
	}

	public StudentDTO(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public StudentDTO(int id, String name, String namek, String email, int department, int course,
			int school_year, int set_in, int question, String answer, String key) {
		this.id = id;
		this.name = name;
		this.namek = namek;
		this.email = email;
		this.department = department;
		this.course = course;
		this.school_year = school_year;
		this.set_in = set_in;
		this.question = question;
		this.answer = answer;
		this.pass = key;
	}

	public StudentDTO(String name2, String namek2, String mail, int subject2, String dname, int did, String cname,
			int coid, int year, int clasies, int question2) {
		this.name = name2;
		this.namek = namek2;
		this.email = mail;
		this.subject = subject2;
		this.department_name = dname;
		this.department = did;
		this.course_name = cname;
		this.course = coid;
		this.school_year = year;
		this.set_in = clasies;
		this.question = question2;
	}

	public StudentDTO(String name2, String namek2, String mail, String subject2, int subid, String dname, int did,
			String cname, int coid, int year, int clasies, int question2) {
		this.name = name2;
		this.namek = namek2;
		this.email = mail;
		this.subject_name = subject2;
		this.subject = subid;
		this.department_name = dname;
		this.department = did;
		this.course_name = cname;
		this.course = coid;
		this.school_year = year;
		this.set_in = clasies;
		this.question = question2;
	}

	public StudentDTO(int sid) {
		this.id = sid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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


	public int getSubject() {
		return subject;
	}

	public void setSubject(int subject) {
		this.subject = subject;
	}

	public String getSubject_name() {
		return subject_name;
	}

	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name;
	}

	public int getDepartment() {
		return department;
	}

	public void setDepartment(int department) {
		this.department = department;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public int getCourse() {
		return course;
	}

	public void setCourse(int course) {
		this.course = course;
	}

	public int getSchool_year() {
		return school_year;
	}

	public void setSchool_year(int school_year) {
		this.school_year = school_year;
	}

	public int getSet_in() {
		return set_in;
	}

	public void setSet_in(int set_in) {
		this.set_in = set_in;
	}

	public int getQuestion() {
		return question;
	}

	public void setQuestion(int question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}
