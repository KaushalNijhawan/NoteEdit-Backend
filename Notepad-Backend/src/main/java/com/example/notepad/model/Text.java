package com.example.notepad.model;

public class Text {
	private String textContent;
	private String textURL;
	private String password;
	private boolean isloggedIn;

	

	public Text(String textContent, String textURL, String password, boolean isloggedIn) {
		super();
		this.textContent = textContent;
		this.textURL = textURL;
		this.password = password;
		this.isloggedIn = isloggedIn;
	}

	public boolean isIsloggedIn() {
		return isloggedIn;
	}

	public void setIsloggedIn(boolean isloggedIn) {
		this.isloggedIn = isloggedIn;
	}

	public String getTextContent() {
		return textContent;
	}

	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}

	public String getTextURL() {
		return textURL;
	}

	public void setTextURL(String textURL) {
		this.textURL = textURL;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Text [textContent=" + textContent + ", textURL=" + textURL + ", password=" + password + ", isloggedIn="
				+ isloggedIn + "]";
	}

}
