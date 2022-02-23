package com.koshelev.spring.web.core.controllers;

import com.koshelev.spring.web.api.cart.CartDto;
import com.koshelev.spring.web.api.exceptions.ResourceNotFoundException;
import com.koshelev.spring.web.core.converters.ProductConverter;
import com.koshelev.spring.web.api.core.ProductDto;
import com.koshelev.spring.web.core.entities.Product;
import com.koshelev.spring.web.core.services.ProductService;
import com.koshelev.spring.web.core.validators.ProductValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Tag(name = "Контроллер продуктов", description = "Методы для работы с продуктами")
public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;
    private final ProductValidator productValidator;

    @Operation(
            summary = "Запрос на получение страницы продуктов",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Page.class))
                    )

            }
    )
    @GetMapping
    public Page<ProductDto> getProducts(@RequestParam (name = "min_price",required = false) Integer minPrice,
                                        @RequestParam (name = "max_price",required = false) Integer maxPrice,
                                        @RequestParam (name = "title_part",required = false) String titlePart,
                                        @RequestParam (name = "category", required = false) String category,
                                        @RequestParam (defaultValue = "1") Integer pageNumber){
        if (pageNumber < 1){
            pageNumber = 1;
        }
        return productService.findAll(minPrice, maxPrice, titlePart, category, pageNumber).map(productConverter::entityToDto);
    }


    @Operation(
            summary = "Запрос на получение списка товаров по списку ID",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = List.class))
                    )

            }
    )
    @PostMapping("/ids")
    public List<ProductDto> getProducts(@RequestBody @Parameter(description = "Список ID запрашиваемых товаров", required = true) List<Long> productsId){
        return productService.getProductByListId(productsId).stream().map(productConverter::entityToDto).collect(Collectors.toList());
    }

    @Operation(
            summary = "Запрос на получение продукта по ID",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ProductDto.class))
                    )
            }
    )
    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable @Parameter(description = "ID товара", required = true) Long id){
        Product product = productService.getProductById(id).orElseThrow(() -> new ResourceNotFoundException("Product with ID - " + id + " not found"));
        return productConverter.entityToDto(product);
    }

    @Operation(
            summary = "Запрос на удаление продукта по ID",
            responses = {
                    @ApiResponse(
                            description = "Удаление прошло успешно", responseCode = "200"
                    )
            },
            //метод удаления товара скрыт
            hidden = true
    )
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable @Parameter(description = "ID товара", required = true) Long id){
        productService.deleteById(id);
    }

    @Operation(
            summary = "Запрос на создание нового товара",
            responses = {
                    @ApiResponse(
                            description = "Создание товара прошло успешно", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ProductDto.class))
                    )
            }
    )
    @PostMapping
    public ProductDto createNewProduct(@RequestBody @Parameter(description = "Модель нового товара", required = true) ProductDto productDto){
        productValidator.validate(productDto);
        Product product = productConverter.dtoToEntity(productDto);
        productService.save(product);
        return productConverter.entityToDto(product);
    }


    @Operation(
            summary = "Запрос на изменение товара",
            responses = {
                    @ApiResponse(
                            description = "Параметры товара успешно изменены", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ProductDto.class))
                    )
            }
    )
    @PutMapping
    public ProductDto updateProduct(@RequestBody @Parameter(description = "Модель товара с измененными параметрами", required = true) ProductDto productDto){
        productValidator.validate(productDto);
        Product product = productService.update(productDto);
        return productConverter.entityToDto(product);
    }
}
