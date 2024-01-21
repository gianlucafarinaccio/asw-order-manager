package asw.ordermanager.ordervalidationservice.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

@Service
public class OrderService {

	private final Logger logger = Logger.getLogger(this.getClass().toString());

	@Autowired
	private OrderRepository orderRepository;


 	public Order createOrder(String customer, String address, List<OrderItem> orderItems, double total) {
		Order order = new Order(customer, orderItems);
		order = orderRepository.save(order);
        logger.info("Ordine replicato correttamente!");
		return order;
	}

 	public Order getOrder(Long id) {
		Order order = orderRepository.findById(id).orElse(null);
		return order;
	}
	
}