package crud.blog.dto;

import crud.blog.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserRequest {
    
    private String email;
    private String password;
}
