package Sample01.Sample.security;

public enum ExceptionCode {

    // Common
    UNAUTHORIZED(401, "401", "Unauthorized"),
    UNKNOWN_ERROR(404, "C000", " Unknown Error"),
    INVALID_INPUT_VALUE(400, "C001", " Invalid Input Value"),
    METHOD_NOT_ALLOWED(405, "C002", " Invalid Input Value"),
    HANDLE_ACCESS_DENIED(403, "403", "Access is Denied"),

    // Member
    EMAIL_DUPLICATION(400, "M001", "Email is Duplication"),
    LOGIN_INPUT_INVALID(400, "M002", "Login input is invalid"),
    ;

    private final String code;
    private final String message;
    private int status;

    ExceptionCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public Object getMessage () {
        return this.message;
    }

    public Object getCode () {
        return this.code;
    }

}
