package com.demo;

public class Server1 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1243);
        Socket socket = serverSocket.accept();
        System.out.println("新的连接建立");
        BufferedReader bufferedReader;
        InputStreamReader inputStreamReader;
        BufferedWriter bufferedWriter;
        OutputStreamWriter outputStreamWriter;
        while (true){
            InputStream in;
            inputStreamReader = new InputStreamReader(socket.getInputStream());
            bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            System.out.println(socket.getInetAddress()+":"+bufferedReader.readLine());
            OutputStream out;
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            System.out.println("回复");
            Scanner scanner = new Scanner(System.in);
            bufferedWriter.write(scanner.nextLine()+"\n");
            bufferedWriter.flush();

        }


    }
}
