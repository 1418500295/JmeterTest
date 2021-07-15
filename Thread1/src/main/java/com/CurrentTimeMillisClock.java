private static final CurrentTimeMillisClock MILLIS_CLOCK = new CurrentTimeMillisClock(1);
    private final long precision;
    private final AtomicLong now;

    private CurrentTimeMillisClock(long precision) {
        this.precision = precision;
        now = new AtomicLong(System.currentTimeMillis());
        scheduleClockUpdating();
    }

    public static CurrentTimeMillisClock millisClock() {
        return MILLIS_CLOCK;
    }

    private void scheduleClockUpdating() {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor(runnable -> {
            Thread thread = new Thread(runnable, "system.clock");
            thread.setDaemon(true);
            return thread;
        });
        scheduler.scheduleAtFixedRate(() -> now.set(System.currentTimeMillis()), precision, precision, TimeUnit.MILLISECONDS);
    }

    public long now() {
        return now.get();
    }

//使用CurrentTimeMillisClock.millisClock().now()获取时间戳
