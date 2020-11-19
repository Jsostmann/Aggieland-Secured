package com.aggieland.auth;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class AuthorizationDAOTest {

    String[] testCases = {"Apple","apple","aPPle","  apple","apple  ",""};

    String[] expectedResults = {
            "393374ca4948ba5d30d24c45611a17a7f63ac98904dd17da4a8cdab2854a611daba24786a252d2d476a446cb476a5332bafb41b1409591683b6a5ce7680ba9e2",
            "844d8779103b94c18f4aa4cc0c3b4474058580a991fba85d3ca698a0bc9e52c5940feb7a65a3a290e17e6b23ee943ecc4f73e7490327245b4fe5d5efb590feb2",
            "f45fbf8109e673efa8718e25ceb4aa96dbae0e9fe31bed2099e1ac5f33ede5976a91ea4d875c834ce0d370c949df72d49798a5c069fae79b95fff62cb37e4c5f",
            "efc73cb6346de6393c0efeaa4a7e8232ebc29d020918bbfb201c2d4bdc1f2ee667d212250f6213b5db1236345eeb0d0ee678011030c9cf6c878b68ced2e0426c",
            "353e16ec3ad3c5389f75e26dec96b32d38244fc9612852105ec2c2019f8c7905d7146c6be39e56e289afade01da38d601f40387b1309b46c6b2a58393bd899e9",
            "cf83e1357eefb8bdf1542850d66d8007d620e4050b5715dc83f4a921d36ce9ce47d0d13c5d85f2b0ff8318d2877eec2f63b931bd47417a81a538327af927da3e"
            };

    @Test
    public void testHash() {
        for(int i = 0; i < testCases.length; i++) {
           assertEquals("Error at testHast() test case: " + i, expectedResults[i], AuthorizationDAO.hashEncrypt(testCases[i]));
        }
    }

    @Test
    public void testVerifyHash() {
        for(int i = 0; i < testCases.length; i++) {
            assertTrue("Error at testVerifyHash() test case: " + i, AuthorizationDAO.checkHashEncrypt(testCases[i],expectedResults[i]));
        }
    }
}
