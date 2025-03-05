package Jwt.Authentication.Controllers.UserManagement;


import Jwt.Authentication.Models.Auth.mJwtData;
import Jwt.Authentication.Models.Users.mUsers;
import Jwt.Authentication.Services.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200") // Apply to all methods in the controller
public class UsersController {
    @Autowired
    private UsersServiceImpl userService;

    @GetMapping("/getUserDetails")
    public mJwtData getUserDetailsForJwtToken(@RequestParam String email){
        return userService.getUserDetailsForJwtToken(email);
    }
}
