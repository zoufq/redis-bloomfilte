package com.redisbloom.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 自定义BitSet
 * @author wangxu
 *         blog:http://www.cnblogs.com/wxisme/
 *         email:wxu1994@163.com
 * @date 2016/8/4
 */
public class RedisBitSetNotCluster implements RedisBitSet{

    private JedisPool pool;
    private String name;

    public RedisBitSetNotCluster() {}

    public RedisBitSetNotCluster(JedisPool pool, String name) {
        this.pool = pool;
        this.name = name;
    }

    public void set(int bitIndex) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.setbit(this.name, bitIndex, true);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    public void set(int bitIndex, boolean value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.setbit(this.name, bitIndex, value);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    public boolean get(int bitIndex) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.getbit(this.name, bitIndex);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(jedis!=null)
                jedis.close();
        }
        return false;
    }

    public void clear(int bitIndex) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.setbit(this.name, bitIndex, false);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    public void clear() {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.del(this.name);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    public long size() {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.bitcount(this.name);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(jedis!=null)
                jedis.close();
        }
        return 0;
    }

    public boolean isEmpty() {
        return size()<=0;
    }
}
