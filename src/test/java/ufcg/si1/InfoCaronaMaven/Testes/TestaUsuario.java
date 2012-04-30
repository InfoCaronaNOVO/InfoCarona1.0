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
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.SessaoInexistenteException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.SessaoInvalidaException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.VagaInvalidaException;
import ufcg.si1.InfoCaronaMaven.Sistema.Id;
import ufcg.si1.InfoCaronaMaven.Sistema.Usuario;


public class TestaUsuario {
	private Usuario usuario1, usuario2, usuario3, usuario4;
	private Id id;
	
	@Before
	public void criaUsuariosValidos() throws EmailInvalidoException, NomeInvalidoException, LoginInvalidoException, SenhaInvalidoException, EnderecoInvalidoException {
		usuario1 = new Usuario("Elvis Presley", "elvispresley@rock.com", "Rua do Sucesso", "123mudar", "presley");
		usuario2 = new Usuario("Michael Jackson", "michaeljackson@pop.com", "Rua da Felicidade", "minhasenhasecreta", "michael");
		usuario3 = new Usuario("Princess Dianna", "dianna@princess.com", "Rua da Beleza", "soulinda", "princess");
		usuario4 = new Usuario("Bob Marley", "marley@reggae.com", "Rua da Tranquilidade", "pazeamor", "bob");
		id = new Id(5);
	}
	
	//Metodos auxiliares
	private void criaUsuario1() throws Exception{
		usuario1 = new Usuario("wallison", "walli@email", "UFCG", "1234", "walli");
	}
	
	private void criaUsuario2() throws Exception{
		usuario2 = new Usuario("", "walli@email", "UFCG", "1234", "walli");
	}
	
	private void criaUsuario3() throws Exception{
		usuario2 = new Usuario(null, "walli@email", "UFCG", "1234", "walli");
	}
	
	private void criaUsuario4() throws Exception{
		usuario3 = new Usuario("gilles", "", "UFCG", "4321", "gillesVovo");
	}
	
	private void criaUsuario5() throws Exception{
		usuario3 = new Usuario("gilles", null, "UFCG", "4321", "gillesVovo");
	}
	
	private void criaUsuario6() throws Exception{
		usuario4 = new Usuario("felipe", "felipe@email", "UFCG", "1234", "");
	}
	
	private void criaUsuario7() throws Exception{
		usuario4 = new Usuario("felipe", "felipe@email", "UFCG", "1234", null);
	}
	
	//Testando Construtor
	@Test
	public void testaUsuario() throws Exception {
		try {
			criaUsuario1();
		} catch (Exception e) {
			Assert.fail("Usuario com parametros corretos mas esta retornando erro.");
		}
		
		try{
			criaUsuario2();
			Assert.fail("Nome do usuario nao pode ser vazio.");
		}catch(NomeInvalidoException e){
			Assert.assertEquals("Nome inválido", e.getMessage());
		}
		
		try{
			criaUsuario3();
			Assert.fail("Nome do usuario nao pode ser null.");
		}catch(NomeInvalidoException e){
			Assert.assertEquals("Nome inválido", e.getMessage());
		}
		
		try{
			criaUsuario4();
			Assert.fail("Email do usuario nao pode ser vazio.");
		}catch(EmailInvalidoException e){
			Assert.assertEquals("Email inválido", e.getMessage());
		}
		
		try{
			criaUsuario5();
			Assert.fail("Email do usuario nao pode ser null.");
		}catch(EmailInvalidoException e){
			Assert.assertEquals("Email inválido", e.getMessage());
		}
		
		try{
			criaUsuario6();
			Assert.fail("Login do usuario nao pode ser vazio.");
		}catch(LoginInvalidoException e){
			Assert.assertEquals("Login inválido", e.getMessage());
		}
		
		try{
			criaUsuario7();
			Assert.fail("Login do usuario nao pode ser null.");
		}catch(LoginInvalidoException e){
			Assert.assertEquals("Login inválido", e.getMessage());
		}
	}
	
	//Testando Metodos Get
	@Test
	public void testaGetNome() {
		Assert.assertEquals("Elvis Presley", usuario1.getNome());
		Assert.assertEquals("Michael Jackson", usuario2.getNome());
		Assert.assertEquals("Princess Dianna", usuario3.getNome());
		Assert.assertEquals("Bob Marley", usuario4.getNome());		
	}
	
	@Test
	public void testaGetEmail() {
		Assert.assertEquals("elvispresley@rock.com", usuario1.getEmail());
		Assert.assertEquals("michaeljackson@pop.com", usuario2.getEmail());
		Assert.assertEquals("dianna@princess.com", usuario3.getEmail());
		Assert.assertEquals("marley@reggae.com", usuario4.getEmail());
	}
	
