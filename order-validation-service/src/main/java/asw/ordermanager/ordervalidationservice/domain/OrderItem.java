package asw.ordermanager.ordervalidationservice.domain;

import jakarta.persistence.Embeddable;
import lombok.*;

/* Ordine. */
@Embeddable
@Data
@NoArgsConstructor @AllArgsConstructor
public class OrderItem {

    private String product;
    private int quantity;

}