package com.koshelev.spring.web.core;

import com.koshelev.spring.web.api.dto.ProductDto;
import com.koshelev.spring.web.core.converters.ProductConverter;
import com.koshelev.spring.web.core.entities.Product;
import com.koshelev.spring.web.core.services.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private ProductConverter productConverter;


    @Test
    public void getProductByIdTest() throws Exception{
        Product product = new Product(5L, "Product", 45);
        ProductDto productDto = new ProductDto(5L, "Product", 45);
        Mockito.when(productService.getProductById(5L)).thenReturn(Optional.of(product));
        Mockito.when(productConverter.entityToDto(product)).thenReturn(productDto);
        mvc
                .perform(get("/api/v1/products/5").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is(productDto.getTitle())))
                .andExpect(jsonPath("$.cost", is(productDto.getCost())));
    }
}
