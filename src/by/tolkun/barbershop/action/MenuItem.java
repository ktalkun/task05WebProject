package by.tolkun.barbershop.action;

import java.io.Serializable;

public class MenuItem implements Serializable {
	private String url;
	private String name;
	private String icon;

	public MenuItem(final String inputUrl,
					final String inputName) {
		url = inputUrl;
		name = inputName;
	}

	public MenuItem(final String inputUrl,
					final String inputName,
					final String inputIcon) {
		url = inputUrl;
		name = inputName;
		icon = inputIcon;
	}

	public String getUrl() {
		return url;
	}

	public String getName() {
		return name;
	}

	public String getIcon() {
		return icon;
	}
}
