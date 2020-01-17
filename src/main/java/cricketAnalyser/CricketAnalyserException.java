package cricketAnalyser;

public class CricketAnalyserException extends Exception {

    public ExceptionType type;

    public enum ExceptionType {
        CENSUS_FILE_PROBLEM, INCORRECT_FILE_DATA, FILE_NOT_FOUND, NO_CRICKET_DATA
    }

    public CricketAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

    public CricketAnalyserException(String message, ExceptionType type, Throwable cause) {
        super(message, cause);
        this.type = type;
    }
}
