package imageDB.image;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import imageDB.authors.Author;
import imageDB.location.Location;
import imageDB.tags.Tag;

@Entity
@Table(name = "image")		
public class Image {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	
	@Column(name= "imageURL")
	public String imageURL;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	@ManyToMany(cascade = {
	        CascadeType.PERSIST,
	        CascadeType.MERGE
	    })
	    @JoinTable(name = "imagetag",
	        joinColumns = @JoinColumn(name = "imageid"),
	        inverseJoinColumns = @JoinColumn(name = "tagid")
	    )
	    private List<Tag> tags = new ArrayList<>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "authors.author_id")
	public Author author;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "locations.location_id")
	public Location location;
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Image)) return false;
        return id != null && id.equals(((Image) o).getId());
    }
 
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
    
}
