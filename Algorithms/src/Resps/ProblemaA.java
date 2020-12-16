//OJO: Este archivo no pertenece a ningún paquete (package)

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.io.*; 
import java.util.*; 
import java.lang.*; 
import java.util.Arrays;


/**
 * Programa para calcular la cantidad y suma de numeros pares en una lista de numeros
 * @author Nicole Bahamon y Jaime Carvajal
 */
public class ProblemaA {
	private static int tamanio=0;
	private static int primo=0;
	static int x=0;
	public static void main(String[] args) throws Exception {
		ProblemaA instancia = new ProblemaA();
		try ( 
				InputStreamReader is= new InputStreamReader(System.in);
				BufferedReader br = new BufferedReader(is);
				) { 
			String line = br.readLine();

			while(line!=null && line.length()>0 && !"0 0".equals(line)) {
				final String [] dataStr = line.split(" ");
				tamanio=Integer.valueOf(dataStr[0]);
				int tamanioFijo=tamanio;
				primo=Integer.valueOf(dataStr[1]);
				int [][] myArray = new int[tamanio][tamanio];
				int[][]arraySuplementario1=new int[tamanio][tamanio*2]; //accumulation of both files.....
				while(tamanio>0) {
					for (int i=0; i<myArray.length; i++) {
						String[] line1 = br.readLine().split(" ");
						for (int j=0; j<line1.length; j++) {
							arraySuplementario1[i][j]=Integer.parseInt(line1[j]);
							tamanio--;
						}
					}
				}

				arrayConcatenate(arraySuplementario1);
				if(arraySuplementario1[0][0]==0){
					int[] temp=arraySuplementario1[0];
					for(int k=1;k<arraySuplementario1.length;k++)
						if(arraySuplementario1[k][0]!=0){
							arraySuplementario1[0]=arraySuplementario1[k];
							arraySuplementario1[k]=temp;
							break;
						}
				}
				GaussJacques(myArray,primo,arraySuplementario1);
				if (arraySuplementario1[tamanioFijo-1][tamanioFijo-1]==0){
					System.out.println("X");
				}
				else{
					for(int i = 0; i < tamanioFijo; ++i)
					{String prueba="";
					for(int j = tamanioFijo; j < tamanioFijo*2; ++j) {
						if(j==tamanioFijo*2-1){
							System.out.print((int)arraySuplementario1[i][j] + "");
						}
						else{
							System.out.print((int)arraySuplementario1[i][j] + " ");}
					}
					System.out.println();
					}
				}
				System.out.println("*");

				line=br.readLine();
			}
		}

	}



	public static void GaussJacques(int[][] matriz,int m,int[][] matrizAumentada){
		for(int i=0;i<matriz.length;i++){
			acomodarPivote(matrizAumentada,i,m);
		}

	}
	public static void acomodarPivote(int[][] matrizAumentada,int i, int m){
		long[] x=euclidesExtendido(m,matrizAumentada[i][i]);
		int y1=(int) (x[2]+m);
		for(int j=i;j<(matrizAumentada.length)*2;j++){
			matrizAumentada[i][j]=modulo((matrizAumentada[i][j]*y1),m);
		}
		int v = 0;
		for( int k = 0; k < matrizAumentada.length; ++k)
		{
			if( k != i)
			{	
				int valorAReducir = -1*matrizAumentada[k][i];
				v = ( valorAReducir*matrizAumentada[i][i]+matrizAumentada[k][i] );
				matrizAumentada[k][i]=modulo(v,m);

				for( int y = i+1; y < matrizAumentada.length*2; y++ )
				{
					v = ( valorAReducir*matrizAumentada[i][y]+matrizAumentada[k][y] );
					matrizAumentada[k][y]=modulo(v,m);
				}
			}
		}
	}

	public static void arrayConcatenate(int[][]  array1){
		int tamanioPls=array1.length;
		for(int j=0;j<tamanioPls;j++ ){
			array1[j][j+tamanioPls]=1;
		}
	}
	public static int modulo(int a,int m){
		int resultado=0;
		if(a<0){
			resultado=((a % m) + m) % m;
		}
		else{
			resultado=a % m;
		}

		return resultado;
	}
	/* Algoritmo de Euclides extendido realizado por Jorge Valverde-Rebaza(2009) de http://jc-info.blogspot.com/2009/03/algoritmo-de-euclides-extendido-codigo.html:
	 */
	public static long[] euclidesExtendido(long a, long b) 
	{
		long[] resp = new long[3];
		long x=0,y=0,d=0;
		if(b==0)
		{
			resp[0] = a; resp[1] = 1; resp[2] = 0;
		}  
		else
		{
			long x2 = 1, x1 = 0, y2 = 0, y1 = 1;
			long q = 0, r = 0;
			while(b>0)
			{
				q = (a/b);
				r = a - q*b;
				x = x2-q*x1;
				y = y2 - q*y1;
				a = b;
				b = r;
				x2 = x1;
				x1 = x;
				y2 = y1;
				y1 = y;
			}
			resp[0] = a;
			resp[1] = x2;
			resp[2] = y2;
		}
		return resp;  
	}


}
