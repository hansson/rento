package com.hansson.rentit.entitys;

public class Apartment {

	private String mArea;
	private String mCity;
	private String mAddress;
	private String mUrl;
	private String mSummary;
	private int mRooms;
	private int mSize;
	private int mRent;
	// This identifier should be unique to be able to decide if the apartment is a new one or if it was already stored in the database.
	private String mIdentifier;
	private String mLandlord;

	public Apartment() {
	}

	public Apartment(String landlord) {
		mLandlord = landlord;
	}

	public String getArea() {
		return mArea;
	}

	public void setArea(String area) {
		mArea = area;
	}

	public String getCity() {
		return mCity;
	}

	public void setCity(String city) {
		mCity = city;
	}

	public String getAddress() {
		return mAddress;
	}

	public void setAddress(String address) {
		mAddress = address;
	}

	public String getUrl() {
		return mUrl;
	}

	public void setUrl(String url) {
		mUrl = url;
	}

	public int getRooms() {
		return mRooms;
	}

	public void setRooms(int rooms) {
		mRooms = rooms;
	}

	public int getSize() {
		return mSize;
	}

	public void setSize(int size) {
		mSize = size;
	}

	public int getRent() {
		return mRent;
	}

	public void setRent(int rent) {
		mRent = rent;
	}

	public String getSummary() {
		return mSummary;
	}

	public void setSummary(String summary) {
		mSummary = summary;
	}

	public String getIdentifier() {
		return mIdentifier;
	}

	public void setIdentifier(String identifier) {
		mIdentifier = identifier;
	}

	public String getLandlord() {
		return mLandlord;
	}

	public void setLandlord(String landlord) {
		mLandlord = landlord;
	}
}
