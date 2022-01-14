package com.example.inventariobp.service.interfaces;

import com.example.inventariobp.model.StoreDTO;
import com.example.inventariobp.model.vo.OrderVO;

import java.util.List;
import java.util.Optional;

public interface IStoreService {

    Optional<StoreDTO> getStore(Long id);

    List<StoreDTO> getAllStores();

    StoreDTO saveStore(StoreDTO dto);

    Long deleteStore(Long id);

    Boolean placeOrder(OrderVO data);
}
