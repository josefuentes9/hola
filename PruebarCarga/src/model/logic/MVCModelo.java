package model.logic;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

import com.opencsv.CSVReader;


import model.data_structures.ListaEncadenada;
import view.MVCView;
/**
 * Definicion del modelo del mundo
 *
 */
public class MVCModelo {
	/**
	 * Constantes
	 */
	public final static int MES = 0;
	public final static int DIA = 1;
	public final static int HORA = 2;
	/**
	 * Atributos del modelo del mundo
	 */
	private ListaEncadenada<ListaEncadenada<UBERTrip>> viajes;
	//private ListaEncadenada<UBERTrip> datos;
	private MVCView view;

	private int CantidadDatos;

	private UBERTrip viaje;

	private double mayor;

	private double menor;

	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public MVCModelo()
	{
		viajes = new ListaEncadenada<ListaEncadenada<UBERTrip>> ();
		mayor=0;
		menor=999999999;
		CantidadDatos=0;
	}
	/**
	 * Cambia el mayor por el numero de entrada
	 * 
	 */
	public int darCantidadDatos()
	{
		return CantidadDatos;
	}

	public void cambiarCantidadDatos(int n)
	{
		CantidadDatos=n;
	}
	/**
	 * Cambia el mayor por el numero de entrada
	 * 
	 */
	public void cambiarMayor(double n)
	{
		mayor=n;
	}

	public double darMayor()
	{
		return mayor;
	}
	/**
	 * Cambia el menor por el numero de entrada
	 * 
	 */
	public void cambiarMenor(double n)
	{
		menor=n;
	}
	public double darMenor()
	{
		return menor;
	}

	public void crearLista(int trimestre) throws Exception
	{
		viajes.insertarCabeza(agregarporMeses(trimestre));
		viajes.insertarCola(agregarporDias(trimestre));
		viajes.insertarCola(agregarporHora(trimestre));
		view.printMessage("La zona con menor identificador en todos los archivos del trimestre seleccionado fue: "+ darMenor());
		view.printMessage("La zona con mayor identificador en todos los archivos del trimestre seleccionado fue: "+ darMayor());
	}

	public ListaEncadenada<UBERTrip> agregarporHora(int trimestre)throws Exception
	{
		ListaEncadenada<UBERTrip> datos=new ListaEncadenada<UBERTrip>();
		CSVReader reader=null;
		reader = new CSVReader(new FileReader("./data/bogota-cadastral-2018-"+trimestre+"-All-HourlyAggregate.csv"));
		int contador=0;
		String [] nextLine=reader.readNext();
		while ((nextLine = reader.readNext()) != null) {
			// nextLine[] is an array of values from the line
			nextLine=reader.readNext();
			if(contador==0){
				datos.insertarCabeza(new UBERTrip(nextLine[0],nextLine[1],nextLine[2],nextLine[3],nextLine[4],nextLine[5], nextLine[6]));
			}
			else{
				datos.insertarCola(new UBERTrip(nextLine[0],nextLine[1],nextLine[2],nextLine[3],nextLine[4],nextLine[5], nextLine[6]));

			}
			if(mayor<datos.dar(contador).getSourceid()){
				cambiarMayor(datos.dar(contador).getSourceid());
			}
			else if(menor>datos.dar(contador).getSourceid()){
				cambiarMenor(datos.dar(contador).getSourceid());
			}
			contador++;
			cambiarCantidadDatos(CantidadDatos+1);
		}
		view.printMessage("Total de viajes en el archivo de horas del trimestre seleccionado: "+contador);
		//System.out.print("Total de viajes en el archivo de Horas del trimestre seleccionado: "+contador);
		reader.close();
		return datos;

	}

