package com.xgh.test.redis.lock;

import redis.clients.jedis.*;

import java.util.HashMap;
import java.util.Map;

public class RedisWithReentrantLock {

    private ThreadLocal<Map<String, Integer>> lockers = new ThreadLocal<>();

    private Jedis jedis;

    public RedisWithReentrantLock(Jedis jedis) {
        this.jedis = jedis;
    }

    private boolean _lock(String key) {
        return jedis.set(key, "", "nx", "ex", 5L) != null;
    }

    private void _unlock(String key) {
        jedis.del(key);
    }


    private Map<String, Integer> currentLockers() {
        Map<String, Integer> refs = lockers.get();
        if (refs != null) {
            return refs;
        }
        lockers.set(new HashMap<>());
        return lockers.get();
    }

    public boolean lock(String key) {
        Map<String, Integer> refs = currentLockers();
        Integer refCnt = refs.get(key);
        if (refCnt != null) {
            refs.put(key, refCnt + 1);
            return true;
        }
        boolean ok = this._lock(key);
        if (!ok) {
            return false;
        }
        refs.put(key, 1);
        return true;
    }


    public boolean unlock(String key) {
        Map<String, Integer> refs = currentLockers();

        Integer refCnt = refs.get(key);
        if (refCnt == null) {
            return false;
        }
        refCnt -= 1;
        if (refCnt > 0) {
            refs.put(key, refCnt);
        } else {
            refs.remove(key);
            this._unlock(key);
        }
        return true;
    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.220.131", 6379);
        jedis.auth("123456");
        RedisWithReentrantLock redisWithReentrantLock = new RedisWithReentrantLock(jedis);

        System.out.println(redisWithReentrantLock.lock("books1"));
        System.out.println(redisWithReentrantLock.lock("books1"));
        System.out.println(redisWithReentrantLock.unlock("books1"));
        System.out.println(redisWithReentrantLock.unlock("books1"));


    }


}
