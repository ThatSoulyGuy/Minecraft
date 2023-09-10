package com.thatsoulzguy.minecraft.util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;

public class Hash
{
    private static String ConvertToHex(final byte[] messageDigest)
    {
        BigInteger count = new BigInteger(1, messageDigest);
        String out = count.toString(16);

        while (out.length() < 32)
            out = "0".concat(out);

        return out;
    }

    public static String GenerateMD5Hash(final String input)
    {
        MessageDigest messageDigest;

        try
        {
            messageDigest = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException e)
        {
            throw new RuntimeException(e);
        }


        return ConvertToHex(messageDigest.digest(input.getBytes()));
    }

    public static String GenerateSHAHash(final String input) throws NoSuchAlgorithmException
    {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

        return ConvertToHex(messageDigest.digest(input.getBytes(StandardCharsets.UTF_8)));
    }

    public static String GeneratePasswordHashWithSalt(final String text)
    {
        try
        {
            return GenerateSaltedHash(text, GenerateSalt());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return "";
    }

    private static String GenerateSaltedHash(final String textToHash, final byte[] salt) throws NoSuchAlgorithmException
    {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(salt);

        return ConvertToHex(messageDigest.digest(textToHash.getBytes()));
    }


    public static String GeneratePasswordHash(final String password) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException
    {
        int iterations = 1000;
        byte[] salt = GenerateSalt();

        byte[] hash = GeneratePBEHash(password, iterations, salt, 64);

        return iterations + ":" + ConvertToHex(salt) + ":" + ConvertToHex(hash);
    }

    private static byte[] GenerateSalt() throws NoSuchAlgorithmException, NoSuchProviderException{

        SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");

        byte[] salt = new byte[16];

        random.nextBytes(salt);

        return salt;
    }

    private static boolean ValidatePassword(final String originalPassword, final String storedPasswordHash) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        String[] parts = storedPasswordHash.split(":");

        int iterations = Integer.valueOf(parts[0]);
        byte[] salt = ConvertToBytes(parts[1]);
        byte[] hash = ConvertToBytes(parts[2]);

        byte[] originalPasswordHash = GeneratePBEHash(originalPassword, iterations, salt, hash.length);

        int difference = hash.length ^ originalPasswordHash.length;

        for(int h = 0; h < hash.length && h < originalPasswordHash.length; h++)
            difference |= hash[h] ^ originalPasswordHash[h];

        return difference == 0;
    }

    private static byte[] GeneratePBEHash(final String password, final int iterations, final byte[] salt, final int keyLength) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        PBEKeySpec key = new PBEKeySpec(password.toCharArray(), salt, iterations, keyLength * 8);

        return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(key).getEncoded();
    }

    private static byte[] ConvertToBytes(final String hex)
    {
        byte[] bytes = new byte[hex.length() / 2];

        for(int b = 0; b < bytes.length; b++)
            bytes[b] = Integer.valueOf(hex.substring(2 * b, 2 * b + 2), 16).byteValue();

        return bytes;
    }

    public static String GenerateChecksum(final String filePath)throws IOException, NoSuchAlgorithmException
    {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

        try (DigestInputStream stream = new DigestInputStream(new FileInputStream(filePath), messageDigest))
        {
            while (stream.read() != -1);
            messageDigest = stream.getMessageDigest();
        }

        return ConvertToHex(messageDigest.digest());
    }

    /*
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException, FileNotFoundException, IOException {
        Hash hash = new Hash();

        String hash = hashCreator.createMD5Hash("password123");
        System.out.println(hash + " "+hash.length());

        hash = hashCreator.createSHAHash("password123");
        System.out.println(hash+ " "+hash.length());

        String saltedHash = hashCreator.createPasswordHashWithSalt("password123");
        System.out.println(saltedHash);

        String strongPwdHash = hashCreator.generateStrongPasswordHash("password123");
        System.out.println(strongPwdHash);

        boolean matchResult = hashCreator.validatePassword("password123", strongPwdHash);
        System.out.println("matchResult "+matchResult);

        String checksum = hashCreator.createChecksum("/Users/pratikdas/Downloads/heart.csv");
        System.out.println("checksum "+checksum);
    }
    */
}