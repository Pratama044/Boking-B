package com.bengkel.booking.services;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.bengkel.booking.models.BookingOrder;
import com.bengkel.booking.models.Customer;
import com.bengkel.booking.models.ItemService;
import com.bengkel.booking.models.MemberCustomer;
import com.bengkel.booking.models.Vehicle;

public class BengkelService {
	public static Scanner input = new Scanner(System.in);
	
	//Silahkan tambahkan fitur-fitur utama aplikasi disini
	
	//Login

	public static String getLoginById(List<Customer> listCustomer ,Scanner input){
		String custId = "";
		boolean istrueId = false;
		int incrementId = 0;
		do {
			System.out.println("Masukan Customer Id :");
			custId = input.nextLine();
			
			for (Customer customer : listCustomer) {
				if(customer.getCustomerId().equalsIgnoreCase(custId)){
					istrueId = true;
				}
			}
			if(istrueId == true){
				System.out.println("Id ditemukan");
			}else{
				System.out.println("Id salah / tidak ada");
				incrementId++;
			}
			if(incrementId == 4){
			    MenuService.login();
		    }
		} while (!istrueId != false);
		return custId;
	}
	// public static String getLoginCustomerByPassword(List<Customer> listCustomers){
		
	// 	// String custId = getLoginByCustomerId(listCustomers);
	// 	String custPassword = Validation.getCustomerPassword(Validation.validCustomerBYPassword(listCustomers), input);
	// 	return custPassword;
	// }
	
	//Info Customer
	
