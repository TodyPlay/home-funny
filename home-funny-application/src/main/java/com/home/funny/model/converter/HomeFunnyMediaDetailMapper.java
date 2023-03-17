package com.home.funny.model.converter;

import com.home.funny.model.dto.HomeFunnyMediaDetailDto;
import com.home.funny.model.po.HomeFunnyMediaDetail;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface HomeFunnyMediaDetailMapper {
    HomeFunnyMediaDetail toEntity(HomeFunnyMediaDetailDto homeFunnyMediaDetailDto);

    HomeFunnyMediaDetailDto toDto(HomeFunnyMediaDetail homeFunnyMediaDetail);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    HomeFunnyMediaDetail partialUpdate(HomeFunnyMediaDetailDto homeFunnyMediaDetailDto, @MappingTarget HomeFunnyMediaDetail homeFunnyMediaDetail);
}