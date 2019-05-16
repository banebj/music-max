package com.pmf.musicmax.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pmf.musicmax.model.Comment;
import com.pmf.musicmax.model.Song;
import com.pmf.musicmax.model.Topic;
import com.pmf.musicmax.model.User;

@Repository
@Transactional
public class TopicRepo {

	@PersistenceContext
	EntityManager em;
	
	public List<Topic> getAllTopics(){
		List<Topic> topics = new ArrayList<Topic>();
		try{
			Query q = em.createQuery("select t from Topic t");
			topics = q.getResultList();
			return topics;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public boolean addTopic(Topic topic) {
		em.persist(topic);
		return true;
	}
	
	public List<Comment> getAllCommentsByTopicId(int topicId) {
		List<Comment> comments = new ArrayList<>();
		try {
			Query q = em.createQuery("select c from Comment c where c.topic.id = :topicId");
			q.setParameter("topicId", topicId);
			comments = q.getResultList();
			return comments;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Topic getTopicById(int id) {
		Topic topic = em.find(Topic.class, id);
		return topic;
	}
	
	public Comment addCommentByTopicAndUserId(String text, int userId, int topicId) {
		Comment c = new Comment();
		c.setText(text);
		User user = em.find(User.class, userId);
		c.setUser(user);
		Topic topic = em.find(Topic.class, topicId);
		c.setTopic(topic);
		
		em.persist(c);
				
		return c;
	}
	

}
