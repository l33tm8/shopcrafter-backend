package ru.ilya.shopcraftercore.service.customization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ilya.shopcraftercore.dto.customization.BlockDto;
import ru.ilya.shopcraftercore.entity.customization.Block;
import ru.ilya.shopcraftercore.entity.customization.BlockType;
import ru.ilya.shopcraftercore.entity.goods.Store;
import ru.ilya.shopcraftercore.entity.customization.Page;
import ru.ilya.shopcraftercore.exception.EntityNotFoundException;
import ru.ilya.shopcraftercore.repository.customization.BlockRepository;
import ru.ilya.shopcraftercore.repository.customization.PageRepository;
import ru.ilya.shopcraftercore.repository.goods.StoreRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BlockService {
    private final BlockRepository blockRepository;
    private final StoreRepository storeRepository;
    private final PageRepository pageRepository;

    @Autowired
    public BlockService(BlockRepository blockRepository, StoreRepository storeRepository, PageRepository pageRepository) {
        this.pageRepository = pageRepository;
        this.storeRepository = storeRepository;
        this.blockRepository = blockRepository;
    }

    public List<BlockDto> getAllPageBlocks(long storeId, long pageId) {
        checkStoreExists(storeId);
        Page page = getPageById(pageId);
        List<Block> blocks = page.getBlocks();

        return blocks
                .stream()
                .map(BlockDto::fromEntity)
                .toList();
    }

    public BlockDto getPageBlock(long storeId, long pageId, long blockId) {
        checkStoreExists(storeId);
        getPageById(pageId);

        Optional<Block> blockOptional = blockRepository.findById(blockId);
        if (blockOptional.isEmpty()) {
            throw new EntityNotFoundException("Block with id " + blockId + " not found");
        }

        Block block = blockOptional.get();
        return BlockDto.fromEntity(block);
    }

    public BlockDto createPageBlock(long storeId, long pageId, BlockDto blockDto) {
        checkStoreExists(storeId);
        Page page = getPageById(pageId);
        Block block = new Block();
        block.setPage(page);
        block.setName(blockDto.getName());
        block.setType(BlockType.valueOf(blockDto.getBlockType()));
        block.setParams(blockDto.getParams());
        block = blockRepository.save(block);
        return BlockDto.fromEntity(block);
    }

    public BlockDto updatePageBlock(long storeId, long pageId, long blockId, BlockDto blockDto) {
        checkStoreExists(storeId);
        getPageById(pageId);
        Optional<Block> blockOptional = blockRepository.findById(blockId);
        if (blockOptional.isEmpty()) {
            throw new EntityNotFoundException("Block with id " + blockId + " not found");
        }
        Block block = blockOptional.get();
        block.setName(blockDto.getName());
        block.setType(BlockType.valueOf(blockDto.getBlockType()));
        block.setParams(blockDto.getParams());
        block = blockRepository.save(block);
        return BlockDto.fromEntity(block);
    }

    public void deletePageBlock(long storeId, long pageId, long blockId) {
        checkStoreExists(storeId);
        getPageById(pageId);
        Optional<Block> blockOptional = blockRepository.findById(blockId);
        if (blockOptional.isEmpty()) {
            throw new EntityNotFoundException("Block with id " + blockId + " not found");
        }
        Block block = blockOptional.get();
        blockRepository.delete(block);
    }

    private void checkStoreExists(long storeId) {
        Optional<Store> storeOptional = storeRepository.findById(storeId);
        if (storeOptional.isEmpty()) {
            throw new EntityNotFoundException("Store with id " + storeId + " not found");
        }
    }

    private Page getPageById(long pageId) {
        Optional<Page> pageOptional = pageRepository.findById(pageId);
        if (pageOptional.isEmpty()) {
            throw new EntityNotFoundException("Page with id " + pageId + " not found");
        }
        return pageOptional.get();
    }
}
