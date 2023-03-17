package com.home.funny.model;

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

    @ManyToOne
    @JoinColumn(name = "media_id")
    private HomeFunnyMultiMedia multiMedia;

    @Column(name = "shorter")
    private Integer shorter;

    @ManyToOne
    @JoinColumn(name = "storage_id")
    private HomeFunnyStorage storage;

}