package zw.co.booker.bookstore.exception;

public class RecordExistException extends RuntimeException {

    public RecordExistException(String message) {
        super(message);
    }

    public RecordExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
