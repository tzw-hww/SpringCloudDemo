package com.example.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @Autowired
    HealthStatusService healthStatusService;

    @RequestMapping("/health")
    public String getHealth(@RequestParam("status") Boolean status){

        healthStatusService.setStatus(status);
        return status.toString();
    }
}
