package monad;

import java.util.Optional;

class Implementation {

    private final OrderService orderService;
    private final ShippingService shippingService;

    Implementation(OrderService orderService, ShippingService shippingService) {
        this.orderService = orderService;
        this.shippingService = shippingService;
    }

    String monadicGetCurrentLocationOfOrderWithId(String orderId) {
        return Optional.ofNullable(orderId)
                .map(orderService::findOrderByOrderId)
                .filter(Order::hasTrackingId)
                .map(Order::getTrackingId)
                .map(shippingService::findTrackingInfoByTrackingId)
                .map(TrackingInfo::getCurrentLocation)
                .orElse("No location found");
    }

    String imperativeGetCurrentLocationOfOrderWithId(String orderId) {
        if (orderId != null) {
            Order order = orderService.findOrderByOrderId(orderId);

            if (order != null && order.hasTrackingId()) {
                TrackingInfo trackingInfo =
                        shippingService.findTrackingInfoByTrackingId(order.getTrackingId());

                if (trackingInfo != null) {
                    return trackingInfo.getCurrentLocation();
                }
            }
        }

        return "No location found";
    }
}
