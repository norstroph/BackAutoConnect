package com.AutoConnect.AutoConnect.Mapper;

import com.AutoConnect.AutoConnect.DTO.CoordinateDTO;
import com.AutoConnect.AutoConnect.DTO.ExternalAPI.Feature;
import com.AutoConnect.AutoConnect.DTO.ExternalAPI.GeocodeResponse;
import com.AutoConnect.AutoConnect.DTO.ExternalAPI.Geometry;
import com.AutoConnect.AutoConnect.Entity.Garage;

public class CoordinateMapper {
    public static CoordinateDTO geocodeResponseToCoordinateDTO(GeocodeResponse geocodeResponse){
        Feature newFeature = geocodeResponse.getFeatures().getFirst();
        Geometry geometry = newFeature.getGeometry();
        Double longitude = geometry.getCoordinates().getFirst();
        Double latitude = geometry.getCoordinates().get(1);

        CoordinateDTO coordinateDTO = new CoordinateDTO();
        coordinateDTO.setLongitude(longitude);
        coordinateDTO.setLatitude(latitude);
        return coordinateDTO;
    }
}
