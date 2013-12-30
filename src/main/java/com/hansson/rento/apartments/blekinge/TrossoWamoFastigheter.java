package com.hansson.rento.apartments.blekinge;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hansson.rento.apartments.ApartmentUtils;
import com.hansson.rento.apartments.ApartmentsInterface;
import com.hansson.rento.entities.Apartment;

public class TrossoWamoFastigheter extends ApartmentUtils implements ApartmentsInterface {

	private static final String LANDLORD = "Tross&ouml;, W&auml;m&ouml; & Pribo fastigheter";
	private static final String BASE_URL = "http://bovision.se/more/MaklarLista.aspx?ai=9530";
	
	private static final Logger mLog = LoggerFactory.getLogger("rento");

	@Override
	public List<Apartment> getAvailableApartments() {
		List<Apartment> apartmentList = new LinkedList<Apartment>();
		Document doc = connect(BASE_URL);
		if (doc != null) {
			Elements apartments = doc.select(".data");
			for (Element element : apartments) {
				try {
					Apartment apartment = new Apartment(LANDLORD);
					String[] split = element.select(".areaname").get(0).child(0).childNode(0).toString().split(" ");
					apartment.setCity(split[0]);
					String area = "";
					for (int j = 1; j < split.length; j++) {
						area += split[j];
					}
					apartment.setArea(area);
					apartment.setAddress(element.select(".adress").get(0).child(0).childNode(0).toString());
					String roomString = element.select(".rum").get(0).child(0).childNode(0).toString();
					Pattern roomPattern = Pattern.compile("\\d*");
					Matcher matcher = roomPattern.matcher(roomString);
					matcher.find();
					apartment.setRooms(Double.valueOf(matcher.group()));
					apartment.setRent(Integer.valueOf(element.select(".avgift").get(0).child(0).childNode(0).toString().replaceAll("\\D", "")));
					String size = element.select(".boarea").get(0).child(0).childNode(0).toString().replaceAll("\\D", "");
					apartment.setSize(Integer.valueOf(size.substring(0, size.length() - 1)));
					apartment.setUrl(element.select(".adress").get(0).child(0).attr("href"));
					apartment.setIdentifier(apartment.getUrl().split("/")[apartment.getUrl().split("/").length - 1]);
					apartmentList.add(apartment);
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
