package com.home.funny.model.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.home.funny.model.po.HomeFunnyUserDetail}
 */
public record HomeFunnyUserDetailDto(Long id, String username, String password,
                                     Boolean enabled) implements Serializable {
}