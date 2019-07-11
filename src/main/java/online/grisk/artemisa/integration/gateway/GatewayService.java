package online.grisk.hades.integration.gateway;

import org.springframework.integration.annotation.Gateway;
import org.springframework.messaging.Message;

import java.util.Map;

public interface GatewayService {
    @Gateway
    Map process(Message message);
}
