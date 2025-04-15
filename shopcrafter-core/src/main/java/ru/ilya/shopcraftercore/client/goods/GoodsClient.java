package ru.ilya.shopcraftercore.client.goods;

import dto.StoreDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "shopcrafter-goods", url = "${shopcrafter-goods.service.url}")
public interface GoodsClient {

    @GetMapping("/store/{id}")
    String getStore(@PathVariable("id") String id);

    @GetMapping("/store")
    String getAllStores();
}
