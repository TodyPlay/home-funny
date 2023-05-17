package com.home.funny.model.po;

import com.home.funny.constant.MediaType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "home_funny_media_detail")
public class HomeFunnyMediaDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "detail_name", length = 64)
    private String detailName;

    @Enumerated(EnumType.STRING)
    @Column(name = "media_type")
    private MediaType mediaType;

    @ManyToOne
    @JoinColumn(name = "media_id")
    private HomeFunnyMultiMedia multiMedia;

    @Column(name = "sorter")
    private Integer sorter;

    @ManyToOne
    @JoinColumn(name = "storage_id")
    private HomeFunnyStorage storage;

}