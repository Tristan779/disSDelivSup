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
        Deliveryman maxi = new Deliveryman("Maxi Meme", "123-456-7890", Arrays.asList(
                new Itinerary(LocalTime.of(9, 0), LocalTime.of(23, 59))
        ));
        deliverymen.put(maxi.getId(), maxi);

        Deliveryman tough = new Deliveryman("Tough Mass", "234-567-8901", Arrays.asList(
                new Itinerary(LocalTime.of(8, 0), LocalTime.of(16, 0))
        ));
        deliverymen.put(tough.getId(), tough);

        Deliveryman luck = new Deliveryman("Luck Ass", "345-678-9012", Arrays.asList(
                new Itinerary(LocalTime.of(7, 0), LocalTime.of(15, 0))
        ));
        deliverymen.put(luck.getId(), luck);

        Deliveryman tri = new Deliveryman("Tri Tan", "456-789-0123", Arrays.asList(
                new Itinerary(LocalTime.of(10, 0), LocalTime.of(18, 0))
        ));
        deliverymen.put(tri.getId(), tri);
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

    public OrderResponse addOrder(DeliveryOrder order) {
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
                        return new OrderResponse("success", deliveryman.getName(), deliveryman.getPhone(), null);
                    }
                }
            }
            LocalTime earliestAvailableTime = calculateEarliestAvailableTime(deliveryman);
            return new OrderResponse("fail", null, null, earliestAvailableTime);
        }
        return new OrderResponse("invalid deliveryman ID", null, null, null);
    }

    private LocalTime calculateEarliestAvailableTime(Deliveryman deliveryman) {
        LocalTime now = LocalTime.now();
        for (Itinerary itinerary : deliveryman.getItineraries()) {
            if (itinerary.getEndTime().isAfter(now)) {
                return itinerary.getEndTime();
            }
        }
        return null;
    }

    public double getTotalEarnings(UUID deliverymanId) {
        Deliveryman deliveryman = deliverymen.get(deliverymanId);
        if (deliveryman != null) {
            return deliveryman.getTotalEarnings();
        }
        return 0.0;
    }
}
