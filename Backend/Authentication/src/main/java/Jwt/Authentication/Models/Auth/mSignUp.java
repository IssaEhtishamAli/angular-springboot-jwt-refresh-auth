package Jwt.Authentication.Models.Auth;

import lombok.Data;
import lombok.NoArgsConstructor;

public class mSignUp {
    @Data
    @NoArgsConstructor
    public static class SignUp{
        private int Id;
        private String FullName;
        private String Email;
        private String Password;
        private String Phone;
        private String ProfilePicture;
        public SignUp(int id,String fullName,String email,String password,String phone,String profilePicture){
            Id = id;
            FullName = fullName;
            Email = email;
            Password = password;
            Phone = phone;
            ProfilePicture = profilePicture;
        }

    }
}
