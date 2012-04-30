package ufcg.si1.InfoCaronaMaven.Testes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ufcg.si1.InfoCaronaMaven.Exception.ExceptionUsuario.numeroMaximoException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.DataInvalidaException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.DestinoInvalidoException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.HoraInvalidaException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.OrigemInvalidaException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.SessaoInvalidaException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.VagaInvalidaException;
import ufcg.si1.InfoCaronaMaven.Sistema.Carona;
import ufcg.si1.InfoCaronaMaven.Sistema.Id;

public class TestaCarona {
	private Carona carona1, carona2, carona3, carona4;
	private Id id;
	
	private void criaCarona1() throws Exception {
		carona1 = new Carona("Campina Grande", "João Pessoa", "22/10/2012", "10:00", 3, id.gerarId(), "Jose");
	}
	
	private void criaCarona2() throws Exception {
		carona2 = new Carona("", "Natal", "02/06/2012", "03:00", 2, id.gerarId(), "Marcus");
	}
	
	private void criaCarona3() throws Exception {
		carona2 = new Carona(null, "Natal", "02/06/2012", "03:00", 2, id.gerarId(), "Marcus");
	}
	
	private void criaCarona4() throws Exception {
		carona3 = new Carona("Natal", "", "11/09/2012", "00:00", 1, id.gerarId(), "Flavio");
	}
	
	private void criaCarona5() throws Exception {
		carona3 = new Carona("Natal", null, "11/09/2012", "00:00", 1, id.gerarId(), "Flavio");
	}
	
	private void criaCarona6() throws Exception {
		carona4 = new Carona("Recife", "João Pessoa", "11/09/2009", "00:00", 1, id.gerarId(), "Maria");
	}
	
	private void criaCarona7() throws Exception {
		carona4 = new Carona("Recife", "João Pessoa", "20/03/2013", "25:00", 1, id.gerarId(), "Maria");
	}
	
	private void criaCarona8() throws Exception {
		carona4 = new Carona("Campina Grande", "João Pessoa", "22/10/2012", "10:00", -2, id.gerarId(), "Jose");
	}
	
	private void criaCarona9() throws Exception {
		carona4 = new Carona("Campina Grande", "João Pessoa", "22/10/2012", "10:00", 0, id.gerarId(), "Jose");
	}
	
	@Before
	public void instanciandoObjetos() throws SessaoInvalidaException, OrigemInvalidaException, DestinoInvalidoException, DataInvalidaException, HoraInvalidaException, VagaInvalidaException, numeroMaximoException {
		id = new Id(5);
		carona1 = new Carona("Campina Grande", "João Pessoa", "22/10/2012", "10:00", 1, id.gerarId(), "Jose");
		carona2 = new Carona("João Pessoa", "Natal", "06/06/2012", "09:00", 2, id.gerarId(), "Mario");
		carona3 = new Carona("Natal", "Recife", "10/05/2012", "08:00", 3, id.gerarId(), "Fernanda");
		carona4 = new Carona("Recife", "Campina Grande", "17/07/2012", "07:00", 4, id.gerarId(), "Isabela");
	}
	
