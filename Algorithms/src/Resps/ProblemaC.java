package Resps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class ProblemaC {
	private static int minSteps(int[] arr, int n, int p)
	{
		int pasos[] = new int[n];
		int i, j;
		j = n - 1;
		if (n == 0 || arr[0] == 0)
			return 0;
		Arrays.fill(pasos, Integer.MAX_VALUE);
		pasos[n - 1] = 0;
		int [] copy = new int[n];
		System.arraycopy(pasos, 0, copy, 0, n);
		int p2 = p;
		while (true){
			if(j == 0){
				j = n - 1;
			}
			if(j == n - 1){
				System.arraycopy(pasos, 0, copy, 0, n);
			}
			for(i = n - 2; i > -1; i --){
				if(arr[i] + i == j && pasos[j] < Integer.MAX_VALUE && arr[j] != -1 && arr[i] != -1){
					pasos[i] = Math.min(pasos[i], pasos[j] + 1);

				}
				if(pasos[i + 1] < Integer.MAX_VALUE && arr[j] != -1 && arr[i] != -1){
					pasos[i] = Math.min(pasos[i + 1] + 1, pasos[i]);
				}
				if(i - arr[i] == j && pasos[j] < Integer.MAX_VALUE && arr[j] != -1 && arr[i] != -1){
					pasos[i] = Math.min(pasos[i], pasos[j] + 1);

				}
				if(i - 1 >= 0 && pasos[i - 1] < Integer.MAX_VALUE && arr[i - 1] != -1 && arr[i] != -1){
					pasos[i] = Math.min(pasos[i - 1] + 1, pasos[i]);
				}
				if(check(arr, i, j, p2) && pasos[j] < Integer.MAX_VALUE){
					if(pasos[j] + Math.abs(j - i) - 1 < pasos[i]){
						pasos[i] = pasos[j] + Math.abs(j - i);
						p2 = p2 + 1 - Math.abs(j - i);
					}
				}
				if(check(arr, j, i, p2) && pasos[i] < Integer.MAX_VALUE){
					if(pasos[i] + Math.abs(i - j) - 1 < pasos[j]){
						pasos[j] = pasos[i] + Math.abs(i - j);
						p2 = p2 + 1 - Math.abs(j - i);
					}

				}
			}
			j --;
			if((j == 0 && Arrays.equals(copy, pasos))){
				break;
			}
		}			


		for(int m = 0; m < n; m++){
			if (pasos[m] == Integer.MAX_VALUE){
				pasos[m] = -1;
			}
		}
		return pasos[0];

	}


	public static boolean check(int[] arr, int ini, int fin, int plank){
		if(plank < Math.abs(fin - ini) - 1 || fin == ini || Math.abs(fin - ini) <= 1 || arr[fin] == -1 || arr[ini] == -1){
			return false;
		}
		if(ini > fin){
			int temp = fin;
			fin = ini;
			ini = temp;
		}
		for(int i = ini + 1; i < fin; i++){
			if(arr[i] != -1){
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		System.out.println("Entrada: ");
		List<int[]> lista = new ArrayList<int[]>();
		List<Integer> lista2 = new ArrayList<Integer>();
		try{
			while (true){
				String first = input.nextLine();
				if(first.equals("0 0")){
					break;
				}
				String[] values1 = first.split(" ");
				int arr[] = new int[Integer.parseInt(values1[0])];
				int plank = Integer.parseInt(values1[1]);
				String second= input.nextLine();
				String[] values2 = second.split(" ");
				for(int i = 0; i < arr.length; i++){
					arr[i] = Integer.parseInt(values2[i]);
				}
				lista.add(arr);
				lista2.add(plank);
			}
		}catch(Exception e){
			System.out.println("Input incorrecto. Volver a intentar.");
		}
		input.close();
		for(int i = 0; i < lista.size(); i++){
			System.out.println(minSteps(lista.get(i), lista.get(i).length, lista2.get(i)));
		}
	}
}
