package ufcg.si1.infoCarona.model.sistema;

import java.util.LinkedList;
import java.util.List;

import ufcg.si1.infoCarona.model.ArgumentoInexistenteException;
import ufcg.si1.infoCarona.model.NumeroMaximoException;
import ufcg.si1.infoCarona.model.carona.Carona;
import ufcg.si1.infoCarona.model.carona.CaronaException;
import ufcg.si1.infoCarona.model.negociacao.SolicitacaoDeVaga;
import ufcg.si1.infoCarona.model.negociacao.SugestaoDePontoDeEncontro;
import ufcg.si1.infoCarona.model.usuario.Usuario;

public class SistemaNegociacao {
	
	private SistemaRaiz sistema;
	
	public SistemaNegociacao(){
		sistema = SistemaRaiz.getInstance();
	}
	
	public String sugerirPontoEncontro(String idSessao, String idCarona,
			String pontos) throws CaronaException,
			NumeroMaximoException, ArgumentoInexistenteException {
		if (pontos == null || pontos.equals("")) {
			throw new IllegalArgumentException("Ponto Inválido");
		}
		Usuario usuarioTemp = sistema.procuraUsuarioLogado(idSessao);
		Carona caronaTemp = sistema.getCarona(idCarona);
		if(sistema.usuarioJahEstahNaCarona(usuarioTemp, caronaTemp)){
			throw new IllegalArgumentException("Ponto Inválido");
		}
		
		return usuarioTemp.sugerirPontoEncontro(pontos, caronaTemp,
				sistema.id.gerarId(), usuarioTemp);

	}
	
	public String solicitarVagaPontoEncontro(String idSessao, String idCarona,
			String ponto) throws CaronaException,NumeroMaximoException, ArgumentoInexistenteException {

		Usuario usuarioTemp = sistema.procuraUsuarioLogado(idSessao);
		Carona carona = sistema.controleRepositorio.localizaCaronaPorId(idCarona);
		if (ponto.equals("")) {
			ponto = null; // subtende-se que o usuario aceita os pontos que o
							// dono da carona indicou
		}

		return usuarioTemp.solicitarVagaPontoEncontro(ponto, carona,
				sistema.id.gerarId(), usuarioTemp);
	}
	
	public void responderSugestaoPontoEncontro(String idSessao,
			String idCarona, String idSugestao, String pontos)
			throws CaronaException, ArgumentoInexistenteException{

		Usuario usuarioTemp = sistema.procuraUsuarioLogado(idSessao);
		SugestaoDePontoDeEncontro sugestaoTemp = sistema.controleRepositorio.getSugestaoId(idSugestao, idCarona);
		Carona caronaTemp = sistema.controleRepositorio.localizaCaronaPorId(idCarona);

		usuarioTemp.responderSugestaoPontoEncontro(sugestaoTemp, pontos, caronaTemp);
	}
	
	public void aceitarSolicitacaoPontoEncontro(String idSessao,
			String idSolicitacao) throws ArgumentoInexistenteException  {

		Usuario usuarioTemp = sistema.procuraUsuarioLogado(idSessao);
		SolicitacaoDeVaga solicitacao = sistema.controleRepositorio
				.localizaSolicitacaoPorId(idSolicitacao);

		usuarioTemp.aceitarSolicitacaoPontoEncontro(solicitacao);
	}
	
	public void rejeitarSolicitacao(String idSessao, String idSolicitacao) throws ArgumentoInexistenteException{
		Usuario usuarioTemp = sistema.procuraUsuarioLogado(idSessao);
		SolicitacaoDeVaga solicitacao = sistema.controleRepositorio
				.localizaSolicitacaoPorId(idSolicitacao);
		usuarioTemp.rejeitarSolicitacao(solicitacao);

	}

	public void desistirRequisicao(String idSessao, String idCarona,
			String idSolicitacao) throws CaronaException, ArgumentoInexistenteException {

		Usuario usuarioTemp = sistema.procuraUsuarioLogado(idSessao);
		Carona caronaTemp = sistema.controleRepositorio.localizaCaronaPorId(idCarona);
		SolicitacaoDeVaga solicitacaoTemp = sistema.controleRepositorio.localizaSolicitacaoPorId(idSolicitacao);
		usuarioTemp.desistirRequisicao(solicitacaoTemp, caronaTemp);

	}
	
	public LinkedList<String> getPontosSugeridos(String idCarona) throws CaronaException {
		LinkedList<String> retorno = new LinkedList<String>();
		Carona caronaTemp = sistema.controleRepositorio.localizaCaronaPorId(idCarona);
		List<SugestaoDePontoDeEncontro> listaSugestoes = caronaTemp.getListaDeSugestoes();
		for(SugestaoDePontoDeEncontro sugestao : listaSugestoes){
			for (String ponto : sugestao.getListaDeSugestaoDePontosDeEncontro()) {
				retorno.add(ponto);
			}
		}
		return retorno;
	}
	
	public List<String> getPontosEncontro(String idCarona)
			throws CaronaException {
		List<String> retorno = new LinkedList<String>();
        List<SugestaoDePontoDeEncontro> sugestoes = sistema.controleRepositorio.localizaCaronaPorId(idCarona)
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
	
	public List<SolicitacaoDeVaga> getSolicitacoesConfirmadas(String idCarona) throws CaronaException {
		return sistema.controleRepositorio.localizaCaronaPorId(idCarona).getSolicitacoesConfirmadas();
	}

	public List<SolicitacaoDeVaga> getSolicitacoesPendentes(String idCarona)
			throws CaronaException {
		return sistema.controleRepositorio.localizaCaronaPorId(idCarona)
				.getSolicitacoesPendentes();
	}
	
	public String getAtributoSolicitacao(String idSolicitacao, String atributo) {
		return sistema.controleRepositorio.getAtributoSolicitacao(idSolicitacao,
				atributo);
	}
}
