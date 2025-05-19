package ru.ilya.shopcrafterapi.controller.goods;


import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
    @Operation(summary = "Создание магазина", description = "Создание магазина, авторизованный пользователь становится владельцем")
    public StoreDto createStore(@AuthenticationPrincipal UserDetails userDetails, @RequestBody UpdateStoreDto storeDto) {
        return storeService.createStore(userDetails, storeDto);
    }

    @PutMapping("/{id}")
    public StoreDto updateStore(@AuthenticationPrincipal UserDetails userDetails, @PathVariable long id, @RequestBody UpdateStoreDto storeDto) {
        return storeService.updateStore(userDetails, id, storeDto);
    }

    @DeleteMapping("/{id}")
    public void deleteStore(@AuthenticationPrincipal UserDetails userDetails, @PathVariable long id) {
        storeService.deleteStore(userDetails, id);
    }


}
