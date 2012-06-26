package ufcg.si1.infoCarona.view;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface InfoCaronaServerAsync {
	void abrirSessao(String login, String senha, AsyncCallback<String> asyncCallback);
	void criarUsuario(String login, String senha, String nome, String endereco, String email, AsyncCallback<Void> retorno);
}
