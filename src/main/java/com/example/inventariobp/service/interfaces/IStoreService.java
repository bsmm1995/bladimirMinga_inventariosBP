package com.example.inventariobp.service.interfaces;

import com.example.inventariobp.model.Store;
import com.example.inventariobp.model.dto.OrderDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IStoreService {

    Optional<Store> getStore(Long id);

    List<Store> getAllStores();

    Store saveStore(Store dto);

    Long deleteStore(Long id);

    Boolean placeOrder(OrderDTO data);

    Boolean productExistsInTheStore(Long storeId, Long productId);
}
