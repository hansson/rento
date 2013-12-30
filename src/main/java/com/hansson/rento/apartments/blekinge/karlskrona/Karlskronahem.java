package com.hansson.rento.apartments.blekinge.karlskrona;

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

public class Karlskronahem extends ApartmentUtils implements ApartmentsInterface {

	private final static String KARLSKRONA = "Karlskrona";
	private final static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.72 Safari/537.36";
	private final static String LANDLORD = "Karlskronahem";
	private final static String BASE_URL = "http://marknad.karlskronahem.se";

	private Logger mLog = LoggerFactory.getLogger("rento");

	@Override
	public List<Apartment> getAvailableApartments() {
		List<Apartment> apartmentList = new LinkedList<Apartment>();
		// Get html for first page
		Document doc = connect(BASE_URL + "/HSS/Object/object_list.aspx?objectgroup=1");
		if (doc != null) {
			int currentPage = 1;
			// Find number of pages
			Elements pageSwitcher = doc.select(".right").get(0).getElementsByTag("a");
			int pages = pageSwitcher.size();
			// Handle and iterate all pages
			do {
				// Parse data from html
				// Get all <tr> tags from html with listitem-even or
				// listitem-odd class
				Elements apartments = doc.getElementsByTag("tr");
				// Iterate all elements
				for (Element element : apartments) {
					try {
						if (element.hasClass("listitem-even") || element.hasClass("listitem-odd")) {
							Apartment apartment = new Apartment(LANDLORD);
							Element address = element.child(1).getElementsByTag("a").get(0);
							apartment.setUrl(BASE_URL + "/HSS/Object/" + address.attr("href"));
							apartment.setAddress(address.childNode(0).toString());
							apartment.setIdentifier(address.attr("href").split("[&=]")[3]);
							apartment.setCity(KARLSKRONA);
							apartment.setArea(element.child(2).getElementsByTag("span").get(0).childNode(0).toString());
							String rent = element.child(5).getElementsByTag("span").get(0).childNode(0).toString().replace("&nbsp;", "");
							apartment.setRent(Integer.valueOf(rent));
							apartment.setRooms(Double.valueOf(element.child(3).getElementsByTag("span").get(0).childNode(0).toString()));
							apartment.setSize(Integer.valueOf(element.child(4).getElementsByTag("span").get(0).childNode(0).toString()));
							apartmentList.add(apartment);
						}
					} catch (Exception e) {
						mLog.error(LANDLORD + " error on element #" + apartments.indexOf(element));
						e.printStackTrace();
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
					doc = connect("http://marknad.karlskronahem.se/HSS/Object/object_list.aspx?objectgroup=1", postData, USER_AGENT);
				}
				currentPage++;
			} while (currentPage <= pages);
		}
		return apartmentList;
	}

	@Override
	public String getLandlord() {
		return LANDLORD;
	}
}
