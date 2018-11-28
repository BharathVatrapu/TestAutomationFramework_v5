package com.testautomation.framework.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @Author Bharath
 * @Date 19-march-2018
 */
public class HtmlUtils {

	/*	To get the Link based on Text*/
	public String getLink(String html,String linkText) throws Exception
	{
		String href=null;
		if(html!=null) {
			Document doc = Jsoup.parse(html);
			Elements links = doc.select("a[href]");

			for (Element link_a : links) {
				if (link_a.text().toLowerCase().trim().contains(linkText.toLowerCase().trim())) {
					href = link_a.attr("href");
					break;
				}
			}
		}
		return href;
	}


	
	
}
