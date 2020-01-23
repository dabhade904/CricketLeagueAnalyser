package cricketAnalyser;

public class CricketAnalyserException extends Exception {

    public ExceptionType type;

    public enum ExceptionType {
        CRICKET_FILE_PROBLEM,
        FILE_NOT_FOUND,
        NO_DATA,
        UNABLE_TO_PARSE,
        DELIMITER_OR_HEADER_PROBLEM,
    }

    public CricketAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}
