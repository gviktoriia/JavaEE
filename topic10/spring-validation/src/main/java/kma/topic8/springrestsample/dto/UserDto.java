package kma.topic8.springrestsample.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDto {

    @NotEmpty(message = "Login can't be empty")
    @Pattern(regexp = "^[a-z]{5,10}$", message = "Login has bad format")
    private String login;

    @NotEmpty(message = "Name can't be empty")
    @Size(max = 10, message = "Name should have up to 10 symbols")
    private String name;

    @NotNull(message = "Age is required")
    @Min(value = 0, message = "Age can't be negative")
    @Max(value = 100, message = "You are too old :)")
    private Integer age;

    @NotNull(message = "Salary is required")
    @Digits(integer = 2, fraction = 2, message = "Salary is bad formatted")
    // one of: 11, 11.1, 11.11
    private Double salary;
}
