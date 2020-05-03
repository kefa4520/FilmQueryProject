package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {
  
	DatabaseAccessor db = new DatabaseAccessorObject();

  public static void main(String[] args) throws SQLException {
	  Scanner input = new Scanner(System.in);
	  FilmQueryApp app = new FilmQueryApp();
      app.launch(input);
  }


  private void launch(Scanner input) throws SQLException { 
    System.out.println("Choose from the following options");
    System.out.println("1. Look up a film by its id");
    System.out.println("2. Look up a film by a search keyword");
    System.out.println("3. Exit the application");
    System.out.println("Entry: ");
    startUserInterface(input);
    
  }

  private void startUserInterface(Scanner input) throws SQLException {
    String choice = input.next();
    
    while(true) {
    	 if (choice.equals("1")) {
             System.out.println("Enter film id: ");
             int enteredFilmId = input.nextInt();
            
             Film film = db.findFilmById(enteredFilmId);
             if (film == null) {
                 System.out.println("No films found");
             } else {
                 System.out.println(film);
             }
             launch(input);
         } 
    	
    	if (choice.equals("2")) {
            System.out.println("Enter the keyword. \nEntry: ");
            String keyword = input.next();
            List<Film> films = db.findFilmsByKeyword(keyword);
            if(films.size()==0) {
                System.out.println("No films found");
            }else {
                for (Film film : films) {
                    System.out.println(film);
                    
                }
            }
            
            launch(input);
        }
    	
    	if((!choice.equals("1")) && (!choice.equals("2")) && (!choice.equals("3"))) {
    		System.out.println("Wrong input. Try again.");
    		launch(input);
    	}
    	
    	if(choice.equals("3")) {
    	 System.out.println("Goodbye");
    	 break;
    	}
    	return;
    }
  }

}
