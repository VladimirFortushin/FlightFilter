package com.gridnine.testing;

import com.gridnine.testing.FlightBuilder;

public class Main {
    /*
    filterDepartureIsNotBeforeCurrentTime()
    filterDepartureIsBeforeArrival()
    filterGroundTimeNotGreaterThanTwoHours()
     */
    public static void main(String[] args) {
        FlightFilter flightFilter = new FlightFilter();

        System.out.println(flightFilter.filterDepartureIsNotBeforeCurrentTime(FlightBuilder.createFlights()));
        System.out.println(flightFilter.filterDepartureIsBeforeArrival(FlightBuilder.createFlights()));
        System.out.println(flightFilter.filterGroundTimeNotGreaterThanTwoHours(FlightBuilder.createFlights()));

    }
}