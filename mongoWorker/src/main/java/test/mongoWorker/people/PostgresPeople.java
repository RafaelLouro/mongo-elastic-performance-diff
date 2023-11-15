package test.mongoWorker.people;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Table(name = "people")
public class PostgresPeople {
    @Id
    private UUID id;
    private String cpf;
    private UUID groupId;
    private Integer groupVersion;
    private Boolean active;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public PostgresPeople() {
    }

    public PostgresPeople(String cpf, UUID groupId, Integer groupVersion, Boolean active, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.cpf = cpf;
        this.groupId = groupId;
        this.groupVersion = groupVersion;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public PostgresPeople(UUID id, String cpf, UUID groupId, Integer groupVersion, Boolean active, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.cpf = cpf;
        this.groupId = groupId;
        this.groupVersion = groupVersion;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public UUID getGroupId() {
        return groupId;
    }

    public void setGroupId(UUID groupId) {
        this.groupId = groupId;
    }

    public Integer getGroupVersion() {
        return groupVersion;
    }

    public void setGroupVersion(Integer groupVersion) {
        this.groupVersion = groupVersion;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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
        if (!(o instanceof PostgresPeople that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(cpf, that.cpf) && Objects.equals(groupId, that.groupId) && Objects.equals(groupVersion, that.groupVersion) && Objects.equals(active, that.active) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cpf, groupId, groupVersion, active, createdAt, updatedAt);
    }
}
