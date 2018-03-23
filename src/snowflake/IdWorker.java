package snowflake;

/**
 * @author wangxuan
 * @date 2018/3/23 16:38
 */
public class IdWorker {

    private long workerId;
    private long dataCenterId;
    private long sequence;

    public IdWorker(long workerId, long dataCenterId, long sequence){

        assert workerId > maxWorkerId || workerId < 0;
        assert dataCenterId > maxdataCenterId || dataCenterId < 0;

        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
        this.sequence = sequence;
    }

    private final static long twepoch = 1288834974657L;  //起始时间戳，用于用当前时间戳减去这个时间戳，算出偏移量 2010-11-04 09:42:54

    private final static long workerIdBits = 5L; //workerId占用的位数：5
    private final static long dataCenterIdBits = 5L; //dataCenterId占用的位数：5
    // ~ 位运算符，相当于NOT
    private final static long maxWorkerId = ~(-1L << workerIdBits); // workerId可以使用的最大数值：31
    private final static long maxdataCenterId = ~(-1L << dataCenterIdBits); // dataCenterId可以使用的最大数值：31
    private final static long sequenceBits = 12L; //序列号占用的位数：12

    private final static long workerIdShift = sequenceBits; // 12
    private final static long dataCenterIdShift = sequenceBits + workerIdBits; // 17
    private final static long timestampLeftShift = sequenceBits + workerIdBits + dataCenterIdBits; // 22
    private final static long sequenceMask = ~(-1L << sequenceBits); // 4095

    private long lastTimestamp = -1L;

    public synchronized long nextId() {
        long timestamp = timeGen();

        // 如果当前时间小于最后时间戳，则时钟有问题
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds",
                    lastTimestamp - timestamp));
        }

        // 当时间戳相同时，序列号自增1
        if (lastTimestamp == timestamp) {
            // 序列号自增并和sequenceMask相与，将sequence值控制在0 ~ 4095
            sequence = (sequence + 1) & sequenceMask;
            // 当序列号自增后回到0时，将时间戳更新
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }
        // 更新最后时间戳
        lastTimestamp = timestamp;
        // 时间戳偏移量左移22位 (或) dataCenterId左移17位 (或) workerId左移12位 (或) sequence
        return ((timestamp - twepoch) << timestampLeftShift) |
                (dataCenterId << dataCenterIdShift) |
                (workerId << workerIdShift) |
                sequence;
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    private long timeGen(){
        return System.currentTimeMillis();
    }

    //---------------测试---------------
    public static void main(String[] args) {
        IdWorker worker = new IdWorker(1,1,1);
        for (int i = 0; i < 30; i++) {
            System.out.println(worker.nextId());
        }
    }
}
