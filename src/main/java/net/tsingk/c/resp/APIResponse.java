package net.tsingk.c.resp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude
public class APIResponse {

    @JsonProperty("error")
    private int error;

    @JsonProperty("result")
    private int result;


    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
