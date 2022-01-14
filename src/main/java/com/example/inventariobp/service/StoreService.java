package com.example.inventariobp.service;

import com.example.inventariobp.model.CustomerDTO;
import com.example.inventariobp.model.ProductDTO;
import com.example.inventariobp.model.StoreDTO;
import com.example.inventariobp.model.vo.OrderVO;
import com.example.inventariobp.repository.ICustomerRepository;
import com.example.inventariobp.repository.IProductRepository;
import com.example.inventariobp.repository.IStoreRepository;
import com.example.inventariobp.service.interfaces.IStoreService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StoreService implements IStoreService {

    private final IStoreRepository storeRepository;
    private final ICustomerRepository customerRepository;
    private final IProductRepository productRepository;
    private final EntityManager entityManager;

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

    @Override
    public Boolean placeOrder(OrderVO data) {
        Optional<CustomerDTO> customer = customerRepository.findById(data.getCustomerId());
        if (!customer.isPresent())
            throw new IllegalStateException(String.format("Client with id %d does not exist", data.getCustomerId()));

        data.getDetail().forEach(element -> {
            Optional<StoreDTO> store = storeRepository.findById(element.getStoreId());
            if (!store.isPresent())
                throw new IllegalStateException(String.format("Store with id %d does not exist", element.getStoreId()));

            element.getDetail().forEach(e -> {
                Optional<ProductDTO> product = productRepository.findById(e.getProductId());
                if (!product.isPresent())
                    throw new IllegalStateException(String.format("Product with id %d does not exist", e.getProductId()));

                if (e.getQuantity() == null || Math.abs(e.getQuantity()) < Double.MIN_NORMAL)
                    throw new IllegalStateException("The quantity of the product cannot be less than one unit");
            });
        });

        //TODO restar stock y escribir transaccion
        return Boolean.TRUE;
    }

    public List<ProductDTO> getAllProductsByStore(Long stroreId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT PRO.ID , PRO.COD , PRO.NAME, PRO.PRICE, PRO.STOCK ")
                .append("FROM PRODUCT PRO ")
                .append("INNER JOIN STORE_PRODUCT STP ")
                .append("ON  STP.PRODUCT_ID = PRO.ID ")
                .append("AND STP.STORE_ID = :stroreId");

        Query query = entityManager.createNativeQuery(sql.toString());
        query.setParameter("stroreId", stroreId);

        List<Object[]> results = query.getResultList();
        return results
                .stream()
                .map(result -> new ProductDTO((Long) result[0], (String) result[1], (String) result[2], (Double) result[3], (Double) result[4]))
                .collect(Collectors.toList());
    }
}