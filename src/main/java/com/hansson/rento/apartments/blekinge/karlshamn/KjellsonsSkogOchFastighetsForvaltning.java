package com.hansson.rento.apartments.blekinge.karlshamn;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hansson.rento.ApartmentService;
import com.hansson.rento.apartments.ApartmentUtils;
import com.hansson.rento.apartments.ApartmentsInterface;
import com.hansson.rento.entities.Apartment;

public class KjellsonsSkogOchFastighetsForvaltning extends ApartmentUtils implements ApartmentsInterface {

	private static final String LANDLORD = "Kjellsons Skog- &amp; Fastighetsf&ouml;rvaltning";
	private static final String BASE_URL = "http://www.kjellssonsforvaltning.se/LedLgh.htm";
	private Logger mLog = LoggerFactory.getLogger("rento");

	@Override
	public List<Apartment> getAvailableApartments() {
		List<Apartment> apartmentList = new LinkedList<Apartment>();
		Document doc = connect(BASE_URL);
		if (doc != null) {
			Elements apartments = doc.getElementsByTag("tr");
			apartments.remove(0); //Remove header
			for (Element element : apartments) {
				try {
					if(element.getElementsByTag("td").size() == 3) {
						Apartment apartment = new Apartment(LANDLORD);
						apartment.setCity("Karlshamn");
						apartment.setUrl(BASE_URL);
						String[] cityAndAddress = element.getElementsByTag("td").get(0).getElementsByTag("a").text().split(" ");
						if(cityAndAddress.length > 2) {
							String address = "";
							for(int i = 0 ; i < cityAndAddress.length - 1 ; i++) {
								address+= cityAndAddress[i];
							}
							apartment.setAddress(address);
						} else {
							apartment.setAddress(cityAndAddress[0]);
						}
						apartment.setCity(cityAndAddress[cityAndAddress.length - 1]);
						
						Pattern p = Pattern.compile("\\d+ rok");
						Matcher matcher = p.matcher(element.getElementsByTag("td").get(1).text());
						matcher.find();
						apartment.setRooms(Double.valueOf(matcher.group().replaceAll("\\D", "")));
						
						p = Pattern.compile("\\d+ kr/m");
						matcher = p.matcher(element.getElementsByTag("td").get(1).text());
						matcher.find();
						apartment.setRent(Integer.valueOf(matcher.group().replaceAll("\\D", "")));
						
						p = Pattern.compile("\\d+ kvm");
						matcher = p.matcher(element.getElementsByTag("td").get(1).text());
						matcher.find();
						apartment.setSize(Integer.valueOf(matcher.group().replaceAll("\\D", "")));
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
