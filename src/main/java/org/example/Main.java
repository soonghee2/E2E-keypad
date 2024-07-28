package org.example;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Map<Integer, String> hashes = generateHashes();
        for (Map.Entry<Integer, String> entry : hashes.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    private static Map<Integer, String> generateHashes() {
        Map<Integer, String> hashes = new HashMap<>();
        Random random = new Random();
        for (int i = 0; i <= 9; i++) {
            String hash = generateHash(String.valueOf(random.nextInt(10000)));
            hashes.put(i, hash);
        }
        return hashes;
    }

    private static String generateHash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] result = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : result) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
