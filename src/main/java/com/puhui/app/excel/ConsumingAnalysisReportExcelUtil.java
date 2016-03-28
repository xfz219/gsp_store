package com.puhui.app.excel;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.puhui.app.po.ConsumingAnalysisReport;

/**
 * @comment app耗时分析表excel
 * @author liwang
 * @time 2015年7月23日 下午2:45:25
 */
public class ConsumingAnalysisReportExcelUtil {

    public static HSSFWorkbook creatExcel(List<ConsumingAnalysisReport> list) throws Exception {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("APP耗时分析表");

        /************* 设置列宽度 ***************/
        sheet.setColumnWidth(0, 4000);
        sheet.setColumnWidth(1, 4000);
        sheet.setColumnWidth(2, 4000);
        sheet.setColumnWidth(3, 4000);
        sheet.setColumnWidth(4, 4000);
        sheet.setColumnWidth(5, 4000);
        sheet.setColumnWidth(6, 4000);
        sheet.setColumnWidth(7, 4000);
        sheet.setColumnWidth(8, 4000);
        sheet.setColumnWidth(9, 4000);
        sheet.setColumnWidth(10, 4000);
        sheet.setColumnWidth(11, 4000);
        sheet.setColumnWidth(12, 4000);
        sheet.setColumnWidth(13, 4000);
        sheet.setColumnWidth(14, 4000);
        sheet.setColumnWidth(15, 4000);
        sheet.setColumnWidth(16, 4000);
        sheet.setColumnWidth(17, 4000);
        sheet.setColumnWidth(18, 4000);
        sheet.setColumnWidth(19, 4000);
        sheet.setColumnWidth(20, 4000);
        sheet.setColumnWidth(21, 6000);
        sheet.setColumnWidth(22, 6000);
        /***************** 样式1 *********************/
        // 创建字体样式
        HSSFFont font = wb.createFont();
        font.setFontName("仿宋");
        font.setBoldweight((short) 100);
        font.setFontHeight((short) 300);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 字体加粗
        font.setColor(HSSFColor.DARK_TEAL.index);

        // 创建单元格样式
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        // 设置边框
        style.setBottomBorderColor(HSSFColor.RED.index);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);

        style.setFont(font);// 设置字体
        style.setLocked(true);

        /****************** 样式2 ****************************/
        // 创建字体样式
        HSSFFont font1 = wb.createFont();
        font1.setFontName("仿宋");
        font1.setFontHeightInPoints((short) 12);// 设置字体大小
        font1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 字体加粗
        font1.setColor(HSSFColor.DARK_TEAL.index);
        // 创建单元格样式
        HSSFCellStyle style1 = wb.createCellStyle();
        style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style1.setFillForegroundColor(HSSFColor.WHITE.index);
        style1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        // 设置边框
        style1.setBottomBorderColor(HSSFColor.RED.index);
        style1.setBorderBottom(HSSFCellStyle.BORDER_DOTTED);
        style1.setBorderLeft(HSSFCellStyle.BORDER_DOTTED);
        style1.setBorderRight(HSSFCellStyle.BORDER_DOTTED);
        style1.setBorderTop(HSSFCellStyle.BORDER_DOTTED);

        style1.setFont(font1);// 设置字体

        // 创建Excel的sheet的一行
        HSSFRow row = sheet.createRow(0);
        row.setHeight((short) 500);// 设定行的高度

        HSSFCell cell = row.createCell(0);
        cell.setCellStyle(style);
        cell.setCellValue("进件号");

        cell = row.createCell(1);
        cell.setCellStyle(style);
        cell.setCellValue("客户名称");

        cell = row.createCell(2);
        cell.setCellStyle(style);
        cell.setCellValue("销售姓名");

        cell = row.createCell(3);
        cell.setCellStyle(style);
        cell.setCellValue("录入身份信息耗时");

        cell = row.createCell(4);
        cell.setCellStyle(style);
        cell.setCellValue("录入职业信息耗时");

        cell = row.createCell(5);
        cell.setCellStyle(style);
        cell.setCellValue("录入联系人信息耗时");

        cell = row.createCell(6);
        cell.setCellStyle(style);
        cell.setCellValue("申请征信报告信息耗时");

        cell = row.createCell(7);
        cell.setCellStyle(style);
        cell.setCellValue("验证录入淘宝信息耗时");

        cell = row.createCell(8);
        cell.setCellStyle(style);
        cell.setCellValue("验证运营商信息耗时");

        cell = row.createCell(9);
        cell.setCellStyle(style);
        cell.setCellValue("提交工资流水信息耗时");

        cell = row.createCell(10);
        cell.setCellStyle(style);
        cell.setCellValue("上传身份证照片耗时");

        cell = row.createCell(11);
        cell.setCellStyle(style);
        cell.setCellValue("上传居住证明信息耗时");

        cell = row.createCell(12);
        cell.setCellStyle(style);
        cell.setCellValue("上传工作证明耗时");

