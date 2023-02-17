package com.home.funny.model;

import com.home.funny.constant.MediaType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "home_funny_multi_media")
public class HomeFunnyMultiMedia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", length = 64)
    private String name;

    @Column(name = "cover", length = 128)
    private String cover;

    @ManyToOne
    @JoinColumn(name = "cover_file_id")
    private HomeFunnyFile coverFile;

    @Enumerated(EnumType.STRING)
    @Column(name = "media_type")
    private MediaType mediaType;

    @Column(name = "create_date")
    private LocalDate createDate;

    @Column(name = "description", length = 128)
    private String description;

    @OneToMany(mappedBy = "multiMedia")
    private List<HomeFunnyMediaTagMapping> tagMappings;

    @OneToMany(mappedBy = "multiMedia")
    private List<HomeFunnyMediaDetail> mediaDetails = new ArrayList<>();

}