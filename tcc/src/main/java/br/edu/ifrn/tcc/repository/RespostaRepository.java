package br.edu.ifrn.tcc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.ifrn.tcc.dominio.Resposta;

public interface RespostaRepository extends JpaRepository<Resposta, Integer> {

	@Query("select r from Resposta r where r.titulo like %:titulo% "+ "and r.forum.id like %:forum% ")
	List<Resposta> findByTituloAndId(@Param("titulo") String titulo, @Param("forum") Integer forum);
	
}
