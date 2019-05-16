package com.pmf.musicmax.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pmf.musicmax.model.Comment;
import com.pmf.musicmax.model.Song;
import com.pmf.musicmax.model.Topic;
import com.pmf.musicmax.model.User;
import com.pmf.musicmax.repository.TopicRepo;

@Controller
@RequestMapping(value="topic")
public class TopicController {
	
	@Autowired
	TopicRepo tr;
	
	@RequestMapping(value="getAllTopics", method=RequestMethod.GET)
	public String getAllTopics(Model m, HttpServletRequest request){
		List<Topic> topics = tr.getAllTopics();
		request.getSession().setAttribute("topics", topics);
		m.addAttribute("topics", topics);
		
		return "Topic/manageTopics";
	}
	
	@RequestMapping(value="addTopic", method=RequestMethod.POST)
	public String addTopic(Model m, HttpServletRequest request){
		String title = request.getParameter("title");
		String text = request.getParameter("text");
		User user = (User) request.getSession().getAttribute("user");
		Topic topic = new Topic();
		topic.setTitle(title);
		topic.setText(text);
		topic.setUser(user);
		
		int i=0;
		if(title=="") {
			request.getSession().setAttribute("messageTitle", "Title Missing");
			i++;
		}else {
			request.getSession().setAttribute("messageTitle", "");
		}
		if(text=="") {
			request.getSession().setAttribute("messageText", "Text Missing");
			i++;
		}else {
			request.getSession().setAttribute("messageText", "");
		}
		if(i>0) {
			request.getSession().setAttribute("topic", topic);
			m.addAttribute("topic", topic);
			return "Topic/addTopic";
		}
		
		
		boolean topicAdded = tr.addTopic(topic);
		if(topicAdded){
			return "redirect:/topic/getAllTopics";
		}
		return "Topic/addTopic";
	}
	
	@RequestMapping(value="getAllCommentsByTopicId", method=RequestMethod.GET)
	public String getAllCommentsByTopicId(Model m, HttpServletRequest request){
		int topicId = Integer.parseInt(request.getParameter("id"));

		
		Topic topic = tr.getTopicById(topicId);
		request.getSession().setAttribute("topic", topic);
		m.addAttribute("topic", topic);
		
		List<Comment> comments  = tr.getAllCommentsByTopicId(topicId);
		request.getSession().setAttribute("comments", comments);
		m.addAttribute("comments", comments);
		
		
		return "Topic/addComment";
	}
	
	@RequestMapping(value="addCommentByTopicAndUserId", method=RequestMethod.POST)
	public String addCommentByTopicAndUserId(Model m, HttpServletRequest request){
		Topic topic = (Topic) request.getSession().getAttribute("topic");
		int topicId = topic.getId();
		User user = (User) request.getSession().getAttribute("user");
		int userId = user.getId();
		String text = request.getParameter("text");
		System.out.println(topicId);
		System.out.println("userId = " + userId);
		
		Comment commented = tr.addCommentByTopicAndUserId(text, userId, topicId);
		
		//Update comments list in session
		List<Comment> comments  = tr.getAllCommentsByTopicId(topicId);
		request.getSession().setAttribute("comments", comments);
		m.addAttribute("comments", comments);
		
		m.addAttribute("topic", topic);//To be seen when page is refrehsed
		
		return "Topic/addComment";
	}
	
	
}
