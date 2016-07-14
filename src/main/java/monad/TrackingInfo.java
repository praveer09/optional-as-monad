package monad;

class TrackingInfo {
    private final String currentLocation;

    TrackingInfo(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    String getCurrentLocation() {
        return currentLocation;
    }
}
