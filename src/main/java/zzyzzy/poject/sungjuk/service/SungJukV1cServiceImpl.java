package zzyzzy.poject.sungjuk.service;

import zzyzzy.poject.sungjuk.model.SungJukVO;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SungJukV1cServiceImpl implements SungJukV1cService {
    private Scanner sc = null;
    private SungJukVO[] sjs = null;
    private int idx = 0;

    public SungJukV1cServiceImpl() {
        sc = new Scanner(System.in);
        sjs = new SungJukVO[10];
    }

    // 성적 프로그램 메뉴
    public int displayMenu() {
        int menu = -1;

        StringBuffer sb = new StringBuffer();
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

        try {
            menu = sc.nextInt();
        } catch (InputMismatchException ex) {
            // nextInt 시 문자를 입력했을때 입력 버퍼에 남은 문자찌꺼기 제거
            sc.nextLine();
            // 숫자가 아닌 그 이외 문자 입력시 menu에 -1 대입
            menu = -1;
        }

        return menu;
    }

    public void processMenu(int menu) {
        switch (menu) {
            case 1: newSungJuk(); break;
            case 2: readSungJuk(); break;
            case 3: readOneSungJuk(); break;
            case 4: modifySungJuk(); break;
            case 5: removeSungJuk(); break;
            case 0: System.exit(0); break;
            default:
                System.out.println("\n>> 잘못 입력하셨습니다! <<\n");
        }
    }

    public void removeSungJuk() {
        // 이름입력 -> 대상검색 -> 대상제거
        System.out.print("삭제할 학생이름은? ");
        String name = sc.next();

        try {
            for (int i = 0; i < sjs.length; i++) {
                if (sjs[i].getName().equals(name)) {
                    sjs[i] = null;  // 삭제할 배열요소에 null 대입
                    System.out.println("\n삭제되었습니다\n");
                    break;
                }
            }
        } catch (NullPointerException ex) {
        }
        
    }

    public void modifySungJuk() {
        // 이름입력 -> 대상검색 -> 새로운데이터입력 -> 성적처리
        System.out.print("수정할 학생이름은? ");
        String name = sc.next();

        for (int i = 0; i < sjs.length; i++) {
            try {
                if (sjs[i].getName().equals(name)) {
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
            } catch (NullPointerException ex) {
            } catch (InputMismatchException ex) {
                System.out.println("\n잘못 입력하셨습니다... 다시 시도하세요!\n");
                sc.nextLine();
                return;
            }
        }
    }

    public void readOneSungJuk() {
        // 이름입력 -> 대상검색 -> 대상출력
        System.out.print("조회할 학생이름은? ");
        String name = sc.next();

        SungJukVO one = null;

        try {
            for (SungJukVO sj : sjs) {
                if (sj.getName().equals(name)) {
                    one = sj;
                    break;
                }
            }
        } catch (NullPointerException ex) {
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
        try {
            for (SungJukVO sj : sjs) {
                System.out.printf(fmt, sj.getName(),
                        sj.getKor(), sj.getEng(), sj.getMat());
            } // sjs 배열에 저장된 모든 성적데이터 출력
        } catch (NullPointerException ex) {
        }
    }

    // 성적 데이터 추가
    public void newSungJuk() {
        String name = "";
        int kor = 0, eng = 0, mat = 0;

        try {
            System.out.print("이름은? ");
            name = sc.next();
            System.out.print("국어는? ");
            kor = sc.nextInt();
            System.out.print("영어은? ");
            eng = sc.nextInt();
            System.out.print("수학은? ");
            mat = sc.nextInt();
        } catch (InputMismatchException ex) {
            System.out.println("\n잘못 입력하셨습니다... 다시 시도하세요!\n");
            sc.nextLine();
            return;
        }

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
