package com.pmf.musicmax.repository;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pmf.musicmax.model.Category;
import com.pmf.musicmax.model.Comment;
import com.pmf.musicmax.model.Song;
import com.pmf.musicmax.model.User;

@Repository
@Transactional
public class SongRepo {

	@PersistenceContext
	EntityManager em;
	
	public List<Song> getAllSongs(){
		List<Song> songs = new ArrayList<Song>();
		try{
			Query q = em.createQuery("select s from Song s");
			songs = q.getResultList();
			return songs;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public List<Category> getAllCategories() {
		List<Category> categories = new ArrayList<Category>();
		try{
			Query q = em.createQuery("select c from Category c");
			categories = q.getResultList();
			return categories;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public List<String> getAllTitles() {
		List<String> titles = new ArrayList<String>();
		try{
			Query q = em.createQuery("select distinct s.title from Song s");
			titles = q.getResultList();
			return titles;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public List<String> getAllAuthors() {
		List<String> authors = new ArrayList<String>();
		try{
			Query q = em.createQuery("select distinct s.author from Song s");
			authors = q.getResultList();
			return authors;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public List<String> getAllArtists() {
		List<String> artists = new ArrayList<String>();
		try{
			Query q = em.createQuery("select distinct s.artist from Song s");
			artists = q.getResultList();
			return artists;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Integer> getAllReleaseYears() {
		List<Integer> releaseYears = new ArrayList<Integer>();
		try{
			Query q = em.createQuery("select distinct s.releaseYear from Song s");
			releaseYears = q.getResultList();
			return releaseYears;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public List<Song> getAllSongsByParameter(int categoryId, String title, String author, String artist, int releaseYear) {
		List<Song> songs = new ArrayList<Song>();
		try{
			Query q = em.createQuery("select s from Song s where s.category.id = :categoryId or s.title like :title or s.author like :author or s.artist like :artist or s.releaseYear like :releaseYear");
			q.setParameter("categoryId", categoryId);
			q.setParameter("title", title);
			q.setParameter("author", author);
			q.setParameter("artist", artist);
			q.setParameter("releaseYear", releaseYear);
				
			songs = q.getResultList();
			return songs;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public Song addSong(Song song, int userId) {
		Song s = null;
		String title = song.getTitle();
		String author = song.getAuthor();
		int releaseYear = song.getReleaseYear();
		try{
			Query q = em.createQuery("select s from Song s where s.title like :title and s.author like :author and s.releaseYear like :releaseYear");
			q.setParameter("title", title);
			q.setParameter("author", author);
			q.setParameter("releaseYear", releaseYear);
			try{
				s = (Song) q.getSingleResult();
			}catch(Exception e){}
			if(s==null){
				System.out.println("Uspesno");
				em.persist(song);
				return null;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return s;
	}
	
	public Song addSong(int categoryId, String title, String author, String artist, int releaseYear, int userId) {
		Song s = null;
		Song song = new Song();
		Category c = em.find(Category.class, categoryId);
		song.setCategory(c);
		User user = em.find(User.class, userId);
		song.setUser(user);
		song.setTitle(title);
		song.setAuthor(author);
		song.setArtist(artist);
		song.setReleaseYear(releaseYear);
		
		try{
			Query q = em.createQuery("select s from Song s where s.title like :title and s.author like :author and s.releaseYear like :releaseYear");
			q.setParameter("title", title);
			q.setParameter("author", author);
			q.setParameter("releaseYear", releaseYear);
			try{
				s = (Song) q.getSingleResult();
			}catch(Exception e){}
			if(s==null){
				System.out.println("Uspesno");
				user.setSongCount(user.getSongCount()+1);
				em.merge(user);
				em.persist(song);
				return null;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return s;
	}

	
	public Song getSongById(int id) {
		Song song = em.find(Song.class, id);
		return song;
	}

	public List<Comment> getAllCommentsBySongById(int songId) {
		List<Comment> comments = new ArrayList<>();
		try {
			Query q = em.createQuery("select c from Comment c where c.song.id = :songId");
			q.setParameter("songId", songId);
			comments = q.getResultList();
			return comments;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Comment addCommentBySongAndUserId(String text, int userId, int songId) {
		Comment c = new Comment();
		c.setText(text);
		User user = em.find(User.class, userId);
		c.setUser(user);
		Song song = em.find(Song.class, songId);
		c.setSong(song);
		
		em.persist(c);
				
		return c;
	}
	
	public List<Song> getAllSongsByParameter2(int categoryId, String title, String author, String artist, int releaseYear) {
		List<Song> songs = new ArrayList<Song>();
		String query = "select s from Song s ";
		String addQuery = " where ";
		
		int i = 0;
		if(categoryId!=0) {
			addQuery += "s.category.id = :categoryId ";
			i++;
		}
		if(!title.equals("0")) {
			if(i>0) {
				addQuery += "and s.title like :title ";
			}else {
				addQuery += "s.title like :title ";
			}
			i++;
		}
		if(!author.equals("0")) {
			if(i>0) {
				addQuery += "and s.author like :author ";
			}else {
				addQuery += "s.author like :author ";
			}
			i++;
		}
		if(!artist.equals("0")) {
			if(i>0) {
				addQuery += "and s.artist like :artist ";
			}else {
				addQuery += "s.artist like :artist ";
			}
			i++;
		}
		if(releaseYear!=0) {
			if(i>0) {
				addQuery += "and s.releaseYear like :releaseYear ";
			}else {
				addQuery += "s.releaseYear like :releaseYear ";
			}
			i++;
		}
		if(i>0) {
			query += addQuery;
		}
		
		try{
			Query q = em.createQuery(query);
			if(categoryId!=0) {
				q.setParameter("categoryId", categoryId);
			}
			if(!title.equals("0")) {
				q.setParameter("title", title);
			}
			if(!author.equals("0")) {
				q.setParameter("author", author);
			}
			if(!artist.equals("0")) {
				q.setParameter("artist", artist);
			}
			if(releaseYear!=0) {
				q.setParameter("releaseYear", releaseYear);
			}
				
			songs = q.getResultList();
			return songs;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	
}
