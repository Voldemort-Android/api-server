package voldemort.writter.server.persistence.dao;

import org.hibernate.validator.constraints.SafeHtml.Tag;

import voldemort.writter.server.persistence.entity.User;

public interface TagDao {
	Tag createTag(Tag user);
	
	Tag findByTagname(String name);
	
	Tag findById(String id);
}
