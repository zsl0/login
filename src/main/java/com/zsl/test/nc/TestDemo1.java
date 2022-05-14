package com.zsl.test.nc;

import ucar.ma2.InvalidRangeException;
import ucar.ma2.Section;
import ucar.nc2.Attribute;
import ucar.nc2.NetcdfFile;
import ucar.nc2.Variable;
import ucar.nc2.dataset.NetcdfDataset;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.function.Consumer;

/**
 * @Author zsl
 * @Date 2022/1/4 14:20
 */
public class TestDemo1 {


    private static float latNorth;  // latnorth 是数据左上角经度， 数据精度是0.01， 乘以100 是计算 维度有多少个点
    private static float lonEast;

    public static void main(String[] args) {
//        fun02();
        fun01();
//        System.out.println(LocalDateTime.now().toString().split("\\.")[0].split("T")[1].replace(":", ""));
    }

    /**
     * 从全球nc数据中裁剪指定区域的数据
     *
     * @param resourPath nc文件所在位置路径
     *  @param startLon  指定区域开始的经度
     *  @param startLat  指定区域开始的纬度
     * @param latCount  纬度要读取多少个点
     * @param lonCount  经度要读取多少个点
     * @param begin    从时间纬度的第几层开始读取
     * @param end      第几层结束
     * @return   指定区域的数据
     */
    public static short[][] readNCfile(String resourPath, int latCount, int lonCount, float startLon, float startLat,int begin,
                                       int end) {

        try {
            NetcdfFile ncFile = NetcdfFile.open(resourPath); //获取源文件
            Variable v = ncFile.findVariable("qpf_ml"); //读取qpf_ml的变量，
            //
            short[][] values = null;
            for (int i = begin; i < end; i++) {    //本读取的qpf_ml是一个3维数据，时间、经度、维度，一下子把3维数据全部读出来会很大，时间维度是24层，所以通过遍历时间维度获取数据，i为时间维度的层数

                int[] origin = { i, (int) ((latNorth - startLat) * 100), (int) ((startLon - lonEast) * 100) };//origin 设置维度准备从哪个位置开始读取数据
                int[] shape = { 1, latCount, lonCount };//shape 和origin对应，设置读取多少点后结束
                short[][] temp = (short[][]) v.read(origin, shape).reduce(0).copyToNDJavaArray(); //去掉时间维度，变为二维

                if (values != null) {
                    for (int j = 0; j < latCount; j++) {
                        for (int k = 0; k < lonCount; k++) {
                            values[j][k] += temp[j][k];
                        }
                    }
                }else {
                    values = temp;
                }

            }
            ncFile.close();
            return values;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidRangeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    public static void fun02() {
        String filename = "C:\\Users\\Administrator\\Desktop\\20210109\\20210109.115000.1km.000.nc";

        String lon = "lon";
        String lat = "lat";
        String level = "level";
        String time = "time";
        String R = "R";
        String CR = "CR";
        String V = "V";
        try {
            NetcdfFile ncfile = NetcdfDataset.open(filename);

            System.out.println(ncfile);

            Variable variable1 = ncfile.findVariable(lon);
            Variable variable2 = ncfile.findVariable(lat);
            Variable variable3 = ncfile.findVariable(level);
            Variable variable4 = ncfile.findVariable(time);
            Variable variable5 = ncfile.findVariable(R);
            Variable variable6 = ncfile.findVariable(CR);
            Variable variable7 = ncfile.findVariable(V);

            System.out.println(variable1.read(new int[]{0}, new int[]{2}));

            double[] lonArr = (double[]) variable1.read().copyTo1DJavaArray();

            System.out.println("lonArr: " + lonArr.length);
            System.out.println("----------------lonArr------------------");
            for (int i = 0; i < lonArr.length; i++) {
                System.out.println("lonArr[" + i + "] : " + lonArr[i]);
            }

//            int[] origin = new int[] {0, 1, 1, 2};
//            int[] shape = new int[] {1, 1, 2, 5};
//            System.out.println(variable5.read(new Section(origin, shape)).toString());

//            variable6.read().copyTo1DJavaArray().

//            System.out.println(variable5.getShape());
//            System.out.println(variable5.read(origin, variable5.getShape()).toString());



        } catch (IOException | InvalidRangeException e) {
            e.printStackTrace();
        }
    }

    public static void fun01() {
        String filename = "C:\\Users\\Administrator\\Desktop\\20210109\\20210109.114000.1km.000.nc";

        String lon = "lon";
        String lat = "lat";
        String level = "level";
        String time = "time";
        String R = "R";
        String CR = "CR";
        String V = "V";
        try {
            NetcdfFile ncfile = NetcdfDataset.open(filename);

            System.out.println(ncfile);

            Variable variable1 = ncfile.findVariable(lon);
            Variable variable2 = ncfile.findVariable(lat);
            Variable variable3 = ncfile.findVariable(level);
            Variable variable4 = ncfile.findVariable(time);
            Variable variable5 = ncfile.findVariable(R);
            Variable variable6 = ncfile.findVariable(CR);
            Variable variable7 = ncfile.findVariable(V);


            System.out.println(variable1.read().copyTo1DJavaArray());
            System.out.println(variable2.read().copyTo1DJavaArray());
            System.out.println(variable3.read().copyTo1DJavaArray());
            System.out.println(variable4.read().copyTo1DJavaArray());

            int[] origin = new int[] {0, 1, 1, 1};
            int[] shape = new int[] {1, 1, 1, 1};
            System.out.println(variable5.read(new Section(origin, shape)).toString());
            System.out.println(variable5.getShape());
//            System.out.println(variable5.read(origin, variable5.getShape()).toString());

            System.out.println("----------------R------------------");
            variable5.attributes().forEach(new Consumer<Attribute>() {
                @Override
                public void accept(Attribute attribute) {
                    System.out.println(attribute);
                }
            });
            System.out.println("----------------CR------------------");
            variable6.attributes().forEach(new Consumer<Attribute>() {
                @Override
                public void accept(Attribute attribute) {
                    System.out.println(attribute);
                }
            });
            System.out.println("----------------V------------------");
            System.out.println(variable7.attributes().findAttribute("_FillValue").getStringValue());
            variable7.attributes().forEach(new Consumer<Attribute>() {
                @Override
                public void accept(Attribute attribute) {
                    System.out.println(attribute);
                }
            });

            double[] lonArr = (double[]) variable1.read().copyTo1DJavaArray();
            double[] latArr = (double[]) variable2.read().copyTo1DJavaArray();
            double[] levelArr = (double[]) variable3.read().copyTo1DJavaArray();
            long[] timeArr = (long[]) variable4.read().copyTo1DJavaArray();
            float[] RArr = (float[]) variable5.read().copyTo1DJavaArray();
            float[] CRArr = (float[]) variable6.read().copyTo1DJavaArray();
            float[] VArr = (float[]) variable7.read().copyTo1DJavaArray();

            System.out.println("lonArr: " + lonArr.length);
            System.out.println("----------------lonArr------------------");
            for (int i = 0; i < lonArr.length; i++) {
                System.out.println("lonArr[" + i + "] : " + lonArr[i]);
            }

            System.out.println("latArr: " + latArr.length);
            System.out.println("----------------latArr------------------");
            for (int i = 0; i < latArr.length; i++) {
                System.out.println("latArr[" + i + "] : " + latArr[i]);
            }

            System.out.println("levelArr: " + levelArr.length);
            System.out.println("----------------levelArr------------------");
            for (int i = 0; i < levelArr.length; i++) {
                System.out.println("levelArr[" + i + "] : " + levelArr[i]);
            }

            System.out.println("timeArr: " + timeArr.length);
            System.out.println("----------------timeArr------------------");
            for (int i = 0; i < timeArr.length; i++) {
                System.out.println("timeArr[" + i + "] : " + timeArr[i]);
            }

/*            System.out.println("RArr: " + RArr.length);
            System.out.println("----------------RArr------------------");
            for (int i = 0; i < RArr.length; i++) {
                System.out.println("RArr[" + i + "] : " + RArr[i]);
            }

            System.out.println("CRArr: " + CRArr.length);
            System.out.println("----------------CRArr------------------");
            for (int i = 0; i < CRArr.length; i++) {
                System.out.println("CRArr[" + i + "] : " + CRArr[i]);
            }

            System.out.println("VArr: " + VArr.length);
            System.out.println("----------------VArr------------------");
            for (int i = 0; i < VArr.length; i++) {
                System.out.println("VArr[" + i + "] : " + VArr[i]);
            }*/
            Attribute units = variable4.findAttribute("units");
            System.out.println(units.getStringValue());


        } catch (IOException | InvalidRangeException e) {
            e.printStackTrace();
        }
    }
}
