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
    private double totalEarnings;

    // Constructors, getters, and setters

    public Deliveryman() {
        this.bookedTimes = new ArrayList<>();
    }

    public Deliveryman(String name, List<Itinerary> itineraries) {
        this.deliverymanId = UUID.randomUUID();
        this.name = name;
        this.itineraries = itineraries;
        this.bookedTimes = new ArrayList<>();
        this.totalEarnings = 0.0;
    }

    public UUID getDeliverymanId() {
        return deliverymanId;
    }

    public void setDeliverymanId(UUID id) {
        this.deliverymanId = id;
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
        for (Itinerary booked : bookedTimes) {
            if (booked.getStartTime().isBefore(endTime) && booked.getEndTime().isAfter(startTime)) {
                return false; // Overlapping booking
            }
        }
        return true;
    }

    public void bookTime(LocalTime startTime, LocalTime endTime) {
        bookedTimes.add(new Itinerary(startTime, endTime));
    }
}
