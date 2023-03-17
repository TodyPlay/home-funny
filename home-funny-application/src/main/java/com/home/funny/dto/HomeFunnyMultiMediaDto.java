package com.home.funny.dto;

import com.home.funny.constant.MediaType;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DTO for the {@link com.home.funny.model.HomeFunnyMultiMedia} entity
 */
public record HomeFunnyMultiMediaDto(Long id,
                                     String name,
                                     String coverName,
                                     MediaType mediaType,
                                     LocalDate createDate,
                                     String description) implements Serializable {
}