	@Test
	public void testaGetEndereco() {
		Assert.assertEquals("Rua do Sucesso", usuario1.getEndereco());
		Assert.assertEquals("Rua da Felicidade", usuario2.getEndereco());
		Assert.assertEquals("Rua da Beleza", usuario3.getEndereco());
		Assert.assertEquals("Rua da Tranquilidade", usuario4.getEndereco());
	}
	
	@Test
	public void testaGetSenha() {
		Assert.assertEquals("123mudar", usuario1.getSenha());
		Assert.assertEquals("minhasenhasecreta", usuario2.getSenha());
		Assert.assertEquals("soulinda", usuario3.getSenha());
		Assert.assertEquals("pazeamor", usuario4.getSenha());
	}
	
	@Test
	public void testaGetLogin() {
		Assert.assertEquals("presley", usuario1.getLogin());
		Assert.assertEquals("michael", usuario2.getLogin());
		Assert.assertEquals("princess", usuario3.getLogin());
		Assert.assertEquals("bob", usuario4.getLogin());
	}
	
	@Test
	public void testaGetListaDeSolicitacaoDeVagas(){
		Assert.assertTrue(usuario1.getListaDeSolicitacaoDeVagas().size() == 0);
		Assert.assertTrue(usuario2.getListaDeSolicitacaoDeVagas().size() == 0);
		Assert.assertTrue(usuario3.getListaDeSolicitacaoDeVagas().size() == 0);
		Assert.assertTrue(usuario4.getListaDeSolicitacaoDeVagas().size() == 0);
	}
	
	@Test
	public void testaGetCaronasSeguras() {
		Assert.assertTrue(usuario1.getCaronasSeguras() == 0);
		Assert.assertTrue(usuario2.getCaronasSeguras() == 0);
		Assert.assertTrue(usuario3.getCaronasSeguras() == 0);
		Assert.assertTrue(usuario4.getCaronasSeguras() == 0);
	}
	
	@Test
	public void testaGetCaronasNaoFuncionaram() {
		Assert.assertTrue(usuario1.getCaronasNaoFuncionaram() == 0);
		Assert.assertTrue(usuario2.getCaronasNaoFuncionaram() == 0);
		Assert.assertTrue(usuario3.getCaronasNaoFuncionaram() == 0);
		Assert.assertTrue(usuario4.getCaronasNaoFuncionaram() == 0);
	}
	
	@Test
	public void testaGetFaltasEmVagas() {
		Assert.assertTrue(usuario1.getFaltasEmVagas() == 0);
		Assert.assertTrue(usuario2.getFaltasEmVagas() == 0);
		Assert.assertTrue(usuario3.getFaltasEmVagas() == 0);
		Assert.assertTrue(usuario4.getFaltasEmVagas() == 0);
	}
	
	@Test
	public void testaGetPresencaEmVagas() {
		Assert.assertTrue(usuario1.getPresencaEmVagas() == 0);
		Assert.assertTrue(usuario2.getPresencaEmVagas() == 0);
		Assert.assertTrue(usuario3.getPresencaEmVagas() == 0);
		Assert.assertTrue(usuario4.getPresencaEmVagas() == 0);
	}
	
	@Test
	public void testaGetCaronas() {
		Assert.assertTrue(usuario1.getCaronas().size() == 0);
		Assert.assertTrue(usuario2.getCaronas().size() == 0);
		Assert.assertTrue(usuario3.getCaronas().size() == 0);
		Assert.assertTrue(usuario4.getCaronas().size() == 0);
	}
	
	@Test
	public void testaGetSolicitacaoAceitas() {
		Assert.assertTrue(usuario1.getSolicitacaoAceitas().size() == 0);
		Assert.assertTrue(usuario2.getSolicitacaoAceitas().size() == 0);
		Assert.assertTrue(usuario3.getSolicitacaoAceitas().size() == 0);
		Assert.assertTrue(usuario4.getSolicitacaoAceitas().size() == 0);
	}

