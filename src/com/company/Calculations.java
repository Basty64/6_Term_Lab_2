package com.company;

import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.correlation.Covariance;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.util.LinkedHashMap;

public class Calculations {


    private LinkedHashMap<String, Double[]> calc;

    public void calc (Parser p) {

        LinkedHashMap<String, Double[]> calc = new LinkedHashMap<String, Double[]>();
        this.calc = calc;

        Double[] a = new Double[3];
        Double[] a1 = new Double[3];
        Double[] a2 = new Double[3];
        Double[] a3 = new Double[3];
        Double[] a4 = new Double[3];
        Double[] a5 = new Double[3];
        Double[] a6 = new Double[3];
        Double[] a7 = new Double[3];
        Double[] a8 = new Double[3];
        Double[] a9 = new Double[3];
        Double[] a10 = new Double[3];
        for (int i = 0; i < 3; i++) {

            a[i] = StatUtils.geometricMean(p.getGeneralList().get(i));
        }
        calc.put("Геометрическое среднее", a);

        for (int i = 0; i < 3; i++) {
            a1[i] = StatUtils.mean(p.getGeneralList().get(i));
        }
        calc.put("Арифметическое среднее", a1);

        for (int i = 0; i < 3; i++) {
            a2[i] = Math.sqrt(StatUtils.variance(p.getGeneralList().get(i)));
        }
        calc.put("Оценка стандартного отклонения", a2);

        for (int i = 0; i < 3; i++) {
            a3[i] = StatUtils.max(p.getGeneralList().get(i)) - StatUtils.min(p.getGeneralList().get(i));
        }
        calc.put("Размах выборки", a3);

        calc.put("Коэффициент ковариации для x и y", new Double[]{
                new Covariance().covariance(p.getGeneralList().get(0), p.getGeneralList().get(1)),
                new Covariance().covariance(p.getGeneralList().get(0), p.getGeneralList().get(1)),
                0.0});

        calc.put("Коэффициент ковариации для x и z", new Double[]{
                new Covariance().covariance(p.getGeneralList().get(0), p.getGeneralList().get(2)),
                0.0,
                new Covariance().covariance(p.getGeneralList().get(0), p.getGeneralList().get(2))});

        calc.put("Коэффициент ковариации для y и z", new Double[]{
                0.0,
                new Covariance().covariance(p.getGeneralList().get(2), p.getGeneralList().get(1)),
                new Covariance().covariance(p.getGeneralList().get(1), p.getGeneralList().get(2))});

        for (int i = 0; i < 3; i++) {
            a4[i] = (double) p.getGeneralList().get(i).length;
        }
        calc.put("Количество элементов выборки", a4);

        for (int i = 0; i < 3; i++) {
            a5[i] = Math.sqrt(StatUtils.variance(p.getGeneralList().get(i))) / Math.abs(StatUtils.mean(p.getGeneralList().get(i)));
        }
        calc.put("Коэффициент вариации", a5);

        for (int i = 0; i < 3; i++) {
            a6[i] = StatUtils.mean(p.getGeneralList().get(i)) + (new TDistribution(p.getGeneralList().get(i).length - 1).inverseCumulativeProbability(0.95) * Math.sqrt(StatUtils.variance(p.getGeneralList().get(i)))) / Math.sqrt(p.getGeneralList().get(i).length);
        }
        calc.put("+Доверительный интервал", a6);

        for (int i = 0; i < 3; i++) {
            a7[i] = StatUtils.mean(p.getGeneralList().get(i)) - (new TDistribution(p.getGeneralList().get(i).length - 1).inverseCumulativeProbability(0.95) * Math.sqrt(StatUtils.variance(p.getGeneralList().get(i)))) / Math.sqrt(p.getGeneralList().get(i).length);
        }
        calc.put("-Доверительный интервал", a7);

        for (int i = 0; i < 3; i++) {
            a8[i] = StatUtils.variance(p.getGeneralList().get(i));
        }
        calc.put("Оценка дисперсии", a8);

        for (int i = 0; i < 3; i++) {
            a9[i] = StatUtils.max(p.getGeneralList().get(i));
        }
        calc.put("Максимум выборки", a9);

        for (int i = 0; i < 3; i++) {
            a10[i] = StatUtils.min(p.getGeneralList().get(i));
        }
        calc.put("Минимум выборки", a10);


    }

    public LinkedHashMap<String,Double[]> getCalc() {

        return this.calc;
    }


    public void writer(String path, Calculations calc) throws Exception {

        int i = 1;

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sh = wb.createSheet("Бондаренко");
        XSSFRow r = sh.createRow(0);
        r.createCell(0).setCellValue("Термины:");
        r.createCell(1).setCellValue("x");
        r.createCell(2).setCellValue("y");
        r.createCell(3).setCellValue("z");

        for (String s : getCalc().keySet())
        {
            XSSFRow row = sh.createRow(i);
            row.createCell(0).setCellValue(s);
            row.createCell(1).setCellValue(getCalc().get(s)[0]);
            row.createCell(2).setCellValue(getCalc().get(s)[1]);
            row.createCell(3).setCellValue(getCalc().get(s)[2]);
            i++;

        }
        FileOutputStream f = new FileOutputStream("path.xlsx");
        wb.write(f);
        wb.close();
    }
}