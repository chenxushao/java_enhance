package bbejeck.guava.chapter8.files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * User: Bill Bejeck
 * Date: 5/2/13
 * Time: 10:00 PM
 */
public class ReadingWritingFilesExample {

    public static void main(String[] args) throws Exception {
        List<String> sentences = Lists.newArrayList();
        sentences.add("This is line one\n");
        sentences.add("This is line two\n");
        sentences.add("This is line three\n");
        sentences.add("This is line four\n");

        File file = new File("src/main/resources/sentences.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        for (String sentence : sentences) {
             writer.write(sentence);
        }
        writer.close();

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while((line= reader.readLine()) != null){
            System.out.println(line);
        }
        reader.close();
    }
}
