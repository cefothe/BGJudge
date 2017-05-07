package com.cefothe.bgjudge.workers.executors;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cefothe on 13.07.16.
 */
public class JavaExecutor implements  Executor {

    @Override
    public List<String> execute(File file, List<String> params) throws IOException {

        Process process =
                new ProcessBuilder("java","-cp",file.getAbsolutePath())
                        .redirectErrorStream(false)
                        .start();

        ArrayList<String> output = new ArrayList<>();
        BufferedReader br = new BufferedReader(
                new InputStreamReader(process.getInputStream()));
        String line = null;

        OutputStream stdin = process.getOutputStream();

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin));

        for(String param:params){
            writer.write(param);
        }
        writer.flush();
        writer.close();

        while ( (line = br.readLine()) != null )
            output.add(line);

        return  output;
    }
}
