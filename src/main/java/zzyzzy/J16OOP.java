package zzyzzy;

public class J16OOP {
    // 상속 (확장 extend)
    // 부모클래스로 부터 변수, 메서드를 물려받아
    // 새로운 클래스를 만드는 기법
    // 한번 정의된 데이터유형을 필요에 따라
    // 다시 재활용함으로써 반복되는 코드를 줄일수 있음

    // 상속의 장점 : 기존 작성된 클래스 재활용
    // 중복코드 배제, 유지보수 용이
    // 통일성 유지, 다형성 구현 용이

    // 부모/상위/슈퍼 <=> 자식/하위/파생

    public static void main(String[] args) {
        SCV scv1 = new SCV();
        Marine marine1 = new Marine();
        Firebat firebat1 = new Firebat();
        Medic medic1 = new Medic();

        System.out.println(scv1.life);
        scv1.attack();

        System.out.println(marine1.life);
        marine1.attack();

        System.out.println(firebat1.life);
        firebat1.attack();

        System.out.println(medic1.life);
        medic1.attack();
    }
}

class Unit {
    protected int life;     // 생명력
    protected int power;    // 공격력
    protected double move;  // 이동속도
    protected int sight;    // 시야
    protected int time;     // 생산소요시간

    public Unit() {
    }

    public Unit(int life, int power, double move, int sight, int time) {
        this.life = life;
        this.power = power;
        this.move = move;
        this.sight = sight;
        this.time = time;
    }

    protected void attack() {}
    protected void move() {}
}

class SCV extends Unit {
    public SCV() {
        // super : 부모클래스의 생성자를 호출하는 특수한 키워드
        super(60, 5, 2.3444, 7, 20);
    }
}

class Marine extends Unit {
    public Marine() {
        super(40, 6, 1.875, 7, 24);
    }
}

class Firebat extends Unit {
    public Firebat() {
        super(50, 8*2, 1.875, 7, 24);
    }
}

class Medic extends Unit {
    public Medic() {
        super(60, 0, 1.875, 9, 30);
    }
}
