package com.odpjr.springbootplayground.timeservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/time")
public class TimeController {


    @GetMapping("/timestamp")
    public String getCurrentTimestamp() {
        System.out.println("getCurrentTimestamp");
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    @GetMapping("/dayofweek")
    public String getDayOfWeek() {
        System.out.println("getDayOfWeek");
        return new SimpleDateFormat("EEEE").format(new Date());
    }

    @GetMapping("/month")
    public String getMonth() {
        System.out.println("getMonth");
        return new SimpleDateFormat("MMMM").format(new Date());
    }
}
