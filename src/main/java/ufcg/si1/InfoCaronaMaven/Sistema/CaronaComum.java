package ufcg.si1.InfoCaronaMaven.Sistema;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.DataInvalidaException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.DestinoInvalidoException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.HoraInvalidaException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.OrigemInvalidaException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.SessaoInvalidaException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.VagaInvalidaException;
/**
 * Classe Carona, que gerencia os dados da carona e possui as listas de solicitações, sugestões e pontos de encontro.
 * @author Felipe Lindemberg
 *
 */
public class CaronaComum extends CaronaAbstract{

	/**
	 * Construtor da classe Carona
	 * @param origem - recebe uma String informando a origem da corana
	 * @param destino - recebe uma String informando o destino da carona
	 * @param data - recebe uma String no mormato xx/xx/xxxx e verifica se essa data é válida e nao aconteceu ainda.
	 * @param hora - recebe uma String no formato xx:xx informando a hora que a carona vai acontecer, e verifica se é um hora válida
	 * @param vagas - recebe um int informando o numero de vagas disponivel na carona
	 * @param idCarona - recebe uma String identifcador da carona
	 * @param donoDaCarona - recebe uma String com o login do dono da carona.
	 * @throws SessaoInvalidaException
	 * @throws OrigemInvalidaException - retorna uma exceção caso a origem passada seja null ou vazia
	 * @throws DestinoInvalidoException - retorna uma exceção caso o destino passado seja null ou vazio
	 * @throws DataInvalidaException - retorna uma exceção caso a data passada seja null, vazia ou data "passada"
	 * @throws HoraInvalidaException- retorna uma exceção caso a hora passada seja null, vazia ou data em formato inválida
	 * @throws VagaInvalidaException - retorna uma exceção caso o numero de vagsa seja negativo ou nao seja um numéro válido.
	 */
	public CaronaComum(String origem, String destino, String data, String hora, int vagas, String idCarona, Usuario donoDaCarona) throws SessaoInvalidaException, OrigemInvalidaException, DestinoInvalidoException, DataInvalidaException, HoraInvalidaException, VagaInvalidaException {
        super(origem, destino, data, hora, vagas, idCarona, donoDaCarona);
        super.tipoCarona = TiposCarona.COMUM;
	}

}
