package ru.ilya.shopcraftergoods.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.StoreDto;
import dto.UpdateStoreDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.ilya.shopcraftergoods.service.StoreService;

@Component
public class KafkaStoreListener {
    private final ObjectMapper objectMapper;
    private final StoreService storeService;
    public KafkaStoreListener(ObjectMapper objectMapper, StoreService storeService) {
        this.objectMapper = objectMapper;
        this.storeService = storeService;
    }

    @KafkaListener(topics = "${service.kafka.MessageTopicStoreReceive}",
            groupId = "goods-service-group")
    public void consume(String message) {
        String[] parts = message.split(":", 2);
        String commandType = parts[0];
        String dtoJson = parts[1];
//        switch (commandType) {
//            case "CREATE" -> {
//                UpdateStoreDto updateStoreDto = objectMapper.convertValue(dtoJson, UpdateStoreDto.class);
//                storeService.createStore(updateStoreDto);
//            }
//            case "UPDATE" -> {
//                 StoreDto storeDto = objectMapper.convertValue(dtoJson, StoreDto.class);
//                storeService.updateStore(storeDto. )
//            }
//        }
    }

}
