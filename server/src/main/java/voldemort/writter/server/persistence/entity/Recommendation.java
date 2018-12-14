package voldemort.writter.server.persistence.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "recommendation")
public class Recommendation implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne(targetEntity = Story.class)
	private Story story;
	
	@ManyToOne(targetEntity = User.class)
	private User user;
	
	@Column(nullable = false)
	private double rating;
	
	private boolean enabled = true;
	
    private Date created;

    private Date modified;
    
    public Recommendation() {};
    
	public Recommendation(Long id, Story story, User user, double rating, boolean enabled, Date created,
			Date modified) {
		this.id = id;
		this.story = story;
		this.user = user;
		this.rating = rating;
		this.enabled = enabled;
		this.created = created;
		this.modified = modified;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Story getStory() {
		return story;
	}

	public void setStory(Story story) {
		this.story = story;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}
    
    
}
