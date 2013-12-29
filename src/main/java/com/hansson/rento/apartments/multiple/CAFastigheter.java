package com.hansson.rento.apartments.multiple;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hansson.rento.apartments.ApartmentUtils;
import com.hansson.rento.apartments.ApartmentsInterface;
import com.hansson.rento.entities.Apartment;

public class CAFastigheter extends ApartmentUtils implements ApartmentsInterface {

	private final static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.72 Safari/537.36";
	private final static String LANDLORD = "CA Fastigheter";
	private final static String BASE_URL = "http://www.cafastigheter.se";

	private static final Logger mLog = LoggerFactory.getLogger("rento");

	@Override
	public List<Apartment> getAvailableApartments() {
		List<Apartment> apartmentLIst = new LinkedList<Apartment>();
		try {
			// Get html for first page
			Document doc = connect(BASE_URL + "/Sok_ledigt/Lediga_bostader");
			if (doc != null) {
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

				Elements viewState = doc.select("#__VIEWSTATE");
				// First select item is not a city
				for (String city : availableCities) {
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
					doc = connect(BASE_URL + "/Sok_ledigt/Lediga_bostader", postData, USER_AGENT);
					viewState = doc.select("#__VIEWSTATE");
					Element element = doc.getElementsByClass("listHolder").first().getElementsByTag("ul").first().getElementsByTag("li").first();
					while (element != null) {
						try {
							Apartment apartment = new Apartment(LANDLORD);
							apartment.setCity(city);
							Element linkElement = element.getElementsByTag("a").first();
							apartment.setUrl(BASE_URL + linkElement.attr("href"));
							doc = connect(apartment.getUrl());
							Elements infoBox = doc.getElementsByClass("internalPuff").first().getElementsByTag("p");
							apartment.setAddress(infoBox.get(0).childNode(0).toString().trim());
							for (Element info : infoBox) {
								if (info.childNode(0).toString().endsWith("kvm")) {
									apartment.setSize(Integer.valueOf(info.childNode(0).toString().trim().split(" ")[0]));
								} else if (info.childNode(0).toString().endsWith("kr")) {
									apartment.setRent(Integer.valueOf(info.childNode(0).toString().replaceAll("\\D", "")));
								} else if (info.childNode(0).toString().endsWith("rum")) {
									apartment.setRooms(Double.valueOf(info.childNode(0).toString().trim().split(" ")[0].replaceAll(",", ".")));
								} else if (info.childNode(0).toString().contains("-") && !isDate(info.childNode(0).toString())) {
									apartment.setIdentifier(info.childNode(0).toString().trim());
								}
							}
							apartmentLIst.add(apartment);
						} catch (Exception e) {
							Element linkElement = element.getElementsByTag("a").first();
							mLog.error("Failed to retrieve apartment at url: " + BASE_URL + linkElement.attr("href"));
						}
						element = element.nextElementSibling();
					}
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

	@Override
	public String getLandlord() {
		return LANDLORD;
	}
}
