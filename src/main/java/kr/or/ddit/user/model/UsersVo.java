package kr.or.ddit.user.model;

import java.util.List;

public class UsersVo {
	
	private List<UserVo> userVoList;

	public List<UserVo> getUserVoList() {
		return userVoList;
	}

	public void setUserVoList(List<UserVo> userVoList) {
		this.userVoList = userVoList;
	}

	@Override
	public String toString() {
		return "UsersVo [userVoList=" + userVoList + "]";
	}

	
	
	
	
	/*
	 * public List<UserVo> getUsersVoList() { return userVoList; }
	 * 
	 * public void setUsersVoList(List<UserVo> userVoList) { this.userVoList =
	 * userVoList; }
	 * 
	 * @Override public String toString() { return "UsersVo [userVoList=" +
	 * userVoList + "]"; }
	 */
	

	
	
}
