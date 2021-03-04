package com.gohenry.boot.customer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Customer {


    private Long id;


    @NotBlank
    @Size(min = 2, max = 50)
    private String firstName;


    @NotBlank
    @Size(min = 2, max = 50)
    private String lastName;


    @NotBlank
    @Size(min = 13, max = 13)
    private String ssn;
}
