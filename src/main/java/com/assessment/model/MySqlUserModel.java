package com.assessment.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class MySqlUserModel {
    private Long id;
    @NonNull
    @NotNull
    @Length(min = 3, max = 100)
    private String name;
    @NonNull
    @NotNull
    @Email
    private String email;
}
