import java.util.Random;

public class Karyawan {
	//encapsulation -> public bisa edit, bisa dibaca.
	//private -> bisa diedit doang atau bisa dibaca doang, atau ga sama sekali.
	private String kode;
	public String nama, jabatan, gender;
	public int salary;
	
	public Karyawan(String nama, String jabatan, String gender) { //constructor -> fungsi untuk menciptakan object.
		//property: code, nama, jabatan, gender, salary
		this.kode = generateCode();
		this.nama = nama;
		this.jabatan = jabatan;
		this.gender = gender;
		
		int salary = 0;
		
		if(jabatan == "Admin") {
			salary = 4000000;
		} else if(jabatan == "Manager"){
			salary = 8000000;
		} else if(jabatan == "Supervisor") {
			salary = 7000000;
		}
		
		this.salary = salary;
	}
	
	private String generateCode(){
		Random rand = new Random(); //Random -> class
		char[] arr = new char[7];
		arr[0] = (char)(rand.nextInt(26) + 65); // 65 - 91
		arr[1] = (char)(rand.nextInt(26) + 65);
		arr[2] = '-';
		arr[3] = (char)(rand.nextInt(10) + 48);
		arr[4] = (char)(rand.nextInt(10) + 48);
		arr[5] = (char)(rand.nextInt(10) + 48);
		arr[6] = (char)(rand.nextInt(10) + 48);
		
		return String.valueOf(arr);
	}
	
	public String getCode(){
		return this.kode;
	}

}