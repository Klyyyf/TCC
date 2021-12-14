package br.edu.ifrn.tcc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.ifrn.tcc.dominio.Agenda;

public interface AgendaRepository extends JpaRepository<Agenda, Integer>{

	@Query("select a from Agenda a where a.materia like %:materia% "+ "and a.prof.nome like %:prof% ")
	List<Agenda> findByMateriaAndProf(@Param("materia") String materia, @Param("prof") String prof);
	
}
