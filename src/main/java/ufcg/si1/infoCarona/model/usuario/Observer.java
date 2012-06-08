package ufcg.si1.infoCarona.model.usuario;

import java.util.LinkedList;
import java.util.List;

import ufcg.si1.infoCarona.model.Interesse;
import ufcg.si1.infoCarona.model.carona.Carona;
import util.UtilInfo;

public class Observer {
	
	private List<Interessado> interessados;
	
	public Observer(){
		interessados = new LinkedList<Interessado>();
	}
	
	public void addInteressado(Interessado interessado){
		interessados.add(interessado);
	}
	
	public void removeInteressado(Interessado interessado){
		interessados.remove(interessado);
	}
	
	public void cadastrouCarona(Carona carona){
		enviaMsgAInteressadosEmCarona(carona);
	}
	
	private void enviaMsgAInteressadosEmCarona(Carona carona){
		List<Interessado> listaDeInteressados = localizaInteressados(carona);
		for (Interessado interessado : listaDeInteressados) {
			String novaMensagem = "Carona cadastrada no dia " + UtilInfo.converteCalendarEmStringData(carona.getCalendario()) + ", às " + UtilInfo.converteCalendarEmStringHora(carona.getCalendario()) + " de acordo com os seus interesses registrados. Entrar em contato com " + carona.getDonoDaCarona().getEmail();
			interessado.addMensagen(novaMensagem);
		}
	}
	
	private List<Interessado> localizaInteressados(Carona carona) {
		List<Interessado> retorno = new LinkedList<Interessado>();
		
		for (Interessado interessado : interessados) {
			for (Interesse interesseTemp : interessado.getListaDeInteresses()) {
				if (interesseTemp.getOrigem().equals(carona.getOrigem())) {
					if (interesseTemp.getDestino().equals(carona.getDestino())) {
						if (!interesseTemp.caronaEhNoDiaMarcado()) {
							if ((UtilInfo.getHora(interesseTemp.getCalendarioInicial()) <= UtilInfo.getHora(carona.getCalendario())) && (UtilInfo.getHora(carona.getCalendario()) <= UtilInfo.getHora(interesseTemp.getCalendarioFinal()))) {
								if ((UtilInfo.getMinutos(interesseTemp.getCalendarioInicial()) <= UtilInfo.getMinutos(carona.getCalendario())) && (UtilInfo.getMinutos(carona.getCalendario()) <= UtilInfo.getMinutos(interesseTemp.getCalendarioFinal()))){
									retorno.add(interessado);
								}
							}
						}else{
							if (UtilInfo.converteCalendarEmStringData(interesseTemp.getCalendarioInicial()).equals(UtilInfo.converteCalendarEmStringData(carona.getCalendario()))) {
								if ((UtilInfo.getHora(interesseTemp.getCalendarioInicial()) <= UtilInfo.getHora(carona.getCalendario())) && (UtilInfo.getHora(carona.getCalendario()) <= UtilInfo.getHora(interesseTemp.getCalendarioFinal()))) {
									if ((UtilInfo.getMinutos(interesseTemp.getCalendarioInicial()) <= UtilInfo.getMinutos(carona.getCalendario())) && (UtilInfo.getMinutos(carona.getCalendario()) <= UtilInfo.getMinutos(interesseTemp.getCalendarioFinal()))){
										retorno.add(interessado);
									}
								}
							}
						}
					}
				}
			}
		}
		
		return retorno;
	}
}
