package com.home.funny.model.po;

import com.home.funny.model.po.listener.StorageEntityEventListener;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.InputStream;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "home_funny_storage", uniqueConstraints = {
        @UniqueConstraint(name = "uc_storage__group_path", columnNames = {"storage_group", "storage_path"})
})
@EntityListeners({StorageEntityEventListener.class, AuditingEntityListener.class})

public class HomeFunnyStorage extends AbstractAuditable<HomeFunnyUserDetail, Long> {

    @Column(name = "storage_name", length = 64, nullable = false)
    private String storageName;

    @Column(name = "storage_group", length = 16, nullable = false)
    private String storageGroup;

    @Column(name = "storage_path", nullable = false)
    private String storagePath;

    @Column(name = "size")
    private Long size;

    @Version
    @Column(name = "version")
    private Long version;

    @Transient
    private InputStream inputStream;

    @Override
    public void setId(Long id) {
        super.setId(id);
    }
}