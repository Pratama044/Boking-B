package com.bengkel.booking.services;

import java.util.ArrayList;
import java.util.List;

import com.bengkel.booking.models.Customer;
import com.bengkel.booking.models.MemberCustomer;
import com.bengkel.booking.models.Vehicle;


public class PrintCustomerMenu {
    public static List<Vehicle> getVehicleId(List<Customer> listCustomer, String custId){
        List<Vehicle> listVehicle = new ArrayList<>();
        

        for(Customer list : listCustomer){
            if(list.getCustomerId().equalsIgnoreCase(custId)){
                return list.getVehicles();
            }
            // return list.getVehicles();
            
        }
        return null;
    }
    public static String showCustomerMenu(List<Customer> listCustomer , List<Vehicle> listVehicle, String custId){
        System.out.println("=============== Customer Profil ============");
        
        for(Customer list : listCustomer){
            if(list.getCustomerId().equalsIgnoreCase(custId)){
                if(list instanceof MemberCustomer){
                    System.out.println("Customer Id     :          "+list.getCustomerId());
                    System.out.println("Nama            :          "+list.getName());
                    System.out.println("Customer Status :          "+list.getMember());
                    System.out.println("Alamat          :          "+list.getAddress());
                    System.out.println("Saldo Coin      :          "+((MemberCustomer)list).getSaldoCoin());
                    System.out.println("List Kendaraan:");
                }else{
                    System.out.println("Customer Id     :          "+list.getCustomerId());
                    System.out.println("Nama            :          "+list.getName());
                    System.out.println("Customer Status :          "+list.getMember());
                    System.out.println("Alamat          :          "+list.getAddress());
                    System.out.println("List Kendaraan:");
                }
            }

            
            
            
        }
        int num = 1;
        String formatTable = "| %-5s | %-10s | %-10s | %-15s | %-10s |\n";
        System.out.println("===================================================================");
        System.out.format(formatTable, "No", "Vehicle Id", "Warna", "Tipe Kendaraan", "Tahun");
        System.out.println("===================================================================");
        for(Vehicle list : listVehicle){
            System.out.format(formatTable, num, list.getVehiclesId(), list.getColor(), list.getVehicleType(), list.getYearRelease());
            num++;
            return list.getVehicleType();
        }
        System.out.println("===================================================================");
        return null;
        
    }
}
