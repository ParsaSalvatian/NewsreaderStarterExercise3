package at.ac.fhcampuswien.newsanalyzer.ui;


import at.ac.fhcampuswien.newsanalyzer.Exception.NewsApiException;
import at.ac.fhcampuswien.newsanalyzer.ctrl.Controller;
import at.ac.fhcampuswien.newsapi.beans.Article;
import at.ac.fhcampuswien.newsapi.enums.Category;
import at.ac.fhcampuswien.newsapi.enums.Country;
import at.ac.fhcampuswien.newsapi.enums.Endpoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class UserInterface 
{
	private Controller ctrl = new Controller();
	public List<Article> a;
	public void getDataFromCtrl1(){

		a=ctrl.process("corona", Endpoint.TOP_HEADLINES, Country.at, Category.health);
	}

	public void getDataFromCtrl2(){
		a=ctrl.process("krypto", Endpoint.TOP_HEADLINES, Country.de, Category.technology);

	}

	public void getDataFromCtrl3(){

		a=ctrl.process("a", Endpoint.TOP_HEADLINES, Country.it, Category.science);
	}
	
	public void getDataForCustomInput() {
		try{
			Scanner scan= new Scanner(System.in);
			System.out.println("Q: ");
			String o=scan.nextLine();
			System.out.println("Endpoint: ");
			Endpoint en = Endpoint.valueOf(scan.next());
			System.out.println("Country: ");
			Country co = Country.valueOf(scan.next());
			System.out.println("a");
			Category ca = Category.valueOf(scan.next());
			a=ctrl.process(o,en,co,ca);


		}catch (Exception e){
			System.out.println("PLEASE TYPE THE RIGHT KEYWORDS!!");
		}


	}

	public void getQuantityOfArticles(){
		ctrl.analyze1(a);
	}

	public void getProviderWithMostArticles(){

	}

	public void getAuthorWithShortestName(){
	ctrl.analyze3(a);
	}

	public void getLongestTitle(){

	}

	public void start() {
		Menu<Runnable> menu = new Menu<>("User Interface");
		menu.setTitle("Wählen Sie aus:");
		menu.insert("a", " news article from austria about corona", this::getDataFromCtrl1);
		menu.insert("b", " news article from germany about crypto", this::getDataFromCtrl2);
		menu.insert("c", " news article from italy about science", this::getDataFromCtrl3);
		menu.insert("d", " User Input:",this::getDataForCustomInput);
		menu.insert("e", " Quantity of articles:",this::getQuantityOfArticles);
		menu.insert("f", " Provider with the most aritcles:",this::getProviderWithMostArticles);
		menu.insert("g", " Author with the shortest name:",this::getAuthorWithShortestName);
		menu.insert("h", " Sort by longest title:",this::getLongestTitle);
		menu.insert("q", "Quit", null);
		Runnable choice;
		while ((choice = menu.exec()) != null) {
			 choice.run();
		}
		System.out.println("Program finished");
	}


    protected String readLine() {
		String value = "\0";
		BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
		try {
			value = inReader.readLine();
        } catch (IOException ignored) {
		}
		return value.trim();
	}

	protected Double readDouble(int lowerlimit, int upperlimit) 	{
		Double number = null;
        while (number == null) {
			String str = this.readLine();
			try {
				number = Double.parseDouble(str);
            } catch (NumberFormatException e) {
                number = null;
				System.out.println("Please enter a valid number:");
				continue;
			}
            if (number < lowerlimit) {
				System.out.println("Please enter a higher number:");
                number = null;
            } else if (number > upperlimit) {
				System.out.println("Please enter a lower number:");
                number = null;
			}
		}
		return number;
	}
}
