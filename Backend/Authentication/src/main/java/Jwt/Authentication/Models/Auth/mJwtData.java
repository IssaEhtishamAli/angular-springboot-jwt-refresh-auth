package Jwt.Authentication.Models.Auth;

import lombok.Data;
import org.springframework.context.annotation.Scope;

@Scope(scopeName = "prototype")
@Data
public class mJwtData {
    private String Email;
    private String Password;
    private String FullName;
    private int UserId;

    private String ProfilePicture;
}
