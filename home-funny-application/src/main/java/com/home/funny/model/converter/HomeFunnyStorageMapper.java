package com.home.funny.model.converter;

import com.home.funny.model.dto.HomeFunnyStorageDto;
import com.home.funny.model.po.HomeFunnyStorage;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface HomeFunnyStorageMapper {
    HomeFunnyStorage toEntity(HomeFunnyStorageDto homeFunnyStorageDto);

    HomeFunnyStorageDto toDto(HomeFunnyStorage homeFunnyStorage);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    HomeFunnyStorage partialUpdate(HomeFunnyStorageDto homeFunnyStorageDto, @MappingTarget HomeFunnyStorage homeFunnyStorage);
}