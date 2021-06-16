package at.ac.fhcampuswien.newsanalyzer.Exception;

public class NewsApiException extends Exception{
    public NewsApiException(String errorMessage){
        super(errorMessage);
    }
}
