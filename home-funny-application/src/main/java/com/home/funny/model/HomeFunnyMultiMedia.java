package com.home.funny.model;

import com.home.funny.constant.MediaType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/**
 * 多媒体类型
 */
@Data
@Table("home_funny_multi_media")
public class HomeFunnyMultiMedia {
    @Id
    private Long id;
    private String name;
    private String cover;
    private String description;
    private MediaType type;
    @MappedCollection(idColumn = "media_id")
    private HomeFunnyMediaTagMapping[] mediaTagMappings;
}
