package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		List<Employee> list = new ArrayList<>();
		
		System.out.print("Enter full file path: ");
		String path = sc.nextLine();
		
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			
			String line = br.readLine();
			
			while (line != null) {
				String field[] = line.split(",");
				String name = field[0];
				String email = field[1];
				Double salary = Double.parseDouble(field[2]);
				
				list.add(new Employee(name, email, salary));
				
				line = br.readLine();
			}
			
			System.out.println("Enter salary to use as filter");
			Double filterValue = sc.nextDouble();
			
			Comparator<String> comp = (a, b) -> a.toUpperCase().compareTo(b.toUpperCase());
			
			List<String> namesList = list.stream()
					.filter(p -> p.getSalary() > filterValue)
					.map(p -> p.getEmail())
					.sorted(comp)
					.collect(Collectors.toList());
			
			System.out.println();
			System.out.println("Email of people whose salary is more than " + filterValue + ":");
			namesList.forEach(System.out::println);
						
			Double salarySum = list.stream()
					.filter(p -> p.getEmail().toUpperCase().charAt(0) == 'M')
					.map(p -> p.getSalary())
					.reduce(0.0, (a, b) -> a + b);
							
			
			
			System.out.println();
			System.out.print("Sum of salary of people whose name starts with 'M': " +salarySum);
			
			
		} catch(IOException e){
			System.out.println("Error: " + e.getMessage());
		}
		
		
		
		sc.close();
	}

}
