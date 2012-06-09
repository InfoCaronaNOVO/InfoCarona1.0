package ufcg.si1.infoCarona.testes;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ufcg.si1.infoCarona.model.Id;
import ufcg.si1.infoCarona.model.LoggerException;
import ufcg.si1.infoCarona.model.NumeroMaximoException;
import ufcg.si1.infoCarona.model.carona.Carona;
import ufcg.si1.infoCarona.model.carona.CaronaComum;
import ufcg.si1.infoCarona.model.carona.CaronaException;
import ufcg.si1.infoCarona.model.carona.CaronaMunicipal;
import ufcg.si1.infoCarona.model.usuario.Usuario;
import util.UtilInfo;

public class TestaCarona {
	private Carona carona1, carona2, carona3, carona4, caronaMunicipal;
	private Id id;
	private Calendar calendar;
	
	
	
	private void criaCarona1() throws Exception {
		
		calendar = UtilInfo.converteStringEmCalendar("22/10/2013", "10:00");
		Usuario jose = new Usuario("Jose", "jose@email", "rua da paz", "12345", "Jose");
		carona1 = new CaronaComum("Campina Grande", "João Pessoa", calendar , 3, id.gerarId(), jose);
	}
	
	private void criaCarona2() throws Exception {
		calendar = UtilInfo.converteStringEmCalendar("02/06/2013", "03:00");
		Usuario marcus = new Usuario("Marcus", "marcus@email", "rua da paz", "12345", "Marcus");
		carona2 = new CaronaComum("", "Natal", calendar, 2, id.gerarId(), marcus);
	}
	
	private void criaCarona3() throws Exception {
		calendar = UtilInfo.converteStringEmCalendar("02/06/2013", "03:00");
		Usuario marcus = new Usuario("Marcus", "marcus@email", "rua da paz", "12345", "Marcus");
		carona2 = new CaronaComum(null, "Natal", calendar, 2, id.gerarId(), marcus);
	}
	
	private void criaCarona4() throws Exception {
		calendar = UtilInfo.converteStringEmCalendar("11/09/2013", "00:00");
		Usuario flavio = new Usuario("Flavio", "falvio@email", "rua da paz", "12345", "Flavio");
		carona3 = new CaronaComum("Natal", "", calendar, 1, id.gerarId(), flavio);
	}
	
	private void criaCarona5() throws Exception {
		calendar = UtilInfo.converteStringEmCalendar("11/09/2013", "00:00");
		Usuario flavio = new Usuario("Flavio", "falvio@email", "rua da paz", "12345", "Flavio");
		carona3 = new CaronaComum("Natal", null, calendar, 1, id.gerarId(), flavio);
	}
	
	private void criaCarona6() throws Exception {
		calendar = UtilInfo.converteStringEmCalendar("11/09/2009", "00:00");
		Usuario maria = new Usuario("Maria", "maria@email", "rua da paz", "12345", "Maria");
		carona4 = new CaronaComum("Recife", "João Pessoa", calendar, 1, id.gerarId(), maria);
	}
	
	private void criaCarona7() throws Exception {
		calendar = UtilInfo.converteStringEmCalendar("20/03/2013", "25:00");
		Usuario maria = new Usuario("Maria", "maria@email", "rua da paz", "12345", "Maria");
		carona4 = new CaronaComum("Recife", "João Pessoa", calendar, 1, id.gerarId(), maria);
	}
	
	private void criaCarona8() throws Exception {
		calendar = UtilInfo.converteStringEmCalendar("22/10/2013", "10:00");
		Usuario jose = new Usuario("Jose", "jose@email", "rua da paz", "12345", "Jose");
		carona4 = new CaronaComum("Campina Grande", "João Pessoa", calendar, -2, id.gerarId(), jose);
	}
	
