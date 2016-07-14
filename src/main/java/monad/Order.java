package monad;

class Order {
    private final String trackingId;

    Order(String trackingId) {
        this.trackingId = trackingId;
    }

    boolean hasTrackingId() {
        return trackingId != null;
    }

    String getTrackingId() {
        return trackingId;
    }
}
