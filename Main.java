import java.util.Scanner;

public class Main{

public static void main(String[] args){
    Scanner veri =new Scanner(System.in);
    //Değerlendirme Formu 7: Dizi(matris) kullanıcı tarafından belirlerniyor.
    System.out.print("Satır giriniz : ");
    int row=veri.nextInt();
    if (row<=0){
        System.out.println("Hatalı giriş yaptınız! Lütfen sıfırdan büyük bir sayı giriniz.");
    }
    System.out.print("Sütun Giriniz : ");
    int col=veri.nextInt();

    MineSweeper mine=new MineSweeper("map","board",row,col);
    mine.inGame();
}
}