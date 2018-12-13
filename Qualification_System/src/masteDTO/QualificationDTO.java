package masteDTO;

public class QualificationDTO {
	private int qid;
	private String qname;
	private int cid;
	private String cname;
	private String classname;
	private String level;
	private int sid;
	private int subid;
	private String subject;
	private int did;
	private String depart;
	private int coid;
	private String course;
	private int year;
	private int clasies;
	private String redate;
	private String update;
	private String examdate;
	private String result;

	private String level1;
	private String level2;
	private String level3;
	private String level4;

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

	private int cid1;
	private int cid2;
	private int cid3;
	private int cid4;

	private String cname1;
	private String cname2;
	private String cname3;
	private String cname4;

	private String url1;
	private String passrate1;
	private String passing1;
	private String url2;
	private String passrate2;
	private String passing2;
	private String url3;
	private String passrate3;
	private String passing3;
	private String url4;
	private String passrate4;
	private String passing4;

	public QualificationDTO(int i, int cid, String name, String level, int sid, String subject, int department, int course,
			int school_year, int set_in, String dates, String date, String result) {
		this.qid = i;
		this.cid = cid;
		this.cname = name;
		this.level = level;
		this.sid = sid;
		this.subject = subject;
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

	public QualificationDTO(int qid, int sid, String sname2, String cname2, String date, String result2) {
		this.qid = qid;
		this.sid = sid;
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

	public QualificationDTO(int cid2, String cname2, String level2, int sid2, int depart, int course, int school,
			int clasies2) {
		this.cid = cid2;
		this.cname = cname2;
		this.level = level2;
		this.sid = sid2;
		this.did = depart;
		this.coid = course;
		this.year = school;
		this.clasies = clasies2;
	}

	public QualificationDTO(String name, String level, String day, String result) {
		this.cname = name;
		this.level = level;
		this.examdate = day;
		this.result = result;
	}

	public QualificationDTO(String cname2, String classname, String superior1, String level1,
			String superior2, String level2, String superior3, String level3, String superior4 ,String level4) {
		this.cname = cname2;
		this.classname = classname;
		this.level1 = level1;
		this.level2 = level2;
		this.level3 = level3;
		this.level4 = level4;
		this.s1 = superior1;
		this.s2 = superior2;
		this.s3 = superior3;
		this.s4 = superior4;
	}

	public QualificationDTO(int sid, String sname, String cname, String level, String dates, String result) {
		this.sid = sid;
		this.sname = sname;
		this.cname = cname;
		this.level = level;
		this.examdate = dates;
		this.result = result;
	}

	public QualificationDTO(int cid2, String cname2, String level, int sid2, String subject2, int depart, int course,
			int school, int clasies2) {
		this.cid = cid2;
		this.cname = cname2;
		this.level = level;
		this.sid = sid2;
		this.subject = subject2;
		this.did = depart;
		this.coid = course;
		this.year = school;
		this.clasies = clasies2;
	}

	public QualificationDTO(String cname2, String sname2, String depart, String course, int school, int clasies2,
			String dates, String result2) {
		this.cname = cname2;
		this.sname = sname2;
		this.depart = depart;
		this.course = course;
		this.year = school;
		this.clasies = clasies2;
		this.examdate = dates;
		this.result = result2;
	}

	public QualificationDTO(int key) {
		this.sid = key;
	}

	public QualificationDTO(int key, int sub, int did2, int coid2, int year2, int clasies2) {
		this.sid = key;
		this.subid= sub;
		this.did = did2;
		this.coid = coid2;
		this.year = year2;
		this.clasies = clasies2;
	}

	public QualificationDTO(String level, int cid2) {
		this.cid = cid2;
		this.level =level;
	}

	public QualificationDTO(String name) {
		this.cname = name;
	}

	public QualificationDTO(int cid, String cname, String level, int sid, int subject, int depart, int course,
			int school, int clasies) {
		this.cid = cid;
		this.cname = cname;
		this.level = level;
		this.sid = sid;
		this.subid = subject;
		this.did = depart;
		this.coid = course;
		this.year = school;
		this.clasies = clasies;

	}

	public QualificationDTO(int sid2, String sname2, String dname, String coname, String cname2, String dates,
			String result2) {
		this.sid = sid2;
		this.sname = sname2;
		this.depart = dname;
		this.course = coname;
		this.cname = cname2;
		this.examdate = dates;
		this.result = result2;
	}

	public QualificationDTO(int qid2, int sid2, String sname2, String dname, String coname, String cname2, String dates,
			String result2) {
		this.qid = qid2;
		this.sid = sid2;
		this.sname = sname2;
		this.depart = dname;
		this.course = coname;
		this.cname = cname2;
		this.examdate = dates;
		this.result = result2;
	}

	public QualificationDTO(int sid2, String sname2, String cname2, String dates, String result2) {
		this.sid = sid2;
		this.sname = sname2;
		this.cname = cname2;
		this.examdate = dates;
		this.result = result2;
	}

	public QualificationDTO(String cname2, String classname, int cid1, String level1, int cid2, String level2,
			int cid3, String level3, int cid4, String level4) {
		this.cname = cname2;
		this.classname = classname;
		this.level1 = level1;
		this.level2 = level2;
		this.level3 = level3;
		this.level4 = level4;
		this.cid1 = cid1;
		this.cid2 = cid2;
		this.cid3 = cid3;
		this.cid4 = cid4;
	}

	public QualificationDTO(String cname, String classname2, String cname1, String level1, String pass1, String passing1,
			String link1, String cname2, String level2, String pass2, String passing2, String link2, String cname3,
			String level3, String pass3, String passing3, String link3, String cname4, String level4, String pass4,
			String passing4, String link4) {
		this.cname = cname;
		this.classname = classname2;
		this.cname1 = cname1;
		this.level1 = level1;
		this.passrate1 = pass1;
		this.passing1 = passing1;
		this.url1  = link1;
		this.cname2 = cname2;
		this.level2 = level2;
		this.passrate2 = pass2;
		this.passing2 = passing2;
		this.url2  = link2;
		this.cname3 = cname3;
		this.level3 = level3;
		this.passrate3 = pass3;
		this.passing3 = passing3;
		this.url3  = link3;
		this.cname4 = cname4;
		this.level4 = level4;
		this.passrate4 = pass4;
		this.passing4 = passing4;
		this.url4  = link4;

	}

	public int getQid() {
		return qid;
	}

	public void setQid(int qid) {
		this.qid = qid;
	}

	public String getQname() {
		return qname;
	}

	public void setQname(String qname) {
		this.qname = qname;
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

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getDepart() {
		return depart;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
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


	public int getSubid() {
		return subid;
	}

	public void setSubid(int subid) {
		this.subid = subid;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
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

	public String getLevel1() {
		return level1;
	}

	public void setLevel1(String level1) {
		this.level1 = level1;
	}

	public String getLevel2() {
		return level2;
	}

	public void setLevel2(String level2) {
		this.level2 = level2;
	}

	public String getLevel3() {
		return level3;
	}

	public void setLevel3(String level3) {
		this.level3 = level3;
	}

	public String getLevel4() {
		return level4;
	}

	public void setLevel4(String level4) {
		this.level4 = level4;
	}

	public int getCid1() {
		return cid1;
	}

	public void setCid1(int cid1) {
		this.cid1 = cid1;
	}

	public int getCid2() {
		return cid2;
	}

	public void setCid2(int cid2) {
		this.cid2 = cid2;
	}

	public int getCid3() {
		return cid3;
	}

	public void setCid3(int cid3) {
		this.cid3 = cid3;
	}

	public int getCid4() {
		return cid4;
	}

	public void setCid4(int cid4) {
		this.cid4 = cid4;
	}

	public String getUrl1() {
		return url1;
	}

	public void setUrl1(String url1) {
		this.url1 = url1;
	}

	public String getPassrate1() {
		return passrate1;
	}

	public void setPassrate1(String passrate1) {
		this.passrate1 = passrate1;
	}

	public String getPassing1() {
		return passing1;
	}

	public void setPassing1(String passing1) {
		this.passing1 = passing1;
	}

	public String getUrl2() {
		return url2;
	}

	public void setUrl2(String url2) {
		this.url2 = url2;
	}

	public String getPassrate2() {
		return passrate2;
	}

	public void setPassrate2(String passrate2) {
		this.passrate2 = passrate2;
	}

	public String getPassing2() {
		return passing2;
	}

	public void setPassing2(String passing2) {
		this.passing2 = passing2;
	}

	public String getUrl3() {
		return url3;
	}

	public void setUrl3(String url3) {
		this.url3 = url3;
	}

	public String getPassrate3() {
		return passrate3;
	}

	public void setPassrate3(String passrate3) {
		this.passrate3 = passrate3;
	}

	public String getPassing3() {
		return passing3;
	}

	public void setPassing3(String passing3) {
		this.passing3 = passing3;
	}

	public String getUrl4() {
		return url4;
	}

	public void setUrl4(String url4) {
		this.url4 = url4;
	}

	public String getPassrate4() {
		return passrate4;
	}

	public void setPassrate4(String passrate4) {
		this.passrate4 = passrate4;
	}

	public String getPassing4() {
		return passing4;
	}

	public void setPassing4(String passing4) {
		this.passing4 = passing4;
	}

	public String getCname1() {
		return cname1;
	}

	public void setCname1(String cname1) {
		this.cname1 = cname1;
	}

	public String getCname2() {
		return cname2;
	}

	public void setCname2(String cname2) {
		this.cname2 = cname2;
	}

	public String getCname3() {
		return cname3;
	}

	public void setCname3(String cname3) {
		this.cname3 = cname3;
	}

	public String getCname4() {
		return cname4;
	}

	public void setCname4(String cname4) {
		this.cname4 = cname4;
	}

}