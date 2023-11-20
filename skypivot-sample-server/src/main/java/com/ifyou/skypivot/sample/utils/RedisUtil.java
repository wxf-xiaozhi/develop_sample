package com.ifyou.skypivot.sample.utils;

import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.Codec;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Author
 * @date 2021/8/9 13:59
 */
@Component
@SuppressWarnings("unused")
@Slf4j
public final class RedisUtil {

    @Resource
    private RedissonClient redisson;

    // =============================common============================
    /**
     26
     * 指定缓存失效时间
     * @param key 键
     * @param time 时间(秒)
     * @return boolean
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisson.getBucket(key).expire( time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            log.warn("", e);
            return false;
        }
    }
    /**
     * 获取key 获取过期时间
     * @param key 键 不能为null
     * @return 时间(秒) 返回-1代表为永久有效
     */
    public long getExpire(String key) {
        return redisson.getBucket(key).remainTimeToLive()/1000;
    }
    /**
     * 判断key是否存在
     * @param key 键
     * @return true 存在 false不存在
     */
    public boolean hasKey(String key) {

        return redisson.getBucket(key).isExists();

    }
    /**
     * 删除缓存
     * @param key 可以传一个值 或多个
     */
    @SuppressWarnings("unchecked")
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisson.getBucket(key[0]).delete();
            } else {
                for (String s : key) {
                    redisson.getBucket(s).delete();
                }
            }
        }
    }


    // ============================String=============================
    /**
     * 普通缓存获取
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        return key == null ? null : redisson.getBucket(key).get();
    }

    /**
     * 普通缓存获取
     * @param key 键
     * @return 值
     */
    public <T> T getBean(String key,Class<T> clazz) {
        Object o = redisson.getBucket(key).get();
        if(ObjectUtil.isNotNull(o)){
            return (T)o;
        }
        return null;
    }
    /**
     * 普通缓存放入
     * @param key 键
     * @param value 值
     * @return true成功 false失败
     */
    public boolean set(String key, Object value) {
        redisson.getBucket(key).set( value);
        return true;
    }

    /**
     * 普通缓存放入并设置时间
     * @param key 键
     * @param value 值
     * @param time 时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public boolean set(String key, Object value, long time) {
        if (time > 0) {
            redisson.getBucket(key).set(value, time, TimeUnit.SECONDS);
        } else {
            set(key, value);
        }
        return true;

    }

//    /**
//     * 递增
//     * @param key 键
//     * @return long
//     */
//    public long incr(String key) {
//        return redisTemplate.opsForValue().increment(key);
//    }
//
//    /**
//     * 递增
//     * @param key 键
//     * @param delta 要增加几(大于0)
//     * @return long
//     */
//    public long incr(String key, long delta) {
//        if (delta < 0) {
//            throw new RuntimeException("递增因子必须大于0");
//        }
//        return redisTemplate.opsForValue().increment(key, delta);
//    }
//    /**
//     * 递减
//     * @param key 键
//     * @param delta 要减少几(小于0)
//     * @return long
//     */
//    public long decr(String key, long delta) {
//        if (delta < 0) {
//            throw new RuntimeException("递减因子必须大于0");
//        }
//        return redisTemplate.opsForValue().increment(key, -delta);
//    }
//
    // ================================Map=================================
    /**
     * HashGet
     * @param key 键 不能为null
     * @param item 项 不能为null
     * @return 值
     */
    public Object hget(String key, String item) {
        return redisson.getMap(key).get(item);
    }
    public <T> T hget(String key, String item,Class<T> clazz) {
        Object o = redisson.getMap(key).get(item);
        if(ObjectUtil.isNotNull(o)){
            return (T)o;
        }
        return null;
    }
    /**
     * 获取hashKey对应的所有键值
     * @param key 键
     * @return 对应的多个键值
     */
    public Map<Object, Object> hmget(String key) {
        Map<Object, Object> map = new HashMap<>();
        Set<Map.Entry<Object, Object>> entries = redisson.getMap(key).entrySet();
        Iterator<Map.Entry<Object, Object>> iterator = entries.iterator();
        while (iterator.hasNext()){
            Map.Entry<Object, Object> next = iterator.next();
            map.put(next.getKey(),next.getValue());
        }
        return map;
    }
    /**
     * HashSet
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false 失败
     */
    public void hmset(String key, Map<String, Object> map) {
        redisson.getMap(key).putAll(map);
//        for (Map.Entry<Object, Object> stringObjectEntry : map.entrySet()) {
//            redisson.getMap(key).entrySet().add(stringObjectEntry);
//        }

    }
    /**
     * HashSet 并设置时间
     * @param key 键
     * @param map 对应多个键值
     * @param time 时间(秒)
     * @return true成功 false失败
     */
    public boolean hmset(String key, Map<String, Object> map, long time) {

        try {
            redisson.getMap(key).putAll(map);
            if (time > 0) {
                redisson.getMap(key).expire(time,TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            log.warn("", e);
            return false;
        }
    }
    /**
     * 向一张hash表中放入数据,如果不存在将创建
     * @param key 键
     * @param item 项
     * @param value 值
     * @return true 成功 false失败
     */
    public boolean hset(String key, String item, Object value) {
        try {
            redisson.getMap(key).putIfAbsent(item,value);
            return true;
        } catch (Exception e) {
            log.warn("", e);
            return false;
        }
    }
    /**
     * 向一张hash表中放入数据,如果不存在将创建
     * @param key 键
     * @param item 项
     * @param value 值
     * @param time 时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    public boolean hset(String key, String item, Object value, long time) {
        try {
            redisson.getMap(key).putIfAbsent(item,value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            log.warn("", e);
            return false;
        }
    }
    /**
     * 删除hash表中的值
     * @param key 键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    public void hdel(String key, Object... item) {
        Object remove = redisson.getMap(key).fastRemove(item);
    }
    /**
     * 判断hash表中是否有该项的值
     * @param key 键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    public boolean hHasKey(String key, String item) {
        boolean bool = false;
        if(redisson.getMap(key) != null){
            return redisson.getMap(key).containsKey(item);
        }
        return false;
    }
//    /**
//     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
//     * @param key 键
//     * @param item 项
//     * @param by 要增加几(大于0)
//     * @return
//     */
//    public double hincr(String key, String item, double by) {
//        return redisTemplate.opsForHash().increment(key, item, by);
//    }
//    /**
//     * hash递减
//     * @param key 键
//     * @param item 项
//     * @param by 要减少记(小于0)
//     * @return
//     */
//    public double hdecr(String key, String item, double by) {
//        return redisTemplate.opsForHash().increment(key, item, -by);
//    }
//

//
//    // ===============================list=================================
//    /**
//     * 获取list缓存的内容
//     * @param key 键
//     * @param start 开始
//     * @param end 结束 0 到 -1代表所有值
//     * @return List<Object>
//     */
//    public List<Object> lGet(String key, long start, long end) {
//        try {
//            return redisTemplate.opsForList().range(key, start, end);
//        } catch (Exception e) {
//            log.warn("", e);
//            return null;
//        }
//    }
//    /**
//     * 获取list缓存的长度
//     * @param key 键
//     * @return long
//     */
//    public long lGetListSize(String key) {
//        try {
//            return redisTemplate.opsForList().size(key);
//        } catch (Exception e) {
//            log.warn("", e);
//            return 0;
//        }
//    }
//    /**
//     * 通过索引 获取list中的值
//     * @param key 键
//     * @param index 索引 index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
//     * @return Object
//     */
//    public Object lGetIndex(String key, long index) {
//        try {
//            return redisTemplate.opsForList().index(key, index);
//        } catch (Exception e) {
//            log.warn("", e);
//            return null;
//        }
//    }
//    /**
//     * 将list放入缓存
//     * @param key 键
//     * @param value 值
//     * @return boolean
//     */
//    public boolean lSet(String key, Object value) {
//        try {
//            redisTemplate.opsForList().rightPush(key, value);
//            return true;
//        } catch (Exception e) {
//            log.warn("", e);
//            return false;
//        }
//    }
//    /**
//     * 将list放入缓存
//     * @param key 键
//     * @param value 值
//     * @param time 时间(秒)
//     * @return boolean
//     */
//    public boolean lSet(String key, Object value, long time) {
//        try {
//            redisTemplate.opsForList().rightPush(key, value);
//            if (time > 0) {
//                expire(key, time);
//            }
//            return true;
//        } catch (Exception e) {
//            log.warn("", e);
//            return false;
//        }
//    }
//    /**
//     * 将list放入缓存
//     * @param key 键
//     * @param value 值
//     * @return
//     */
//    public boolean lSet(String key, List<Object> value) {
//        try {
//            redisTemplate.opsForList().rightPushAll(key, value);
//            return true;
//        } catch (Exception e) {
//            log.warn("", e);
//            return false;
//        }
//    }
//    /**
//     484
//     * 将list放入缓存
//     *
//     * @param key 键
//     * @param value 值
//     * @param time 时间(秒)
//     * @return boolean
//     */
//    public boolean lSet(String key, List<Object> value, long time) {
//        try {
//            redisTemplate.opsForList().rightPushAll(key, value);
//            if (time > 0) {
//                expire(key, time);
//            }
//            return true;
//        } catch (Exception e) {
//            log.warn("", e);
//            return false;
//        }
//    }
//    /**
//     * 根据索引修改list中的某条数据
//     * @param key 键
//     * @param index 索引
//     * @param value 值
//     * @return boolean
//     */
//    public boolean lUpdateIndex(String key, long index, Object value) {
//        try {
//            redisTemplate.opsForList().set(key, index, value);
//            return true;
//        } catch (Exception e) {
//            log.warn("", e);
//            return false;
//        }
//    }
//    /**
//     * 移除N个值为value
//     * @param key 键
//     * @param count 移除多少个
//     * @param value 值
//     * @return 移除的个数
//     */
//    public long lRemove(String key, long count, Object value) {
//        try {
//            Long remove = redisTemplate.opsForList().remove(key, count, value);
//            return remove;
//        } catch (Exception e) {
//            log.warn("", e);
//            return 0;
//        }
//    }

    /**
     * 根据key获取有序Zset中的第一个
     * @param key
     * @return
     */
    public Object getZSetFirst(String key, Codec codec) {
        RScoredSortedSet<Object> scoredSortedSet = redisson.getScoredSortedSet(key, codec);
        if(scoredSortedSet != null){
            Object first = scoredSortedSet.first();
            return first;
        }
        return null;
    }
    /**
     * 向Zset添加值
     * @param key 键
     * @return
     */
    public Boolean zAdd(String key,Object value ,Double score) {
        RScoredSortedSet<Object> scoredSortedSet = redisson.getScoredSortedSet(key);
        return scoredSortedSet.add(score,value);
    }

    /**
     * 根据key获取ZSet中的所有值
     * @param key 键
     * @return
     */
    public <T> List<T>  getBeanByZRange(String key, Integer startIndex,Integer endIndex,Class<T> clazz) {
        Collection<Object> objects = getByZRange(key,startIndex,endIndex);
        List<T> a = new ArrayList<>();
        for (Object object : objects) {
            a.add((T)object);
        }
        return a;
    }
    /**
     * 根据key获取ZSet中的所有值,使用StringCodec
     * @param key 键
     * @return
     */
    public Collection<Object> getByZRange(String key,Integer startIndex,Integer endIndex) {
        RScoredSortedSet<Object> scoredSortedSet = redisson.getScoredSortedSet(key);
        Collection<Object> objects = scoredSortedSet.valueRange(startIndex,endIndex);
        return objects;
    }


    /**
     * 根据key获取ZSet中的所有值,使用StringCodec
     * @param key 键
     * @return
     */
    public Collection<Object> getByZRangeByCodec(String key,Integer startIndex,Integer endIndex, Codec codec) {
        RScoredSortedSet<Object> scoredSortedSet = redisson.getScoredSortedSet(key, codec);
        Collection<Object> objects = scoredSortedSet.valueRange(startIndex,endIndex);
        return objects;
    }




}
