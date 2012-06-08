package ufcg.si1.infoCarona.model.sistema;

import ufcg.si1.infoCarona.controller.ControlerRepositorio;
import ufcg.si1.infoCarona.model.ArgumentoInexistenteException;
import ufcg.si1.infoCarona.model.Id;
import ufcg.si1.infoCarona.model.LoggerException;
import ufcg.si1.infoCarona.model.NumeroMaximoException;
import ufcg.si1.infoCarona.model.usuario.Usuario;
import util.UtilInfo;

public class SistemaUsuarioNaoLogado {
	
	private ControlerRepositorio controler;
	private Id id;
	
	public SistemaUsuarioNaoLogado(){
		controler = new ControlerRepositorio();
		id = Id.getInstance(5);
	}
	
	public void criarUsuario(String login, String senha, String nome,
			String endereco, String email) throws LoggerException{

		if (controler.checaExisteLogin(login)) {
			throw new LoggerException("Já existe um usuário com este login");
		}

		if (controler.checaExisteEmail(email)) {
			throw new LoggerException("Já existe um usuário com este email");
		}

		Usuario novoUsuario = new Usuario(nome, email, endereco, senha, login);
		controler.addUsuario(novoUsuario);

	}
	
	public String abrirSessao(String login, String senha)
			throws LoggerException, NumeroMaximoException, ArgumentoInexistenteException {

		String idSessao = id.gerarId();

		if (!(UtilInfo.checaLogin(login))) {
			throw new LoggerException("Login inválido");
		}

		Usuario usuarioTemp = controler.buscarUsuarioPorLogin(login);

		if (usuarioTemp.getSenha().equals(senha)) {
			SistemaRaiz.usuariosLogados.put(idSessao, usuarioTemp);
		} else {
			throw new LoggerException("Login inválido");
		}

		return idSessao;
	}
	
}
