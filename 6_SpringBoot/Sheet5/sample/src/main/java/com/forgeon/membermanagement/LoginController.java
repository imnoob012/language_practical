package com.forgeon.membermanagement;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

@GetMapping("/login")
public String login() {
return "login";
}

// index.htmlは今回においては、動的なので、templatesにおいている
@GetMapping("/") 
public String index() {
return "index";
}

}