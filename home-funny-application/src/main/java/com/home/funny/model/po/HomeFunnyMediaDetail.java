package com.home.funny.model.po;

import com.home.funny.constant.MediaType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
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
    @JoinColumn(name = "storage_id")
    private HomeFunnyStorage storage;

}