package masteDTO;

public class QualificationDTO {
	private int qid;
	private int cid;
	private String cname;
	private String level;
	private int sid;
	private int did;
	private int coid;
	private int year;
	private int clasies;
	private String redate;
	private String update;
	private String examdate;
	private String result;

	private String sname;
	private String s1;
	private String s2;
	private String s3;
	private String s4;

	private int count1;
	private int count2;
	private int count3;

	private String l1;
	private String l2;
	private String l3;
	private String l4;

	public QualificationDTO(int i, int cid, String name, String level, int sid, int department, int course,
			int school_year, int set_in, String dates, String dates2, String date, String result) {
		this.qid = i;
		this.cid = cid;
		this.cname = name;
		this.level = level;
		this.sid = sid;
		this.did = department;
		this.coid = course;
		this.year = school_year;
		this.clasies = set_in;
		this.redate = dates;
		this.update = dates;
		this.examdate = date;
		this.result = result;

	}

	public QualificationDTO(int cid, String name) {
		this.cid = cid;
		this.cname = name;
	}

	public QualificationDTO(int sid2, String sname, String cname2, String date, String result2, String s1, String s2,
			String s3, String s4) {
		this.sid = sid2;
		this.sname = sname;
		this.cname = cname2;
		this.examdate = date;
		this.result = result2;
		this.s1 = s1;
		this.s2 = s2;
		this.s3 = s3;
		this.s4 = s4;
	}

	public QualificationDTO(int counta, int counto, int counts) {
		this.count1 = counta;
		this.count2 = counto;
		this.count3 = counts;
	}

	public QualificationDTO(int sid2, String sname2, String cname2, String date, String result2) {
		this.sid = sid2;
		this.sname = sname2;
		this.cname = cname2;
		this.examdate = date;
		this.result = result2;
	}

	public QualificationDTO(int sid, String sname, String cname, String dates, String result, String s1,
			String s2, String s3, String s4, String l1, String l2, String l3, String l4) {
		this.sid = sid;
		this.sname = sname;
		this.cname = cname;
		this.examdate = dates;
		this.result = result;
		this.s1 = s1;
		this.s2 = s2;
		this.s3 = s3;
		this.s4 = s4;
		this.l1 = l1;
		this.l2 = l2;
		this.l3 = l3;
		this.l4 = l4;
	}

	public int getQid() {
		return qid;
	}

	public void setQid(int qid) {
		this.qid = qid;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public int getDid() {
		return did;
	}

	public void setDid(int did) {
		this.did = did;
	}

	public int getCoid() {
		return coid;
	}

	public void setCoid(int coid) {
		this.coid = coid;
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

	public String getRedate() {
		return redate;
	}

	public void setRedate(String redate) {
		this.redate = redate;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public String getExamdate() {
		return examdate;
	}

	public void setExamdate(String examdate) {
		this.examdate = examdate;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getS1() {
		return s1;
	}

	public void setS1(String s1) {
		this.s1 = s1;
	}

	public String getS2() {
		return s2;
	}

	public void setS2(String s2) {
		this.s2 = s2;
	}

	public String getS3() {
		return s3;
	}

	public void setS3(String s3) {
		this.s3 = s3;
	}

	public String getS4() {
		return s4;
	}

	public void setS4(String s4) {
		this.s4 = s4;
	}

	public int getCount1() {
		return count1;
	}

	public void setCount1(int count1) {
		this.count1 = count1;
	}

	public int getCount2() {
		return count2;
	}

	public void setCount2(int count2) {
		this.count2 = count2;
	}

	public int getCount3() {
		return count3;
	}

	public void setCount3(int count3) {
		this.count3 = count3;
	}

	public String getL1() {
		return l1;
	}

	public void setL1(String l1) {
		this.l1 = l1;
	}

	public String getL2() {
		return l2;
	}

	public void setL2(String l2) {
		this.l2 = l2;
	}

	public String getL3() {
		return l3;
	}

	public void setL3(String l3) {
		this.l3 = l3;
	}

	public String getL4() {
		return l4;
	}

	public void setL4(String l4) {
		this.l4 = l4;
	}

}