package br.edu.ifrn.tcc.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifrn.tcc.dominio.Forum;
import br.edu.ifrn.tcc.repository.ForumRepository;

@Controller
@RequestMapping("/turmas")
public class CriarForumController {
	
	@Autowired
	private ForumRepository forumRepository;
	
	private List<String> validarDados(Forum forum){
		
		List<String> msgs = new ArrayList<>();
		
		if(forum.getTitulo() == null || forum.getTitulo().isEmpty()) {
			msgs.add("O campo Título é obrigatório.");
		}
		if(forum.getTexto() == null || forum.getTexto().isEmpty()) {
			msgs.add("O campo Texto é obrigatório.");
		}
		
		return msgs;
		
	}

	@GetMapping("/criarForum")
	public String criarForum(ModelMap model) {
		
		model.addAttribute("forum", new Forum());
		
		return "criarForum";
		
	}
	
	@PostMapping("/salvarF")
	public String salvar (Forum forum, RedirectAttributes attr, ModelMap model) {
		
		Date data = new Date();
		
		forum.setData(data);
		
		List<String> msgValidacao = validarDados(forum);
		
		if(!msgValidacao.isEmpty()) {
			model.addAttribute("msgsErro", msgValidacao);
			return "CriarForum";
		}
		
		forumRepository.save(forum);
		
		return "redirect:/turmas/principal";
		
	}
	
}
