package com.example.inventariobp.service;

import com.example.inventariobp.model.StoreDTO;
import com.example.inventariobp.repository.IStoreRepository;
import com.example.inventariobp.service.interfaces.IStoreService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StoreService implements IStoreService {

    private final IStoreRepository storeRepository;

    @Override
    public Optional<StoreDTO> getStore(Long id) {
        return storeRepository.findById(id);
    }

    @Override
    public List<StoreDTO> getAllStores() {
        return storeRepository.findAll();
    }

    @Override
    public StoreDTO saveStore(StoreDTO dto) {
        return storeRepository.save(dto);
    }

    @Override
    public Long deleteStore(Long id) {
        storeRepository.deleteById(id);
        return id;
    }
}
