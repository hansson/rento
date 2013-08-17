package com.hansson.rentit.apartments;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
					summaryString += handleNode(node, apartment);
				}
				apartment.setSummary(summaryString);
				apartmentLIst.add(apartment);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return apartmentLIst;
	}

	private String handleNode(Node node, Apartment apartment) {
		String summaryString = "";
		if (node.childNodeSize() > 0) {
			if (node.nodeName().equalsIgnoreCase("p")) {
				Node childNode = node.childNode(0);
				while (childNode != null) {
					if (!childNode.nodeName().equalsIgnoreCase("a") && childNode.toString().trim().length() != 0) {
						summaryString += node.childNode(0).childNode(0).toString();
						Pattern p = Pattern.compile("Objekt: [-0-9]+");
						Matcher matcher = p.matcher(summaryString += node.childNode(0).childNode(0).toString());
						if (matcher.find()) {
							apartment.setIdentifier(matcher.group().split(" ")[1]);
						}
					}
					childNode = childNode.nextSibling();
				}
			} else if (node.nodeName().equalsIgnoreCase("table")) {
				apartment.setRooms(Integer.valueOf(node.childNode(1).childNode(1).childNode(1).childNode(1).childNode(0).toString()));
				apartment.setRent(Integer.valueOf(node.childNode(1).childNode(1).childNode(3).childNode(1).childNode(0).toString().replaceAll("\\D", "")));
				apartment.setSize(Integer.valueOf(node.childNode(1).childNode(3).childNode(1).childNode(1).childNode(0).toString().replace("m&sup2;", "").replaceAll("\\D", "")));
				apartment.setAddress(node.childNode(1).childNode(3).childNode(3).childNode(1).childNode(0).toString());
			}
		}
		return summaryString;
	}
}
