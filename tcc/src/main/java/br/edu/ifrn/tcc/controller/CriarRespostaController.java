package br.edu.ifrn.tcc.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifrn.tcc.dominio.Resposta;
import br.edu.ifrn.tcc.repository.ForumRepository;
import br.edu.ifrn.tcc.repository.RespostaRepository;

@Controller
@RequestMapping("/turmas")
public class CriarRespostaController {
	
	@Autowired
	private RespostaRepository respostaRepository;
	
	@Autowired
	private ForumRepository forumRepository;
	
	private List<String> validarDados(Resposta resposta){
		
		List<String> msgs = new ArrayList<>();
		
		if(resposta.getTitulo() == null || resposta.getTitulo().isEmpty()) {
			msgs.add("O campo Título é obrigatório.");
		}
		if(resposta.getTexto() == null || resposta.getTexto().isEmpty()) {
			msgs.add("O campo Texto é obrigatório.");
		}
		
		return msgs;
		
	}

	@GetMapping("/responder/{id}")
	public String criarResposta(@PathVariable("id") Integer idForum, ModelMap model) {
		
		Resposta resposta = new Resposta();
		
		resposta.setForum(forumRepository.findById(idForum).get());
		
		model.addAttribute("resposta", resposta);
		
		return "criarResposta";
		
	}
	
	@PostMapping("/salvarR")
	public String salvar (Resposta resposta, RedirectAttributes attr, ModelMap model) {
		
		Date data = new Date();
		
		resposta.setData(data);
		
		List<String> msgValidacao = validarDados(resposta);
		
		if(!msgValidacao.isEmpty()) {
			model.addAttribute("msgsErro", msgValidacao);
			return "CriarForum";
		}
		
		respostaRepository.save(resposta);
		
		return "redirect:/turmas/principal";
		
	}
	
}
