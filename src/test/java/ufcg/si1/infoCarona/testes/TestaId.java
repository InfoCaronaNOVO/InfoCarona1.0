package ufcg.si1.infoCarona.testes;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ufcg.si1.infoCarona.model.Id;
import ufcg.si1.infoCarona.model.NumeroMaximoException;

public class TestaId {
	private Id id1, id2;
	
	@Before
	public void instancia(){
		id1 = Id.getInstance(5);
		id2 = Id.getInstance(4);
	}
	
	@Test
	public void testaGeraId() throws NumeroMaximoException{
		String idTest1 = id1.gerarId();
		String idTest2 = id1.gerarId();
		Assert.assertFalse(idTest1.equals(idTest2));
		Assert.assertEquals(5, id1.getNumeroDeDigitos());
		Assert.assertEquals(5, idTest1.length());
		Assert.assertEquals(5, idTest2.length());
		
		Assert.assertFalse( id1.gerarId().equals(id1.gerarId()) );
		
		idTest1 = id2.gerarId();
		idTest2 = id2.gerarId();
		Assert.assertFalse(idTest1.equals(idTest2));
		Assert.assertEquals("O Id é singleton, dever ser instanciado uma unica vez.",5, id2.getNumeroDeDigitos());
		Assert.assertEquals(5, idTest1.length());
		Assert.assertEquals(5, idTest2.length());
		
		Assert.assertFalse( id2.gerarId().equals(id2.gerarId()) );
	}
	
	
}
