package net.tsingk.c;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.tsingk.m.User;

@JsonInclude
public class UserResponse extends APIResponse {

    @JsonProperty("data")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private User user;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
