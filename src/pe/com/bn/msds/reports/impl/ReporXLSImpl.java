package pe.com.bn.msds.reports.impl;

import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import pe.com.bn.msds.common.Constante;
import pe.com.bn.msds.model.Programa;
import pe.com.bn.msds.reports.inte.RepoXLS;

@Service
public class ReporXLSImpl implements RepoXLS{
	
	@Override
	public Workbook getReporteExcelProgramas( List<Programa> programas) {
		
		Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Reporte");

        int filaInicio = 0;
        int filaFin = 1;
        int columnaInicio = 0;
        int columnaFin = Constante.TITULOS_COLUMN.size()-1;

        CellRangeAddress cellRangeAddress = new CellRangeAddress(filaInicio, filaFin, columnaInicio, columnaFin);
        sheet.addMergedRegion(cellRangeAddress);

        CellStyle estiloCelda = workbook.createCellStyle();
        estiloCelda.setAlignment(CellStyle.ALIGN_CENTER);
        estiloCelda.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        // Crear la fuente
        Font fonttTITLE = workbook.createFont();
        fonttTITLE.setBold(true);
        fonttTITLE.setFontHeightInPoints((short) 30);

        // Asignar la fuente al estilo de celda
        estiloCelda.setFont(fonttTITLE);
        
        // CREANDO CELDAS PARA COMBINAR 
        for (int i = 0; i <= filaFin; i++) {
        	Row  fila = sheet.createRow(i);
        	for (int j = columnaInicio; j <columnaFin; j++) {
        		Cell celda = fila.createCell(j);
                celda.setCellStyle(estiloCelda);

			}	
		}
        String fechaFormateada = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        Cell celdaSuperiorIzquierda = sheet.getRow(filaInicio).getCell(columnaInicio);
        celdaSuperiorIzquierda.setCellValue(Constante.CONST_TITULO_REPORTE+"     Fecha: "+fechaFormateada);


    	
        CellStyle estiloTitulo = workbook.createCellStyle();
     // Alineación horizontal y vertical centradas
        estiloTitulo.setAlignment(CellStyle.ALIGN_CENTER);
        estiloTitulo.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
     // Establecer el fondo rojo
        estiloTitulo.setFillForegroundColor(IndexedColors.DARK_RED.getIndex());
        estiloTitulo.setFillPattern(CellStyle.SOLID_FOREGROUND);

        // letra 
         Font font =   workbook.createFont();
	    Color color = new Color(255,255,255);//RGB color
        ((XSSFFont) font).setColor(new XSSFColor(color));
        font.setBold(true); // Establecer la fuente en negrita
        estiloTitulo.setFont(font);

        
        // CREANDO CELDAS PARA COLUMNAS 
      
        	Row  filaTitulos = sheet.createRow(filaFin+1);
        	for (int j = columnaInicio; j <Constante.TITULOS_COLUMN.size(); j++) {
        		Cell celda = filaTitulos.createCell(j);
                celda.setCellStyle(estiloTitulo);
                celda.setCellValue(Constante.TITULOS_COLUMN.get(j));
              
			}	
		
        
 
        
        	 // Completar datos Excel
            List<String> atributos = getAttributeNames(Programa.class);
            Method[] metodos = new Method[atributos.size()];
         // Obtener métodos de los atributos
            for (int j = 0; j < atributos.size(); j++) {
                try {
                    metodos[j] = Programa.class.getMethod("get" + capitalize(atributos.get(j)));
                } catch (NoSuchMethodException | SecurityException e) {
                    e.printStackTrace();
                }
            }
      
      for (int i = 1; i < programas.size()+1; i++) {
        Row  filaRegistro = sheet.createRow(filaTitulos.getRowNum() + i);
        
        for (int j = 0; j < atributos.size(); j++) {
	        Cell celda = filaRegistro.createCell(j);
	      
            Object valor = null;
            try {
                valor = metodos[j].invoke(programas.get(i - 1));
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
            }
            if (j == 0) {
                celda.setCellValue(i);

			}else{
                celda.setCellValue((String)valor);
			}

		} 
	}
        
      // Ajustar el tamaño de las columnas
      for (int j = columnaInicio; j < Constante.TITULOS_COLUMN.size(); j++) {
          sheet.autoSizeColumn(j);
      }
        return workbook;
	}

	
	
	
	private String capitalize(String str) {
	        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
		}

	public static List<String> getAttributeNames(Class<?> clazz) {
	        List<String> attributeNames = new ArrayList<String>();
	        for (Field field : clazz.getDeclaredFields()) {
	            attributeNames.add(field.getName());
	        }
	        return attributeNames;
	    }
}
