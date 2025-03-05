package Jwt.Authentication.Models.Users;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class mUsers {

    @Data
    @NoArgsConstructor
    public static class Users{
        private int Id;
        private String Name;
        private String Email;
        private String Address;
        private String Password;
        private String Phone;
        private String Cnic;
        private String Gender;
        private String Dob;

        public Users(int id,String name, String email, String address, String password, String phone, String cnic,String gender,String dob){
            Id= id;
            Name = name;
            Email = email;
            Address = address;
            Password = password;
            Phone = phone;
            Cnic = cnic;
            Gender = gender;
            Dob = dob;
        }
    }
    @Data
    public static class GetUserForAuthentication{
        private String Email;
        private String Password;
        private String UserName;
        public GetUserForAuthentication(String email,String password,String userName){
            Email = email;
            Password = password;
            UserName = userName;
        }
        public GetUserForAuthentication(){}
    }
    @Data
    public  static class PaginatedResponse {
        private int TotalElements;
        private List<Users> Content;
        private int CurrentPage;
        private int PageSize;
        public PaginatedResponse(int totalElements,List<Users> content,int currentPage,int pageSize){
        TotalElements = totalElements;
        Content = content;
        CurrentPage = currentPage;
        PageSize = pageSize;
        }
    }
}
