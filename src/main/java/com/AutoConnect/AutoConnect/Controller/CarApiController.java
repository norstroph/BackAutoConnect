package com.AutoConnect.AutoConnect.Controller;


import com.AutoConnect.AutoConnect.DTO.DataDTO;
import com.AutoConnect.AutoConnect.Service.RestTemplateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/car")
public class CarApiController {

    private final RestTemplateService restTemplateService;

    public CarApiController(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    @GetMapping("/mark")
    public ResponseEntity<DataDTO> getMarkCarApi() {
        return ResponseEntity.ok(restTemplateService.getMarkCarApi());
    }
}
