package com.redisbloom.redis;

import com.redisbloom.util.PropertyUtil;
import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.Set;

/**
 * 操作Redis的工具类
 * @author wangxu
 *         blog:http://www.cnblogs.com/wxisme/
 *         email:wxu1994@163.com
 * @date 2016/8/4
 */
public class RedisUtils {
    private static JedisPool pool = null;
    private static JedisCluster jedisCluster = null;
    static {
        initRedis();
    }
    private static void initRedis(){
        String ip = PropertyUtil.getValue("redis.ip");
        Integer port = Integer.parseInt(PropertyUtil.getValue("redis.port","6379"));
        String pass = PropertyUtil.getValue("redis.pass");

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(8);
        config.setMaxIdle(8);
        config.setMinIdle(8);
        //获取连接时的最大等待毫秒数
        config.setMaxWaitMillis(10*1000);
        config.setTestOnBorrow(false);
        config.setTestOnReturn(false);
        config.setTimeBetweenEvictionRunsMillis(30*1000);
        config.setNumTestsPerEvictionRun(10);
        config.setMinEvictableIdleTimeMillis(60*1000);

        if(pass == null){
            pool = new JedisPool(config, ip, port, 10*1000);
        }else{
            pool = new JedisPool(config, ip, port, 10*1000, pass);
        }

        //jedisCluster = getJedisCluster(config,new HostAndPort(ip, port));
    }

    /*public static JedisCluster getJedisCluster(){
        return jedisCluster;
    }*/

    public static JedisPool getPool(){
        return pool;
    }

    /**
     * 根据传入的主机和端口参数,以及配置参数对象来创建并返回JedisCluster对象
     * 注：因redis2.8占时不支持cluster 设置密码，所以该方法暂时不能用
     * @param hostAndPorts
     * @return JedisCluster
     */
    public static JedisCluster getJedisCluster(JedisPoolConfig config,HostAndPort ... hostAndPorts) {
        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        for (HostAndPort hostAndPort : hostAndPorts) {
            jedisClusterNodes.add(hostAndPort);
        }
        JedisCluster jedisCluster = new JedisCluster(jedisClusterNodes, 10000000,config);
        return jedisCluster;
    }

    /**
     * 根据传入的主机和端口参数来创建并返回JedisCluster对象
     * @param hostAndPorts
     * @return JedisCluster
     */
    public static JedisCluster getJedisCluster(HostAndPort ... hostAndPorts) {
        return getJedisCluster(null,hostAndPorts);
    }

    /**
     * 获取一个Jedis对象
     * @param host
     * @return Jedis
     */
    public static Jedis getJedis(String host) {
        if (host != null && "".equals(host)) {
            Jedis j = new Jedis(host);
            return new Jedis(host);
        }
        return null;
    }

}
