package com.lhf.springboot.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.Collections;

/**
 * @ClassName: RedisTool2
 * @Author: liuhefei
 * @Description: Redis实现分布式锁
 * @Date: 2020/6/9 17:57
 */
public class RedisTool2 {

    private static Jedis jedis = new Jedis("127.0.0.1",6379);

    private static final String LOCK_SUCCESS = "OK";

    private static final String SET_IF_NOT_EXIST = "NX";

    private static final String SET_WITH_EXPIRE_TIME = "PX";

    private static final Long RELEASE_SUCCESS = 1L;


    /**
     * EX seconds ： 将键的过期时间设置为 seconds 秒。 执行 SET key value EX seconds 的效果等同于执行 SETEX key seconds value 。
     *
     * PX milliseconds ： 将键的过期时间设置为 milliseconds 毫秒。 执行 SET key value PX milliseconds 的效果等同于执行 PSETEX key milliseconds value 。
     *
     * NX ： 只在键不存在时， 才对键进行设置操作。 执行 SET key value NX 的效果等同于执行 SETNX key value 。
     *
     * XX ： 只在键已经存在时， 才对键进行设置操作。
     */

    /**
 　　* 尝试获取分布式锁
 　　* @param lockKey 锁
 　　* @param requestId 请求标识
 　　* @param expireTime 超期时间(过期时间) 需要根据实际的业务场景确定
 　　* @return 是否获取成功
 　　*/
    public static boolean tryGetDistributedLock(String lockKey, String requestId, int expireTime) {
        SetParams params = new SetParams();
        String result = jedis.set(lockKey, requestId, params.nx().ex(expireTime));
        if (LOCK_SUCCESS.equals(result)) {
             return true;
        }
        return false;
    }


    /**
     * 尝试获取分布式锁
     * @param lockKey 锁
     * @param requestId 请求标识
     * @param expireTime 超期时间（过期时间）需要根据实际的业务场景确定
     * @return 是否获取成功
     */
    public static boolean tryGetDistributedLock1(String lockKey, String requestId, int expireTime){
        //只在键 key 不存在的情况下， 将键 key 的值设置为 value 。若键 key 已经存在， 则 SETNX 命令不做任何动作。设置成功返回1，失败返回0
        long code = jedis.setnx(lockKey, requestId);   //保证加锁的原子操作
        //通过timeOut设置过期时间保证不会出现死锁【避免死锁】
        jedis.expire(lockKey, expireTime);   //设置键的过期时间
        if(code == 1){
            return true;
        }
        return false;
    }

    /**
     * 解锁操作
     * @param key 锁标识
     * @param value 客户端标识
     * @return
     */

    public static Boolean unLock(String key,String value){

        //luaScript 这个字符串是个lua脚本，代表的意思是如果根据key拿到的value跟传入的value相同就执行del，否则就返回0【保证安全性】
        String luaScript = "if redis.call(\"get\",KEYS[1]) == ARGV[1] then return redis.call(\"del\",KEYS[1]) else  return 0 end";

        //jedis.eval(String,list,list);这个命令就是去执行lua脚本，KEYS的集合就是第二个参数，ARGV的集合就是第三参数【保证解锁的原子操作】
        Object var2 = jedis.eval(luaScript, Collections.singletonList(key), Collections.singletonList(value));

        if (RELEASE_SUCCESS == var2) {
            return true;
        }
        return false;
    }

    /**
     * 解锁操作
     * @param key  锁标识
     * @param value  客户端标识
     * @return
     */
    public static Boolean unLock1(String key, String value){
        //key就是redis的key值作为锁的标识，value在这里作为客户端的标识，只有key-value都比配才有删除锁的权利【保证安全性】
        String oldValue = jedis.get(key);
        long delCount = 0;  //被删除的key的数量
        if(oldValue.equals(value)){
            delCount = jedis.del(key);
        }
        if(delCount > 0){  //被删除的key的数量大于0，表示删除成功
            return true;
        }
        return false;
    }


    /**
     * 重试机制：
     * 如果在业务中去拿锁如果没有拿到是应该阻塞着一直等待还是直接返回，这个问题其实可以写一个重试机制，
     * 根据重试次数和重试时间做一个循环去拿锁，当然这个重试的次数和时间设多少合适，是需要根据自身业务去衡量的
     * @param key 锁标识
     * @param value 客户端标识
     * @param timeOut 过期时间
     * @param retry 重试次数
     * @param sleepTime 重试间隔时间
     * @return
     */
    public Boolean lockRetry(String key,String value,int timeOut,Integer retry,Long sleepTime){
        Boolean flag = false;
        try {
            for (int i=0;i<retry;i++){
                flag = tryGetDistributedLock(key,value,timeOut);
                if(flag){
                    break;
                }
                Thread.sleep(sleepTime);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

}
