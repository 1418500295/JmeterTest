package com.demo;

public class One {
  public static void main(String[] args) throws IOException {
        Socket socket  = new Socket("127.0.0.1",1243);
        System.out.println("连接成功");
        BufferedWriter bufferedWriter;
        OutputStreamWriter outputStreamWriter;
        BufferedReader bufferedReader;
        InputStreamReader inputStreamReader;
        while (true){
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);
            System.out.println("回复");
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(scanner.nextLine()+"\n");
            bufferedWriter.flush();
            inputStreamReader = new InputStreamReader(socket.getInputStream());
            bufferedReader = new BufferedReader(inputStreamReader);
            System.out.println(socket.getInetAddress()+":"+bufferedReader.readLine());

        }

    }
}
