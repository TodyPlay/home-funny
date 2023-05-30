package com.home.funny.model.converter;

import com.home.funny.model.dto.HomeFunnyMediaTagDto;
import com.home.funny.model.po.HomeFunnyMediaTag;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface HomeFunnyMediaTagMapper {
    HomeFunnyMediaTagDto toDto(HomeFunnyMediaTag homeFunnyMediaTag);

    HomeFunnyMediaTag toEntity(HomeFunnyMediaTagDto homeFunnyMediaTagDto);
}