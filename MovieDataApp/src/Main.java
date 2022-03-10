import java.io.*;
import java.util.Scanner;


public class Main {
	
	
	/*************************************************************************************
	 * MAIN
	 * This Program will ask the user to enter movie id from IMDB
	 * will print the movie title and at end will print the 
	 * most popular movie from the input (will not save duplicte movies)
	 * @param args
	 * @throws IOException 
	 *************************************************************************************/
	
	public static void main(String[] args) throws IOException {
		
		String input_imdb_id;
		MovieList list=new MovieList();
		//MovieList popular_movies=new MovieList();//will save the most popular movies if there is some with same rate will save them all
		Movie most_popular=null;
		
		System.out.println("Welcome to Movie data app!");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Please enter imdb id:(for exit enter 0)");

		
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) { 
        	input_imdb_id=sc.nextLine(); 
        	//break condition
        	if (input_imdb_id.equals("0"))
        		break;        	        	         
            try
            {
            	list.addMovie(new Movie(input_imdb_id));
                Movie m= list.getLastMovieAdded();
                if((most_popular==null)||(m.getPopularity()>most_popular.getPopularity()))
                	most_popular=m;
                System.out.println("Movie search response fo id:"+input_imdb_id+""); 
                System.out.println("Title: "+m.getTitle());
            }
            catch(IOException ex){
                System.out.println("ERROR!! Wrong id. try again ... " );
            }

      
            System.out.println("\nPlease enter imdb id:(for exit enter 0)");   
        } 
        
        if(list.getListSize()>0)
        {
        	System.out.println("\n***The most popular movie in the list:***\n"+most_popular);
        }

        //System.out.println("Movie list\n"+list);
        sc.close();
        
/***Example to run: ******
tt0137523
tt0241527
tt0277027
tt0111161
tt3659388
tt0167261
tt1877830
tt0974015
tt0068646
tt0108052
tt0118799
tt0120689
tt0076759
tt0088763
tt0101414
0
*/
        		

	}
		

}
