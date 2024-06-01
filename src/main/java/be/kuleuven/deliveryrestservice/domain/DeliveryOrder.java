package be.kuleuven.deliveryrestservice.domain;

import java.util.UUID;

public class DeliveryOrder {
    private UUID orderId;
    private String address;
    private UUID deliverymanId;

    // Constructors, getters, and setters

    public DeliveryOrder() {
    }

    public DeliveryOrder(String address, UUID deliverymanId) {
        this.orderId = UUID.randomUUID();
        this.address = address;
        this.deliverymanId = deliverymanId;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UUID getDeliverymanId() {
        return deliverymanId;
    }

    public void setDeliverymanId(UUID deliverymanId) {
        this.deliverymanId = deliverymanId;
    }
}
