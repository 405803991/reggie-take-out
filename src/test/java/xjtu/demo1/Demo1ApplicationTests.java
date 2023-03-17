package xjtu.demo1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Demo1ApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testString() {
        ValueOperations valueOperations = redisTemplate.opsForValue();

        valueOperations.set("city123", "bj");

        String value = (String) redisTemplate.opsForValue().get("city123");
        System.out.println(value);



    }

}
