package monad;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ImplementationTest {

    @Mock
    private OrderService orderService;

    @Mock
    private ShippingService shippingService;

    private Implementation implementation;

    @Before
    public void setUp() throws Exception {
        implementation = new Implementation(orderService, shippingService);
    }

    @Test
    public void returnsCurrentLocationInfoForOrderId() throws Exception {
        String orderId = "some-order";
        String trackingId = "some-tracking-id";
        String currentLocation = "some-current-location";

        when(orderService.findOrderByOrderId(orderId))
                .thenReturn(new Order(trackingId));
        when(shippingService.findTrackingInfoByTrackingId(trackingId))
                .thenReturn(new TrackingInfo(currentLocation));

        assertThat(implementation.monadicGetCurrentLocationOfOrderWithId(orderId))
                .isEqualTo(currentLocation);

        assertThat(implementation.imperativeGetCurrentLocationOfOrderWithId(orderId))
                .isEqualTo(currentLocation);
    }

    @Test
    public void returnsNoLocationFoundWhenOrderIsInvalid() throws Exception {
        String invalidOrderId = "some-invalid-order-id";
        String noLocationFound = "No location found";
        when(orderService.findOrderByOrderId(invalidOrderId)).thenReturn(null);

        assertThat(implementation.monadicGetCurrentLocationOfOrderWithId(invalidOrderId))
                .isEqualTo(noLocationFound);

        assertThat(implementation.imperativeGetCurrentLocationOfOrderWithId(invalidOrderId))
                .isEqualTo(noLocationFound);

    }

    @Test
    public void returnNoLocationFoundWhenOrderIdIsNull() throws Exception {
        String noLocationFound = "No location found";
        when(orderService.findOrderByOrderId(null)).thenReturn(null);

        assertThat(implementation.monadicGetCurrentLocationOfOrderWithId(null))
                .isEqualTo(noLocationFound);

        assertThat(implementation.imperativeGetCurrentLocationOfOrderWithId(null))
                .isEqualTo(noLocationFound);

    }
}