	@Before
	public void instanciandoObjetos() throws CaronaException, NumeroMaximoException, LoggerException {
		id = Id.getInstance(5);
		
		Usuario jose = new Usuario("Jose", "jose@email", "rua da paz", "12345", "Jose");
		Usuario mario = new Usuario("Mario", "mario@email", "rua da paz", "12345", "Mario");
		Usuario fernanda = new Usuario("Fernanda", "fernanda@email", "rua da paz", "12345", "Fernanda");
		Usuario isabela = new Usuario("Isabela", "isabela@email", "rua da paz", "12345", "Isabela");
		
		calendar = UtilInfo.converteStringEmCalendar("22/10/2013", "10:00");
		carona1 = new CaronaComum("Campina Grande", "João Pessoa", calendar, 1, id.gerarId(), jose);
		
		calendar = UtilInfo.converteStringEmCalendar("06/06/2013", "09:00");
		carona2 = new CaronaComum("João Pessoa", "Natal", calendar, 2, id.gerarId(), mario);
		
		calendar = UtilInfo.converteStringEmCalendar("10/12/2013", "08:00");
		carona3 = new CaronaComum("Natal", "Recife", calendar, 3, id.gerarId(), fernanda);
		
		calendar = UtilInfo.converteStringEmCalendar("17/07/2013", "07:00");
		carona4 = new CaronaComum("Recife", "Campina Grande", calendar, 4, id.gerarId(), isabela);
		
		calendar = UtilInfo.converteStringEmCalendar("04/06/2013", "20:00");
		caronaMunicipal = new CaronaMunicipal("Açude Velho","Shopping Boulevard","Campina Grande", calendar, 2, id.gerarId(), jose);
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
		} catch (CaronaException e) {
			Assert.assertEquals("Origem inválida", e.getMessage());
		}
		
		try {
			criaCarona3();
			Assert.fail("Nao pode existir carona com origem null.");
		} catch (CaronaException e) {
			Assert.assertEquals("Origem inválida", e.getMessage());
		}
		
		try {
			criaCarona4();
			Assert.fail("Nao pode existir carona com destino vazio.");
		} catch (CaronaException e) {
			Assert.assertEquals("Destino inválido", e.getMessage());
		}
		
		try {
			criaCarona5();
			Assert.fail("Nao pode existir carona com destino null.");
		} catch (CaronaException e) {
			Assert.assertEquals("Destino inválido", e.getMessage());
		}
		
		try {
			criaCarona6();
			Assert.fail("Nao pode existir carona com data passada.");
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("Data inválida", e.getMessage());
		}
		
		try {
			criaCarona7();
			Assert.fail("Nao pode existir carona com hora invalida.");
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("Hora inválida", e.getMessage());
		}
		
		try {
			criaCarona8();
			Assert.fail("Nao pode existir carona com o numero de vagas menor que 0.");
		} catch (IllegalArgumentException e) {
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
		Assert.assertEquals("Açude Velho", caronaMunicipal.getOrigem());
	}

	@Test
	public void testaGetDonoDaCarona() {
		Assert.assertEquals("Jose", carona1.getDonoDaCarona().getNome());
		Assert.assertEquals("Mario", carona2.getDonoDaCarona().getNome());
		Assert.assertEquals("Fernanda", carona3.getDonoDaCarona().getNome());
		Assert.assertEquals("Isabela", carona4.getDonoDaCarona().getNome());
		Assert.assertEquals("Jose", caronaMunicipal.getDonoDaCarona().getNome());
	}
	
	@Test
	public void testaGetDestino() {
		Assert.assertEquals("João Pessoa", carona1.getDestino());
		Assert.assertEquals("Natal", carona2.getDestino());
		Assert.assertEquals("Recife", carona3.getDestino());
		Assert.assertEquals("Campina Grande", carona4.getDestino());
		Assert.assertEquals("Shopping Boulevard", caronaMunicipal.getDestino());
	}
	
	@Test
	public void testaGetData() {
		Assert.assertEquals("22/10/2013", UtilInfo.converteCalendarEmStringData(carona1.getCalendario()));
		Assert.assertEquals("06/06/2013", UtilInfo.converteCalendarEmStringData(carona2.getCalendario()));
		Assert.assertEquals("10/12/2013", UtilInfo.converteCalendarEmStringData(carona3.getCalendario()));
		Assert.assertEquals("17/07/2013", UtilInfo.converteCalendarEmStringData(carona4.getCalendario()));
		Assert.assertEquals("04/06/2013", UtilInfo.converteCalendarEmStringData(caronaMunicipal.getCalendario()));
	}
	
