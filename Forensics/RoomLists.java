import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
public class RoomLists
{
    public static void main(int schools){
        ArrayList<ArrayList<String>> rooms = new ArrayList<ArrayList<String>>(0);
        ArrayList<ArrayList<Integer>> roomPreference = new ArrayList<ArrayList<Integer>>(0);
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
            roomPreference.add(new ArrayList<Integer>(0));
            for (int c = 0; c < codeNum; c++){
                localRoomTotal ++;
                rooms.get(i).add("TBP");
                roomPreference.get(i).add(0);
            }
            System.out.println(localRoomTotal);
        }
        System.out.println(rooms);
        // Sorting algarithm for round 1 sorting
        ArrayList<String> round1 = codes;
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(sort1(codes, rooms, roomPreference));
    }

    public static ArrayList<ArrayList<Integer>> sort1(ArrayList<String> codes, ArrayList<ArrayList<String>> rooms, ArrayList<ArrayList<Integer>> roomPreference){
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        Collections.shuffle(codes);
        ArrayList<String> codesToSort = codes;
        ArrayList<String> sortedCodes = new ArrayList<String>(0);
        int i = 0;
        while (sortedCodes.size() < codes.size()){
            for (int x = 0; x < codesToSort.size(); x++){
                if (codes.get(x).indexOf(alphabet.substring(i, i+1)) >= 0){
                    sortedCodes.add(codes.get(x));
                }
            }
            i++;
        }
        codes = sortedCodes;
        System.out.print("Codes: ");
        System.out.println(codes);
        for (String code : codes){
            String schoolNum = code.substring(0,1);
            int[] roomList = new int[roomPreference.size()];
            for (int r = 0; r < roomPreference.size(); r++){
                for (int j = 0; j < roomPreference.get(r).size(); j++){
                if(((ArrayList<String>)rooms.get(r)).get(j).equals("TBP"))
                roomList[r] = roomList[r] + 1;
                }
                System.out.println(roomList[r]);
            }
            for (int r = 0; r < roomPreference.size(); r++){
                int roomRating = roomList[r];
                if (roomPreference.get(r).indexOf(schoolNum) != -1){
                    roomRating += 2;
                }
                for (int j = 0; j < roomPreference.get(r).size(); j++){
                    int placeRating = roomRating;
                    if (!((ArrayList<String>)rooms.get(r)).get(j).equals("TBP")){
                        placeRating += 10;
                    }
                    ((ArrayList<Integer>)roomPreference.get(r)).set(j, placeRating);
                }
            }
        }
        return roomPreference;
    }
}