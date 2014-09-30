package com.hansson.rento.utils;

public class HtmlUtil {

	public static String htmlToText(String html) {
		if (html != null) {
			html = html.replace("&aring;", "\u00e5");
			html = html.replace("&auml;", "\u00e4");
			html = html.replace("&ouml;", "\u00f6");
			html = html.replace("&Aring;", "\u00c5");
			html = html.replace("&Auml;", "\u00c4");
			html = html.replace("&Ouml;", "\u00d6");
		}
		return html;
	}

	public static String textToHtml(String html) {
		if (html != null) {
			html = html.replace("\u00e5", "&aring;");
			html = html.replace("\u00e4", "&auml;");
			html = html.replace("\u00f6", "&ouml;");
			html = html.replace("\u00c5", "&Aring;");
			html = html.replace("\u00c4", "&Auml;"); 
			html = html.replace("\u00d6", "&Ouml;");
		}
		return html;
	}

}
 