package streamExam;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ReduceExam {

	public static void main(String[] args) {
		
		String[] strArr = {
				"Inhrtitance", "Java", "Lamda", "stream",
				"OptionalDouble", "IntStream", "count", "sum"};
		
		Stream.of(strArr).forEach(System.out::println);
		
		// noneMatch는 람다식과 일치하는 요소가 없으면 true를 반환
		boolean noEmptyStr = Stream.of(strArr).noneMatch(s->s.length()==0);
		// findFirst 조건과 일치하는 첫 번째 요소 반환
		Optional<String> sWord = Stream.of(strArr)
										.filter(s->s.charAt(0)=='s').findFirst();
		
		System.out.println(noEmptyStr); //true
		System.out.println(sWord.get()); // stream
		
		IntStream intStream1 = Stream.of(strArr).mapToInt(String::length);
		IntStream intStream2 = Stream.of(strArr).mapToInt(String::length);
		IntStream intStream3 = Stream.of(strArr).mapToInt(String::length);
		IntStream intStream4 = Stream.of(strArr).mapToInt(String::length);
		
		//reduce : 스트림의 요소를 소모해가면서 지정한 연산을 처리하는 최종연산
		
		// 문자열의 갯수를 카운팅
		int count = intStream1.reduce(0, (a,b) -> a + 1);
		// 문자열들의 길이의 합
		int sum = intStream2.reduce(0, (a,b) -> a + b);
		
		// 최댓값
		OptionalInt max = intStream3.reduce(Integer::max);
		// 최솟값
		OptionalInt min = intStream4.reduce(Integer::min);
		
		System.out.println(count); // 8
		System.out.println(sum); // 57
		System.out.println(max.getAsInt()); // 14
		System.out.println(min.getAsInt()); // 3
		
		
		
		
		
		
		}

	}


