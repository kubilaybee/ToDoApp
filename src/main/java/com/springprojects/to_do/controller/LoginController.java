package com.springprojects.to_do.controller;

import com.springprojects.to_do.service.AuthService;
import static org.springframework.http.HttpStatus.OK;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

    private AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public ResponseEntity<String> login(ModelMap modelMap) {

        modelMap.put("username", getLoggedInUsername());
        String response = "Welcome " + getLoggedInUsername() + " !";
        return new ResponseEntity<>(response, OK);
    }

}
