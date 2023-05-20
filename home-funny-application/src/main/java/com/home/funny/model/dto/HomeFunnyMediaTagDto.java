package com.home.funny.model.dto;

import com.home.funny.model.po.HomeFunnyMediaTag;

import java.io.Serializable;

/**
 * DTO for {@link HomeFunnyMediaTag}
 */

public record HomeFunnyMediaTagDto(Long id, String name) implements Serializable {

}