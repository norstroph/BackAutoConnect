package com.AutoConnect.AutoConnect.Controller;

import com.AutoConnect.AutoConnect.Entity.Services;
import com.AutoConnect.AutoConnect.Repository.ServiceRepository;
import com.AutoConnect.AutoConnect.Service.RepairService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServicesController {
   private final RepairService repairService;

   public ServicesController(RepairService repairService){
       this.repairService = repairService;
   }

   @GetMapping
    public ResponseEntity<List<Services>> getAllServices(){
       return new ResponseEntity<>(repairService.getAllService(), HttpStatus.OK);
   }

}
