package com.home.funny.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("home_funny_media_tag_mapping")
public class HomeFunnyMediaTagMapping {
    @Id
    private Long id;
    private Long mediaId;
    private Long tagId;
    @MappedCollection(idColumn = "id")
    private HomeFunnyMediaTag mediaTag;
}
