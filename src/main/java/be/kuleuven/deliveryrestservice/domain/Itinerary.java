package be.kuleuven.deliveryrestservice.domain;

import java.time.LocalTime;

public class Itinerary {
    private LocalTime startTime;
    private LocalTime endTime;

    // Constructors, getters, and setters

    public Itinerary(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
}
