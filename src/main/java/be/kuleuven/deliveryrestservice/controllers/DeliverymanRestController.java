package be.kuleuven.deliveryrestservice.controllers;

import be.kuleuven.deliveryrestservice.domain.*;
import be.kuleuven.deliveryrestservice.exceptions.DeliverymanNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class DeliverymanRestController {

    private final DeliverymanRepository deliverymanRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    DeliverymanRestController(DeliverymanRepository deliverymanRepository, ObjectMapper objectMapper) {
        this.deliverymanRepository = deliverymanRepository;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/rest/deliverymen")
    CollectionModel<EntityModel<Deliveryman>> getAllDeliverymen() {
        List<EntityModel<Deliveryman>> deliverymen = deliverymanRepository.getAllDeliverymen().stream()
                .map(deliveryman -> EntityModel.of(deliveryman,
                        linkTo(methodOn(DeliverymanRestController.class).getDeliveryman(deliveryman.getDeliverymanId())).withSelfRel(),
                        linkTo(methodOn(DeliverymanRestController.class).getAllDeliverymen()).withRel("deliverymen")))
                .collect(Collectors.toList());

        return CollectionModel.of(deliverymen,
                linkTo(methodOn(DeliverymanRestController.class).getAllDeliverymen()).withSelfRel());
    }

    @GetMapping("/rest/deliverymen/{id}")
    EntityModel<Deliveryman> getDeliveryman(@PathVariable UUID id) {
        Deliveryman deliveryman = deliverymanRepository.getDeliveryman(id).orElseThrow(() -> new DeliverymanNotFoundException(id));
        return EntityModel.of(deliveryman,
                linkTo(methodOn(DeliverymanRestController.class).getDeliveryman(id)).withSelfRel(),
                linkTo(methodOn(DeliverymanRestController.class).getAllDeliverymen()).withRel("deliverymen"));
    }

    @GetMapping("/rest/deliverymen/{id}/itineraries")
    CollectionModel<EntityModel<Itinerary>> getItineraries(@PathVariable UUID id) {
        List<EntityModel<Itinerary>> itineraries = deliverymanRepository.getItineraries(id).stream()
                .map(itinerary -> EntityModel.of(itinerary))
                .collect(Collectors.toList());

        return CollectionModel.of(itineraries,
                linkTo(methodOn(DeliverymanRestController.class).getItineraries(id)).withSelfRel());
    }

    @PostMapping("/rest/orders")
    public ResponseEntity<OrderResponse> placeOrder(
            @RequestParam String orderDetails,
            @RequestParam String userName,
            @RequestParam String street,
            @RequestParam String number,
            @RequestParam String city,
            @RequestParam String zip,
            @RequestParam String phoneNumber,
            @RequestParam String restaurantNames) {

        List<String> restaurantNameList;
        try {
            restaurantNameList = objectMapper.readValue(restaurantNames, new TypeReference<List<String>>() {});
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new OrderResponse("Invalid restaurant names format", null, null, null, userName, street, number, city, zip, phoneNumber, null));
        }

        DeliveryOrder order = new DeliveryOrder(UUID.randomUUID(), orderDetails, userName, street, number, city, zip, phoneNumber, restaurantNameList);
        OrderResponse response = deliverymanRepository.addOrder(order);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/rest/deliverymen/{id}/earnings")
    public ResponseEntity<Double> getTotalEarnings(@PathVariable UUID id) {
        double earnings = deliverymanRepository.getTotalEarnings(id);
        return ResponseEntity.ok(earnings);
    }
}
