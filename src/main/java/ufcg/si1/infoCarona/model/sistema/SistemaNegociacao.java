package ufcg.si1.infoCarona.model.sistema;

import java.util.LinkedList;
import java.util.List;

import ufcg.si1.infoCarona.controller.ControlerRepositorio;
import ufcg.si1.infoCarona.model.ArgumentoInexistenteException;
import ufcg.si1.infoCarona.model.Id;
import ufcg.si1.infoCarona.model.NumeroMaximoException;
import ufcg.si1.infoCarona.model.carona.Carona;
import ufcg.si1.infoCarona.model.carona.CaronaException;
import ufcg.si1.infoCarona.model.negociacao.SolicitacaoDeVaga;
import ufcg.si1.infoCarona.model.negociacao.SugestaoDePontoDeEncontro;
import ufcg.si1.infoCarona.model.usuario.Usuario;
import util.UtilInfo;

public class SistemaNegociacao {
	
	private Id id;
	private ControlerRepositorio controler;
	
	public SistemaNegociacao(){
		controler = new ControlerRepositorio();
		id = Id.getInstance(5);
	}
	
	/**
	 * Metodo utilizado para sugerir ponto de encontro em uma carona
	 * @param idSessao - id do usuario logado que irá sugerir os pontos
	 * @param idCarona - id da carona no qual será sugerido os pontos
	 * @param pontos - os pontos de encontro
	 * @return -  o id da sugestão
	 * @throws CaronaException - referentes a problemas com a localização do objeto do tipo carona
	 * @throws NumeroMaximoException - referentes a problemas na criação do id da sugestçao
	 * @throws ArgumentoInexistenteException - quando os pontos são passados vazios.
	 */
	public String sugerirPontoEncontro(String idSessao, String idCarona,
			String pontos) throws CaronaException,
			NumeroMaximoException, ArgumentoInexistenteException {
		if (pontos == null || pontos.equals("")) {
			throw new IllegalArgumentException("Ponto Inválido");
		}
		Usuario usuarioTemp = SistemaRaiz.procuraUsuarioLogado(idSessao);
		Carona caronaTemp = this.getCarona(idCarona);
		if(SistemaRaiz.usuarioJahEstahNaCarona(usuarioTemp, caronaTemp)){
			throw new IllegalArgumentException("Ponto Inválido");
		}
		
		return usuarioTemp.sugerirPontoEncontro(pontos, caronaTemp,
				id.gerarId(), usuarioTemp);

	}
	
	/**
	 * Metodo utilizado para um usuario solicitar vaga em uma carona
	 * @param idSessao - id do usuario solicitante
	 * @param idCarona - id da carona em que o usuario solicita vaga
	 * @param ponto - pontos referentes ao encontro na carona
	 * @return - id da solicitação de vaga na carona
	 * @throws CaronaException - referentes a problemas com o objeto do tipo carona 
	 * @throws NumeroMaximoException - 
	 * @throws ArgumentoInexistenteException
	 */
	public String solicitarVagaPontoEncontro(String idSessao, String idCarona,
			String ponto) throws CaronaException,NumeroMaximoException, ArgumentoInexistenteException {

		Usuario usuarioTemp = SistemaRaiz.procuraUsuarioLogado(idSessao);
		Carona carona = controler.localizaCaronaPorId(idCarona);
		if (ponto.equals("")) {
			ponto = null; // subtende-se que o usuario aceita os pontos que o
							// dono da carona indicou
		}

		return usuarioTemp.solicitarVagaPontoEncontro(ponto, carona,
				id.gerarId(), usuarioTemp);
	}
	
	/**
	 * Metodo utilizado para o usuario dono da carona, responda a uma sugestão de ponto de encontro
	 * @param idSessao - id do usuario logado e dono da carona
	 * @param idCarona - id da carona do usuario e que tem um ponto de encontro para responder
	 * @param idSugestao - id da sugestão no qual o usuario irá responder
	 * @param pontos - os pontos com o qual o dono da carona responde em relação a sugestão
	 * @throws CaronaException - referentes a problemas com a localização do objeto do tipo carona 
	 * @throws ArgumentoInexistenteException - quando os pontos são passados vazios.
	 */
	public void responderSugestaoPontoEncontro(String idSessao,
			String idCarona, String idSugestao, String pontos)
			throws CaronaException, ArgumentoInexistenteException{

		Usuario usuarioTemp = SistemaRaiz.procuraUsuarioLogado(idSessao);
		SugestaoDePontoDeEncontro sugestaoTemp = controler.getSugestaoId(idSugestao, idCarona);
		Carona caronaTemp = controler.localizaCaronaPorId(idCarona);

		usuarioTemp.responderSugestaoPontoEncontro(sugestaoTemp, pontos, caronaTemp);
	}
	
	/**
	 * Metodo para que o usuario aceite os pontos que outro usuario sugerio
	 * @param idSessao - id do usuario 
	 * @param idSolicitacao - id da solicitação que o mesmo irá aceitar
	 * @throws ArgumentoInexistenteException
	 */
	public void aceitarSolicitacaoPontoEncontro(String idSessao,
			String idSolicitacao) throws ArgumentoInexistenteException  {

		Usuario usuarioTemp = SistemaRaiz.procuraUsuarioLogado(idSessao);
		SolicitacaoDeVaga solicitacao = controler
				.localizaSolicitacaoPorId(idSolicitacao);

		usuarioTemp.aceitarSolicitacaoPontoEncontro(solicitacao);
	}
	
