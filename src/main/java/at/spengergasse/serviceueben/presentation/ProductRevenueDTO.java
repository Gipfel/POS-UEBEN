package at.spengergasse.serviceueben.presentation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProductRevenueDTO extends RepresentationModel<ProductRevenueDTO> {
    private Long productId;
    private String productName;
    private double totalRevenue;

}