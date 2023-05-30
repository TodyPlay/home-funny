package com.home.funny.model.po;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "home_funny_user_detail")
public class HomeFunnyUserDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", length = 64)
    private String username;

    @Column(name = "password", length = 128)
    private String password;

    @Column(name = "enabled")
    private Boolean enabled;

}