package etltest;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-04-20 14:16
 */
public class LogMapper2 extends Mapper<LongWritable, Text, Text, NullWritable> {

    private Text outK = new Text();
    private LogBean logBean;

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        boolean result = parseLog(line, context);

        if (!result){
            return;
        }
        outK.set(logBean.toString());
        context.write(outK,NullWritable.get());
    }

    private boolean parseLog(String line,Context context){
        String[] split = line.split(" ");
        logBean = new LogBean();

        if (split.length > 11){
            logBean.setIp(split[0]);
            logBean.setTime_zone(split[3].substring(1) + " " +  split[4].substring(0,split[4].length() - 1));
            logBean.setUser(split[5].substring(1));
            logBean.setRequest(split[6]);
            logBean.setUserURL(split[7].substring(0,split[7].length()-1));
            logBean.setStatus(split[8]);
            logBean.setBody_bytes_sent(split[9]);
            logBean.setHttp_refer(split[10]);

            if (split.length > 12){
                logBean.setHttp_user_agent(split[11] + " " + split[12].substring(0,split[12].length() - 1) + ")");

            }else {
                logBean.setHttp_user_agent(split[11]);
            }

            if (Integer.parseInt(split[8]) > 400){
                logBean.setValid(false);
            }else {
                logBean.setValid(true);
            }

            return logBean.isValid();
        }

        return false;

    }
}
