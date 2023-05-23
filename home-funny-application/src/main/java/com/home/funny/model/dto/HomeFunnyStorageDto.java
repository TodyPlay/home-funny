package com.home.funny.model.dto;

import com.home.funny.model.po.HomeFunnyStorage;

import java.io.Serializable;

/**
 * A DTO for the {@link HomeFunnyStorage} entity
 */
public record HomeFunnyStorageDto(Long id,
                                  String storageName,
                                  String storageGroup,
                                  String storagePath,
                                  Long size) implements Serializable {
}