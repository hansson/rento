package com.hansson.rento.apartments;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hansson.rento.entities.Apartment;

public abstract class ApartmentUtils {

	private static final int MAX_BACKOFF = 64;
	private static final Logger mLog = LoggerFactory.getLogger("rento");

	protected Document connect(String url) {
		boolean giveUp = false;
		int backoff = 1;
		while (!giveUp) {
			try {
				return Jsoup.connect(url).get();
			} catch (IOException e) {
				if (backoff < MAX_BACKOFF) {
					backoff *= 2;
					mLog.info("Failed to connect to: " + url);
					mLog.info("Doing backoff, " + backoff);
					try {
						Thread.sleep(backoff * 1000);
					} catch (InterruptedException e1) {
						// Should not occur
					}
				} else {
					giveUp = true;
					mLog.error(url + ", is unreachable!");
				}
			}
		}
		return null;
	}

	protected Document connect(String url, Map<String, String> postData, String userAgent) {
		boolean giveUp = false;
		int backoff = 1;
		while (!giveUp) {
			try {
				return Jsoup.connect(url).data(postData).userAgent(userAgent).header("Content-Type", "application/x-www-form-urlencoded; charset=utf-8").post();
			} catch (IOException e) {
				if (backoff < MAX_BACKOFF) {
					backoff *= 2;
					mLog.info("Failed to connect to: " + url);
					mLog.info("Doing backoff, " + backoff);
					try {
						Thread.sleep(backoff * 1000);
					} catch (InterruptedException e1) {
						// Should not occur
					}
				} else {
					giveUp = true;
					mLog.error(url + ", is unreachable!");
				}
			}
		}
		return null;
	}

	protected List<Apartment> getApartmentsMultiPage(Document doc, String baseUrl, String landlord) {
		List<Apartment> apartmentList = null;
		int methodCounter = 0;
		while (apartmentList == null) {
			switch (methodCounter) {
			case 0:
				if (doc.getElementsByTag("tr").size() > 0) {
					apartmentList = getAparmentInfoFromTable(doc, baseUrl, landlord);
				}
				break;
			default:
				apartmentList = new LinkedList<Apartment>();
				break;
			}
			methodCounter++;
		}

		return apartmentList;
	}

	private List<Apartment> getAparmentInfoFromTable(Document doc, String baseUrl, String landlord) {
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
					if (cols.getRooms() > -1 && !cellIsRooms(cells.get(cols.getRooms()))) {

					} else if (cols.getSize() > -1 && !cellIsSize(cells.get(cols.getSize()))) {

					} else if (cols.getPrice() > -1 && !cellIsPrice(cells.get(cols.getPrice()))) {

					} else if (cols.getCity() > -1 && !cellIsCity(cells.get(cols.getCity()))) {

					} else if (cols.getArea() > -1 && !cellIsArea(cells.get(cols.getArea()))) {

					} else if (cols.getAddress()> -1 && !cellIsAddress(cells.get(cols.getAddress()))) {

					}
					apartmentList.add(apartment);
				}
			}
		}

		return apartmentList;
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
			} else if (cols.getPrice() == -1 && cellIsPrice(cell)) {
				cols.setPrice(cells.indexOf(cell));
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

	private boolean cellIsPrice(Element cell) {
		return cell.text().equalsIgnoreCase("hyra") || cell.text().equalsIgnoreCase("pris") || cell.text().equalsIgnoreCase("hyra");
	}

}