	public ListaEncadenada<UBERTrip> agregarporDias(int trimestre)throws Exception
	{
		ListaEncadenada<UBERTrip> datos=new ListaEncadenada<UBERTrip>();
		CSVReader reader=null;
		reader = new CSVReader(new FileReader("./data/bogota-cadastral-2018-"+trimestre+"-WeeklyAggregate.csv"));
		int contador=0;
		String [] nextLine=reader.readNext();
		while ((nextLine = reader.readNext()) != null) {
			// nextLine[] is an array of values from the line
			nextLine=reader.readNext();
			if(contador==0){
				datos.insertarCabeza(new UBERTrip(nextLine[0],nextLine[1],nextLine[2],nextLine[3],nextLine[4],nextLine[5], nextLine[6]));
			}
			else{
				datos.insertarCola(new UBERTrip(nextLine[0],nextLine[1],nextLine[2],nextLine[3],nextLine[4],nextLine[5], nextLine[6]));

			}
			if(mayor<datos.dar(contador).getSourceid()){
				cambiarMayor(datos.dar(contador).getSourceid());
			}
			else if(menor>datos.dar(contador).getSourceid()){
				cambiarMenor(datos.dar(contador).getSourceid());
			}
			//System.out.print(datos.dar(contador).getDstid());
			//System.out.print("---------"+contador+"holafin--");
			contador++;
			cambiarCantidadDatos(CantidadDatos+1);
		}
		view.printMessage("Total de viajes en el archivo de dias del trimestre seleccionado: "+contador);
		//System.out.print("Total de viajes en el archivo de dias del trimestre seleccionado: "+contador);
		reader.close();
		return datos;

	}

	public ListaEncadenada<UBERTrip> agregarporMeses(int trimestre)throws Exception
	{
		ListaEncadenada<UBERTrip> datos=new ListaEncadenada<UBERTrip>();
		CSVReader reader=null;
		reader = new CSVReader(new FileReader("./data/bogota-cadastral-2018-"+trimestre+"-All-MonthlyAggregate.csv"));
		int contador=0;
		String [] nextLine=reader.readNext();
		while ((nextLine = reader.readNext()) != null) {
			// nextLine[] is an array of values from the line
			nextLine=reader.readNext();
			if(contador==0){
				datos.insertarCabeza(new UBERTrip(nextLine[0],nextLine[1],nextLine[2],nextLine[3],nextLine[4],nextLine[5], nextLine[6]));
			}
			else{
				datos.insertarCola(new UBERTrip(nextLine[0],nextLine[1],nextLine[2],nextLine[3],nextLine[4],nextLine[5], nextLine[6]));

			}
			if(mayor<datos.dar(contador).getSourceid()){
				cambiarMayor(datos.dar(contador).getSourceid());
			}
			else if(menor>datos.dar(contador).getSourceid()){
				cambiarMenor(datos.dar(contador).getSourceid());
			}
			//System.out.print(datos.dar(contador).getDstid());
			//System.out.print("---------"+contador+"holafin--");
			contador++;
			cambiarCantidadDatos(CantidadDatos+1);
		}
		view.printMessage("Total de viajes en el archivo de meses del trimestre seleccionado: "+contador);
		//System.out.print("Total de viajes en el archivo de meses del trimestre seleccionado: "+contador);
		reader.close();
		return datos;

	}

	public void consultarTiempoPromedioyDesviaciónEstandar(int destino, int origen, int info)
	{
		boolean encontrado=false;
		for(int i=0; i<viajes.dar(info).darLongitud()&&encontrado==false;i++)
		{
			if(viajes.dar(info).dar(i).getDstid()==destino&&viajes.dar(info).dar(i).getSourceid()==origen)
			{
				view.printMessage("Para el viaje"+i+"el tiempo promedio es: "+viajes.dar(info).dar(i).getMean_travel_time()+" y la desviación estandar es"+viajes.dar(info).dar(i).getStandard_deviation_travel_time());
			encontrado=true;
			}
		}
		if (!encontrado)
		{
			//mostrar en pantalla
			view.printMessage("No existe información al respecto");
		}
	}
	
	

	public void mayorTiempoPromedio(int n, int mes)
	{
		
	}

}
