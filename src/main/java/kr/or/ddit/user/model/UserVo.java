package kr.or.ddit.user.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

public class UserVo {
	
	private String userid;
	private String usernm;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")		// 3번 방법.어노테이션 추가
	private Date reg_dt;
	
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	private Date hire_dt;
	
	// 주입은 1,000 로 함
	// 1000 => 1,000, 1,000 => 1000
	@NumberFormat(pattern = "#,###")
	private int price;
	
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Date getHire_dt() {
		return hire_dt;
	}

	public void setHire_dt(Date hire_dt) {
		this.hire_dt = hire_dt;
	}

	public Date getReg_dt() {
		return reg_dt;
	}

	public void setReg_dt(Date reg_dt) {
		this.reg_dt = reg_dt;
	}

	public UserVo() {} // 기본생성자 생성
	
	public UserVo(String userid, String usernm) {
		setUserid(userid);
		setUsernm(usernm);
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

	@Override
	public String toString() {
		return "UserVo [userid=" + userid + ", usernm=" + usernm + ", reg_dt=" + reg_dt + "]";
	}


	
	
	
	
}
