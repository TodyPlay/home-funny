package com.home.funny.model.po;

import com.home.funny.constant.MediaType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
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

    @ManyToOne
    @JoinColumn(name = "cover_storage_id")
    private HomeFunnyStorage coverStorage;

    @Enumerated(EnumType.STRING)
    @Column(name = "media_type")
    private MediaType mediaType;

    @CreatedDate
    @Column(name = "create_date")
    private LocalDate createDate;

    @Column(name = "description", length = 128)
    private String description;

    @ManyToMany(cascade = {CascadeType.MERGE})
    private List<HomeFunnyMediaTag> mediaTags;

    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<HomeFunnyMediaDetail> mediaDetails;

}