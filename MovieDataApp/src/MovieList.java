import java.util.*;

/**********************
 * I wrote special class to store ArrayList
 * Beacause this arraylist will not save duplicate movies
 * (i used hashset to store the id and to check in O(1) 
 * that the is does not exist
 * 
 * **/
public class MovieList {

	
	private ArrayList <Movie> movie_list;
	private HashSet<String> movies_id;
	
	public MovieList()
	{
		this.movie_list=new ArrayList<Movie>();
		this.movies_id=new HashSet<String>();
	}
	
	/*public ArrayList <Movie> getList()
	{
		return this.movie_list;
	}*/
	
	public boolean addMovie(Movie m)
	{
		if(m!=null)
		{
			if(!movies_id.contains(m.getId()))
			{
				movies_id.add(m.getId());
				return  this.movie_list.add(m);
			}			
		}
		return false;
			
	}
	//getters &setters
	public int getListSize()
	{
		return this.movie_list.size();
	}
	
	public Movie getLastMovieAdded()
	{
		if(movie_list.size()>=1)
			return this.movie_list.get(movie_list.size()-1);
		return null;
	}
	

	
	
	public String  toString ()
	{
		StringBuilder s=new StringBuilder();
		s.append(this.movie_list);
		return	(s.toString());
	}
	

}
