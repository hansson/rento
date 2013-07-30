package com.hansson.rentit.appartments;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.hansson.rentit.entitys.Apartment;

public class KarlskronahemApartments implements ApartmentsInterface {

	public final static String SELECT_PAGE = "ctl00$ctl01$DefaultSiteContentPlaceHolder1$Col1$upSearchFilterRent|ctl00$ctl01$DefaultSiteContentPlaceHolder1$Col1$ucNavBar$rptButtons$ctl0%d$btnPage";
	public final static String EVENT_TARGET = "ctl00$ctl01$DefaultSiteContentPlaceHolder1$Col1$ucNavBar$rptButtons$ctl%s$btnPage";
	public final static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.72 Safari/537.36";

	@Override
	public List<Apartment> getAvailableAppartments() {
		List<Apartment> appartmentLIst = new LinkedList<Apartment>();
		try {
			Document doc = Jsoup.connect("http://marknad.karlskronahem.se/HSS/Object/object_list.aspx?objectgroup=1").get();
			Elements viewState = doc.select("#__VIEWSTATE");
			Elements eventValidation = doc.select("#__EVENTVALIDATION");
			String moveToPage = String.format(SELECT_PAGE, 1);
			String eventTarget = String.format(EVENT_TARGET, 1);
			Map<String, String> postData = new HashMap<String, String>();
			postData.put("ctl00$ctl01$DefaultSiteContentPlaceHolder1$ScriptManager", moveToPage);
			postData.put("__EVENTTARGET", eventTarget);
			postData.put("__EVENTARGUMENT", "");
			postData.put("__VIEWSTATE", viewState.get(0).attr("value"));
			postData.put("__EVENTVALIDATION", eventValidation.get(0).attr("value"));
			postData.put("ctl00$ctl01$DefaultSiteContentPlaceHolder1$Col1$hdnRentMin", "0");
			postData.put("ctl00$ctl01$DefaultSiteContentPlaceHolder1$Col1$hdnRentMax", "15000");
			postData.put("ctl00$ctl01$DefaultSiteContentPlaceHolder1$Col1$hdnRoomMin", "1");
			postData.put("ctl00$ctl01$DefaultSiteContentPlaceHolder1$Col1$hdnRoomMax", "6");
			postData.put("ctl00$ctl01$DefaultSiteContentPlaceHolder1$Col1$hdnSearchedRentMin", "0");
			postData.put("ctl00$ctl01$DefaultSiteContentPlaceHolder1$Col1$hdnSearchedRentMax", "15000");
			postData.put("ctl00$ctl01$DefaultSiteContentPlaceHolder1$Col1$hdnSearchedRoomMin", "1");
			postData.put("ctl00$ctl01$DefaultSiteContentPlaceHolder1$Col1$hdnSearchedRoomMax", "6");
			postData.put("__ASYNCPOST", "true");
			doc = Jsoup.connect("http://marknad.karlskronahem.se/HSS/Object/object_list.aspx?objectgroup=1").data(postData).userAgent(USER_AGENT).post();
			System.out.println(doc);
			// Elements summaryList = doc.select(".summary");
			// for (int i = 0; i < dataList.size(); i++) {
			// Element data = dataList.get(i);
			// Element summary = summaryList.get(i);
			// Appartment appartment = new Appartment("Karlskronahem");
			// appartment.setArea(data.select(".areaname").get(0).child(0).childNode(0).toString());
			// appartment.setAddress(data.select(".adress").get(0).child(0).childNode(0).toString());
			// String roomString = data.select(".rum").get(0).child(0).childNode(0).toString();
			// Pattern roomPattern = Pattern.compile("\\d*");
			// Matcher matcher = roomPattern.matcher(roomString);
			// matcher.find();
			// appartment.setRooms(Integer.valueOf(matcher.group()));
			// String[] imageStringArray = data.child(0).child(0).child(0).attr("src").split("&");
			// appartment.setImageUrl(imageStringArray[0] + "&" + imageStringArray[1] + "&" + "wm=128" + "&" + "hm=128"); // TODO change width and height when
			// // they are decided
			// appartment.setRent(Integer.valueOf(data.select(".avgift").get(0).child(0).childNode(0).toString().replaceAll("\\D", "")));
			// String size = data.select(".boarea").get(0).child(0).childNode(0).toString().replaceAll("\\D", "");
			// appartment.setSize(Integer.valueOf(size.substring(0, size.length() - 1)));
			// appartment.setUrl(data.select(".adress").get(0).child(0).attr("href"));
			// appartment.setIdentifier(appartment.getUrl().split("/")[appartment.getUrl().split("/").length - 1]);
			// appartment.setSummary(HtmlUtil.htmlToText(summary.child(0).childNode(0).toString().trim()));
			// appartmentLIst.add(appartment);
			// }
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return appartmentLIst;
	}
}
