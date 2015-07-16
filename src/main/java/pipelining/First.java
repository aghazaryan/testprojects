package pipelining;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.*;

/**
 * Created by aghasighazaryan on 7/15/15.
 */
public class First {


    private int size = 0;
    private Jedis jedis;
    private Pipeline pipeline;

    private Set<String> keys = new HashSet<String>();

    public First(int size, String host, int port) {
        this.size = size;
        jedis = new Jedis(host, port);
        pipeline = jedis.pipelined();

        for (int i = 0; i < 5; i++) {
            jedis.set("key" + i, "value" + i);
            jedis.set("foo" + i, "value" + i);
        }
    }

    public void delete(String pattern) {


        keys.addAll(jedis.keys(pattern));


        Iterator<String> iterator = keys.iterator();
        int i;

        while (!keys.isEmpty()) { // delete batch
            i = 0;
            while (iterator.hasNext() && i++ < size) {
                pipeline.del(iterator.next());
                iterator.remove();
            }
            pipeline.sync();
        }

    }

}
