package api.models;

import lombok.Data;

@Data
public class UserModel {
    int id;
    String email, first_name, last_name, avatar;
}
