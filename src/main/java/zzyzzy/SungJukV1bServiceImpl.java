package zzyzzy;

import java.util.Scanner;

public class SungJukV1bServiceImpl implements SungJukV1bService {
    private Scanner sc = null;
    private SungJukVO[] sjs = null;
    private int idx = 0;

    public SungJukV1bServiceImpl() {
        sc = new Scanner(System.in);
        sjs = new SungJukVO[10];
    }

    // 성적 프로그램 메뉴
    public String displayMenu() {
        StringBuilder sb = new StringBuilder();
        sb.append("---------------------------- \n")
          .append(" 성적 처리 프로그램 v1 \n")
          .append("---------------------------- \n")
          .append(" 1. 성적데이터 추가 \n")
          .append(" 2. 성적데이터 조회 \n")
          .append(" 3. 성적데이터 상세조회 \n")
          .append(" 4. 성적데이터 수정 \n")
          .append(" 5. 성적데이터 삭제 \n")
          .append(" 0. 프로그램 종료 \n")
          .append("---------------------------- \n")
          .append(" 작업을 선택하세요 : ");
        System.out.print(sb);
        String menu = sc.next();

        return menu;
    }

    public void processMenu(String menu) {
        switch (menu) {
            case "1": newSungJuk(); break;
            case "2": readSungJuk(); break;
            case "3": readOneSungJuk(); break;
            case "4": modifySungJuk(); break;
            case "5": removeSungJuk(); break;
            case "0": System.exit(0); break;
            default:
                System.out.println("\n>> 잘못 입력하셨습니다! <<\n");
        }
    }

    public void removeSungJuk() {
        // 이름입력 -> 대상검색 -> 대상제거
        System.out.print("삭제할 학생이름은? ");
        String name = sc.next();

        for (int i = 0; i < sjs.length; i++) {
            if (sjs[i] != null && sjs[i].getName().equals(name)) {
                sjs[i] = null;  // 삭제할 배열요소에 null 대입
                System.out.println("\n삭제되었습니다\n");
                break;
            }
        }
        
    }

    public void modifySungJuk() {
        // 이름입력 -> 대상검색 -> 새로운데이터입력 -> 성적처리
        System.out.print("수정할 학생이름은? ");
        String name = sc.next();

        for (int i = 0; i < sjs.length; i++) {
            if (sjs[i] != null && sjs[i].getName().equals(name)) {
                System.out.print("국어는? ");
                int kor = sc.nextInt();
                System.out.print("영어은? ");
                int eng = sc.nextInt();
                System.out.print("수학은? ");
                int mat = sc.nextInt();

                SungJukVO sj = new SungJukVO(name, kor, eng, mat);
                computeSungJuk(sj);
                sjs[i] = sj;   // 기존 성적데이터 위치에
                               // 새롭게 생성한 객체 대입
                System.out.println("\n수정완료!!\n");
                break;
            }
        }
    }

    public void readOneSungJuk() {
        // 이름입력 -> 대상검색 -> 대상출력
        System.out.print("조회할 학생이름은? ");
        String name = sc.next();

        SungJukVO one = null;
        for (SungJukVO sj : sjs) {
            if (sj != null && sj.getName().equals(name)) {
                one = sj;  break;
            }
        }

        if (one != null) {
            System.out.printf("\n %s \n\n", one);
        } else {
            System.out.println("\n찾는 데이터가 없습니다!\n");
        }

    }

    // 성적 리스트 조회 (이름,국어,영어,수학)
    public void readSungJuk() {
        String fmt = "\n %s %d %d %d \n\n";
        for (SungJukVO sj : sjs) {
            if (sj != null)  // 배열에 성적데이터가 존재한다면
                System.out.printf(fmt, sj.getName(),
                    sj.getKor(), sj.getEng(), sj.getMat());
        } // sjs 배열에 저장된 모든 성적데이터 출력
    }

    // 성적 데이터 추가
    public void newSungJuk() {
        System.out.print("이름은? ");
        String name = sc.next();
        System.out.print("국어는? ");
        int kor = sc.nextInt();
        System.out.print("영어은? ");
        int eng = sc.nextInt();
        System.out.print("수학은? ");
        int mat = sc.nextInt();

        SungJukVO sj = new SungJukVO(name, kor, eng, mat);
        computeSungJuk(sj);    // 성적 처리
        sjs[idx++] = sj;       // 처리된 성적데이터 배열에 저장
    }

    public void computeSungJuk(SungJukVO sj) {
        sj.setTot( sj.getKor() + sj.getEng() + sj.getMat() );
        sj.setAvg( (double) sj.getTot() / 3 );

        switch ((int)(sj.getAvg()/10)) {
            case 10: case 9: sj.setGrd('수'); break;
            case 8: sj.setGrd('우'); break;
            case 7: sj.setGrd('미'); break;
            case 6: sj.setGrd('양'); break;
            default: sj.setGrd('가'); break;
        }
    }

}
