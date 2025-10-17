package org.example.Domain.dtos.maintenances;

import org.example.Domain.models.Car;
import org.example.Domain.models.MaintenanceType;

public class UpdateMaintenanceRequestDto {

    private Long id;
    private String description;
    private MaintenanceType type;
    private Car carMaintenance;

    public UpdateMaintenanceRequestDto() {}

    public UpdateMaintenanceRequestDto(Long id, String description, MaintenanceType type, Car carMaintenance) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.carMaintenance = carMaintenance;
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
}
