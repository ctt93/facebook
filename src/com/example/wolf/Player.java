package com.example.wolf;

public class Player {
	private String name;
	private int imageId;
	public Player(String name,int imageId){
		this.name=name;
		this.imageId=imageId;
	}
	public String getName() {
		return name;
	}

	public int getImageId() {
		return imageId;
	}
}
