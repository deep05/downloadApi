package com.download.download.combo;

public class ResponseFile {

	String name;
	String contents;

	public ResponseFile withName(String name) {
		this.setName(name);
		return this;
	}

	public ResponseFile withContents(String contents) {
		this.setContents(contents);
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

}