	//Testando Metodos Set
	@Test
	public void testaSetNome() throws NomeInvalidoException {
		Assert.assertEquals("Elvis Presley", usuario1.getNome());
		try {
			usuario1.setNome("");
			Assert.fail("O nome do usuario nao pode ser vazio.");
		} catch (NomeInvalidoException e) {
			Assert.assertEquals("Nome inválido", e.getMessage());
		}
		Assert.assertEquals("Elvis Presley", usuario1.getNome());
		
		Assert.assertEquals("Michael Jackson", usuario2.getNome());
		try {
			usuario2.setNome(null);
			Assert.fail("O nome do usuario nao pode ser null.");
		} catch (NomeInvalidoException e) {
			Assert.assertEquals("Nome inválido", e.getMessage());
		}
		Assert.assertEquals("Michael Jackson", usuario2.getNome());
		
		Assert.assertEquals("Princess Dianna", usuario3.getNome());
		try {
			usuario3.setNome("Dianna       ");
		} catch (NomeInvalidoException e) {
			Assert.assertEquals("Nome inválido", e.getMessage());
		}
		Assert.assertEquals("Dianna", usuario3.getNome());
		
		Assert.assertEquals("Bob Marley", usuario4.getNome());
		try {
			usuario4.setNome("            Bob Marley              ");
		} catch (NomeInvalidoException e) {
			Assert.assertEquals("Nome inválido", e.getMessage());
		}
		Assert.assertEquals("Bob Marley", usuario4.getNome());		
	}

	@Test
	public void testaSetEmail() throws EmailInvalidoException {
		Assert.assertEquals("elvispresley@rock.com", usuario1.getEmail());
		try {
			usuario1.setEmail("");
			Assert.fail("Email do usuario nao pode ser vazio.");
		} catch (EmailInvalidoException e) {
			Assert.assertEquals("Email inválido", e.getMessage());
		}
		Assert.assertEquals("elvispresley@rock.com", usuario1.getEmail());
		
		Assert.assertEquals("michaeljackson@pop.com", usuario2.getEmail());
		try {
			usuario2.setEmail(null);
			Assert.fail("O email do usuario nao pode ser null.");
		} catch (EmailInvalidoException e) {
			Assert.assertEquals("Email inválido", e.getMessage());
		}
		Assert.assertEquals("michaeljackson@pop.com", usuario2.getEmail());
		
		Assert.assertEquals("dianna@princess.com", usuario3.getEmail());
		try {
			usuario3.setEmail("Dianna       @princess.com");
			Assert.fail("Nao pode conter espacos no email do usuario.");
		} catch (EmailInvalidoException e) {
			Assert.assertEquals("Email inválido", e.getMessage());
		}
		Assert.assertEquals("dianna@princess.com", usuario3.getEmail());
		
		Assert.assertEquals("marley@reggae.com", usuario4.getEmail());
		try {
			usuario4.setEmail("            Bob Marley              @reggae.co/m");
			Assert.fail("O email do usuario nao pode conter espacos.");
		} catch (EmailInvalidoException e) {
			Assert.assertEquals("Email inválido", e.getMessage());
		}
		Assert.assertEquals("marley@reggae.com", usuario4.getEmail());
	}

	@Test
	public void testaSetSenha() {
		Assert.assertEquals("123mudar", usuario1.getSenha());
		try {
			usuario1.setSenha("");
			Assert.fail("A senha do usuario nao pode ser vazia.");
		} catch (SenhaInvalidoException e) {
			Assert.assertEquals("Senha inválido", e.getMessage());
		}
		Assert.assertEquals("123mudar", usuario1.getSenha());
		
		Assert.assertEquals("minhasenhasecreta", usuario2.getSenha());
		try {
			usuario2.setSenha(null);
			Assert.fail("A senha do usuario nao pode ser null.");
		} catch (SenhaInvalidoException e) {
			Assert.assertEquals("Senha inválido", e.getMessage());
		}
		Assert.assertEquals("minhasenhasecreta", usuario2.getSenha());
		
		Assert.assertEquals("soulinda", usuario3.getSenha());
		try {
			usuario3.setSenha("SoUlInDa");
		} catch (SenhaInvalidoException e) {
			Assert.assertEquals("Senha inválido", e.getMessage());
		}
		Assert.assertEquals("SoUlInDa", usuario3.getSenha());
		
		Assert.assertEquals("pazeamor", usuario4.getSenha());
		try {
			usuario4.setSenha("   pazeamor   ");
		} catch (SenhaInvalidoException e) {
			Assert.assertEquals("Senha inválido", e.getMessage());
		}
		Assert.assertEquals("pazeamor", usuario4.getSenha());		
	}


	@Test
	public void testaSetCaronasNaoFuncionaram() {
		Assert.assertFalse(usuario1.getCaronasNaoFuncionaram()<0);
		Assert.assertFalse(usuario2.getCaronasNaoFuncionaram()<0);
		Assert.assertFalse(usuario3.getCaronasNaoFuncionaram()<0);
		Assert.assertFalse(usuario4.getCaronasNaoFuncionaram()<0);
		
		Assert.assertTrue(usuario1.getCaronasNaoFuncionaram()==0);
		usuario1.setCaronasNaoFuncionaram();
		usuario1.setCaronasNaoFuncionaram();
		Assert.assertTrue(usuario1.getCaronasNaoFuncionaram()==2);
		
		Assert.assertTrue(usuario2.getCaronasNaoFuncionaram()==0);
		usuario2.setCaronasNaoFuncionaram();
		Assert.assertTrue(usuario2.getCaronasNaoFuncionaram()==1);
	}
	
