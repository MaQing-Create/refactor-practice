package com.twu.refactoring;

public class Rental {

    private Movie movie;

    private int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public Movie getMovie() {
        return movie;
    }

    public double getPriceAmount(){
        double priceAmount = 0;
        for (Movie.MovieType movieType:Movie.MovieType.values()){
            if (movieType.getPriceCode() == movie.getPriceCode()){
                priceAmount += movieType.getInitiatePrice();
                if (daysRented > movieType.getInitiateDays())
                    priceAmount += (daysRented - movieType.getInitiateDays()) * movieType.getPerDayPrice();
            }
        }
        return priceAmount;
    }
}