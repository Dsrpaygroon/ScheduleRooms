import java.util.Scanner;
import java.util.Random;
public class RoomLists
{
    public static void main(int schools){
        String rooms = "";
        String codes = "";
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int total, schoolTotal, roomNum;
        total = schoolTotal = roomNum = 0;
        Scanner key = new Scanner(System.in);
        
        for (int i = 0; i < schools; i++){         //Gathers all school codes from user
            System.out.printf("How many entries for school %d?\n", i + 1);
            schoolTotal = key.nextInt();
            total += schoolTotal;
            if (schoolTotal > 0){
                for (int c = 0; c < schoolTotal; c++){
                    codes += (i + 1) + alphabet.substring(c, c+1) + "|";
                }
            }
            System.out.println(codes);
            System.out.println(total);
        }
        
        roomNum = total / 6;
        for (int i = 0; i < roomNum; i++){
            int codeNum = total / roomNum;
            for (int c = 0; i < codeNum; c++){
            }
        }
    }
}