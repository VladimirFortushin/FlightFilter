package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class FlightFilter extends FlightBuilder {

    public List<Flight> filterDepartureIsNotBeforeCurrentTime (List<Flight> flights){
        List<Flight> filteredListOfFlights = new ArrayList<>();
        for(Flight flight : flights){
            List<Segment> segments = flight.getSegments().stream()
                    .filter(segment -> segment.getDepartureDate().isAfter(LocalDateTime.now()))
                    .collect(Collectors.toList());

            if(!segments.isEmpty()){filteredListOfFlights.add(new Flight(segments));}
        }
        return filteredListOfFlights;
    }

    public List<Flight> filterDepartureIsBeforeArrival (List<Flight> flights){
        List<Flight> filteredListOfFlights = new ArrayList<>();
        for(Flight flight : flights){
            List<Segment> segments = flight.getSegments().stream()
                    .filter(segment -> segment.getDepartureDate().isBefore(segment.getArrivalDate()))
                    .collect(Collectors.toList());

            if(!segments.isEmpty()){filteredListOfFlights.add(new Flight(segments));}
        }
        return filteredListOfFlights;
    }

    public List<Flight> filterGroundTimeNotGreaterThanTwoHours (List<Flight> flights){
        Set<Flight> filteredSetOfFlights = new LinkedHashSet<>();
        for(Flight flight : flights){
            List<Segment> segments = flight.getSegments();
            for (int i = 0; i <= segments.size(); i++) {
                try{
                    if(!(segments.get(i + 1).getDepartureDate()
                            .isAfter(segments.get(i).getArrivalDate().plusHours(2)))){
                        filteredSetOfFlights.add(flight);
                    }
                }catch (IndexOutOfBoundsException e){
                    if(segments.size() == 1){filteredSetOfFlights.add(flight);}
                    break;
                }
            }
        }
        return new ArrayList<>(filteredSetOfFlights);
    }






}
