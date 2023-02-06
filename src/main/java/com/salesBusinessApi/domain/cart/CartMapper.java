package com.salesBusinessApi.domain.cart;

import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.FIELD
)
public interface CartMapper {

    @Mapping(target = "cart.code")
    @Mapping(target = "cart.listItemProducts")
    @Mapping(target = "cart.totalValue")
    @Mapping(target = "cart.description")
    @Named("toDtoFromEntity")
    CartDTO toDtoFromEntity(Cart cart);

    Cart toEntityFromDto(CartDTO cartDTO);

    public abstract void updateEntityFromDto(CartDTO cartDTO, @MappingTarget Cart cart);


}
