package com.example.movie_backend.util;

import java.sql.Timestamp;
import java.util.Random;

public class IdGenerator {
    public static Long generateId() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        String year = timestamp.toString().substring(2, 4);
        String month = timestamp.toString().substring(5, 7);
        String day = timestamp.toString().substring(8, 10);
        String hour = timestamp.toString().substring(11, 13);
        String minute = timestamp.toString().substring(14, 16);
        String second = timestamp.toString().substring(17, 19);
        String millisecond = timestamp.toString().substring(20, 22);

        Random random = new Random();
        String rand = Integer.toString(random.nextInt(100));
//        if (rand.length() < 2) {
//            rand = "00" + rand;
//        } else if (rand.length() < 3) {
//            rand = "0" + rand;
//        }
        if (rand.length() < 2) {
            rand = "0" + rand;
        }
        return Long.parseLong(year + month + day + hour + minute + second + millisecond + rand);
    }
}
