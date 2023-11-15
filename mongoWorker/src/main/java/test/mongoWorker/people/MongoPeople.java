package test.mongoWorker.people;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document(value = "people")
public class MongoPeople {
    @Id
    private String id;
    private String cpf;
    private UUID groupId;
    private Integer groupVersion;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public MongoPeople() {
    }

    public MongoPeople(String cpf, UUID groupId, Integer groupVersion, Boolean active, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.cpf = cpf;
        this.groupId = groupId;
        this.groupVersion = groupVersion;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public MongoPeople(String id, String cpf, UUID groupId, Integer groupVersion, Boolean active, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.cpf = cpf;
        this.groupId = groupId;
        this.groupVersion = groupVersion;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public MongoPeople(PostgresPeople postgresPeople) {
        //this.id = postgresGroup.getId();
        this.cpf = postgresPeople.getCpf();
        this.groupId = postgresPeople.getGroupId();
        this.groupVersion = postgresPeople.getGroupVersion();
        this.active = true;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();

    }
}
