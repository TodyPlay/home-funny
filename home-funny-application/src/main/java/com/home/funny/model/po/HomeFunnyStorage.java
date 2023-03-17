package com.home.funny.model.po;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "home_funny_storage", uniqueConstraints = {
        @UniqueConstraint(name = "uc_homefunnystorage_storage_group", columnNames = {"storage_group", "storage_path"})
})
public class HomeFunnyStorage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "storage_name", length = 64, nullable = false)
    private String storageName;

    @Column(name = "storage_group", length = 16, nullable = false)
    private String storageGroup;

    @Column(name = "storage_path", nullable = false)
    private String storagePath;

}