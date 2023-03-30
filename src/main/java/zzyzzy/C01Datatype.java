package zzyzzy;

public class C01Datatype {
    public static void main(String[] args) {
        // 변수와 자료형
        // 변수는 데이터를 담는 그릇
        // 변수 선언시 변수에 저장할 값의 유형을 지정해야 함
        // 값의 유형에는 정수/실수/문자/불리언/문자열 등이 있음

        int a = 3;
        int b = 5;
        // int c = 1.5;   변수 유형과 대입하려는 값이 일치x
        System.out.println("a: " + a + ", b: " + b);

        // 변수의 자료형이 동일한 경우 한 줄로 선언가능
        int time = 20, speed = 20;

        // 상수 : 변하지 않는 값을 담은 그릇, final 로 선언
        // 상수 선언시 이름은 주로 대문자 사용
        final double PI = 3.14;
        // PI = 123.456;  // 상수에 값 대입시 오류 발생

        System.out.println("PI : " + PI);


        // 자료형
        // 자바의 자료형에는 기본자료형과 참조자료형으로 나뉨
        // 기본자료형 : 단일값을 다룸 (정수,실수,문자,블리언)
        // 참조자료형 : 값이 저장된 주소를 다룸 (문자열,배열,클래스)

        // 정수 integer : byte, short, int, long
        // 자바에서 숫자는 기본적으로 int로 취급
        int c = 123;
        byte d = 1;
        short e = 25123;

        // 아래의 숫자가 long임을 나타내기 위해 L 접미사 추가
        long f = 1234561234567L;

        // 실수 : float, double
        // 자바에서 실수는 기본적으로 double로 취급
        double g = 1234.5678;

        // 아래의 숫자가 Float임을 나타내기 위해 F 접미사 추가
        float h = 9876.5432F;


        // 문자 : char
        // 자바에서 단일문자를 다룰때 사용 (작은 따움표로 정의)
        char i = 'a', j = '가';
        char k = '\uAC00';  // '가'의 유니코드
        System.out.println(k);

        // 논리형 : boolean
        boolean l = true;

        // 문자열 : String
        // 자바에서 다중문자를 다룰때 사용 (큰 따움표로 정의)
        String m = "Hello";
        String n = "🎈";
        System.out.println(n);


        // 형식문자열을 이용한 출력 : printf
        System.out.printf("a: %d, b: %d \n", a, b);


        // ex) 성적 처리프로그램 v1
        // 이름, 국어, 영어, 수학을 초기화시켜서
        // 총점, 평균을 계산한 뒤 결과 출력
        String name = "홍길동";
        int kor = 99;
        int eng = 98;
        int mat = 99;

        int tot = kor + eng + mat;
        //double avg = (kor + eng + mat) / 3;
        //double avg = tot / 3;  // 정수를 정수로 나누면 결과도 정수
        double avg = tot / 3.0;  // 정수를 실수로 나누면 결과는 실수

        System.out.printf("이름 : %s \n", name);
        System.out.printf("국어 : %d \n", kor);
        System.out.printf("영어 : %d \n", eng);
        System.out.printf("수학 : %d \n", mat);
        System.out.printf("총점 : %d \n", tot);
        System.out.printf("평균 : %.1f \n", avg);

    }
}
