package Jwt.Authentication.Controllers.Auth;

import Jwt.Authentication.Models.Auth.mJwtData;
import Jwt.Authentication.Models.Auth.mJwtResponse;
import Jwt.Authentication.Models.Auth.mLogin;
import Jwt.Authentication.Models.Auth.mSignUp;
import Jwt.Authentication.Generic.mGeneric;
import Jwt.Authentication.Services.UsersServiceImpl;
import Jwt.Authentication.Utill.JwtUtilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200") // Apply to all methods in the controller
public class AuthController {
    @Autowired
    private JwtUtilRepository jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsersServiceImpl userService;


    @PostMapping(value = "signup")
    public ResponseEntity<?> signUp(@RequestBody mSignUp.SignUp signUp){
        mGeneric.mApiResponse response = userService.signUp(signUp);
        try {
            if (response.getRespCode() == 1) {
                return ResponseEntity.ok(new mGeneric.mApiResponse(1,"User Register Successfully",response));
            } else {
                return new ResponseEntity<>(new mGeneric.mApiResponse<>(0, "User Not Found",""), HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
    @PostMapping(value = "signin")
    public  ResponseEntity<?> signin(@RequestBody mLogin userLogin){
        try {
            Authentication AuthResp = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getEmail(),userLogin.getPassword()));
            mJwtData UserInfo = userService.getUserDetailsForJwtToken(userLogin.getEmail());
            UserInfo.setEmail(userLogin.getEmail());
            String picture=UserInfo.getProfilePicture();
            if(UserInfo !=null){
                String Token=this.jwtUtil.generateToken(UserInfo);
                mJwtResponse response= new mJwtResponse(Token,UserInfo.getUserId(),UserInfo.getFullName(),picture);
                return ResponseEntity.ok(new mGeneric.mApiResponse(1,"User Login Successfully",response));
            }
            else {
                return new ResponseEntity<>(new mGeneric.mApiResponse<>(0, "User Not Found",""), HttpStatus.NOT_FOUND);
            }
        }catch (BadCredentialsException e){
            ErrorResponseException errorResponse = new ErrorResponseException(HttpStatus.BAD_REQUEST);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }catch (Exception e){
            ErrorResponseException errorResponse = new ErrorResponseException(HttpStatus.BAD_REQUEST);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
}
