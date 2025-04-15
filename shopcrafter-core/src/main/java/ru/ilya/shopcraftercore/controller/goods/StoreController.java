package ru.ilya.shopcraftercore.controller.goods;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ilya.shopcraftercore.client.goods.GoodsClient;

@RestController
@RequestMapping("/store")
public class StoreController {
    private final GoodsClient goodsClient;
    public StoreController(GoodsClient goodsClient) {
        this.goodsClient = goodsClient;
    }

    @GetMapping
    public String getAllStores() {
        return goodsClient.getAllStores();
    }

}
