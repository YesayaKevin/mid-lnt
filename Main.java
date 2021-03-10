import java.io.IOException;
import java.util.*;

public class Main {
	private static final Vector<Karyawan> karyawanList = new Vector<>();
	static Scanner sc = new Scanner(System.in);
	//final -> kyk define di C
	private static int totalAdmin, totalManager, totalSupervisor;
	
	
	public static void main(String[] args) {
		//Menu
		int chooseMenu;
		do{
			System.out.println("Menu:");
			System.out.println("1. Insert data karyawan");
			System.out.println("2. View data karyawan");
			System.out.println("3. Update data karyawan");
			System.out.println("4. Delete data karyawan");
			System.out.println("5. Exit program");
			System.out.print("Input [1-5]:");
			chooseMenu = scanInt();
			switch(chooseMenu) {
				case 1: //Insert Data Karyawan.
					String tempName = null, gender = null, jabatan = null;
					do{
						System.out.print("Input nama karyawan [>= 3]: ");
						tempName = sc.nextLine();
						if(tempName.length() < 3) {
							System.out.println("Error input, please input correctly!");
						}
					} while(tempName.length() < 3);
					do {
						System.out.print("Input jenis kelamin [Laki-laki | Perempuan] (Case Sensitive): ");
						gender = sc.nextLine();
						if(!gender.equals("Laki-laki") && !gender.equals("Perempuan")) {
							System.out.println("Error input, please input correctly!");
						}
					} while(!gender.equals("Laki-laki") && !gender.equals("Perempuan"));
					
					do {
						System.out.print("Input jabatan [Manager | Supervisor | Admin] (Case Sensitive): ");
						jabatan = sc.nextLine();
						if(!jabatan.equals("Manager") && !jabatan.equals("Admin") && !jabatan.equals("Supervisor")) {
							System.out.println("Error input, please input correctly!");
						}
					} while(!jabatan.equals("Manager") && !jabatan.equals("Admin") && !jabatan.equals("Supervisor"));
					
					Karyawan tempKaryawan = new Karyawan(tempName, jabatan, gender);
					karyawanList.add(tempKaryawan); //masukkan karyawan kedalam list.
					System.out.println("Berhasil menambahkan karyawan dengan id " + tempKaryawan.getCode());
					
					if(jabatan.equals("Manager")){
						totalManager++;
						if(totalManager % 3 == 1 && totalManager > 3) {
							System.out.print("Bonus sebesar 10% telah diberikan kepada karyawan");
							for(int i = 0; i < karyawanList.size(); i++) {
								Karyawan temp = karyawanList.get(i);
								if(temp.jabatan.equals("Manager")) {
									temp.salary *= 1.1; 
									System.out.print(temp.getCode());
									if(temp != tempKaryawan) {
										System.out.print(", ");
									}
								}
							}
						}
					} else if(jabatan.equals("Supervisor")){
						totalSupervisor++;
						if(totalSupervisor % 3 == 1 && totalSupervisor > 3) {
							System.out.println("Bonus sebesar 7.5% telah diberikan kepada karyawan");
							for(int i = 0; i < karyawanList.size(); i++) {
								Karyawan temp = karyawanList.get(i);
								if(temp.jabatan.equals("Supervisor")) {
									temp.salary *= 1.075; 
									System.out.print(temp.getCode());
									if(temp != tempKaryawan) {
										System.out.print(", ");
									}
								}
							}
						}
					} else if(jabatan.equals("Admin")){
						totalAdmin++;
						if(totalAdmin % 3 == 1 && totalAdmin > 3) {
							System.out.println("Bonus sebesar 5% telah diberikan kepada karyawan");
							for(int i = 0; i < karyawanList.size(); i++) {
								Karyawan temp = karyawanList.get(i);
								if(temp.jabatan.equals("Admin")) {
									temp.salary *= 1.05;
									System.out.print(temp.getCode());
									if(temp != tempKaryawan) {
										System.out.print(", ");
									}
								}
							}
						}
					}
					System.out.println("ENTER to return");
					sc.nextLine();
					break;
				case 2: // View Data Karyawan
				{
					Vector<Karyawan> karyawanSorted = new Vector<>(karyawanList);
					karyawanSorted.sort(new SortByName());
					System.out.println("|----|-----------------|---------------------------------|---------------|--------------|-------------|");
					System.out.println("|No  |Kode Karyawan    |Nama Karyawan                    |Jenis Kelamin  |Jabatan       |Gaji Karyawan|");
					System.out.println("|----|-----------------|---------------------------------|---------------|--------------|-------------|");
					for(int i = 0; i < karyawanSorted.size() ; i++) {
						Karyawan temp = karyawanSorted.get(i);
						System.out.printf("|%4d|%16s|%33s|%16s|%14s|%12d|\n", i+1, temp.getCode(), temp.nama, temp.gender, temp.jabatan, temp.salary);
					}
					System.out.println("|----|-----------------|---------------------------------|---------------|--------------|-------------|");
					break;
				}
				
				case 3: //Update data
				{
					Vector<Karyawan> karyawanSorted = new Vector<>(karyawanList);
					karyawanSorted.sort(new SortByName());
					System.out.println("|----|-----------------|---------------------------------|---------------|--------------|-------------|");
					System.out.println("|No  |Kode Karyawan    |Nama Karyawan                    |Jenis Kelamin  |Jabatan       |Gaji Karyawan|");
					System.out.println("|----|-----------------|---------------------------------|---------------|--------------|-------------|");
					for(int i = 0; i < karyawanSorted.size() ; i++) {
						Karyawan temp = karyawanSorted.get(i);
						System.out.printf("|%4d|%16s|%33s|%16s|%14s|%12d|\n", i+1, temp.getCode(), temp.nama, temp.gender, temp.jabatan, temp.salary);
					}
					System.out.println("|----|-----------------|---------------------------------|---------------|--------------|-------------|");
					int index;
					System.out.print("Input number: ");
					index = scanInt();
					String tempKode, tempJabatan, tempNama, tempGender;
					int tempSalary, indexUpdate;
					
					for(int i = 0; i < karyawanSorted.size(); i++) {
						if(karyawanSorted.get(index-1).equals(karyawanList.get(i))) {
							do{
								System.out.print("Input kode karyawan [XX-1234]: ");
								tempKode = sc.nextLine();
								if(tempKode.length() < 3) {
									System.out.println("Error input, please input correctly!");
								}
							} while(tempKode.length() < 3);
							
							do{
								System.out.print("Input nama karyawan [>= 3]: ");
								tempNama = sc.nextLine();
								if(tempNama.length() < 3) {
									System.out.println("Error input, please input correctly!");
								}
							} while(tempNama.length() < 3);
							
							do {
								System.out.print("Input jenis kelamin [Laki-laki | Perempuan] (Case Sensitive): ");
								tempGender = sc.nextLine();
								if(!tempGender.equals("Laki-laki") && !tempGender.equals("Perempuan")) {
									System.out.println("Error input, please input correctly!");
								}
							} while(!tempGender.equals("Laki-laki") && !tempGender.equals("Perempuan"));
							
							do {
								System.out.print("Input jabatan [Manager | Supervisor | Admin] (Case Sensitive): ");
								tempJabatan = sc.nextLine();
								if(!tempJabatan.equals("Manager") && !tempJabatan.equals("Admin") && !tempJabatan.equals("Supervisor")) {
									System.out.println("Error input, please input correctly!");
								}
							} while(!tempJabatan.equals("Manager") && !tempJabatan.equals("Admin") && !tempJabatan.equals("Supervisor"));
							System.out.print("Input salary :");
							tempSalary = scanInt();
							System.out.println("Data successfully updated");
							System.out.println("ENTER to return");
							sc.nextLine();
							break;
						}
					}
				}
				
				case 4: //Delete data
				{
					Vector<Karyawan> karyawanSorted = new Vector<>(karyawanList);
					karyawanSorted.sort(new SortByName());
					System.out.println("|----|-----------------|---------------------------------|---------------|--------------|-------------|");
					System.out.println("|No  |Kode Karyawan    |Nama Karyawan                    |Jenis Kelamin  |Jabatan       |Gaji Karyawan|");
					System.out.println("|----|-----------------|---------------------------------|---------------|--------------|-------------|");
					for(int i = 0; i < karyawanSorted.size() ; i++) {
						Karyawan temp = karyawanSorted.get(i);
						System.out.printf("|%4d|%16s|%33s|%16s|%14s|%12d|\n", i+1, temp.getCode(), temp.nama, temp.gender, temp.jabatan, temp.salary);
					}
					System.out.println("|----|-----------------|---------------------------------|---------------|--------------|-------------|");
					
					int index;
					System.out.print("Input number: ");
					index = scanInt();
					for(int i = 0; i < karyawanSorted.size(); i++) {
						if(karyawanSorted.get(index-1).equals(karyawanList.get(i))) {
							karyawanList.remove(i);
							karyawanSorted.remove(index-1);
							break;
						}
					}
					System.out.println("Success Deleted");
					System.out.println("ENTER to return");
					sc.nextLine();
					break;
				}
			}
		} while(chooseMenu != 5);
	}
	
	private static int scanInt() {
		int test = 0;
		boolean temp = false;
		do {
			if (sc.hasNextInt()) {
				temp = true;
				test = sc.nextInt();
			} else {
				sc.nextLine();
				System.out.println("Error input, please input correctly!");
			}
		} while (!temp);
		sc.nextLine();
		return test;
	}
}
