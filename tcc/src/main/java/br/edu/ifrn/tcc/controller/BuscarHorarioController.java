package br.edu.ifrn.tcc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifrn.tcc.dominio.Agenda;
import br.edu.ifrn.tcc.repository.AgendaRepository;

@Controller
@RequestMapping("/horarios")
public class BuscarHorarioController {
	
	@Autowired
	private AgendaRepository agendaRepository;

	@GetMapping("/principal")
	public String horarios() {
		return "horarios";
	}
	
	@GetMapping("/buscar")
	public String buscar (
				@RequestParam(name="materia", required=false) String materia,
				@RequestParam(name="prof", required=false) String prof,
				ModelMap model
			) {
		
		List<Agenda> materiasEncontradas = agendaRepository.findByMateriaAndProf(materia, prof);
		
		model.addAttribute("materiasEncontradas", materiasEncontradas);
		
		return "horarios";
		
	}
	
	@GetMapping("/remover/{id}")
	public String remover(@PathVariable("id") Integer idAgenda, RedirectAttributes attr) {
		
		agendaRepository.deleteById(idAgenda);
		attr.addFlashAttribute("msgSucesso", "Horário removido com sucesso!");
		
		return "redirect:/horarios/principal";
		
	}
	
}
