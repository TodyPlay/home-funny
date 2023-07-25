package com.home.funny.model.dto;

import com.home.funny.constant.MediaType;
import com.home.funny.model.po.HomeFunnyMultiMedia;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * A DTO for the {@link HomeFunnyMultiMedia} entity
 */
public record HomeFunnyMultiMediaDto(Long id,
                                     @NotBlank
                                     String name,
                                     MediaType mediaType,
                                     String description,

                                     List<HomeFunnyMediaDetailDto> mediaDetails,
                                     HomeFunnyStorageDto coverStorage,
                                     List<HomeFunnyMediaTagDto> mediaTags,
                                     LocalDateTime lastModifiedDate) implements Serializable {

}