import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
public class RoomLists
{
    public static void main(int schools){
        ArrayList<ArrayList<String>> rooms = new ArrayList<ArrayList<String>>(0);
        ArrayList<String> codes = new ArrayList<String>(0);
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int total, schoolTotal, roomNum, codeNum;
        total = schoolTotal = roomNum = codeNum = 0;
        Scanner key = new Scanner(System.in);
        for (int i = 0; i < schools; i++){         //Gathers all school codes from user
            System.out.printf("How many entries for school %d?\n", i + 1);
            schoolTotal = key.nextInt();
            total += schoolTotal;
            if (schoolTotal > 0){
                for (int c = 0; c < schoolTotal; c++){
                    codes.add((i + 1) + alphabet.substring(c, c+1));
                }
            }
            System.out.println(codes);
            System.out.println(total);
        }
        roomNum = total / 6;
        int overflow = total % 6;
        for (int i = 0; i < roomNum; i++){
            if (overflow > 0){
                codeNum = 7;
                overflow --;
            }
            else {
                codeNum = 6;

            }
            int localRoomTotal = 0;
            rooms.add(new ArrayList<String>(0));
            for (int c = 0; c < codeNum; c++){
                localRoomTotal ++;
                rooms.get(i).add(0,"TBP");                                 
            }
            System.out.println(localRoomTotal);
        }
        System.out.println(rooms);
        // Sorting algarithm for round 1 sorting
        ArrayList<String> round1 = codes;
        System.out.println(sort1(codes, rooms));
    }

    public static ArrayList<String> sort1(ArrayList<String> codes, ArrayList<ArrayList<String>> rooms){
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        Collections.shuffle(codes);
        ArrayList<String> codesToSort = codes;
        ArrayList<String> sortedCodes = new ArrayList<String>(0);
        int i = 0;
        while (sortedCodes.size() < codes.size()){
            System.out.println(alphabet.substring(i,i+1));
            for (int x = 0; x < codesToSort.size(); x++){
                System.out.print(codes.get(x) + "... ");
                if (codes.get(x).indexOf(alphabet.substring(i, i+1)) >= 0){
                    sortedCodes.add(codes.get(x));
                }
            }
            i++;
        }
        codes = sortedCodes;
        for (String code : codes){
            String schoolNum = code.substring(0,1);
            String value = code.substring(1);
            int x = 0;
        }
        return codes;
    }
}