package com.hansson.rento.apartments.smaland.vaxjo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.hansson.rento.apartments.ApartmentUtils;
import com.hansson.rento.apartments.ApartmentsInterface;
import com.hansson.rento.entities.Apartment;
import com.hansson.rento.utils.apartments.VaxjoApartment;
import com.hansson.rento.utils.apartments.VaxjoJson;

public class BoplatsVaxjoStudent extends ApartmentUtils implements ApartmentsInterface {
	
	private final static String LANDLORD = "Boplats V&auml;xj&ouml; studentl&auml;genhet";
	private final static String BASE_URL = "http://bpvx.vaxjo.se/API/Service/SearchServiceHandler.ashx";
	private final static String URL_TEMPLATE = "http://bpvx.vaxjo.se/pgObjectInformation.aspx?company=1&obj=%s&mg=2&tradingAd=0&goback=y&ru=/pgSearchResult.aspx#seekAreaMode=simple&search=y&page=1&syndicateNo=1&objectMainGroupNo=2&rent=0;15000&area=0;150&advertisement=-1&scrollto=1140-014-01-0205&scrollto=1140-017-01-0104";
	@Override
	public List<Apartment> getAvailableApartments() {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("X-Momentum-API-KEY", "mK4ld96AVfqt2V9e9Jc7RA==");
		headers.put("Content-Type", "application/x-www-form-urlencoded");
		headers.put("Accept", "application/json");
		Map<String, String> postData = new HashMap<String, String>();
		postData.put("Parm1", "{\"CompanyNo\":0,\"SyndicateNo\":1,\"ObjectMainGroupNo\":2,\"MarketPlaces\":[{\"No\":3}],\"Advertisements\":[{\"No\":-1}],\"RentLimit\":{\"Min\":0,\"Max\":15000},\"AreaLimit\":{\"Min\":0,\"Max\":150},\"Page\":1,\"Take\":10,\"SortOrder\":\"PublishingDate asc,SeekAreaDescription asc,StreetName asc\",\"ReturnParameters\":[\"ObjectNo\",\"FirstEstateImageUrl\",\"Street\",\"SeekAreaDescription\",\"PlaceName\",\"ObjectSubDescription\",\"ObjectArea\",\"RentPerMonth\",\"MarketPlaceDescription\",\"CountInterest\",\"FirstInfoTextShort\",\"FirstInfoText\",\"EndPeriodMP\",\"FreeFrom\",\"SeekAreaUrl\",\"Latitude\",\"Longitude\",\"BoardNo\"]}");
		postData.put("CallbackMethod", "PostObjectSearch");
		postData.put("CallbackParmCount", "1");
		postData.put("__WWEVENTCALLBACK", "");
		Document doc = connect(BASE_URL, postData, headers, true);
		List<Apartment> apartmentList = new LinkedList<Apartment>();
		List<VaxjoApartment>  results = new Gson().fromJson(doc.text(), VaxjoJson.class).getResult();
		for(VaxjoApartment result : results) {
			Apartment apartment = new Apartment(LANDLORD);
			apartment.setStudent(true);
			apartment.setUrl(String.format(URL_TEMPLATE, result.getObjectNo()));
			apartment.setAddress(result.getStreet());
			apartment.setArea(result.getSeekAreaDescription());
			apartment.setCity(result.getPlaceName());
			apartment.setRent(result.getRentPerMonthSort());
			String rooms = result.getObjectSubDescription().replaceAll("\\D", "");
			apartment.setRooms(Double.valueOf(!rooms.trim().equals("") ? rooms : "0"));
			apartment.setSize((int)result.getObjectAreaSort());
			apartmentList.add(apartment);
		}
		return apartmentList;
	}

	@Override
	public String getLandlord() {
		return LANDLORD;
	}

}
