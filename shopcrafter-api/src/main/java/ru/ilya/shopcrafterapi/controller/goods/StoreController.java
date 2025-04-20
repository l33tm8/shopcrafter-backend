package ru.ilya.shopcrafterapi.controller.goods;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.ilya.shopcraftercore.dto.goods.store.StoreDto;
import ru.ilya.shopcraftercore.dto.goods.store.UpdateStoreDto;
import ru.ilya.shopcraftercore.service.goods.StoreService;

import java.util.List;

@RestController
@RequestMapping("/stores")
public class StoreController {
    private final StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping
    public List<StoreDto> getStores() {
        return storeService.getAllStores();
    }

    @GetMapping("/{id}")
    public StoreDto getStore(@PathVariable long id) {
        return storeService.getStoreById(id);
    }

    @PostMapping
    public StoreDto createStore(@RequestBody UpdateStoreDto storeDto) {
        return storeService.createStore(storeDto);
    }

    @PutMapping("/{id}")
    public StoreDto updateStore(@PathVariable long id, @RequestBody UpdateStoreDto storeDto) {
        return storeService.updateStore(id, storeDto);
    }

    @DeleteMapping("/{id}")
    public void deleteStore(@PathVariable long id) {
        storeService.deleteStore(id);
    }
}
