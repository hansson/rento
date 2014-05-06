package com.hansson.rento.apartments;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.hansson.rento.entities.Apartment;

public class ApartmentInfoFromTable extends ApartmentUtils {

	public List<Apartment> handle(Document doc, String baseUrl, String landlord, boolean singlePage) {
		List<Apartment> apartmentList = null;
		Elements elements = doc.getElementsByTag("tr");
//		elements.addAll(doc.getElementsByTag("tr"));
		// Try to find columns for city, address, price, etc.

		Columns cols = new Columns();
		for (Element element : elements) {
			Elements badRows = element.getElementsByTag("tr");
			if(badRows != null && badRows.size() > 1) {
				continue;
			}
			Elements cells = element.getElementsByTag("td");
			cols = resolveColumns(cells, cols);
		}

		// If no header was found we should not search for apartments with the
		// table pattern.
		if (cols.getHightest() > -1) {
			apartmentList = new LinkedList<Apartment>();
			for (Element element : elements) {
				Elements cells = element.getElementsByTag("td");
				if (cells.size() >= cols.getHightest()) {
					Apartment apartment = new Apartment(landlord);
					if (resolveColumnValues(apartment, cols, cells)) {
						if (singlePage) {
							apartmentList.add(apartment);
						} else {
							String url = element.getElementsByTag("a").attr("href");
							apartment.setUrl(baseUrl + url);
							doc = connect(apartment.getUrl());
							if (doc != null) {
								apartmentList.add(new ApartmentInfoSecondPage().handle(doc, apartment));
							}
						}
					}
				}
			}
		}

		return apartmentList;
	}

	private boolean resolveColumnValues(Apartment apartment, Columns cols, Elements cells) {
		boolean somethingSet = false;
		if (cols.getRooms() > -1 && !cellIsRoomsHeader(cells.get(cols.getRooms()))) {
			if (!checkIsValidRooms(cells.get(cols.getRooms()).text())) {
				return false;
			}
			Pattern p = Pattern.compile("[\\d,\\.]+");
			Matcher matcher = p.matcher(cells.get(cols.getRooms()).text());
			matcher.find();
			apartment.setRooms(Double.valueOf(matcher.group().replaceAll(",", ".")));
			somethingSet = true;
		}
		if (cols.getSize() > -1 && !cellIsSizeHeader(cells.get(cols.getSize()))) {
			if (!checkIsValidSize(cells.get(cols.getSize()).text())) {
				return false;
			}
			Pattern p = Pattern.compile("[\\d]+");
			Matcher matcher = p.matcher(cells.get(cols.getSize()).text());
			matcher.find();
			apartment.setSize(Integer.valueOf(matcher.group()));
			somethingSet = true;
		}
		if (cols.getRent() > -1 && !cellIsRentHeader(cells.get(cols.getRent()))) {
			if (!checkIsValidRent(cells.get(cols.getRent()).text())) {
				return false;
			}
			Pattern p = Pattern.compile("[\\d]+");
			Matcher matcher = p.matcher(cells.get(cols.getRent()).text());
			matcher.find();
			apartment.setRent(Integer.valueOf(matcher.group()));
			somethingSet = true;
		}
		if (cols.getCity() > -1 && !cellIsCityHeader(cells.get(cols.getCity()))) {
			if (!checkIsValidCityOrArea(cells.get(cols.getCity()).text())) {
				return false;
			}
			apartment.setCity(cells.get(cols.getCity()).text());
			somethingSet = true;
		}
		if (cols.getArea() > -1 && !cellIsAreaHeader(cells.get(cols.getArea()))) {
			if (!checkIsValidCityOrArea(cells.get(cols.getArea()).text())) {
				return false;
			}
			apartment.setArea(cells.get(cols.getArea()).text());
			somethingSet = true;
		}
		if (cols.getAddress() > -1 && !cellIsAddressHeader(cells.get(cols.getAddress()))) {
			apartment.setAddress(cells.get(cols.getAddress()).text());
			somethingSet = true;
		}
		return somethingSet;
	}

	protected boolean checkIsValidCityOrArea(String text) {
		if (text == null || text.equals("")) {
			return false;
		}
		Pattern p = Pattern.compile("[\\d\\.,!?]+");
		Matcher matcher = p.matcher(text);
		if (text.equals("")) {
			return true;
		} else {
			return !matcher.find();
		}
	}

	protected boolean checkIsValidRent(String text) {
		if (text == null || text.equals("")) {
			return false;
		}
		text = text.toLowerCase();
		text = text.replaceAll("[-:\\dkrse\\.,* ]", "");
		Pattern p = Pattern.compile("[\\D]+");
		Matcher matcher = p.matcher(text);
		if (text.equals("")) {
			return true;
		} else {
			return !matcher.find();
		}
	}

	protected boolean checkIsValidSize(String text) {
		if (text == null || text.equals("")) {
			return false;
		}
		text = text.toLowerCase();
		text = text.replaceAll("[\\dkvm ]", "");
		Pattern p = Pattern.compile("[\\D]+");
		Matcher matcher = p.matcher(text);
		if (text.equals("")) {
			return true;
		} else {
			return !matcher.find();
		}
	}


	protected boolean checkIsValidRooms(String text) {
		if (text == null || text.equals("")) {
			return false;
		}
		text = text.toLowerCase();
		text = text.replaceAll("[\\d,\\. rok]", "");
		Pattern p = Pattern.compile("[\\D]+");
		Matcher matcher = p.matcher(text);
		if (text.equals("")) {
			return true;
		} else {
			return !matcher.find();
		}
	}

	private Columns resolveColumns(Elements cells, Columns cols) {
		for (Element cell : cells) {
			if (cols.getCity() == -1 && cellIsCityHeader(cell)) {
				cols.setCity(cells.indexOf(cell));
			} else if (cols.getArea() == -1 && cellIsAreaHeader(cell)) {
				cols.setArea(cells.indexOf(cell));
			} else if (cols.getAddress() == -1 && cellIsAddressHeader(cell)) {
				cols.setAddress(cells.indexOf(cell));
			} else if (cols.getRooms() == -1 && cellIsRoomsHeader(cell)) {
				cols.setRooms(cells.indexOf(cell));
			} else if (cols.getSize() == -1 && cellIsSizeHeader(cell)) {
				cols.setSize(cells.indexOf(cell));
			} else if (cols.getRent() == -1 && cellIsRentHeader(cell)) {
				cols.setRent(cells.indexOf(cell));
			}
		}
		return cols;
	}

	private boolean cellIsCityHeader(Element cell) {
		String text = cell.text().replace(":", "");
		return text.equalsIgnoreCase("ort");
	}

	private boolean cellIsAreaHeader(Element cell) {
		String text = cell.text().replace(":", "");
		return text.equalsIgnoreCase("område");
	}

	private boolean cellIsAddressHeader(Element cell) {
		String text = cell.text().replace(":", "");
		return text.equalsIgnoreCase("adress");
	}

	private boolean cellIsRoomsHeader(Element cell) {
		String text = cell.text().replace(":", "");
		return text.equalsIgnoreCase("rum") || text.equalsIgnoreCase("lgh typ");
	}

	private boolean cellIsSizeHeader(Element cell) {
		String text = cell.text().replace(":", "");
		return text.equalsIgnoreCase("storlek") || text.equalsIgnoreCase("kvm");
	}

	private boolean cellIsRentHeader(Element cell) {
		String text = cell.text().replace(":", "");
		return text.equalsIgnoreCase("hyra") || text.equalsIgnoreCase("pris") || text.equalsIgnoreCase("hyra/mån");
	}

}
