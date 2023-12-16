package com.demo;

public class Client {
  public static void heartBeat(BufferedWriter bufferedWriter){
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    bufferedWriter.write(LocalDateTime.now()+"心跳检测\n");
                    bufferedWriter.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        },0,5, TimeUnit.SECONDS);
    }
  
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket  = new Socket("127.0.0.1",1111);
        System.out.println("连接成功");
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));;
        heartBeat(bufferedWriter);
        BufferedReader bufferedReader;
        InputStreamReader inputStreamReader;
        while (true){
//            Scanner scanner = new Scanner(System.in);
//            System.out.println("回复");
            Thread.sleep(2000);
            bufferedWriter.write("我是客户端\n");
            bufferedWriter.flush();
            inputStreamReader = new InputStreamReader(socket.getInputStream());
            bufferedReader = new BufferedReader(inputStreamReader);
            System.out.println(socket.getInetAddress()+":"+bufferedReader.readLine());

        }

    }
}
