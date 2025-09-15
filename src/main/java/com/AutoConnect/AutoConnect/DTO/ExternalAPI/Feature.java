package com.AutoConnect.AutoConnect.DTO.ExternalAPI;

public class Feature {
    private Geometry geometry;
    private Properties properties;

    public Feature() {}

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
