package rpc;

import sun.misc.Unsafe;

public class CASLock {
    volatile static int state = 0;
    static final Unsafe unsafe = Unsafe.getUnsafe();
    private static long valueoffset;

    static {
        try {
            valueoffset = unsafe.objectFieldOffset(CASLock.class.getField("state"));//拿到内存中state的实时值
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public boolean trylock() {
        //当资源处于空闲状态时候，只有一个线程能拿到资源，并将state状态改为占用state+1
        if (getState() == 0) {
            int expect = getState();
            //当有多个线程同时对state进行操作时候，只能有一个能成功，其余失败（原子操作）
            if (unsafe.compareAndSwapInt(this, valueoffset, expect, expect + 1)) {
                return true;
            }
            return false;
        }
        return false;
    }

    public void unlock() {
        int except = getState();
        if (except == 0) throw new IllegalArgumentException("没有资源被占用");
        unsafe.compareAndSwapInt(this, valueoffset, except, except - 1);
    }

    public int getState() {
        return this.state;
    }

    public static void main(String[] args) {

    }
}


