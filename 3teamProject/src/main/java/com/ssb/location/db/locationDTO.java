package com.ssb.location.db;

public class locationDTO {
	private int location_id;
	private String location_name;
	private String location_phone;
	private String location_postcode;
	private String location_add;
	private String locationD_add;
	private String location_title;
	private String location_requested;
	private int member_id;

	public int getLocation_id() {
		return location_id;
	}

	public String getLocation_name() {
		return location_name;
	}

	public String getLocation_phone() {
		return location_phone;
	}

	public String getLocation_postcode() {
		return location_postcode;
	}

	public String getLocation_add() {
		return location_add;
	}

	public String getLocationD_add() {
		return locationD_add;
	}

	public String getLocation_title() {
		return location_title;
	}

	public String getLocation_requested() {
		return location_requested;
	}

	public int getMember_id() {
		return member_id;
	}

	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}

	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}

	public void setLocation_phone(String location_phone) {
		this.location_phone = location_phone;
	}

	public void setLocation_postcode(String location_postcode) {
		this.location_postcode = location_postcode;
	}

	public void setLocation_add(String location_add) {
		this.location_add = location_add;
	}

	public void setLocationD_add(String locationD_add) {
		this.locationD_add = locationD_add;
	}

	public void setLocation_title(String location_title) {
		this.location_title = location_title;
	}

	public void setLocation_requested(String location_requested) {
		this.location_requested = location_requested;
	}

	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}

	@Override
	public String toString() {
		return "locationDTO [location_id=" + location_id + ", location_name=" + location_name + ", location_phone="
				+ location_phone + ", location_postcode=" + location_postcode + ", location_add=" + location_add
				+ ", locationD_add=" + locationD_add + ", location_title=" + location_title + ", location_requested="
				+ location_requested + ", member_id=" + member_id + "]";
	}

}
