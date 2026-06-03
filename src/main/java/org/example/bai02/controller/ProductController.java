package org.example.bai02.controller;

import lombok.RequiredArgsConstructor;

import org.example.bai02.model.dto.response.ApiDataResponse;
import org.example.bai02.model.entity.Product;
import org.example.bai02.service.ProductService;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ApiDataResponse<List<Product>> findAll() {

        return new ApiDataResponse<>(
                true,
                "Lấy danh sách sản phẩm thành công",
                productService.findAll(),
                HttpStatus.OK
        );
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    public ApiDataResponse<Product> save(
            @RequestBody Product product) {

        return new ApiDataResponse<>(
                true,
                "Thêm sản phẩm thành công",
                productService.save(product),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    public ApiDataResponse<Product> update(
            @PathVariable Long id,
            @RequestBody Product product) {

        return new ApiDataResponse<>(
                true,
                "Cập nhật sản phẩm thành công",
                productService.update(id, product),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    public ApiDataResponse<String> delete(
            @PathVariable Long id) {

        productService.delete(id);

        return new ApiDataResponse<>(
                true,
                "Xóa sản phẩm thành công",
                "Deleted",
                HttpStatus.OK
        );
    }
}