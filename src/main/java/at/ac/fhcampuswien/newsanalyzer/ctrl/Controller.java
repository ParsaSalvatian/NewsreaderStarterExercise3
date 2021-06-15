package at.ac.fhcampuswien.newsanalyzer.ctrl;

import at.ac.fhcampuswien.newsapi.NewsApi;
import at.ac.fhcampuswien.newsapi.NewsApiBuilder;
import at.ac.fhcampuswien.newsapi.beans.Article;
import at.ac.fhcampuswien.newsapi.beans.NewsResponse;
import at.ac.fhcampuswien.newsapi.enums.Category;
import at.ac.fhcampuswien.newsapi.enums.Country;
import at.ac.fhcampuswien.newsapi.enums.Endpoint;

import java.util.List;

public class Controller {

	public static final String APIKEY = "86660de6b43c44a4b89893a8f032f407";  //TODO add your api key

	public void process(String s,Endpoint en,Country co,Category ca) {
		System.out.println("Start process");

		//TODO implement Error handling
		try{
		//TODO load the news based on the parameters
		NewsApi newsApi = new NewsApiBuilder()
				.setApiKey(APIKEY)
				.setQ(s)
				.setEndPoint(en)// example of how to use enums
				.setSourceCountry(co)       // example of how to use enums
				.setSourceCategory(ca) // example of how to use enums
				.createNewsApi();

		NewsResponse newsResponse = newsApi.getNews();
		if(newsResponse != null){
			List<Article> articles = newsResponse.getArticles();
			articles.stream().forEach(article -> System.out.println(article.toString()));
		}
		//TODO implement methods for analysis
		}catch (Exception e){
		}
		System.out.println("End process");
	}
	

	public Object getData() {
		
		return null;
	}
}
