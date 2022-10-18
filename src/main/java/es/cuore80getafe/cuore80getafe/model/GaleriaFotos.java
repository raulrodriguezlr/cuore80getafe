package es.cuore80getafe.cuore80getafe.model;

import java.io.File;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;


@Entity
@Table(name="galeria_fotos")
public class GaleriaFotos implements Serializable{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	// @Lob
	  //private S foto;
	 private String Filetype;
	private String nombre;
	private String path;
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	/*
	public byte[] getFoto() {
		return foto;
	}
	public void setFoto(byte[] fotos) {
		this.foto = fotos;
	}*/
	public String getNombre() {
		return nombre;
	}
	public String getFileType() {
	    return Filetype;
	  }

	  public void setFiletype(String type) {
	    this.Filetype = type;
	  }
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public GaleriaFotos() {
		super();
	}
	public GaleriaFotos( String name, String type,String path) {
		super();
		 this.Filetype = type;
		//this.foto = data;
		this.nombre = name;
		this.path=path;
	}
	public GaleriaFotos(String name) {
		super();
		this.nombre = name;
		//this.foto = fotos;
	
	}
	
	
	private static final long serialVersionUID = 1L;
}
