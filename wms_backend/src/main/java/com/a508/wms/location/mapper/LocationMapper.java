package com.a508.wms.location.mapper;

import com.a508.wms.floor.mapper.FloorMapper;
import com.a508.wms.location.domain.Location;
import com.a508.wms.location.dto.LocationRequestDto;
import com.a508.wms.location.dto.LocationResponseDto;
import com.a508.wms.warehouse.domain.Warehouse;
import org.springframework.stereotype.Component;

@Component
public class LocationMapper {

    /**
     * @param request
     * @param warehouse
     * @return
     */
    public static Location fromLocationRequestDto(LocationRequestDto request,
        Warehouse warehouse) {
        return Location.builder()
            .name(request.getName())
            .rotation(request.getRotation())
            .xPosition(request.getXPosition())
            .yPosition(request.getYPosition())
            .xSize(request.getXSize())
            .ySize(request.getYSize())
            .zSize(request.getZSize())
            .warehouse(warehouse)
            .productStorageType(request.getStorageType())
            .build();
    }

    /**
     * @param location
     * @return
     */
    public static LocationResponseDto toLocationResponseDto(Location location) {
        return LocationResponseDto.builder()
            .id(location.getId())
            .xPosition(location.getXPosition())
            .yPosition(location.getYPosition())
            .xSize(location.getXSize())
            .ySize(location.getYSize())
            .zSize(location.getZSize())
            .name(location.getName())
            .storageType(location.getProductStorageType())
            .rotation(location.getRotation())
            .floorResponses(location.getFloors().stream().map(
                FloorMapper::toFloorResponseDto
            ).toList())
            .build();
    }

}
