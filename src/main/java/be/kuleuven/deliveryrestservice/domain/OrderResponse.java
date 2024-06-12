package be.kuleuven.deliveryrestservice.domain;

import java.time.LocalTime;
import java.util.List;

public class OrderResponse {
    private String status;
    private String deliverymanName;
    private String deliverymanPhone;
    private LocalTime earliestAvailableTime;
    private String userName;
    private String street;
    private String number;
    private String city;
    private String zip;
    private String userPhoneNumber;
    private List<String> restaurantNames;

    // Constructors, getters, and setters

    public OrderResponse() {}

    public OrderResponse(String status, String deliverymanName, String deliverymanPhone, LocalTime earliestAvailableTime,
                         String userName, String street, String number, String city, String zip, String userPhoneNumber, List<String> restaurantNames) {
        this.status = status;
        this.deliverymanName = deliverymanName;
        this.deliverymanPhone = deliverymanPhone;
        this.earliestAvailableTime = earliestAvailableTime;
        this.userName = userName;
        this.street = street;
        this.number = number;
        this.city = city;
        this.zip = zip;
        this.userPhoneNumber = userPhoneNumber;
        this.restaurantNames = restaurantNames;
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

    public LocalTime getEarliestAvailableTime() {
        return earliestAvailableTime;
    }

    public void setEarliestAvailableTime(LocalTime earliestAvailableTime) {
        this.earliestAvailableTime = earliestAvailableTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public List<String> getRestaurantNames() {
        return restaurantNames;
    }

    public void setRestaurantNames(List<String> restaurantNames) {
        this.restaurantNames = restaurantNames;
    }
}