	//Testando Construtor
	@Test
	public void testaCarona() throws Exception {
		try {
			criaCarona1();
		} catch (Exception e) {
			Assert.fail("Falhou ao criar uma carona com parametros corretos.");
		}
		
		try {
			criaCarona2();
			Assert.fail("Nao pode existir carona com origem vazia.");
		} catch (OrigemInvalidaException e) {
			Assert.assertEquals("Origem inválida", e.getMessage());
		}
		
		try {
			criaCarona3();
			Assert.fail("Nao pode existir carona com origem null.");
		} catch (OrigemInvalidaException e) {
			Assert.assertEquals("Origem inválida", e.getMessage());
		}
		
		try {
			criaCarona4();
			Assert.fail("Nao pode existir carona com destino vazio.");
		} catch (DestinoInvalidoException e) {
			Assert.assertEquals("Destino inválido", e.getMessage());
		}
		
		try {
			criaCarona5();
			Assert.fail("Nao pode existir carona com destino null.");
		} catch (DestinoInvalidoException e) {
			Assert.assertEquals("Destino inválido", e.getMessage());
		}
		
		try {
			criaCarona6();
			Assert.fail("Nao pode existir carona com data passada.");
		} catch (DataInvalidaException e) {
			Assert.assertEquals("Data inválida", e.getMessage());
		}
		
		try {
			criaCarona7();
			Assert.fail("Nao pode existir carona com hora invalida.");
		} catch (HoraInvalidaException e) {
			Assert.assertEquals("Hora inválida", e.getMessage());
		}
		
		try {
			criaCarona8();
			Assert.fail("Nao pode existir carona com o numero de vagas menor que 0.");
		} catch (VagaInvalidaException e) {
			Assert.assertEquals("Vaga inválida", e.getMessage());
		}
		
		try {
			criaCarona9();
			Assert.fail("Nao pode existir carona com o numero de vagas igual a 0.");
		} catch (VagaInvalidaException e) {
			Assert.assertEquals("Vaga inválida", e.getMessage());
		}
	}

	//Testando Metodos Get
	@Test
	public void testaGetListaDeSolicitacao() {
		Assert.assertTrue("A lista de solicitacao da carona tem que comecar vazia.", carona1.getListaDeSolicitacao().size() == 0);
		Assert.assertTrue("A lista de solicitacao da carona tem que comecar vazia.", carona2.getListaDeSolicitacao().size() == 0);
		Assert.assertTrue("A lista de solicitacao da carona tem que comecar vazia.", carona3.getListaDeSolicitacao().size() == 0);
		Assert.assertTrue("A lista de solicitacao da carona tem que comecar vazia.", carona4.getListaDeSolicitacao().size() == 0);
	}
	
	@Test
	public void testaGetListaDeSugestoes() {
		Assert.assertTrue("A lista de sugestoes da carona tem que comecar vazia.", carona1.getListaDeSugestoes().size() == 0);
		Assert.assertTrue("A lista de sugestoes da carona tem que comecar vazia.", carona2.getListaDeSugestoes().size() == 0);
		Assert.assertTrue("A lista de sugestoes da carona tem que comecar vazia.", carona3.getListaDeSugestoes().size() == 0);
		Assert.assertTrue("A lista de sugestoes da carona tem que comecar vazia.", carona4.getListaDeSugestoes().size() == 0);
	}
	
	@Test
	public void testaGetOrigem() {
		Assert.assertEquals("Campina Grande", carona1.getOrigem());
		Assert.assertEquals("João Pessoa", carona2.getOrigem());
		Assert.assertEquals("Natal", carona3.getOrigem());
		Assert.assertEquals("Recife", carona4.getOrigem());
	}

	@Test
	public void testaGetDonoDaCarona() {
		Assert.assertEquals("Jose", carona1.getDonoDaCarona());
		Assert.assertEquals("Mario", carona2.getDonoDaCarona());
		Assert.assertEquals("Fernanda", carona3.getDonoDaCarona());
		Assert.assertEquals("Isabela", carona4.getDonoDaCarona());
	}
	
	@Test
	public void testaGetDestino() {
		Assert.assertEquals("João Pessoa", carona1.getDestino());
		Assert.assertEquals("Natal", carona2.getDestino());
		Assert.assertEquals("Recife", carona3.getDestino());
		Assert.assertEquals("Campina Grande", carona4.getDestino());
	}
	
	@Test
	public void testaGetData() {
		Assert.assertEquals("22/10/2012", carona1.getData());
		Assert.assertEquals("06/06/2012", carona2.getData());
		Assert.assertEquals("10/05/2012", carona3.getData());
		Assert.assertEquals("17/07/2012", carona4.getData());
	}
	
	@Test
	public void testaGetHora() {
		Assert.assertEquals("10:00", carona1.getHora());
		Assert.assertEquals("09:00", carona2.getHora());
		Assert.assertEquals("08:00", carona3.getHora());
		Assert.assertEquals("07:00", carona4.getHora());
	}
	
