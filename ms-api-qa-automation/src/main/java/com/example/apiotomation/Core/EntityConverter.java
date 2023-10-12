package com.example.apiotomation.Core;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface EntityConverter<D extends BaseDto, E extends BaseEntity> {
    Set<E> convertToEntitySet(Collection<D> dtoList);

    List<E> convertToEntityList(Collection<D> dtoList);

    E convertToEntity(D dto);
}
