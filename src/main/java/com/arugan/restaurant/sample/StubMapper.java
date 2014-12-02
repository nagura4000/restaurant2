package com.arugan.restaurant.sample;
import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class StubMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();
  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {

      String line = value.toString();
      StringTokenizer tk = new StringTokenizer(line);
      while(tk.hasMoreTokens()){
          word.set(tk.nextToken());
          context.write(word, one);
      }

  }
}
