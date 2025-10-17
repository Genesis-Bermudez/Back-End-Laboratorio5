package org.example.Domain.dtos.maintenances;

import org.example.Domain.dtos.auth.UserResponseDto;
import org.example.Domain.models.Car;
import org.example.Domain.models.MaintenanceType;

public class MaintenanceRequestDto {

    private Long id;
    private String description;
    private MaintenanceType type;
    private Car carMaintenance;
    private UserResponseDto owner;
    private String createdAt;
    private String updatedAt;

    public MaintenanceRequestDto() {}

    public MaintenanceRequestDto(Long id, String description, MaintenanceType type, Car carMaintenance, UserResponseDto owner, String createdAt, String updatedAt) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.carMaintenance = carMaintenance;
        this.owner = owner;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public MaintenanceType getType() { return type; }
    public void setType(MaintenanceType type) { this.type = type; }
    public Car getCarMaintenance() { return carMaintenance; }
    public void setCarMaintenance(Car carMaintenance) {this.carMaintenance = carMaintenance; }
    public UserResponseDto getOwner() { return owner; }
    public void setOwner(UserResponseDto ownerId) { this.owner = ownerId; }
    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
    public String getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }
}
