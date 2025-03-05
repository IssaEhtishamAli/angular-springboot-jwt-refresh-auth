package Jwt.Authentication.Controllers.Admin;

import Jwt.Authentication.Models.Users.mUsers;
import Jwt.Authentication.Services.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:4200") // Apply to all methods in the controller
public class AdminController {

    @Autowired
    private IAdminService _serviceImpl;

    @GetMapping("/userspage")
    public mUsers.PaginatedResponse getEmployees(@RequestParam(defaultValue = "1") int page,
                                                 @RequestParam(defaultValue = "10") int size) {
        int totalUsers = _serviceImpl.getTotalUsers();
        int offset = (page - 1) * size;
        List<mUsers.Users> users=_serviceImpl.getPaginatedUsers(size, offset);
        return new mUsers.PaginatedResponse(totalUsers, users, page, size);
    }

}
