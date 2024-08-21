package com.hsbc.dao;

public interface CredentialDao {
    // set credentials on sign-up
    public void add(String username, String password);
    // verify credentials on sign-in
    public void verify(String username, String password);
}
