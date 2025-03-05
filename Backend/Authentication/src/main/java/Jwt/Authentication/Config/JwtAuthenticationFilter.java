package Jwt.Authentication.Config;

import Jwt.Authentication.Models.Auth.mJwtData;
import Jwt.Authentication.Utill.JwtUtilRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtilRepository jwtutil;
    @Autowired
    private mJwtData UserInfoObj;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestTokenHeader=request.getHeader("Authorization");
        String email=null;
        String jwtToken=null;
        if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer ")){
            jwtToken=requestTokenHeader.substring(7);
            //Getting UserName from jwt token

            try{
                boolean IsExpired = jwtutil.isTokenExpired(jwtToken);
                if(!IsExpired) {
                    Object UserInfo = jwtutil.extractAllClaims(jwtToken).get("UserInfo");
                    HashMap<String, Object> UserInfoMap = (HashMap<String, Object>) UserInfo;
//                    UserInfoObj.setUserId(UserInfoMap.get("userId").toString());
                    UserInfoObj.setEmail(UserInfoMap.get("email").toString());
                    UserInfoObj.setPassword(UserInfoMap.get("password").toString());
                    UserInfoObj.setFullName(UserInfoMap.get("fullName").toString());
                    email=jwtutil.extractUsername(jwtToken);
                    String NewToken = jwtutil.generateToken(UserInfoObj);
                    response.setHeader("Access-Control-Expose-Headers","NewToken");
                    response.setHeader("NewToken", NewToken);
                }

            }catch (Exception e){
                e.printStackTrace();
            } finally {
//                this.UserInfoObj.clear();
            }
            UserDetails userDetails = new User("issaehtishamali@gmail.com","",new ArrayList<>());
            if(email!=null && SecurityContextHolder.getContext().getAuthentication()==null){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken= new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }else{
                System.out.println("Token is not validated");
            }
        }
        filterChain.doFilter(request,response);
    }
}