	@Test
	public void testaGetVagas() {
		Assert.assertFalse("Numero de vagas nao pode ser negativo.",carona1.getVagas() < 0);
		Assert.assertFalse("Numero de vagas nao pode ser negativo.",carona2.getVagas() < 0);
		Assert.assertFalse("Numero de vagas nao pode ser negativo.",carona3.getVagas() < 0);
		Assert.assertFalse("Numero de vagas nao pode ser negativo.",carona4.getVagas() < 0);
		
		Assert.assertFalse("Numero de vagas nao pode ser zero.",carona1.getVagas() == 0);
		Assert.assertFalse("Numero de vagas nao pode ser zero.",carona2.getVagas() == 0);
		Assert.assertFalse("Numero de vagas nao pode ser zero.",carona3.getVagas() == 0);
		Assert.assertFalse("Numero de vagas nao pode ser zero.",carona4.getVagas() == 0);
		
		Assert.assertTrue("Numero de vagas nao corresponde.",carona1.getVagas() == 1);
		Assert.assertTrue("Numero de vagas nao corresponde.",carona2.getVagas() == 2);
		Assert.assertTrue("Numero de vagas nao corresponde.",carona3.getVagas() == 3);
		Assert.assertTrue("Numero de vagas nao corresponde.",carona4.getVagas() == 4);
	}
	
	@Test
	public void testaGetIdCarona() {
		Assert.assertFalse("Id de carona tem que ser unico.", carona1.getIdCarona() == carona2.getIdCarona());
		Assert.assertFalse("Id de carona tem que ser unico.", carona1.getIdCarona() == carona3.getIdCarona());
		Assert.assertFalse("Id de carona tem que ser unico.", carona1.getIdCarona() == carona4.getIdCarona());
		Assert.assertFalse("Id de carona tem que ser unico.", carona2.getIdCarona() == carona3.getIdCarona());
		Assert.assertFalse("Id de carona tem que ser unico.", carona2.getIdCarona() == carona4.getIdCarona());
		Assert.assertFalse("Id de carona tem que ser unico.", carona3.getIdCarona() == carona4.getIdCarona());
	}
	
	@Test
	public void testaGetAtributo() {
		Assert.assertTrue("O atributo nao corresponde corretamente.",carona1.getAtributo("origem")==carona1.getOrigem());
		Assert.assertTrue("O atributo nao corresponde corretamente.",carona2.getAtributo("destino")==carona2.getDestino());
		Assert.assertTrue("O atributo nao corresponde corretamente.",carona3.getAtributo("data")==carona3.getData());
		Assert.assertTrue("O atributo nao corresponde corretamente.",carona4.getAtributo("vagas").equals(""+carona4.getVagas()));
	}
	
	@Test
	public void testaGetDadosCarona(){
		Assert.assertEquals("As informacoes da carona nao batem.","origem=Campina Grande destino=João Pessoa data=22/10/2012 hora=10:00 vagas=1", carona1.getDadosCarona());
		Assert.assertEquals("As informacoes da carona nao batem.","origem=João Pessoa destino=Natal data=06/06/2012 hora=09:00 vagas=2", carona2.getDadosCarona());
		Assert.assertEquals("As informacoes da carona nao batem.","origem=Natal destino=Recife data=10/05/2012 hora=08:00 vagas=3", carona3.getDadosCarona());
		Assert.assertEquals("As informacoes da carona nao batem.","origem=Recife destino=Campina Grande data=17/07/2012 hora=07:00 vagas=4", carona4.getDadosCarona());
	}
	
