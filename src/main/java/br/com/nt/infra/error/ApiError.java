package br.com.nt.infra.error;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiError {

    @JsonProperty("code")
    protected String code;
    @JsonProperty("reason")
    protected String reason;
    @JsonProperty("message")
    protected String message;
    @JsonIgnore
    protected Throwable throwable;

    public ApiError(GenericApiException ex) {
        this.throwable = ex.getCause();
        this.message = ex.getMessage();
        this.reason = ex.getReason();
    }
}
