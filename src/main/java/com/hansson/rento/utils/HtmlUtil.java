package com.hansson.rento.utils;

public class HtmlUtil {

	public static String htmlToText(String html) {
		if (html != null) {
			html = html.replace("&aring;", "å");
			html = html.replace("&auml;", "ä");
			html = html.replace("&ouml;", "ö");
			html = html.replace("&Aring;", "Å");
			html = html.replace("&Auml;", "Ä");
			html = html.replace("&Ouml;", "Ö");
		}
		return html;
	}

	public static String textToHtml(String html) {
		if (html != null) {
			html = html.replace("å", "&aring;");
			html = html.replace("ä", "&auml;");
			html = html.replace("ö", "&ouml;");
			html = html.replace("Å", "&Aring;");
			html = html.replace("Ä", "&Auml;");
			html = html.replace("Ö", "&Ouml;");
		}
		return html;
	}

}
