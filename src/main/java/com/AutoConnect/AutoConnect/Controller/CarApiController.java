package com.AutoConnect.AutoConnect.Controller;



import com.AutoConnect.AutoConnect.DTO.DataDTO;
import com.AutoConnect.AutoConnect.DTO.YearsDTO;
import com.AutoConnect.AutoConnect.Service.RestTemplateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/car")
public class CarApiController {

    private final RestTemplateService restTemplateService;

    public CarApiController(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    @GetMapping("/mark")
    public ResponseEntity<List<DataDTO>> getMarkCarApi() {
        return ResponseEntity.ok(restTemplateService.getMarkCarApi());
    }

    @GetMapping("/model")
    public ResponseEntity<List<DataDTO>> getModelCarApi(@RequestParam String make) {
        return ResponseEntity.ok(restTemplateService.getModelCarApi(make));
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/year")
    public ResponseEntity<YearsDTO[]> getYearCarApi(@RequestParam String make, @RequestParam String model) {
        return ResponseEntity.ok(restTemplateService.getYearCarApi(make, model));
    }


}
