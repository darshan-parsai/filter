package com.filter.filter.model;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest {
    private String email;
   private String password;
}
