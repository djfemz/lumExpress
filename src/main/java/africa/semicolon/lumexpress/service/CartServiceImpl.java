package africa.semicolon.lumexpress.service;

import africa.semicolon.lumexpress.data.dto.request.CartRequest;
import africa.semicolon.lumexpress.data.dto.response.CartResponse;
import africa.semicolon.lumexpress.data.models.Cart;
import africa.semicolon.lumexpress.data.models.Item;
import africa.semicolon.lumexpress.data.models.Product;
import africa.semicolon.lumexpress.data.repositories.CartRepository;
import africa.semicolon.lumexpress.exception.CartNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService{
    private final CartRepository cartRepository;
    private final ProductService productService;
    @Override
    public CartResponse addProductToCart(CartRequest cartRequest) {
        Cart cart = cartRepository.findById(cartRequest.getCartId())
                .orElseThrow(()->new CartNotFoundException(
                        String.format("cart with id %d not found", cartRequest.getCartId())));
        Product foundProduct = productService
                .getProductById(cartRequest.getProductId());

        Item item = buildCartItem(foundProduct);
        cart.getItems().add(item);
        Cart cartToSave = updateCartSubTotal(cart);
        Cart savedCart = cartRepository.save(cartToSave);

        return CartResponse.builder()
                .message("item added to cart")
                .cart(savedCart)
                .build();
    }

    @Override
    public List<Cart> getCartList() {
        return cartRepository.findAll();
    }

    private Item buildCartItem(Product foundProduct) {
        return Item.builder()
                .product(foundProduct)
                .quantity(1)
                .build();
    }

    private Cart updateCartSubTotal(Cart cart){
        cart.getItems()
                .forEach(item -> sumCartItemPrices(cart, item));
        return cart;
    }

    private static void sumCartItemPrices(Cart cart, Item item) {
        var itemPrice = item.getProduct().getPrice();
        cart.setSubTotal(itemPrice.add(cart.getSubTotal()));
    }
}
