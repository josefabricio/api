package com.digitalholics.iotheraphy.security.interfaces.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/general-portal")
@RequiredArgsConstructor
public class GeneralController {

    @GetMapping
    public String get(){
        return "GET:: general controller";
    }

    @PostMapping
    public String post(){
        return "POST:: general controller";
    }

    @PutMapping
    public String put(){
        return "PUT:: general controller";
    }

    @DeleteMapping
    public String delete(){
        return "DELETE:: general controller";
    }

}
