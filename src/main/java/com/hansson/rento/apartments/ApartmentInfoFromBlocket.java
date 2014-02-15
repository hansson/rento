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
		Element list = doc.getElementById("item_list");
		if (list != null && list.children() != null) {
			Elements elements = list.children();
			for (Element element : elements) {
				Apartment apartment = new Apartment(landlord);
				apartment.setUrl(element.getElementsByTag("a").attr("href"));
				doc = connect(apartment.getUrl());
				apartment.setRent(Integer.valueOf(doc.getElementById("vi_price").text().replaceAll("\\D", "")));
				Element addressAndCity = doc.getElementsByClass("adparam_map_link").get(0);
				apartment.setAddress(addressAndCity.getElementsByTag("a").text());
				apartment.setCity(addressAndCity.getElementsByTag("div").get(1).text());
				Elements roomsAndSize = doc.getElementById("view_params").getElementsByTag("li");
				apartment.setRooms(Double.valueOf(roomsAndSize.get(2).text().replaceAll("\\D", "")));
				apartment.setSize(Integer.valueOf(roomsAndSize.get(3).text().replaceAll("&sup2;", "").replaceAll("\\D", "")));
				apartmentList.add(apartment);
			}
		}
		return apartmentList;
	}

}
