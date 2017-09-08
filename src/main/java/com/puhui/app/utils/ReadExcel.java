package com.puhui.app.utils;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadExcel {
    private String filePath;
    private List list = new ArrayList<>();

    public ReadExcel(String filePath){
        this.filePath = filePath;
    }

    public void readExcel() throws IOException, BiffException {
        //创建输入流
        InputStream stream = new FileInputStream(filePath);
        //获取Excel文件对象
        Workbook rwb = Workbook.getWorkbook(stream);
        //获取文件的指定工作表 默认的第一个
        Sheet sheet = rwb.getSheet(0);
        //行数(表头的目录不需要，从1开始)
        for(int i=0; i<sheet.getRows(); i++){
            //创建一个数组 用来存储每一列的值
            String[] str = new String[sheet.getColumns()];
            Cell cell = null;
            //列数
            for(int j=0; j<sheet.getColumns(); j++){
                //获取第i行，第j列的值
                cell = sheet.getCell(j,i);
                str[j] = cell.getContents();
            }
            //把刚获取的列存入list
            list.add(str);
        }
    }
    public List<Map<String,String>> outData(){
        List<Map<String,String>> listMap = new ArrayList<>();
        for(int i=1;i<list.size()-1;i++){
            String[] str = (String[])list.get(0);
            String[] str1 = (String[])list.get(i);
            Map<String,String> map = new HashMap<>();
            for(int j=0;j<str.length;j++){
                map.put(str[j],str1[j]);
            }
            listMap.add(map);
        }
        return listMap;
    }

    public static void main(String args[]) throws BiffException, IOException{
        ReadExcel excel = new ReadExcel("/Users/finup/Desktop/2.xls");
        excel.readExcel();
        List<Map<String,String>> listMap = excel.outData();
        System.out.println(listMap);
    }
}  

