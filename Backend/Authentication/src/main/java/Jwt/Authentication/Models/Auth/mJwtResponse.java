package Jwt.Authentication.Models.Auth;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class mJwtResponse {
    private String jwtToken;
    private int UserId;
    private String FullName;

    private  String ProfilePicture;

    public mJwtResponse(String jwtToken, int userId, String fullName, String profilePicture) {
        this.jwtToken = jwtToken;
        UserId = userId;
        FullName = fullName;
        ProfilePicture = profilePicture;
    }
}
