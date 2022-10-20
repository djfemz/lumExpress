package africa.semicolon.lumexpress.service;

import africa.semicolon.lumexpress.data.dto.request.CartRequest;
import africa.semicolon.lumexpress.data.dto.response.CartResponse;
import africa.semicolon.lumexpress.data.models.Cart;

import java.util.List;

public interface CartService {
    CartResponse addProductToCart(CartRequest cartRequest);
    List<Cart> getCartList();
}
