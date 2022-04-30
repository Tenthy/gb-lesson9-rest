package ru.kmetha.javarest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.kmetha.javarest.dao.CartDao;
import ru.kmetha.javarest.entity.Cart;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {

    private final CartDao cartDao;

    public Cart save(Cart cart) {
        return cartDao.saveAndFlush(cart);
    }

    public List<Cart> findAll() {
        return cartDao.findAll();
    }

    public Cart findById(Long id) {
        return cartDao.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        cartDao.deleteById(id);
    }
}
