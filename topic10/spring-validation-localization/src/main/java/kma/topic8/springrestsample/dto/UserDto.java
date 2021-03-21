package kma.topic8.springrestsample.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDto {

    @NotEmpty(message = "{login.empty}")
    @Pattern(regexp = "^[a-z]{5,10}$", message = "{login.bad.format}")
    private String login;

    @NotEmpty(message = "{name.required}")
    @Size(max = 15, message = "{name.bad.size}")
    private String name;

}
