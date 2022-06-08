package com.company;

import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;

public class Parser {

    private ArrayList<double[]> generalList;

    public ArrayList<double[]> getGeneralList() {
        return this.generalList;
    }


    public void reader(String s) throws IOException {
        XSSFWorkbook wb = new XSSFWorkbook(s);
        XSSFSheet sh = wb.getSheetAt(0);
        XSSFRow row = sh.getRow(1);


        ArrayList<double[]> generalList = new ArrayList<>();

        for (int j = 0; j < row.getLastCellNum(); j++) {
            double[] general = new double[sh.getLastRowNum()];
            for (int i = 1; i <= sh.getLastRowNum(); i++) {
                general[i - 1] = sh.getRow(i).getCell(j).getNumericCellValue();

            }

            generalList.add(general);
        }


        this.generalList = generalList;

        for (double[] gene: generalList
        ) {
            for (int i = 0; i < gene.length; i++) {
                System.out.print(gene[i] + " ");
            }
            System.out.println();
        }

        wb.close();


    }
}
