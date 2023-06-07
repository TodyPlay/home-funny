package com.home.funny.model.converter;

import com.home.funny.model.dto.HomeFunnyUserDetailDto;
import com.home.funny.model.po.HomeFunnyUserDetail;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface HomeFunnyUserDetailMapper {
    HomeFunnyUserDetail toEntity(HomeFunnyUserDetailDto homeFunnyUserDetailDto);

    HomeFunnyUserDetailDto toDto(HomeFunnyUserDetail homeFunnyUserDetail);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    HomeFunnyUserDetail partialUpdate(HomeFunnyUserDetailDto homeFunnyUserDetailDto, @MappingTarget HomeFunnyUserDetail homeFunnyUserDetail);
}