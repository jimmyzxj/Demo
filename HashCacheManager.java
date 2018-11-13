import java.util.HashMap;

public class HashCacheManager {

    //cacheMap for storage
    private static HashMap<String, Object> cacheMap = new HashMap<>();

    private HashCacheManager() {
    }

    public static HashCacheManager getInstance() {
        return new HashCacheManager();
    }

    public void put(String key, Object obj) {
        cacheMap.put(key, new CacheItem(obj));
    }

    public void put(String key, Object obj, long expireTime) {
        cacheMap.put(key, new CacheItem(obj, expireTime));
    }

    public Object get(String key) {
        return checkVaild(key);
    }

    //checked the obj whether alive
    public Object checkVaild(String key) {
        CacheItem value = (CacheItem) cacheMap.get(key);
        long currentTime = System.currentTimeMillis();
        if ((currentTime - value.getPutTime()) > value.getExpireTime()) {
            cacheMap.remove(key);
            return null;
        }
        return value.getValue();
    }

    //cacheItem contains the value of storage and the expireTime,putTime for the value
    class CacheItem {
        private Object value;
        //putTime -- the time of the value putting in
        private long putTime;
        //expireTime -- the living time of the value
        private long expireTime = 60 * 1000;

        public CacheItem(Object value, long expireTime) {
            this.value = value;
            this.expireTime = expireTime;
            this.putTime = System.currentTimeMillis();
        }

        public CacheItem(Object value) {
            this.value = value;
            this.putTime = System.currentTimeMillis();
            ;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public long getExpireTime() {
            return expireTime;
        }

        public void setExpireTime(long expireTime) {
            this.expireTime = expireTime;
        }

        public long getPutTime() {
            return putTime;
        }

        public void setPutTime(long putTime) {
            this.putTime = putTime;
        }
    }
}
