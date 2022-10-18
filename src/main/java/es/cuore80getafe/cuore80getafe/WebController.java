package es.cuore80getafe.cuore80getafe;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.file.ConfigurationSource.Resource;
import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.*;  
import javax.servlet.*;  
import es.cuore80getafe.cuore80getafe.model.GaleriaFotos;
import es.cuore80getafe.cuore80getafe.repository.RepoGaleriaFotos;
import es.cuore80getafe.cuore80getafe.service.GaleriaFotosService;


@Controller
public class WebController extends HttpServlet {
	@Autowired
	private GaleriaFotosService fotoService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	@GetMapping("/defaultsite")
	public String defaultsite() {
		return "index";
	}
	
	@GetMapping("/about")
	public String about() {
		return "about";
	}
	
	@GetMapping("/contact")
	public String contact() {
		return "contact";
	}
	@GetMapping("/error")
	public String error() {
		return "error";
	}
	@GetMapping("/uploadPhotos")
	public String form(Model model) {
		model.addAttribute("galeriaFotos",new GaleriaFotos());
		return "nuevaFoto";
	}

	@GetMapping("/galeria")
	public String galeriaFotos(Model model) {
		List<GaleriaFotos> list =fotoService.findAll();
		 List<GaleriaFotos> first = new ArrayList<GaleriaFotos>() ;
		    List<GaleriaFotos> second = new ArrayList<GaleriaFotos>();
		    List<GaleriaFotos> third = new ArrayList<GaleriaFotos>();
		int size = list.size();
		int contador=0;
		if(size>0) {
		    for (int i = 0; i < size*0.3; i++) {
		    
		            first.add(list.get(i));
		            contador+=1;
		            
		        }
			if(size>1)
		    for (int i = contador; i < size*0.6; i++) {
			    
		    	second.add(list.get(i));
		    	 contador+=1;
		         
	        }
			if(size>2)
		    for (int i = contador; i < size; i++) {
			    
		    	third.add(list.get(i));
		    }
		}
	       
	    
		model.addAttribute("first",(List<GaleriaFotos>)first);
		model.addAttribute("second",(List<GaleriaFotos>)second);
		model.addAttribute("third",(List<GaleriaFotos>)third);
		return "galeriaFotos";
	}
	@GetMapping("/galeriaBorrar")
	public String galeriaFotosBorrar(Model model) {
		List<GaleriaFotos> list =fotoService.findAll();
		 List<GaleriaFotos> first = new ArrayList<GaleriaFotos>() ;
		    List<GaleriaFotos> second = new ArrayList<GaleriaFotos>();
		    List<GaleriaFotos> third = new ArrayList<GaleriaFotos>();
		int size = list.size();
		int contador=0;
		if(size>0) {
		    for (int i = 0; i < size*0.3; i++) {
		    
		            first.add(list.get(i));
		            contador+=1;
		            
		        }
			if(size>1)
		    for (int i = contador; i < size*0.6; i++) {
			    
		    	second.add(list.get(i));
		    	 contador+=1;
		         
	        }
			if(size>2)
		    for (int i = contador; i < size; i++) {
			    
		    	third.add(list.get(i));
		    }
		}
	       
	    
		model.addAttribute("first",(List<GaleriaFotos>)first);
		model.addAttribute("second",(List<GaleriaFotos>)second);
		model.addAttribute("third",(List<GaleriaFotos>)third);
		return "galeriaFotosBorrar";
	}
	@PostMapping("/deletePhotos")
	public String borrarFoto(@RequestParam(name="idF", required=false)Long idF) {
		fotoService.delete(idF);
		return "redirect:/galeria";
		
	}
	@PostMapping("/uploadPhotos")
	public String String(@RequestParam(name="file", required=false)MultipartFile file){
		if(!file.isEmpty()) {
			String separador =System.getProperty("file.separator");
			Path directorioImagenes=Paths.get("src"+separador+"main"+separador+"resources"+separador+"static"+separador+"img");

			Path directorioNuevo=Paths.get("static"+separador+"img");
			String rutaAbsoluta=directorioImagenes.toFile().getAbsolutePath();
			System.out.println(rutaAbsoluta);
		
			
			if(directorioImagenes.toFile().exists()) {
				
				try {
					byte[]bytesImg=file.getBytes();
					Path rutaCompleta= Paths.get(rutaAbsoluta+System.getProperty("file.separator")+file.getOriginalFilename());
					Files.write(rutaCompleta,bytesImg);
					GaleriaFotos foto= new GaleriaFotos(file.getOriginalFilename(),file.getContentType(),rutaCompleta.toString());
					fotoService.save(foto);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(directorioNuevo.toFile().exists()) {
				try {
					rutaAbsoluta=directorioNuevo.toFile().getAbsolutePath();
					byte[]bytesImg=file.getBytes();
					Path rutaCompleta= Paths.get(rutaAbsoluta+System.getProperty("file.separator")+file.getOriginalFilename());
					Files.write(rutaCompleta,bytesImg);
					GaleriaFotos foto= new GaleriaFotos(file.getOriginalFilename(),file.getContentType(),rutaCompleta.toString());
					fotoService.save(foto);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			else {				
				if(directorioNuevo.toFile().mkdirs()) {
					try {
						rutaAbsoluta=directorioNuevo.toFile().getAbsolutePath();
						byte[]bytesImg=file.getBytes();
						Path rutaCompleta= Paths.get(rutaAbsoluta+System.getProperty("file.separator")+file.getOriginalFilename());
						Files.write(rutaCompleta,bytesImg);
						GaleriaFotos foto= new GaleriaFotos(file.getOriginalFilename(),file.getContentType(),rutaCompleta.toString());
						fotoService.save(foto);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					System.out.println("Error al crear directorio");
				}
			
			
			//System.out.println(file.getOriginalFilename());
			//GaleriaFotos dbFile = fotoService.storeFile(file);
			 
		    }
		}return "redirect:/galeria";
	}
	
	/*@GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long fileId) {
        // Load file from database
		GaleriaFotos dbFile = fotoService.getFile(fileId);
		StringReader reader = new StringReader(dbFile.getFileType());
        return ResponseEntity.ok()
                .contentType(dbFile.getFileType())
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getNombre() + "\"")
                .body(new ByteArrayResource(dbFile.getFoto()));
    }
	*/
	 
}
	

