package com.home.funny.model.po;

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
@Table(name = "home_funny_media_tag", uniqueConstraints = {
        @UniqueConstraint(name = "uc_homefunnymediatag_name", columnNames = {"name"})
})
public class HomeFunnyMediaTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", length = 64)
    private String name;

}