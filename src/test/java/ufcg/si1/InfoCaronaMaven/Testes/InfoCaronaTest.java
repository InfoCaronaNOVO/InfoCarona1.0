package ufcg.si1.InfoCaronaMaven.Testes;

import java.util.ArrayList;
import java.util.List;

import ufcg.si1.InfoCaronaMaven.Sistema.Fachada;
import easyaccept.EasyAcceptFacade;

public class InfoCaronaTest {

	public static void main(String[] args) {
		Fachada fachada = new Fachada();
		List<String> files = new ArrayList<String>();
		// Put the us1.txt file into the "test scripts" list
		 files.add("US01.txt");
		files.add("US02.txt");
		files.add("US03.txt");
		files.add("US04.txt");
		files.add("US05.txt");	
		 files.add("US06.txt");
		files.add("US07.txt");
		 files.add("US08.txt");
		// files.add("US09.txt");
		// files.add("US11.txt");
		// files.add("US12.txt");
		// Instantiate the sistena

		// Instantiate EasyAccept fa�ade
		EasyAcceptFacade eaFacade = new EasyAcceptFacade(fachada, files);
		// Execute the tests
		eaFacade.executeTests();
		// Print the tests execution results
		System.out.println(eaFacade.getCompleteResults());

	}

}
