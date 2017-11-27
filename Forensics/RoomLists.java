import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
public class RoomLists{
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

    public static ArrayList<ArrayList<String>> sort1(ArrayList<String> codes, ArrayList<ArrayList<String>> rooms, ArrayList<ArrayList<Integer>> roomPreference){
        Scanner key = new Scanner(System.in);
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        Collections.shuffle(codes);
        ArrayList<String> codesToSort = codes;
        ArrayList<String> sortedCodes = new ArrayList<String>(0);
        int q = 0;
        while (sortedCodes.size() < codes.size()){
            for (int x = 0; x < codesToSort.size(); x++){
                if (codes.get(x).indexOf(alphabet.substring(q, q+1)) >= 0){
                    sortedCodes.add(codes.get(x));
                }
            }
            q++;
        }
        codes = sortedCodes;
        System.out.print("Codes: ");
        System.out.println(codes);
        for (String code : codes){
            String schoolNum = code.substring(0,code.length()-1);
            int[] roomList = new int[roomPreference.size()];
            for (int r = 0; r < roomPreference.size(); r++){
                for (int j = 0; j < roomPreference.get(r).size(); j++){
                    if(((ArrayList<String>)rooms.get(r)).get(j).equals("TBP"))
                        roomList[r] = roomList[r] + 1;
                }
            }
            int max = -1;
            for (int j = 0; j < roomList.length; j++){
                if (roomList[j] > max)
                    max = roomList[j];}
            for (int j = 0; j < roomList.length; j++){
                if (roomList[j] != max)
                    roomList[j] = 1;
                else
                    roomList[j] = 0;
            }
            for (int r = 0; r < roomPreference.size(); r++){
                int roomRating = roomList[r];
                for (int j = 0; j < roomPreference.get(r).size(); j++){
                    if (((ArrayList<String>)rooms.get(r)).get(j).substring(0,((ArrayList<String>)rooms.get(r)).get(j).length()-1).equals(schoolNum)){
                        roomRating += 2;
                    }
                }
                for (int j = 0; j < roomPreference.get(r).size(); j++){
                    int placeRating = roomRating;
                    if (!((ArrayList<String>)rooms.get(r)).get(j).equals("TBP")){
                        placeRating += 10;
                    }
                    ((ArrayList<Integer>)roomPreference.get(r)).set(j, placeRating);
                }
            }
            ArrayList<Integer> bestX = new ArrayList<Integer>(0);
            ArrayList<Integer> bestY = new ArrayList<Integer>(0);
            int rating = 0;
            for (int j = 0; bestX.size() == 0; j++){
                for (int r = 0; r < roomPreference.size(); r++){
                    for (int c = 0; c < roomPreference.get(r).size(); c++){
                        if (((ArrayList<Integer>)roomPreference.get(r)).get(c) == j){
                            bestX.add(r);
                            bestY.add(c);
                        }
                    }
                }
                rating = j;
            }
            if (rating < 2){
                Random random = new Random();
                int pick = random.nextInt(bestX.size());
                System.out.printf("%s, placed in room %d at place %d with rating %d\n", code, bestX.get(pick), bestY.get(pick), rating);
                ((ArrayList<String>)rooms.get(bestX.get(pick))).set(bestY.get(pick), code);
                for (ArrayList<String> room : rooms){
                    System.out.println(room);
                }
                System.out.println();
                for (ArrayList<Integer> preference : roomPreference){
                    System.out.println(preference);
                }
                key.next();
                System.out.println();
            }
            else{
                String letter = code.substring(code.length() - 1);
                for (int i = 0; i < roomPreference.size(); i++){
                    for (int j = 0; j < roomPreference.get(i).size(); j++){
                        ((ArrayList<Integer>)roomPreference.get(i)).set(j, letter.compareTo(rooms.get(i).get(j).substring(rooms.get(i).get(j).length() - 1)));
                        if ((((ArrayList<String>)rooms.get(i)).get(j).substring(0,((ArrayList<String>)rooms.get(i)).get(j).length()-1).equals(schoolNum)))
                            ((ArrayList<Integer>)roomPreference.get(i)).set(j, ((ArrayList<Integer>)roomPreference.get(i)).get(j) + 10);
                    }
                }
                boolean done = false;
                int p = 0;
                int x = -5;
                int y = -5;
                while (done == false){
                    for (int i = 0; i < roomPreference.size(); i++){
                        for (int j = 0; j < roomPreference.get(i).size(); j++){
                            if (((ArrayList<Integer>)roomPreference.get(i)).get(j) == p){
                                done = true;
                                x = i;
                                y = j;
                            }
                        }
                    }
                }
                codes.add(((ArrayList<String>)rooms.get(x)).get(y));
                ((ArrayList<String>)rooms.get(x)).set(y, code);
                System.out.printf("%s, placed in room %d at place %d\n", code, x, y);
                ((ArrayList<String>)rooms.get(x)).set(y, code);
                for (ArrayList<String> room : rooms){
                    System.out.println(room);
                }
                System.out.println();
                for (ArrayList<Integer> preference : roomPreference){
                    System.out.println(preference);
                }
                key.next();
                System.out.println();
            }
        }
        return rooms;
    }
}