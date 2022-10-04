package africa.semicolon.lumexpress.data.dto.request;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UpdateProductRequest {
    private Long productId;
    private BigDecimal price;
    private int quantity;
    private String description;

}
