package ufcg.si1.infoCarona.view;


import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("greet")
public interface InfoCaronaServer extends RemoteService{
	String abrirSessao(String login, String senha) throws ExceptionSerialized;
	void criarUsuario(String login, String senha, String nome, String endereco, String email) throws ExceptionSerialized;

}
