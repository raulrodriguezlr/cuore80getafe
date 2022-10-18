package es.cuore80getafe.cuore80getafe.service;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import es.cuore80getafe.cuore80getafe.MyFileNotFoundException;
import es.cuore80getafe.cuore80getafe.model.GaleriaFotos;
import es.cuore80getafe.cuore80getafe.repository.RepoGaleriaFotos;


@Service
public class GaleriaFotosService {
	@Autowired
	public RepoGaleriaFotos repository;
	public GaleriaFotosService(RepoGaleriaFotos repoFotos) {
		this.repository=repoFotos;
	}
	
	public boolean exist(Long id) {
		return repository.existsById(id);
	}
	public void delete(Long id) {
		repository.deleteById(id);
	}
	public void save(GaleriaFotos foto){
		repository.save(foto);
	}
	public Optional<GaleriaFotos> findOne(long id) {
		return repository.findById(id);
	}

	public List<GaleriaFotos> findAll() {
		return repository.findAll();
	}
	public boolean coincidenContraseñas(String contraseña1, String contraseña2) {
		return contraseña1.equals(contraseña2);
		
	}
	public void editarNombre (long id,String name) {
		repository.updateNombreById(id, name);
	
		
	}
	
	
/*
	public GaleriaFotos storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            GaleriaFotos dbFile = new GaleriaFotos(fileName, file.getContentType(), file.getBytes());

            return repository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public GaleriaFotos getFile(Long fileId) {
        return repository.findById(fileId)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    }
	
*/
	
	
	public static String getMD5(String input) {
		 try {
		 MessageDigest md = MessageDigest.getInstance("MD5");
		 byte[] messageDigest = md.digest(input.getBytes());
		 BigInteger number = new BigInteger(1, messageDigest);
		 String hashtext = number.toString(16);

		 while (hashtext.length() < 32) {
		 hashtext = "0" + hashtext;
		 }
		 return hashtext;
		 }
		 catch (NoSuchAlgorithmException e) {
		 throw new RuntimeException(e);
		 }
		 }

}
