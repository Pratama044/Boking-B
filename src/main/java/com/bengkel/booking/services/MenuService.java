package com.bengkel.booking.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.bengkel.booking.models.BookingOrder;
import com.bengkel.booking.models.Customer;
import com.bengkel.booking.models.ItemService;
import com.bengkel.booking.models.Vehicle;
import com.bengkel.booking.repositories.BookingRepository;
import com.bengkel.booking.repositories.CustomerRepository;
import com.bengkel.booking.repositories.ItemServiceRepository;

public class MenuService {
	private static List<Customer> listAllCustomers = CustomerRepository.getAllCustomer();
	private static List<ItemService> listAllItemService = ItemServiceRepository.getAllItemService();
	private static List<BookingOrder> listBoking = BookingRepository.getAllBokingOrder();
	
	private static Scanner input = new Scanner(System.in);
	public static void run() {
		boolean isLooping = true;
		do {
			login();
			mainMenu();
		} while (isLooping);
		
	}
	
	public static void login() {
		String[] listMenuLogin = {"Login", "Exit"};
		
		PrintService.printMenu(listMenuLogin, "");
		int choice = 0;
		boolean isLooping = true;
		String customerId ="";
		String custId = "";
		String serviceId = "";
		String vehicleType ="";
		do {
			System.out.print("Pilih Menu : ");
			choice = input.nextInt();
			switch (choice) {
				case 1:
				    System.out.println("================ Login ============");
					custId = BengkelService.getLoginById(listAllCustomers, input);
					customerId = custId;
					BengkelService.getCustomerPassword(Validation.validCustomerBYPassword(listAllCustomers, customerId) , input);
					isLooping = true;
					// BengkelService.showMenuLogin(listAllCustomers, "Login");
					// BengkelService.getLoginByCustomerId(listAllCustomers);
					// BengkelService.getLoginCustomerByPassword(Validation.validCustomerById(listAllCustomers, custId));
					// BengkelService.getLoginCustomerByPassword(listAllCustomers, custId);
					break;
			
				default:
				    isLooping = false;
					break;
			}
			
		} while (!isLooping != false);
		
		
		
		do {
			String[] listMenu = {"Informasi Customer", "Booking Bengkel", "Top Up Bengkel Coin", "Informasi Booking", "Logout"};
		    int menuChoice = 0;

		    List<Vehicle> listVehicle = PrintCustomerMenu.getVehicleId(listAllCustomers, customerId);
			PrintService.printMenu(listMenu, "Booking Bengkel Menu");
			menuChoice = Validation.validasiNumberWithRange("Masukan Pilihan Menu:", "Input Harus Berupa Angka!", "^[0-9]+$", listMenu.length-1, 0);
			System.out.println(menuChoice);
			
			switch (menuChoice) {
			    case 1:
				//panggil fitur Informasi Customer
				// String customerId = 
				    vehicleType = PrintCustomerMenu.showCustomerMenu(listAllCustomers, listVehicle, customerId);
				    break;
			    case 2:
				//panggil fitur Booking Bengkel
				    System.out.println("Vehicle Type "+vehicleType);
				    serviceId =BengkelService.getBokingMenu(listVehicle, listAllItemService, listAllCustomers, listBoking, customerId, vehicleType);
				    break;
			    case 3:
				//panggil fitur Top Up Saldo 
				    BengkelService.menuTopUp(listAllCustomers);
				    break;
			    case 4:
				    BengkelService.getInfoBokingOrder(listAllItemService, listBoking, serviceId);
				//panggil fitur Informasi Booking Order
				   
				   break;
			    default:
				   System.out.println("Logout");
				   isLooping = false;
				   break;
			}
			
		} while (isLooping);
	}
	
	public static void mainMenu() {
		
		
		
	}
	
	//Silahkan tambahkan kodingan untuk keperluan Menu Aplikasi
}
