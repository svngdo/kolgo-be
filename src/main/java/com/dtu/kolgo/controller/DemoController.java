package com.dtu.kolgo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("demo")
public class DemoController {

    @GetMapping
    public String demo() {
        return "Hello user";
    }

    @GetMapping("admin")
    public String demoAdmin() {
        return "Hello ADMIN";
    }

    @GetMapping("kol")
    public String demoKol() {
        return "Hello KOL";
    }

    @GetMapping("ent")
    public String demoEnterprise() {
        return "Hello Enterprise";
    }

}
