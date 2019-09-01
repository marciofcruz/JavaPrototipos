package br.com.unip.aps.ws.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.joda.time.DateTime;

public class DateTimeAdapter extends XmlAdapter<String, DateTime> {

	public DateTime unmarshal(String s) throws Exception {
		return new DateTime(s);
	}

	public String marshal(DateTime dt) throws Exception {
		return dt.toString();
	}

}