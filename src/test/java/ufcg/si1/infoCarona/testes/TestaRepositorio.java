package ufcg.si1.infoCarona.testes;

import java.awt.List;
import java.util.Calendar;
import java.util.LinkedList;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ufcg.si1.infoCarona.model.Id;
import ufcg.si1.infoCarona.model.LoggerException;
import ufcg.si1.infoCarona.model.NumeroMaximoException;
import ufcg.si1.infoCarona.model.Repositorio;
import ufcg.si1.infoCarona.model.carona.Carona;
import ufcg.si1.infoCarona.model.carona.CaronaComum;
import ufcg.si1.infoCarona.model.carona.CaronaException;
import ufcg.si1.infoCarona.model.usuario.Usuario;
import util.UtilInfo;

public class TestaRepositorio {
	private Repositorio repositorio;
	private Calendar calendar;
	private Id id;
	private Usuario usuario1, usuario2;
	private String id0,id1,id2;
	private Carona carona0;
	private LinkedList lista;
	
	@Before
	public void instacia() throws CaronaException, NumeroMaximoException, LoggerException{
		repositorio = Repositorio.getInstance();
		repositorio.zerarSistema();
		
		calendar = UtilInfo.converteStringEmCalendar("12/12/2013", "10:00");
		id = Id.getInstance(5);
		
		usuario1 = new Usuario("Marck", "marck@gmail.com", "bela vista", "1212", "Marck");
		id0 = id.gerarId();
		usuario1.cadastrarCarona("campina grande", "joão pessoa", calendar, 3, id0);
		
		usuario2 = new Usuario("Juan", "juan@gmail.com", "bela vista", "1234", "Juan");
	}
	
	@After
	public void finalizar(){
		repositorio.zerarSistema();
	}
	
	@Test
	public void testaAddUsuario() throws CaronaException, NumeroMaximoException, LoggerException{
		
		repositorio.addUsuario(usuario1);
		Usuario user = repositorio.buscaUsuarioLogin("Marck");
		
		Assert.assertFalse(user.equals(usuario2));
		Assert.assertEquals(usuario1, user);
		Assert.assertEquals(1, repositorio.getTodosOsUsuarios().size());
		Assert.assertEquals(1, repositorio.getTodasAsCaronas().size());
		
		repositorio.addUsuario(usuario2);
		user = repositorio.buscaUsuarioEmail("juan@gmail.com");
		
		Assert.assertFalse(user.equals(usuario1));
		Assert.assertEquals(usuario2, user);
		Assert.assertEquals(2, repositorio.getTodosOsUsuarios().size());
		Assert.assertEquals(1, repositorio.getTodasAsCaronas().size());
	}
	
	@Test
	public void testaLocalizaCarona() throws CaronaException, NumeroMaximoException{
		repositorio.addUsuario(usuario1);
		
		calendar = UtilInfo.converteStringEmCalendar("12/10/2013", "12:00");
		id1 = id.gerarId();
		carona0 = new CaronaComum("aculá", "alí", calendar, 2, id1, usuario2);
		usuario2.cadastrarCarona("aculá", "alí", calendar, 2, id1);
		calendar = UtilInfo.converteStringEmCalendar("11/10/2013", "12:00");
		id2 = id.gerarId();
		usuario2.cadastrarCarona("origem", "destino", calendar, 1, id2);
		
		repositorio.addUsuario(usuario2);
		
		Assert.assertEquals(usuario1, repositorio.getCaronaId(id0).getDonoDaCarona());
		Assert.assertEquals(usuario2, repositorio.getCaronaId(id1).getDonoDaCarona());
		Assert.assertEquals(usuario2, repositorio.getCaronaId(id2).getDonoDaCarona());
		Assert.assertEquals(carona0, repositorio.getCaronaId(id1));
		Assert.assertEquals(3, repositorio.getTodasAsCaronas().size());
		Assert.assertEquals("campina grande", repositorio.getCaronaId(id0).getOrigem());
		Assert.assertEquals("joão pessoa", repositorio.getCaronaId(id0).getDestino());
		
		lista = new LinkedList<Carona>();
		lista.add(carona0);
		Assert.assertEquals(lista, repositorio.localizaCaronaPorDestino("alí"));
		Assert.assertEquals(lista, repositorio.localizaCaronaPorOrigem("aculá"));
	}
}
