package ru.ilya.shopcrafterapi.controller.customization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.ilya.shopcraftercore.dto.customization.PageDto;
import ru.ilya.shopcraftercore.dto.customization.PutPageDto;
import ru.ilya.shopcraftercore.service.customization.PageService;

import java.util.List;

@RestController
@RequestMapping("/stores/{storeId}/pages")
public class PageController {
    private final PageService pageService;

    @Autowired
    public PageController(PageService pageService) {
        this.pageService = pageService;
    }

    @GetMapping
    public List<PageDto> findAllPages(@PathVariable long storeId) {
        return pageService.getShopPages(storeId);
    }

    @GetMapping("/{pageId}")
    public PageDto findPage(@PathVariable long storeId, @PathVariable long pageId) {
        return pageService.getPage(storeId, pageId);
    }

    @PostMapping
    public PageDto createPage(@PathVariable long storeId, @RequestBody PutPageDto dto) {
        return pageService.createPage(storeId, dto);
    }

    @PutMapping("/{pageId}")
    public PageDto updatePage(@PathVariable long storeId, @PathVariable long pageId, @RequestBody PutPageDto dto) {
        return pageService.updatePage(storeId, pageId, dto);
    }

    @DeleteMapping("/{pageId}")
    public void deletePage(@PathVariable long storeId, @PathVariable long pageId) {
        pageService.deletePage(storeId, pageId);
    }
}
