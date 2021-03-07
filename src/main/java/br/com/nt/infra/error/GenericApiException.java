package br.com.nt.infra.error;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GenericApiException extends Exception {

	private static final long serialVersionUID = -3759446936842974968L;
	private final ApiErrorMessage code;
    private final String reason;
    private final ApiError apiError;

    public GenericApiException(ApiErrorMessage apiErrorMessage, String reason, String message,  Throwable cause) {
        super(message, cause);
        this.code = apiErrorMessage;
        this.reason = reason;
        this.apiError= new  ApiError(this);
    }

    public GenericApiException(String message) {
        super(message);
        this.reason = message;
        this.code = ApiErrorMessage.UNKNOWN_ERROR;
        this.apiError= new  ApiError(this);
    }
}
