import java.util.Comparator;

public class SortByName implements Comparator<Karyawan>{
	public int compare(Karyawan a, Karyawan b) {
		return a.nama.compareTo(b.nama);
	}
	
}
