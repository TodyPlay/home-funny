package com.home.funny.model.dto.query;

import com.home.funny.constant.MediaType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * 多媒体查询
 */
@Getter
@Setter
public class MediaQueryDTO {

    private String name;

    private MediaType[] types;

    private String[] tags;

    private LocalDate dateStart;

    private LocalDate dateEnd;

}
