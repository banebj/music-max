package com.pmf.musicmax.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String email;

	private String firstName;

	private String lastName;

	private String password;

	private int songCount;

	private String username;

	//bi-directional many-to-one association to Comment
	@OneToMany(mappedBy="user")
	private List<Comment> comments;

	//bi-directional many-to-one association to News
	@OneToMany(mappedBy="user")
	private List<News> news;

	//bi-directional many-to-one association to Song
	@OneToMany(mappedBy="user")
	private List<Song> songs;

	//bi-directional many-to-one association to Topic
	@OneToMany(mappedBy="user")
	private List<Topic> topics;

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="roleId")
	private Role role;

	public User() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getSongCount() {
		return this.songCount;
	}

	public void setSongCount(int songCount) {
		this.songCount = songCount;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setUser(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setUser(null);

		return comment;
	}

	public List<News> getNews() {
		return this.news;
	}

	public void setNews(List<News> news) {
		this.news = news;
	}

	public News addNew(News news) {
		getNews().add(news);
		news.setUser(this);

		return news;
	}

	public News removeNew(News news) {
		getNews().remove(news);
		news.setUser(null);

		return news;
	}

	public List<Song> getSongs() {
		return this.songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	public Song addSong(Song song) {
		getSongs().add(song);
		song.setUser(this);

		return song;
	}

	public Song removeSong(Song song) {
		getSongs().remove(song);
		song.setUser(null);

		return song;
	}

	public List<Topic> getTopics() {
		return this.topics;
	}

	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}

	public Topic addTopic(Topic topic) {
		getTopics().add(topic);
		topic.setUser(this);

		return topic;
	}

	public Topic removeTopic(Topic topic) {
		getTopics().remove(topic);
		topic.setUser(null);

		return topic;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}