	@Test
	public void testaGetHora() {
		Assert.assertEquals("10:00", UtilInfo.converteCalendarEmStringHora(carona1.getCalendario()));
		Assert.assertEquals("09:00", UtilInfo.converteCalendarEmStringHora(carona2.getCalendario()));
		Assert.assertEquals("08:00", UtilInfo.converteCalendarEmStringHora(carona3.getCalendario()));
		Assert.assertEquals("07:00", UtilInfo.converteCalendarEmStringHora(carona4.getCalendario()));
		Assert.assertEquals("20:00", UtilInfo.converteCalendarEmStringHora(caronaMunicipal.getCalendario()));
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
	public void testaGetDadosCarona(){
		Assert.assertEquals("As informacoes da carona nao batem.","origem=Campina Grande destino=João Pessoa data=22/10/2013 hora=10:00 vagas=1", carona1.getDadosCarona());
		Assert.assertEquals("As informacoes da carona nao batem.","origem=João Pessoa destino=Natal data=06/06/2013 hora=09:00 vagas=2", carona2.getDadosCarona());
		Assert.assertEquals("As informacoes da carona nao batem.","origem=Natal destino=Recife data=10/12/2013 hora=08:00 vagas=3", carona3.getDadosCarona());
		Assert.assertEquals("As informacoes da carona nao batem.","origem=Recife destino=Campina Grande data=17/07/2013 hora=07:00 vagas=4", carona4.getDadosCarona());
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
		} catch (CaronaException e) {
			Assert.assertEquals("Origem inválida", e.getMessage());
		}
		
		try {
			carona2.setOrigem(null);
			Assert.fail("A carona nao pode ter origem null.");
		} catch (CaronaException e) {
			Assert.assertEquals("Origem inválida", e.getMessage());
		}
		
		try {
			carona3.setOrigem("- 09 $ %");
			Assert.fail("A origem da carona nao pode ter apenas caracteres especiais ou numeros.");
		} catch (CaronaException e) {
			Assert.assertEquals("Origem inválida", e.getMessage());
		}
		
		Assert.assertEquals("Recife",carona4.getOrigem());
		try {
			carona4.setOrigem("Maceio");
		} catch (CaronaException e) {
			Assert.assertEquals("Origem inválida", e.getMessage());
		}
		Assert.assertEquals("Maceio", carona4.getOrigem());
	}
	
	@Test
	public void testaSetDestino() {
		try {
			carona1.setDestino("");
			Assert.fail("A carona nao pode ter destino vazio.");
		} catch (CaronaException e) {
			Assert.assertEquals("Destino inválido", e.getMessage());
		}
		
		try {
			carona2.setDestino(null);
			Assert.fail("A carona nao pode ter destino null.");
		} catch (CaronaException e) {
			Assert.assertEquals("Destino inválido", e.getMessage());
		}
		
		try {
			carona3.setDestino("- 09 $ %");
			Assert.fail("O destino da carona nao pode ter apenas caracteres especiais ou numeros.");
		} catch (CaronaException e) {
			Assert.assertEquals("Destino inválido", e.getMessage());
		}
		
		Assert.assertEquals("Campina Grande",carona4.getDestino());
		try {
			carona4.setDestino("Bahia");
		} catch (CaronaException e) {
			Assert.assertEquals("Destino inválido", e.getMessage());
		}
		Assert.assertEquals("Bahia", carona4.getDestino());
	}
	
	//Testando Metodos Especificos
	@Test
	public void testaToString(){
		Assert.assertEquals("Campina Grande para João Pessoa, no dia 22/10/2013, as 10:00", carona1.toString());
		Assert.assertEquals("João Pessoa para Natal, no dia 06/06/2013, as 09:00", carona2.toString());
		Assert.assertEquals("Natal para Recife, no dia 10/12/2013, as 08:00", carona3.toString());
		Assert.assertEquals("Recife para Campina Grande, no dia 17/07/2013, as 07:00", carona4.toString());
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
