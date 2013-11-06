package com.hansson.rento.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "APARTMENTS", schema = "PUBLIC", catalog = "PUBLIC")
public class Apartment {

	private Integer mId;
	private String mArea;
	private String mCity;
	private String mAddress;
	private String mUrl;
	private Double mRooms;
	private Integer mSize;
	private Integer mRent;
	// This identifier should be unique to be able to decide if the apartment is a new one or if it was already stored in the database.
	private String mIdentifier;
	private String mLandlord;

	public Apartment() {
	}

	public Apartment(String landlord) {
		mLandlord = landlord;
	}

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return mId;
	}

	@Column(name = "MAREA", length = 64)
	public String getArea() {
		return mArea;
	}

	@Column(name = "MCITY", length = 64)
	public String getCity() {
		return mCity;
	}

	@Column(name = "MADDRESS", length = 64)
	public String getAddress() {
		return mAddress;
	}

	@Column(name = "MURL", length = 512)
	public String getUrl() {
		return mUrl;
	}

	@Column(name = "MROOMS")
	public Double getRooms() {
		return mRooms;
	}

	@Column(name = "MSIZE")
	public Integer getSize() {
		return mSize;
	}

	@Column(name = "MRENT")
	public Integer getRent() {
		return mRent;
	}

	@Column(name = "MIDENTIFIER", length = 512)
	public String getIdentifier() {
		return mIdentifier;
	}

	@Column(name = "MLANDLORD", length = 64)
	public String getLandlord() {
		return mLandlord;
	}

	public void setId(Integer id) {
		mId = id;
	}

	public void setArea(String area) {
		mArea = area;
	}

	public void setCity(String city) {
		mCity = city;
	}

	public void setAddress(String address) {
		mAddress = address;
	}

	public void setUrl(String url) {
		mUrl = url;
	}

	public void setRooms(Double rooms) {
		mRooms = rooms;
	}

	public void setSize(Integer size) {
		mSize = size;
	}

	public void setRent(Integer rent) {
		mRent = rent;
	}

	public void setIdentifier(String identifier) {
		mIdentifier = identifier;
	}

	public void setLandlord(String landlord) {
		mLandlord = landlord;
	}
	
	@Override
	public String toString() {
		return mLandlord + " - " + mIdentifier;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Apartment) {
			Apartment that = (Apartment) obj;
			return this.mLandlord.equals(that.mLandlord) && this.mIdentifier.equals(that.mIdentifier);
		} else {
			return false;
		}
	}

}
