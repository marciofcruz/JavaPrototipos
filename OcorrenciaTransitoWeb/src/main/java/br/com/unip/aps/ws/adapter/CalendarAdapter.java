package br.com.unip.aps.ws.adapter;

import java.util.Calendar;

import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class CalendarAdapter extends XmlAdapter<String, Calendar> {

	@Override
	public String marshal(Calendar c) throws Exception {
		return DatatypeConverter.printDate(c);
	}

	@Override
	public Calendar unmarshal(String s) throws Exception {
		return DatatypeConverter.parseDate(s);
	}

}