	/**
	 * Metodo utilizado para que o dono da carona recuse uma solicitação de vaga em uma carona que seja dono.
	 * @param idSessao - id do usuario logado
	 * @param idSolicitacao - solicitação que sera recusada
	 * @throws ArgumentoInexistenteException - quando não existe esta solicitação
	 */
	public void rejeitarSolicitacao(String idSessao, String idSolicitacao) throws ArgumentoInexistenteException{
		Usuario usuarioTemp = SistemaRaiz.procuraUsuarioLogado(idSessao);
		SolicitacaoDeVaga solicitacao = controler
				.localizaSolicitacaoPorId(idSolicitacao);
		usuarioTemp.rejeitarSolicitacao(solicitacao);

	}

	/**
	 * Metodo utilizado para que um usuario desista de uma solicitação de vaga em uma determinada carona
	 * @param idSessao - id do usuario
	 * @param idCarona - id da carona que o mesmo solicitou vaga
	 * @param idSolicitacao - id da sugestão da vaga
	 * @throws CaronaException
	 * @throws ArgumentoInexistenteException
	 */
	public void desistirRequisicao(String idSessao, String idCarona,
			String idSolicitacao) throws CaronaException, ArgumentoInexistenteException {

		Usuario usuarioTemp = SistemaRaiz.procuraUsuarioLogado(idSessao);
		Carona caronaTemp = controler.localizaCaronaPorId(idCarona);
		SolicitacaoDeVaga solicitacaoTemp = controler.localizaSolicitacaoPorId(idSolicitacao);
		usuarioTemp.desistirRequisicao(solicitacaoTemp, caronaTemp);

	}
	
	/**
	 * Metodo que retorna todos os pontos sugeridos por caroneiros
	 * @param idSessao - id do usuario logado
	 * @param idCarona - id da carona
	 * @return lista de sugestão de pontos de encontro
	 * @throws CaronaException
	 */
	public LinkedList<String> getPontosSugeridos(String idCarona) throws CaronaException {
		LinkedList<String> retorno = new LinkedList<String>();
		Carona caronaTemp = controler.localizaCaronaPorId(idCarona);
		List<SugestaoDePontoDeEncontro> listaSugestoes = caronaTemp.getListaDeSugestoes();
		for(SugestaoDePontoDeEncontro sugestao : listaSugestoes){
			for (String ponto : sugestao.getListaDeSugestaoDePontosDeEncontro()) {
				retorno.add(ponto);
			}
		}
		return retorno;
	}
	
	/**
	 * Metodo que retorna todos os pontos de encontro que estão nesta carona
	 * @param idSessao - id do a seção
	 * @param idCarona - id da carona em questão
	 * @return - lista de sugestão de pontos de encontro
	 * @throws CaronaException
	 */
	public List<String> getPontosEncontro(String idCarona)
			throws CaronaException {
		List<String> retorno = new LinkedList<String>();
        List<SugestaoDePontoDeEncontro> sugestoes = controler.localizaCaronaPorId(idCarona)
                .getListaDeSugestoes();
        
        for (int i = 0; i < sugestoes.size(); i++) {
            List<String> pontos = sugestoes.get(i).getListaDeSugestaoDePontosDeEncontro();
            for (int j = 0; j < pontos.size(); j++) {
                String ponto = pontos.get(j);
                if (!retorno.contains(ponto)) {
                    retorno.add(ponto);
                }
            }
        }
        
        return retorno;
	}
	/**
	 * Metodo que retorna todas as solicitações que foram confirmadas desta carona
	 * @param idCarona - a carona
	 * @return - solicitações confirmadas
	 * @throws CaronaException
	 */
	public List<SolicitacaoDeVaga> getSolicitacoesConfirmadas(String idCarona) throws CaronaException {
		return controler.localizaCaronaPorId(idCarona).getSolicitacoesConfirmadas();
	}

	/**
	 * Metodo que retorna as solicitações pendentes de uma determinada carona
	 * @param idSessao - id do usuario logado
	 * @param idCarona - id da carona em questão
	 * @return - solicitações pendentes
	 * @throws CaronaException
	 */
	public List<SolicitacaoDeVaga> getSolicitacoesPendentes(String idCarona)
			throws CaronaException {
		return controler.localizaCaronaPorId(idCarona)
				.getSolicitacoesPendentes();
	}
	
	/**
	 * Metodo para retorna algum atributo de um objeto do tipo solicitação
	 * @param idSolicitacao - id da solicitação 
	 * @param atributo - atributo procurado
	 * @return - o atributo procurado
	 */
	public String getAtributoSolicitacao(String idSolicitacao, String atributo) {
		return controler.getAtributoSolicitacao(idSolicitacao,
				atributo);
	}
	
	/**
	 * Metodo que retorna um objeto do tipo carona cadastrada no repositorio, procurando a mesma
	 * somente pelo o idcarona
	 * @param idCarona - o id da carona
	 * @return a carona procurada
	 * @throws CaronaException - referentes a problemas com a localização do objeto do tipo carona
	 */
	public Carona getCarona(String idCarona) throws CaronaException {
		if (UtilInfo.ehVazioOuNull(idCarona)) {
			throw new CaronaException("Carona Inválida");
		}
		return controler.localizaCaronaPorId(idCarona);
	}
}
