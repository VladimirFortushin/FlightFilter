package com.gridnine.testing;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

class FilterTest extends FlightBuilder {
    private final FlightFilter flightFilter = new FlightFilter();
    @Test
    void departureShouldNotBeBeforeCurrentTime() {
        List<Flight> filteredFlightList = flightFilter.filterDepartureIsNotBeforeCurrentTime(FlightBuilder.createFlights());

        for (Flight flight : filteredFlightList) {
            List<Segment> segments = flight.getSegments();
            for (Segment segment : segments) {
                if (segment.getDepartureDate().isBefore(LocalDateTime.now())) {
                    fail("The Departure is before current time in this segment: " + segment);
                }
            }
        }
    }

    @Test
    void departureShouldBeBeforeArrival() {
        List<Flight> filteredFlightList = flightFilter.filterDepartureIsBeforeArrival(FlightBuilder.createFlights());

        for (Flight flight : filteredFlightList) {
            List<Segment> segments = flight.getSegments();
            for (Segment segment : segments) {
                if (segment.getArrivalDate().isBefore(segment.getDepartureDate())) {
                    fail("The Arrival is before Departure in this segment: " + segment);
                }
            }
        }
    }

    @Test
    void groundTimeShouldNotBeGreaterThanTwoHours() {
        List<Flight> filteredFlightList = flightFilter.filterGroundTimeNotGreaterThanTwoHours(FlightBuilder.createFlights());

        for (Flight flight : filteredFlightList) {
            List<Segment> segments = flight.getSegments();
            for (int i = 0; i <= segments.size(); i++) {
                try {
                    if(segments.get(i).getArrivalDate().plusHours(2)
                            .isBefore(segments.get(i + 1).getDepartureDate())){
                        fail("The ground time in these segments is greater than 2 hours: "
                                + segments.get(i) + " and " + segments.get(i + 1));
                    }
                }catch (IndexOutOfBoundsException e){
                    break;
                }
            }
        }
    }


}