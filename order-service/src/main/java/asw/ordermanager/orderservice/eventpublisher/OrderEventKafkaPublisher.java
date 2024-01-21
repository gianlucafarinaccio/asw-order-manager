package asw.ordermanager.orderservice.eventpublisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import asw.ordermanager.orderservice.domain.OrderEventPublisher;
import asw.ordermanager.api.event.*;
import asw.ordermanager.common.api.event.DomainEvent;
import java.util.logging.Logger;
import org.springframework.kafka.core.KafkaTemplate;

@Component
public class OrderEventKafkaPublisher implements OrderEventPublisher{

    private final Logger logger = Logger.getLogger(this.getClass().toString());

    @Autowired
    private KafkaTemplate<String, DomainEvent> template;

    private String channel = OrderServiceEventChannel.orderCreated;

    @Override
    public void publish(DomainEvent event) {
        logger.info("EVENT PUBLISHER: " + event.toString() + " ON CHANNEL: " + channel);
        template.send(channel, event);
    }

}
