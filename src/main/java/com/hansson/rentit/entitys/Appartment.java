package com.hansson.rentit.entitys;

public class Appartment {

	private String mArea;
	private String mAddress;
	private String mUrl;
	private String mImageUrl;
	private String mSummary;
	private int mRooms;
	private int mSize;
	private int mRent;
	private String mIdentifier;
	private String mLandlord;

	public Appartment() {
	}

	public Appartment(String landlord) {
		mLandlord = landlord;
	}

	public String getArea() {
		return mArea;
	}

	public void setArea(String area) {
		mArea = area;
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

	public String getImageUrl() {
		return mImageUrl;
	}

	public void setImageUrl(String imageUrl) {
		mImageUrl = imageUrl;
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
