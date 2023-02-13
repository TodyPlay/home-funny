package com.home.funny.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * 多媒体标签
 */
@Data
@Table("home_funny_media_tag")
public class HomeFunnyMediaTag {
    @Id
    private Long id;

    private String name;
}
