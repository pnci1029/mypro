package memory;

public class Memory {
    // 사용중인 메모리
    private Long used;

    // 최대 메모리
    private Long max;

    public Memory(Long used, Long max) {
        this.used = used;
        this.max = max;
    }

    public Long getUsed() {
        return used;
    }

    public Long getMax() {
        return max;
    }

    @Override
    public String toString() {
        return "Memory{" +
                "used=" + used +
                ", max=" + max +
                '}';
    }
}
