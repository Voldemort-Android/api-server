package tag.copy;

import org.hibernate.validator.constraints.SafeHtml.Tag;

public interface TagService {
	
	Tag addTag(Tag tag);
	
	Tag editTag (Tag tag);
	
	Tag attachedTag ( Tag tag);
}

