package ufcg.si1.InfoCaronaMaven.Sistema;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class ManipulaArquivoXML {

	private List lista = new LinkedList();
	private String nomeDoArquivo;

	/**
	 * 
	 * Construtor de Grava, Grava eh uma classe responsalve por ler, gravar,
	 * remover e atualizar arquivos XML
	 * 
	 * @param nomeArquivo
	 *            Eh um String com o nome que vc deseja salvar o seu arquivo
	 *            xml.
	 * 
	 * */
	public ManipulaArquivoXML(String nomeArquivo) {
		this.setNomeArquivo(nomeArquivo);
		this.setLista();
	}

	private void setLista() {
		lista = this.ler();
	}
	
	public void setLista(List list){
		lista = new LinkedList();
		for (Object object : list) {
			lista.add(object);
		}
	}

	private void setNomeArquivo(String nome) {
		this.nomeDoArquivo = nome + ".xml";
	}
	
	public void addObject(Object obj){
		lista.add(obj);
	}
	
	public void finalizarXML(){
		XStream xstream = new XStream(new DomDriver());
		String myXML = xstream.toXML(lista);
		
		try {
			
			BufferedWriter writer = null;

			writer = new BufferedWriter(new FileWriter(this.nomeDoArquivo));
			writer.write(myXML);
			writer.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Retorna uma List com o conteudo do XML, se o xml nao existe ele sera
	 * criado em branco
	 * 
	 * */
	public List ler() {
		FileReader reader = null;
		try {
			reader = new FileReader(this.nomeDoArquivo);
		} catch (FileNotFoundException e) {
			System.out.println("Nao existia dados no arquivo.");
			return (new LinkedList());
		}
		
		XStream xstream = new XStream(new DomDriver());
		lista = ((List) xstream.fromXML(reader));
		try{
			reader.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return lista;
	}

	/**
	 * 
	 * 
	 * Adiciona Objetos no arquivo, se nao existir o arquivo, ele sera criado
	 * para que o objeto seja adicionado
	 * 
	 * @param obj
	 *            Eh um Object para ser adicionado ao arquivo xml
	 * 
	 * */
	private void addNoXML(Object obj) {

		// verifica se o arquivo existe.
		FileReader reader = null;
		XStream xstream = new XStream(new DomDriver());
		lista = this.ler();
		lista.add(obj);
		
		String myXML = xstream.toXML(lista);
		try {
			reader = new FileReader(this.nomeDoArquivo);
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo não existe.");
			
		}

		try {
			
			BufferedWriter writer = null;

			writer = new BufferedWriter(new FileWriter(this.nomeDoArquivo));
			System.out.println("Arquivo foi criado.");
			writer.write(myXML);
			writer.close();
			
			System.out.println("Aquivo escrito.");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 
	 * Remove o Objeto na posicao escolhida, se o arquivo nao existe ele sera
	 * criado em branco
	 * 
	 * @param posicao
	 *            Eh a posicao do objeto a ser removido
	 * 
	 * */
	public void remover(int posicao) {
		FileReader reader = null;
		List pw = null;
		try {
			reader = new FileReader(this.nomeDoArquivo);

		} catch (FileNotFoundException e) {

			XStream xstream = new XStream(new DomDriver());
			String myXML = xstream.toXML(lista);

			BufferedWriter writer = null;

			try {
				writer = new BufferedWriter(new FileWriter(this.nomeDoArquivo));
			} catch (IOException e1) {
				System.out.println(e1.getMessage());
			}

			try {
				writer.write(myXML);
				writer.close();
				System.out
						.println("Arquivo nao existia.\n...\nArquivo foi criado.");

			} catch (IOException f) {
				f.printStackTrace();
			}
		}

		XStream xstream = new XStream(new DomDriver());
		try {

			pw = ((List) xstream.fromXML(reader));
			pw.remove(posicao);

			String myXML = xstream.toXML(pw);

			BufferedWriter writer = null;
			try {
				writer = new BufferedWriter(new FileWriter(this.nomeDoArquivo));
			} catch (IOException e1) {

			}

			try {
				writer.write(myXML);
				writer.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println("Nao existe arquivo");
		}

	}

	/**
	 * Atualiza grava um objeto por cima do arquivo existente
	 * 
	 * @param obj
	 *            Eh um Object que sera gravado no arquivo
	 * */
	public void atualiza(Object obj) {
		XStream xstream = new XStream(new DomDriver());

		String myXML = xstream.toXML(obj);
		BufferedWriter writer = null;

		try {
			writer = new BufferedWriter(new FileWriter(this.nomeDoArquivo));
		} catch (IOException e1) {

		}

		try {
			writer.write(myXML);
			writer.close();

		} catch (IOException e) {

			e.printStackTrace();

		} catch (Exception e) {
			System.out.println("Nao existe arquivo");
		}
	}

	public void clear() {
		LinkedList lista = new LinkedList();
		FileWriter file = null;
		try {
			file = new FileWriter(this.nomeDoArquivo);

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		XStream XS = new XStream(new DomDriver());

		String string = XS.toXML(lista);
		try {
			file.write(string);
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
