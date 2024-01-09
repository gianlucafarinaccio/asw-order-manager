package asw.ordermanager.ordervalidationservice.orderserviceclient;

import asw.ordermanager.ordervalidationservice.domain.*;


import asw.ordermanager.orderservice.api.rest.*;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.*;

import java.util.logging.Logger;

@Service
public class OrderServiceRestClientAdapter implements OrderServiceClientPort {

	@Autowired
	@Qualifier("loadBalancedWebClient")
	private WebClient loadBalancedWebClient;

	private final Logger logger = Logger.getLogger(this.getClass().toString());

	public Order getOrder(Long id) {
		GetOrderResponse or = null;
		Mono<GetOrderResponse> response = loadBalancedWebClient
				.get()
				.uri("http://orderservice/orders/{id}", id)
				.retrieve()
				.bodyToMono(GetOrderResponse.class);
		try {
			or = response.block();
		} catch (WebClientException e) {
			logger.info("GETORDER " + id + ": " + e.getMessage());
		}
		return toOrder(or);
	}

	private Order toOrder(GetOrderResponse or) {
		if (or==null) {
			return null;
		}
		List<OrderItemElement> orderItemElements = or.getOrderItems();
		List<Product> productOrderList= new LinkedList<Product>();

		for (OrderItemElement orderItem : orderItemElements){
			String productName = orderItem.getProduct();
			int productOrderQuantity = orderItem.getQuantity();
			Product product = new Product(productName,productOrderQuantity);
			productOrderList.add(product);
		}
		return new Order(
				or.getId(),
				or.getCustomer(),
				toOrderItems(productOrderList));
	}

	/* Converte un OrderItemElement in un OrderItem. */
	private Product toOrderItem(Product productOrder) {
		return new Product(
				productOrder.getName(),
				productOrder.getStockLevel());
	}

	/* Converte una collezione di OrderItemElement in una collezione di OrderItem. */
	private List<Product> toOrderItems(List<Product> productOrderList ) {
		List<Product> products =
				productOrderList
						.stream()
						.map(product -> toOrderItem(product))
						.collect(Collectors.toList());
		return products;
	}

}