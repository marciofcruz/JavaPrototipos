package br.com.aps.biometria.controller;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class PainelNotificacao extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextArea textNotification;
	private JProgressBar loading;
	private JScrollPane scroll;
	private DateTimeFormatter timeFormatter;

	public PainelNotificacao() {
		loadComponents();
		loadLayouts();
		customizeComponents();
		addComponents();
	}

	private void loadComponents() {
		textNotification = new JTextArea();
		loading = new JProgressBar();
		scroll = new JScrollPane();
		timeFormatter = DateTimeFormat.forPattern("HH:mm:ss: ");
	}

	private void loadLayouts() {
		setLayout(new BorderLayout());
	}

	private void customizeComponents() {
		setBorder(BorderFactory.createTitledBorder("Notificações"));
		scroll.setPreferredSize(new Dimension(500, 0));
		loading.setPreferredSize(new Dimension(500, 15));
		textNotification.setEditable(false);
	}

	private void addComponents() {
		scroll.setViewportView(textNotification);
		add(scroll, BorderLayout.CENTER);
		add(loading, BorderLayout.SOUTH);
	}

	public void addText(String text) {
		int length = textNotification.getText().length();
		String time = timeFormatter.print(DateTime.now());
		textNotification.append(time + text + "\n");
		textNotification.setCaretPosition(length);
		update(getGraphics());
	}

	public void clear() {
		textNotification.setText("");
		loading.setValue(0);
	}

	public void setLoadingValue(int value) {
		loading.setValue(value);
	}

}
