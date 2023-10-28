package com.bengkel.booking.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.bengkel.booking.models.Customer;
import com.bengkel.booking.models.ItemService;
import com.bengkel.booking.models.Vehicle;

public class Validation {
	
	public static String validasiInput(String question, String errorMessage, String regex) {
	    Scanner input = new Scanner(System.in);
	    String result;
	    boolean isLooping = true;
	    do {
	      System.out.print(question);
	      result = input.nextLine();

	      //validasi menggunakan matches
	      if (result.matches(regex)) {
	        isLooping = false;
	      }else {
	        System.out.println(errorMessage);
	      }

	    } while (isLooping);

	    return result;
	  }
	
	public static int validasiNumberWithRange(String question, String errorMessage, String regex, int max, int min) {
	    int result;
	    boolean isLooping = true;
	    do {
	      result = Integer.valueOf(validasiInput(question, errorMessage, regex));
	      if (result >= min && result <= max) {
	        isLooping = false;
	      }else {
	        System.out.println("Pilihan angka " + min + " s.d " + max);
	      }
	    } while (isLooping);

	    return result;
	}
	
	


	// public static String getId(List<Customer> listCustomer){
	// 	String customerId = "";
	// 	customerId = BengkelService.getLoginByCustomerId(listCustomer);
	// 	return customerId;
	// }
	public static List<Customer> validCustomerById(List<Customer> listCustomer, String id){
		List<Customer> listCustomerId = new ArrayList<>();
		String customerId = id;
		listCustomer.stream()
		.filter(data -> data.getCustomerId().equalsIgnoreCase(customerId))
		.forEach((data) -> {
			listCustomerId.add(data);
		});
		
		return listCustomerId;
	}
	public static List<Customer> validCustomerBYPassword(List<Customer> listCustomer, String custId){
		List<Customer> listCustomerPassword = new ArrayList<>();
		// .filter(data -> data.getPassword().equalsIgnoreCase(password))
		
		listCustomer.stream()
		.filter(data -> data.getCustomerId().equalsIgnoreCase(custId))
		.forEach((data) ->{
			listCustomerPassword.add(data);
		});
		return listCustomerPassword;
	}
	public static List<Vehicle> getAllVehicle(List<Customer> listCustomer){
		List<Vehicle> listVehicle = new ArrayList<>();
		for(Vehicle list : listVehicle){
			
		}
		return listVehicle;
	}
	public static String validIdService(List<ItemService> listService, Scanner input){
		List<ItemService> listAddService = new ArrayList<>();
		boolean isTrueService = false;
		String serviceId = "";
		double harga = 0;
		do {
			System.out.println("Silahkan Masukan Service Id :");
			serviceId = input.nextLine();
			for(ItemService list : listService){
				if(list.getServiceId().equalsIgnoreCase(serviceId)){
					isTrueService = true;
					harga += list.getPrice();
				}
			}
			if(isTrueService == true){
				System.out.println();
			}else{
				System.out.println("Service id tidak ditemukan");
			}
		} while (!isTrueService != false);
		return serviceId;
	}
	public static List<ItemService> SearchItemService(List<ItemService> listService, String serviceId){
		List<ItemService> listAddService = new ArrayList<>();

		listService.stream()
		.filter(data -> data.getServiceId().equalsIgnoreCase(serviceId))
		.forEach((data) ->{
			listAddService.add(data);
		});
		return listAddService;
	}
	
}
