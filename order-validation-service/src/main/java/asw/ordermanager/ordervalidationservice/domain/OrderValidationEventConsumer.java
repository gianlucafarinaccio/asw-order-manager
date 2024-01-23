package asw.ordermanager.ordervalidationservice.domain;
import asw.ordermanager.api.event.OrderCreatedEvent;
import asw.ordermanager.api.event.ProductCreatedEvent;
import asw.ordermanager.api.event.UpdateStockLevelEvent;
import asw.ordermanager.common.api.event.DomainEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.*;

@Service
public class OrderValidationEventConsumer {

    private final Logger logger = Logger.getLogger(this.getClass().toString());

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

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
        productService.updateProductStockLevel(evt.getName(), evt.getStockLevelVariation());
    }

    private void onProductCreated(ProductCreatedEvent evt) {
        productService.createProduct(evt.getName(), evt.getStockLevel());
    }

    private void onOrderCreated(OrderCreatedEvent evt) {
        orderService.createOrder(evt.getId(), evt.getCustomer(), null);
    }


}