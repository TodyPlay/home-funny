package com.home.funny.dto.media;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 多媒体查询
 */
@Getter
@Setter
public class MediaQueryDTO {

    private String name;

    private String[] type;

    private String[] tags;

    private Date[] date;

}
