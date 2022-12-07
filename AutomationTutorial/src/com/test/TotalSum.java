package com.test;

public class TotalSum {
	
	private Long sNo;
	private Integer adultMale;
	private Integer adultFemale;
	private Integer childMale;
	private Integer childFemale;
	private Integer sum;
	
	public TotalSum() {
		
	}
	
	public TotalSum(Long sNo, Integer adultMale, Integer adultFemale, Integer childMale, Integer childFemale, Integer sum) {
		super();
		this.sNo = sNo;
		this.adultMale = adultMale;
		this.adultFemale = adultFemale;
		this.childMale = childMale;
		this.childFemale = childFemale;
		this.sum = sum;
	}

	

	public Long getsNo() {
		return sNo;
	}

	public void setsNo(Long sNo) {
		this.sNo = sNo;
	}

	public Integer getAdultMale() {
		return adultMale;
	}

	public void setAdultMale(Integer adultMale) {
		this.adultMale = adultMale;
	}

	public Integer getAdultFemale() {
		return adultFemale;
	}

	public void setAdultFemale(Integer adultFemale) {
		this.adultFemale = adultFemale;
	}

	public Integer getChildMale() {
		return childMale;
	}

	public void setChildMale(Integer childMale) {
		this.childMale = childMale;
	}

	public Integer getChildFemale() {
		return childFemale;
	}

	public void setChildFemale(Integer childFemale) {
		this.childFemale = childFemale;
	}

	public Integer getSum() {
		return sum;
	}

	public void setSum(Integer sum) {
		this.sum = sum;
	}

	@Override
	public String toString() {
		return "TotalSum [sNo=" + sNo + ", adultMale=" + adultMale + ", adultFemale=" + adultFemale + ", childMale="
				+ childMale + ", childFemale=" + childFemale + ", sum=" + sum + "]";
	}
	
}
