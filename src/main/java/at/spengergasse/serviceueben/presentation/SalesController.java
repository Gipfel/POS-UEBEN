package at.spengergasse.serviceueben.presentation;

import at.spengergasse.serviceueben.service.SalesService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class SalesController {
    private final SalesService salesService;

    @GetMapping("/top-products")
    public ResponseEntity<List<ProductRevenueDTO>> getTopProducts(
            @RequestParam(defaultValue = "5") int limit
    ) {
        return ResponseEntity.ok(
                salesService.getTopSellingProducts(limit).stream()
                        .map(this::addLinks)
                        .collect(Collectors.toList())
        );
    }

    private ProductRevenueDTO addLinks(ProductRevenueDTO dto) {
        dto.add(
                linkTo(methodOn(ProductController.class).getProduct(dto.getProductId()))
                        .withRel("product")
        );
        return dto;
    }
}