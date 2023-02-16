package com.home.funny.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "home_funny_file", uniqueConstraints = {
        @UniqueConstraint(name = "uc_homefunnyfile_file_group", columnNames = {"file_group", "file_path"})
})
public class HomeFunnyFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "file_name", length = 64)
    private String fileName;

    @Column(name = "file_group", length = 16)
    private String fileGroup;

    @Column(name = "file_path")
    private String filePath;

}