	@Test
	public void testaSetCaronasSeguras() {
		Assert.assertFalse(usuario1.getCaronasSeguras()<0);
		Assert.assertFalse(usuario2.getCaronasSeguras()<0);
		Assert.assertFalse(usuario3.getCaronasSeguras()<0);
		Assert.assertFalse(usuario4.getCaronasSeguras()<0);
		
		Assert.assertTrue(usuario3.getCaronasSeguras()==0);
		usuario3.setCaronasSeguras();
		usuario3.setCaronasSeguras();
		Assert.assertTrue(usuario3.getCaronasSeguras()==2);
		
		Assert.assertTrue(usuario4.getCaronasSeguras()==0);
		usuario4.setCaronasSeguras();
		Assert.assertTrue(usuario4.getCaronasSeguras()==1);
	}

	@Test
	public void testaSetPresencaEmVagas() {
		Assert.assertTrue(usuario1.getPresencaEmVagas()==0);
		Assert.assertTrue(usuario2.getPresencaEmVagas()==0);
		Assert.assertTrue(usuario3.getPresencaEmVagas()==0);
		Assert.assertTrue(usuario4.getPresencaEmVagas()==0);
		
		usuario1.setPresencaEmVagas();
		usuario1.setPresencaEmVagas();
		Assert.assertTrue(usuario1.getPresencaEmVagas()==2);
		
		usuario2.setPresencaEmVagas();
		usuario2.setPresencaEmVagas();
		usuario2.setPresencaEmVagas();
		Assert.assertTrue(usuario2.getPresencaEmVagas()==3);
		
	}

	@Test
	public void testaSetFaltasEmVagas() {
		Assert.assertTrue(usuario1.getFaltasEmVagas() == 0);
		Assert.assertTrue(usuario2.getFaltasEmVagas() == 0);
		Assert.assertTrue(usuario3.getFaltasEmVagas() == 0);
		Assert.assertTrue(usuario4.getFaltasEmVagas() == 0);
		
		usuario3.setFaltasEmVagas();
		usuario3.setFaltasEmVagas();
		usuario3.setFaltasEmVagas();
		usuario3.setFaltasEmVagas();
		Assert.assertTrue(usuario3.getFaltasEmVagas() == 4);
		
		usuario4.setFaltasEmVagas();
		usuario4.setFaltasEmVagas();
		usuario4.setFaltasEmVagas();
		Assert.assertTrue(usuario4.getFaltasEmVagas() == 3);
		
	}

	//Testando Metodos especificos
	@Test
	public void testaCadastrarCarona() throws SessaoInvalidaException, SessaoInexistenteException, OrigemInvalidaException, DestinoInvalidoException, DataInvalidaException, HoraInvalidaException, VagaInvalidaException, numeroMaximoException {
		usuario1.cadastrarCarona("Campina Grande", "João Pessoa", "22/12/2012", "22:00", 3, id.gerarId());
		usuario2.cadastrarCarona("Campina Grande", "Recife", "10/10/2012", "10:00", 1, id.gerarId());
		usuario3.cadastrarCarona("João Pessoa", "Natal", "06/08/2012", "12:00", 2, id.gerarId());
	}

	@Test
	public void testaToString() {
		Assert.assertEquals("Nome: Elvis Presley Login: presley", usuario1.toString());
		Assert.assertEquals("Nome: Michael Jackson Login: michael", usuario2.toString());
		Assert.assertEquals("Nome: Princess Dianna Login: princess", usuario3.toString());
		Assert.assertEquals("Nome: Bob Marley Login: bob", usuario4.toString());
	}

	
	@Test
	public void testaEquals() {
		Assert.assertTrue(usuario1.equals(usuario1));
		Assert.assertTrue(usuario2.equals(usuario2));
		Assert.assertTrue(usuario3.equals(usuario3));
		Assert.assertTrue(usuario4.equals(usuario4));
		Assert.assertFalse(usuario1.equals(usuario2));
		Assert.assertFalse(usuario1.equals(usuario3));
		Assert.assertFalse(usuario1.equals(usuario4));
		Assert.assertFalse(usuario2.equals(usuario3));
		Assert.assertFalse(usuario2.equals(usuario4));
		Assert.assertFalse(usuario3.equals(usuario4));
	}
}
