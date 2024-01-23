package asw.ordermanager.ordervalidationservice.domain;
import asw.ordermanager.api.event.OrderCreatedEvent;
import asw.ordermanager.api.event.ProductCreatedEvent;
import asw.ordermanager.api.event.UpdateStockLevelEvent;
import asw.ordermanager.common.api.event.DomainEvent;
import org.springframework.stereotype.Service;

import java.util.logging.*;

@Service
public class OrderValidationEventConsumer {

    private final Logger logger = Logger.getLogger(this.getClass().toString());

    public void onEvent(DomainEvent event) {
        if (event instanceof OrderCreatedEvent evt) {
            onOrderCreated(evt);
        } else if (event instanceof ProductCreatedEvent evt) {
            onProductCreated(evt);
        } else if (event instanceof UpdateStockLevelEvent evt) {
            onUpdateStockLevel(evt);
        } else logger.info("UNKNOWN EVENT: " + event);

    }

    private void onUpdateStockLevel(UpdateStockLevelEvent evt) {
        ProductService productService = new ProductService();
        productService.updateProductStockLevel(evt.getName(), evt.getStockLevelVariation());
    }

    private void onProductCreated(ProductCreatedEvent evt) {
        Product product = new Product(evt.getName(), evt.getStockLevel());
    }

    private void onOrderCreated(OrderCreatedEvent evt) {
        Order order = new Order(evt.getId(),evt.getCustomer(),null);
    }


}