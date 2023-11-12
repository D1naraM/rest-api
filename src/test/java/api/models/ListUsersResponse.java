package api.models;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ListUsersResponse {
    int page;
    int per_page;
    int total;
    int total_pages;
    ArrayList<UserModel> data;
    SupportModel support;
}