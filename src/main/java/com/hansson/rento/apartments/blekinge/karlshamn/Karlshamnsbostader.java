package com.hansson.rento.apartments.blekinge.karlshamn;

import java.util.LinkedList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hansson.rento.apartments.ApartmentUtils;
import com.hansson.rento.apartments.ApartmentsInterface;
import com.hansson.rento.entities.Apartment;

public class Karlshamnsbostader extends ApartmentUtils implements ApartmentsInterface {

	private static final String LANDLORD = "Karlshamnsbost&auml;der";
	private static final String BASE_URL = "http://sokbostad.karlshamnsbostader.se/";
	private Logger mLog = LoggerFactory.getLogger("rento");

	@Override
	public List<Apartment> getAvailableApartments() {
		List<Apartment> apartmentList = new LinkedList<Apartment>();
		Document doc = connect(BASE_URL + "SearchResult1.aspx");
		if (doc != null) {
			Elements apartments = doc.getElementsByTag("tr");
			for (Element element : apartments) {
				try {
					if (element.getElementsByTag("td").get(0).getElementsByTag("img").size() > 0) {
						Apartment apartment = new Apartment(LANDLORD);
						apartment.setUrl(BASE_URL + element.getElementsByTag("td").get(0).getElementsByTag("a").attr("href"));
						apartment.setIdentifier(apartment.getUrl().split("=")[1]);
						doc = (connect(apartment.getUrl()));
						String[] cityAndAreaSplit = element.getElementById("Label7").text().split(" ");
						apartment.setCity(cityAndAreaSplit[0]);
						String area = "";
						for(int i = 1 ; i < cityAndAreaSplit.length ; i++) {
							area+= cityAndAreaSplit[i];
						}
						apartment.setArea(area);
						
						Elements apartmentInfo = element.getElementsByTag("tr");
						apartment.setStudent(false);
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
