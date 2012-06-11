package ufcg.si1.infoCarona.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import ufcg.si1.infoCarona.model.carona.CaronaException;
import ufcg.si1.infoCarona.model.usuario.Usuario;

public class Interesse {

	private final Usuario interessado;
	private String origem;
	private Calendar calendarioFinal;
	private Calendar calendarioInicial;
	private final String id;
	private String destino;
	private boolean caronaEhNoDia;

	/**
	 * Construtor da classe interesse
	 * 
	 * @param interessado
	 *            - o dono do objeto interesse
	 * @param origem
	 *            - a origem da carona que o usuario esta interessado
	 * @param destino
	 *            - o destino da carona que o usuario esta interessado
	 * @param calendarioInicial
	 *            - hora e data inicial que o usuario esta interessado em pegar
	 *            carona
	 * @param calendarioFinal
	 *            - hora e data final que o usuario esta interessado em pegar
	 *            carona
	 * @param id
	 *            - id do objeto interesse a ser criado
	 * @param caronaEhNoDia
	 *            - se a carona que o usuario busca eh no dia que ele cadastra o
	 *            interesse
	 * @throws CaronaException
	 */
	public Interesse(Usuario interessado, String origem, String destino,
			Calendar calendarioInicial, Calendar calendarioFinal, String id,
			boolean caronaEhNoDia) throws CaronaException {
		this.interessado = interessado;
		setOrigem(origem);
		setDestino(destino);
		setCalendarioInicial(calendarioInicial);
		setCalendarioFinal(calendarioFinal);
		this.id = id;
		this.caronaEhNoDia = caronaEhNoDia;

	}

	/**
	 * @return retorna a id do objeto interesse
	 */
	public String getId() {
		return id;
	}

	/**
	 * 
	 * @return retorna o dono do objeto interesse
	 */
	public Usuario getInteressado() {
		return interessado;
	}

	/**
	 * 
	 * @return retorna a origem da carona que o interessado busca
	 */
	public String getOrigem() {
		return origem;
	}

	/**
	 * 
	 * @return retorna o destino da carona que o interessado busca
	 */
	public String getDestino() {
		return destino;
	}

	/**
	 * 
	 * @return hora e data inicial que o usuario esta interessado em pegar
	 *         carona
	 */
	public Calendar getCalendarioInicial() {
		return this.calendarioInicial;
	}

	/**
	 * 
	 * @return hora e data final que o usuario esta interessado em pegar carona
	 */
	public Calendar getCalendarioFinal() {
		return this.calendarioFinal;
	}

	/**
	 * Define a hora e data inicial que o usuario esta interessado em pegar
	 * carona
	 * 
	 * @param calendario
	 *            - data e hora
	 */
	public void setCalendarioInicial(Calendar calendario) {
		this.calendarioInicial = calendario;
	}

	/**
	 * Define a hora e data final que o usuario esta interessado em pegar carona
	 * 
	 * @param calendario
	 *            - data e hora
	 */
	public void setCalendarioFinal(Calendar calendario) {
		this.calendarioFinal = calendario;
	}

	/**
	 * Define a origem da carona que o usuario esta interessado
	 * 
	 * @param origem
	 * @throws CaronaException
	 */
	private void setOrigem(String origem) throws CaronaException {
		if ((origem == null)
				|| (origem.matches("[\\-/.\\[_\\]()!\"+,:;<=>{|}#@$%¨&*0-9].*"))
				|| (origem.trim().equals(""))) {
			throw new IllegalArgumentException("Origem inválida");
		}
		this.origem = origem.trim();
	}

	/**
	 * Define o destino da carona que o usuario esta interessado
	 * 
	 * @param destino
	 * @throws CaronaException
	 */
	private void setDestino(String destino) throws CaronaException {
		if ((destino == null)
				|| (destino
						.matches("[\\-/.\\[_\\]()!\"+,:;<=>{|}#@$%¨&*0-9].*"))
				|| (destino.trim().equals(""))) {
			throw new IllegalArgumentException("Destino inválido");
		}
		this.destino = destino.trim();
	}

	private boolean checaData(String stringData) {
		boolean retorno = true;
		if (!stringData.equals("")) {
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

	/**
	 * metodo para checar se o interesse é no dia ou não
	 * 
	 * @return true ou false
	 */
	public boolean caronaEhNoDiaMarcado() {
		return caronaEhNoDia;
	}

}
