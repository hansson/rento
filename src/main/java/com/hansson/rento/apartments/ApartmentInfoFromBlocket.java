package com.hansson.rento.apartments;

import java.util.LinkedList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.hansson.rento.entities.Apartment;

public class ApartmentInfoFromBlocket extends ApartmentUtils {

	public List<Apartment> handle(Document doc, String landlord) {
		List<Apartment> apartmentList = new LinkedList<Apartment>();
		Elements elements = doc.getElementsByClass("item_row");
		for(Element element : elements) {
			Apartment apartment = new Apartment(landlord);
			apartment.setUrl(element.attr("href"));
			doc = connect(apartment.getUrl());
			apartment.setRent(Integer.valueOf(doc.getElementById("vi_price").text().replaceAll("\\D", "")));
			Element addressAndCity = doc.getElementsByClass("adparam_map_link").get(0);
			apartment.setAddress(addressAndCity.getElementsByTag("a").text());
			apartment.setCity(addressAndCity.getElementsByTag("div").text());
			apartmentList.add(apartment);
		}
		return apartmentList;
	}

}
