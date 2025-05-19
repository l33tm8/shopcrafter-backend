package ru.ilya.shopcraftercore.service.goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import ru.ilya.shopcraftercore.dto.goods.store.StoreDto;
import ru.ilya.shopcraftercore.dto.goods.store.UpdateStoreDto;
import org.springframework.stereotype.Service;
import ru.ilya.shopcraftercore.entity.auth.User;
import ru.ilya.shopcraftercore.entity.goods.Store;
import ru.ilya.shopcraftercore.exception.EntityNotFoundException;
import ru.ilya.shopcraftercore.exception.ForbiddenException;
import ru.ilya.shopcraftercore.repository.auth.UserRepository;
import ru.ilya.shopcraftercore.repository.goods.StoreRepository;

import java.util.List;
import java.util.Objects;

@Service
public class StoreService {
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    @Autowired
    public StoreService(StoreRepository storeRepository, UserRepository userRepository) {
        this.storeRepository = storeRepository;
        this.userRepository = userRepository;
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
        storeRepository.delete(store);
    }
}
