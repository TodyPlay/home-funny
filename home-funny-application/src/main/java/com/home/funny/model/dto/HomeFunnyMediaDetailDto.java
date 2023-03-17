package com.home.funny.model.dto;

import com.home.funny.model.po.HomeFunnyMediaDetail;

import java.io.Serializable;

/**
 * A DTO for the {@link HomeFunnyMediaDetail} entity
 */
public record HomeFunnyMediaDetailDto(Long id, String detailName, Integer shorter, HomeFunnyStorageDto storage) implements Serializable {
}