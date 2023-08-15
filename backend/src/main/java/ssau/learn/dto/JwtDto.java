package ssau.learn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class JwtDto {
    private String token;
    private Long id;
    private String login;
    private String email;
    private String role;
}
