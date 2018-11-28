package iot;

import redis.clients.jedis.Jedis;

public class Tt {
 public static void main(String[] args) {
	Jedis jedis=new Jedis("192.168.1.103", 6379);
	System.out.println(jedis.get("q"));
	
}
}
