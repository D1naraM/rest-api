package api.models;

import lombok.Data;

@Data
public class RegisterBody {
    String email, password;
}
