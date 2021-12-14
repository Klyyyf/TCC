package br.edu.ifrn.tcc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifrn.tcc.dominio.Agenda;
import br.edu.ifrn.tcc.repository.AgendaRepository;
import br.edu.ifrn.tcc.repository.UsuarioRepository;

@Controller
@RequestMapping("/horarios")
public class HorarioController {
	
	@Autowired
	private AgendaRepository agendaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	private List<String> validarDados(Agenda agenda){
		
		List<String> msgs = new ArrayList<>();
		
		if(agenda.getMateria() == null || agenda.getMateria().isEmpty()) {
			msgs.add("O campo Matéria é obrigatório.");
		}
		if(agenda.getProf() == null) {
			msgs.add("O campo Professor é obrigatório.");
		}
		if(agenda.getLocal() == null || agenda.getLocal().isEmpty()) {
			msgs.add("O campo Local é obrigatório.");
		}
		if(agenda.getData() == null || agenda.getData().isEmpty()) {
			msgs.add("O campo Data é obrigatório.");
		}
		if(agenda.getHora() == null || agenda.getHora().isEmpty()) {
			msgs.add("O campo Hora é obrigatório.");
		}
		
		return msgs;
		
	}
	
	@GetMapping("/criarHorario")
	public String criarHorario(ModelMap model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 

		String currentPrincipalName = authentication.getName();
		
		Agenda agenda = new Agenda();
		
		agenda.setProf(usuarioRepository.findByEmail(currentPrincipalName).get());
		
		model.addAttribute("agenda", agenda);
		
		return "criarHorario";
	}
	
	@PostMapping("/salvar")
	public String salvar (Agenda agenda, RedirectAttributes attr, ModelMap model) {
		
		List<String> msgValidacao = validarDados(agenda);
		
		if(!msgValidacao.isEmpty()) {
			model.addAttribute("msgsErro", msgValidacao);
			return "CriarHorario";
		}
		
		agendaRepository.save(agenda);
		
		return "redirect:/horarios/principal";
		
	}
	
	@GetMapping("/editar/{id}")
	public String inciarEdicao(@PathVariable("id") Integer idAgenda, ModelMap model) {
		
		Agenda a = agendaRepository.findById(idAgenda).get();
		
		model.addAttribute("agenda", a);
		
		return "criarHorario";
		
	}
	
}
