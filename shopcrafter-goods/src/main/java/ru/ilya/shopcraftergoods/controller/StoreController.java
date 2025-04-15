package ru.ilya.shopcraftergoods.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/store")
public class StoreController {

    @GetMapping
    public String getAllStores() {
        return "ok";
    }

    @GetMapping("/{id}")
    public String getStoreById(@PathVariable int id) {
        return "ok";
    }
}
