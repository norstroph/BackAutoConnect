package com.AutoConnect.AutoConnect.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceDTO {
    private Long id;
    @Override
    public String toString() {
        return "just a service" + id + "}";
    }
}
