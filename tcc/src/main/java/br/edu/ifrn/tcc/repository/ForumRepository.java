package br.edu.ifrn.tcc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.ifrn.tcc.dominio.Forum;

public interface ForumRepository extends JpaRepository<Forum, Integer> {
	
	@Query("select f from Forum f where f.titulo like %:titulo% "+ "and f.texto like %:texto% ")
	List<Forum> findByTituloAndTexto(@Param("titulo") String titulo, @Param("texto") String texto);
	
}
