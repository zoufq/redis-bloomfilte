package com.redisbloom.redis;

/**
 * 自定义BitSet
 * @author wangxu
 *         blog:http://www.cnblogs.com/wxisme/
 *         email:wxu1994@163.com
 * @date 2016/8/4
 */
 public interface RedisBitSet {
     void set(int bitIndex);
     void set(int bitIndex, boolean value);
     boolean get(int bitIndex);
     void clear(int bitIndex);
     void clear();
     long size();
     boolean isEmpty();
}
