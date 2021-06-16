package at.ac.fhcampuswien.newsanalyzer.ctrl;

import at.ac.fhcampuswien.newsanalyzer.Exception.NewsApiException;
import at.ac.fhcampuswien.newsapi.NewsApi;
import at.ac.fhcampuswien.newsapi.NewsApiBuilder;
import at.ac.fhcampuswien.newsapi.beans.Article;
import at.ac.fhcampuswien.newsapi.beans.NewsResponse;
import at.ac.fhcampuswien.newsapi.enums.Category;
import at.ac.fhcampuswien.newsapi.enums.Country;
import at.ac.fhcampuswien.newsapi.enums.Endpoint;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Controller {

	public static final String APIKEY = "86660de6b43c44a4b89893a8f032f407";  //TODO add your api key
	//public static final String APIKEY = "8666sfaasf0de6b43c44a4b89893a8f032f407";

	public List<Article> process(String s,Endpoint en,Country co,Category ca) {
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

			return articles;

		}
			//TODO implement methods for analysis
		}catch (NewsApiException e){
			//System.out.println("It didn't work");
			System.out.println(e.getMessage());
		}
		System.out.println("End process");

		return null;

	}

	public void analyze1(List<Article> a){
		long e=a.stream().count();
		System.out.println(e);
	}

	public void analyze2(List<Article> a){
	}


	public void analyze3(List<Article> a){
		Article names = a.stream().filter(article -> article.getAuthor()!=null).sorted(Comparator.comparing(article -> article.getAuthor().length())).findFirst().orElse(new Article());
		System.out.println(names.getAuthor());
	}


	public void analyze4(List<Article> a){
		a.stream().sorted(Comparator.comparingInt(article ->article.getTitle().length())).collect(Collectors.toList());

	}

	public Object getData() {
		
		return null;
	}
}

