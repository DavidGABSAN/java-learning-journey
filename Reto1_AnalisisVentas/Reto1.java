/*
 * Descripción:
 * Este programa permite registrar ventas diarias y calcular o analizar:
 * - Total de ventas acumuladas a lo largo de los días introducidos.
 * - La media de ventas diaria.
 * - Contar cuántos días han generado más de 1000€ y cuántos han quedado por debajo de 500€.
 * - Si todas las ventas de cada día han sido mayores o iguales a 500€.
 * - La tendencia que han seguido las ventas (CRECIENTE, DECRECIENTE, ESTABLE o IRREGULAR).
 * - Una valoración del rendimiento (MALO, REGULAR o EXCELENTE).
 */

package reto1;
import java.util.Scanner; // Importamos la clase Scanner para leer datos por teclado.

public class Reto1 {

	public static void main(String[] args) {
		
		// Punto de entrada del programa.
	    // Llamo al método ProgramaVentas(), que contiene toda la lógica de interacción y cálculos.
		
		programaVentas ();
		
	}
	
	/* Método principal del programa:
	 * 
	 * - Pide al usuario que introduzca el número de días que quiere registrar. 
	 * - Lee las ventas diarias con validación.
	 * - Calcula totales, medias, contadores y tendencia.
	 * - Muestra un resumen final con resultados y rendimiento.*/

