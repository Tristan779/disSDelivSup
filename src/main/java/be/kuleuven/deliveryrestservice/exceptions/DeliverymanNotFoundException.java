package be.kuleuven.deliveryrestservice.exceptions;

import java.util.UUID;

public class DeliverymanNotFoundException extends RuntimeException {
    public DeliverymanNotFoundException(UUID id) {
        super("Could not find deliveryman " + id);
    }
}
