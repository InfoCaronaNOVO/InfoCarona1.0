package ufcg.si1.InfoCaronaMaven.Testes;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ufcg.si1.InfoCaronaMaven.Exception.ExceptionUsuario.EmailInvalidoException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionUsuario.EnderecoInvalidoException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionUsuario.LoginInvalidoException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionUsuario.NomeInvalidoException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionUsuario.SenhaInvalidoException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionUsuario.numeroMaximoException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.DataInvalidaException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.DestinoInvalidoException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.HoraInvalidaException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.OrigemInvalidaException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.SessaoInvalidaException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.VagaInvalidaException;
import ufcg.si1.InfoCaronaMaven.Sistema.Carona;
import ufcg.si1.InfoCaronaMaven.Sistema.Id;
import ufcg.si1.InfoCaronaMaven.Sistema.SolicitacaoDeVaga;
import ufcg.si1.InfoCaronaMaven.Sistema.Usuario;

public class TestaSolicitacaodeVaga {
	
	private Id id;
	private Usuario usuario1, usuario2;
	private Carona carona1, carona2;
	private SolicitacaoDeVaga solicitacao1, solicitacao2;
	
	@Before
	public void instanciandoObjetos() throws EmailInvalidoException, NomeInvalidoException, LoginInvalidoException, SenhaInvalidoException, EnderecoInvalidoException, SessaoInvalidaException, OrigemInvalidaException, DestinoInvalidoException, DataInvalidaException, HoraInvalidaException, VagaInvalidaException, numeroMaximoException {
		id = new Id(5);
		usuario1 = new Usuario("João","joao@mail.com","Rua Patati","joao123","joao");
		usuario2 = new Usuario("Maria","maria@mail.com","Rua Patata","maria123","maria");
		carona1 = new Carona("Campina Grande", "João Pessoa", "12/12/2012", "12:00", 2, id.gerarId(), usuario1.getNome());
		carona2 = new Carona("Bahia", "Campina Grande", "11/11/2012", "11:00", 1, id.gerarId(), usuario2.getNome());
		solicitacao1 = new SolicitacaoDeVaga(carona1, "Igreja do Rosario", id.gerarId(), usuario1);
		solicitacao2 = new SolicitacaoDeVaga(carona2, "Pelourinho", id.gerarId(), usuario2);
	}

	//Testando Construtor
	@Test
	public void testaSolicitacaoDeVaga() throws numeroMaximoException {
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
		Assert.assertEquals("João", solicitacao1.getDonoDaCarona());
		Assert.assertEquals("Maria", solicitacao2.getDonoDaCarona());
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
	public void testaGetAtributoSolicitacao(){
		Assert.assertEquals(solicitacao1.getAtributoSolicitacao("origem"), solicitacao1.getOrigem());
		Assert.assertEquals(solicitacao1.getAtributoSolicitacao("destino"), solicitacao1.getDestino());
		Assert.assertEquals(solicitacao2.getAtributoSolicitacao("Dono da carona"), solicitacao2.getDonoDaCarona());
		Assert.assertEquals(solicitacao2.getAtributoSolicitacao("Ponto de Encontro"), solicitacao2.getPonto());
	}
	
	@Test
	public void testaGetCarona() {
		
	}
}
