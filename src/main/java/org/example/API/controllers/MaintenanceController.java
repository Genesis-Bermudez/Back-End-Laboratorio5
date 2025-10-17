package org.example.API.controllers;

import com.google.gson.Gson;
import org.example.DataAccess.services.MaintenanceService;
import org.example.Domain.dtos.RequestDto;
import org.example.Domain.dtos.ResponseDto;
import org.example.Domain.dtos.auth.UserResponseDto;
import org.example.Domain.dtos.cars.*;
import org.example.Domain.dtos.maintenances.AddMaintenanceRequestDto;
import org.example.Domain.dtos.maintenances.DeleteMaintenanceRequestDto;
import org.example.Domain.dtos.maintenances.UpdateMaintenanceRequestDto;
import org.example.Domain.models.Car;
import org.example.Domain.models.Maintenance;

import java.util.List;
import java.util.stream.Collectors;

public class MaintenanceController {
    private final MaintenanceService maintenanceService;
    private final Gson gson = new Gson();

    public MaintenanceController(MaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    public ResponseDto route(RequestDto request) {
        try {
            switch (request.getRequest()) {
                case "add":
                    return handleAddMaintenance(request);
                case "update":
                    return handleUpdateMaintenance(request);
                case "delete":
                    return handleDeleteMaintenance(request);
                case "list":
                    return handleListMaintenances(request);
                case "get":
                    return handleGetMaintenance(request);
                default:
                    return new ResponseDto(false, "Unknown request: " + request.getRequest(), null);
            }
        } catch (Exception e) {
            return new ResponseDto(false, e.getMessage(), null);
        }
    }

    // --- ADD MAINTENANCE ---
    private ResponseDto handleAddMaintenance(RequestDto request) {
        try {
            if (request.getToken() == null || request.getToken().isEmpty()) {
                return new ResponseDto(false, "Unauthorized", null);
            }

            AddMaintenanceRequestDto dto = gson.fromJson(request.getData(), AddMaintenanceRequestDto.class);
            Maintenance maintenance = maintenanceService.createMaintenance(dto.getDescription(), dto.getType(), dto.getCarMaintenance());

            MaintenanceResponseDto response = toResponseDto(maintenance);
            return new ResponseDto(true, "Maintenance added successfully", gson.toJson(response));
        } catch (Exception e) {
            System.out.println("Error in handleAddMaintenance: " + e.getMessage());
            throw e;
        }
    }

    // --- UPDATE MAINTENANCE ---
    private ResponseDto handleUpdateMaintenance(RequestDto request) {
        try {
            if (request.getToken() == null || request.getToken().isEmpty()) {
                return new ResponseDto(false, "Unauthorized", null);
            }

            UpdateMaintenanceRequestDto dto = gson.fromJson(request.getData(), UpdateMaintenanceRequestDto.class);
            Maintenance updated = maintenanceService.updateMaintenance(dto.getId(), dto.getDescription(), dto.getType(), dto.getCarMaintenance());

            if (updated == null) {
                return new ResponseDto(false, "Maintenance not found", null);
            }

            MaintenanceResponseDto response = toResponseDto(updated);
            return new ResponseDto(true, "Maintenance updated successfully", gson.toJson(response));
        } catch (Exception e) {
            System.out.println("Error in handleUpdateMaintenance: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // --- DELETE MAINTENANCE ---
    private ResponseDto handleDeleteMaintenance(RequestDto request) {
        try {
            if (request.getToken() == null || request.getToken().isEmpty()) {
                return new ResponseDto(false, "Unauthorized", null);
            }

            DeleteMaintenanceRequestDto dto = gson.fromJson(request.getData(), DeleteMaintenanceRequestDto.class);
            boolean deleted = maintenanceService.deleteMaintenance(dto.getId());

            if (!deleted) {
                return new ResponseDto(false, "Maintenance not found or could not be deleted", null);
            }

            return new ResponseDto(true, "Maintenance deleted successfully", null);
        } catch (Exception e) {
            System.out.println("Error in handleDeleteMaintenance: " + e.getMessage());
            throw e;
        }
    }

    // --- LIST MAINTENANCES ---
    private ResponseDto handleListMaintenances(RequestDto request) {
        try {
            if (request.getToken() == null || request.getToken().isEmpty()) {
                return new ResponseDto(false, "Unauthorized", null);
            }

            List<Maintenance> maintenances = maintenanceService.getAllMaintenances();
            List<CarResponseDto> maintenanceDtos = maintenances.stream()
                    .map(this::toResponseDto)
                    .collect(Collectors.toList());

            ListCarsResponseDto response = new ListCarsResponseDto(carDtos);
            return new ResponseDto(true, "Cars retrieved successfully", gson.toJson(response));
        } catch (Exception e) {
            System.out.println("Error in handleListCars: " + e.getMessage());
            throw e;
        }
    }

    // --- GET SINGLE MAINTENANCE ---
    private ResponseDto handleGetMaintenance(RequestDto request) {
        try {
            if (request.getToken() == null || request.getToken().isEmpty()) {
                return new ResponseDto(false, "Unauthorized", null);
            }

            DeleteCarRequestDto dto = gson.fromJson(request.getData(), DeleteCarRequestDto.class);
            Car car = carService.getCarById(dto.getId());

            if (car == null) {
                return new ResponseDto(false, "Car not found", null);
            }

            CarResponseDto response = toResponseDto(car);
            return new ResponseDto(true, "Car retrieved successfully", gson.toJson(response));
        } catch (Exception e) {
            System.out.println("Error in handleGetCar: " + e.getMessage());
            throw e;
        }
    }

    // --- Helper method ---
    private MaintenanceResponseDto toResponseDto(Maintenance maintenance) {
        var owner =  new UserResponseDto(
                maintenance.getCarMaintenance().getId(),
                maintenance.getCarMaintenance().getMake(),
                maintenance.getCarMaintenance().,
                maintenance.getCarMaintenance().getRole(),
                maintenance.getCarMaintenance().getCreatedAt().toString(),
                maintenance.getCarMaintenance().getUpdatedAt().toString()
        );

        return new CarResponseDto(
                maintenance.getId(),
                maintenance.getMake(),
                maintenance.getModel(),
                maintenance.getYear(),
                owner,
                maintenance.getCreatedAt().toString(),
                maintenance.getUpdatedAt().toString()
        );
    }
}
