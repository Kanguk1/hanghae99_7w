package clone.colley.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter

public class MyPostDto {

    private String user_profile;
    @NotNull
    private String nickname;
    private String introduce;

}