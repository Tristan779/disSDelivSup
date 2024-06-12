package be.kuleuven.deliveryrestservice.domain;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class DeliverymanRepository {
    private final Map<UUID, Deliveryman> deliverymen = new HashMap<>();
    private final List<Delivery> deliveries = new ArrayList<>();

    @PostConstruct
    public void initData() {
        // Initialize with some deliverymen and itineraries for testing
        Deliveryman maxi = new Deliveryman("John Doe", "123-456-7890", Arrays.asList(
                new Itinerary(LocalTime.of(9, 0), LocalTime.of(23, 59))
        ));
        deliverymen.put(maxi.getDeliverymanId(), maxi);

        Deliveryman tough = new Deliveryman("Jane Smith", "234-567-8901", Arrays.asList(
                new Itinerary(LocalTime.of(8, 0), LocalTime.of(16, 0))
        ));
        deliverymen.put(tough.getDeliverymanId(), tough);

        Deliveryman luck = new Deliveryman("Robert Johnson", "345-678-9012", Arrays.asList(
                new Itinerary(LocalTime.of(7, 0), LocalTime.of(15, 0))
        ));
        deliverymen.put(luck.getDeliverymanId(), luck);

        Deliveryman tri = new Deliveryman("Emily Davis", "456-789-0123", Arrays.asList(
                new Itinerary(LocalTime.of(10, 0), LocalTime.of(18, 0))
        ));
        deliverymen.put(tri.getDeliverymanId(), tri);
    }

    public Collection<Deliveryman> getAllDeliverymen() {
        return deliverymen.values();
    }

    public Optional<Deliveryman> getDeliveryman(UUID id) {
        return Optional.ofNullable(deliverymen.get(id));
    }

    public List<Itinerary> getItineraries(UUID deliverymanId) {
        Deliveryman deliveryman = deliverymen.get(deliverymanId);
        if (deliveryman != null) {
            return deliveryman.getItineraries();
        }
        return Collections.emptyList();
    }

    public DeliveryConfirmation requestDeliveryman(Delivery delivery) {
        LocalTime now = LocalTime.now();
        LocalTime endTime = now.plusMinutes(10 * delivery.getRestaurantNames().size()).plusHours(2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH'h'mm");
        String formattedEndTime = endTime.format(formatter);
        for (Deliveryman deliveryman : deliverymen.values()) {
            if (deliveryman.isAvailable(now, endTime)) {
                // Book the timeslot
                deliveryman.bookTime(now, endTime);
                // Increase earnings
                deliveryman.addEarnings(15);
                // Add the delivery to deliveries list
                deliveries.add(delivery);
                // Create and return the DeliveryConfirmation
                return new DeliveryConfirmation("Success", deliveryman.getName(), deliveryman.getPhone(), formattedEndTime);
            }
        }
        // If no deliveryman is available, return a DeliveryConfirmation with status "Failed"
        return new DeliveryConfirmation("Failed", null, null, null);
    }

    private LocalTime calculateEarliestAvailableTime() {
        LocalTime now = LocalTime.now();
        LocalTime earliestAvailableTime = null;
        for (Deliveryman deliveryman : deliverymen.values()) {
            for (Itinerary itinerary : deliveryman.getItineraries()) {
                if (itinerary.getEndTime().isAfter(now) && (earliestAvailableTime == null || itinerary.getStartTime().isBefore(earliestAvailableTime))) {
                    earliestAvailableTime = itinerary.getStartTime();
                }
            }
        }
        return earliestAvailableTime;
    }

    public double getTotalEarnings(UUID deliverymanId) {
        Deliveryman deliveryman = deliverymen.get(deliverymanId);
        if (deliveryman != null) {
            return deliveryman.getTotalEarnings();
        }
        return 0.0;
    }
}
