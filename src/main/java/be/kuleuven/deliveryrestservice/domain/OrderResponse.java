package be.kuleuven.deliveryrestservice.domain;

import java.time.LocalTime;

public class OrderResponse {
    private String status;
    private String deliverymanName;
    private String deliverymanPhone;
    private LocalTime earliestAvailableTime;

    // Constructors, getters, and setters

    public OrderResponse() {}

    public OrderResponse(String status, String deliverymanName, String deliverymanPhone, LocalTime earliestAvailableTime) {
        this.status = status;
        this.deliverymanName = deliverymanName;
        this.deliverymanPhone = deliverymanPhone;
        this.earliestAvailableTime = earliestAvailableTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeliverymanName() {
        return deliverymanName;
    }

    public void setDeliverymanName(String deliverymanName) {
        this.deliverymanName = deliverymanName;
    }

    public String getDeliverymanPhone() {
        return deliverymanPhone;
    }

    public void setDeliverymanPhone(String deliverymanPhone) {
        this.deliverymanPhone = deliverymanPhone;
    }

    public LocalTime getEarliestAvailableTime() {
        return earliestAvailableTime;
    }

    public void setEarliestAvailableTime(LocalTime earliestAvailableTime) {
        this.earliestAvailableTime = earliestAvailableTime;
    }
}
