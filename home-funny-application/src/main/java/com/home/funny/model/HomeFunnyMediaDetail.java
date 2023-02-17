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

    @ManyToOne
    @JoinColumn(name = "media_id")
    private HomeFunnyMultiMedia multiMedia;

    @Column(name = "order")
    private Integer order;

    @ManyToOne
    @JoinColumn("file_id")
    private HomeFunnyFile file;

}