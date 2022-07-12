package es.cuore80getafe.cuore80getafe.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class images {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long id;
	public String url;
	public images() {
		
	}
	public images(String url) {
		this.url=url;
	}
}
