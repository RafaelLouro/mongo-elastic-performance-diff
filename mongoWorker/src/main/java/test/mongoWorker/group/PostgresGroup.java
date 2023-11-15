package test.mongoWorker.group;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Table(name = "groups")
public class PostgresGroup implements Persistable {
    @Id
    @Column(value = "id")
    private UUID id;
    private String externalId;
    @Version
    private Integer version;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public UUID getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return Objects.isNull(id);
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public PostgresGroup() {
    }

    public PostgresGroup(String externalId) {
        this.externalId = externalId;
    }

    public PostgresGroup(String externalId, Integer version, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.externalId = externalId;
        this.version = version;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public PostgresGroup(UUID id, String externalId, Integer version, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.externalId = externalId;
        this.version = version;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", externalId='" + externalId + '\'' +
                ", version=" + version +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
