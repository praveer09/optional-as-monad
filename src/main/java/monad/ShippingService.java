package monad;

interface ShippingService {
    TrackingInfo findTrackingInfoByTrackingId(String trackingId);
}
