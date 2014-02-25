package com.hansson.rento.apartments.blekinge.karlshamn;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
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

		try {
			Connection.Response res = Jsoup.connect("http://sokbostad.karlshamnsbostader.se/").execute();
			Document doc = res.parse();
			String sessionId = res.cookie("ASP.NET_SessionId");
			Map<String, String> postData = new HashMap<String, String>();
			postData.put("lghTyp", "Alla");
			postData.put("antalRum", "Alla");
			postData.put("hyra", "Alla");
			postData.put("mkdPlats", "Alla");
			postData.put("omrade", "Alla");
			postData.put("utkommet", "Alla");

			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Cookie", "ASP.NET_SessionId" + sessionId);
			doc = connect(BASE_URL + "SearchResult1.aspx", postData, headers, false);

			if (doc != null) {
				Elements apartments = doc.getElementsByTag("tr");
				for (Element element : apartments) {
					try {
						if (element.getElementsByTag("td").get(0).getElementsByTag("img").size() > 0) {
							Apartment apartment = new Apartment(LANDLORD);
							apartment.setUrl(BASE_URL + element.getElementsByTag("a").attr("href"));
							apartment.setIdentifier(apartment.getUrl().split("=")[1]);
							doc = connect(apartment.getUrl());
							String[] cityAndAreaSplit = doc.getElementById("Label7").text().split(" ");
							apartment.setCity(cityAndAreaSplit[0]);
							String area = "";
							for (int i = 1; i < cityAndAreaSplit.length; i++) {
								area += cityAndAreaSplit[i];
							}
							apartment.setArea(area);
							apartment.setAddress(doc.getElementById("Label5").text().split("\\d{3} {0,1}\\d{2}")[0]);
							apartment.setRent(Integer.valueOf(doc.getElementById("Label6").text().replaceAll("\\D", "")));
							apartment.setSize(Integer.valueOf(doc.getElementById("Label4").text().replaceAll("\\.\\d|\\D", "")));
							String rooms = doc.getElementById("Label2").text().replaceAll("\\D", "");
							if(rooms.equalsIgnoreCase("stud")) {
								apartment.setRooms(1d);
							} else if(!rooms.trim().equals("")){
								apartment.setRooms(Double.valueOf(rooms));
							} else {
								apartment.setRooms(0d);
							}
							apartment.setStudent(doc.getElementById("Label1").text().equalsIgnoreCase("studentrum"));
							apartmentList.add(apartment);
						}
					} catch (Exception e) {
						mLog.error(LANDLORD + " error on element #" + apartments.indexOf(element));
						e.printStackTrace();
					}
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return apartmentList;
	}

	@Override
	public String getLandlord() {
		return LANDLORD;
	}
}
