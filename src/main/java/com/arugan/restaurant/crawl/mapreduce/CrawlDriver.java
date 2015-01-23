package com.arugan.restaurant.crawl.mapreduce;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import com.arugan.restaurant.mongodb.MongoDB;
import com.mongodb.DBCollection;

public class CrawlDriver extends Configured implements Tool {

	public static void main(String[] args) throws Exception {
		ToolRunner.run(new CrawlDriver(), args);
	}

	@Override
	public int run(String[] args) throws Exception {
		/*
		 * Validate that two arguments were passed from the command line.
		 */
		if (args.length != 2) {
			System.out.printf("Usage: restrant <input dir> <output dir>\n");
			System.exit(-1);
		}

		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd-HHmm");
		String dateKey = format.format(new Date());


		DBCollection target = MongoDB.getInstance().getTargetDBCollection();
		target.drop();

		String inputDir = args[0];
		String outputDir = args[1];
		boolean success = false;
		Path inputPath = null;
		int maxDepth = Integer.parseInt(args[2]);

		for (int depth = 0; depth < maxDepth; depth++) {

			/*
			 * Instantiate a Job object for your job's configuration.
			 */
			Job job = new Job(new Configuration(), "restrant");

			/*
			 * Specify the jar file that contains your driver, mapper, and reducer.
			 * Hadoop will transfer this jar file to nodes in your cluster running
			 * mapper and reducer tasks.
			 */
			job.setJarByClass(CrawlDriver.class);

			/*
			 * Specify an easily-decipherable name for the job.
			 * This job name will appear in reports and logs.
			 */
			job.setJobName("Restrant Crawl");
			job.setOutputKeyClass(NullWritable.class);
			job.setOutputValueClass(Text.class);

			job.setMapperClass(CrawlMapper.class);
			job.setReducerClass(CrawlReducer.class);

			job.setMapOutputKeyClass(Text.class);
			job.setMapOutputValueClass(Text.class);

			job.setInputFormatClass(TextInputFormat.class);
			job.setOutputFormatClass(TextOutputFormat.class);

			if (inputPath == null) {
				inputPath = new Path(inputDir);
			}

			FileInputFormat.setInputPaths(job, inputPath);
			//FileOutputFormat.setOutputPath(job, new Path(args[1]));
			Path outoutPath = new Path(outputDir + "/" + dateKey + "/" + depth);
			FileOutputFormat.setOutputPath(job, outoutPath);

			inputPath = outoutPath;

			/*
			 * Start the MapReduce job and wait for it to finish.
			 * If it finishes successfully, return 0. If not, return 1.
			 */
			success = job.waitForCompletion(true);
		}
		return success ? 0 : 1;
	}
}

