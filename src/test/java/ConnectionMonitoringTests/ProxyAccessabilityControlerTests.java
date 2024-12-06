package ConnectionMonitoringTests;

import ConnectionMonitoring.ProxyAccessabilityController;
import model.ProxyDetails;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class ProxyAccessabilityControlerTests {

    @Nested
    class ProxyAccessabilityControllerTest {

        @Test
        void testCheckConnection_ProxyIsAvailable() throws IOException {
            // Створення мок-об'єкта ProxyDetails
            ProxyDetails mockProxy = mock(ProxyDetails.class);
            when(mockProxy.getIp()).thenReturn("192.168.0.1");
            when(mockProxy.getPort()).thenReturn("8001");

            // Мокування методу isAvaible, щоб він повертав true (доступний)
            ProxyAccessabilityController controller = spy(new ProxyAccessabilityController());
            doReturn(true).when(controller).isAvaible(mockProxy);

            controller.checkConnection(mockProxy);

            verify(controller, never()).updateIfNotAvaible(mockProxy);
        }

        @Test
        void testCheckConnection_ProxyIsNotAvailable() throws IOException {
            ProxyDetails mockProxy = mock(ProxyDetails.class);
            when(mockProxy.getIp()).thenReturn("192.168.0.1");
            when(mockProxy.getPort()).thenReturn("9002");

            ProxyAccessabilityController controller = spy(new ProxyAccessabilityController());
            doReturn(false).when(controller).isAvaible(mockProxy);

            doNothing().when(controller).updateIfNotAvaible(mockProxy);

            controller.checkConnection(mockProxy);

            verify(controller, times(1)).updateIfNotAvaible(mockProxy);
        }

    }
}
