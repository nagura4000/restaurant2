package com.arugan.restaurant.crawl.mapreduce;
import java.io.IOException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class CrawlReducer extends Reducer<Text, Text, NullWritable, Text> {

  @Override
  public void reduce(Text result, Iterable<Text> values, Context context)
      throws IOException, InterruptedException {

//	  String crawlResult = result.toString();

//      int type = 0;
//      if (crawlResult.startsWith("http://")) {
//    	  type = 1;
//      }
//      context.write(new IntWritable(type), result);
//      context.write(result, new IntWritable(type));

      context.write(NullWritable.get(), result);
  }
}