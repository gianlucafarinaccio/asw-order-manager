package asw.ordermanager.ordervalidationservice.domain;

import java.util.*;

import jakarta.persistence.*;

import lombok.*;

/* Ordine. */
@Entity
@Table(name="VALID_ORDERS")
@Data
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Order implements Comparable<Order> {

	@Id
	@EqualsAndHashCode.Include
	private Long id;

	private String customer;

	@OneToMany
	private List<OrderItem> orderItems;


	@Override
	public int compareTo(Order other) {
		return this.id.compareTo(other.id);
	}

}
