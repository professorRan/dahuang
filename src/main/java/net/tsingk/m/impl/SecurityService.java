package net.tsingk.m.impl;

import net.tsingk.m.ISecurityService;
import net.tsingk.m.SignException;
import org.springframework.stereotype.Service;

@Service
public class SecurityService implements ISecurityService {


    @Override
    public String generateToken(byte[] data) throws SignException {
        return null;
    }

    @Override
    public boolean verifyToken(String token) throws SignException {
        return false;
    }

    @Override
    public String signData(byte[] data) throws SignException {
        return null;
    }

    @Override
    public String verifySignature(byte[] data) throws SignException {
        return null;
    }
}
