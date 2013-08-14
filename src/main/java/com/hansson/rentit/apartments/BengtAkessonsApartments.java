package com.hansson.rentit.apartments;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import com.google.gag.annotation.disclaimer.CarbonFootprint;
import com.google.gag.enumeration.CO2Units;
import com.hansson.rentit.entitys.Apartment;

public class BengtAkessonsApartments implements ApartmentsInterface {

	private static final String KARLSKRONA = "Karlskrona";
	private final static String LANDLORD = "Bengt &Aring;kessons Fastigheter";
	private final static String BASE_URL = "http://web.bafast.se";

	@Override
	@CarbonFootprint(units = CO2Units.FIRKINS_PER_FORTNIGHT, value = 167.5)
	public List<Apartment> getAvailableApartments() {
		List<Apartment> apartmentLIst = new LinkedList<Apartment>();
		try {
			Document doc = Jsoup.connect(BASE_URL + "/category/lediga-objekt/lediga-lagenheter/").get();
			Elements elementsByClass = doc.getElementsByClass("post");
			for (Element element : elementsByClass) {
				Apartment apartment = new Apartment(LANDLORD);
				apartment.setArea(KARLSKRONA);
				apartment.setUrl(element.getElementsByTag("a").get(0).attr("href"));
				String imageUrl = element.getElementsByTag("img").get(0).attr("src");
				imageUrl = imageUrl.substring(0, imageUrl.indexOf("?w"));
				// TODO change width and height when they are decided
				imageUrl = imageUrl + "&w=128&h=128&crop=1";
				apartment.setImageUrl(imageUrl);
				String summaryString = "";
				for (Node node : element.getElementsByClass("entry").get(0).childNodes()) {
					if (node.childNodeSize() > 0) {
						if (node.nodeName().equalsIgnoreCase("p") && !node.childNode(0).nodeName().equalsIgnoreCase("a")) {
							// Add to summary.
							summaryString += node.childNode(0).childNode(0).toString();
						} else if (node.nodeName().equalsIgnoreCase("table")) {
							// Iterate table
						}
					}
				}
				apartment.setSummary(summaryString);
				apartmentLIst.add(apartment);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return apartmentLIst;
	}
}
