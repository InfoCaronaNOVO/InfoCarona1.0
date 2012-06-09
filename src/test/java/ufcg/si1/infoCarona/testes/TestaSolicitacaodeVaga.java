package ufcg.si1.infoCarona.testes;

import java.util.Calendar;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ufcg.si1.infoCarona.model.Id;
import ufcg.si1.infoCarona.model.LoggerException;
import ufcg.si1.infoCarona.model.NumeroMaximoException;
import ufcg.si1.infoCarona.model.carona.Carona;
import ufcg.si1.infoCarona.model.carona.CaronaComum;
import ufcg.si1.infoCarona.model.carona.CaronaException;
import ufcg.si1.infoCarona.model.negociacao.SolicitacaoDeVaga;
import ufcg.si1.infoCarona.model.usuario.Usuario;
import util.UtilInfo;


public class TestaSolicitacaodeVaga {
	
	private Id id;
	private Calendar calendar;
	private Usuario usuario1, usuario2;
	private Carona carona1, carona2;
	private SolicitacaoDeVaga solicitacao1, solicitacao2;
	
	@Before
	public void instanciandoObjetos() throws LoggerException, CaronaException, NumeroMaximoException {
		id = Id.getInstance(5);
		usuario1 = new Usuario("João","joao@mail.com","Rua Patati","joao123","joao");
		usuario2 = new Usuario("Maria","maria@mail.com","Rua Patata","maria123","maria");
		
		calendar = UtilInfo.converteStringEmCalendar("12/12/2013", "12:00");
		carona1 = new CaronaComum("Campina Grande", "João Pessoa", calendar, 2, id.gerarId(), usuario1);
		
		calendar = UtilInfo.converteStringEmCalendar("11/11/2013", "11:00");
		carona2 = new CaronaComum("Bahia", "Campina Grande", calendar , 1, id.gerarId(), usuario2);
		solicitacao1 = new SolicitacaoDeVaga(carona1, "Igreja do Rosario", id.gerarId(), usuario1);
		solicitacao2 = new SolicitacaoDeVaga(carona2, "Pelourinho", id.gerarId(), usuario2);
	}

	//Testando Construtor
	@Test
	public void testaSolicitacaoDeVaga() throws NumeroMaximoException {
		solicitacao1 = new SolicitacaoDeVaga(carona1, "Igreja do Rosario", id.gerarId(), usuario1);
		solicitacao2 = new SolicitacaoDeVaga(carona2, "Pelourinha", id.gerarId(), usuario2);		
	}

	//Testando Metodos Get
	@Test
	public void testaGetDonoSolicitacao() {
		Assert.assertFalse(usuario1.equals(solicitacao2.getDonoSolicitacao()));
		Assert.assertFalse(usuario2.equals(solicitacao1.getDonoSolicitacao()));
		Assert.assertTrue(usuario1.equals(solicitacao1.getDonoSolicitacao()));
		Assert.assertTrue(usuario2.equals(solicitacao2.getDonoSolicitacao()));
	}
	
	@Test
	public void testaGetOrigem(){
		Assert.assertEquals("Campina Grande", solicitacao1.getOrigem());
		Assert.assertEquals("Bahia", solicitacao2.getOrigem());
	}
	
	@Test
	public void testaGetDestino() {
		Assert.assertEquals("João Pessoa", solicitacao1.getDestino());
		Assert.assertEquals("Campina Grande", solicitacao2.getDestino());
	}
	
	@Test
	public void testaGetDonoDaCarona() {
		Assert.assertEquals("João", solicitacao1.getDonoDaCarona().getNome());
		Assert.assertEquals("Maria", solicitacao2.getDonoDaCarona().getNome());
	}
	
	@Test
	public void testaGetPonto() {
		Assert.assertEquals("Igreja do Rosario",solicitacao1.getPonto());
		Assert.assertEquals("Pelourinho",solicitacao2.getPonto());
	}
	
	@Test
	public void testaGetIdSolicitacao() {
		//nunca um id deve ser igual a outro
		Assert.assertFalse(solicitacao1.getIdSolicitacao()==solicitacao2.getIdSolicitacao());
	}
	
	@Test
	public void testaGetCarona() {
		
	}
}
