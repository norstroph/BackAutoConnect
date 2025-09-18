package com.AutoConnect.AutoConnect.DTO;

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

class Properties {
    private String label;

    public Properties() {}

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
