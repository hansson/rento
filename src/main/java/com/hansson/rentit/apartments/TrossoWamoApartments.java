package com.hansson.rentit.apartments;

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

import com.google.gag.annotation.disclaimer.CarbonFootprint;
import com.google.gag.enumeration.CO2Units;
import com.hansson.rentit.entitys.Apartment;
import com.hansson.rentit.utils.HtmlUtil;

public class TrossoWamoApartments implements ApartmentsInterface {

	private static final String LANDLORD = "Tross&ouml;, W&auml;m&ouml; & Pribo fastigheter";
	private static final String BASE_URL = "http://bovision.se/more/MaklarLista.aspx?ai=9530";

	@Override
	@CarbonFootprint(units = CO2Units.FIRKINS_PER_FORTNIGHT, value = 42.1)
	public List<Apartment> getAvailableApartments() {
		List<Apartment> apartmentLIst = new LinkedList<Apartment>();
		try {
			Document doc = Jsoup.connect(BASE_URL).get();
			Elements dataList = doc.select(".data");
			Elements summaryList = doc.select(".summary");
			for (int i = 0; i < dataList.size(); i++) {
				Element data = dataList.get(i);
				Element summary = summaryList.get(i);
				Apartment apartment = new Apartment(HtmlUtil.textToHtml(LANDLORD));
				String[] split = data.select(".areaname").get(0).child(0).childNode(0).toString().split(" ");
				apartment.setCity(split[0]);
				String area = "";
				for (int j = 1; j < split.length; j++) {
					area += split[j];
				}
				apartment.setArea(area);
				apartment.setAddress(data.select(".adress").get(0).child(0).childNode(0).toString());
				String roomString = data.select(".rum").get(0).child(0).childNode(0).toString();
				Pattern roomPattern = Pattern.compile("\\d*");
				Matcher matcher = roomPattern.matcher(roomString);
				matcher.find();
				apartment.setRooms(Integer.valueOf(matcher.group()));
				String[] imageStringArray = data.child(0).child(0).child(0).attr("src").split("&");
				if (imageStringArray.length > 1) {
					// TODO change width and height when they are decided
					apartment.setImageUrl(imageStringArray[0] + "&" + imageStringArray[1] + "&" + "wm=128" + "&" + "hm=128");
				} else {
					if (imageStringArray[0].contains("no_img.png")) {
						apartment.setImageUrl(LOGO);
					} else {
						apartment.setImageUrl(imageStringArray[0]);
					}
				}
				apartment.setRent(Integer.valueOf(data.select(".avgift").get(0).child(0).childNode(0).toString().replaceAll("\\D", "")));
				String size = data.select(".boarea").get(0).child(0).childNode(0).toString().replaceAll("\\D", "");
				apartment.setSize(Integer.valueOf(size.substring(0, size.length() - 1)));
				apartment.setUrl(data.select(".adress").get(0).child(0).attr("href"));
				apartment.setIdentifier(apartment.getUrl().split("/")[apartment.getUrl().split("/").length - 1]);
				apartment.setSummary(summary.child(0).childNode(0).toString().trim());
				apartmentLIst.add(apartment);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return apartmentLIst;
	}
}
