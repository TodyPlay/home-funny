package com.home.funny.dto.media;

import com.home.funny.constant.MediaType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

/**
 * 多媒体查询
 */
@Getter
@Setter
public class MediaQueryDTO {

    private String name;

    private MediaType[] type;

    private String[] tags;

    private LocalDate[] date;

}
