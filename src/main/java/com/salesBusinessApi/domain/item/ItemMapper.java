package com.salesBusinessApi.domain.item;

import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.FIELD
)
public interface ItemMapper {

    @Mapping(target = "item.code")
    @Mapping(target = "item.name")
    @Mapping(target = "item.valueItem")
    @Mapping(target = "item.description")
    @Named("toDtoFromEntity")
    ItemDTO toDtoFromEntity(Item item);

    Item toEntityFromDto(ItemDTO itemDTO);

    public abstract void updateEntityFromDto(ItemDTO itemDTO, @MappingTarget Item item);


}
