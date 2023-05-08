package com.home.funny.model.dto;

import com.home.funny.constant.MediaType;
import com.home.funny.model.po.HomeFunnyMultiMedia;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * A DTO for the {@link HomeFunnyMultiMedia} entity
 */
public record HomeFunnyMultiMediaDto(Long id,
                                     String name,
                                     String coverName,
                                     MediaType mediaType,
                                     LocalDate createDate,
                                     String description,
                                     List<HomeFunnyMediaDetailDto> mediaDetails) implements Serializable {
}