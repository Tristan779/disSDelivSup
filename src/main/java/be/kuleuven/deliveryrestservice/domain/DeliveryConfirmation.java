package be.kuleuven.deliveryrestservice.domain;

import java.time.LocalTime;
import java.util.List;

public class DeliveryConfirmation {
    private String status;
    private String deliverymanName;
    private String deliverymanPhone;
    private String earliestAvailableTime;

    // Constructors, getters, and setters

    public DeliveryConfirmation() {}

    public DeliveryConfirmation(String status, String deliverymanName, String deliverymanPhone, String earliestAvailableTime) {
        this.status = status;
        this.deliverymanName = deliverymanName;
        this.deliverymanPhone = deliverymanPhone;
        this.earliestAvailableTime = earliestAvailableTime;
    }

    // Getters and setters
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

    public String getEarliestAvailableTime() {
        return earliestAvailableTime;
    }

    public void setEarliestAvailableTime(String earliestAvailableTime) {
        this.earliestAvailableTime = earliestAvailableTime;
    }
}
