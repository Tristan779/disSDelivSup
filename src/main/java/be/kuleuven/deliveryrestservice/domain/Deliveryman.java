package be.kuleuven.deliveryrestservice.domain;

import java.util.List;
import java.util.UUID;
import java.util.ArrayList;
import java.time.LocalTime;

public class Deliveryman {
    private UUID deliverymanId;
    private String name;
    private List<Itinerary> itineraries;
    private List<Itinerary> bookedTimes;
    private String phone;
    private double totalEarnings;

    // Constructors, getters, and setters

    public Deliveryman() {
        this.bookedTimes = new ArrayList<>();
    }


    public Deliveryman(String name, List<Itinerary> itineraries) {
        this.deliverymanId = UUID.randomUUID();
    }

    public Deliveryman(String name, String phone, List<Itinerary> itineraries) {
        this.deliverymanId = UUID.randomUUID();
        this.name = name;
        this.itineraries = itineraries;
        this.bookedTimes = new ArrayList<>();
        this.totalEarnings = 0.0;
    }

    public UUID getDeliverymanId() {
        return deliverymanId;
    }

    public void setDeliverymanId(UUID deliverymanId) {
        this.deliverymanId = deliverymanId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Itinerary> getItineraries() {
        return itineraries;
    }

    public void setItineraries(List<Itinerary> itineraries) {
        this.itineraries = itineraries;
    }

    public List<Itinerary> getBookedTimes() {
        return bookedTimes;
    }

    public void setBookedTimes(List<Itinerary> bookedTimes) {
        this.bookedTimes = bookedTimes;
    }

    public double getTotalEarnings() {
        return totalEarnings;
    }

    public void addEarnings(double earnings) {
        this.totalEarnings += earnings;
    }

    public boolean isAvailable(LocalTime startTime, LocalTime endTime) {
        return itineraries.stream()
                .filter(itinerary -> isWithinItinerary(itinerary, startTime, endTime))
                .anyMatch(itinerary -> isNotBooked(startTime, endTime));
    }

    private boolean isWithinItinerary(Itinerary itinerary, LocalTime startTime, LocalTime endTime) {
        return !startTime.isBefore(itinerary.getStartTime()) && !endTime.isAfter(itinerary.getEndTime());
    }

    private boolean isNotBooked(LocalTime startTime, LocalTime endTime) {
        return bookedTimes.stream()
                .noneMatch(booked -> booked.getStartTime().isBefore(endTime) && booked.getEndTime().isAfter(startTime));
    }

    public void bookTime(LocalTime startTime, LocalTime endTime) {
        bookedTimes.add(new Itinerary(startTime, endTime));
    }
}
