package com.opso.cheapshop.controller;

import com.opso.cheapshop.domain.model.Product;
import com.opso.cheapshop.domain.model.Vote;
import com.opso.cheapshop.domain.service.ProductService;
import com.opso.cheapshop.resource.SaveProductResource;
import com.opso.cheapshop.resource.ProductResource;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

import com.opso.cheapshop.resource.SaveVoteResource;
import com.opso.cheapshop.resource.VoteResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api"})
public class ProductsController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ModelMapper mapper;

    public ProductsController() {
    }

    @GetMapping({"/products"})
    public Page<ProductResource> getAllProducts(Pageable pageable) {
        List<ProductResource> products = (List)this.productService.getAllProducts(pageable).getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        int productCount = products.size();
        return new PageImpl(products, pageable, (long)productCount);
    }

    @GetMapping({"/products/{id}"})
    public ProductResource getProductById(@PathVariable(name = "id") Long productId) {
        return this.convertToResource(this.productService.getProductById(productId));
    }

    @PostMapping({"/products"})
    public ProductResource createProduct(@Valid @RequestBody SaveProductResource resource) {
        return this.convertToResource(this.productService.createProduct(this.convertToEntity(resource)));
    }

    @PutMapping({"/products/{id}"})
    public ProductResource updateProduct(@PathVariable(name = "id") Long productId, @Valid @RequestBody SaveProductResource resource) {
        return this.convertToResource(this.productService.updateProduct(productId, this.convertToEntity(resource)));
    }

    @DeleteMapping({"/products/{productId}"})
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {
        return this.productService.deleteProduct(productId);
    }

    private Product convertToEntity(SaveProductResource resource) {
        return (Product)this.mapper.map(resource, Product.class);
    }

    private ProductResource convertToResource(Product entity) {
        return (ProductResource)this.mapper.map(entity, ProductResource.class);
    }
}