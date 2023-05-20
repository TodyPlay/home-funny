package com.home.funny.model.converter;

import com.home.funny.model.dto.HomeFunnyMultiMediaDto;
import com.home.funny.model.po.HomeFunnyMultiMedia;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {HomeFunnyMediaDetailMapper.class, HomeFunnyStorageMapper.class})
public interface HomeFunnyMultiMediaMapper {
    HomeFunnyMultiMedia toEntity(HomeFunnyMultiMediaDto homeFunnyMultiMediaDto);

    HomeFunnyMultiMediaDto toDto(HomeFunnyMultiMedia homeFunnyMultiMedia);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    HomeFunnyMultiMedia partialUpdate(HomeFunnyMultiMediaDto homeFunnyMultiMediaDto, @MappingTarget HomeFunnyMultiMedia homeFunnyMultiMedia);
}