package com.twu.refactoring;

public class Movie {

	public enum MovieType{
		REGULAR(0, 2,2,1.5),NEW_RELEASE(1,0,0,3),CHILDRENS(2,1.5,3,1.5);

		private int priceCode;
		private double initiatePrice;
		private int initiateDays;
		private double perDayPrice;

		MovieType(int priceCode, double initiatePrice, int initiateDays, double perDayPrice){
			this.priceCode = priceCode;
			this.initiatePrice = initiatePrice;
			this.initiateDays = initiateDays;
			this.perDayPrice = perDayPrice;
		}

		int getPriceCode(){
			return priceCode;
		}

		double getInitiatePrice(){
			return initiatePrice;
		}

		int getInitiateDays(){
			return initiateDays;
		}

		double getPerDayPrice(){
			return perDayPrice;
		}
	}

	public static final int  CHILDRENS = 2;
	public static final int  REGULAR = 0;
	public static final int  NEW_RELEASE = 1;

	private String title;
	private int priceCode;

	public Movie(String title, int priceCode) {
		this.title = title;
		this.priceCode = priceCode;
	}

	public int getPriceCode() {
		return priceCode;
	}

	public void setPriceCode(int arg) {
    	priceCode = arg;
	}

	public String getTitle () {
		return title;
	}
}

