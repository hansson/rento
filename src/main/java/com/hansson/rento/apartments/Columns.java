package com.hansson.rento.apartments;

public class Columns {

	private int mCity = -1;
	private int mArea = -1;
	private int mAddress = -1;
	private int mRooms = -1;
	private int mSize = -1;
	private int mPrice = -1;
	private int mHightest = -1;

	public int getCity() {
		return mCity;
	}

	public void setCity(int city) {
		mCity = city;
		if (city > mHightest) {
			mHightest = city;
		}
	}

	public int getArea() {
		return mArea;
	}

	public void setArea(int area) {
		mArea = area;
		if (area > mHightest) {
			mHightest = area;
		}
	}

	public int getAddress() {
		return mAddress;
	}

	public void setAddress(int address) {
		mAddress = address;
		if (address > mHightest) {
			mHightest = address;
		}
	}

	public int getRooms() {
		return mRooms;
	}

	public void setRooms(int rooms) {
		mRooms = rooms;
		if (rooms > mHightest) {
			mHightest = rooms;
		}
	}

	public int getSize() {
		return mSize;
	}

	public void setSize(int size) {
		mSize = size;
		if (size > mHightest) {
			mHightest = size;
		}
	}

	public int getPrice() {
		return mPrice;
	}

	public void setPrice(int price) {
		mPrice = price;
		if (price > mHightest) {
			mHightest = price;
		}
	}

	public int getHightest() {
		return mHightest;
	}

}
