package com.hansson.rento.apartments.multiple;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.hansson.rento.apartments.ApartmentsInterface;
import com.hansson.rento.entities.Apartment;
import com.hansson.rento.utils.apartments.HSBCitiesJson;

public class HSBSydost implements ApartmentsInterface {

	private static final String LANDLORD = "HSB Sydost";
	private static final String BASE_URL = "http://www.hsbmarknad.se";
	private static final Logger mLog = LoggerFactory.getLogger("rento");

	@Override
	public List<Apartment> getAvailableApartments() {
		List<Apartment> apartmentList = new LinkedList<Apartment>();
		try {
			Document doc = Jsoup.connect(BASE_URL + "/sydost").get();
			Pattern p = Pattern.compile("addMarkersVisaNoll\\(.*\\)");
			Matcher matcher = p.matcher(doc.getElementsByTag("script").get(5).childNode(0).toString());
			matcher.find();
			Gson gson = new Gson();
			HSBCitiesJson[] fromJson = gson.fromJson(matcher.group().replaceAll("addMarkersVisaNoll\\(|, firstMap\\)", ""), HSBCitiesJson[].class);
			System.out.println("TEST");
//			for (Element element : cities) {
//				doc = Jsoup.connect(BASE_URL + element.attr("href")).get();
//				Elements apartments = doc.getElementsByClass("search-result-box");
//				for(Element apartmentInfo : apartments) {
//					Apartment apartment = new Apartment(LANDLORD);
//					apartment.setAddress(apartmentInfo.getElementsByTag("h2").text());
//					apartment.setCity(apartmentInfo.getElementsByClass("srb-image-title").get(0).text());
//					
//					Element infoBox = apartmentInfo.getElementsByClass("srb-info-basic").get(0);
//					apartment.setAddress(infoBox.getElementsByTag("h2").get(0).text());
//				}
//			}
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
