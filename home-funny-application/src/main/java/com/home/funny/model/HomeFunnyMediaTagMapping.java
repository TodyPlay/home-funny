package com.home.funny.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "home_funny_media_tag_mapping", uniqueConstraints = {
        @UniqueConstraint(name = "uc_homefunnymediatagmapping", columnNames = {"media_id", "tag_id"})
})
public class HomeFunnyMediaTagMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "media_id")
    @JsonIgnore
    private HomeFunnyMultiMedia multiMedia;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private HomeFunnyMediaTag mediaTag;
}