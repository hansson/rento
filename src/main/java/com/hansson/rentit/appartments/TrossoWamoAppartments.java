package com.hansson.rentit.appartments;

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

import com.hansson.rentit.entitys.Appartment;
import com.hansson.rentit.utils.HtmlUtil;

public class TrossoWamoAppartments implements AppartmentsInterface {

	@Override
	public List<Appartment> getAvailableAppartments() {
		List<Appartment> appartmentLIst = new LinkedList<Appartment>();
		try {
			Document doc = Jsoup.connect("http://bovision.se/more/MaklarLista.aspx?ai=9530").get();
			Elements dataList = doc.select(".data");
			Elements summaryList = doc.select(".summary");
			for (int i = 0; i < dataList.size(); i++) {
				Element data = dataList.get(i);
				Element summary = summaryList.get(i);
				Appartment appartment = new Appartment("Trossö, Wämö & Pribo fastigheter");
				appartment.setArea(data.select(".areaname").get(0).child(0).childNode(0).toString());
				appartment.setAddress(data.select(".adress").get(0).child(0).childNode(0).toString());
				String roomString = data.select(".rum").get(0).child(0).childNode(0).toString();
				Pattern roomPattern = Pattern.compile("\\d*");
				Matcher matcher = roomPattern.matcher(roomString);
				matcher.find();
				appartment.setRooms(Integer.valueOf(matcher.group()));
				String[] imageStringArray = data.child(0).child(0).child(0).attr("src").split("&");
				appartment.setImageUrl(imageStringArray[0] + "&" + imageStringArray[1] + "&" + "wm=256" + "&" + "hm=256"); // TODO change width and height when
																															// they are decided
				appartment.setRent(Integer.valueOf(data.select(".avgift").get(0).child(0).childNode(0).toString().replaceAll("\\D", "")));
				String size = data.select(".boarea").get(0).child(0).childNode(0).toString().replaceAll("\\D", "");
				appartment.setSize(Integer.valueOf(size.substring(0, size.length() - 1)));
				appartment.setUrl(data.select(".adress").get(0).child(0).attr("href"));
				appartment.setIdentifier(appartment.getUrl().split("/")[appartment.getUrl().split("/").length - 1]);
				appartment.setSummary(HtmlUtil.htmlToText(summary.child(0).childNode(0).toString().trim()));
				System.out.println(appartment.getSummary());
				appartmentLIst.add(appartment);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return appartmentLIst;
	}
}
