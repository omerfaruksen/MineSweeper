import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
public  class MineSweeper {
    String[][] map;//Mayınların bulunduğu harita
    String[][] board;//Oyuncunun gördüğü oyun alanı
    int nRow;
    int nCol;
    boolean game=true;
    Scanner info=new Scanner(System.in);
    ArrayList<String> selectedCoordinates = new ArrayList<>();

    MineSweeper(String map, String board, int nRow, int nCol){
        this.nCol=nCol;
        this.nRow=nRow;
        this.map=new String[nRow][nCol];
        this.board=new String[nRow][nCol];
    }

    public void inGame(){//Oyunun başladığı nokta
        System.out.println("Mayınlı Harita");
        for (String[] row: map){
            Arrays.fill(row,"-");
        }
        //Değerlendirme Formu 8: Diziye rastgele mayın ekleme
        int randomR, randomC;
        Random randomNumber=new Random();
        int size=(nRow*nCol)/4;
        int sayac=0;
        while (sayac!=size){

            randomR=randomNumber.nextInt(nRow);
            randomC=randomNumber.nextInt(nCol);

            if (map[randomR][randomC]==("-")){
                map[randomR][randomC]="*";
                sayac++;
            }

        }
        for (String [] row:map){
            for (String col:row){
                System.out.print(col+" ");
            }
            System.out.println();
        }
        for (String[] row: board){
            Arrays.fill(row,"-");
        }

        System.out.println("Oyun Alanı");
        int boardRow, boardCol,success=0;
        while (game){

            printScreen(board);

            //Değerlendirme Formu 9 : Kullanıcıdan işaretlemek istediği noktanın verileri alınıyor.
            System.out.print("Satır giriniz : ");
            boardRow= info.nextInt();
            System.out.print("Sütun giriniz : ");
            boardCol= info.nextInt();
            System.out.println("=====================");
            //Girilen koordinatın daha önce girilip girilmediğinin kontrolü yapılıyor eğer daha önce girildiyse,
            // kullanıcıdan yeni koordinat isteniyor.
            String coordinate = boardRow + "-" + boardCol;
            if (selectedCoordinates.contains(coordinate)) {
                System.out.println("Bu koordinatı daha önce seçtiniz. Lütfen başka bir koordinat seçin.");
                continue;
            } else {
                selectedCoordinates.add(coordinate);
            }
            //Değerlendirme Formu 10 : Kullanıcının seçtiği noktanın dizi içerisinde olup olmadığı kontrol ediliyor.
            //Dizi içerisinde değilse ekrana mesaj yazdırılıyor.
            if (boardRow<0||boardRow>nRow||boardCol<0||boardCol>nCol){
                System.out.println("Geçersiz koordinat girdiniz !");
                continue;
            }
            //Değerlendirme Formu 14: Oyuncunun kazanması şartının sağlandığı kısım.(Girilen koordinatlar tekrar girilemiyor.)
            if (map[boardRow][boardCol]!="*"){
                check(boardRow,boardCol);
                success++;
                if (success==(nRow*nCol-(size))){
                    System.out.println("Tebrikler Kazandınız!");//Değerlendirme Formu 15 :Oyuncunun kazanması durumunda karşılaşacağı mesaj
                    break;
                }
            //Değerlendirme Formu 13: Oyuncunun oyunu kaybetmesinin kontrolünün yapıldığı kısım.
            }else {
                game=false;
                System.out.println("Kaybettiniz!");//Değerlendirme Formu 15 : Oyuncunun kaybetmesi durumunda karşılacağı mesaj
            }
        }

    }
    //Değerlendirme Formu 12 :Kullanıcının seçtiği noktanın etrafındaki mayın kontrolünün sağlandığı kısım.
    public void check(int row,int col){
        int mayinsayisi=0;
        if (this.map[row][col]=="-"){
            if ((row>0 && col>0) && (this.map[row-1][col-1].equals("*"))){
                mayinsayisi++;
            }
            if((col>0)&&(this.map[row][col-1].equals("*"))){
                mayinsayisi++;
            }
            if ((row<nRow-1 && col>0)&&(this.map[row+1][col-1].equals("*"))){
                mayinsayisi++;
            }
            if ((row>0)&&(this.map[row-1][col].equals("*"))){
                mayinsayisi++;
            }
            if ((row<nRow-1 && col<nCol-1 )&&(this.map[row+1][col+1].equals("*"))){
                mayinsayisi++;
            }
            if ((col<nCol-1)&&(this.map[row][col+1].equals("*"))){
                mayinsayisi++;
            }
            if ((row>0 && col<nCol-1 ) && (this.map[row-1][col+1].equals("*"))){
                mayinsayisi++;
            }
            if ((row<nRow-1)&&(this.map[row+1][col].equals("*"))){
                mayinsayisi++;
            }

            this.board[row][col]=String.valueOf(mayinsayisi);

        }


    }
    public void printScreen(String[][]arr){//Haritanın ekrana basılması için çağırılan metot
        for (int i=0; i< arr.length; i++ ){
            for (int j=0; j< arr[0].length; j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }
}