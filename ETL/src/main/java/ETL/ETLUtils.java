package ETL;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-04-29 20:20
 */
public class ETLUtils {
    /*
    * @Description:    封装的数据清洗的工具类
    * @author:         Yu HaiFeng
    * @email:          15548737663@163.com
    * @createDate:     2020/4/29 20:22
    * @param string
    * @return:         void
    */
    public static String ETLData(String string){
        StringBuilder builder = new StringBuilder();

        //RX24KLBhwMI	lemonette	697	People & Blogs	512	24149	4.22	315	474	t60tW0WevkE	WZgoejVDZlo
        String[] split = string.split("\t");
        //清洗规则：
        //1.去掉无效数据
        if (split.length < 9){
            return null;
        }
        //2.去掉空格：People & Blogs
        split[3] = split[3].replaceAll(" ", "");//这一步很骚，处理完再放回去
        //3.t60tW0WevkE	WZgoejVDZlo	Xa_op4MhSkg ：用&连接

        //这一套逻辑我是真没想到
        for (int i = 0; i < split.length; i++) {
            if (i < 9){

                if (i < split.length - 1){
                    builder.append(split[i]).append("\t");
                }else {
                    builder.append(split[i]);
                }
            }else {
                if (i < split.length - 1){
                    builder.append(split[i]).append("&");
                }else {
                    builder.append(split[i]);
                }
            }
        }

        return builder.toString();
    }

}
