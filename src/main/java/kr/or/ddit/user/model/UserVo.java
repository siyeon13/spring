package kr.or.ddit.user.model;

public class UserVo {
	
	private String userid;
	private String usernm;
	
	public UserVo() {} // 기본생성자 생성
	
	public UserVo(String userid, String usernm) {
		setUserid(userid);
		setUsernm(usernm);
	}
	@Override
	public String toString() {
		return "UserVo [userid=" + userid + ", usernm=" + usernm + "]";
	}
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsernm() {
		return usernm;
	}
	public void setUsernm(String usernm) {
		this.usernm = usernm;
	}
	
	
}