        cell = row.createCell(13);
        cell.setCellStyle(style);
        cell.setCellValue("上传房产证明耗时");

        cell = row.createCell(14);
        cell.setCellStyle(style);
        cell.setCellValue("上传经营证明耗时");

        cell = row.createCell(15);
        cell.setCellStyle(style);
        cell.setCellValue("上传经营地址证明耗时");

        cell = row.createCell(16);
        cell.setCellStyle(style);
        cell.setCellValue("上传已婚证明耗时");

        cell = row.createCell(17);
        cell.setCellStyle(style);
        cell.setCellValue("上传子女证明耗时");

        cell = row.createCell(18);
        cell.setCellStyle(style);
        cell.setCellValue("上传学历证明耗时");

        cell = row.createCell(19);
        cell.setCellStyle(style);
        cell.setCellValue("上传社保公积金证明耗时");

        cell = row.createCell(20);
        cell.setCellStyle(style);
        cell.setCellValue("上传其他证明耗时");

        cell = row.createCell(21);
        cell.setCellStyle(style);
        cell.setCellValue("客户录入资料总耗时");

        cell = row.createCell(22);
        cell.setCellStyle(style);
        cell.setCellValue("更换产品次数");

        if(CollectionUtils.isNotEmpty(list)&&list.size()>0){
        	for (int i = 0; i < list.size(); i++) {
                ConsumingAnalysisReport consumingAnalysisReport = list.get(i);

                row = sheet.createRow(i + 1);
                row.setHeight((short) 420);
                
                cell = row.createCell(0);
                cell.setCellStyle(style1);
                cell.setCellValue(consumingAnalysisReport.getRequestId());

                cell = row.createCell(1);
                cell.setCellStyle(style1);
                cell.setCellValue(consumingAnalysisReport.getCustomerName());

                cell = row.createCell(2);
                cell.setCellStyle(style1);
                cell.setCellValue(consumingAnalysisReport.getSalesName());

                cell = row.createCell(3);
                cell.setCellStyle(style1);
                cell.setCellValue(consumingAnalysisReport.getIdentityTime());

                cell = row.createCell(4);
                cell.setCellStyle(style1);
                cell.setCellValue(consumingAnalysisReport.getOccupationTime());

                cell = row.createCell(5);
                cell.setCellStyle(style1);
                cell.setCellValue(consumingAnalysisReport.getContactTime());

                cell = row.createCell(6);
                cell.setCellStyle(style1);
                cell.setCellValue(consumingAnalysisReport.getCreditReportTime());

                cell = row.createCell(7);
                cell.setCellStyle(style1);
                cell.setCellValue(consumingAnalysisReport.getTaobaoTime());

                cell = row.createCell(8);
                cell.setCellStyle(style1);
                cell.setCellValue(consumingAnalysisReport.getCommunicationTime());

                cell = row.createCell(9);
                cell.setCellStyle(style1);
                cell.setCellValue(consumingAnalysisReport.getPayFlowTime());

                cell = row.createCell(10);
                cell.setCellStyle(style1);
                cell.setCellValue(consumingAnalysisReport.getIdPhotoTime());

                cell = row.createCell(11);
                cell.setCellStyle(style1);
                cell.setCellValue(consumingAnalysisReport.getResidenceCertificateTime());

                cell = row.createCell(12);
                cell.setCellStyle(style1);
                cell.setCellValue(consumingAnalysisReport.getWorkCertificateTime());

                cell = row.createCell(13);
                cell.setCellStyle(style1);
                cell.setCellValue(consumingAnalysisReport.getEstateCertificateTime());

                cell = row.createCell(14);
                cell.setCellStyle(style1);
                cell.setCellValue(consumingAnalysisReport.getOperatorCertificateTime());

                cell = row.createCell(15);
                cell.setCellStyle(style1);
                cell.setCellValue(consumingAnalysisReport.getBusinessAddressCertificateTime());

                cell = row.createCell(16);
                cell.setCellStyle(style1);
                cell.setCellValue(consumingAnalysisReport.getMarriedTime());

                cell = row.createCell(17);
                cell.setCellStyle(style1);
                cell.setCellValue(consumingAnalysisReport.getChildrenTime());

                cell = row.createCell(18);
                cell.setCellStyle(style1);
                cell.setCellValue(consumingAnalysisReport.getDegreeTime());

                cell = row.createCell(19);
                cell.setCellStyle(style1);
                cell.setCellValue(consumingAnalysisReport.getProvidentSocialTime());

                cell = row.createCell(20);
                cell.setCellStyle(style1);
                cell.setCellValue(consumingAnalysisReport.getOtherTime());

                cell = row.createCell(21);
                cell.setCellStyle(style1);
                cell.setCellValue(consumingAnalysisReport.getDataTotalTime());

                cell = row.createCell(22);
                cell.setCellStyle(style1);
                cell.setCellValue(consumingAnalysisReport.getProductUpdateCount());
            }
        }
        return wb;
    }
}
