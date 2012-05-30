package ufcg.si1.infoCarona.model.sistema;

import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import ufcg.si1.infoCarona.model.ArgumentoInexistenteException;
import ufcg.si1.infoCarona.model.LoggerException;
import ufcg.si1.infoCarona.model.NumeroMaximoException;
import ufcg.si1.infoCarona.model.carona.Carona;
import ufcg.si1.infoCarona.model.carona.CaronaException;
import ufcg.si1.infoCarona.model.negociacao.SolicitacaoDeVaga;
import ufcg.si1.infoCarona.model.usuario.Usuario;
import util.UtilInfo;

public class SistemaUsuario {
	
	private SistemaRaiz sistema;
	
	public SistemaUsuario(){
		sistema = SistemaRaiz.getInstance();
	}
	
	public void encerrarSessao(String login) {
		Collection<String> listaUsuariosLogados = sistema.usuariosLogados.keySet();
		Usuario removeUsuario = null;
		for (String idSessao : listaUsuariosLogados) {
			if (sistema.usuariosLogados.get(idSessao).getLogin().equals(login)) {
				removeUsuario = sistema.usuariosLogados.get(idSessao);
				break;
			}
		}
		listaUsuariosLogados.remove(removeUsuario);
	}

	public String getAtributoUsuario(String login, String atributo)
			throws LoggerException, ArgumentoInexistenteException {

		String retorno = "";

		if (!UtilInfo.checaLogin(login)) {
			throw new LoggerException("Login inválido");
		}

		if (UtilInfo.checaAtributo(atributo)) {
			throw new IllegalArgumentException("Atributo inválido");
		}
		if (!UtilInfo.checaAtributoValido(atributo)) {
			throw new ArgumentoInexistenteException("Atributo inexistente");
		}

		retorno = sistema.controleRepositorio.getAtributoUsuario(login, atributo);

		return retorno;
	}
	
	public String getAtributoPerfil(String login, String atributo)
			throws
			LoggerException, ArgumentoInexistenteException {
		
		if (!atributo.equals("historico de vagas em caronas")) {
			return sistema.controleRepositorio.getAtributoUsuario(login, atributo);
		}
		Usuario usuarioTemp = sistema.controleRepositorio.buscarUsuarioPorLogin(login);
			List<String> lista = new LinkedList<String>();
			for (Carona caronaTemp : usuarioTemp.getSolicitacaoAceitas()) {
				lista.add(caronaTemp.getIdCarona());				
			}
			if(lista.size() == 0){
				return "";
			}
			return lista.toString().replace("{", "[").replace("}", "]").replace(", ", ",");
			
		}
	
	public String cadastrarInteresse(String idSessao, String origem, String destino, Calendar calendarioInicial, Calendar calendarioFinal, boolean caronaEhNoDia) throws NumeroMaximoException, CaronaException, ArgumentoInexistenteException {
		Usuario usuarioTemp = sistema.procuraUsuarioLogado(idSessao);
		return usuarioTemp.cadastrarInteresse(origem, destino, calendarioInicial, calendarioFinal, sistema.id.gerarId(), caronaEhNoDia);
		
	}
	
	public List<String> verificarMensagensPerfil(String idSessao) throws ArgumentoInexistenteException {
		return sistema.procuraUsuarioLogado(idSessao).getListaDeMensagens();
	}
	
	public String visualizarPerfil(String idSessao, String login)
			throws LoggerException, ArgumentoInexistenteException {
		Usuario usuarioTemp = sistema.procuraUsuarioLogado(idSessao);
		Usuario usuarioProcurado = null;
		try {
			usuarioProcurado = sistema.controleRepositorio.buscarUsuarioPorLogin(login);
		} catch (Exception e) {
			throw new LoggerException("Login inválido");
		}

		return usuarioTemp.visualizarPerfil(usuarioProcurado);
	}
}
