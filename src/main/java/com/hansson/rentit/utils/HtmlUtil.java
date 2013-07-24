package com.hansson.rentit.utils;

public class HtmlUtil {

	public static String htmlToText(String html) {
		html = html.replace("&aring;", "å");
		html = html.replace("&auml;", "ä");
		html = html.replace("&ouml;", "ö");
		html = html.replace("&Aring;", "Å");
		html = html.replace("&Auml;", "Ä");
		html = html.replace("&Ouml;", "Ö");
		return html;
	}
}
