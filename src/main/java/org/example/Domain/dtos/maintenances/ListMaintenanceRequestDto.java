package org.example.Domain.dtos.maintenances;

import org.example.Domain.dtos.maintenances.MaintenanceRequestDto;

import java.util.List;

public class ListMaintenanceRequestDto {
    private List<MaintenanceRequestDto> maintenances;

    public ListMaintenanceRequestDto() {}

    public ListMaintenanceRequestDto(List<MaintenanceRequestDto> maintenances) {
        this.maintenances = maintenances;
    }

    public List<MaintenanceRequestDto> getMaintenances() { return maintenances; }
    public void setMaintenances(List<MaintenanceRequestDto> cars) { this.maintenances = maintenances; }
}
