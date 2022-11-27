package com.linkho.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * com.linkho.authentication.dto
 * Created by NhatLinh - 19127652
 * Date 11/26/2022 - 9:52 AM
 * Description: ...
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDto {
    private String email;
    private String password;
}

