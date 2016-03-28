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

import com.puhui.app.po.ProcessAnalysisReport;

/**
 * @comment app全流程分析表excel
 * @author liwang
 * @time 2015年7月23日 下午2:46:01
 */
public class ProcessAnalysisReportExcelUtil {

    public static HSSFWorkbook creatExcel(List<ProcessAnalysisReport> list) throws Exception {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("APP全流程分析表");

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
        sheet.setColumnWidth(23, 4000);
        sheet.setColumnWidth(24, 4000);
        sheet.setColumnWidth(25, 4000);
        sheet.setColumnWidth(26, 4000);
        sheet.setColumnWidth(27, 4000);
        sheet.setColumnWidth(28, 4000);
        sheet.setColumnWidth(29, 4000);
        sheet.setColumnWidth(30, 6000);
        sheet.setColumnWidth(31, 4000);
        sheet.setColumnWidth(32, 4000);
        sheet.setColumnWidth(33, 4000);
        sheet.setColumnWidth(34, 4000);
        sheet.setColumnWidth(35, 4000);
        sheet.setColumnWidth(36, 4000);
        sheet.setColumnWidth(37, 4000);
        sheet.setColumnWidth(38, 4000);
        sheet.setColumnWidth(39, 4000);
        sheet.setColumnWidth(40, 4000);
        sheet.setColumnWidth(41, 4000);
        sheet.setColumnWidth(42, 4000);
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
        cell.setCellValue("员工编号");

        cell = row.createCell(1);
        cell.setCellStyle(style);
        cell.setCellValue("员工姓名");
        
        cell = row.createCell(2);
        cell.setCellStyle(style);
        cell.setCellValue("注册客户数量");

        cell = row.createCell(3);
        cell.setCellStyle(style);
        cell.setCellValue("身份信息完成数量");

        cell = row.createCell(4);
        cell.setCellStyle(style);
        cell.setCellValue("身份信息完成率");

        cell = row.createCell(5);
        cell.setCellStyle(style);
        cell.setCellValue("职业信息完成数量");

        cell = row.createCell(6);
        cell.setCellStyle(style);
        cell.setCellValue("职业信息完成率");

        cell = row.createCell(7);
        cell.setCellStyle(style);
        cell.setCellValue("联系人信息完成数量");

        cell = row.createCell(8);
        cell.setCellStyle(style);
        cell.setCellValue("联系人信息完成率");

        cell = row.createCell(9);
        cell.setCellStyle(style);
        cell.setCellValue("征信报告信息完成数量");

        cell = row.createCell(10);
        cell.setCellStyle(style);
        cell.setCellValue("征信报告信息完成率");

        cell = row.createCell(11);
        cell.setCellStyle(style);
        cell.setCellValue("淘宝信息完成数量");

        cell = row.createCell(12);
        cell.setCellStyle(style);
        cell.setCellValue("淘宝信息完成率");

        cell = row.createCell(13);
        cell.setCellStyle(style);
        cell.setCellValue("运营商信息完成数量");

        cell = row.createCell(14);
        cell.setCellStyle(style);
        cell.setCellValue("运营商信息完成率");

        cell = row.createCell(15);
        cell.setCellStyle(style);
        cell.setCellValue("工资流水信息完成数量");

        cell = row.createCell(16);
        cell.setCellStyle(style);
        cell.setCellValue("工资流水信息完成率");

        cell = row.createCell(17);
        cell.setCellStyle(style);
        cell.setCellValue("身份证照片完成数量");

        cell = row.createCell(18);
        cell.setCellStyle(style);
        cell.setCellValue("身份证照片完成率");

        cell = row.createCell(19);
        cell.setCellStyle(style);
        cell.setCellValue("居住证明信息完成数量");

        cell = row.createCell(20);
        cell.setCellStyle(style);
        cell.setCellValue("居住证明信息完成率");

        cell = row.createCell(21);
        cell.setCellStyle(style);
        cell.setCellValue("工作证明信息完成数量");

        cell = row.createCell(22);
        cell.setCellStyle(style);
        cell.setCellValue("工作证明信息完成率");

        cell = row.createCell(23);
        cell.setCellStyle(style);
        cell.setCellValue("房产证明信息完成数量");

        cell = row.createCell(24);
        cell.setCellStyle(style);
        cell.setCellValue("房产证明信息完成率");

        cell = row.createCell(25);
        cell.setCellStyle(style);
        cell.setCellValue("经营证明信息完成数量");

        cell = row.createCell(26);
        cell.setCellStyle(style);
        cell.setCellValue("经营证明信息完成率");

        cell = row.createCell(27);
        cell.setCellStyle(style);
        cell.setCellValue("经营地址证明信息完成数量");

        cell = row.createCell(28);
        cell.setCellStyle(style);
        cell.setCellValue("经营地址证明信息完成率");

        cell = row.createCell(29);
        cell.setCellStyle(style);
        cell.setCellValue("已婚证明信息完成数量");

        cell = row.createCell(30);
        cell.setCellStyle(style);
        cell.setCellValue("已婚证明信息完成率");

        cell = row.createCell(31);
        cell.setCellStyle(style);
        cell.setCellValue("子女证明信息完成数量");

        cell = row.createCell(32);
        cell.setCellStyle(style);
        cell.setCellValue("子女证明信息完成率");

        cell = row.createCell(33);
        cell.setCellStyle(style);
        cell.setCellValue("学历证明信息完成数量");

        cell = row.createCell(34);
        cell.setCellStyle(style);
        cell.setCellValue("学历证明信息完成率");

        cell = row.createCell(35);
        cell.setCellStyle(style);
        cell.setCellValue("社保公积金信息完成数量");

        cell = row.createCell(36);
        cell.setCellStyle(style);
        cell.setCellValue("社保公积金信息完成率");

        cell = row.createCell(37);
        cell.setCellStyle(style);
        cell.setCellValue("其他证明信息完成数量");

        cell = row.createCell(38);
        cell.setCellStyle(style);
        cell.setCellValue("其他证明信息完成率");

        cell = row.createCell(39);
        cell.setCellStyle(style);
        cell.setCellValue("客户完成录入提交销售质检件数");

        cell = row.createCell(40);
        cell.setCellStyle(style);
        cell.setCellValue("客户完成录入提交销售质检件完成率");

        cell = row.createCell(41);
        cell.setCellStyle(style);
        cell.setCellValue("销售质检完成件数");

        cell = row.createCell(42);
        cell.setCellStyle(style);
        cell.setCellValue("销售质检完成率");
        
        if(CollectionUtils.isNotEmpty(list)&&list.size()>0){
        	for (int i = 0; i < list.size(); i++) {
                ProcessAnalysisReport processAnalysisReport = list.get(i);

                row = sheet.createRow(i + 1);
                row.setHeight((short) 420);
                
                cell = row.createCell(0);
                cell.setCellStyle(style1);
                cell.setCellValue(processAnalysisReport.getSalesNo());

                cell = row.createCell(1);
                cell.setCellStyle(style1);
                cell.setCellValue(processAnalysisReport.getSalesName());
                
                cell = row.createCell(2);
                cell.setCellStyle(style1);
                cell.setCellValue(processAnalysisReport.getRegistCount());

                cell = row.createCell(3);
                cell.setCellStyle(style1);
                cell.setCellValue(processAnalysisReport.getIdentityCount());

                cell = row.createCell(4);
                cell.setCellStyle(style1);
                cell.setCellValue(processAnalysisReport.getIdentityRate());

                cell = row.createCell(5);
                cell.setCellStyle(style1);
                cell.setCellValue(processAnalysisReport.getOccupationCount());

                cell = row.createCell(6);
                cell.setCellStyle(style1);
                cell.setCellValue(processAnalysisReport.getOccupationRate());

                cell = row.createCell(7);
                cell.setCellStyle(style1);
                cell.setCellValue(processAnalysisReport.getContactCount());

                cell = row.createCell(8);
                cell.setCellStyle(style1);
                cell.setCellValue(processAnalysisReport.getContactRate());

                cell = row.createCell(9);
                cell.setCellStyle(style1);
                cell.setCellValue(processAnalysisReport.getCreditReportCount());

                cell = row.createCell(10);
                cell.setCellStyle(style1);
                cell.setCellValue(processAnalysisReport.getCreditReportRate());

                cell = row.createCell(11);
                cell.setCellStyle(style1);
                cell.setCellValue(processAnalysisReport.getTaobaoCount());

                cell = row.createCell(12);
                cell.setCellStyle(style1);
                cell.setCellValue(processAnalysisReport.getTaobaoRate());

                cell = row.createCell(13);
                cell.setCellStyle(style1);
                cell.setCellValue(processAnalysisReport.getCommunicationCount());

                cell = row.createCell(14);
                cell.setCellStyle(style1);
                cell.setCellValue(processAnalysisReport.getCommunicationRate());

                cell = row.createCell(15);
                cell.setCellStyle(style1);
                cell.setCellValue(processAnalysisReport.getPayFlowCount());

                cell = row.createCell(16);
                cell.setCellStyle(style1);
                cell.setCellValue(processAnalysisReport.getPayFlowRate());

                cell = row.createCell(17);
                cell.setCellStyle(style1);
                cell.setCellValue(processAnalysisReport.getIdPhotoCount());

                cell = row.createCell(18);
                cell.setCellStyle(style1);
                cell.setCellValue(processAnalysisReport.getIdPhotoRate());

                cell = row.createCell(19);
                cell.setCellStyle(style1);
                cell.setCellValue(processAnalysisReport.getResidenceCertificateCount());

                cell = row.createCell(20);
                cell.setCellStyle(style1);
                cell.setCellValue(processAnalysisReport.getResidenceCertificateRate());

                cell = row.createCell(21);
                cell.setCellStyle(style1);
                cell.setCellValue(processAnalysisReport.getWorkCertificateCount());

                cell = row.createCell(22);
                cell.setCellStyle(style1);
                cell.setCellValue(processAnalysisReport.getWorkCertificateRate());

                cell = row.createCell(23);
                cell.setCellStyle(style1);
                cell.setCellValue(processAnalysisReport.getEstateCertificateCount());

                cell = row.createCell(24);
                cell.setCellStyle(style1);
                cell.setCellValue(processAnalysisReport.getEstateCertificateRate());

                cell = row.createCell(25);
                cell.setCellStyle(style1);
                cell.setCellValue(processAnalysisReport.getOperatorCertificateCount());

                cell = row.createCell(26);
                cell.setCellStyle(style1);
                cell.setCellValue(processAnalysisReport.getOperatorCertificateRate());

                cell = row.createCell(27);
                cell.setCellStyle(style1);
                cell.setCellValue(processAnalysisReport.getBusinessAddressCertificateCount());

                cell = row.createCell(28);
                cell.setCellStyle(style1);
                cell.setCellValue(processAnalysisReport.getBusinessAddressCertificateRate());

                cell = row.createCell(29);
                cell.setCellStyle(style1);
                cell.setCellValue(processAnalysisReport.getMarriedCount());

                cell = row.createCell(30);
                cell.setCellStyle(style1);
                cell.setCellValue(processAnalysisReport.getMarriedRate());

                cell = row.createCell(31);
                cell.setCellStyle(style1);
                cell.setCellValue(processAnalysisReport.getChildrenCount());

                cell = row.createCell(32);
                cell.setCellStyle(style1);
                cell.setCellValue(processAnalysisReport.getChildrenRate());

                cell = row.createCell(33);
                cell.setCellStyle(style1);
                cell.setCellValue(processAnalysisReport.getDegreeCount());

                cell = row.createCell(34);
                cell.setCellStyle(style1);
                cell.setCellValue(processAnalysisReport.getDegreeRate());

                cell = row.createCell(35);
                cell.setCellStyle(style1);
                cell.setCellValue(processAnalysisReport.getProvidentSocialCount());

                cell = row.createCell(36);
                cell.setCellStyle(style1);
                cell.setCellValue(processAnalysisReport.getProvidentSocialRate());

                cell = row.createCell(37);
                cell.setCellStyle(style1);
                cell.setCellValue(processAnalysisReport.getOtherCount());

                cell = row.createCell(38);
                cell.setCellStyle(style1);
                cell.setCellValue(processAnalysisReport.getOtherRate());

                cell = row.createCell(39);
                cell.setCellStyle(style1);
                cell.setCellValue(processAnalysisReport.getInputCompletionCount());

                cell = row.createCell(40);
                cell.setCellStyle(style1);
                cell.setCellValue(processAnalysisReport.getInputCompletionRate());

                cell = row.createCell(41);
                cell.setCellStyle(style1);
                cell.setCellValue(processAnalysisReport.getCheckCompletionCount());

                cell = row.createCell(42);
                cell.setCellStyle(style1);
                cell.setCellValue(processAnalysisReport.getCheckCompletionRate());

            }
        }
        return wb;
    }
}
