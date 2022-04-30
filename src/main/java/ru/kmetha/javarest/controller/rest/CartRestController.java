package ru.kmetha.javarest.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kmetha.javarest.entity.Cart;
import ru.kmetha.javarest.entity.Product;
import ru.kmetha.javarest.service.CartService;
import ru.kmetha.javarest.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartRestController {

    private final CartService cartService;
    private final ProductService productService;

    @GetMapping("")
    public List<Cart> getCartList() {
        return cartService.findAll();
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<?> getCart(@PathVariable("cartId") Long id) {
        Cart cart;
        if (id != null) {
            cart = cartService.findById(id);
            if (cart != null) {
                return new ResponseEntity<>(cart, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{cartId}/add/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String addProductInCart(@PathVariable("cartId") Long cartId, @PathVariable("productId") Long productId) {
        if (productId != null && cartId != null) {
            Cart cart = cartService.findById(cartId);
            cart.addProduct(productService.findById(productId));
            cartService.save(cart);
            return "Success";
        }
        return "Failed";
    }

    @DeleteMapping("/{cartId}/delete/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteById(@PathVariable("cartId") Long cartId, @PathVariable("productId") Long productId) {
        if (productId != null && cartId != null) {
            Cart cart = cartService.findById(cartId);
            cart.deleteProduct(productService.findById(productId));
            cartService.save(cart);
            return "Success";
        }
        return "Failed";
    }
}
