package voldemort.writter.server.persistence.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "stories")
public class Story implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false)
    private String title;
	
	@Lob
	@Column(nullable = false)
	@JsonInclude(Include.NON_NULL)
    private String text;
	
	@Column(nullable = false)
	private int points = 0;
	
	@Column(nullable = false)
    private int views = 0;
	
	@ManyToOne(targetEntity = User.class)
	@JsonIgnoreProperties({"enabled", "created", "modified"})
	private User author;
	
	@Column(nullable = false)
	@JsonIgnore
	private boolean enabled = true;
	
	@Column(nullable = false)
    private Date created;

	@Column(nullable = false)
    private Date modified;

    public Story() {
    	
    }
    
    public Story(Long id) {
    	this.id = id;
    }
    
    public Story(Long id, String title, int views, Date created, Date modified, User author) {
		this.id = id;
		this.title = title;
		this.views = views;
		this.created = created;
		this.modified = modified;
		this.author = author;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
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
