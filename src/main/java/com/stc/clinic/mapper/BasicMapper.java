package com.stc.clinic.mapper;

import org.mapstruct.MappingTarget;

public interface BasicMapper<T, D> {
	T dtoToEntity(D dto);

	D entityToDto(T entity);

	void updateEntityFromDto(D dto, @MappingTarget T entity);
}
	