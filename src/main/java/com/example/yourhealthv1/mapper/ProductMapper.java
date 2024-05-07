package com.example.yourhealthv1.mapper;

import com.example.yourhealthv1.dto.ProductDto;
import com.example.yourhealthv1.entity.Products;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "grams", source = "grams")
    @Mapping(target = "cals", source = "cals")
    @Mapping(target = "proteins", source = "proteins")
    @Mapping(target = "carbs", source = "carbs")
    @Mapping(target = "fats", source = "fats")
    Products productDtoToProductsEntity(ProductDto productDto);


    @Mapping(target = "name", source = "name")
    @Mapping(target = "grams", source = "grams")
    @Mapping(target = "cals", source = "cals")
    @Mapping(target = "proteins", source = "proteins")
    @Mapping(target = "carbs", source = "carbs")
    @Mapping(target = "fats", source = "fats")
    List<ProductDto> productsEntityToProductDto(List<Products> productsList);
}
