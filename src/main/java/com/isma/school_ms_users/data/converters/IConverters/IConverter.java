package com.isma.school_ms_users.core.converters.IConverters;

import java.util.List;

/***
 *
 * @param <U>
 * @param <V>
 */
public interface IConverter<U,V> {
    public V convertToDto(U u);
    public List<V> convertListToListDto(List<U> us);
    public U convertToEntity(V v);
    public List<U> convertListDtoToListEntity(List<V> vs);
}
