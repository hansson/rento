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

	public List<Apartment> handle(Document doc, String baseUrl, String landlord) {
		List<Apartment> apartmentList = null;
		Elements elements = doc.getElementsByTag("th");
		elements.addAll(doc.getElementsByTag("tr"));
		// Try to find columns for city, address, price, etc.

		Columns cols = new Columns();
		for (Element element : elements) {
			Elements cells = element.getElementsByTag("td");
			cols = resolveColumns(cells, cols);
		}

		// If no header was found we should not search for apartments with the table pattern.
		if (cols.getHightest() > -1) {
			apartmentList = new LinkedList<Apartment>();
			for (Element element : elements) {
				Elements cells = element.getElementsByTag("td");
				if (cells.size() >= cols.getHightest()) {
					Apartment apartment = new Apartment(landlord);
					if(resolveColumnValues(apartment, cols, cells)) {
						String url = element.getElementsByTag("a").attr("href");
						Document connect = connect(baseUrl + url);
						if(connect != null) {
							apartmentList.add(new ApartmentInfoSecondPage().handle(doc, apartment));
						}
					}
				}
			}
		}

		return apartmentList;

	}

	private boolean resolveColumnValues(Apartment apartment, Columns cols, Elements cells) {
		if (cols.getRooms() > -1 && !cellIsRooms(cells.get(cols.getRooms()))) {
			if (!checkIsValidRooms(cells.get(cols.getRooms()).text())) {
				return false;
			}
			Pattern p = Pattern.compile("[\\d,\\.]+");
			Matcher matcher = p.matcher(cells.get(cols.getRooms()).text());
			matcher.find();
			apartment.setRooms(Double.valueOf(matcher.group().replaceAll(",", ".")));
		} else if (cols.getSize() > -1 && !cellIsSize(cells.get(cols.getSize()))) {
			if (!checkIsValidSize(cells.get(cols.getSize()).text())) {
				return false;
			}
			Pattern p = Pattern.compile("[\\d]+");
			Matcher matcher = p.matcher(cells.get(cols.getSize()).text());
			matcher.find();
			apartment.setSize(Integer.valueOf(matcher.group()));
		} else if (cols.getRent() > -1 && !cellIsRent(cells.get(cols.getRent()))) {
			if (!checkIsValidRent(cells.get(cols.getRent()).text())) {
				return false;
			}
			Pattern p = Pattern.compile("[\\d]+");
			Matcher matcher = p.matcher(cells.get(cols.getRent()).text());
			matcher.find();
			apartment.setRent(Integer.valueOf(matcher.group()));
		} else if (cols.getCity() > -1 && !cellIsCity(cells.get(cols.getCity()))) {
			if (!checkIsValidCityOrArea(cells.get(cols.getCity()).text())) {
				return false;
			}
			apartment.setCity(cells.get(cols.getCity()).text());
		} else if (cols.getArea() > -1 && !cellIsArea(cells.get(cols.getArea()))) {
			if (!checkIsValidCityOrArea(cells.get(cols.getArea()).text())) {
				return false;
			}
			apartment.setArea(cells.get(cols.getArea()).text());
		} else if (cols.getAddress() > -1 && !cellIsAddress(cells.get(cols.getAddress()))) {
			apartment.setAddress(cells.get(cols.getAddress()).text());
		} else {
			return false;
		}
		return true;
	}

	protected boolean checkIsValidCityOrArea(String text) {
		if(text == null || text.equals("")) {
			return false;
		}
		Pattern p = Pattern.compile("[\\d\\.,!?]+");
		Matcher matcher = p.matcher(text);
		if(text.equals("")) {
			return true;
		} else {
			return !matcher.find();
		}
	}

	protected boolean checkIsValidRent(String text) {
		if(text == null || text.equals("")) {
			return false;
		}
		text = text.toLowerCase();
		text = text.replaceAll("[-:\\dkrse\\., ]", "");
		Pattern p = Pattern.compile("[\\D]+");
		Matcher matcher = p.matcher(text);
		if(text.equals("")) {
			return true;
		} else {
			return !matcher.find();
		}
	}

	protected boolean checkIsValidSize(String text) {
		if(text == null || text.equals("")) {
			return false;
		}
		text = text.toLowerCase();
		text = text.replaceAll("[\\dkvm ]", "");
		Pattern p = Pattern.compile("[\\D]+");
		Matcher matcher = p.matcher(text);
		if(text.equals("")) {
			return true;
		} else {
			return !matcher.find();
		}
		
	}

	protected boolean checkIsValidRooms(String text) {
		if(text == null || text.equals("")) {
			return false;
		}
		text = text.toLowerCase();
		text = text.replaceAll("[\\d,\\. rok]", "");
		Pattern p = Pattern.compile("[\\D]+");
		Matcher matcher = p.matcher(text);
		if(text.equals("")) {
			return true;
		} else {
			return !matcher.find();
		}
	}

	private Columns resolveColumns(Elements cells, Columns cols) {
		for (Element cell : cells) {
			if (cols.getCity() == -1 && cellIsCity(cell)) {
				cols.setCity(cells.indexOf(cell));
			} else if (cols.getArea() == -1 && cellIsArea(cell)) {
				cols.setArea(cells.indexOf(cell));
			} else if (cols.getAddress() == -1 && cellIsAddress(cell)) {
				cols.setAddress(cells.indexOf(cell));
			} else if (cols.getRooms() == -1 && cellIsRooms(cell)) {
				cols.setRooms(cells.indexOf(cell));
			} else if (cols.getSize() == -1 && cellIsSize(cell)) {
				cols.setSize(cells.indexOf(cell));
			} else if (cols.getRent() == -1 && cellIsRent(cell)) {
				cols.setRent(cells.indexOf(cell));
			}
		}
		return cols;
	}

	private boolean cellIsCity(Element cell) {
		return cell.text().equalsIgnoreCase("ort");
	}

	private boolean cellIsArea(Element cell) {
		return cell.text().equalsIgnoreCase("område");
	}

	private boolean cellIsAddress(Element cell) {
		return cell.text().equalsIgnoreCase("adress");
	}

	private boolean cellIsRooms(Element cell) {
		return cell.text().equalsIgnoreCase("rum");
	}

	private boolean cellIsSize(Element cell) {
		return cell.text().equalsIgnoreCase("storlek");
	}

	private boolean cellIsRent(Element cell) {
		return cell.text().equalsIgnoreCase("hyra") || cell.text().equalsIgnoreCase("pris") || cell.text().equalsIgnoreCase("hyra");
	}

}