	//Booking atau Reservation
	public static Customer getCustomerById(List<Customer> listCustomer, String idCustomer){
		// listCustomer.stream()
		// .filter(data -> data.getCustomerId().equalsIgnoreCase(idCustomer))
		// .forEach((data) ->{
		// 	return data;
		// });
		for(Customer data : listCustomer){
			if(data.getCustomerId().equalsIgnoreCase(idCustomer)){
				return data;
			}
		}
		return null;
	}
	public static String getCustomerPassword(List<Customer> listCustomers, Scanner input){
		// String custId = getLoginByCustomerId(listCustomers);
		String custPassword = "";
		String password = "";
		boolean istruePassword = false;
		int incrementPassword = 0;

		System.out.println();
		do {
			System.out.println("Masukan Customer Password : ");
			custPassword = input.nextLine();
			for(Customer list : listCustomers){
				if(list.getPassword().equalsIgnoreCase(custPassword)){
					istruePassword = true;
				}
			}
			if(istruePassword == true){
				System.out.println("Password di temukan");
				
			}else{
				incrementPassword++;
				System.out.println("Password salah / tidak ada");
			}
			if(incrementPassword == 4){
			    MenuService.login();
		    }
		} while (!istruePassword != false);	
		return custPassword;
	}
	public static String getBokingMenu(List<Vehicle> listVehicle, List<ItemService> listService, List<Customer> listCustomers, List<BookingOrder> listBoking, String idCustomer, String vehicleType){
		
		System.out.println("=================== Boking Bengkel ===============");
		boolean isTrue = false;
		boolean pilihUlang = false;
		String vehicleId = "";
		String pilihLagi = "";
		Customer customer = null;
		int num = 1;
		double hargaService = 0;
		String formatTable = "| %-5s | %-15s | %-15s | %-15s | %-15s |\n";
		do {
			System.out.println("Masukan Vehicle Id :");
			vehicleId = input.nextLine();
			for(Vehicle list : listVehicle){
				if(list.getVehiclesId().equalsIgnoreCase(vehicleId)){
					isTrue = true;
				}
			}
			if(isTrue == true){
				System.out.println("List Service yang tersedia : ");
			}else{
				System.out.println("Vehicle Id tidak ada / salah");
			}
			
		} while (!isTrue != false);
		boolean corent = false;
					
		System.out.println("Jenis Kendaraan "+vehicleType);
		System.out.println("=====================================================================");
		System.out.format(formatTable ,"No", "Service Id", "Nama Service", "Tipe Kendaraan", "Harga");
		System.out.println("=====================================================================");
	    for(ItemService list : listService){
			if(list.getVehicleType().equalsIgnoreCase(vehicleType)){
				System.out.format(formatTable, num, list.getServiceId(), list.getServiceName(),list.getVehicleType(), (int)list.getPrice());
			    num++;
		    }
			
		}
		System.out.println("=====================================================================");

		// Masukan service id
		String idService = "";
		System.out.println();
		double harga = 0;
		do {
			idService =Validation.validIdService(listService, input );
			do{
				System.out.println("Apakah anda ingin menambahkan service lainya(Y/T)?? :");
				pilihLagi = input.nextLine();
				if(pilihLagi.equalsIgnoreCase("Y")){
					corent = true;
					pilihUlang = true;
					for(ItemService list : listService){
						if(idService.equalsIgnoreCase(list.getServiceId())){
							harga += list.getPrice();
						}
					}
				}else if(pilihLagi.equalsIgnoreCase("T")){
					corent = true;
					pilihUlang = false;
					for(ItemService list : listService){
						if(idService.equalsIgnoreCase(list.getServiceId())){
							harga += list.getPrice();
						}
					}
				}else{
					System.out.println("Input tidak sesuai, silahkan ulangi!");
					corent = false;
				}
			}while(!corent);
		} while (pilihUlang);
		String metodePembayaran = getMetodePembayaran(listCustomers);
		double harusDibayar = 0;
		for(BookingOrder list : listBoking){
			list.setTotalServicePrice(harga);
			harusDibayar = list.getTotalPayment();
		}
		
		System.out.println("Boking berhasil");
		System.out.println("Total Harga Service         : "+formatCurency(harga));
		System.out.println("Total yang harus di bayar   : "+formatCurency(harusDibayar)); 
		// List Customer
		customer = getCustomerById(listCustomers, idCustomer);

		// Membuat boking
		BookingOrder bookingOrder = new BookingOrder("Book-", customer, listService, metodePembayaran, harga, hargaService);
		listBoking.add(bookingOrder);

		String bokingId = "";
		int number = 0;
		for(BookingOrder list : listBoking){
			number++;
			if(num < 10){
				bokingId = "Book-"+ idCustomer+"-00"+number;
			}else if(num < 100 && num >= 10){
				bokingId = "Book-"+idCustomer+"-000"+number;
			}
			list.setBookingId(bokingId);
		}

		return idService;
		
	}
	//Top Up Saldo Coin Untuk Member Customer
	public static String getMetodePembayaran(List<Customer> listCustomer){
		boolean isTrue = false;
		String pembayaran = "";
		do {
			System.out.println("Silahkan masukan metode pembayaran (Saldo coin / cash)");
			pembayaran = input.nextLine();
			for(Customer list : listCustomer){
				if(list.getMember().equalsIgnoreCase("Member")){
					if(pembayaran.equalsIgnoreCase("Saldo Coin") || pembayaran.equalsIgnoreCase("Cash")){
						isTrue = true;
					}
				}else if(list.getMember().equalsIgnoreCase("None Member")){
					if(!pembayaran.equalsIgnoreCase("Cash")){
						
						System.out.println("Metode Pembayaran Tidak Tersedia");
					}else if(pembayaran.equalsIgnoreCase("Saldo Coin")){
						isTrue = false;
					}
				}
			}
			if(isTrue == true){
				System.out.println();
				return pembayaran;
			}else{
				System.out.println("Metode pembayaran tidak tersedia");
			}

		} while (!isTrue != false);
		return null;
	}
	public static void getInfoBokingOrder(List<ItemService> listService, List<BookingOrder> listBoking,String serviceId){
		int num =1;
		System.out.println("================== Data Boking Customer ================");
		System.out.println("==========================================================================================================================");
		String formatTable = "| %-5s | %-20s | %-15s | %-15s |  %-15s | %-15s | %-20s |\n";
		System.out.format(formatTable, "No", "Boking Id", "Nama CUstomer", "Payment Method", "Total Service", "Total Payment", "List Service");
		System.out.println("==========================================================================================================================");
		for(BookingOrder data : listBoking){
			System.out.format(formatTable, num, data.getBookingId(), data.getCustomer().getName(), data.getPaymentMethod(), formatCurency(data.getTotalServicePrice()),formatCurency(data.getTotalPayment()), getServiceList(data.getServices(), serviceId));
			num++;
		}
		System.out.println("==========================================================================================================================");
	}
	public static String getServiceList(List<ItemService> listService, String serviceId){
		String result = "";
		for(int i = 0 ; i < listService.size(); i++){
			if(listService.get(i).getServiceId().equalsIgnoreCase(serviceId)){
				if(i == listService.size() - 1){
				    result += listService.get(i).getServiceName();
			    }else{
				    result += listService.get(i).getServiceName() + ", ";
			    }
			}
		}
		return result;
	}
	public static void menuTopUp(List<Customer> listCustomer){
		System.out.println("=============== Top Up Saldo Coin ==============");
		boolean isMember = false;
		double topUp = 0;
		String result = "";
		for(int i = 0; i < listCustomer.size(); i++){
			if(listCustomer.get(i).getMember().equalsIgnoreCase("Member")){
				isMember = true;
			}else if(listCustomer.get(i).getMember().equalsIgnoreCase("None Member")){
				System.out.println("Maaf menu ini hanya tersedia untuk member");
				break;
			}

		}
		if (isMember == true) {
				System.out.println("Masukan Besaran Top Up : ");
			    topUp = input.nextDouble();
				for (Customer customer : listCustomer) {
			        if(customer instanceof MemberCustomer){
				    topUp += ((MemberCustomer)customer).getSaldoCoin();
				    ((MemberCustomer)customer).setSaldoCoin(topUp);
					System.out.println("Saldo anda sekarang "+formatCurency(topUp));
				}
			}
			
		}
		
		
		
	}

	
	public static String formatCurency(double uang){        
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        DecimalFormat df = new DecimalFormat("Rp#,##0", symbols); 

        return df.format(uang);
    }
	
}
