package br.edu.ifrn.tcc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.ifrn.tcc.dominio.Forum;
import br.edu.ifrn.tcc.dominio.Resposta;
import br.edu.ifrn.tcc.repository.ForumRepository;
import br.edu.ifrn.tcc.repository.RespostaRepository;

@Controller
@RequestMapping("/turmas")
public class RespostasController {
	
	@Autowired
	private RespostaRepository respostaRepository;
	
	@Autowired
	private ForumRepository forumRepository;

	@GetMapping("/respostas/{id}")
	public String respostas(@PathVariable("id") Integer idForum, ModelMap model){
		
		Forum f = forumRepository.findById(idForum).get();
		List<Resposta> respostas = f.getRespostas();

		model.addAttribute("respostasEncontradas", respostas);

		return "respostas";
		
	}
	
	@GetMapping("/buscarR")
	public String buscar (
				@RequestParam(name="titulo", required=false) String titulo,
				@RequestParam(name="forum", required=false) Integer forum,
				ModelMap model
			) {
		
		List<Resposta> respostasEncontradas = respostaRepository.findByTituloAndId(titulo, forum);
		
		model.addAttribute("respostasEncontradas", respostasEncontradas);
		
		return "respostas";
		
	}
	
}
