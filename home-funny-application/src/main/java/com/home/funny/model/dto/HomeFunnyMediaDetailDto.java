package com.home.funny.model.dto;

import com.home.funny.constant.MediaType;
import com.home.funny.model.po.HomeFunnyMediaDetail;

import java.io.Serializable;

/**
 * A DTO for the {@link HomeFunnyMediaDetail} entity
 */
public record HomeFunnyMediaDetailDto(Long id,
                                      String detailName,
                                      MediaType mediaType,
                                      HomeFunnyStorageDto storage) implements Serializable {
}