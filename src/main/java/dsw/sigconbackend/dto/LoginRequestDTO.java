package dsw.sigconbackend.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class LoginRequestDTO {
    private String email;
    private String password;
}
