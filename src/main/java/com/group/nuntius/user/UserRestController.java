package com.group.nuntius.user;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserRestController {

    @RequestMapping("/info")
    public String hello() {
        return "Userinfo";
    }

}
