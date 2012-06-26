package ufcg.si1.infoCarona.controller;

import ufcg.si1.infoCarona.model.ArgumentoInexistenteException;
import ufcg.si1.infoCarona.model.LoggerException;
import ufcg.si1.infoCarona.model.NumeroMaximoException;
import ufcg.si1.infoCarona.model.sistema.SistemaRaiz;
import ufcg.si1.infoCarona.model.sistema.SistemaUsuarioNaoLogado;
	

public class ControllerView  {
	
	private SistemaUsuarioNaoLogado sistemaNaoLogado;
	public ControllerView() {
		sistemaNaoLogado = new SistemaUsuarioNaoLogado();
	}
	
	public String abrirSessao(String login, String senha) throws LoggerException, NumeroMaximoException, ArgumentoInexistenteException {
		return sistemaNaoLogado.abrirSessao(login, senha);
		
	}
	
	public void criarUsuario(String login, String senha, String nome, String endereco, String email) throws LoggerException {
		sistemaNaoLogado.criarUsuario(login, senha, nome, endereco, email);
	}
	
	
	
}
