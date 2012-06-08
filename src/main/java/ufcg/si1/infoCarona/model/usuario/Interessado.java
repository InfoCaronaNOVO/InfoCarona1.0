package ufcg.si1.infoCarona.model.usuario;

import java.util.List;

import ufcg.si1.infoCarona.model.Interesse;

public interface Interessado {
	
	public void addMensagen(String novaMensagem);
	public List<Interesse> getListaDeInteresses();
}
