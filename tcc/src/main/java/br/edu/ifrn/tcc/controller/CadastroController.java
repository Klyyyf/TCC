package br.edu.ifrn.tcc.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifrn.tcc.dominio.Usuario;
import br.edu.ifrn.tcc.repository.UsuarioRepository;

@Controller
@RequestMapping("/usuarios")
public class CadastroController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	private List<String> validarDados(Usuario usuario){
		
		List<String> msgs = new ArrayList<>();
		
		if(usuario.getNome() == null || usuario.getNome().isEmpty()) {
			msgs.add("O campo nome é obrigatório.");
		}
		if(usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
			msgs.add("O campo e-mail é obrigatório.");
		}
		if(usuario.getSenha() == null || usuario.getSenha().isEmpty()) {
			msgs.add("O campo senha é obrigatório.");
		}
		if(usuario.getCargo() == null || usuario.getCargo().isEmpty()) {
			msgs.add("O campo cargo é obrigatório.");
		}
		if(usuario.getSexo() == null || usuario.getSexo().isEmpty()) {
			msgs.add("O campo sexo é obrigatório.");
		}
		
		return msgs;
		
	}
	
	@GetMapping("/cadastro")
	public String cadastro(ModelMap model) {
		model.addAttribute("usuario", new Usuario());
		return "cadastro";
		
	}
	
	@PostMapping("/salvar")
	@Transactional(readOnly = false)
	public String salvar(Usuario usuario, RedirectAttributes attr, ModelMap model){
		
		List<String> msgValidacao = validarDados(usuario);
		
		if(!msgValidacao.isEmpty()) {
			model.addAttribute("msgsErro", msgValidacao);
			return "cadastro";
		}
		
		String senhaCripto = new BCryptPasswordEncoder().encode(usuario.getSenha());
		usuario.setSenha(senhaCripto);
		
		usuarioRepository.save(usuario);
		attr.addFlashAttribute("msgSucesso", "Operação realizada com sucesso!");
		
		return "redirect:/usuarios/cadastro";
		
	}
	
	@ModelAttribute("cargos")
	public List<String> getCargos(){
		
		return Arrays.asList("PROFESSOR", "ALUNO");
		
	}

}
