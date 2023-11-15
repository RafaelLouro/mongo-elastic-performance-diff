package test.mongoWorker.group;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Objects;

@Document(value = "groups")
public class MongoGroup {
    @Id
    private String id;
    private String externalId;
    private Integer version;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public MongoGroup() {
    }

    public MongoGroup(String id, String externalId, Integer version, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.externalId = externalId;
        this.version = version;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MongoGroup mongoGroup)) return false;
        return id.equals(mongoGroup.id) && externalId.equals(mongoGroup.externalId) && version.equals(mongoGroup.version) && createdAt.equals(mongoGroup.createdAt) && updatedAt.equals(mongoGroup.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, externalId, version, createdAt, updatedAt);
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

    public MongoGroup(PostgresGroup postgresGroup) {
        //this.id = postgresGroup.getId();
        this.externalId = postgresGroup.getExternalId();
        this.version = postgresGroup.getVersion();
        this.createdAt = postgresGroup.getCreatedAt();
        this.updatedAt = postgresGroup.getUpdatedAt();;

    }
}
