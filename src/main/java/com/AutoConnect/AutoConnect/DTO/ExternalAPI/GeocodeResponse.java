package com.AutoConnect.AutoConnect.DTO.ExternalAPI;

import java.util.List;

public class GeocodeResponse {
    private List<Feature> features;

    public GeocodeResponse() {}

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }
}

