package com.home.funny.model.po;

import com.home.funny.model.po.listener.StorageEntityEventListener;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.InputStream;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Entity
@Table(name = "home_funny_storage", uniqueConstraints = {
        @UniqueConstraint(name = "uc_storage__group_path", columnNames = {"storage_group", "storage_path"})
})
@EntityListeners({StorageEntityEventListener.class, AuditingEntityListener.class})
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(name = "size")
    private Long size;

    @CreatedDate
    @Column(name = "create_date_time")
    private LocalDateTime createDateTime;

    @LastModifiedDate
    @Column(name = "list_modify_date_time")
    private LocalDateTime lastModifyDateTime;

    @CreatedBy
    @ManyToOne
    @JoinColumn(name = "creator")
    private HomeFunnyUserDetail creator;

    @LastModifiedBy
    @ManyToOne
    @JoinColumn(name = "last_modified_by")
    private HomeFunnyUserDetail lastModifiedBy;

    @Version
    @Column(name = "version")
    private Integer version;

    @Transient
    private InputStream inputStream;

}