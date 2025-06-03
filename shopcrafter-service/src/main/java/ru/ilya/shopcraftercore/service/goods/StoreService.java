package ru.ilya.shopcraftercore.service.goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import ru.ilya.shopcraftercore.dto.goods.store.StoreDto;
import ru.ilya.shopcraftercore.dto.goods.store.UpdateStoreDto;
import org.springframework.stereotype.Service;
import ru.ilya.shopcraftercore.entity.auth.User;
import ru.ilya.shopcraftercore.entity.goods.Category;
import ru.ilya.shopcraftercore.entity.goods.Store;
import ru.ilya.shopcraftercore.entity.goods.Product;
import ru.ilya.shopcraftercore.entity.order.OrderItem;
import ru.ilya.shopcraftercore.exception.EntityNotFoundException;
import ru.ilya.shopcraftercore.exception.ForbiddenException;
import ru.ilya.shopcraftercore.repository.auth.UserRepository;
import ru.ilya.shopcraftercore.repository.goods.CategoryRepository;
import ru.ilya.shopcraftercore.repository.goods.StoreRepository;
import ru.ilya.shopcraftercore.repository.goods.ProductRepository;
import ru.ilya.shopcraftercore.repository.order.OrderItemRepository;

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;

@Service
public class StoreService {
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public StoreService(
            StoreRepository storeRepository,
            UserRepository userRepository,
            CategoryRepository categoryRepository,
            ProductRepository productRepository,
            OrderItemRepository orderItemRepository
    ) {
        this.storeRepository = storeRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public StoreDto getStoreByName(String name) {
        Store store = storeRepository.findByDescription(name).orElseThrow( () -> new EntityNotFoundException("Store not found"));
        return StoreDto.fromEntity(store);
    }

    public StoreDto getStoreById(long id) {
        Store store = storeRepository.findById(id).orElse(null);
        if (store == null) {
            return null;
        }
        return StoreDto.fromEntity(store);
    }
    public List<StoreDto> getAllStores() {
        List<Store> stores = (List<Store>) storeRepository.findAll();
        return stores.stream().map(StoreDto::fromEntity).toList();
    }

    @Transactional
    public StoreDto createStore(UserDetails userDetails, UpdateStoreDto storeDto) {
        Store store = new Store();
        store.setName(storeDto.getName());
        store.setDescription(storeDto.getDescription());
        User owner = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(()
                -> new EntityNotFoundException("owner not found"));
        store.setOwner(owner);
        storeRepository.save(store);
        owner.getStores().add(store);
        userRepository.save(owner);
        return StoreDto.fromEntity(store);
    }

    @Transactional
    public StoreDto updateStore(UserDetails userDetails, long id, UpdateStoreDto storeDto) {
        Store store = storeRepository.findById(id).orElse(null);
        if (store == null) {
            return null;
        }
        User owner = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(()
                -> new EntityNotFoundException("owner not found"));
        if (!Objects.equals(store.getOwner().getId(), owner.getId())) {
            throw new ForbiddenException("you don't have access to this store");
        }
        store.setName(storeDto.getName());
        store.setDescription(storeDto.getDescription());
        storeRepository.save(store);
        return StoreDto.fromEntity(store);
    }

    @Transactional
    public StoreDto updateStoreImage(UserDetails userDetails, long id, String imageUrl) {
        Store store = storeRepository.findById(id).orElse(null);
        if (store == null) {
            throw new EntityNotFoundException("store not found");
        }
        User owner = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(()
                -> new EntityNotFoundException("owner not found"));
        if (!Objects.equals(store.getOwner().getId(), owner.getId())) {
            throw new ForbiddenException("you don't have access to this store");
        }
        store.setImageUrl(imageUrl);
        storeRepository.save(store);
        return StoreDto.fromEntity(store);
    }

    @Transactional
    public void deleteStore(UserDetails userDetails, long id) {
        Store store = storeRepository.findById(id).orElse(null);
        if (store == null) {
            throw new EntityNotFoundException("store not found");
        }
        User owner = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(()
                -> new EntityNotFoundException("owner not found"));
        if (!Objects.equals(store.getOwner().getId(), owner.getId())) {
            throw new ForbiddenException("you don't have access to this store");
        }

        owner.getStores().remove(store);
        userRepository.save(owner);

        if (store.getWorkers() != null) {
            store.getWorkers().clear();
        }

        if (store.getCategories() != null) {
            List<Category> categories = new ArrayList<>(store.getCategories());
            for (Category category : categories) {
                if (category.getProducts() != null) {
                    List<Product> products = new ArrayList<>(category.getProducts());
                    for (Product product : products) {
                        List<OrderItem> orderItems = orderItemRepository.findByProductId(product.getId());
                        for (OrderItem orderItem : orderItems) {
                            orderItemRepository.delete(orderItem);
                        }
                        productRepository.delete(product);
                    }
                }
                categoryRepository.delete(category);
            }
            store.getCategories().clear();
        }

        storeRepository.save(store);
        storeRepository.delete(store);
    }
}
