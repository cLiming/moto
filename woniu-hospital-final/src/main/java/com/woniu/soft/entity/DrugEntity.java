package com.woniu.soft.entity;

public class DrugEntity {
	private Integer uId;
	private Integer drugId;
	private Integer number;
	private String drugName;
	
	public Integer getuId() {
		return uId;
	}
	public void setuId(Integer uId) {
		this.uId = uId;
	}
	public Integer getDrugId() {
		return drugId;
	}
	public void setDrugId(Integer drugId) {
		this.drugId = drugId;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	@Override
	public String toString() {
		return "DrugEntity [uId=" + uId + ", drugId=" + drugId + ", numbers=" + number + ", drugName=" + drugName
				+ "]";
	}
	
	
}
