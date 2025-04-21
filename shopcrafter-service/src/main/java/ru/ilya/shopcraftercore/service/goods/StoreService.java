package ru.ilya.shopcraftercore.service.goods;

import org.springframework.beans.factory.annotation.Autowired;
import ru.ilya.shopcraftercore.dto.goods.store.StoreDto;
import ru.ilya.shopcraftercore.dto.goods.store.UpdateStoreDto;
import org.springframework.stereotype.Service;
import ru.ilya.shopcraftercore.entity.goods.Store;
import ru.ilya.shopcraftercore.repository.goods.StoreRepository;

import java.util.List;

@Service
public class StoreService {
    private final StoreRepository storeRepository;

    @Autowired
    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
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

    public StoreDto createStore(UpdateStoreDto storeDto) {
        Store store = new Store();
        store.setName(storeDto.getName());
        store.setOwnerId(storeDto.getOwnerId());
        store.setUserId(storeDto.getUserId());
        storeRepository.save(store);
        return StoreDto.fromEntity(store);
    }

    public StoreDto updateStore(long id, UpdateStoreDto storeDto) {
        Store store = storeRepository.findById(id).orElse(null);
        if (store == null) {
            return null;
        }
        store.setName(storeDto.getName());
        store.setOwnerId(storeDto.getOwnerId());
        storeRepository.save(store);
        return StoreDto.fromEntity(store);
    }

    public void deleteStore(long id) {
        storeRepository.deleteById(id);
    }
}
