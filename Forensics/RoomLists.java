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
        ArrayList<ArrayList<String>> emptyRooms = rooms;
        System.out.println();
        System.out.println();
        System.out.println();
        ArrayList<ArrayList<String>> round1 = sort1(codes, rooms, roomPreference);
        System.out.println(round1);
        System.out.println("Lets see if round 2 is working now:");
        ArrayList<ArrayList<String>> round2 = sort2(roomPreference, round1, emptyRooms);
        System.out.println(round2);
    }

    public static ArrayList<ArrayList<String>> sort1(ArrayList<String> codes, ArrayList<ArrayList<String>> rooms, ArrayList<ArrayList<Integer>> roomPreference){
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
        ArrayList<String> reSort = new ArrayList<String>(sortedCodes.size());
        System.out.print("Codes: ");
        System.out.println(codes);
        ArrayList<int[]> pastPlacements = new ArrayList<int[]>(0);
        int[] bla = new int[]{-1,-1};
        pastPlacements.add(bla);
        for (String code : codes){
            sort(code, rooms, roomPreference, reSort, pastPlacements);
            reSort = rooms.get(rooms.size()-1);
            rooms.remove(rooms.size()-1);
            while (reSort.size() > 0){
                sort(reSort.get(0), rooms, roomPreference, reSort, pastPlacements);
                reSort = rooms.get(rooms.size()-1);
                int[] placement = new int[2];
                for (int i = 0; i < rooms.size(); i++){
                    if (rooms.get(i).indexOf(reSort.get(0)) > -1){
                        placement[0] = i;
                        placement[1] = rooms.get(i).indexOf(reSort.get(0)); 
                    }
                }
                reSort.remove(0);
                rooms.remove(rooms.size()-1);
            }
            pastPlacements = new ArrayList<int[]>(0);
            pastPlacements.add(bla);
        }
        return rooms;
    }

    public static ArrayList<ArrayList<String>> sort2(ArrayList<ArrayList<Integer>> roomPreference, ArrayList<ArrayList<String>> round1, ArrayList<ArrayList<String>> round2){
        // Step one, sort first and last codes into, not the first and the last
        ArrayList<String> codes = new ArrayList<String>(0);
        for (int i = 0; i < round1.size(); i++){
            for (int j = 0; j < round1.get(i).size(); j++){
                if (j == 0 || j == round1.get(i).size()-1)
                    codes.add(round1.get(i).get(j));
            }
        }
        for (String code : codes){
            int room = codes.indexOf(code)/2;
            int[] roomList = new int[roomPreference.size()];
            for (int r = 0; r < roomPreference.size(); r++){
                for (int j = 0; j < roomPreference.get(r).size(); j++){
                    if(((ArrayList<String>)round2.get(r)).get(j).equals("TBP"))
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
        }
        // Step two, sort the middle ones into, the rest

        return round2;
    }

    public static void sort(String code, ArrayList<ArrayList<String>> rooms, ArrayList<ArrayList<Integer>> roomPreference, ArrayList<String> reSort, ArrayList<int[]> pastPlacements){
        Scanner key = new Scanner(System.in); 
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
                        if (((ArrayList<Integer>)roomPreference.get(i)).get(j) == p && i != pastPlacements.get(0)[0] && j != pastPlacements.get(0)[1]){
                            done = true;
                            x = i;
                            y = j;
                        }
                    }
                }
                p++;
            }
            reSort.add(((ArrayList<String>)rooms.get(x)).get(y));
            System.out.printf("Adding %s to list to place", ((ArrayList<String>)rooms.get(x)).get(y));
            ((ArrayList<String>)rooms.get(x)).set(y, code);
            System.out.printf("\n%s, placed in room %d at place %d with rating %d\n", code, x, y, p);
            ((ArrayList<String>)rooms.get(x)).set(y, code);
        }
        rooms.add(reSort);
    }

}