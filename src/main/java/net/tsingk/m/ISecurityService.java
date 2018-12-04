package net.tsingk.m;

public interface ISecurityService {

    public String generateToken(byte[] data) throws SignException;


    public boolean verifyToken(String token) throws SignException;


    public String signData(byte[] data) throws SignException;

    public String verifySignature(byte[] data) throws SignException;
}
