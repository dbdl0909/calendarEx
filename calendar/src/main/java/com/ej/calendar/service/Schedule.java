package com.ej.calendar.service;

public class Schedule {
	private int scheduleNo;
	private String scheduleDate;
	private String scheduleTitle;
	private String scheduleContent;
	private String schedulePlace;
	private String scheduleTitleColor;
	
	public int getScheduleNo() {
		return scheduleNo;
	}
	public void setScheduleNo(int scheduleNo) {
		this.scheduleNo = scheduleNo;
	}
	public String getScheduleDate() {
		return scheduleDate;
	}
	public void setScheduleDate(String scheduleDate) {
		this.scheduleDate = scheduleDate;
	}
	public String getScheduleTitle() {
		return scheduleTitle;
	}
	public void setScheduleTitle(String scheduleTitle) {
		this.scheduleTitle = scheduleTitle;
	}
	public String getScheduleContent() {
		return scheduleContent;
	}
	public void setScheduleContent(String scheduleContent) {
		this.scheduleContent = scheduleContent;
	}
	public String getSchedulePlace() {
		return schedulePlace;
	}
	public void setSchedulePlace(String schedulePlace) {
		this.schedulePlace = schedulePlace;
	}
	public String getScheduleTitleColor() {
		return scheduleTitleColor;
	}
	public void setScheduleTitleColor(String scheduleTitleColor) {
		this.scheduleTitleColor = scheduleTitleColor;
	}
	
	@Override
	public String toString() {
		return "Schedule [scheduleNo=" + scheduleNo + ", scheduleDate=" + scheduleDate + ", scheduleTitle="
				+ scheduleTitle + ", scheduleContent=" + scheduleContent + ", schedulePlace=" + schedulePlace
				+ ", scheduleTitleColor=" + scheduleTitleColor + "]";
	}
}
