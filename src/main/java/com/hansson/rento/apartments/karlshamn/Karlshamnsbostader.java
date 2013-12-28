package com.hansson.rento.apartments.karlshamn;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hansson.rento.apartments.ApartmentsInterface;
import com.hansson.rento.entities.Apartment;

public class Karlshamnsbostader implements ApartmentsInterface {

	private static final String LANDLORD = "Karlshamnsbost&auml;der";
	private static final String BASE_URL = "http://sokbostad.karlshamnsbostader.se/";
	private Logger mLog = LoggerFactory.getLogger("rento");

	@Override
	public List<Apartment> getAvailableApartments() {
		List<Apartment> apartmentList = new LinkedList<Apartment>();
		try {
			Document doc = Jsoup.connect(BASE_URL + "SearchResult1.aspx").get();
			Elements apartments = doc.getElementsByTag("tr");
			for (Element element : apartments) {
				try {
					if(element.getElementsByTag("td").get(0).getElementsByTag("img").size() > 0) {
						
					}
				} catch (Exception e) {
					mLog.error(LANDLORD + " error on element #" + apartments.indexOf(element));
					e.printStackTrace();
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return apartmentList;
	}

	@Override
	public String getLandlord() {
		return LANDLORD;
	}
}
