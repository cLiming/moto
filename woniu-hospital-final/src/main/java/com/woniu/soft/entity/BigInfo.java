package com.woniu.soft.entity;

import java.util.List;

public class BigInfo {
	private List<User> userList;
	
	private List<Drug> drugList;
	
	private List<Project> projectList;

	
	
	
	public BigInfo() {
	}

	public BigInfo(List<User> userList, List<Drug> drugList, List<Project> projectList) {
		super();
		this.userList = userList;
		this.drugList = drugList;
		this.projectList = projectList;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public List<Drug> getDrugList() {
		return drugList;
	}

	public void setDrugList(List<Drug> drugList) {
		this.drugList = drugList;
	}

	public List<Project> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<Project> projectList) {
		this.projectList = projectList;
	}

	@Override
	public String toString() {
		return "BigInfo [userList=" + userList + ", drugList=" + drugList + ", projectList=" + projectList + "]";
	}
	
	
	
	
}
