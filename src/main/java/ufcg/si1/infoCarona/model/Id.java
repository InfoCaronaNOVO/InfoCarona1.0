package ufcg.si1.infoCarona.model;

public class Id {

	private int numeroDeDigitos;
	private int cont = 0;
	private String ultimoId;
	public static Id instance;

	/**
	 * construtor da classe id
	 * 
	 * @param numeroDeDigitos
	 *            - o numero de digitos que cada id terá
	 */
	protected Id(int numeroDeDigitos) {
		this.numeroDeDigitos = numeroDeDigitos;
	}

	/**
	 * Singleton metodo paracriar um objeto da classe id, caso ainda não tenha
	 * criado.
	 * 
	 * @param numeroDeDigitos
	 * @return
	 */
	public static Id getInstance(int numeroDeDigitos) {
		if (instance == null) {
			instance = new Id(numeroDeDigitos);
		}
		return instance;
	}

	/**
	 * Metodo para gerar uma id para algum tipo de objeto do sistema
	 * 
	 * @return id
	 * @throws NumeroMaximoException
	 *             caso o numero maximo de ids permitidos seja alcançado
	 */
	public String gerarId() throws NumeroMaximoException {
		String retorno = "";
		String stringCont = cont + "";
		cont++;

		if (stringCont.length() > numeroDeDigitos) {
			throw new NumeroMaximoException();
		}

		for (int i = stringCont.length(); i < numeroDeDigitos; i++) {
			retorno += 0;
		}

		ultimoId = retorno + stringCont;

		return (ultimoId);
	}

	/**
	 * @return retorna o numeor de digitos que cada id possui
	 */
	public int getNumeroDeDigitos() {
		return numeroDeDigitos;
	}

	/**
	 * Metodo que retorna o ultimo id gerado
	 * 
	 * @return id
	 */
	public String getUltimoId() {
		return ultimoId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cont;
		return result;
	}

	/**
	 * Equals da classe id Compara pelo ultimo id gerado
	 */
	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof Id)) {
			return false;
		}

		if (!(((Id) obj).getUltimoId().equals(this.ultimoId))) {
			return false;
		}

		return true;
	}

	/**
	 * tostring da classe id com o numero de digitos e o ultimo ID gerado
	 */
	@Override
	public String toString() {
		String retorno = "O n�mero de digitos � " + this.numeroDeDigitos
				+ " e o ultimo ID gerado foi " + this.ultimoId;
		return retorno;
	}
}
