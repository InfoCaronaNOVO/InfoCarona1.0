package ufcg.si1.InfoCaronaMaven.Testes;


import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import ufcg.si1.InfoCaronaMaven.Sistema.Fachada;
import easyaccept.EasyAcceptFacade;

public class TesteMaven extends TestCase {

	public TesteMaven(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(TesteMaven.class);
	}

	public void testarEasyAcceptScript() {

		List<String> files = new ArrayList<String>();
		// Put the us1.txt file into the "test scripts" list
		
		files.add("scripts/US01.txt");
		files.add("scripts/US02.txt");
		files.add("scripts/US03.txt");
		files.add("scripts/US04.txt");
		files.add("scripts/US05.txt");
		files.add("scripts/US06.txt");
		files.add("scripts/US07.txt");
		files.add("scripts/US08.txt");
		//Falta implementar os metodos dos user abaixo e ajeitar os scripts do monitor que veio errado
		files.add("scripts/US09.txt");
		files.add("scripts/US10.txt");
		//files.add("scripts/US11.txt");
		//files.add("scripts/US12.txt");
		// Instantiate the Monopoly Game fa�ade
		
		Fachada fachadaInfoCarona = new Fachada();

		// Instantiate EasyAccept fa�ade
		EasyAcceptFacade eaFacade = new EasyAcceptFacade(fachadaInfoCarona, files);

		// Execute the tests
		eaFacade.executeTests();

		// Print the tests execution results
		System.out.println(eaFacade.getCompleteResults());


		assertTrue(eaFacade.getTotalNumberOfNotPassedTests() == 0);
	}


}
