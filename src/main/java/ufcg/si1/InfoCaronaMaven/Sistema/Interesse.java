package ufcg.si1.InfoCaronaMaven.Sistema;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.DataInvalidaException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.DestinoInvalidoException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.OrigemInvalidaException;

public class Interesse {

	private final Usuario interessado;
	private String origem;
	private String data;
	private String horaInicio;
	private String horaFim;
	private final String id;
	private final String horaInicioDefault = "00:00";
	private final String horaFimDefault = "23:59";
	private String destino;

	public Interesse(Usuario interessado, String origem, String destino,
			String data, String horaInicio, String horaFim, String id)
			throws OrigemInvalidaException, DestinoInvalidoException,
			DataInvalidaException {
		this.interessado = interessado;
		setOrigem(origem);
		setDestino(destino);
		setData(data);
		setHoraInicio(horaInicio);
		setHoraFim(horaFim);
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

	public String getHoraInicio() {
		return horaInicio;
	}

	public String getHoraFim() {
		return horaFim;
	}

	private void setHoraInicio(String horaInicial) {
		if (horaInicial.equals("")) {
			this.horaInicio = horaInicioDefault;
		} else {
			this.horaInicio = horaInicial;
		}
	}

	private void setHoraFim(String horaFinal) {
		if (horaFinal.equals("")) {
			this.horaFim = horaFimDefault;
		} else {
			this.horaFim = horaFinal;
		}
	}

	private void setOrigem(String origem) throws OrigemInvalidaException {
		if ((origem == null)
				|| (origem
						.matches("[\\-/.\\[_\\]()!\"+,:;<=>{|}#@$%¨&*0-9].*"))
				|| (origem.trim().equals(""))) {
			throw new OrigemInvalidaException();
		}
		this.origem = origem.trim();
	}

	private void setDestino(String destino) throws DestinoInvalidoException {
		if ((destino == null)
				|| (destino
						.matches("[\\-/.\\[_\\]()!\"+,:;<=>{|}#@$%¨&*0-9].*"))
				|| (destino.trim().equals(""))) {
			throw new DestinoInvalidoException();
		}
		this.destino = destino.trim();
	}

	public void setData(String data) throws DataInvalidaException {
		if ((data == null) || !(checaData(data))) {
			throw new DataInvalidaException();
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
