package be.kuleuven.deliveryrestservice.domain;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalTime;
import java.util.*;

@Component
public class DeliverymanRepository {
    private final Map<UUID, Deliveryman> deliverymen = new HashMap<>();
    private final List<DeliveryOrder> orders = new ArrayList<>();

    @PostConstruct
    public void initData() {
        // Initialize with some deliverymen and itineraries for testing
        Deliveryman maxi = new Deliveryman("Maxi Meme", Arrays.asList(
                new Itinerary(LocalTime.of(9, 0), LocalTime.of(17, 0))
        ));
        deliverymen.put(maxi.getDeliverymanId(), maxi);

        Deliveryman tough = new Deliveryman("Tough Mass", Arrays.asList(
                new Itinerary(LocalTime.of(8, 0), LocalTime.of(16, 0))
        ));
        deliverymen.put(tough.getDeliverymanId(), tough);

        Deliveryman luck = new Deliveryman("Luck Ass", Arrays.asList(
                new Itinerary(LocalTime.of(7, 0), LocalTime.of(15, 0))
        ));
        deliverymen.put(luck.getDeliverymanId(), luck);

        Deliveryman tri = new Deliveryman("Tri Tan", Arrays.asList(
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

    public String addOrder(DeliveryOrder order) {
        Deliveryman deliveryman = deliverymen.get(order.getDeliverymanId());
        if (deliveryman != null) {
            // Assume every delivery takes 45 minutes
            LocalTime now = LocalTime.now();
            LocalTime deliveryEndTime = now.plusMinutes(45);
            for (Itinerary itinerary : deliveryman.getItineraries()) {
                if (itinerary.getStartTime().isBefore(now) && itinerary.getEndTime().isAfter(deliveryEndTime)) {
                    if (deliveryman.isAvailable(now, deliveryEndTime)) {
                        orders.add(order);
                        deliveryman.bookTime(now, deliveryEndTime);
                        deliveryman.addEarnings(15.0);
                        return "Order placed successfully.";
                    } else {
                        return "Deliveryman is already booked during this time.";
                    }
                }
            }
            return "No available deliverymen.";
        }
        return "Invalid deliveryman ID.";
    }

    public double getTotalEarnings(UUID deliverymanId) {
        Deliveryman deliveryman = deliverymen.get(deliverymanId);
        if (deliveryman != null) {
            return deliveryman.getTotalEarnings();
        }
        return 0.0;
    }
}
