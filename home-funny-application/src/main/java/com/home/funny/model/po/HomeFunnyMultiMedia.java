package com.home.funny.model.po;

import com.home.funny.constant.MediaType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "home_funny_multi_media")
@EntityListeners(AuditingEntityListener.class)
public class HomeFunnyMultiMedia extends AbstractAuditable<HomeFunnyUserDetail, Long> {

    @Column(name = "name", length = 64)
    private String name;

    @ManyToOne
    @JoinColumn(name = "cover_storage_id")
    private HomeFunnyStorage coverStorage;

    @Enumerated(EnumType.STRING)
    @Column(name = "media_type")
    private MediaType mediaType;

    @Column(name = "description", length = 128)
    private String description;

    @ManyToMany(cascade = {CascadeType.MERGE})
    @Fetch(FetchMode.SUBSELECT)
    private List<HomeFunnyMediaTag> mediaTags;

    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    private List<HomeFunnyMediaDetail> mediaDetails;

    @Override
    public void setId(Long id) {
        super.setId(id);
    }
}