	private static void programaVentas () {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Bienvenido al programa VENTAS.\n\nIntroduzca cuántos días de ventas desea registrar: ");
		
		// ENTRADA DATOS POR PARTE DEL USUARIO Y REALIZACIÓN DE LOS CÁLCULOS
		
		float ventaAcumulado = 0f; // Suma las ventas totales introducidas por el usuario.
		int ventMayor1000 = 0; // Días con importes superiores a 1000€.
		int ventMenor500 = 0; // Días con importes inferiores a 500€.
		int ventMayorIgual500 = 0; // Total de días con venta mayor o igual a 500€.
		float copiaVenta = 0; // Guarda la venta del día anterior.
		int consecutivosMayores = 0; // Veces que la venta sube respecto al día anterior.
		int consecutivosMenores = 0; // Veces que la venta baja respecto al día anterior.
		int consecutivosIguales = 0; // Veces que la venta se mantiene igual que el día anterior.
		
		int totalDias = scanner.nextInt(); // Solicita al usuario cuántos días de ventas desea registrar.
		while (totalDias <= 0) {
			System.out.print("Valor incorrecto para días. Por favor introduce un número positivo mayor que cero: ");
			totalDias = scanner.nextInt();
		}
		
		/* No se permiten 0 días porque el programa necesita al menos un día de datos para calcular totales, 
		 * medias y tendencias, ya que analiza un periodo. En cambio, sí se permiten días con 0 € de ventas, 
		 * ya que son una situación real (un día sin ventas) y además, lógicamente, penalizan el promedio.*/

		// BUCLE PRINCIPAL
		
		/* Bucle principal: recorre cada día de ventas y va actualizando los cálculos.
		 * Es un bucle for en el que la variable dia empieza en 1, se ejecuta mientras dia sea menor o igual
		 * que totalDias (introducido por el usuario) y se incrementa de uno en uno en cada iteración. */
		
		for (int dia = 1; dia <= totalDias; dia++) {
			
		// 1. Entrada y validación del importe de ventas del día
			
			System.out.print("Introduce el importe de las ventas totales del día " + dia + ": ");
			
			float ventaDia = scanner.nextFloat(); // Usuario introduce el total de ventas de cada día.
		
			// Impedimos que se puedan introducir importes negativos en las ventas  mediante este bucle while.
			while (ventaDia < 0) {
				System.out.print("El valor del importe no puede ser negativo, introdúcelo de nuevo por favor: ");
				ventaDia = scanner.nextFloat(); 
			}
	
		// 2. Cáculo del total de ventas acumuladas y contadores de tramos de ventas 
				
			ventaAcumulado = ventaAcumulado + ventaDia; 
			
			/* Utilizamos un condicional if anidado:
			 * - Si las ventas son mayores o iguales a 500€, aumentamos ventMayorIgual500.
			 * - Dentro de ese caso, si además son mayores de 1000€, aumentamos ventMayor1000.
			 * - Si no se cumple la primera condición (ventas < 500), aumentamos ventMenor500. */
			
			if (ventaDia >=500) {
				ventMayorIgual500++;
					if (ventaDia > 1000) {
						ventMayor1000++;
						}
					}
		
			else {
				ventMenor500++;
			}
				
		// 3. Comparación con el día anterior para estudiar la tendencia
		
			/* Utilizamos condicionales anidados para, en primer lugar, tratar de forma especial el primer día (dia == 1),
			 * en cuyo caso solo guardamos el valor de ventaDia, ya que no tenemos un día anterior con el que comparar.
			 * A partir del segundo día comparamos la venta actual con la del día anterior (copiaVenta) y actualizamos
			 * el contador correspondiente (sube, baja o se mantiene igual). Esto nos permitirá más adelante determinar
			 * la tendencia global del período. */
			
				if (dia == 1) {
					copiaVenta = ventaDia;
				}
				else {
					if (ventaDia > copiaVenta) {
						copiaVenta = ventaDia;
						consecutivosMayores++;	
					}
					else if (ventaDia < copiaVenta) {
						copiaVenta = ventaDia;
						consecutivosMenores++;
					}
					else if (ventaDia == copiaVenta) {
						copiaVenta = ventaDia;
						consecutivosIguales++;
					}
				}
				
		}
		
		// IMPRESIÓN RESULTADOS COMO RESUMEN
		
		System.out.println("\nRESUMEN:\n");
		
		// 1. Impresión de la venta total
		
		float ventaAcumuladoRedondeado = Math.round(ventaAcumulado * 100) / 100f;  
		/* Redondeamos el total acumulado a dos decimales:
		 1) Multiplicamos por 100 para desplazar los decimales.
		 2) Usamos Math.round() para redondear.
		 3) Dividimos entre 100f para volver a dejar el número con dos decimales.
		 Es importante destacar que creamos la variable redondeada para poder redondear en ella y no sobre la venta acumulada,
		 porque si redondeamos la venta total quedaría alterada el resultado de la media diaria. Tanto el total redondeado
		 como el promedio redondeado lo usaremos únicamente para imprimir dichos resultados, y que queden más coherentes
		 conforme al formato de un precio (2 decimales y redondeo).*/
		
		System.out.println("Las ventas totales en " + totalDias + " días han sido de " + ventaAcumuladoRedondeado + "€.");
		
		// 2. Cálculo promedio diario e impresión
		
		float promedioDiario = ventaAcumulado / totalDias; // Media de ventas diaria.
		/* Aplicamos el redondeo de la misma forma que anteriormente.
		 * Usaremos el promedio diario sin redondear para calcular los tramos del rendimiento.*/
		float promedioDiarioRedondeado = Math.round(promedioDiario * 100) / 100f;
		System.out.println("La media de ventas diaria ha sido de " + promedioDiarioRedondeado + "€.");
		
		// 3. Impresión número de días con ventas mayores a 1000€ e inferiores a 500€
		
		System.out.println("Se han reportado " + ventMayor1000 + " días con un importe superior a 1000€.");
		System.out.println("Se han reportado " + ventMenor500 + " días con un importe inferior a 500€.");
		
		// 4. Impresión todas ventas iguales o mayores a 500€
		
		/* Sabemos que todos los días tienen ventas iguales o mayores a 500€ usando una condición que 
		 * establezca que la variable ventMayorIgual500 (acumulado calculado en el bucle for) es igual
		 * al número total de días declarados.*/
		
		if (ventMayorIgual500 == totalDias) {
			System.out.println("Todas las ventas han sido iguales o mayores a 500€.");
		}
		
		// 5. Finalización e impresión de la funcionalidad rachas
		
		/* - Establecemos una condición primaria que descarte la opción de poder establecer tendencias
		 * con un solo día, ya que no tiene sentido.
		 * - El segundo condicional es el más importante. En el establecemos las igualdades que nos 
		 * permiten saber si la racha ha sido creciente, decreciente, estable o irregular.
		 * Igualamos nuestras variables acumuladas (consecutivosMayores, consecutivosMenores o consecutivosIguales) 
		 * al total de días menos uno, porque solo hay comparaciones a partir del segundo día.
		 * El primer día no se puede comparar con ningún valor anterior, ya que no existe, por eso el número máximo 
		 * de comparaciones posibles es totalDias - 1. Si no tuviéramos esto en cuenta, la detección de la tendencia
		 * sería incorrecta.*/
		
		if (totalDias == 1) {
			System.out.println("\nNo se puede determinar tendencia con un solo día.");
		}
		
		if (totalDias > 1) {
		
				System.out.println("\nRACHA DE VENTAS EN LOS DÍAS INTRODUCIDOS:\n");
			if (consecutivosMayores == totalDias - 1) {
				System.out.println("Ventas crecientes: cada día se ha vendido más que el anterior.");
			}
			else if (consecutivosMenores == totalDias - 1) {
				System.out.println("Ventas decrecientes: cada día se ha vendido menos que el anterior.");
			}
			else if (consecutivosIguales == totalDias - 1) {
				System.out.println("Ventas estables: las ventas diarias han sido iguales todos los días.");
			}
			else {
				System.out.println("Ventas irregulares o con altibajos: se han reportado una combinación de subidas\ny bajadas en las ventas diarias que no forma una tendencia estable.");
			}
		}
		
		// 6. Aplicación del rendimiento
		
		/* El enunciado define claramente los casos “Mala” (media < 300) y “Excelente” (todas las ventas ≥ 500). 
		 * Para los casos que no entran en ninguna de esas dos categorías, como por ejemplo una media muy alta 
		 * pero con algunos días malos, he decidido clasificarlos como “Regular”, porque el enunciado no especifica 
		 * una categoría distinta para ellos. De este modo siempre hay una clasificación posible y respetamos las 
		 * reglas dadas.
		 * El hecho es que, he decidido ceñirme al enunciado tal cual está escrito, pero este apartado quizás sea algo
		 * confuso o ambiguo para mí.*/
		
		System.out.println("\nRENDIMIENTO:\n");
		
		if (promedioDiario < 300) {
			System.out.println("El rendimiento de las ventas respecto a los días introducidos ha sido MALO.");
		}
		else if (ventMayorIgual500 == totalDias) {
			System.out.println("El rendimiento de las ventas respecto a los días introducidos ha sido EXCELENTE.");
		}
		else {
			System.out.println("El rendimiento de las ventas respecto a los días introducidos ha sido REGULAR.");
		}
		
		
		scanner.close(); // Cerramos el scanner.
	}
	
}

