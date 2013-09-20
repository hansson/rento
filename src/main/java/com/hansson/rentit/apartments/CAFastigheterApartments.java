package com.hansson.rentit.apartments;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
			Elements scriptTags = doc.getElementsByTag("script");
			List<String> availableCities = new LinkedList<String>();
			for (Element scriptTag : scriptTags) {
				if (scriptTag.childNodes().size() > 0 && scriptTag.childNodes().get(0).toString().trim().contains("var OrtTypData")) {
					String var = scriptTag.childNodes().get(0).toString().trim();
					var = var.substring(var.indexOf("=") + 3, var.indexOf(";") - 1);
					String[] split = var.replaceAll("Ort.*?,| |\\{|\\}|\"|Value|,", "").split(":");
					for (String s : split) {
						if (!s.equals("")) {
							availableCities.add(s);
						}
					}
				}
			}
			// First select item is not a city
			for (String city : availableCities) {
				System.out.println("Requesting " + city);
				Elements viewState = doc.select("#__VIEWSTATE");
				String eventTarget = "hfOrtTyp";
				eventTarget = eventTarget.replace("_", "$");
				Map<String, String> postData = new HashMap<String, String>();
				postData.put("__EVENTTARGET", eventTarget);
				postData.put("__EVENTARGUMENT", "");
				postData.put("__VIEWSTATE", viewState.get(0).attr("value"));
				postData.put("dxObjectState", "");
				postData.put("ctl00$Header1$ToolsMenu1$txtSearch", "");
				postData.put("ctl00$ContentPlaceHolder1$sectionMain$ctl00$ctl00$ctl00$ctl00$ddlOrt", city);
				postData.put("ctl00$ContentPlaceHolder1$sectionMain$ctl00$ctl00$ctl00$ctl00$hfOrtTyp", city);
				doc = Jsoup.connect(BASE_URL + "/Sok_ledigt/Lediga_bostader").data(postData).userAgent(USER_AGENT).header("Content-Type",
						"application/x-www-form-urlencoded; charset=utf-8").post();
				Element element = doc.getElementsByClass("listHolder").first().getElementsByTag("ul").first().getElementsByTag("li").first();
				while (element != null) {
					Apartment apartment = new Apartment(LANDLORD);
					apartment.setCity(city);
					Element linkElement = element.getElementsByTag("a").first();
					apartment.setUrl(BASE_URL + linkElement.attr("href"));
					doc = Jsoup.connect(apartment.getUrl()).get();
					Elements infoBox = doc.getElementsByClass("internalPuff").first().getElementsByTag("p");
					apartment.setAddress(infoBox.get(0).childNode(0).toString().trim());
					for (Element info : infoBox) {
						if (info.childNode(0).toString().endsWith("kvm")) {
							apartment.setSize(Integer.valueOf(info.childNode(0).toString().trim().split(" ")[0]));
						} else if (info.childNode(0).toString().endsWith("kr")) {
							apartment.setRent(Integer.valueOf(info.childNode(0).toString().replaceAll("\\D", "")));
						} else if (info.childNode(0).toString().endsWith("rum")) {
							apartment.setRooms(Integer.valueOf(info.childNode(0).toString().trim().split(" ")[0]));
						} else if (info.childNode(0).toString().contains("-") && !isDate(info.childNode(0).toString())) {
							apartment.setIdentifier(info.childNode(0).toString().trim());
						}
					}
					element = element.nextElementSibling();
					apartmentLIst.add(apartment);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return apartmentLIst;
	}

	private boolean isDate(String inputString) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			sdf.parse(inputString);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}
}
