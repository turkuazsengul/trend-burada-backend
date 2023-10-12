package com.example.apiotomation.Core;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface ModelConverter<D extends BaseDto, E extends BaseEntity> {

    Set<D> convertToDtoSet(Collection<E> entityList);

    List<D> convertToDtoList(Collection<E> entityList);

    D convertToDto(E entity);
}
