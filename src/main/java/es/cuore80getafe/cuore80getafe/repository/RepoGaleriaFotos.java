package es.cuore80getafe.cuore80getafe.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.cuore80getafe.cuore80getafe.model.GaleriaFotos;




@Transactional
public interface RepoGaleriaFotos  extends JpaRepository<GaleriaFotos, Long>{
	 	@Modifying
	   @Query(value = "update usuario set nombre = :nombre where id = :id",nativeQuery = true)
	    void updateNombreById(@Param("id") long id, @Param("nombre") String nombre);
	 	@Modifying
		   @Query(value = "update usuario set apellido = :apellido where id = :id",nativeQuery = true)
		    void updateApellidoById(@Param("id") long id, @Param("apellido") String apellido);
	 	@Modifying
		   @Query(value = "update usuario set contraseña = :contraseña where id = :id",nativeQuery = true)
		    void updateContraseñaById(@Param("id") long id, @Param("contraseña") String contraseña);
	 	@Modifying
		   @Query(value = "update usuario set Estado = :Estado where id = :id",nativeQuery = true)
	 		void updateEstadoById(@Param("id") long id, @Param("Estado") String Estado);
	
	
}

