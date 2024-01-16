package asw.ordermanager.ordervalidationservice.domain;

import jakarta.persistence.Entity;
import lombok.*;

/* Ordine. */
@Data
@Entity
@NoArgsConstructor @AllArgsConstructor
public class OrderItem {

    private String product;
    private int quantity;

}