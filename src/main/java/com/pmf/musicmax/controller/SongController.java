package com.pmf.musicmax.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pmf.musicmax.model.Category;
import com.pmf.musicmax.model.Comment;
import com.pmf.musicmax.model.Song;
import com.pmf.musicmax.model.User;
import com.pmf.musicmax.repository.SongRepo;

@Controller
@RequestMapping(value="song")
public class SongController {

	@Autowired
	SongRepo sr;
	
	@RequestMapping(value="getAllSongs", method=RequestMethod.GET)
	public String getAllSongs(Model m, HttpServletRequest request){
		List<Song> songs = sr.getAllSongs();
		request.getSession().setAttribute("songs", songs);
		m.addAttribute("songs", songs);
		
		//For table
		request.getSession().setAttribute("songsTable", songs);
		m.addAttribute("songsTable", songs);
		
		//For select box
		List<Category> categories = sr.getAllCategories();
		request.getSession().setAttribute("categories", categories);
		m.addAttribute("categories", categories);

		List<String> titles = sr.getAllTitles();
		request.getSession().setAttribute("titles", titles);
		m.addAttribute("titles", titles);
		
		List<String> authors = sr.getAllAuthors();
		request.getSession().setAttribute("authors", authors);
		m.addAttribute("authors", authors);
		
		List<String> artists = sr.getAllArtists();
		request.getSession().setAttribute("artists", artists);
		m.addAttribute("artists", artists);
		
		List<Integer> releaseYears = sr.getAllReleaseYears();
		request.getSession().setAttribute("releaseYears", releaseYears);
		m.addAttribute("releaseYears", releaseYears);
		
		return "Song/manageSongs";
	}
	
	//Called when button add song is clicked
	@RequestMapping(value="getAllCategories", method=RequestMethod.GET)
	public String getAllCategories(Model m, HttpServletRequest request){
		
		List<Category> categories  = sr.getAllCategories();
		
		request.getSession().setAttribute("categories", categories);
		m.addAttribute("categories", categories);
		
		return "Song/addSong";
	}
	
	@RequestMapping(value="getAllSongsByParameter", method=RequestMethod.POST)
	public String getAllSongsByParameter(Model m, HttpServletRequest request){		
		List<Song> songsTable = new ArrayList<>();
		
		int categoryId = Integer.parseInt(request.getParameter("category"));
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String artist = request.getParameter("artist");
		int releaseYear = Integer.parseInt(request.getParameter("releaseYear"));
		
		
		songsTable = sr.getAllSongsByParameter2(categoryId, title, author, artist, releaseYear);
		
		if(songsTable.size()==0){
			return "redirect:/song/getAllSongs";
		}else{
			//Updating song Table
			request.getSession().setAttribute("songsTable", songsTable);
			m.addAttribute("songsTable", songsTable);
			return "Song/manageSongs";
		}
	}
	
	@RequestMapping(value="addSong", method=RequestMethod.POST)
	public String addSong(Model m, HttpServletRequest request){
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String artist = request.getParameter("artist");
		int releaseYear = Integer.parseInt(request.getParameter("releaseYear"));
		Song s = new Song();
		s.setTitle(title);
		s.setAuthor(author);
		s.setArtist(artist);
		s.setReleaseYear(releaseYear);
		//Check if user input title and author
		int i=0;
		if(title==""){
			request.getSession().setAttribute("message1", "Title Missing");
			i++;
		}else{
			request.getSession().setAttribute("message1", "");
		}
		if(author==""){
			request.getSession().setAttribute("message2", "Author Missing");
			i++;
		}else{
			request.getSession().setAttribute("message2", "");
		}
		if(i>0){
			request.getSession().setAttribute("message", "");
			request.getSession().setAttribute("song", s);
			m.addAttribute("song", s);
			return "Song/addSong";
		}
		User user = (User) request.getSession().getAttribute("user");
		int userId = user.getId();
		
		Song songg = sr.addSong(categoryId, title, author, artist, releaseYear, userId);
		if(songg==null){
			request.getSession().setAttribute("message", "");
			return "redirect:/song/getAllSongs";
		}
		request.getSession().setAttribute("message", "Song already exists in database");
		return "Song/addSong";
	}
	
	@RequestMapping(value="getAllCommentsBySongById", method=RequestMethod.GET)
	public String getAllCommentsBySongById(Model m, HttpServletRequest request){
		int songId = Integer.parseInt(request.getParameter("id"));

		Song song = sr.getSongById(songId);
		request.getSession().setAttribute("song", song);
		m.addAttribute("song", song);
		
		List<Comment> comments  = sr.getAllCommentsBySongById(songId);
		request.getSession().setAttribute("comments", comments);
		m.addAttribute("comments", comments);//It will be seen without attribute
		
		return "Song/addComment";
	}
	
	@RequestMapping(value="addCommentBySongAndUserId", method=RequestMethod.POST)
	public String addCommentBySongAndUserId(Model m, HttpServletRequest request){
		Song song = (Song) request.getSession().getAttribute("song");
		int songId = song.getId();
		User user = (User) request.getSession().getAttribute("user");
		int userId = user.getId();
		String text = request.getParameter("text");
		System.out.println(songId);
		System.out.println("userId = " + userId);
		
		Comment commented = sr.addCommentBySongAndUserId(text, userId, songId);
		
		//Update comments list in session
		List<Comment> comments  = sr.getAllCommentsBySongById(songId);
		request.getSession().setAttribute("comments", comments);
		m.addAttribute("comments", comments);
		
		m.addAttribute("song", song);//To be seen when page is refrehsed
		
		return "Song/addComment";
	}
	
	@ModelAttribute("song")
	public Song getSong(){
		return new Song();
	}
		
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    sdf.setLenient(true);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}
	
}
