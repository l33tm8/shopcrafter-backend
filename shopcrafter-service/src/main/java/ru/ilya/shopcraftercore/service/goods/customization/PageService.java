package ru.ilya.shopcraftercore.service.goods.customization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ilya.shopcraftercore.dto.customization.PageDto;
import ru.ilya.shopcraftercore.dto.customization.PutPageDto;
import ru.ilya.shopcraftercore.entity.customization.Page;
import ru.ilya.shopcraftercore.entity.goods.Store;
import ru.ilya.shopcraftercore.exception.EntityNotFoundException;
import ru.ilya.shopcraftercore.repository.customization.PageRepository;
import ru.ilya.shopcraftercore.repository.goods.StoreRepository;

import java.util.List;

@Service
public class PageService {

    private final PageRepository pageRepository;
    private final StoreRepository storeRepository;

    @Autowired
    public PageService(PageRepository pageRepository, StoreRepository storeRepository) {
        this.pageRepository = pageRepository;
        this.storeRepository = storeRepository;
    }

    public List<PageDto> getShopPages(long shopId) {
        storeRepository.findById(shopId).orElseThrow(() -> new EntityNotFoundException("Store not found"));
        List<Page> pages = pageRepository.findByStoreId(shopId);
        return pages.stream()
                .map(PageDto::fromEntity)
                .toList();
    }

    public PageDto getPage(long shopId, long pageId) {
        storeRepository.findById(shopId).orElseThrow(() -> new EntityNotFoundException("Store not found"));
        Page page = pageRepository.findById(pageId).orElseThrow(() -> new EntityNotFoundException("Page not found"));
        return PageDto.fromEntity(page);
    }

    @Transactional
    public PageDto createPage(long shopId, PutPageDto dto) {
        Store store = storeRepository.findById(shopId).orElseThrow(() -> new EntityNotFoundException("Store not found"));
        Page page = new Page();
        page.setStore(store);
        page.setTitle(dto.getTitle());
        page = pageRepository.save(page);
        return PageDto.fromEntity(page);
    }

    @Transactional
    public PageDto updatePage(long shopId, long pageId, PutPageDto dto) {
        storeRepository.findById(shopId).orElseThrow(() -> new EntityNotFoundException("Store not found"));
        Page page = pageRepository.findById(pageId).orElseThrow(() -> new EntityNotFoundException("Page not found"));
        page.setTitle(dto.getTitle());
        page = pageRepository.save(page);
        return PageDto.fromEntity(page);
    }

    @Transactional
    public void deletePage(long shopId, long pageId) {
        storeRepository.findById(shopId).orElseThrow(() -> new EntityNotFoundException("Store not found"));
        Page page = pageRepository.findById(pageId).orElseThrow(() -> new EntityNotFoundException("Page not found"));
        pageRepository.delete(page);
    }
}
