package com.hansson.rento.apartments.karlskrona;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.hansson.rento.apartments.ApartmentsInterface;
import com.hansson.rento.entities.Apartment;

public class HansAkessonFastigheter implements ApartmentsInterface {

	private static final String KARLSKRONA = "Karlskrona";
	private final static String LANDLORD = "Hans &Aring;kessons Fastigheter";
	private final static String BASE_URL = "http://www.hansakessonfastigheter.se/page/3/lediga-lagenheter.aspx";

	@Override
	public List<Apartment> getAvailableApartments() {
		List<Apartment> apartmentLIst = new LinkedList<Apartment>();
		try {
			Document doc = Jsoup.connect(BASE_URL).get();
			Elements elementsByClass = doc.getElementsByClass("cutebox");
			for (Element element : elementsByClass) {
				Apartment apartment = new Apartment(LANDLORD);
				apartment.setCity(KARLSKRONA);
				apartment.setUrl(BASE_URL);
				Elements header = element.getElementsByTag("h2");
				
				Pattern p = Pattern.compile("\\d+[\\.\\d]* rok");
				Matcher matcher = p.matcher(header.text());
				if(matcher.find()) {
					Elements apartmentTable = element.getElementsByTag("tbody");
					apartment.setAddress(apartmentTable.get(0).child(1).child(0).text());
					apartment.setRooms(Double.valueOf(apartmentTable.get(0).child(1).child(1).text()));
					apartment.setSize(Integer.valueOf(apartmentTable.get(0).child(1).child(2).text()));
					p = Pattern.compile("[\\d\\.]+ kr");
					matcher = p.matcher(apartmentTable.get(0).child(1).child(4).text());
					matcher.find();
					apartment.setRent(Integer.valueOf(matcher.group().replace(".", "").split(" ")[0]));
					apartmentLIst.add(apartment);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return apartmentLIst;
	}

	@Override
	public String getLandlord() {
		return LANDLORD;
	}
}
