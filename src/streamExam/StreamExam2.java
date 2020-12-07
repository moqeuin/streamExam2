package streamExam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

enum Cont {ASIA, AFRICA,AMERICA, EUROPE}

class Country{
	
	String name;
	Cont cont;
	int popu;
	boolean oecd;
	
	public Country(String name, Cont cont, int popu, boolean oecd) {
		super();
		this.name = name;
		this.cont = cont;
		this.popu = popu;
		this.oecd = oecd;
	}

	public String getName() {
		return name;
	}

	public Cont getCont() {
		return cont;
	}

	public int getPopu() {
		return popu;
	}

	public boolean isOecd() {
		return oecd;
	}
	
	public String toString() {
		return name + "in" + cont + " : " + popu + (oecd?"O":"X");
	}
}

class Data{
	// asList는 배열을 리스트처럼 다루기 위한 메서드, 배열의 참조주소를 가지고 있어 원 배열의 데이터를 변경할 수 있다
	// 배열이므로 정적데이터라서 데이터를 추가할 수 없다.
	static List<Country> listData = Arrays.asList(
									new Country("한국", Cont.ASIA,50,true),
									new Country("미국", Cont.AMERICA, 130, true),
									new Country("중국", Cont.ASIA, 1600, false),
									new Country("일본", Cont.ASIA, 120, true),
									new Country("프랑스", Cont.EUROPE, 90, true),
									new Country("나이지리아", Cont.AFRICA, 60, false)
			
									); 
}

// 스트림을 사용하지 않은 과정

public class StreamExam2 {

	public static void main(String[] args) {
		
		// 1. 중간 저장할 컬렉션이 필요
		List<Country> oecd = new ArrayList<Country>();
		
		
		// 추출, 정렬, 합산하는 알고리즘을 구현해야한다
		
		// 추출
		for (Country con : Data.listData) {
			 
			if(con.oecd) {
				oecd.add(con);
			}
		}
		
		
		// 3. 상위를 뽑기위해서 정렬하는 과정 필요
		// 정렬
		Collections.sort(oecd, new Comparator<Country>() {

			@Override
			public int compare(Country o1, Country o2) {
				
				return o2.popu - o1.popu;
			}							
		});
		
		//합산
		int total = 0;
		for (int i = 0; i < 3; i++) {
			total += oecd.get(i).popu;
		}
		
		System.out.println("oecd 상위 3개국의 총 인구 : " + total);
		
		
	}

}
