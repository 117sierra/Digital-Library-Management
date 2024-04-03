package com.gss.minor1.Repository;

import com.gss.minor1.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class CacheRepository {

    @Autowired
    private RedisTemplate redisTemplate;

    private static String user_prefix="USER::";
    public User getuserbycontact(String contact){
        return (User) redisTemplate.opsForValue().get(user_prefix+contact);
    }
    public void insertDatabycontact(User user){
        redisTemplate.opsForValue().set(user_prefix+user.getContact(),user,10, TimeUnit.MINUTES);
    }
}
