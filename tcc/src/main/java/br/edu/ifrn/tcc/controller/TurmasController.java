package br.edu.ifrn.tcc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.ifrn.tcc.dominio.Forum;
import br.edu.ifrn.tcc.repository.ForumRepository;

@Controller
@RequestMapping("/turmas")
public class TurmasController {
	
	@Autowired
	private ForumRepository forumRepository;

	@GetMapping("/principal")
	public String turmas(){
		
		return "turmas";
		
	}
	
	@GetMapping("/buscar")
	public String buscar (
				@RequestParam(name="titulo", required=false) String titulo,
				@RequestParam(name="texto", required=false) String texto,
				ModelMap model
			) {
		
		List<Forum> forunsEncontrados = forumRepository.findByTituloAndTexto(titulo, texto);
		
		model.addAttribute("forunsEncontrados", forunsEncontrados);
		
		return "turmas";
		
	}
	
}
