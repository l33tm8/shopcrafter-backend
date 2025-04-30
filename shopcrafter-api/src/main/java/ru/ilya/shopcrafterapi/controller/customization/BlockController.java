package ru.ilya.shopcrafterapi.controller.customization;

import org.springframework.web.bind.annotation.*;
import ru.ilya.shopcraftercore.dto.customization.BlockDto;
import ru.ilya.shopcraftercore.dto.customization.PutPageDto;
import ru.ilya.shopcraftercore.entity.customization.Block;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/store/{storeId}/page/{pageId}/blocks")
public class BlockController {

    @GetMapping
    public List<BlockDto> getAllPageBlocks(@PathVariable long storeId, @PathVariable long pageId) {
        return new ArrayList<>();
    }

    @GetMapping("/{blockId}")
    public BlockDto getPageBlock(@PathVariable long storeId, @PathVariable long pageId, @PathVariable long blockId) {
        return new BlockDto();
    }

    @PostMapping
    public BlockDto createPageBlock(@PathVariable long storeId, @PathVariable long pageId, @RequestBody PutPageDto dto) {
        return new BlockDto();
    }

    @PutMapping("/{blockId}")
    public BlockDto updatePageBlock(@PathVariable long storeId, @PathVariable long pageId, @PathVariable long blockId, @RequestBody PutPageDto dto) {
        return new BlockDto();
    }

    @DeleteMapping("/{blockId}")
    public void deleteBlock(@PathVariable long storeId, @PathVariable long pageId, @PathVariable long blockId) {
        return;
    }

}
