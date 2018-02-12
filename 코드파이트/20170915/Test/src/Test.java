import java.util.ArrayList;
import java.util.List;

public class Test {
	public static void main(String[] args) {
//		String str = "1,000";
//		String temp = str.replace(",", "");
//		System.out.println(temp);
//		System.out.println(str);
//		System.out.println(str);
//		System.out.println("----------");
//		String[] array = str.split(".");
//		for(String item : array)
//			System.out.print(item+" ");
		
		List<String> list = new ArrayList<>();
		list.add("1");
		list.add("21");
		list.add("22");
		list.add("23");
		list.add("24");
		list.remove("22");
		for(String str : list)
			System.out.println(str);
	}
}