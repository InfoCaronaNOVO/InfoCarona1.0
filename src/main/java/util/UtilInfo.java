package util;
import java.awt.image.ConvolveOp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.sound.midi.SysexMessage;
import javax.xml.crypto.Data;

import ufcg.si1.InfoCaronaMaven.Sistema.ArgumentoInexistenteException;

public class UtilInfo {
	SimpleDateFormat formatoData;
	SimpleDateFormat formatoHora;
	public UtilInfo(){
		formatoData = new SimpleDateFormat("dd/MM/yyyy");
		formatoHora = new SimpleDateFormat("HH:mm");
	}
	
	public static boolean jahPassou(Calendar calendario){
		GregorianCalendar calendarioAtual=new GregorianCalendar();
		//calendarioAtual.add(calendarioAtual.HOUR, 48);  como add mais dias na data
        return calendarioAtual.getTime().after(calendario.getTime());
	}

	public static Calendar converteStringEmCalendar(String data, String hora){
		if (data == null || data.equals("")) throw new IllegalArgumentException("Data inválida");
		if (hora == null || hora.equals("")) throw new IllegalArgumentException("Hora inválida");
		
		Calendar calendar = new GregorianCalendar();
		int dia=0, mes=0, ano=0, hr=0, minuto=0;
		
		String[] listaData = data.split("/");
		String[] listaHora = hora.split(":");
		
		try{
			dia = Integer.parseInt(listaData[0]);
			mes = Integer.parseInt(listaData[1]);
			ano = Integer.parseInt(listaData[2]);
		}catch(Exception e){
			throw new IllegalArgumentException("Data inválida");
		}
		
		try{
			hr = Integer.parseInt(listaHora[0]);
			minuto = Integer.parseInt(listaHora[1]);
		}catch(Exception e){
			throw new IllegalArgumentException("Hora inválida");
		}
		
		calendar.set(ano, (mes-1), dia, hr, minuto);
		
		if(!UtilInfo.converteCalendarEmStringData(calendar).equals(data)){
			throw new IllegalArgumentException("Data inválida");
		}
		if(jahPassou(calendar)){
			throw new IllegalArgumentException("Data inválida");
		}
		
		return calendar;
	}
	
	public static String converteCalendarEmStringData(Calendar calendar){
		String retorno = "";
		
		retorno = String.format("%02d", calendar.get(calendar.DATE)) + "/" + String.format("%02d", (calendar.get(calendar.MONTH)+1)) + "/" + String.format("%02d", calendar.get(calendar.YEAR));
		
		return retorno;
	}
	
	public static String converteCalendarEmStringHora(Calendar calendar){
		String retorno = "";
		
		retorno = String.format("%02d", calendar.get(calendar.HOUR_OF_DAY)) + ":" + String.format("%02d", calendar.get(calendar.MINUTE));
		
		return retorno;
	}
	
	public static int getHora(Calendar calendario){
		return calendario.get(calendario.HOUR_OF_DAY);
	}
	
	public static int getMinutos(Calendar calendario){
		return calendario.get(calendario.MINUTE);
	}
	
	public static void main(String[] args) {
		EnviarEmail.enviarEmail("Info Carona", "felipelindemberg.cc.ufcg@gmail.com", "testando", "mensagem testando");
	}
}
