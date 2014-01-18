package com.hansson.rento.apartments;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
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

	protected List<Apartment> getApartmentsMultiPage(Document doc, String baseUrl, String landlord, Method method) {
		List<Apartment> apartmentList = null;
		switch (method) {
		case TABULAR:
			if (doc.getElementsByTag("tr").size() > 0) {
				apartmentList = new ApartmentInfoFromTable().handle(doc, baseUrl, landlord);
			}
			break;
		default:
			apartmentList = new LinkedList<Apartment>();
			break;
		}
		return apartmentList;
	}
}
