/*
 * Reto 2
 * Autor: David Santonja
 * Curso: 1º DAW - Módulo Programación
 * Fecha: DICIEMBRE 2025
 * 
 * Descripción:
 * Este programa permite registrar información censal referida a un pueblo y sus calles. Para ello 
 * se vale de arrays y bucles for, que permiten ir recopilando información del usuario. 
 * Dicha información se expone al final a modo de resumen.
 */

package reto2;

import java.util.Scanner; import java.util.Scanner; // Importamos la clase Scanner para la entrada de datos por teclado

/* Dentro de main ponemos la clase scanner, creamos las variables y arrays necesarios y vamos llamando
 * a los distintos métodos, que están escritos a continuación.*/
public class Reto2 {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		int calles = numeroCalles(scanner);
		
		String [] nombresCalles = new String[calles];
		int [] totalHabitantesPorCalle = new int [calles];
		int [] numeroPortalesCalle = new int [calles];
		
		int totalHabitantesPueblo = recogerDatosCenso( scanner,
			    nombresCalles,
			    numeroPortalesCalle,
			    totalHabitantesPorCalle);
		
		scanner.close();
		
		resumenDatos(nombresCalles, 
				numeroPortalesCalle, 
				totalHabitantesPorCalle,
				totalHabitantesPueblo);
		
		
	}
		
	/* Creamos el método numeroCalles, que solicita al usuario el número de calles.
	 * Se valida la entrada controlando tanto errores de tipo (letras en lugar de números)
	 * mediante una excepción, como errores de valor (números menores o iguales que cero). */
		
	private static int numeroCalles(Scanner scanner) {

		    int calles = 0;
		    boolean valido = false;

		    while (!valido) {

		        System.out.print("Introduce el número de calles: ");

		        try {
		            calles = scanner.nextInt();

		            if (calles > 0) {
		                valido = true; 
		            } else {
		                System.out.println("El número debe ser mayor que 0.");
		            }

		        } catch (Exception e) {
		            System.out.println("Error: debes introducir un número entero.");
		            scanner.nextLine(); 
		        }
		    }

		    return calles;
		}
		
	/* Creamos el método recogerDatosCenso.
	 * Este método solicita los datos requeridos en el ejercicio mediante dos bucles for anidados.
	 * El primer bucle recorre las calles, pidiendo el nombre de cada una y el número de portales,
	 * almacenando estos datos en arrays creados previamente en el main.
	 * 
	 * El bucle anidado utiliza el número de portales para solicitar el número de habitantes
	 * de cada portal, acumulando dicho valor como número total de habitantes de la calle.
	 * 
	 * Finalmente, se va acumulando el total de habitantes del pueblo, que será el valor
	 * que devuelva este método. */
		
		private static int recogerDatosCenso ( Scanner scanner,
			    String[] nombresCalles,
			    int[] numeroPortalesCalle,
			    int[] totalHabitantesPorCalle) {
			
			scanner.nextLine();
			
		int totalHabitantesPueblo = 0;
		
		for (int i = 0; i < nombresCalles.length; ++i) {
			int numeroPortales = 0;
			System.out.println("Introduce el nombre de la calle " + (i+1) + " : ");
			nombresCalles [i] = scanner.nextLine();
			System.out.println("Introduce el número de portales de la calle " + (i+1) + ": ");
			numeroPortales = scanner.nextInt();
			numeroPortalesCalle [i] = numeroPortales;
			scanner.nextLine();
			int totalHabitantesCalle = 0;
			for (int p = 1; p <= numeroPortales; p++) {
				System.out.println("¿Cuántos habitantes hay en el portal " + p + "? ");
				int habitantesPortal = scanner.nextInt();
				scanner.nextLine();
				totalHabitantesCalle += habitantesPortal;
			}
			totalHabitantesPorCalle [i] = totalHabitantesCalle;
			totalHabitantesPueblo += totalHabitantesCalle;
			
		}
		return totalHabitantesPueblo;
	}
		
		
		/*Por último creamos este método resumen que por medio de los parámetros recibidos y 
		 * un bucle for sencillo muestra la información recopilada tal como se pide en el enunciado.*/
	
	private static void resumenDatos (String[] nombresCalles, 
			int[] numeroPortalesCalle,
			int [] totalHabitantesPorCalle, 
			int totalHabitantesPueblo) {
		System.out.println("\nRESUMEN\n");
		for (int i=0; i < nombresCalles.length; i++) {
			System.out.println("Calle: " + nombresCalles[i]);
			System.out.println("Número de portales: " + numeroPortalesCalle[i]);
			System.out.println("Número de habitantes en la calle: " + totalHabitantesPorCalle[i]);
		}
		System.out.println("Número habitantes pueblo: " + totalHabitantesPueblo);
	}
	
}






