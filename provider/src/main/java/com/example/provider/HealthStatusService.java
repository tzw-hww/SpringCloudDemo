package com.example.provider;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Service;

@Service
public class HealthStatusService implements HealthIndicator {

    private boolean status = true;

    public void setStatus(boolean status) {
        this.status = status;
    }


    @Override
    public Health health() {
        if(status){
            return new Health.Builder().up().build();
        }else{
            return new Health.Builder().down().build();
        }
    }
}
