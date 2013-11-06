package com.hansson.rento.apartments;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gag.annotation.disclaimer.CarbonFootprint;
import com.google.gag.enumeration.CO2Units;
import com.hansson.rento.entities.Apartment;
import com.hansson.rento.utils.HtmlUtil;

public class KarlskronahemApartments implements ApartmentsInterface {

	private final static String KARLSKRONA = "Karlskrona";
	private final static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.72 Safari/537.36";
	private final static String LANDLORD = "Karlskronahem";
	private final static String BASE_URL = "http://marknad.karlskronahem.se";

	private Logger mLog = LoggerFactory.getLogger("rento");
	private long mBackoff = 2;

	@Override
	@CarbonFootprint(units = CO2Units.FIRKINS_PER_FORTNIGHT, value = 167.5)
	public List<Apartment> getAvailableApartments() {
		boolean isDone = false;
		List<Apartment> apartments = null;
		while (!isDone) {
			try {
				apartments = getApartments();
				if (apartments != null) {
					isDone = true;
				}
			} catch (IOException io) {
				mLog.info("IO Exception, doing " + mBackoff + " backoff");
				if (mBackoff <= 64) {
					try {
						Thread.sleep(mBackoff * 1000); 
						mBackoff *= 2;
					} catch (InterruptedException e) {
						// Should never occur
					}
				} else {
					//Better luck next time
					isDone = true;
				}

			}
		}
		return apartments;

	}

	private List<Apartment> getApartments() throws IOException {
		List<Apartment> apartmentList = new LinkedList<Apartment>();
		try {
			// Get html for first page
			Document doc = Jsoup.connect(BASE_URL + "/HSS/Object/object_list.aspx?objectgroup=1").get();
			int currentPage = 1;
			// Find number of pages
			Elements pageSwitcher = doc.select(".right").get(0).getElementsByTag("a");
			int pages = pageSwitcher.size();
			// Handle and iterate all pages
			do {
				// Parse data from html
				// Get all <tr> tags from html with listitem-even or listitem-odd class
				Elements elementsByTag = doc.getElementsByTag("tr");
				// Iterate all elements
				for (Element element : elementsByTag) {
					if (element.hasClass("listitem-even") || element.hasClass("listitem-odd")) {
						Apartment apartment = new Apartment(LANDLORD);
						Element address = element.child(1).getElementsByTag("a").get(0);
						apartment.setUrl(BASE_URL + "/HSS/Object/" + address.attr("href"));
						apartment.setAddress(HtmlUtil.textToHtml(address.childNode(0).toString()));
						apartment.setIdentifier(address.attr("href").split("[&=]")[3]);
						apartment.setCity(KARLSKRONA);
						apartment.setArea(HtmlUtil.textToHtml(element.child(2).getElementsByTag("span").get(0).childNode(0).toString()));
						String rent = element.child(5).getElementsByTag("span").get(0).childNode(0).toString().replace("&nbsp;", "");
						apartment.setRent(Integer.valueOf(rent));
						apartment.setRooms(Double.valueOf(element.child(3).getElementsByTag("span").get(0).childNode(0).toString()));
						apartment.setSize(Integer.valueOf(element.child(4).getElementsByTag("span").get(0).childNode(0).toString()));
						apartmentList.add(apartment);
					}
				}
				// If there are more pages, prepare the next post
				if (pages > currentPage) {
					Elements viewState = doc.select("#__VIEWSTATE");
					Elements eventValidation = doc.select("#__EVENTVALIDATION");
					String eventTarget = pageSwitcher.get(currentPage).attr("id");
					eventTarget = eventTarget.replace("_", "$");
					Map<String, String> postData = new HashMap<String, String>();
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
					doc = Jsoup.connect("http://marknad.karlskronahem.se/HSS/Object/object_list.aspx?objectgroup=1").data(postData).userAgent(USER_AGENT).header("Content-Type", "application/x-www-form-urlencoded; charset=utf-8").post();
				}
				currentPage++;
			} while (currentPage <= pages);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return apartmentList;
	}
	@Override
	public String getLandlord() {
		return LANDLORD;
	}
}