	@Test
	public void testaGetSolicitacoesConfirmadas() {
		Assert.assertTrue("Nao pode ter solicitacoes confirmadas quando a carona eh criada.", carona1.getSolicitacoesConfirmadas().size() == 0);
		Assert.assertTrue("Nao pode ter solicitacoes confirmadas quando a carona eh criada.", carona2.getSolicitacoesConfirmadas().size() == 0);
		Assert.assertTrue("Nao pode ter solicitacoes confirmadas quando a carona eh criada.", carona3.getSolicitacoesConfirmadas().size() == 0);
		Assert.assertTrue("Nao pode ter solicitacoes confirmadas quando a carona eh criada.", carona4.getSolicitacoesConfirmadas().size() == 0);
	}

	@Test
	public void testaGetSolicitacoesPendentes() {
		Assert.assertTrue("Nao pode ter solicitacoes pendentes quando a carona eh criada.", carona1.getSolicitacoesPendentes().size() == 0);
		Assert.assertTrue("Nao pode ter solicitacoes pendentes quando a carona eh criada.", carona2.getSolicitacoesPendentes().size() == 0);
		Assert.assertTrue("Nao pode ter solicitacoes pendentes quando a carona eh criada.", carona3.getSolicitacoesPendentes().size() == 0);
		Assert.assertTrue("Nao pode ter solicitacoes pendentes quando a carona eh criada.", carona4.getSolicitacoesPendentes().size() == 0);
	}
	
	//Testando Metodos Set
	@Test
	public void testaSetOrigem() {
		try {
			carona1.setOrigem("");
			Assert.fail("A carona nao pode ter origem vazia.");
		} catch (OrigemInvalidaException e) {
			Assert.assertEquals("Origem inválida", e.getMessage());
		}
		
		try {
			carona2.setOrigem(null);
			Assert.fail("A carona nao pode ter origem null.");
		} catch (OrigemInvalidaException e) {
			Assert.assertEquals("Origem inválida", e.getMessage());
		}
		
		try {
			carona3.setOrigem("- 09 $ %");
			Assert.fail("A origem da carona nao pode ter apenas caracteres especiais ou numeros.");
		} catch (OrigemInvalidaException e) {
			Assert.assertEquals("Origem inválida", e.getMessage());
		}
		
		Assert.assertEquals("Recife",carona4.getOrigem());
		try {
			carona4.setOrigem("Maceio");
		} catch (OrigemInvalidaException e) {
			Assert.assertEquals("Origem inválida", e.getMessage());
		}
		Assert.assertEquals("Maceio", carona4.getOrigem());
	}
	
	@Test
	public void testaSetDestino() {
		try {
			carona1.setDestino("");
			Assert.fail("A carona nao pode ter destino vazio.");
		} catch (DestinoInvalidoException e) {
			Assert.assertEquals("Destino inválido", e.getMessage());
		}
		
		try {
			carona2.setDestino(null);
			Assert.fail("A carona nao pode ter destino null.");
		} catch (DestinoInvalidoException e) {
			Assert.assertEquals("Destino inválido", e.getMessage());
		}
		
		try {
			carona3.setDestino("- 09 $ %");
			Assert.fail("O destino da carona nao pode ter apenas caracteres especiais ou numeros.");
		} catch (DestinoInvalidoException e) {
			Assert.assertEquals("Destino inválido", e.getMessage());
		}
		
		Assert.assertEquals("Campina Grande",carona4.getDestino());
		try {
			carona4.setDestino("Bahia");
		} catch (DestinoInvalidoException e) {
			Assert.assertEquals("Destino inválido", e.getMessage());
		}
		Assert.assertEquals("Bahia", carona4.getDestino());
	}
	
