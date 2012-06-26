package ufcg.si1.infoCarona.server;

import ufcg.si1.infoCarona.view.ExceptionSerialized;
import ufcg.si1.infoCarona.view.InfoCaronaServer;
import ufcg.si1.infoCarona.controller.ControllerView;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial") 
public class InfoCaronaServerImpl extends RemoteServiceServlet implements InfoCaronaServer { 
	
	private ControllerView  controller;
	
	public InfoCaronaServerImpl() {
		controller = new ControllerView();
	}
	
	public String abrirSessao(String login, String senha) throws ExceptionSerialized {
			try {
				return controller.abrirSessao(login, senha);
			} catch (Exception e) {
				throw new ExceptionSerialized(e.getMessage());
			}
	}
	
	public void criarUsuario(String login, String senha, String nome, String endereco, String email) throws ExceptionSerialized {
		try {
			controller.criarUsuario(login, senha, nome, endereco, email);
		} catch (Exception e) {
			throw new ExceptionSerialized(e.getMessage()); 
		}
	}
	
}
