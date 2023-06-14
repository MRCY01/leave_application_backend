// package com.example.leaveApp.jwt;
//
// import io.jsonwebtoken.Claims;
// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import lombok.NonNull;
// import lombok.RequiredArgsConstructor;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;
//
// import java.io.IOException;
//
// @Component
// @RequiredArgsConstructor
// public class JWTAuthFilter extends OncePerRequestFilter {
//
//    @Autowired
//    JWTUtil jwtUtil;
//    @Autowired
//    private final UserDetailsService userDetailsService;
//
//    @Override
//    protected void doFilterInternal(
//            @NonNull HttpServletRequest request,
//            @NonNull HttpServletResponse response,
//            @NonNull FilterChain filterChain
//    ) throws ServletException, IOException {
//
//        final String header = request.getHeader("Authorization");
//        final String token;
//        final String userId;
//        //checking Jwt token
//        if(header==null || !header.startsWith("Bearer ")){
//            filterChain.doFilter(request,response);
//            return;
//        }
//        //extract token if true
//        token = header.substring(7);
//
//        userId = jwtUtil.extractUserId(token);
//        String userEmail = jwtUtil.extractUserEmail(token);
//
//        if(userEmail!=null&& SecurityContextHolder.getContext().getAuthentication() ==null){
//            UserDetails userDetails =
//        }
// //        String token = extractToken(request);
// //        try{
// //            if(token!=null && validateToken(token)){
// //                // If the token is valid, set the user in the Spring Security context
// //                Claims claims = JWTUtil.parseToken(token);
// //                String userId = claims.getSubject();
// //                String userName = claims.get("role", String.class);
// //
// //                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userId,null,null);
// //                auth.setDetails(userName);
// //                SecurityContextHolder.getContext().setAuthentication(auth);
// //            }
// //        }catch(Exception e){
// //            System.out.println(e);
// //        }
// //
// //        //proceed with the requests
// //        filterChain.doFilter(request,response);
// //    }
//
//    //extracting Authorization header
// //    private String extractToken(HttpServletRequest request) {
// //        String header = request.getHeader("Authorization");
// //        //check jwtToken
// //        if(header!=null && header.startsWith("Bearer ")){
// //            return header.substring(7);
// //        }
// //        return null;
// //    }
// //
// //    private boolean validateToken(String token){
// //        //logic to validate token signature and expiration
// //        return true;
//    }
// }
