package africa.semicolon.lumexpress.data.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartRequest {
    private Long productId;
    private Long cartId;
}
