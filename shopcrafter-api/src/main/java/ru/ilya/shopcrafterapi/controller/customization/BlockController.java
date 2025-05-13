package ru.ilya.shopcrafterapi.controller.customization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.ilya.shopcraftercore.dto.customization.BlockDto;
import ru.ilya.shopcraftercore.dto.customization.PutPageDto;
import ru.ilya.shopcraftercore.entity.customization.Block;
import ru.ilya.shopcraftercore.service.customization.BlockService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/store/{storeId}/page/{pageId}/blocks")
public class BlockController {

    private final BlockService blockService;

    @Autowired
    public BlockController(final BlockService blockService) {
        this.blockService = blockService;
    }

    @GetMapping
    public List<BlockDto> getAllPageBlocks(@PathVariable long storeId, @PathVariable long pageId) {
        return blockService.getAllPageBlocks(storeId, pageId);
    }

    @GetMapping("/{blockId}")
    public BlockDto getPageBlock(@PathVariable long storeId, @PathVariable long pageId, @PathVariable long blockId) {
        return blockService.getPageBlock(storeId, pageId, blockId);
    }

    @PostMapping
    public BlockDto createPageBlock(@PathVariable long storeId, @PathVariable long pageId, @RequestBody BlockDto dto) {
        return blockService.createPageBlock(storeId, pageId, dto);
    }

    @PutMapping("/{blockId}")
    public BlockDto updatePageBlock(@PathVariable long storeId, @PathVariable long pageId, @PathVariable long blockId, @RequestBody BlockDto dto) {
        return blockService.updatePageBlock(storeId, pageId, blockId, dto);
    }

    @DeleteMapping("/{blockId}")
    public void deleteBlock(@PathVariable long storeId, @PathVariable long pageId, @PathVariable long blockId) {
        blockService.deletePageBlock(storeId, pageId, blockId);
    }

}
