package com.home.funny.model.dto;

import com.home.funny.constant.MediaType;
import com.home.funny.model.po.HomeFunnyMultiMedia;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * A DTO for the {@link HomeFunnyMultiMedia} entity
 */
public record HomeFunnyMultiMediaDto(Long id,
                                     @NotBlank
                                     String name,
                                     MediaType mediaType,
                                     LocalDate createDate,
                                     String description,

                                     List<HomeFunnyMediaDetailDto> mediaDetails,
                                     HomeFunnyStorageDto coverStorage,
                                     List<HomeFunnyMediaTagDto> mediaTags) implements Serializable {

}