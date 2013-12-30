package com.hansson.rento.apartments.blekinge.karlskrona;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hansson.rento.apartments.ApartmentUtils;
import com.hansson.rento.apartments.ApartmentsInterface;
import com.hansson.rento.entities.Apartment;

public class HansAkessonFastigheter extends ApartmentUtils implements ApartmentsInterface {

	private static final String KARLSKRONA = "Karlskrona";
	private final static String LANDLORD = "Hans &Aring;kessons Fastigheter";
	private final static String BASE_URL = "http://www.hansakessonfastigheter.se/page/3/lediga-lagenheter.aspx";

	private static final Logger mLog = LoggerFactory.getLogger("rento");

	@Override
	public List<Apartment> getAvailableApartments() {
		List<Apartment> apartmentList = new LinkedList<Apartment>();
		Document doc = connect(BASE_URL);
		if (doc != null) {
			Elements apartments = doc.getElementsByClass("cutebox");
			for (Element element : apartments) {
				try {
					Apartment apartment = new Apartment(LANDLORD);
					apartment.setCity(KARLSKRONA);
					apartment.setUrl(BASE_URL);
					Elements header = element.getElementsByTag("h2");

					Pattern p = Pattern.compile("\\d+[\\.\\d]* rok");
					Matcher matcher = p.matcher(header.text());
					if (matcher.find()) {
						Elements apartmentTable = element.getElementsByTag("tbody");
						apartment.setAddress(apartmentTable.get(0).child(1).child(0).text());
						apartment.setRooms(Double.valueOf(apartmentTable.get(0).child(1).child(1).text()));
						apartment.setSize(Integer.valueOf(apartmentTable.get(0).child(1).child(2).text()));
						p = Pattern.compile("[\\d\\.]+ kr");
						matcher = p.matcher(apartmentTable.get(0).child(1).child(4).text());
						matcher.find();
						apartment.setRent(Integer.valueOf(matcher.group().replace(".", "").split(" ")[0]));
						apartmentList.add(apartment);
					}
				} catch (Exception e) {
					mLog.error(LANDLORD + " error on element #" + apartments.indexOf(element));
					e.printStackTrace();
				}
			}
		}
		return apartmentList;
	}

	@Override
	public String getLandlord() {
		return LANDLORD;
	}
}
