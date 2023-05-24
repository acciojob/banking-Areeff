package com.driver;

import java.util.Arrays;
import java.util.HashMap;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only
    public static double minBalance=5000;
    public int maxValue=Integer.MIN_VALUE;
    HashMap<Character,Integer> map=new HashMap<>();
    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name,balance,minBalance);
        this.tradeLicenseId=tradeLicenseId;
        if(balance<minBalance){
            throw new Exception("Insufficient Balance");
        }
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        if(IsValidLicenseId()){
            return;
        }
        else{
            for(int i=0;i<tradeLicenseId.length();i++){
                map.put(tradeLicenseId.charAt(i), map.getOrDefault(tradeLicenseId.charAt(i),0)+1);
            }
          for(Integer value:map.values()){
              if(maxValue<value){
                  maxValue=value;
              }
          }
          if(tradeLicenseId.length()%2==0){
              if(maxValue>tradeLicenseId.length()/2){
                  throw new Exception("Valid License can not be generated");
              }
          }
          else{
              if(maxValue>(tradeLicenseId.length()+1)/2){
                  throw new Exception("Valid License can not be generated");
              }
          }
        }
        generatevalidLicenseId();
    }

    private void generatevalidLicenseId() {
        char[] array =tradeLicenseId.toCharArray();
        Arrays.sort(array);
        int ans[]=new int[tradeLicenseId.length()];
        for(int i=0;i<tradeLicenseId.length();i=i+2){
            ans[i]=array[i];
        }
        for(int i=1;i<tradeLicenseId.length();i=i+2) {
            ans[i] = array[i];
        }
       String finalId="";
        for(int i=0;i<ans.length;i++){
            finalId+=ans[i];
        }
        tradeLicenseId=finalId;
    }

    private boolean IsValidLicenseId() {
        for (int i=0;i<tradeLicenseId.length()-1;i++){
            if(tradeLicenseId.charAt(i)==tradeLicenseId.charAt(i+1)){
                return false;
            }
        }
        return true;
    }

}
