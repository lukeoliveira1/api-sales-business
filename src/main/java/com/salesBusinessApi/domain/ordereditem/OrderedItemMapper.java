package com.salesBusinessApi.domain.ordereditem;

import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.FIELD
)
public interface OrderedItemMapper {

    @Mapping(target = "orderedItem.code")
    @Mapping(target = "orderedItem.quantity")
    @Mapping(target = "orderedItem.item")
    @Named("toDtoFromEntity")
    OrderedItemDTO toDtoFromEntity(OrderedItem orderedItem);

    OrderedItem toEntityFromDto(OrderedItemDTO orderedItemDTO);

    @Mapping(target = "orderedItem.code")
    @Mapping(target = "orderedItem.codeItem")
    @Mapping(target = "orderedItem.quantity")
    OrderedItem toEntityFromForm(OrderedItemForm orderedItemForm);

    public abstract void updateEntityFromForm(OrderedItemForm orderedItemForm, @MappingTarget OrderedItem orderedItem);

}
