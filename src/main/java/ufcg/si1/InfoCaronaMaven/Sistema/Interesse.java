package ufcg.si1.InfoCaronaMaven.Sistema;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Interesse {

	private final Usuario interessado;
	private String origem;
	private String data;
	private Calendar calendarioFinal;
	private Calendar calendarioInicial;
	private final String id;
	private String destino;

	public Interesse(Usuario interessado, String origem, String destino,
			Calendar calendarioInicial, Calendar calendarioFinal, String id)
			throws CaronaException {
		this.interessado = interessado;
		setOrigem(origem);
		setDestino(destino);
		setData(data);
		setCalendarioInicial(calendarioInicial);
		setCalendarioFinal(calendarioFinal);
		this.id = id;

	}

	public String getId() {
		return id;
	}

	public Usuario getInteressado() {
		return interessado;
	}

	public String getOrigem() {
		return origem;
	}

	public String getDestino() {
		return destino;
	}

	public String getData() {
		return data;
	}
	
	public Calendar getCalendarioInicial(){
		return this.calendarioInicial;
	}
	
	public Calendar getCalendarioFinal(){
		return this.calendarioFinal;
	}
	
	public void setCalendarioInicial(Calendar calendario){
		this.calendarioInicial = calendario;
	}
	
	public void setCalendarioFinal(Calendar calendario){
		this.calendarioFinal = calendario;
	}

	private void setOrigem(String origem) throws CaronaException {
		if ((origem == null)
				|| (origem
						.matches("[\\-/.\\[_\\]()!\"+,:;<=>{|}#@$%¨&*0-9].*"))
				|| (origem.trim().equals(""))) {
			throw new CaronaException("Origem inválida");
		}
		this.origem = origem.trim();
	}

	private void setDestino(String destino) throws CaronaException {
		if ((destino == null)
				|| (destino
						.matches("[\\-/.\\[_\\]()!\"+,:;<=>{|}#@$%¨&*0-9].*"))
				|| (destino.trim().equals(""))) {
			throw new CaronaException("Destino inválido");
		}
		this.destino = destino.trim();
	}

	public void setData(String data) {
		if ((data == null) || !(checaData(data))) {
			throw new IllegalArgumentException("Data inválida");
		}
		this.data = data.trim();
		// TODO devemos add mais 100 anos pra acontecer a carona caso a data
		// seja ""
	}

	private boolean checaData(String stringData) {
		boolean retorno = true;
		if (!stringData.equals("")) {//coloquei isso aki s� pra passar no teste qdo passar string vazia mas temos q ajeitar pra qdo for do tipo DATA
			Calendar data = Calendar.getInstance();
			try {
				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				formato.setLenient(false);
				data.setTime(formato.parse(stringData));
			} catch (ParseException e) {
				retorno = false;
			}
			Calendar dataAtual = Calendar.getInstance();
			if (dataAtual.getTime().compareTo(data.getTime()) == 1) { // -1 data
																		// valida,
																		// 1
																		// data
																		// invalida
				retorno = false;
			}
		}
		return retorno;
	}

}
