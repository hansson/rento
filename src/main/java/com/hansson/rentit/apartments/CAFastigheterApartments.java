package com.hansson.rentit.apartments;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gag.annotation.disclaimer.CarbonFootprint;
import com.google.gag.enumeration.CO2Units;
import com.hansson.rentit.entitys.Apartment;

public class CAFastigheterApartments implements ApartmentsInterface {

	private static final String KARLSKRONA = "Karlskrona";
	private final static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.72 Safari/537.36";
	private final static String LANDLORD = "CA Fastigheter";
	private final static String BASE_URL = "http://www.cafastigheter.se/";

	@Override
	@CarbonFootprint(units = CO2Units.FIRKINS_PER_FORTNIGHT, value = 167.5)
	public List<Apartment> getAvailableApartments() {
		List<Apartment> apartmentLIst = new LinkedList<Apartment>();
		try {
			// Get html for first page
			Document doc = Jsoup.connect(BASE_URL + "/Sok_ledigt/Lediga_bostader").get();
			Elements viewState = doc.select("#__VIEWSTATE");
			String eventTarget = "hfOrtTyp";
			eventTarget = eventTarget.replace("_", "$");
			Map<String, String> postData = new HashMap<String, String>();
			postData.put("__EVENTTARGET", eventTarget);
			postData.put("__EVENTARGUMENT", "");
			postData.put("__VIEWSTATE", viewState.get(0).attr("value"));
			postData.put("dxObjectState", "");
			postData.put("ctl00$Header1$ToolsMenu1$txtSearch", "");
			postData.put("ctl00$ContentPlaceHolder1$sectionMain$ctl00$ctl00$ctl00$ctl00$ddlOrt", KARLSKRONA);
			postData.put("ctl00$ContentPlaceHolder1$sectionMain$ctl00$ctl00$ctl00$ctl00$hfOrtTyp", KARLSKRONA);
			doc = Jsoup.connect(BASE_URL + "/Sok_ledigt/Lediga_bostader").data(postData).userAgent(USER_AGENT).header("Content-Type",
					"application/x-www-form-urlencoded; charset=utf-8").post();
			Element element = doc.getElementsByClass("listHolder").first().getElementsByTag("ul").first().getElementsByTag("li").first();
			while (element != null) {
				Apartment apartment = new Apartment(LANDLORD);
				Element linkElement = element.getElementsByTag("a").first();
				String imageUrl = linkElement.getElementsByTag("img").attr("src");
				imageUrl = imageUrl.substring(0, imageUrl.indexOf("width"));
				// TODO change width and height when they are decided
				imageUrl += "width=128&amp;height=128&amp;ScaleAndClipMode=ScaleHeightClipWidth";
				apartment.setImageUrl(imageUrl);
				apartment.setUrl(BASE_URL + linkElement.attr("href"));
				doc = Jsoup.connect(apartment.getUrl()).get();
				element = element.nextElementSibling();
				apartmentLIst.add(apartment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return apartmentLIst;
	}
}
