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
	
	/**
	 * Metodo que cria um repositorio caso o mesmo ainda não tenha sido criado
	 */
	public ControlerRepositorio(){
		repositorio = Repositorio.getInstance();
	}
	
	/**
	 * Metodo que adciona um usuario ao repositorio 
	 * @param novoUsuario
	 */
	public void addUsuario(Usuario novoUsuario) {
		repositorio.addUsuario(novoUsuario);
	}
	
	/**
	 * Metodo que busca um usuario pelo seu login no repositorio
	 * @param login - login do usuario que será buscado
	 * @return - usuario localizado
	 * @throws LoggerException
	 * @throws ArgumentoInexistenteException - usuario inexistente
	 */
	public Usuario buscarUsuarioPorLogin(String login) throws LoggerException, ArgumentoInexistenteException {
		Usuario retorno = null;
		retorno = repositorio.buscaUsuarioLogin(login);
		if(retorno == null){
			throw new ArgumentoInexistenteException("Usuário inexistente");
		}
		
		return retorno;
	}

	/**
	 * Metodo para retorna um atributo de um usuario
	 * @param login - login do usuario que queremos saber o atributo
	 * @param atributo - o atributo procurado
	 * @return - o atributo procurado
	 * @throws LoggerException
	 * @throws ArgumentoInexistenteException
	 */
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
	
	/**
	 * Metodo para localizar um objeto do tipo carona no repositorio
	 * @param origem - origem da carona a ser localizada
	 * @param destino - destino da carona a ser localizada
	 * @return - retorna da(s) carona(s) localizada(s)
	 */
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

	/**
	 * Metodo responsavel para retornar um atributo da carona em questão.
	 * @param idCarona - id da carona que queremos saber algum atributo
	 * @param atributo - Atributo que iremos retornar da carona
	 * @return o atributo requeridó
	* @throws ArgumentoInexistenteException - quando não existe esse atributo no objeto carona
	 */
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
	
	/**
	 * Metodo que retorna um objeto do tipo carona cadastrada no repositorio, procurando a mesma
	 * somente pelo o idcarona
	 * @param idCarona - o id da carona
	 * @return a carona procurada
	 * @throws CaronaException - referentes a problemas com a localização do objeto do tipo carona
	 */
	public Carona localizaCaronaPorId(String idCarona) throws CaronaException {
		return repositorio.getCaronaId(idCarona);
	}

	/**
	 * Metodo para buscar o trajeto de uma Carona
	 * @param idCarona - Id da carona que queremos saber o projeto
	 * @return - retorna o trajeto da carona da forma: Origem - Destino
	 * @throws CaronaException - referentes a problemas com a localização do objeto do tipo carona
	 * @throws ArgumentoInexistenteException - quando for passado um id de carona vazio.
	 */
	public String getTrajeto(String idCarona) throws CaronaException, ArgumentoInexistenteException {
		
		Carona caronaTemp = repositorio.getCaronaId(idCarona);
		if(caronaTemp == null){
			throw new ArgumentoInexistenteException("Trajeto Inexistente");
		}
		return caronaTemp.getOrigem() + " - " + caronaTemp.getDestino();
	}
	
	/**
	 * Metodo utilizado para buscar uma sugestão de ponto de encontro em uma carona
	 * @param idSugestao - a sugestão a ser buscada
	 * @param idCarona - a carona que possui a sugestao
	 * @return - a sugestão
	 * @throws CaronaException
	 */
	public SugestaoDePontoDeEncontro getSugestaoId(String idSugestao, String idCarona) throws CaronaException{
		return repositorio.getSugestaoId(idSugestao, idCarona);
	}
	
	/**
	 * Metodo que busca no repositorio a existencia de um usuario ja cadastrado com este login
	 * @param login 
	 * @return true ou false
	 */
	public boolean checaExisteLogin(String login){
		return repositorio.checaExisteLogin(login);
	}

	/**
	 * Metodo que busca no repositorio a existencia de um usuario ja cadastrado com este email
	 * @param email
	 * @return true ou false
	 */
	public boolean checaExisteEmail(String email){
		return repositorio.checaExisteEmail(email);
	}
	
	/**
	 * Metodo para retorna algum atributo de um objeto do tipo solicitação
	 * @param idSolicitacao - id da solicitação 
	 * @param atributo - atributo procurado
	 * @return - o atributo procurado
	 */
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
	
	/**
	 * Metodo que busca uma solicitação de vaga pela sua idsolicitação
	 * @param idSolicitacao
	 * @return solicitação de vaga
	 */
	public SolicitacaoDeVaga localizaSolicitacaoPorId(String idSolicitacao) {
		return repositorio.localizaSolicitacaoPorId(idSolicitacao);
	}
	
	/**
	 *  Metodo utilizado para zerar o repositorio.
	 */
	public void zerarSistema() {
		repositorio.zerarSistema();
	}
	
	/**
	 * Encerra o Sistema atual, salvando todos os dados em um arquivo .xml
	 */
	public void encerrarSistema() {
		repositorio.encerrarSistema();
	}

	/**
	 * Metodo para localizar uma carona municipal
	 * @param cidade - Cidade da carona municipal
	 * @param origem - Origem da carona municipal
	 * @param destino - Destino da carona municipal
	 * @return - retorno da(s) carona(s) localizada(s)
	 * @throws CaronaException - referentes a problemas com a localização do objeto do tipo carona
	 */
	public List<Carona> localizarCaronaMunicipal(String cidade, String origem, String destino) throws CaronaException {
		if(destino.equals("") && origem.equals("")){
			return repositorio.localizarCaronaMunicipal(cidade);
		}else{
			return repositorio.localizarCaronaMunicipal(cidade, origem, destino);
		}
	}
}
