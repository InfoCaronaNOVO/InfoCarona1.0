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
	
	/**
	 * Metodo para criar um objeto do tipo Usuario
	 * @param login - Login do Usuario
	 * @param senha - Senha do Usuario
	 * @param nome - Nome do Usuario
	 * @param endereco - Endereço do Usuario
	 * @param email - Email do Usuario
	 * @throws LoggerException - Exceção referente a erros na criação do Objeto do tipo Usuario
	 */
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
	
	/**
	 * Metodo para Abrir uma sessão de um usuario colocando o mesmo no mapa de usuarios logados
	 * @param login - Nome do Usuario
	 * @param senha - Senha do Usuario
	 * @return ID da Sessão do Usuario
	 * @throws LoggerException - referentes a problemas com a localização do objeto do usuario cadastrado
	 * @throws NumeroMaximoException - caso o numero maximo de conexções paralelas do servidor, tenha sido alcançado
	 * @throws ArgumentoInexistenteException - caso os argumentos passados pelo usuario, seja invalido
	 */
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
