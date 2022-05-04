package zw.co.booker.bookstore.exception;

public class RecordNotFound extends RuntimeException {


    public RecordNotFound(String message) {
        super(message);
    }

    public RecordNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
