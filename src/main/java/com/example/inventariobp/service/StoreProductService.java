package com.example.inventariobp.service;

import com.example.inventariobp.model.ProductDTO;
import com.example.inventariobp.model.StoreProductDTO;
import com.example.inventariobp.repository.IStoreProductRepository;
import com.example.inventariobp.service.interfaces.IStoreProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StoreProductService implements IStoreProductService {

    private final IStoreProductRepository storeProductRepository;
    private final EntityManager entityManager;

    @Override
    public Optional<StoreProductDTO> getStoreProduct(Long id) {
        return storeProductRepository.findById(id);
    }

    @Override
    public List<ProductDTO> getAllProductsByStore(Long storeID) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT PRO.ID , PRO.COD , PRO.NAME, PRO.PRICE, PRO.STOCK ")
                .append("FROM PRODUCT PRO ")
                .append("INNER JOIN STORE_PRODUCT STP ")
                .append("ON  STP.PRODUCT_ID = PRO.ID ")
                .append("AND STP.STORE_ID = :storeId");

        Query query = entityManager.createNativeQuery(sql.toString());
        query.setParameter("storeId", storeID);

        List<Object[]> results = query.getResultList();
        return results
                .stream()
                .map(result -> new ProductDTO(((BigInteger) result[0]).longValue(), (String) result[1], (String) result[2], (Double) result[3], (Double) result[4]))
                .collect(Collectors.toList());
    }

    @Override
    public StoreProductDTO saveStoreProduct(StoreProductDTO dto) {
        return storeProductRepository.save(dto);
    }

    @Override
    public Long deleteStoreProduct(Long id) {
        storeProductRepository.deleteById(id);
        return id;
    }
}
