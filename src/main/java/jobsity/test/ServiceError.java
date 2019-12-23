package jobsity.test;

public abstract class ServiceError {
    public final String code;
    public final String description;

    public ServiceError(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
