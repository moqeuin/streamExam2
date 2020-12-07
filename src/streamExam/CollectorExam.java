package streamExam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class CollectorExam {

	public static void main(String[] args) {
			
		// 스트림은 데이터 구조에 대한 연산을 간단하고 효율적으로 사용하기 위한 객체
		// 최종연산 : collect() - 매개변수 타입이 collector여야만 한다  
		// collector : 인터페이스
		// collectors : 미리 구현된 클래스, static메서드로 사용해서 컬렉터를 제공
		
		// collection -> stream : stream()사용해서 변환
		// stream -> collection : toList() : 스트림의 모든 요소를 컬렉션에다가 수집(list,set)
		// toCollection : 특정 컬렉션 수집(ArrayList, LinkedList, .... ), 매개변수 supplier
		

		// 문자열 배열 -> 스트림 -> 리스트
		String[] str = {"김하나","이둘","셋태영"};
		Stream<String> nameStream = Arrays.stream(str);  
		List<String> strList = nameStream.collect(Collectors.toList());
		
		
		// List -> stream -> ArrayList 
		ArrayList<String> arrList = strList.stream().collect(Collectors.toCollection(ArrayList::new));
		
		// 객체배열 -> 스트림 -> 맵
		Person[] per = {new Person("홍길동", 21), new Person("김두한", 43), new Person("장길산", 23)};
		Stream<Person> stream2 = Stream.of(per);
		Map<String, Integer> map = stream2.collect(Collectors.toMap(Person::getName,Person::getAge));
		
		System.out.println(map.get("홍길동")); // 21
		
		// 스트림 -> 객체배열
		stream2 = Stream.of(per);
		Person[] per2 = stream2.toArray(Person[]::new);
		for (Person p : per2) {
			System.out.println(p.toString());
		}
		
		// collect도 통계정보를 가져올 수 있다
		
		// stream.count()
		stream2 = Stream.of(per);
		long count = stream2.collect(Collectors.counting());
		System.out.println(count); // 3
		
		// stream.mapToInt.sum(), int형으로 출력해서 합을 구한다
		stream2 = Stream.of(per);
		long totalScore = stream2.collect(Collectors.summingInt(Person::getAge));
		System.out.println(totalScore); // 87
		
		// stream.max(comparator.compraringInt(person::getTotalScore), 가장 큰 정수의 객체 저장
		stream2 = Stream.of(per);
		Optional<Person> opt = stream2.collect(Collectors.maxBy(Comparator.comparingInt(Person::getAge)));
		System.out.println(opt.get()); //Person [name=김두한, age=43]
		
		// stream.mapToInt(Comparator.comparingInt(Student::getAge));
		stream2 = Stream.of(per);
		IntSummaryStatistics stat = stream2.collect(Collectors.summarizingInt(Person::getAge));
		System.out.println(stat); 
		// IntSummaryStatistics{count=3, sum=87, min=21, average=29.000000, max=43}
		
		// reduce, 최종연산으로 연산한 결과를 반환한다
		
		IntStream is = new Random().ints(1, 46).distinct().limit(6);
		
		// 최댓값
		OptionalInt max = is.reduce(Integer::max);
		is = new Random().ints(1, 46).distinct().limit(6);
		Optional<Integer> max2 = is.boxed().collect(Collectors.reducing(Integer::max));
		System.out.println("max : " + max + " max2 : " + max2);
		//max : OptionalInt[19] max2 : Optional[39]
		
		// 합계
		is = new Random().ints(1, 46).distinct().limit(6);
		long sum = is.reduce(0, (a,b) -> a + b);
		is = new Random().ints(1, 46).distinct().limit(6);
		long sum2 = is.boxed().collect(Collectors.reducing(0, (a,b) -> a + b));
		System.out.println("sum : " + sum + " sum2 : " + sum2);
		// sum : 166 sum2 : 170
		
		// map + reduce
		stream2 = Stream.of(per);
		int grandTotal = stream2.map(Person::getAge).reduce(0,Integer::sum);
		stream2 = Stream.of(per);
		int grandTotal2 = stream2.collect(Collectors.reducing(0,Person::getAge,Integer::sum));
		System.out.println("grandTotal : " + grandTotal + "grandTotal2 : " + grandTotal2);
		// grandTotal : 87grandTotal2 : 87
		
		// joining : 문자열의 스트림의 모든 요소를 하나의 문자열로 연결해 반환
		stream2 = Stream.of(per);
		String stuName1 = stream2.map(Person::getName).collect(Collectors.joining());
		stream2 = Stream.of(per);
		String stuName2 = stream2.map(Person::getName).collect(Collectors.joining(","));
		stream2 = Stream.of(per);
		String stuName3 = stream2.map(Person::getName).collect(Collectors.joining(",","[","]"));
		System.out.println("stuName1 : " + stuName1 + " stuName2 : " + stuName2 + " stuName3 : " + stuName3);
		// stuName1 : 홍길동김두한장길산 stuName2 : 홍길동,김두한,장길산 stuName3 : [홍길동,김두한,장길산]
	}

}


class Person{
	String name;
	int age;
	
	public Person() {
	
	}

	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}
	
 
	
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}

}
