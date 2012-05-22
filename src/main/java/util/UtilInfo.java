package util;
import java.awt.image.ConvolveOp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.crypto.Data;

public class UtilInfo {
	SimpleDateFormat formatoData;
	SimpleDateFormat formatoHora;
	public UtilInfo(){
		formatoData = new SimpleDateFormat("dd/MM/yyyy");
		formatoHora = new SimpleDateFormat("HH:mm");
	}
	
	public boolean jahPassou(Date data){
		GregorianCalendar calendarioAtual=new GregorianCalendar();
		//calendarioAtual.add(calendarioAtual.HOUR, 48);  como add mais dias na data
        return calendarioAtual.getTime().after(data);
   }
	
	public Date converteStringData(String dataString){
		try {
			//formatoData = new SimpleDateFormat("dd/MM/yyyy");
			Calendar c = Calendar.getInstance();
			c.setTime(formatoData.parse(dataString));
			return c.getTime();
		} catch (Exception e) {
			System.out.println("deu pau");
		}
		return null;
	}
	
	public String converteDateString(Date data){
		try {
//			SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
			return formatoData.format(data);
		} catch (Exception e) {
			System.out.println("deu pau");
		}
		return null;
	}
	
	public Date converteStringHora(String horaString){
		try {
			//SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(formatoHora.parse(horaString));
			return calendar.getTime();
		} catch (Exception e) {
			System.out.println("deu pau");
		}
		return null;
	}

	public String converteHoraString(Date hora){
		try {
			//SimpleDateFormat formatoData = new SimpleDateFormat("HH:mm");
			return formatoHora.format(hora);
		} catch (Exception e) {
			System.out.println("deu pau");
		}
		return null;
	}
	

}