	@Test
	public void testaSetData() {
		try {
			carona1.setData("");
			Assert.fail("A data da carona nao pode ser vazia.");
		} catch (DataInvalidaException e) {
			Assert.assertEquals("Data inválida", e.getMessage());
		}
		
		try {
			carona2.setData(null);
			Assert.fail("A data da carona nao pode ser null.");
		} catch (DataInvalidaException e) {
			Assert.assertEquals("Data inválida", e.getMessage());
		}
		
		try {
			carona3.setData("21/13/2012");
			Assert.fail("A data da carona tem que ser v�lida.");
		} catch (DataInvalidaException e) {
			Assert.assertEquals("Data inválida", e.getMessage());
		}
		
		try {
			carona4.setData("01/01/2000");
			Assert.fail("A data da carona nao pode ser uma data passada.");
		} catch (DataInvalidaException e) {
			Assert.assertEquals("Data inválida", e.getMessage());
		}
		
		Assert.assertEquals("22/10/2012", carona1.getData());
		try {
			carona1.setData("01/01/2021");
		} catch (DataInvalidaException e) {
			Assert.assertEquals("Data inválida", e.getMessage());
		}
		Assert.assertEquals("01/01/2021", carona1.getData());
	}

	@Test
	public void testaSetHora() {
		try {
			carona1.setHora("");
			Assert.fail("A hora da carona nao pode ser vazia.");
		} catch (HoraInvalidaException e) {
			Assert.assertEquals("Hora inválida", e.getMessage());
		}
		
		try {
			carona2.setHora(null);
			Assert.fail("A hora da carona nao pode ser null.");
		} catch (HoraInvalidaException e) {
			Assert.assertEquals("Hora inválida", e.getMessage());
		}
		
		try {
			carona3.setHora("10:66");
			Assert.fail("A hora da carona deve ser uma hora válida");
		} catch (HoraInvalidaException e) {
			Assert.assertEquals("Hora inválida", e.getMessage());
		}
		
		Assert.assertEquals("07:00", carona4.getHora());
		try {
			carona4.setHora("23:40");
		} catch (HoraInvalidaException e) {
			Assert.assertEquals("Hora inválida", e.getMessage());
		}
		Assert.assertEquals("23:40", carona4.getHora());
	}
	
	@Test
	public void testaSetVagas() {
		try {
			carona1.setVagas(0);
			Assert.fail("A quantidade de vagas na carona nao pode ser 0.");
		} catch (VagaInvalidaException e) {
			Assert.assertEquals("Vaga inválida",e.getMessage());
		}
		
		try {
			carona2.setVagas(-2);
			Assert.fail("A quantidade de vagas na carona nao pode ser menor que 0.");
		} catch (VagaInvalidaException e) {
			Assert.assertEquals("Vaga inválida",e.getMessage());
		}
		
		Assert.assertTrue(carona3.getVagas() == 3);
		try {
			carona3.setVagas(10);
		} catch (VagaInvalidaException e) {
			Assert.assertEquals("Vaga inválida",e.getMessage());
		}
		Assert.assertTrue(carona3.getVagas() == 10);
		
		Assert.assertTrue(carona4.getVagas() == 4);
		try {
			carona4.setVagas(1);
		} catch (VagaInvalidaException e) {
			Assert.assertEquals("Vaga inválida",e.getMessage());
		}
		Assert.assertTrue(carona4.getVagas() == 1);
	}
	
	//Testando Metodos Especificos
	@Test
	public void testaToString(){
		Assert.assertEquals("Campina Grande para João Pessoa, no dia 22/10/2012, as 10:00", carona1.toString());
		Assert.assertEquals("João Pessoa para Natal, no dia 06/06/2012, as 09:00", carona2.toString());
		Assert.assertEquals("Natal para Recife, no dia 10/05/2012, as 08:00", carona3.toString());
		Assert.assertEquals("Recife para Campina Grande, no dia 17/07/2012, as 07:00", carona4.toString());
	}
	
	@Test
	public void testaEquals() {
		Assert.assertTrue(carona1.equals(carona1));
		Assert.assertTrue(carona2.equals(carona2));
		Assert.assertTrue(carona3.equals(carona3));
		Assert.assertTrue(carona4.equals(carona4));
		Assert.assertFalse(carona1.equals(carona2));
		Assert.assertFalse(carona1.equals(carona3));
		Assert.assertFalse(carona1.equals(carona4));
		Assert.assertFalse(carona2.equals(carona3));
		Assert.assertFalse(carona2.equals(carona4));
		Assert.assertFalse(carona3.equals(carona4));
	}
	
}

