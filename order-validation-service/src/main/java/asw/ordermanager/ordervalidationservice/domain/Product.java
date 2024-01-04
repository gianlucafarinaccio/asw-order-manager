package asw.ordermanager.ordervalidationservice.domain;

import jakarta.persistence.*;
import lombok.*;

/* Prodotto con inventario. */
@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product implements Comparable<Product> {

	@EqualsAndHashCode.Include
	@Id
	private String name;
	/* quantità disponibile */
	private int stockLevel;


	@Override
	public int compareTo(Product other) {
		return this.name.compareTo(other.name);
	}

}