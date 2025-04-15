package ru.ilya.shopcraftergoods.service;

import dto.StoreDto;
import dto.UpdateStoreDto;
import org.springframework.stereotype.Service;
import ru.ilya.shopcraftergoods.entity.Store;
import ru.ilya.shopcraftergoods.repository.StoreRepository;

import java.util.List;

@Service
public class StoreService {
    private final StoreRepository storeRepository;
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
