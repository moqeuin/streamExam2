package streamExam;

import java.util.Optional;
import java.util.OptionalInt;

public class OptionalExam {

	public static void main(String[] args) {
		
		//Optional : 객체의 null처리를 신경쓰지 않기위한 객체, stream같이 사용
		
		// of : 객체를 optional객체에 저장, null값은 허용하지 않는다
		Optional<String> optStr = Optional.of("abcde");  
		Optional<Integer> optInt = optStr.map(String::length);
		
		// ofNullable : of와 마찬가지고 객체 저장, null값을 허용한다.
		Optional<String> optStr2 = Optional.ofNullable(null);
		
		// orElseGet : null값이면 람다식의 반환값으로 대체
		Optional<String> optStr3 = Optional.ofNullable(null);
		String str = optStr3.orElseGet(String::new); 
		
		// orElseThrow : null값일 경우 예외를 던진다
		String st2 = optStr3.orElseThrow(NullPointerException::new);
		
		
		System.out.println(optStr.get()); // 요소를 출력, null값이면 예외처리를 발생
		System.out.println(optInt.get());
		
		int result = Optional.of("123")
					.filter(x->x.length()>0)
					.map(Integer::parseInt)
					.get();
		
		System.out.println(result); //123
		
		// orElse는 null이면 지정된 값을 가져옴
		int result2 = Optional.of("")
					 .filter(x->x.length()>0)
					 .map(Integer::parseInt)
					 .orElse(-1);
		System.out.println(result2); //-1
		
		// ifPresent는 null아닐 경우에 해당문장실행
		Optional.of("456").map(Integer::parseInt)
				.ifPresent(x->System.out.printf("result3=%d%n",x));
		
		OptionalInt optInt1 = OptionalInt.of(0);
		OptionalInt optInt2 = OptionalInt.empty();
		
		System.out.println(optInt1.isPresent()); // true
		System.out.println(optInt2.isPresent()); // false
		
		System.out.println(optInt1.getAsInt()); // 0
		//System.out.println(optInt2.getAsInt()); //NoSuchElementException
		
		System.out.println(optInt1); // OptionalInt[0]
		System.out.println(optInt2); // OptionalInt.empty, empty초기화하면 값이 없는 것으로 취급
		System.out.println(optInt1.equals(optInt2)); // false, 0과 empty 비교가능
	}

}
