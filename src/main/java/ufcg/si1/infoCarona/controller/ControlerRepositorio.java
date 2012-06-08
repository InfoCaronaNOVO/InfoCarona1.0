package ufcg.si1.infoCarona.controller;

import java.util.LinkedList;
import java.util.List;

import ufcg.si1.infoCarona.model.ArgumentoInexistenteException;
import ufcg.si1.infoCarona.model.LoggerException;
import ufcg.si1.infoCarona.model.Repositorio;
import ufcg.si1.infoCarona.model.carona.Carona;
import ufcg.si1.infoCarona.model.carona.CaronaException;
import ufcg.si1.infoCarona.model.carona.TiposCarona;
import ufcg.si1.infoCarona.model.negociacao.SolicitacaoDeVaga;
import ufcg.si1.infoCarona.model.negociacao.SugestaoDePontoDeEncontro;
import ufcg.si1.infoCarona.model.usuario.Usuario;
import util.UtilInfo;

public class ControlerRepositorio {
	Repositorio repositorio;
	
	public ControlerRepositorio(){
		repositorio = Repositorio.getInstance();
	}

	public void addUsuario(Usuario novoUsuario) {
		repositorio.addUsuario(novoUsuario);
	}

	public Usuario buscarUsuarioPorLogin(String login) throws LoggerException, ArgumentoInexistenteException {
		Usuario retorno = null;
		retorno = repositorio.buscaUsuarioLogin(login);
		if(retorno == null){
			throw new ArgumentoInexistenteException("Usuário inexistente");
		}
		
		return retorno;
	}

	public String getAtributoUsuario(String login, String atributo) throws LoggerException, ArgumentoInexistenteException {
		Usuario usuarioTemp = buscarUsuarioPorLogin(login);
		List<String> listaTemp = new LinkedList<String>();
		String retorno = "";

		if (atributo.equals("nome")) {
			retorno = usuarioTemp.getNome();
		}
		else if (atributo.equals("endereco")) {
			retorno = usuarioTemp.getEndereco();
		}
		else if (atributo.equals("email")) {
			retorno = usuarioTemp.getEmail();
		}
		else if (atributo.equals("historico de caronas")) {
			for (Carona caronaTemp : usuarioTemp.getCaronas()) {
				listaTemp.add(caronaTemp.getIdCarona());
			}
			if(listaTemp.isEmpty()){
				retorno = "";
			}else{
			retorno = listaTemp.toString().replace("{", "[").replace("}", "]");
			}
		}
		
		else if (atributo.equals("caronas seguras e tranquilas")) {
			retorno = usuarioTemp.getCaronasSeguras() + "";
		}
		else if (atributo.equals("caronas que não funcionaram")) {
			retorno = usuarioTemp.getCaronasNaoFuncionaram() + "";
		} 
		else if (atributo.equals("faltas em vagas de caronas")) {
			retorno = usuarioTemp.getFaltasEmVagas() + "";
		} 
		else if (atributo.equals("presenças em vagas de caronas")) {
			retorno = usuarioTemp.getPresencaEmVagas()+ "";
		}

		return retorno;
	}
	
	public List<Carona> localizarCarona(String origem, String destino) {
		
		List<Carona> retorno;
		if ((!origem.equals("")) && ((!destino.equals("")))) {
			retorno = repositorio.localizaCaronaPorOrigemDestino(origem, destino);
		} else if ((origem.equals("")) && ((!destino.equals("")))) {
			retorno = repositorio.localizaCaronaPorDestino(destino);

		} else if ((!origem.equals("")) && ((destino.equals("")))) {
			retorno = repositorio.localizaCaronaPorOrigem(origem);
		} else {
			retorno = repositorio.getCaronas();
		}
		return retorno;
		
	}

	public String getAtributoCarona(String idCarona, String atributo) throws ArgumentoInexistenteException {
		Carona caronaTemp = repositorio.localizaCaronaPorId(idCarona);
		if(caronaTemp == null){
			throw new ArgumentoInexistenteException("Item inexistente");
		}
		String retorno = "";
        
        if(atributo.equals("origem")){
                retorno = caronaTemp.getOrigem();
        }else if(atributo.equals("vagas")){
                retorno = (caronaTemp.getVagas()+"");
        }else if(atributo.equals("destino")){
                retorno = caronaTemp.getDestino();
        }else if(atributo.equals("data")){
                retorno = UtilInfo.converteCalendarEmStringData(caronaTemp.getCalendario());
        }else if(atributo.equals("ehMunicipal")){
            if(caronaTemp.getTipoCarona().equals(TiposCarona.MUNICIPAL)){
            	retorno = "true" ;
            }else{
            	retorno = "false"; //sem logica essa bagaça tem q refatorar
            }
        }
        
        return retorno;
	}
	
	public Carona localizaCaronaPorId(String idCarona) throws CaronaException {
		return repositorio.getCaronaId(idCarona);
	}

	public String getTrajeto(String idCarona) throws CaronaException, ArgumentoInexistenteException {
		
		Carona caronaTemp = repositorio.getCaronaId(idCarona);
		if(caronaTemp == null){
			throw new ArgumentoInexistenteException("Trajeto Inexistente");
		}
		return caronaTemp.getOrigem() + " - " + caronaTemp.getDestino();
	}
	
	public SugestaoDePontoDeEncontro getSugestaoId(String idSugestao, String idCarona) throws CaronaException{
		return repositorio.getSugestaoId(idSugestao, idCarona);
	}
	
	public boolean checaExisteLogin(String login){
		return repositorio.checaExisteLogin(login);
	}
	
	public boolean checaExisteEmail(String email){
		return repositorio.checaExisteEmail(email);
	}
	
	public String getAtributoSolicitacao(String idSolicitacao, String atributo) {		
		SolicitacaoDeVaga solicitacaoTemp = localizaSolicitacaoPorId(idSolicitacao);
		
		String retorno = "";
		if(atributo.equals("origem")){
			retorno = solicitacaoTemp.getOrigem();
		}else if(atributo.equals("destino")){
			retorno = solicitacaoTemp.getDestino();
		}else if(atributo.equals("Dono da carona")){
			retorno = solicitacaoTemp.getDonoDaCarona().getNome();
		}else if(atributo.equals("Dono da solicitacao")){
         retorno = solicitacaoTemp.getDonoSolicitacao().getNome();
		}else if(atributo.equals("Ponto de Encontro")){
			retorno = solicitacaoTemp.getPonto();
		}
		
		return retorno;
	}
	
	public SolicitacaoDeVaga localizaSolicitacaoPorId(String idSolicitacao) {
		return repositorio.localizaSolicitacaoPorId(idSolicitacao);
	}

	public void zerarSistema() {
		repositorio.zerarSistema();
	}

	public void encerrarSistema() {
		repositorio.encerrarSistema();
	}

	public List<Carona> localizarCaronaMunicipal(String cidade, String origem, String destino) throws CaronaException {
		if(destino.equals("") && origem.equals("")){
			return repositorio.localizarCaronaMunicipal(cidade);
		}else{
			return repositorio.localizarCaronaMunicipal(cidade, origem, destino);
		}
	}
}
