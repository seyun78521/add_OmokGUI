import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Board extends JPanel{//창 꾸미기
    final int space=40;//여백
    final int size=50;//칸 크기
    final int stone=40;//돌 크기

    int guidex=-1, guidey=-1;//가이드스톤 좌표
    int[][] board=new int[15][15];//바둑판 좌표
    int turn=1;//홀수면 흑돌, 짝수면 백돌

    public Board(){
            setBackground(new Color(198, 198, 198));//연한 회색
            setPreferredSize(new Dimension(780, 780));//창 사이즈

            addMouseListener(new MouseAdapter() {//마우스 클릭 감지
                @Override
                public void mousePressed(MouseEvent e) {//마우스 클릭 좌표
                    int x = e.getX();
                    int y = e.getY();
                    System.out.println("Mouse pressed at: (" + x + ", " + y + ")");
                }
            });
            addMouseMotionListener(new MouseMotionAdapter() {//실시간 마우스 움직임
                @Override
                public void mouseMoved(MouseEvent e) {//마우스 움직임 좌표
                    int mx = e.getX();
                    int my = e.getY();
                    
                    //마우스 좌표를 바둑판 좌표로 변환 ai가 작성함 이해 아직x
                    int xLoca=(mx-space+size/2)/size;
                    int yLoca=(my-space+size/2)/size;

                    if (xLoca >= 0 && xLoca <= 14 && yLoca >= 0 && yLoca <= 14) {//보드 밖 예외처리
                        guidex = xLoca;
                        guidey = yLoca;
                    } 
                    else {
                        guidex = -1; // 밖으로 나가면 안 보이게 값 초기화
                        guidey = -1;
                    }

                    repaint();//새로고침
                }
            });
        }

        @Override
        public void paintComponent(Graphics g){//선 그리기
            super.paintComponent(g);

            Graphics2D g2d=(Graphics2D)g;//선을 두께 지정하기위한 펜
            g2d.setStroke(new BasicStroke((float)1)); // 선의 두께 설정
            g2d.setColor(Color.BLACK);

            for(int i=0;i<15;i++){
                //drawLine 함수에서 (a,b,c,d)일때 a,b는 시작점 x,y좌표, c,d는 끝점 x,y좌표
                g2d.drawLine(space,space+i*size,space+14*size,space+i*size);//가로줄
                g2d.drawLine(space+i*size,space,space+i*size,space+14*size);//세로줄
            }
            //가이드 스톤 그리기
            if (guidex != -1 && guidey != -1) {
                g2d.setColor(new Color(0,0,0,50));//반투명 검정색
                //좌표 계산
                int cenx=space+guidex*size;
                int ceny=space+guidey*size;
                
                g2d.fillOval(cenx-stone/2, ceny-stone/2, stone, stone);//돌 그리는 식 ai가 작성함 이해 아직x
            }
        }
    }

public class OmokGUI{//오목 창 띄우기
    public static void main(String[]args){
        JFrame frame=new JFrame("Omok");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//창 닫으면 프로그램 종료

        Board panel=new Board();
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}