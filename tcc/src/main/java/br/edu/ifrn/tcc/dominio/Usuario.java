package br.edu.ifrn.tcc.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Usuario {
	
	public static final String PROFESSOR = "PROFESSOR";
	public static final String ALUNO = "ALUNO";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	@NotBlank(message = "O campo nome é obrigatório.")
	@Size(min = 2, message = "O campo nome deve conter pelo menos dois caracteres.")
	private String nome;
	
	@Column(nullable = false)
	@NotBlank(message = "O campo email é obrigatório.")
	private String email;
	
	@Column(nullable = false)
	@NotBlank(message = "O campo senha é obrigatório.")
	private String senha;
	
	@Column(nullable = false)
	private String sexo;
	
	@Column(nullable = false)
	private String cargo = ALUNO;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
}
