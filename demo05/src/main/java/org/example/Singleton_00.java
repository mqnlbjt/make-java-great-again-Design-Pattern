package org.example;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Singleton_00 {

    public static Map<String,String> cache = new ConcurrentHashMap<String, String>();

}
