package com.ifyou.skypivot.controller;

import com.sxnd.develop.sample.DevelopSampleApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author GW00305020
 * @ClassName RedissionLockTest
 * @description: TODO
 * @date 2023年09月19日
 * @version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DevelopSampleApplication.class})
public class RedissionLockTest {

    @Resource
    private RedissonClient redisson;

    @Test
    public void testLock() throws InterruptedException {
        RLock lock = redisson.getLock("myLock");

        // traditional lock method
        lock.lock();

        // or acquire lock and automatically unlock it after 10 seconds
        lock.lock(10, TimeUnit.SECONDS);

        // or wait for lock aquisition up to 100 seconds
        // and automatically unlock it after 10 seconds
        boolean res = lock.tryLock(100, 10, TimeUnit.SECONDS);
        if (res) {
            try {

            } finally {

                lock.unlock();
            }
        }
    }
}
