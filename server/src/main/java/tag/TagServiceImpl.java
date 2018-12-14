package tag;

import org.hibernate.validator.constraints.SafeHtml.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import voldemort.writter.server.exception.RestException;
import voldemort.writter.server.persistence.dao.StoryDao;
import voldemort.writter.server.persistence.dao.TagDao;

public class TagServiceImpl implements TagService{

	@Autowired
	StoryDao storyDao;
	
	@Autowired
	TagDao tagDao;
	
	
	@Override
	public Tag addTag(Tag tag) {
		
		
		String text = tag.getText();
		// Data validation
				if (StringUtils.isEmpty(text)) {
					throw new RestException(HttpStatus.UNPROCESSABLE_ENTITY, "tag text cannot be blank");
				}
				if (story == null || storyDao.findOne(tag.getId()) == null) {
					throw new RestException(HttpStatus.UNPROCESSABLE_ENTITY, "Story is missing or does not exist");
				}
		
		
		return tagDao.addTag(tag);
	}

	@Override
	public Tag editTag(Tag tag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tag attachedTag(Tag tag) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
