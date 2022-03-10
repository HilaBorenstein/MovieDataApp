import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


/****************************************************************
 * Class Movie
 * Will save movie data
 * will get JSON String and extract the relevant attributes
 * The Smarter way is to use JSON 3-rd library but I asked to to use them
 * So i parsed manually
 * 
 * @author Borenstein
 *
 ******************************************/

public class Movie {
	
	private String id;
	private String title;
	private String poster_url;
	private double popularity;
	
	//will create url connection and extract the json data format
	
	public Movie (String id) throws IOException 
	{	
		this.id=id;
		 URL url = new URL("http://api.themoviedb.org/3/movie/"+id+"?api_key=9f2d8bb7775c222ca3fec9927f941e6e");
         HttpURLConnection con = (HttpURLConnection) url.openConnection();
         con.setDoOutput(true);
         con.setRequestMethod("GET");
         con.setRequestProperty("Content-Type", "application/json");

         try(BufferedReader br = new BufferedReader(  new InputStreamReader(con.getInputStream(), "utf-8"))) {         	
       		    StringBuilder response = new StringBuilder();
       		    String responseLine = null;
       		    while ((responseLine = br.readLine()) != null) {
       		        response.append(responseLine.trim());
       		    }
       		    String json=response.toString();
       		    //System.out.println("json:"+json+""); 
                LoadTitle(json);
                LoadPoster(json);
                DownloadPoster();
                LoadPopularity(json);  
                // read the url

       		 br.close(); 
       		}
         /*
         catch(IOException ex){
             System.out.println("ERROR!! Wrong id. try again ... " );
         }*/
         
         con.disconnect();
	}
	

	
	
	private  void LoadTitle (String s) {
	    String title="original_title";
		if (!s.contains(title)) 
			System.out.println("title error");
		int from=s.indexOf(title)+17; //to skip the "original_title": 
		StringBuilder sb= new StringBuilder();
		while (s.charAt(from)!='"')
			{
				sb.append(s.charAt(from));
				from++;
			}		
		this.title=sb.toString();
	}
	
	//will save the poster url
	private  void LoadPoster (String s) {
	    String poster="backdrop_path";
		if (!s.contains(poster)) 
			System.out.println("poster error");
		int from=s.indexOf(poster)+16; //to skip the "backdrop_path": 
		StringBuilder sb= new StringBuilder();
		sb.append("https://image.tmdb.org/t/p/original/");
		while (s.charAt(from)!='"')
			{
				sb.append(s.charAt(from));
				from++;
			}		
		this.poster_url=sb.toString();
	}

	//will save the title fron the json string
	private  void LoadPopularity (String s) {
	    String popularity="popularity";
		if (!s.contains(popularity)) 
			System.out.println("popularity error");
		int from=s.indexOf(popularity)+12; //to skip the "popularity": 
		StringBuilder sb= new StringBuilder();
		while (s.charAt(from)!=',')
			{
				sb.append(s.charAt(from));
				from++;
			}		
		//System.out.println("pop:"+sb.toString()+""); 
		this.popularity=Double.valueOf(sb.toString());
	
	}
	//this will remove from the movie title special charcters so i can save it as image name
	private  String RemoveSpecialCharacters () {
		StringBuilder sb= new StringBuilder();
		for (int i = 0; i <this.title.length(); i++)
		{
			if((this.title.charAt(i)==':')||(this.title.charAt(i)=='/')||(this.title.charAt(i)=='*')||(this.title.charAt(i)=='?')||(this.title.charAt(i)=='"')||(this.title.charAt(i)=='<')||(this.title.charAt(i)=='>'))
				sb.append(" ");
			else
				sb.append(this.title.charAt(i));
		}

		return sb.toString();
				
	}

	//methos to download poster to c:// temp
	private  void DownloadPoster () throws IOException {
		
		try {
		URL url = new URL(this.poster_url);
		String new_title=RemoveSpecialCharacters();
		InputStream in = url.openStream();
		Files.copy(in, Paths.get("C:\\temp\\"+new_title+".jpg"), StandardCopyOption.REPLACE_EXISTING);
		in.close();
		}
		catch(IOException ex)
		{
			
		}	

	}

	//getters &setters

	public String getId()
	{
		return this.id;
	}
	
	public String getTitle()
	{
		return this.title;
	}
	
	public String getPoster_url()
	{
		return this.poster_url;
	}
	
	
	public double getPopularity()
	{
		return this.popularity;
	}

	public String  toString ()
	{
		StringBuilder s=new StringBuilder();
		s.append("ID: "+this.id);
		s.append(" ,Title: "+this.title);
		s.append(" ,Popularity: "+this.popularity);
		/*s.append(" ,Poster_url: "+this.poster_url+"\n");*/
		return	(s.toString());
	}

}
