package com.opso.cheapshop;

import com.opso.cheapshop.domain.model.Product;
import com.opso.cheapshop.domain.model.Tracking;
import com.opso.cheapshop.domain.repository.ProductRepository;
import com.opso.cheapshop.domain.service.ProductService;
import com.opso.cheapshop.domain.service.TrackingService;
import com.opso.cheapshop.service.ProductServiceImpl;
import java.util.Optional;

import com.opso.cheapshop.service.TrackingServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith({SpringExtension.class})
public class ProductServiceImplTest {
    @MockBean
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;

    @TestConfiguration
    static class ProductServiceImplTestConfiguration {
        @Bean
        public ProductService productService() {
            return new ProductServiceImpl();
        }
    }
    @Test
    @DisplayName("When getProductByTitle With Valid Title/Price/Description Then Returns Post")
    public void whenGetProductByTitleWithValidTitleThenReturnsPost() {
        // Arrange
        String name = "Mouse Razer";
        Double price = 500D;
        String description = "30k DPI";
        Long id = 1L;
        Product product = new Product().setId(id).setName(name)
                .setPrice(price)
                .setDescription(description);
        when(productRepository.findById(id))
                .thenReturn(Optional.of(product));

        // Act
        Product foundProduct = productService.getProductById(id);

        // Assert
        assertThat(foundProduct.getId()).isEqualTo(id);
    }
//    @NotNull
//    @Column(unique = true)
//    private String name;
//
//    @NotNull
//    private Double price;
//
//    @NotNull
//    private String description;
}
