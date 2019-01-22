package com.javagyanmantra.Excel_Mapper;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ExcelToFile {
	public static void main(String[] args) {
		String rebateFile = "Contract Test.xlsx";
		ContractDataMapper contractDataMapper = new ContractDataMapper();
		try {
			PrintWriter writer = new PrintWriter("C:\\Users\\basan\\Desktop\\Generate\\Contract_data.txt");
			List<Contract> contracts = contractDataMapper.contractDataMapping(rebateFile);
			Collections.sort(contracts, new Comparator<Contract>() {

				@Override
				public int compare(Contract o1, Contract o2) {
					return o1.getCustomerAccount().compareTo(o2.getCustomerAccount());
				}
			});
			List<String> list = new ArrayList<>();
			for (Contract contract : contracts) {
				if (list.contains(contract.getCustomerAccount())) {
					System.out.println(contract);
					writer.print(contract + "\n");
				} else {
					list.add(contract.getCustomerAccount());
					System.out.println("000   " + contract.getCustomerAccount());
					writer.println("000   " + contract.getCustomerAccount() );
					int count = findcustomerAccountOccurance(contracts, contract.getCustomerAccount());
					if (count >= 1) {
						System.out.println(contract);
						writer.print(contract + "\n");
					}
				}
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static int findcustomerAccountOccurance(List<Contract> contracts, String search) {
		int count = 0;
		for (Contract contract : contracts) {
			if (contract.getCustomerAccount().equals(search)) {
				count++;
			}
		}

		return count;
	}
}
