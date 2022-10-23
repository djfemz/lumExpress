package africa.semicolon.lumexpress.service;

import africa.semicolon.lumexpress.data.dto.request.CartRequest;
import africa.semicolon.lumexpress.data.dto.response.CartResponse;
import africa.semicolon.lumexpress.data.models.Cart;
import africa.semicolon.lumexpress.exception.CartNotFoundException;
import africa.semicolon.lumexpress.exception.ProductNotFoundException;

import java.util.List;

public interface CartService {
    CartResponse addProductToCart(CartRequest cartRequest) throws CartNotFoundException, ProductNotFoundException;
    List<Cart> getCartList();

    Cart save(Cart cart);
}
